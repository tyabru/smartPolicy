<template>
  <div class="app-container">
    <search-form-bar>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="案件编码" prop="caseCode">
          <el-input v-model="queryParams.caseCode" placeholder="请输入案件编码" clearable/>
        </el-form-item>
        <el-form-item label="纠纷人员" prop="participantsInDisputes">
          <el-input v-model="queryParams.participantsInDisputes" placeholder="请输入参与纠纷人员" clearable/>
        </el-form-item>
        <el-form-item label="调解警员" prop="participatingPolice">
          <el-input v-model="queryParams.participatingPolice" placeholder="请输入参与调解警员" clearable/>
        </el-form-item>
        <el-form-item label="调解方式" prop="mediationMode">
          <el-select  v-model="queryParams.mediationMode" placeholder="请选择调解方式" style="width: 100%">
            <el-option v-for="item in dict.type['mediation_mode']" :key="item.value" :value="item.value" :label="item.label"/>
          </el-select>
        </el-form-item>
      </el-form>
      <template #btn>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </template>
    </search-form-bar>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['contradiction:MediationRecords:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['contradiction:MediationRecords:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['contradiction:MediationRecords:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['contradiction:MediationRecords:export']">导出</el-button>
      </el-col>
      <right-toolbar style="padding-right: 19px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="MediationRecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案件编码" align="center" prop="caseCode" />
      <el-table-column label="参与纠纷人员" align="center" prop="participantsInDisputesName" />
      <el-table-column label="调解警员" align="center" prop="participatingPolice" />
      <el-table-column label="调解方式" align="center" prop="mediationMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mediation_mode" :value="scope.row.mediationMode"/>
        </template>
      </el-table-column>
      <el-table-column label="调解时间" align="center" prop="mediateTime" width="180"/>
      <el-table-column label="调解地点" align="center" prop="mediationLocation" />
      <el-table-column label="调解结果" align="center" prop="mediationResults" />
      <el-table-column label="调解会议描述" align="center" prop="mediationMeetingDescription" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['contradiction:MediationRecords:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['contradiction:MediationRecords:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['contradiction:MediationRecords:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改纠纷调解记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="span">
            <el-form-item label="案件编码" prop="caseCode">
              <el-select  v-model="form.caseCode" placeholder="请选择案件编码" style="width: 100%" @change="changeContradiction(form.caseCode)">
                <el-option v-for="item in contradictionList" :key="item.caseCode" :label="item.caseCode" :value="item.caseCode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="纠纷人员" prop="participantsInDisputes">
              <el-select  v-model="form.participantsDisputesList" placeholder="请选择纠纷人员" style="width: 100%" multiple>
                <el-option v-for="item in personnelList" :key="item.id" :label="item.peopleName" :value="item.id">
                  {{item.cerNo+"----------"+item.peopleName}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="参与警员" prop="participatingPolice">
              <el-select  v-model="form.participatingPoliceList" placeholder="请选择参与警员" style="width: 100%" multiple>
                <el-option v-for="item in policelList" :key="item.policeNumber" :label="item.policeName" :value="item.policeNumber">
                  {{item.policeNumber+"----------"+item.policeName}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="调解时间" prop="mediateTime">
              <el-date-picker style="width: 325px;" clearable v-model="form.mediateTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择调解时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="调解方式" prop="mediationMode">
              <el-select  v-model="form.mediationMode" placeholder="请选择调解方式" style="width: 100%">
                <el-option v-for="item in dict.type['mediation_mode']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="调解地点" prop="mediationLocation">
              <el-input v-model="form.mediationLocation" placeholder="请输入调解地点" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="调解结果" prop="mediationResults">
              <el-input v-model="form.mediationResults" placeholder="请输入调解结果" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="会议描述" prop="mediationMeetingDescription">
              <el-input v-model="form.mediationMeetingDescription" placeholder="请输入调解会议描述" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="选择文件" prop="filePath">
              <el-upload  class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple
                :accept="accept" :http-request="uploadFile" :on-success="handleSuccess" :on-error="handleError" :on-progress="handleProgress" 
                :on-change="handleChange" :auto-upload="true" :on-exceed="handleExceed" :before-upload="beforeUpload" 
                :limit="1" :on-preview="handlePreview" :show-file-list="true">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              </el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-show="isDisplay" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMediationRecords, getMediationRecords, delMediationRecords, addMediationRecords, updateMediationRecords } from "@/api/contradiction/MediationRecords";
import { listInformation } from "@/api/polices/policeInformation";
import { uploadFile } from "@/api/polices/fileManagements";
import { listContradiction } from "@/api/contradiction/contradiction";
import { listPersonnel, getContradictionPersonByCaseCode } from "@/api/contradiction/personnel";
import { getUserProfile } from "@/api/system/user";
import DictTag from '@/components/DictTag';

export default {
  name: "MediationRecords",
  dicts: ['mediation_mode'],
  components: { DictTag },
  props: {
    accept: {
      type: String,
      default: ''
    },
    size: {
      type: Number,
      default: 50
    },
    fileType: {
      type: Array,
      default: () => [
        'pdf', 'doc', 'docx', 'xls', 'xlsx', 'txt', 'png', 'jpg', 'bmp', 'jpeg', 'ppt', 'rar', 'zip'
      ]
    },
  },
  data() {
    return {
      participantsInDisputes: [],
      participatingPolice: [],
      user: null,
      span: 12,
      //是否显示
      isDisplay: false,
      //是否禁用修改
      disabled: true,
      //是否显示
      isDisplay: false,
      //是否禁用修改
      disabled: true,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 纠纷调解记录表格数据
      MediationRecordsList: [],
      //纠纷信息集合
      contradictionList: [],
      //纠纷参与人员
      personnelList: [],
      //警员信息集合
      policelList: [],
      //文件集合
      fileList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseCode: null,
        participantsInDisputes: null,
        participatingPolice: null,
        mediationMode: null,
      },
      // 表单参数
      form: {
        operateName: null,
        participantsDisputesList: [],
        participatingPoliceList: [],
      },
      // 表单校验
      rules: {
        caseCode: [
          { required: true, message: "案件编码不能为空", trigger: "blur" }
        ],
        mediateTime: [
          { required: true, message: "调解时间不能为空", trigger: "blur" }
        ],
        participantsDisputesList: [
          { required: true, message: "参与纠纷人员不能为空", trigger: "blur" }
        ],
        participatingPoliceList: [
          { required: true, message: "参与调解警员不能为空", trigger: "blur" }
        ],
        operateType: [
          { required: true, message: "操作类型0新增1修改不能为空", trigger: "change" }
        ],
        operateTime: [
          { required: true, message: "操作时间不能为空", trigger: "blur" }
        ],
        mediationMode: [
          { required: true, message: "调解方式不能为空", trigger: "blur" }
        ],
        mediationLocation: [
          { required: true, message: "调解地点不能为空", trigger: "blur" }
        ],
        mediationResults: [
          { required: true, message: "会议描述不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser () {
      getUserProfile().then(response => {
        this.user = response.data;
        this.form.operateName = response.data.nickName;
      })
      this.getList();
      this.getListContradiction();
      this.getListPersonnel();
      this.getListPolices();
    },
    /** 查询纠纷调解记录列表 */
    getList() {
      this.loading = true;
      listMediationRecords(this.queryParams).then(response => {
        this.MediationRecordsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 查询矛盾纠纷信息
    getListContradiction() {
      let form = {
        deptId: null
      }
      listContradiction(form).then(response => {
        this.contradictionList = response.rows;
      })
    },
    // 查询矛盾纠纷参与人员
    getListPersonnel() {
      let form = {
        deptId: null
      }
      listPersonnel(form).then(response => {
        this.personnelList = response.rows;
      })
    },
    // 查询警员信息
    getListPolices() {
      let form = {
        deptId: null
      }
      listInformation(form).then(response => {
        this.policelList = response.rows;
      })
    },
    //根据案件编码查询参与人员
    changeContradiction(caseCode) {
      getContradictionPersonByCaseCode(caseCode).then(response => {
        this.personnelList = response.rows;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        caseCode: null,
        mediateTime: null,
        participantsInDisputes: null,
        participatingPolice: null,
        mediationMode: null,
        mediationLocation: null,
        mediationResults: null,
        mediationMeetingDescription: null,
        createTime: null,
        updateTime: null,
        operateType: null,
        operateName: this.form.operateName,
        operateTime: null,
        remark: null,
        participantsDisputesList: [],
        participatingPoliceList: [],
        filePath: null,
        fileName: null,
      };
      this.resetForm("form");
    },
    handleChange(file, fileList) {
      this.fileList = fileList.slice(-3);
    },

    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择${this.limit}个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    /**
     * 上传之前的回调
     * @param {*} file
     */
     beforeUpload(file) {
      if (file.type !== '' || file.type !== null || file.type !== undefined) {
        // 截取文件的后缀，判断文件类型
        const FileExt = file.name.replace(/.+\./, '').toLowerCase()
        // 计算文件的大小
        const isLt5M = file.size / 1024 / 1024 < this.size // 这里做文件大小限制
        // 如果大于50M
        if (!isLt5M) {
          this.$message.error('上传文件大小不能超过 50MB!')
          return false
        }
        // 如果文件类型不在允许上传的范围内
        if (this.fileType.includes(FileExt)) {
          return true
        } else {
          this.$message.error('上传文件格式不正确!')
          return false
        }
      }
    },
    /**
     * 点击文件列表中已上传的文件时的钩子
     * @param {*} file
     */
    handlePreview(file) {
      this.url = file.url
      this.$refs.preview.clickHandler()
    },
    /**
     * 下载
     * @param {*} file
     */
    handleDownload(file) {
 
    },
    /**
     * 文件列表移除文件时的钩子
     * @param {*} file
     */
    handleRemove(file) {
      this.$emit('remove', file)
    },
    /**
     * 文件上传成功时的钩子
     * @param {*} response
     */
    handleSuccess(response, file,fileList) {
      this.$emit('success', response, file)
    },
    /**
     * 文件上传失败时的钩子
     * @param {*} _err
     */
    handleError(response, file) {
      this.$emit('error', response, file)
    },
    /**
     * 文件上传时的钩子[进度]
     * @param {*} file
     */
    handleProgress(file) {
      console.log(file, '进度')
    },
    /**
     * 自定义上传
     * @param {*} param
     */
    uploadFile(param) {
      const loading = this.$loading({
        lock: true,
        text: '正在读取,请稍后...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      // 获取上传的文件名
      const file = param.file
      // 发送请求的参数格式为FormData
      const formData = new FormData()
      // return
      formData.append('file', file)
      formData.append('agencyId', this.agencyId)
      for (const key in this.data) {
        formData.append(key, this.data[key])
      }
      if (this.form.caseCode == null || this.form.caseCode == "") {
        this.$message.error('文件夹名不能为空');
        loading.close()
        return false;
      }
      // if (this.form.deptId == null) {
      //   this.$message.error('所属部门不能为空');
      //   loading.close()
      //   return false;
      // }
      //调用param中的钩子函数处理各种情况，这样就可以用在组件中用钩子了。也可以用res.code==200来进行判断处理各种情况
      uploadFile(formData,this.form.caseCode,this.user.deptId).then(res => {
        loading.close()
        if (res.code === 200) {
          this.form.filePath = res.filePath
          this.form.fileName = res.fileName
          this.$message.success('上传成功!')
          param.onSuccess(res, file)
        } else {
          this.$message.error('上传失败')
          param.onError(res, file)
        }
      }).catch(err => {
        loading.close()
        this.$message.error('上传失败')
        param.onError(err)
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.disabled = false;
      this.isDisplay = true;
      this.title = "添加纠纷调解记录";
    },
    /** 查看按钮操作 */
    handleView(row) {
      this.reset();
      const id = row.id || this.ids
      getMediationRecords(id).then(response => {
        this.form = response.data;
        this.form.participantsDisputesList = this.stringToArry(this.form.participantsInDisputes);
        this.form.participatingPoliceList = this.stringToArry(this.form.participatingPolice);
        this.open = true;
        this.disabled = true;
        this.isDisplay = false;
        this.title = "查看纠纷调解记录";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMediationRecords(id).then(response => {
        this.form = response.data;
        this.form.participantsDisputesList = this.stringToArry(this.form.participantsInDisputes);
        this.form.participatingPoliceList = this.stringToArry(this.form.participatingPolice);
        this.open = true;
        this.disabled = false;
        this.isDisplay = true;
        this.title = "修改纠纷调解记录";
      });
    },
    stringToArry (str) {
      let list = [];
      let teamList = str.split(',')
      for (let i = 0; i < teamList.length; i++) {
        list.push(teamList[i])
      }
      return list = list.filter((item) => {
        return item !== ''
      })
    },
    arrayToString() {
      let participatingPolice = "";
      for (let i = 0; i < this.form.participatingPoliceList.length; i++) {
        if (i == this.form.participatingPoliceList.length-1) {
          participatingPolice += this.form.participatingPoliceList[i];
        } else {
          participatingPolice += this.form.participatingPoliceList[i] + ",";
        }
      }
      this.form.participatingPolice = participatingPolice;
      let participantsInDisputes = "";
      for (let i = 0; i < this.form.participantsDisputesList.length; i++) {
        if (i == this.form.participantsDisputesList.length-1) {
          participantsInDisputes = this.form.participantsDisputesList[i];
        } else {
          participantsInDisputes = this.form.participantsDisputesList[i] + ",";
        }
      }
      this.form.participantsInDisputes = participantsInDisputes;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.arrayToString();
          if (this.form.id != null) {
            updateMediationRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMediationRecords(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除纠纷调解记录编号为"' + ids + '"的数据项？').then(function() {
        return delMediationRecords(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('contradiction/MediationRecords/export', {
        ...this.queryParams
      }, `MediationRecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.el-upload .el-upload-dragger {
  width: 120%;
  height: 100%;
}
</style>