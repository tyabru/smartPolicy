<template>
  <div class="app-container">
    <search-form-bar>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="案件编码" prop="caseCode">
          <el-input v-model="queryParams.caseCode" placeholder="请输入纠纷标题/内容" clearable/>
        </el-form-item>
        <el-form-item label="标题内容" prop="title">
          <el-input v-model="queryParams.title" placeholder="请输入纠纷标题/内容" clearable/>
        </el-form-item>
        <el-form-item label="纠纷级别" prop="disputeLevel">
          <el-select v-model="queryParams.disputeLevel" class="width-100Rate" placeholder="请选择纠纷级别" >
            <el-option v-for="item in dict.type['important_level']" :key="item.value" :value="item.value" :label="item.label"/>
          </el-select>
        </el-form-item>
        <el-form-item label="报警方式" prop="alarmMode">
          <el-select v-model="queryParams.alarmMode" class="width-100Rate" placeholder="请选择报警方式" >
            <el-option v-for="item in dict.type['alarm_mode']" :key="item.value" :value="item.value" :label="item.label"/>
          </el-select>
        </el-form-item>
        <el-form-item label="调解进度" prop="mediationProgress">
          <el-select v-model="queryParams.mediationProgress" class="width-100Rate" placeholder="请选择调解进度" >
            <el-option v-for="item in dict.type['mediation_progress']" :key="item.value" :value="item.value" :label="item.label"/>
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
          v-hasPermi="['contradiction:contradiction:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['contradiction:contradiction:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['contradiction:contradiction:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['contradiction:contradiction:export']">导出</el-button>
      </el-col>
      <right-toolbar style="padding-right: 19px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contradictionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案件编码" align="center" prop="caseCode" />
      <el-table-column label="标题内容" align="center" prop="title" />
      <el-table-column label="纠纷类型" align="center" prop="disputeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dispute_type" :value="scope.row.disputeType"/>
        </template>
      </el-table-column>
      <el-table-column label="纠纷级别" align="center" prop="disputeLevel" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.important_level" :value="scope.row.disputeLevel"/>
        </template>
      </el-table-column>
      <el-table-column label="发生时间" align="center" prop="startTime" width="150px"/>
      <el-table-column label="结束时间" align="center" prop="endTime" width="150px"/>
      <el-table-column label="发生地点" align="center" prop="place" />
      <el-table-column label="报警方式" align="center" prop="alarmMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.alarm_mode" :value="scope.row.alarmMode"/>
        </template>
      </el-table-column>
      <el-table-column label="参与人数" align="center" prop="partakeNum" />
      <el-table-column label="调解进度" align="center" prop="mediationProgress">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mediation_progress" :value="scope.row.mediationProgress"/>
        </template>
      </el-table-column>
      <el-table-column label="警员警号" align="center" prop="policeNum" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['contradiction:contradiction:query']">详情</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['contradiction:contradiction:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['contradiction:contradiction:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改矛盾纠纷基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <h4 class="title-h4">矛盾纠纷基本信息</h4>
        <el-row>
          <el-col :span="24">
            <el-form-item label="标题内容" prop="title">
              <el-input v-model="form.title" type="textarea" placeholder="请输入纠纷内容" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="案件编码" prop="caseCode">
              <el-input v-model="form.caseCode" placeholder="请输入案件编码" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="调节警员" prop="policeNum">
              <el-select  v-model="form.policeNum" placeholder="请选择调节警员" style="width: 100%">
                <el-option v-for="item in policeInformationList" :key="item.policeNumber" :label="item.policeName" :value="item.policeNumber">
                {{item.policeName+"----------"+item.policeNumber}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="纠纷级别" prop="disputeLevel">
              <el-select v-model="form.disputeLevel" class="width-100Rate" placeholder="请选择纠纷级别" >
                <el-option v-for="item in dict.type['important_level']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="纠纷类型" prop="disputeType">
              <el-select v-model="form.disputeType" class="width-100Rate" placeholder="请选择纠纷级别" >
                <el-option v-for="item in dict.type['dispute_type']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="发生时间" prop="startTime">
              <el-date-picker style="width: 100%;" clearable v-model="form.startTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择发生时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker style="width: 100%;" clearable v-model="form.endTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择结束时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="发生地点" prop="place">
              <el-input v-model="form.place" placeholder="请输入纠纷发生地点" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="报警方式" prop="alarmMode">
              <el-select v-model="form.alarmMode" class="width-100Rate" placeholder="请选择报警方式" >
                <el-option v-for="item in dict.type['alarm_mode']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="参与人数" prop="partakeNum">
              <el-input v-model="form.partakeNum" placeholder="请输入参与人数" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="调解进度" prop="mediationProgress">
              <el-select v-model="form.mediationProgress" class="width-100Rate" placeholder="请选择调解进度" >
                <el-option v-for="item in dict.type['mediation_progress']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="经度" prop="longitude">
              <el-input v-model="form.longitude" placeholder="请输入经度" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="纬度" prop="latitude">
              <el-input v-model="form.latitude" placeholder="请输入纬度" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="是否转办" prop="isTransfer">
              <el-select v-model="form.isTransfer" class="width-100Rate" placeholder="请选择是否转办" >
                <el-option v-for="item in dict.type['sys_yes_no']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="转办人" prop="transferPoliceNum"
              :rules="[{ required: form.isTransfer == 'Y', message: '当纠纷转办时,转办人不能为空', trigger: 'blur' }]">
              <el-select  v-model="form.transferPoliceNum" placeholder="请选择转办人" style="width: 100%" :disabled="form.isTransfer != 'Y'">
                <el-option v-for="item in policeInformationList" :key="item.policeNumber" :label="item.policeName" :value="item.policeNumber">
                {{item.policeName+"----------"+item.policeNumber}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="转办时间" prop="transferTime"
              :rules="[{ required: form.isTransfer == 'Y', message: '当纠纷转办时,转办时间不能为空', trigger: 'blur' }]">
              <el-date-picker style="width: 100%" :disabled="form.isTransfer != 'Y'" clearable v-model="form.transferTime" 
                type="date" value-format="yyyy-MM-dd" placeholder="请选择转办时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="是否成功" prop="isMediateSuccessful">
              <el-select v-model="form.isMediateSuccessful" class="width-100Rate" placeholder="请选择是否成功调解" >
                <el-option v-for="item in dict.type['sys_yes_no']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="结果描述" prop="mediateResultDescription">
              <el-input v-model="form.mediateResultDescription" placeholder="请输入调解最终结果描述" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="部门" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择案件归属部门" />
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
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listContradiction, getContradiction, delContradiction, addContradiction, updateContradiction, 
  getContradictionByCaseCode } from "@/api/contradiction/contradiction";
import { uploadFile } from "@/api/polices/fileManagements";
import { getUserProfile, deptTreeSelect } from "@/api/system/user";
import { listInformation } from "@/api/polices/policeInformation";
import DictTag from '@/components/DictTag';
import { Encrypt } from '@/utils/Aescrypt';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Contradiction",
  dicts: ['sys_yes_no', 'dispute_type', 'alarm_mode', 'mediation_progress', 'important_level'],
  components: { DictTag, Treeselect },
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
    const validCaseCode = (rule, value, callback)=>  {
      if(value == null || value == ""){
        callback("案件编码不能为空！");
      }else {
        getContradictionByCaseCode(value).then(response => {
          if (this.form.id != null && response.data != null && this.form.id != response.data.id) {
            callback("案件编码已存在！")
          } else if (this.form.id == null && response.data != null){
            callback("案件编码已存在！")
          } else {
            callback()
          }
        })
      }
    }
    return {
      user: null,
      span: 12,
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
      // 矛盾纠纷基本信息表格数据
      contradictionList: [],
      //警员信息集合
      policeInformationList: [],
      // 部门树选项
      deptOptions: [],
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
        title: null,
        disputeType: null,
        disputeLevel: null,
        alarmMode: null,
        mediationProgress: null,
        isMediateSuccessful: null,
      },
      // 表单参数
      form: {
        disputeType: '0',
        disputeLevel: "green",
        mediationProgress: '0',
        alarmMode: '0',
        isTransfer: "N",
        isMediateSuccessful: "N",
        operateName: null,
      },
      // 表单校验
      rules: {
        deptId: [{ required: true, message: "部门ID不能为空", trigger: "blur" }],
        caseCode: [{ required: true, message: "案件编码不能为空", trigger: "blur" },
                   { validator: validCaseCode, trigger: "blur" }
         ],
        title: [{ required: true, message: "标题或内容不能为空", trigger: "blur" }],
        disputeLevel: [{ required: true, message: "纠纷级别不能为空", trigger: "blur" }],
        startTime: [{ required: true, message: "发生时间不能为空", trigger: "blur" }],
        endTime: [{ required: true, message: "结束时间不能为空", trigger: "blur" }],
        place: [{ required: true, message: "发生地点不能为空", trigger: "blur" }],
        alarmMode: [{ required: true, message: "报警方式不能为空", trigger: "blur" }],
        partakeNum: [{ required: true, message: "参与人数不能为空", trigger: "blur" }],
        mediationProgress: [{ required: true, message: "调解进度不能为空", trigger: "blur" }],
        policeNum: [{ required: true, message: "参与人数不能为空", trigger: "blur" }],
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
      this.getPoliceList();
      this.getDeptTree();
    },
    /** 查询矛盾纠纷基本信息列表 */
    getList() {
      this.loading = true;
      listContradiction(this.queryParams).then(response => {
        this.contradictionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //查询警员信息
    getPoliceList() {
      const form = {
        deptId : null
      }
      listInformation(form).then(response => {
        this.policeInformationList = response.rows;
      })
    },
    /** 查询部门下拉树结构 */
    getDeptTree() {
      deptTreeSelect().then(response => {
        this.deptOptions = response.data;
      });
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
        deptId: null,
        caseCode: null,
        title: null,
        disputeType: this.form.disputeType,
        disputeLevel: this.form.disputeLevel,
        startTime: null,
        endTime: null,
        place: null,
        longitude: null,
        latitude: null,
        alarmMode: this.form.alarmMode,
        partakeNum: null,
        mediationProgress: this.form.mediationProgress,
        isMediateSuccessful: this.form.isMediateSuccessful,
        mediateResultDescription: null,
        policeNum: null,
        isTransfer: this.form.isTransfer,
        transferPoliceNum: null,
        transferTime: null,
        sourcePlatform: null,
        createTime: null,
        updateTime: null,
        operateType: null,
        operateName: this.user.nickName,
        operateTime: null,
        remark: null
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
      this.title = "添加矛盾纠纷基本信息";
    },
    //详情按钮
    handleView(row) {
      let title = "查看矛盾纠纷信息详情"
      const params = { };
      params['sq_pk'] = Encrypt(JSON.stringify({id: row.id | 'unknown'}));
      this.$tab.openPage(title, '/contradiction/contradictionDetails', params);
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getContradiction(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改矛盾纠纷基本信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          console.log(this.form)
          if (this.form.id != null) {
            updateContradiction(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContradiction(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除矛盾纠纷案件编码为"' + row.caseCode + '"的数据项？').then(function() {
        return delContradiction(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('contradiction/contradiction/export', {
        ...this.queryParams
      }, `contradiction_${new Date().getTime()}.xlsx`)
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