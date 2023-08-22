<template>
  <div class="app-container">
    <search-form-bar>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="小区名称" prop="xqId">
          <el-input v-model="queryParams.xqId" placeholder="请输入小区名称" clearable/>
        </el-form-item>
        <el-form-item label="关注原因" prop="focusReason">
          <el-input v-model="queryParams.focusReason" placeholder="请输入关注原因-字典" clearable/>
        </el-form-item>
        <el-form-item label="关注级别" prop="level">
          <el-input v-model="queryParams.level" placeholder="请输入重点关注级别" clearable/>
        </el-form-item>
        <el-form-item label="人员姓名" prop="name">
          <el-input v-model="queryParams.name" placeholder="请输入人员姓名" clearable/>
        </el-form-item>
        <el-form-item label="身份证号" prop="certNo">
          <el-input v-model="queryParams.certNo" placeholder="请输入身份证号" clearable/>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="queryParams.phone" placeholder="请输入联系方式" clearable/>
        </el-form-item>
      </el-form>
      <template #btn>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </template>
    </search-form-bar>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['person:fcous:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['person:fcous:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['person:fcous:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['person:fcous:export']">导出</el-button>
      </el-col> -->
      <right-toolbar style="padding-right: 19px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fcousList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="小区名称" align="center" prop="communityName" />
      <el-table-column label="人员姓名" align="center" prop="name" />
      <el-table-column label="联系方式" align="center" prop="phone" />
      <el-table-column label="人员类型" align="center" prop="personType">
        <template slot-scope="scope">
          <span v-if="scope.row.personType == 1">常驻人口</span>
          <span v-if="scope.row.personType == 2">流动人员</span>
        </template>
      </el-table-column>
      <el-table-column label="关注原因" align="center" prop="focusReason">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_is_important" :value="scope.row.focusReason"/>
        </template>
      </el-table-column>
      <el-table-column label="重点关注级别" align="center" prop="level">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.important_level" :value="scope.row.level"/>
        </template>
      </el-table-column>
      <el-table-column label="关注原因" align="center" prop="reasonDesc" />
      <el-table-column label="人脸图片地址" align="center" prop="faceImgUrl" >
        <template slot-scope="scope">
          <image-preview :src="scope.row.faceImgUrl" :width="60" :height="80"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['person:fcous:query']">查看</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['person:fcous:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['person:fcous:remove']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改重点关注人口对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px" :disabled="disabled">
        <el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="人员姓名" prop="name">
                <el-input v-model="form.name" placeholder="请输入人员姓名" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="民族" prop="nation">
                <el-input v-model="form.nation" placeholder="请输入民族" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="身份证号" prop="certNo">
                <el-input v-model="form.certNo" placeholder="请输入身份证号" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系方式" prop="phone">
                <el-input v-model="form.phone" placeholder="请输入联系方式" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-col :span="12">
            <el-form-item label="小区名称" prop="xqId">
              <se-community v-model="addressForm.communityId"  class="width-100Rate" @change="communityChange" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="籍贯" prop="nativePalce">
              <el-input v-model="form.nativePalce" placeholder="请输入籍贯" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="关联地址" prop="metaAddrId">
              <se-room-select ref="roomSelect" @change="addressChanged" :disabled="!addressForm.communityCode"
                              :community-code="addressForm.communityCode" class="width-100Rate" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址全称" prop="address">
              <el-input :value="addressForm.fullAddress" class="width-100Rate" readonlyplaceholder="选择关联地址以填充地址全称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="关注级别"  prop="level">
              <el-select v-model="form.level" class="width-100Rate" placeholder="请选择关注的级别">
                <el-option v-for="dict in dict.type.important_level" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="居住日期" prop="livingDate">
              <el-date-picker clearable v-model="form.livingDate" type="date" class="width-100Rate" value-format="yyyy-MM-dd" placeholder="请选择居住日期"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="关注原因" class="width-100Rate" prop="focusReason">
              <el-select v-model="form.focusReason" class="width-100Rate" placeholder="请选择关注原因">
                <el-option v-for="dict in dict.type.sys_is_important" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="补充描述" prop="reasonDesc">
              <el-input v-model="form.reasonDesc" placeholder="请输入关注原因-补充描述" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="人脸图片地址" prop="faceImgUrl">
              <image-upload v-model="form.faceImgUrl" :limit="1"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="人员类型" prop="personType">
              <el-select v-model="form.personType" class="width-100Rate" placeholder="请选择人员类型">
                <el-option v-for="dict in dict.type.sys_person_type" :key="dict.value" :label="dict.label" :value="Number(dict.value)"></el-option>
              </el-select>
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
import { listFcous, getFcous, delFcous, addFcous, updateFcous } from "@/api/person/fcous";
import DictTag from '@/components/DictTag';
import { validPpliceIdCard, validPhone } from "@/utils/validate";

export default {
  name: "Fcous",
  dicts: [ 'important_level', 'sys_is_important', 'sys_person_type', 'sys_nation' ],
  components: { DictTag },
  data() {
    return {
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
      // 重点关注人口表格数据
      fcousList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xqId: null,
        personType: null,
        focusReason: null,
        level: null,
        reasonDesc: null,
        address: null,
        metaAddrId: null,
        nativePlace: null,
        livingDate: null,
        nation: null,
        name: null,
        certType: null,
        certNo: null,
        phone: null,
        faceImgUrl: null,
        features: null,
        sourcePlatform: null,
      },
      // 表单参数
      form: {},
      addressForm: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "人员姓名不能为空", trigger: "blur" }
        ],
        certNo: [
          { required: true, message: "身份证号不能为空", trigger: "blur" },
          { min: 18, max: 18, message: "身份证长度为18", trigger: "blur" },
          { validator: validPpliceIdCard, trigger: "blur" },
        ],
        phone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
          { min: 11, max: 11, message: "手机号长度为11", trigger: "blur" },
          {validator : validPhone, trigger: "blur"}
        ],
        // xqId: [
        //   { required: true, message: "小区名称不能为空", trigger: "blur" }
        // ],
        // metaAddrId: [
        //   { required: true, message: "关联地址不能为空", trigger: "blur" }
        // ],
        // fullAddress: [
        //   { required: true, message: "地址全称不能为空", trigger: "blur" }
        // ],
        level: [
          { required: true, message: "关注级别不能为空", trigger: "blur" }
        ],
        livingDate: [
          { required: true, message: "居住日期不能为空", trigger: "blur" }
        ],
        focusReason: [
          { required: true, message: "关注原因不能为空", trigger: "blur" }
        ],
        faceImgUrl: [
          { required: true, message: "人脸照片不能为空", trigger: "blur" }
        ],
        personType: [
        { required: true, message: "人员类型不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询重点关注人口列表 */
    getList() {
      this.loading = true;
      listFcous(this.queryParams).then(response => {
        this.fcousList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        xqId: null,
        fullAddress: null,
        personType: null,
        focusReason: null,
        level: null,
        reasonDesc: null,
        address: null,
        metaAddrId: null,
        nativePalce: null,
        livingDate: null,
        nation: null,
        name: null,
        certType: null,
        certNo: null,
        phone: null,
        faceImgUrl: null,
        features: null,
        createBy: null,
        createTime: null,
        sourcePlatform: null,
        updateBy: null,
        updateTime: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.disabled = false;
      this.isDisplay = true;
      this.title = "添加重点关注人口";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFcous(id).then(response => {
        this.addressForm.communityId = response.data.xqId;
        this.addressForm.fullAddress = response.data.address;
        this.addressForm.metaAddrId = response.data.metaAddrId;
        this.form = response.data;
        this.open = true;
        this.disabled = false;
        this.isDisplay = true;
        this.title = "修改重点关注人口";
      });
    },
    /** 查看按钮操作 */
    handleView(row) {
      this.reset();
      const id = row.id || this.ids
      getFcous(id).then(response => {
        this.addressForm.communityId = response.data.xqId;
        this.addressForm.fullAddress = response.data.address;
        this.addressForm.metaAddrId = response.data.metaAddrId;
        this.form = response.data;
        this.open = true;
        this.disabled = true;
        this.isDisplay = false;
        this.title = "查看重点关注人口";
      });
    },
    handleData() {
      this.form.address = this.addressForm.fullAddress;
      this.form.metaAddrId = this.addressForm.metaAddrId;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.handleData();
          if (this.form.id != null) {
            updateFcous(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFcous(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除重点关注人口编号为"' + row.name + '"的数据项？').then(function() {
        return delFcous(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('person/fcous/export', {
        ...this.queryParams
      }, `fcous_${new Date().getTime()}.xlsx`)
    },
    communityChange(item, raw) {
      this.$set(this.addressForm, 'communityName', raw.name)
      this.$set(this.addressForm, 'communityCode', raw.code)
    },
    addressChanged(item, raw) {
      this.$set(this.addressForm, 'metaAddrId', item)
      this.$set(this.addressForm, 'fullAddress', raw.fullAddress)
      this.$set(this.addressForm, 'shortName', raw.shortName)
      this.$set(this.addressForm, 'id', raw.id)
    },
    removeAddressData(scope) {
      if(scope.row.id) {
        this.houseList[scope.$index].delFlag = 1;
      } else {
        this.houseList.splice(scope.$index, 1)
      }
    }
  }
};
</script>
