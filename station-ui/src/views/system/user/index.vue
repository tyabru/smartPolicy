<template>
  <div class="app-container">
    <el-row>
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input v-model="deptName" placeholder="请输入部门名称" clearable size="small" prefix-icon="el-icon-search" style="margin-bottom: 20px"/>
        </div>
        <div class="head-container">
          <el-tree :data="deptOptions" :props="defaultProps" :expand-on-click-node="false" :filter-node-method="filterNode"
            ref="tree" node-key="id" default-expand-all highlight-current @node-click="handleNodeClick"/>
        </div>
      </el-col>
      <!--用户数据-->
      <el-col :span="20" :xs="24">
        <search-form-bar>
          <el-form :model="queryParams" ref="queryForm" size="mini" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="警号" prop="userName">
              <el-input v-model="queryParams.userName" placeholder="请输入警号" clearable style="width: 240px"/>
            </el-form-item>
            <el-form-item label="手机号码" prop="phonenumber">
              <el-input v-model="queryParams.phonenumber" placeholder="请输入手机号码" clearable style="width: 240px"/>
            </el-form-item>
            <el-form-item label="状态" prop="status">
              <el-select v-model="queryParams.status" placeholder="用户状态" clearable style="width: 240px">
                <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label" :value="dict.value"/>
              </el-select>
            </el-form-item>
            <el-form-item label="创建时间">
              <el-date-picker v-model="dateRange" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
            </el-form-item>
          </el-form>
          <template #btn>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </template>
        </search-form-bar>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:user:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
              v-hasPermi="['system:user:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
              v-hasPermi="['system:user:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="info" plain icon="el-icon-upload2" size="mini" @click="handleImport"
              v-hasPermi="['system:user:import']">导入</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
              v-hasPermi="['system:user:export']">导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <!-- <el-table-column label="用户编号" align="center" key="userId" prop="userId" v-if="columns[0].visible" /> -->
          <el-table-column label="警号" align="center" key="userName" prop="userName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
          <el-table-column label="用户姓名" align="center" key="nickName" prop="nickName" v-if="columns[2].visible" :show-overflow-tooltip="true" />          
          <el-table-column label="手机号码" align="center" key="phonenumber" prop="phonenumber" v-if="columns[4].visible" width="120" />
          <el-table-column label="身份证号码" align="center" prop="idCard" width="120" />
          <el-table-column label="居住地址" align="center" prop="addressCode" width="120" />
          <el-table-column label="部门" align="center" key="deptName" prop="dept.deptName" v-if="columns[3].visible" :show-overflow-tooltip="true" />
          <el-table-column label="状态" align="center" key="status" v-if="columns[5].visible">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.status" active-value="0" inactive-value="1" @change="handleStatusChange(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[6].visible" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="警员照片" align="center" prop="policePhoto">
            <template slot-scope="scope">
              <el-image style="width: 65px; height: 85px" :src="getImgUrl(scope.row.policePhoto)" 
                :preview-src-list="[getImgUrl(scope.row.policePhoto)]" :onerror="errorUserPhoto"  fit="cover" ></el-image>
              </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="160" class-name="small-padding fixed-width">
            <template slot-scope="scope" v-if="scope.row.userId !== 1">
              <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
                v-hasPermi="['system:user:query']">查看</el-button>
              <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                v-hasPermi="['system:user:edit']">修改</el-button>
              <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                v-hasPermi="['system:user:remove']">删除</el-button>
              <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)" v-hasPermi="['system:user:resetPwd', 'system:user:edit']">
                <el-button size="mini" type="text" icon="el-icon-d-arrow-right">更多</el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="handleResetPwd" icon="el-icon-key"
                    v-hasPermi="['system:user:resetPwd']">重置密码</el-dropdown-item>
                  <el-dropdown-item command="handleAuthRole" icon="el-icon-circle-check"
                    v-hasPermi="['system:user:edit']">分配角色</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>
      </el-col>
    </el-row>

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="警员姓名" prop="nickName">
              <el-input v-model="form.nickName" placeholder="请输入用户姓名" maxlength="30" />
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
            <el-form-item label="警号" prop="userName">
              <el-input v-model="form.userName" placeholder="请输入用户警号" maxlength="30" :disabled="policeNumberDisabled"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入用户密码" type="password" maxlength="20" show-password/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户性别" prop="sex">
              <el-select style="width: 325px;" v-model="form.sex" placeholder="请选择性别">
                <el-option v-for="dict in dict.type.sys_user_sex" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.value">{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="岗位" prop="postIds">
              <el-select style="width: 325px;" v-model="form.postIds" multiple placeholder="请选择岗位">
                <el-option v-for="item in postOptions" :key="item.postId" :label="item.postName" :value="item.postId" :disabled="item.status == 1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色" prop="roleIds">
              <el-select style="width: 325px;" v-model="form.roleIds" multiple placeholder="请选择角色">
                <el-option v-for="item in roleOptions" :key="item.roleId" :label="item.roleName" :value="item.roleId" :disabled="item.status == 1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="身份证号" prop="idCard">
              <el-input v-model="form.idCard" placeholder="请输入身份证号码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phonenumber">
              <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="居住地址" prop="addressCode">
              <el-input v-model="form.addressCode" placeholder="请输入居住地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="政治面貌" prop="identityType">
              <el-select  v-model="form.identityType" placeholder="请选择政治面貌" style="width: 100%">
                <el-option v-for="item in dict.type.identity_type" :key="item.value" :label="item.label" :value="Number(item.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="年龄" prop="policeAge">
              <el-input v-model="form.policeAge" placeholder="请输入年龄" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="民族" prop="nation">
              <el-select  v-model="form.nation" placeholder="请选择民族" style="width: 100%">
                <el-option v-for="item in dict.type.sys_nation" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="毕业院校" prop="graduationSchool">
              <el-input v-model="form.graduationSchool" placeholder="请输入毕业院校" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学历" prop="education">
              <el-select  v-model="form.education" placeholder="请选择学历" style="width: 100%">
                <el-option v-for="item in dict.type.police_education" :key="item.value" :label="item.label" :value="Number(item.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="技能专长" prop="speciality">
              <el-input v-model="form.speciality" placeholder="请输入技能专长" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入职时间" prop="entryTime">
              <el-date-picker style="width: 325px;" clearable v-model="form.entryTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择入职时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="配备执法记录仪" prop="isVehicle">
              <el-select  v-model="form.isVehicle" placeholder="请选择是否配备车载布控球" style="width: 100%">
                <el-option v-for="item in dict.type.sys_yes_no" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备编码" prop="equipmentNumber" 
              :rules="[{ required: form.isVehicle == 'Y', message: '当该警员配备执法记录仪时,设备编码不能为空', trigger: 'blur' }]">
              <el-select  v-model="form.equipmentNumber" placeholder="请选择执法记录仪" style="width: 100%" :disabled="form.isVehicle != 'Y'">
                <el-option v-for="item in policeEnforcementRecorderList" :key="item.deviceCode" :label="item.deviceCode" :value="item.deviceCode">
                {{item.deviceCode+"----------"+item.deviceName}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="警员照片" prop="policePhoto">
              <el-upload action="" list-type="picture-card" accept="image/*" ref="uploadImg2"
                  :limit="1" :file-list="imagelist" :on-preview="handlePictureCardPreview" :on-remove="handleRemove"
                  :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" :on-exceed="handleExceed" :on-error="imgUploadError"
                  :on-change="selectImageChange" :http-request="upload1" class="avatar-uploader" :class="{ disabled: uploadDisabled }" :auto-upload="true">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="dialogImageUrl" alt="" />
              </el-dialog>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-show="isDisplay" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload ref="upload" :limit="1" accept=".xlsx, .xls" :headers="upload.headers" :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading" :on-progress="handleFileUploadProgress" :on-success="handleFileSuccess" :auto-upload="false" drag>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的用户数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addImgData, getImage } from "@/api/polices/policeInformation";
import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { listPoliceEnforcementRecorder } from "@/api/equipment/PoliceEnforcementRecorder";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import * as imageConversion from 'image-conversion';
import { getImgUrl } from "../../../utils";
import { validPassword, validPpliceIdCard, validPhone } from "@/utils/validate";
import policePhoto from "@/views/polices/information/policeman.png";

export default {
  name: "User",
  dicts: [ 'sys_normal_disable', 'sys_user_sex', 'identity_type', 'police_education', 'sys_nation', "sys_yes_no" ],
  components: { Treeselect },
  data() {
    const validatePassword = (rule, value, callback) => {
      const isTrue = validPassword(value)
      if(value && isTrue) {
        callback();
      }else{
        callback("密码安全性较低，密码必须包含大写字母、小写字母、数字、符号（任选三种）");
      }
    }
    return {
      isDisplay: false,
      disabled: false,
      errorUserPhoto: 'this.src="' + policePhoto + '"',
      btnDisabled: false,
      policeNumberDisabled: false,
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
      // 用户表格数据
      userList: null,
      // 弹出层标题
      title: "",
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      fd: null,
      // 图片路径
      dialogImageUrl: "",
      // 是否显示图片
      dialogVisible: false,
      // 图片列表
      imagelist: [],
      // 日期范围
      dateRange: [],
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      //执法记录仪集合
      policeEnforcementRecorderList: [],
      policeEnforcementRecorderListUpdate: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      },
      // 列信息
      columns: [
        { key: 0, label: `用户编号`, visible: true },
        { key: 1, label: `用户名称`, visible: true },
        { key: 2, label: `用户昵称`, visible: true },
        { key: 3, label: `部门`, visible: true },
        { key: 4, label: `手机号码`, visible: true },
        { key: 5, label: `状态`, visible: true },
        { key: 6, label: `创建时间`, visible: true }
      ],
      // 表单校验
      rules: {
        policeNumber: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 6, max: 6, message: "警号长度为6位", trigger: "blur" }
        ],
        nickName: [{ required: true, message: "用户昵称不能为空", trigger: "blur" }],
        userName: [{ required: true, message: "警员警号不能为空", trigger: "blur" }],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' },
          { validator: validatePassword, trigger: "blur" }
        ],
        email: [{ type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"]}],
        phonenumber: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          { min: 11, max: 11, message: "手机号长度为11", trigger: "blur" },
          {validator : validPhone, trigger: "blur"}
        ],
        status: [{ required: true, message: "用户状态不能为空", trigger: "blur" }],
        policeAge: [{ required: true, message: "年龄不能为空", trigger: "blur" }],
        nation: [{ required: true, message: "民族不能为空", trigger: "blur" }],
        idCard: [{ required: true, message: "身份证号码不能为空", trigger: "blur" },
          { min: 18, max: 18, message: "身份证长度为18", trigger: "blur" },
          { validator: validPpliceIdCard, trigger: "blur" },
        ],
        isVehicle: [{ required: true, message: "是否配备执法记录仪不能为空", trigger: "change" }],
        identityType: [{ required: true, message: "政治面貌不能为空", trigger: "change" }],
        policePhoto: [{ required: true, message: "警员照片不能为空", trigger: "blur" }],
        addressCode: [{ required: true, message: "居住地址不能为空", trigger: "blur" }],
        entryTime: [{ required: true, message: "入职不能为空", trigger: "blur" }],
        deptId: [{ required: true, message: "所属派出所编码不能为空", trigger: "blur" }],
        postIds: [{ required: true, message: "所属岗位不能为空", trigger: "blur" }],
        roleIds: [{ required: true, message: "所属角色不能为空", trigger: "blur" }],
        sex: [{ required: true, message: "警员性别不能为空", trigger: "blur" }],
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  computed: {
    uploadDisabled: function () {
      return this.imagelist.length > 0;
    },
  },
  created() {
    this.getList();
    this.getDeptTree();
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
  },
  methods: {
    getImgUrl,
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
          this.getPoliceEnforcementRecorder();
        }
      );
    },
    /** 查询执法仪记录仪列表 */
    getPoliceEnforcementRecorder() {
      let dateForm = {
        deptId: null
      }
      listPoliceEnforcementRecorder(dateForm).then(response => {
        this.policeEnforcementRecorderList = response.rows;
        this.policeEnforcementRecorderListUpdate = response.rows;
        this.handleData(this.policeEnforcementRecorderListUpdate);
      });
    },
    handleData(policeEnforcementRecorderList) {
      let equipmentListUpdate = [];
      let flage = true;
      for (let i = 0; i < policeEnforcementRecorderList.length; i++) {
        for (let j = 0; j < this.userList.length; j++) {
          flage = true;
          if (policeEnforcementRecorderList[i].deviceCode == this.userList[j].equipmentNumber) {
            flage = false;
            break;
          }
        }
        if (flage) {
          equipmentListUpdate.push(policeEnforcementRecorderList[i]);
        }
      }
      this.policeEnforcementRecorderList = equipmentListUpdate;
    },
    /** 查询部门下拉树结构 */
    getDeptTree() {
      deptTreeSelect().then(response => {
        this.deptOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.handleQuery();
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"' + text + '""' + row.userName + '"用户吗？').then(function() {
        return changeUserStatus(row.userId, row.status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      });
    },
    /** 移除图片时调用 */
    handleRemove(file, fileList) {
      this.imagelist = [];
    },
    /** 预览图片时调用 */
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    /** 文件上传之前调用做一些拦截限制 */
    beforeAvatarUpload(file) {
      const _that = this
      const image = new Image();
      const imageConfig = {
        size: 190,
        accuracy: 0.9,
        type: 'image/jpeg'
      }
      return new Promise((resolve, reject) => {
        // if(file.size < 25 * 1024) {
        //   this.$message.warning("为了更加精准的人脸识别，上传图片需要大于25KB!")
        //   reject("为了更加精准的人脸识别，上传图片需要大于25KB!");
        // }
        const url = URL.createObjectURL(file);
        image.onload = function() {
          // 640*480 像素或以上
          if(image.width < 300 || image.height < 400) {
            image.width = image.width < 300? 300: image.width
            image.height = image.height < 400? 400: image.height
            imageConversion.imagetoCanvas(image).then( canvas => {
              imageConversion.canvastoFile(canvas, 1).then( newFile => {
                if(newFile.size > 200 * 1024) {
                  imageConversion.compressAccurately(newFile, imageConfig).then(result => {
                    _that.addToFileUploadForm(new File([result], 'blob2file.jpg',{type: result.type}))
                    resolve(result)
                  })
                }else {
                  _that.addToFileUploadForm(new File([newFile], 'blob2file.jpg',{type: file.type}))
                  resolve(newFile)
                }
              })
            })
          } else if(image.width>1080 || file.size > 200 * 1024) {
            if(image.width>1080) {
              const scale = image.width / 1080
              image.width = 1080
              image.height = image.height/scale
            }
            imageConfig.width = image.width
            imageConfig.height = image.height
            imageConversion.compressAccurately(file, imageConfig).then(result => {
              _that.addToFileUploadForm(new File([result], 'blob2file.jpg',{type: result.type}))
              resolve(result)
            })
          }else {
            _that.addToFileUploadForm(new File([file], 'blob2file.jpg',{type: file.type}))
            resolve(file)
          }
        }
        image.src = url;
      });
    },
    addToFileUploadForm(file) {
      this.form.policePhoto = "policePhoto";
      this.fd = new FormData();
      this.fd.append("file", file);
    },
    handleAvatarSuccess(res, file) {
      var pathArr = res.imgUrl.split("uploadPath");
      this.form.policePhoto = pathArr[1];
    },
    /** 图片上传超过数量限制 */
    handleExceed(files, fileList) {
      this.$message.error("上传图片不能超过1张!");
    },
    /** 图片上传失败调用 */
    imgUploadError(err, file, fileList) {
      this.$message.error("上传图片失败!");
    },
    /** 选择的图片列表变化时调用(增加) */
    async selectImageChange(file, fileList) {
      if (fileList.length >= 1 && this.imagelist.length == 0) {
        this.imagelist.push(file);
      }
    },
    /** 取消默认上传操作 */
    upload1() {},
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        deptId: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        remark: undefined,
        postIds: [],
        roleIds: [],
        policeNumber: null,
        policeAge: null,
        nation: null,
        idCard: null,
        phoneNumber: null,
        email: null,
        identityType: null,
        education: null,
        graduationSchool: null,
        speciality: null,
        policePhoto: null,
        addressCode: null,
        entryTime: null,
        operateName: this.form.operateName,
        isVehicle: null,
        equipmentNumber: null,
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.queryParams.deptId = undefined;
      this.$refs.tree.setCurrentKey(null);
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleResetPwd":
          this.handleResetPwd(row);
          break;
        case "handleAuthRole":
          this.handleAuthRole(row);
          break;
        default:
          break;
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.imagelist = [];
      this.handleData(this.policeEnforcementRecorderListUpdate);
      getUser().then(response => {
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.open = true;
        this.disabled = false;
        this.policeNumberDisabled = false;
        this.title = "添加用户";
        this.isDisplay = true;
        this.form.password = this.initPassword;
      });
    },
    //查看按钮
    handleView(row) {
      this.reset();
      this.imagelist = [];
      this.policeEnforcementRecorderList = this.policeEnforcementRecorderListUpdate;
      const userId = row.userId || this.ids;
      getUser(userId).then(response => {
        this.form = response.data;
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.$set(this.form, "postIds", response.postIds);
        this.$set(this.form, "roleIds", response.roleIds);
        var imgUrl = getImgUrl(this.form.policePhoto);
        let obj = new Object();
        obj.url = imgUrl;
        this.imagelist.push(obj);
        this.open = true;
        this.disabled = true;
        this.isDisplay = false;
        this.policeNumberDisabled = true;
        this.title = "查看用户";
        this.form.password = "";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.imagelist = [];
      this.policeEnforcementRecorderList = this.policeEnforcementRecorderListUpdate;
      const userId = row.userId || this.ids;
      getUser(userId).then(response => {
        this.form = response.data;
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.$set(this.form, "postIds", response.postIds);
        this.$set(this.form, "roleIds", response.roleIds);
        var imgUrl = getImgUrl(this.form.policePhoto);
        let obj = new Object();
        obj.url = imgUrl;
        this.imagelist.push(obj);
        this.open = true;
        this.isDisplay = true;
        this.disabled = false;
        this.policeNumberDisabled = false;
        this.title = "修改用户";
        this.form.password = "";
      });
    },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
        inputValidator: ((value) => {
          if(!value || value.length < 6 || value.length > 18) {
            return "密码长度位6到18位"
          }
          if(validPassword(value)) {
            return true;
          } else {
            return '密码安全性较低，密码必须包含大写字母、小写字母、数字、符号（可选） 可用符号@ # ! % & . _ + = ~ ? -';
          }
        }),
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      }).then(({ value }) => {
          resetUserPwd(row.userId, value).then(response => {
            this.$modal.msgSuccess("修改成功，新密码是：" + value);
          });
        }).catch(() => {});
    },
    /** 分配角色操作 */
    handleAuthRole: function(row) {
      const userId = row.userId;
      this.$router.push("/system/user-auth/role/" + userId);
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(async valid => {
        if (valid) {
          this.btnDisabled = true
          let self = this;
          if (this.fd != null && this.fd != undefined) {
            await addImgData(this.fd).then(function (
              res
            ) {
              self.form.policePhoto = res.imgUrl;
            });
          }
          if (this.form.userId != undefined) {
            updateUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除警号为"' + row.userName + '"的数据项？将同时删除警员信息！！！').then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/user/importTemplate', {
      }, `user_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
