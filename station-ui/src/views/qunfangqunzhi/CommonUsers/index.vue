<template>
  <div class="app-container">
    <el-row>
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入辖区名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            node-key="id"
            default-expand-all
            highlight-current
            @node-click="handleNodeClick"
          />
          <el-tree
            :data="commonUserOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            ref="tree"
            node-key="id"
            default-expand-all
            highlight-current
            @node-click="handleCommonUserNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="用户类型" prop="userType">
            <el-select v-model="queryParams.userType"  @keyup.enter.native="handleQuery"  clearable placeholder="请选择用户类型">
              <el-option
                v-for="item in this.dict.type.common_user_type"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="用户账号" prop="userName">
            <el-input
              v-model="queryParams.userName"
              placeholder="请输入用户账号"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="真实姓名" prop="realName">
            <el-input
              v-model="queryParams.realName"
              placeholder="请输入用户姓名"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="手机号码" prop="phonenumber">
            <el-input
              v-model="queryParams.phonenumber"
              placeholder="请输入手机号码"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
       </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['qunfangqunzhi:CommonUsers:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['qunfangqunzhi:CommonUsers:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['qunfangqunzhi:CommonUsers:remove']"
            >删除</el-button>
          </el-col>
<!--          <el-col :span="1.5">-->
<!--            <el-button-->
<!--              type="warning"-->
<!--              plain-->
<!--              icon="el-icon-download"-->
<!--              size="mini"-->
<!--              @click="handleExport"-->
<!--              v-hasPermi="['qunfangqunzhi:CommonUsers:export']"-->
<!--            >导出</el-button>-->
<!--          </el-col>-->
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"> </right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="CommonUsersList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="账号" align="center" prop="userName" />
          <el-table-column label="真实姓名" align="center" prop="realName" />
          <el-table-column label="所属辖区" align="center" prop="dept.deptName" />
          <el-table-column label="用户类型" align="center" prop="userType" >
            <template slot-scope="scope">
              <span>{{getUserTypeLabel(scope.row.userType)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="手机号码" align="center" prop="phonenumber" />
          <el-table-column label="家庭住址" align="center" prop="homeAddress" />
          <el-table-column label="用户性别" align="center" prop="sex" >
            <template slot-scope="scope">
              <span>{{getUserSexLabel(scope.row.sex)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="帐号状态" align="center" prop="status">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.status"
                active-value="0"
                inactive-value="1"
                @change="handleStatusChange(scope.row)"
              ></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="备注" align="center" prop="remark" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-zoom-in"
                @click="handleDetail(scope.row)"
                v-hasPermi="['qunfangqunzhi:CommonUsers:query']"
              >详情</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['qunfangqunzhi:CommonUsers:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['qunfangqunzhi:CommonUsers:remove']"
              >删除</el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.usersApplication!=null"
                icon="el-icon-delete"
                @click="handleApplication(scope.row)"
                v-hasPermi="['qunfangqunzhi:CommonUsers:query']"
              >查看申请信息</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>


    </el-row>


<!--  对话框-->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="角色类型" prop="userType">
          <el-select v-model="form.userType"  @change="changeUserType"  clearable placeholder="请选择角色">
            <el-option
              v-for="item in this.dict.type.common_user_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="归属部门" prop="deptId" v-if="!deptDisabled">
          <treeselect v-model="form.deptId" :options="deptOptions" :disabled="deptDisabled" :show-count="true" placeholder="请选择归属部门" />
        </el-form-item>
        <el-form-item label="用户账号" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户账号" />
        </el-form-item>
        <el-form-item label="用户姓名" prop="realName">
          <el-input v-model="form.realName" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="性别" prop="realName">
          <el-select v-model="form.sex" placeholder="请选择用户性别" >
            <el-option
              v-for="item in this.dict.type.sys_user_sex"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="身份证号" prop="idNumber">
          <el-input v-model="form.idNumber" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phonenumber">
          <el-input v-model="form.phonenumber" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="家庭住址" prop="homeAddress">
          <el-input v-model="form.homeAddress" placeholder="请输入家庭住址" />
        </el-form-item>
        <el-form-item label="照片" prop="avatar">
          <el-upload
            accept=".jpg,.jpeg,.png"
            class="avatar-uploader"
            :action="uploadImgUrl"
            :on-preview="handlePictureCardPreview"
            :on-remove="this.uploadFile = null"
            :before-upload="handleBeforeUpload"
            :on-success="handleUploadSuccess"
            :headers="headers"
            :show-file-list="false"
            >
            <img v-if="imgUrl" :src="imgUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          请上传
          <span v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </span>
          <span v-if="fileType"> 格式为 <b style="color: #f56c6c">jpg,jpeg,png</b> </span>
          的文件
        </el-form-item>
        <el-form-item label="账户状态" prop="status">
          <el-switch
            v-model="form.status"
            active-value="0"
            inactive-value="1"
          >
          </el-switch>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 详情-->
    <el-dialog :title="title3" :visible.sync="open3"  width="500px" append-to-body>
      <el-form ref="form" :model="form" disabled label-width="100px">
        <el-form-item label="用户邮箱" prop="email">
          <el-input v-model="form.email"  />
        </el-form-item>
        <el-form-item label="身份证号" prop="idNumber">
          <el-input v-model="form.idNumber" />
        </el-form-item>
        <el-form-item label="照片" prop="avatar">
          <el-image
            v-if="this.imgUrl"
            style="width: 300px; height: 300px"
            :src="imgUrl"
            fit="fit"
            :preview-src-list="[imgUrl]">
          </el-image>
          <span v-show="!this.imgUrl" style="color: red">暂无照片</span>
        </el-form-item>
        <el-form-item label="最后登录IP" prop="loginIp">
          <el-input v-model="form.loginIp" />
        </el-form-item>
        <el-form-item label="最后登录时间" prop="loginDate">
          <el-date-picker clearable
                          v-model="form.loginDate"
                          type="date"
                          value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="closeDetailForm">确 定</el-button>
      </div>
    </el-dialog>
<!--     申请信息-->
    <el-dialog :title="title2" :visible.sync="open2" width="500px" append-to-body>
      <el-form ref="form"  label-width="80px" >
        <el-form-item label="申请描述">
          <el-input type="textarea" v-model="form2.description" />
        </el-form-item>
        <el-form-item label="归属部门" prop="deptId">
          <treeselect v-model="form2.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
        </el-form-item>
        <el-form-item label="角色类型">
          <el-select v-model="form2.userType"  clearable placeholder="请选择角色">
            <el-option
              v-for="item in this.dict.type.common_user_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="acceptApplication">确定</el-button>
        <el-button @click="rejectApplication">忽略</el-button>
        <el-button @click="cancel">关闭</el-button>
      </div>
    </el-dialog>



  </div>
</template>

<script>
import { listCommonUsers, getCommonUsers, delCommonUsers, addCommonUsers, updateCommonUsers,uploadApplication,changeCommonUserStatus} from "@/api/qunfangqunzhi/CommonUsers";
import {deptTreeSelect} from "@/api/system/user";
import {getApplicationManage} from  "@/api/qunfangqunzhi/applicationManage"
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {getToken} from "@/utils/auth";

export default {
  name: "CommonUsers",
  components: { Treeselect },
  dicts:['common_user_type','sys_user_sex'],
  data() {
    return {
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
      // 普通用户信息表格数据
      CommonUsersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        userName: null,
        realName: null,
        userType: null,
        email: null,
        idNumber: null,
        phonenumber: null,
        homeAddress: null,
        sex: null,
        avatar: null,
        password: null,
        status: null,
        loginIp: null,
        loginDate: null,
        dialogImageUrl: "",
        dialogVisible: false,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deptId: [
          { required: true, message: "辖区选项不能为空", trigger: "change" }
        ],
        userType:[
          {required: true, message: "用户类型不能为空", trigger: "change"}
        ],
        userName:[
          {required: true, message: "用户账号不能为空", trigger: "change"},
          {pattern:/^[a-zA-Z0-9]{4,16}$/, message: '用户名长度在 4 到 16 个字符,包括字符和数字组合', trigger: 'blur' }
        ],
        phonenumber:[
          {pattern:/^1[3-9][0-9]([0-9]){8}$/, message: '请输入正确的手机号码', trigger: 'blur'}
        ],
        idNumber:[
          {pattern:/^[0-9]{15,17}[1-9xX]?$/, message: '请输入正确的身份证号码', trigger: 'blur'}
        ]
      },
      // 部门名称
      deptName: undefined,
      // 部门树选项
      deptOptions: undefined,
      // 普通用户树
      commonUserOptions:[{id: -1, label: "普通群众"}],
      defaultProps: {
        children: "children",
        label: "label"
      },
      //网格员申请框参数
      title2:"网格员申请信息",
      open2:false,
      form2:{

      },
      //申请id
      applicationId:null,
      //接受或者拒绝
      flag:null,
      //申请界面需要提交的用户
      updateUser:{
        userId:null,
        deptId:null,
        userType:null
      },
      title3:"详情信息",
      open3:false,
      //上传照片地址
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      uploadFile:null,
      // 上传文件类型, 例如['png', 'jpg', 'jpeg']
      fileType:["png", "jpg", "jpeg"],
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      imgUrl:null,
      // 上传图片大小限制(MB)
      fileSize:5,
      deptDisabled:false,
      // 查看更多信息中的图片地址


    };
  },
  created() {
    this.getDeptTree();
    this.getList();
  },

  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  methods: {
    /** 查询普通用户信息列表 */
    getList() {
      this.loading = true;
      listCommonUsers(this.queryParams).then(response => {
        this.CommonUsersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.open2 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: null,
        deptId: null,
        userName: null,
        realName: null,
        userType: null,
        email: null,
        idNumber: null,
        phonenumber: null,
        homeAddress: null,
        sex: null,
        avatar: null,
        password: null,
        status: null,
        delFlag: null,
        loginIp: null,
        loginDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.form2={

      };
      this.resetForm("form2");
      this.resetForm("form");
      this.imgUrl = null;
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
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加普通用户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      getCommonUsers(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改普通用户信息";
        if(this.form.avatar){
          this.imgUrl = process.env.VUE_APP_BASE_API + this.form.avatar
        }
        if(this.form.userType == 0){
          this.deptDisabled = true;
        }else{
          //网格员辖区还未设置
          if(this.form.deptId == 0){
            this.form.deptId = null;
          }
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {

          if(this.form.userType == 0 && this.form.deptId != 0 ){
            this.$alert("普通用户不存在辖区选项");
            return;
          }
          if(this.form.userType != 0 && (this.form.deptId == 0||this.form.deptId == null) ){
            this.$alert("");
            return;
          }

          //修改
          if (this.form.userId != null) {
            updateCommonUsers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCommonUsers(this.form).then(response => {
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
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除选中用户账号？').then(function() {
        return delCommonUsers(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qunfangqunzhi/CommonUsers/export', {
        ...this.queryParams
      }, `CommonUsers_${new Date().getTime()}.xlsx`)
    },

    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.queryParams.userType = null;
      this.handleQuery();
    },
    //群众树节点点击事件
    handleCommonUserNodeClick(){
      this.queryParams.deptId = null;
      this.queryParams.userType = '0';
      this.handleQuery();
    },
    /** 查询部门下拉树结构 */
    getDeptTree() {
      deptTreeSelect().then(response => {
        this.deptOptions = response.data;
      });
    },
    /** 处理网格员申请点击界面 **/
    async handleApplication(row){
      await getApplicationManage(row.usersApplication.id).then(res=>{
        this.form2 = res.data;
      })
      this.open2 = true;
      this.applicationId = row.usersApplication.id;
      this.form2.userId = row.userId;
      this.form2.deptId = row.deptId;
      this.form2.userType = row.userType;
      this.form2 = {...this.form2};


    },
    /**上传批准审核员申请请求**/
    acceptApplication(){
      if((this.form2.deptId == null || this.form2.deptId == "" )|| (this.form2.userType==null||this.form2.userType=="")){
        this.$alert("请确定辖区以及用户类型");
      }else{
        uploadApplication(this.form2,this.applicationId,true).then(res=>{
          this.$message(res.msg);
          this.open2 = false;
          this.reset()
          this.getList();
        })
      }
    },
    /** 拒绝申请请求 **/
    rejectApplication(){
      uploadApplication(null,this.applicationId,false).then(res=>{
        this.$message(res.msg);
        this.open2 = false;
        this.reset()
        this.getList();
      })
    },
    /** 详情按钮操作 */
    handleDetail(row) {
      this.reset();
      const userId = row.userId
      getCommonUsers(userId).then(response => {
        this.form = response.data;
        this.open3 = true;
        this.imgUrl = this.form.avatar?process.env.VUE_APP_BASE_API + this.form.avatar:null
      });
    },
    //获取下用户类型标签内容
    getUserTypeLabel(val){
      for(let dict of this.dict.type.common_user_type){
        if(dict.value == val){
          return dict.label;
        }
      }
    },
    //获取用户信息性别标签
    getUserSexLabel(val){
      for(let dict of this.dict.type.sys_user_sex){
        if(dict.value == val){
          return dict.label;
        }
      }
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"' + text + '""' + row.userName + '"用户吗？').then(function() {
        return changeCommonUserStatus(row.userId, row.status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      });
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      if (res.code === 200) {
        this.$modal.closeLoading();
        this.imgUrl = process.env.VUE_APP_BASE_API+res.fileName;
        this.form.avatar = res.fileName;
      } else {
        this.$modal.closeLoading();
        this.$modal.msgError(res.msg);
        // this.$refs.imageUpload.handleRemove(file);
        // this.uploadedSuccessfully();
      }
    },
    // 预览
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    // 上传前loading加载
    handleBeforeUpload(file) {
      let isImg = false;
      if (this.fileType.length) {
        let fileExtension = "";
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
        }
        isImg = this.fileType.some(type => {
          if (file.type.indexOf(type) > -1) return true;
          if (fileExtension && fileExtension.indexOf(type) > -1) return true;
          return false;
        });
      } else {
        isImg = file.type.indexOf("image") > -1;
      }

      if (!isImg) {
        this.$modal.msgError(`文件格式不正确, 请上传${this.fileType.join("/")}图片格式文件!`);
        return false;
      }
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$modal.msgError(`上传头像图片大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      this.$modal.loading("正在上传图片，请稍候...");
    },
    changeUserType(val){
      if(val == 0){
        this.form.deptId = 0;
        this.deptDisabled = true;
      }else{
        this.form.deptId = null;
        this.deptDisabled = false;
      }
    },
    closeDetailForm(){
      this.open3 = false;
      this.reset();
    }
  }
};
</script>
<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
