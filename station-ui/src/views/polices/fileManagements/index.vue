<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件名称" prop="fileName">
        <el-input v-model="queryParams.fileName" placeholder="请输入文件名称" clearable/>
      </el-form-item>
      <el-form-item label="存储路径" prop="filePath">
        <el-input v-model="queryParams.filePath" placeholder="请输入文件存储路径" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['polices:fileManagements:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['polices:fileManagements:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['polices:fileManagements:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['polices:fileManagements:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fileManagementsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="文件存储路径" align="center" prop="filePath" />
      <el-table-column label="文件描述" align="center" prop="fileDescription" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleFileDownload(scope.row)">下载</el-button>
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleSelect(scope.row)" 
            v-hasPermi="['polices:fileManagements:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['polices:fileManagements:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['polices:fileManagements:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改日常文件管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="文件夹名" prop="folderName">
              <el-input v-model="form.folderName" placeholder="请输入存储的文件夹名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属部门" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="文件描述" prop="fileDescription">
              <el-input v-model="form.fileDescription" placeholder="请输入文件描述" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="选择文件" prop="remark">
              <el-upload  class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple
                :accept="accept"
                :http-request="uploadFile"
                :on-success="handleSuccess"
                :on-error="handleError"
                :on-progress="handleProgress"
                :on-exceed="handleExceed"
                :before-upload="beforeUpload"
                :limit="1"
                :on-preview="handlePreview"
                :show-file-list="false">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              </el-upload>
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
import { listFileManagements, getFileManagements, delFileManagements, addFileManagements, updateFileManagements, uploadFile, fileDownload } from "@/api/polices/fileManagements";
import { getUserProfile, deptTreeSelect } from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "FileManagements",
  components: { Treeselect},
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
      disabled: true,
      user: null,
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
      // 日常文件管理表格数据
      fileManagementsList: [],
      // 部门树选项
      deptOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        fileName: null,
        filePath: null,
        folderName: null,
        fileDescription: null,
        deleteTime: null,
        operateTime: null,
        operateName: null,
        operateType: null,
      },
      // 表单参数
      form: {
        operateName: null
      },
      // 表单校验
      rules: {
        folderName: [{ required: true, message: "文件夹名称不能为空", trigger: "blur" }],
        deptId: [{ required: true, message: "所属部门不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getUser();
    this.getDeptTree();
  },
  methods: {
    getUser () {
      getUserProfile().then(response => {
        this.user = response.data;
        //this.queryParams.deptId = response.data.deptId;
        this.form.operateName = response.data.nickName;
      })
      this.getList();
    },
    /** 查询日常文件管理列表 */
    getList() {
      this.loading = true;
      listFileManagements(this.queryParams).then(response => {
        this.fileManagementsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        fileName: null,
        filePath: null,
        folderName: null,
        fileDescription: null,
        createTime: null,
        updateTime: null,
        deleteTime: null,
        operateTime: null,
        operateName: this.form.operateName,
        operateType: null,
        remark: null
      };
      this.resetForm("form");
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
    handleSuccess(response, file) {
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
      if (this.form.folderName == null || this.form.folderName == "") {
        this.$message.error('文件夹名不能为空');
        loading.close()
        return false;
      }
      if (this.form.deptId == null) {
        this.$message.error('所属部门不能为空');
        loading.close()
        return false;
      }
      //调用param中的钩子函数处理各种情况，这样就可以用在组件中用钩子了。也可以用res.code==200来进行判断处理各种情况
      uploadFile(formData,this.form.folderName,this.form.deptId).then(res => {
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

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.disabled = false;
      this.open = true;
      this.title = "添加日常文件管理";
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      const id = row.id || this.ids
      getFileManagements(id).then(response => {
        this.form = response.data;
        this.disabled = true;
        this.open = true;
        this.title = "查看日常文件管理";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFileManagements(id).then(response => {
        this.form = response.data;
        this.disabled = false;
        this.open = true;
        this.title = "修改日常文件管理";
      });
    },
    //文件下载
    handleFileDownload(row) {
      fileDownload(row.id).then(response => {
        if (response.code == 200) {
          this.$modal.msgSuccess(response.msg);
          this.getList();
        } else {
          this.$message.error(response.msg)
          this.getList();
        }
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFileManagements(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFileManagements(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除日常文件名称为"' + row.fileName + '"的数据项？').then(function() {
        return delFileManagements(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('polices/fileManagements/export', {
        ...this.queryParams
      }, `fileManagements_${new Date().getTime()}.xlsx`)
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
