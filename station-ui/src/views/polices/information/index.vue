<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="警号" prop="policeNumber">
        <el-input v-model="queryParams.policeNumber" placeholder="请输入警号" clearable/>
      </el-form-item>
      <el-form-item label="姓名" prop="policeName">
        <el-input v-model="queryParams.policeName" placeholder="请输入姓名" clearable/>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select  v-model="queryParams.gender" placeholder="请选择警员性别" style="width: 100%">
          <el-option v-for="item in dict.type.sys_user_sex" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input v-model="queryParams.idCard" placeholder="请输入身份证号码" clearable/>
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNumber">
        <el-input v-model="queryParams.phoneNumber" placeholder="请输入手机号" clearable/>
      </el-form-item>
      <el-form-item label="警察类型" prop="policePost">
        <el-select  v-model="queryParams.policePost" placeholder="请选择警察类型" style="width: 100%">
          <el-option v-for="item in dict.type.police_post" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="入职时间" prop="entryTime">
        <el-date-picker style="width: 215px;" clearable v-model="queryParams.entryTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择入职时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" 
        v-hasPermi="['polices:information:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" 
        v-hasPermi="['polices:information:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" 
        v-hasPermi="['polices:information:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" 
        v-hasPermi="['polices:information:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="警号" align="center" prop="policeNumber" />
      <el-table-column label="姓名" align="center" prop="policeName" />
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="年龄" align="center" prop="policeAge" />
      <el-table-column label="民族" align="center" prop="nation" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_nation" :value="scope.row.nation"/>
        </template>
      </el-table-column>
      <el-table-column label="出生日期" align="center" prop="birthday"/>
      <el-table-column label="身份证号" align="center" prop="idCard" />
      <el-table-column label="手机号" align="center" prop="phoneNumber" />
      <el-table-column label="政治面貌" align="center" prop="identityType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.identity_type" :value="scope.row.identityType"/>
        </template>
      </el-table-column>
      <el-table-column label="警员类型" align="center" prop="policePost" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.police_post" :value="scope.row.policePost"/>
        </template>
      </el-table-column>
      <el-table-column label="警员岗位" align="center" prop="policeType" >
        <template slot-scope="scope">
          <span v-for="item in postOptions">
            <span v-if="item.postId == scope.row.policeType">{{item.postName}}</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="部门编码" align="center" prop="deptId" />
      <el-table-column label="地址编码" align="center" prop="addressCode" />
      <el-table-column label="入职时间" align="center" prop="entryTime" />
      <el-table-column label="警员照片" align="center" prop="policePhoto" :render-header="renderZPHeader">
        <template slot-scope="scope">
          <el-image style="width: 68px; height: 100px" :src="getImgUrl(scope.row.policePhoto)" 
            :preview-src-list="[getImgUrl(scope.row.policePhoto)]" :onerror="errorUserPhoto"  fit="cover" ></el-image>
          </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleSelect(scope.row)" 
          v-hasPermi="['polices:information:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" 
          v-hasPermi="['polices:information:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" 
          v-hasPermi="['polices:information:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改警员基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="警号" prop="policeNumber">
              <el-input v-model="form.policeNumber" placeholder="请输入警号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名" prop="policeName">
              <el-input v-model="form.policeName" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-select  v-model="form.gender" placeholder="请选择警员性别" style="width: 100%">
                <el-option v-for="item in dict.type.sys_user_sex" :key="item.value" :label="item.label" :value="Number(item.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年龄" prop="policeAge">
              <el-input v-model="form.policeAge" placeholder="请输入年龄" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="民族" prop="nation">
              <el-select  v-model="form.nation" placeholder="请选择民族" style="width: 100%">
                <el-option v-for="item in dict.type.sys_nation" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
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
            <el-form-item label="身份证号" prop="idCard">
              <el-input v-model="form.idCard" placeholder="请输入身份证号码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="phoneNumber">
              <el-input v-model="form.phoneNumber" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="归属部门" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="警察类型" prop="policePost">
              <el-select  v-model="form.policePost" placeholder="请选择警察类型" style="width: 100%">
                <el-option v-for="item in dict.type.police_post" :key="item.value" :label="item.label" :value="Number(item.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="入职时间" prop="entryTime">
              <el-date-picker style="width: 325px;" clearable v-model="form.entryTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择入职时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属岗位" prop="policeType">
              <el-select  v-model="form.policeType" placeholder="请选择警员所属岗位" style="width: 100%">
                <el-option v-for="item in postOptions" :key="item.postId" :label="item.postName" :value="item.postId" :disabled="item.status == 1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="小区编码" prop="communityCode">
              <el-input v-model="form.communityCode" placeholder="请输入小区编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址编码" prop="addressCode">
              <el-input v-model="form.addressCode" placeholder="请输入居住地址编码" />
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
            <el-form-item label="警员邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入警员邮箱" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="警员照片" prop="policePhoto">
              <el-upload action="" list-type="picture-card" accept="image/*" ref="uploadImg2"
                  :limit="1" :file-list="imagelist" :on-preview="handlePictureCardPreview" :on-remove="handleRemove"
                  :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" :on-exceed="handleExceed" :on-error="imgUploadError"
                  :on-change="selectImageChange" :http-request="upload" class="avatar-uploader" :class="{ disabled: uploadDisabled }" :auto-upload="true">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="dialogImageUrl" alt="" />
              </el-dialog>
            </el-form-item>
          </el-col>
          <el-col :span="12">
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
import { listInformation, getInformation, delInformation, addInformation, updateInformation, addImgData } from "@/api/polices/policeInformation";
import { getUserProfile, deptTreeSelect } from "@/api/system/user";
import { listPost } from "@/api/system/post";
import DictTag from '@/components/DictTag';
import * as imageConversion from 'image-conversion';
import {getImgUrl} from "../../../utils";
import policePhoto from "@/views/polices/information/policeman.png";
import { validPpliceIdCard } from '@/utils/validate';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Information",
  dicts: [ 'sys_user_sex', 'police_post', 'identity_type', 'police_education', 'sys_nation' ],
  components: { DictTag ,Treeselect},
  data() {
    return {
      disabled: true,
      //用户对象
      user: null,
      errorUserPhoto: 'this.src="' + policePhoto + '"',
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
      fd: null,
      // 图片路径
      dialogImageUrl: "",
      // 是否显示图片
      dialogVisible: false,
      // 图片列表
      imagelist: [],
      // 警员基本信息表格数据
      informationList: [],
      // 岗位选项
      postOptions: [],
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
        policeNumber: null,
        policeName: null,
        gender: null,
        policeAge: null,
        nation: null,
        birthday: null,
        idCard: null,
        phoneNumber: null,
        email: null,
        identityType: null,
        education: null,
        graduationSchool: null,
        policePost: null,
        policeType: null,
        speciality: null,
        deptId: null,
        policePhoto: null,
        communityCode: null,
        addressCode: null,
        entryTime: null,
        deleteTime: null,
        operateTime: null,
        operateType: null,
        operateName: null,
      },
      // 表单参数
      form: {
        operateName: null,
      },
      // 表单校验
      rules: {
        policeNumber: [{ required: true, message: "警号不能为空", trigger: "blur" },
          { min: 6, max: 7, message: "警号长度为6~7位", trigger: "blur" }
        ],
        policeName: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        gender: [{ required: true, message: "性别不能为空", trigger: "blur" }],
        policeAge: [{ required: true, message: "年龄不能为空", trigger: "blur" }],
        nation: [{ required: true, message: "民族不能为空", trigger: "blur" }],
        birthday: [{ required: true, message: "出生日期不能为空", trigger: "blur" }],
        idCard: [{ required: true, message: "身份证号码不能为空", trigger: "blur" },
          { min: 18, max: 18, message: "身份证长度为18", trigger: "blur" },
          { validator: validPpliceIdCard, trigger: "blur" }
        ],
        phoneNumber: [{ required: true, message: "手机号不能为空", trigger: "blur" },
          { min: 11, max: 11, message: "手机号长度为11", trigger: "blur" },
        ],
        identityType: [{ required: true, message: "政治面貌不能为空", trigger: "change" }],
        policePost: [{ required: true, message: "警察类型不能为空", trigger: "blur" }],
        policePhoto: [{ required: true, message: "警员照片不能为空", trigger: "blur" }],
        addressCode: [{ required: true, message: "居住地址编码不能为空", trigger: "blur" }],
        communityCode: [{ required: true, message: "小区编码不能为空", trigger: "blur" }],
        entryTime: [{ required: true, message: "入职不能为空", trigger: "blur" }],
        deptId: [{ required: true, message: "所属派出所编码不能为空", trigger: "blur" }],
        policeType: [{ required: true, message: "所属岗位不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getUser();
    this.getDeptTree();
    this.getPost();
  },
  computed: {
    uploadDisabled: function () {
      return this.imagelist.length > 0;
    },
  },
  methods: {
    getImgUrl,
    getPost() {
      listPost().then(response => {
        this.postOptions = response.rows;
      })
    },
    getUser () {
      getUserProfile().then(response => {
        this.user = response.data;
        //this.queryParams.deptId = response.data.deptId;
        this.form.operateName = response.data.nickName;
      })
      this.getList();
    },
    /** 查询警员基本信息列表 */
    getList() {
      this.loading = true;
      listInformation(this.queryParams).then(response => {
        if (response.code == 200) {
          this.informationList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
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
        policeNumber: null,
        policeName: null,
        gender: null,
        policeAge: null,
        nation: null,
        birthday: null,
        idCard: null,
        phoneNumber: null,
        email: null,
        identityType: null,
        education: null,
        graduationSchool: null,
        policePost: null,
        policeType: null,
        speciality: null,
        deptId: null,
        policePhoto: null,
        communityCode: null,
        addressCode: null,
        entryTime: null,
        createTime: null,
        updateTime: null,
        deleteTime: null,
        operateTime: null,
        operateType: null,
        operateName: this.form.operateName,
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
    /** 证件照片列添加点击预览放大后的图片的提示 */
    renderZPHeader(h) {
      return h("span", {}, [
        h("span", {}, "警员照片"),
        h(
          "el-popover",
          {
            props: {
              placement: "top-start",
              width: "200",
              trigger: "hover",
              content: "点击可预览放大后的头像信息！",
            },
          },
          [h("i", { slot: "reference", class: "el-icon-question" }, "")]
        ),
      ]);
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
    upload() {},
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.disabled = false;
      this.imagelist = [];
      this.open = true;
      this.title = "添加警员基本信息";
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      this.imagelist = [];
      const id = row.id || this.ids
      getInformation(id).then(response => {
        this.form = response.data;
        var imgUrl = getImgUrl(this.form.policePhoto);
        let obj = new Object();
        obj.url = imgUrl;
        this.imagelist.push(obj);
        this.disabled = true;
        this.open = true;
        this.title = "查看警员基本信息";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.disabled = false;
      this.imagelist = [];
      const id = row.id || this.ids
      getInformation(id).then(response => {
        this.form = response.data;
        var imgUrl = getImgUrl(this.form.policePhoto);
        let obj = new Object();
        obj.url = imgUrl;
        this.imagelist.push(obj);
        this.disabled = false;
        this.open = true;
        this.title = "修改警员基本信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
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
          if (this.form.id != null) {
            updateInformation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInformation(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除警员警号为"' + row.policeNumber + '"的数据项？').then(function() {
        return delInformation(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('polices/information/export', {
        ...this.queryParams
      }, `information_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
