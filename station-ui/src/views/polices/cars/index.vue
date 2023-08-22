<template>
  <div class="app-container">
    <search-form-bar>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="车牌号码" prop="licenseNumber">
          <el-input v-model="queryParams.licenseNumber" placeholder="请输入车牌号码" clearable/>
        </el-form-item>
        <el-form-item label="车辆编码" prop="carCode">
          <el-input v-model="queryParams.carCode" placeholder="请输入公安内部车辆编码" clearable/>
        </el-form-item>
        <el-form-item label="车辆类型" prop="carType">
          <el-select  v-model="queryParams.carType" placeholder="请选择车辆类型" style="width: 100%">
            <el-option v-for="item in dict.type.sys_car_type" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车牌类型" prop="licenseType">
          <el-select  v-model="queryParams.licenseType" placeholder="请选择车牌类型" style="width: 100%">
            <el-option v-for="item in dict.type.sys_license_type" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否配备车载" prop="isVehicle">
          <el-select  v-model="queryParams.isVehicle" placeholder="请选择是否配备车载布控球" style="width: 100%">
            <el-option v-for="item in dict.type.sys_yes_no" :key="item.value" :label="item.label" :value="item.value"></el-option>
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
        v-hasPermi="['polices:cars:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" 
          v-hasPermi="['polices:cars:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" 
        v-hasPermi="['polices:cars:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" 
        v-hasPermi="['polices:cars:export']">导出</el-button>
      </el-col>
      <right-toolbar style="padding-right: 19px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="carsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="部门名称" align="center" prop="deptName" />
      <el-table-column label="车牌号码" align="center" prop="licenseNumber" />
      <el-table-column label="车辆编码" align="center" prop="carCode" />
      <el-table-column label="车牌类型" align="center" prop="licenseType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_license_type" :value="scope.row.licenseType"/>
        </template>
      </el-table-column>
      <el-table-column label="车辆类型" align="center" prop="carType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_car_type" :value="scope.row.carType"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="车长(单位cm)" align="center" prop="carLong" />
      <el-table-column label="车宽(单位cm)" align="center" prop="carHeight" />
      <el-table-column label="车高(单位cm)" align="center" prop="carWide" /> -->
      <el-table-column label="是否配备车载布控球" align="center" prop="isVehicle">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.isVehicle"/>
        </template>
      </el-table-column>
      <el-table-column label="车载设备编码" align="center" prop="equipmentNumber" />
      <el-table-column label="车辆照片" align="center" prop="vehiclePhotos" :render-header="renderZPHeader">
        <template slot-scope="scope">
          <el-image style="width: 65px; height: 85px" :src="getImgUrl(scope.row.vehiclePhotos)" 
            :preview-src-list="[getImgUrl(scope.row.vehiclePhotos)]" :onerror="errorUserPhoto"  fit="cover" ></el-image>
          </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleSelect(scope.row)" 
            v-hasPermi="['polices:cars:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['polices:cars:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['polices:cars:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改警用车辆对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="部门ID" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择车辆归属单位编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车牌号码" prop="licenseNumber">
              <el-input v-model="form.licenseNumber" placeholder="请输入车牌号码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="车辆编码" prop="carCode">
              <el-input v-model="form.carCode" placeholder="请输入公安内部车辆编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车辆品牌" prop="carBrand">
              <el-input v-model="form.carBrand" placeholder="请输入车辆品牌" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="车辆类型" prop="carType">
              <el-select  v-model="form.carType" placeholder="请选择车辆类型" style="width: 100%">
                <el-option v-for="item in dict.type.sys_car_type" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车牌类型" prop="licenseType">
              <el-select  v-model="form.licenseType" placeholder="请选择车牌类型" style="width: 100%">
                <el-option v-for="item in dict.type.sys_license_type" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否配备车载" prop="isVehicle">
              <el-select  v-model="form.isVehicle" placeholder="请选择是否配备车载布控球" style="width: 100%">
                <el-option v-for="item in dict.type.sys_yes_no" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车载编码" prop="equipmentNumber" 
              :rules="[{ required: form.isVehicle == 'Y', message: '当该车配车载时,车载编码不能为空', trigger: 'blur' }]">
              <el-select  v-model="form.equipmentNumber" placeholder="请选择车载设备" style="width: 100%" :disabled="form.isVehicle != 'Y'">
                <el-option v-for="item in vehicleEquipmentList" :key="item.deviceCode" :label="item.deviceCode" :value="item.deviceCode">
                {{item.deviceCode+"----------"+item.deviceName}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="车长" prop="carLong">
              <el-input style="width: 265px;" v-model="form.carLong" placeholder="请输入车辆长度(单位cm)" />(单位cm)
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车宽" prop="carHeight">
              <el-input style="width: 265px;" v-model="form.carHeight" placeholder="请输入车辆宽度(单位cm)" />(单位cm)
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="车高" prop="carWide">
              <el-input style="width: 265px;" v-model="form.carWide" placeholder="请输入车辆高度(单位cm)" />(单位cm)
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
            <el-form-item label="车辆照片" prop="vehiclePhotos">
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
import { listCars, getCars, delCars, addCars, updateCars, getPoliceCarByLicenseNumber ,getPoliceCarByCarCode, getPoliceCarByEquipmentNumber } from "@/api/polices/policeCars";
import { listPoliceOnboardEquipment } from "@/api/equipment/PoliceOnboardEquipment";
import { addImgData, getImage } from "@/api/polices/policeInformation";
import * as imageConversion from 'image-conversion';
import { getImgUrl } from "../../../utils";
import policeCarPhoto from "@/views/polices/cars/policeCar.png";
import DictTag from '@/components/DictTag';
import { getUserProfile, deptTreeSelect } from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Cars",
  dicts: ['sys_yes_no', 'sys_car_type', 'sys_license_type' ],
  components: { DictTag ,Treeselect},
  data() {
    //检验车牌号
    const validLicenseNumber = (rules, value, callback) => {
      if (value == null || value == "") {
        callback("车牌号码不能为空！")
      } else {
        getPoliceCarByLicenseNumber(value).then(response => {
          if (this.form.id != null && response.data != null && this.form.id != response.data.id) {
            callback("车牌号码已存在！")
          } else if (this.form.id == null && response.data != null){
            callback("车牌号码已存在！")
          } else {
            callback()
          }
        })
      }
    }
    //检验车牌号
    const validCarCode = (rules, value, callback) => {
      if (value == null || value == "") {
        callback("车辆编码不能为空！")
      } else {
        getPoliceCarByCarCode(value).then(response => {
          if (this.form.id != null && response.data != null && this.form.id != response.data.id) {
            callback("车辆编码已存在！")
          } else if (this.form.id == null && response.data != null){
            callback("车辆编码已存在！")
          } else {
            callback()
          }
        })
      }
    }
    return {
      isDisplay: false,
      disabled: true,
      user: null,
      errorUserPhoto: 'this.src="' + policeCarPhoto + '"',
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
      // 警用车辆表格数据
      carsList: [],
      // 部门树选项
      deptOptions: [],
      //车载设备集合
      vehicleEquipmentList: [],
      //车载设备集合
      vehicleEquipmentListUpdate: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licenseNumber: null,
        carCode: null,
        licenseType: null,
        carType: null,
        carBrand: null,
        deptId: null,
        carLong: null,
        carHeight: null,
        carWide: null,
        vehiclePhotos: null,
        isVehicle: null,
        equipmentNumber: null,
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
        licenseNumber: [
          { required: true, message: "车牌号码不能为空", trigger: "blur" },
          { validator : validLicenseNumber, trigger: "blur" }
        ],
        carCode: [
          { required: true, message: "公安内部车辆编码不能为空", trigger: "blur" },
          { validator : validCarCode, trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "车辆归属单位编码不能为空", trigger: "blur" }
        ],
        vehiclePhotos: [
          { required: true, message: "车辆照片不能为空", trigger: "blur" }
        ],
        isVehicle: [
          { required: true, message: "是否配备车载不能为空", trigger: "blur" }
        ],
        carType: [
          { required: true, message: "车辆类型不能为空", trigger: "blur" }
        ],
        licenseType: [
          { required: true, message: "车牌类型不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getUser();
    this.getDeptTree();
  },
  computed: {
    uploadDisabled: function () {
      return this.imagelist.length > 0;
    },
  },
  methods: {
    getImgUrl,
    getUser () {
      getUserProfile().then(response => {
        this.user = response.data;
        //this.queryParams.deptId = response.data.deptId;
        this.form.operateName = response.data.nickName;
      })
      this.getList();
    },
    /** 查询警用车辆列表 */
    getList() {
      this.loading = true;
      listCars(this.queryParams).then(response => {
        this.carsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      this.getVehicleEquipment();
    },
    //车载设备
    getVehicleEquipment() {
      let dateForm = {
        deptId: null
      }
      listPoliceOnboardEquipment(dateForm).then(response => {
        //全部车载
        this.vehicleEquipmentList = response.rows;
        this.vehicleEquipmentListUpdate = response.rows;
        this.handleData(this.vehicleEquipmentListUpdate);
      });
    },
    handleData(vehicleEquipmentList) {
      let equipmentListUpdate = [];
      let flage = true;
      for (let i = 0; i < vehicleEquipmentList.length; i++) {
        for (let j = 0; j < this.carsList.length; j++) {
          flage = true;
          if (vehicleEquipmentList[i].deviceCode == this.carsList[j].equipmentNumber) {
            flage = false;
            break;
          }
        }
        if (flage) {
          equipmentListUpdate.push(vehicleEquipmentList[i]);
        }
      }
      this.vehicleEquipmentList = equipmentListUpdate;
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
        licenseNumber: null,
        carCode: null,
        licenseType: null,
        carType: null,
        carBrand: null,
        deptId: null,
        carLong: null,
        carHeight: null,
        carWide: null,
        vehiclePhotos: null,
        isVehicle: null,
        equipmentNumber: null,
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
      this.form.vehiclePhotos = "vehiclePhotos";
      this.fd = new FormData();
      this.fd.append("file", file);
    },
    handleAvatarSuccess(res, file) {
      var pathArr = res.imgUrl.split("uploadPath");
      this.form.vehiclePhotos = pathArr[1];
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
      this.handleData(this.vehicleEquipmentListUpdate);
      this.open = true;
      this.isDisplay = true;
      this.title = "添加警用车辆";
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      this.imagelist = [];
      const id = row.id || this.ids
      this.vehicleEquipmentList = this.vehicleEquipmentListUpdate;
      getCars(id).then(response => {
        this.form = response.data;
        var imgUrl = getImgUrl(this.form.vehiclePhotos);
        let obj = new Object();
        obj.url = imgUrl;
        this.imagelist.push(obj);
        this.disabled = true;
        this.open = true;
        this.isDisplay = false;
        this.title = "查看警用车辆";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.imagelist = [];
      const id = row.id || this.ids
      this.vehicleEquipmentList = this.vehicleEquipmentListUpdate;
      getCars(id).then(response => {
        this.form = response.data;
        var imgUrl = getImgUrl(this.form.vehiclePhotos);
        let obj = new Object();
        obj.url = imgUrl;
        this.imagelist.push(obj);
        this.disabled = false;
        this.open = true;
        this.isDisplay = true;
        this.title = "修改警用车辆";
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
              self.form.vehiclePhotos = res.imgUrl;
            });
          }
          console.log(this.form)
          if (this.form.id != null) {
            updateCars(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCars(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除车牌号为"' + row.licenseNumber + '"的数据项？').then(function() {
        return delCars(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('polices/cars/export', {
        ...this.queryParams
      }, `cars_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
