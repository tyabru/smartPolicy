<template>
  <table-panel :show-search="showSearch" :loading="loading">
    <template #search-form>
      <el-form :model="queryParams" ref="queryForm" size="mini" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="所属小区" prop="communityId">
          <se-community v-model="queryParams.communityId" style="width: 193px;" />
        </el-form-item>
        <el-form-item label="人员姓名" prop="name">
          <el-input v-model="queryParams.name" placeholder="请输入人员姓名" clearable />
        </el-form-item>
        <el-form-item label="身份证号" prop="certNo">
          <el-input class="width-100Rate" v-model="queryParams.certNo" placeholder="请输入身份证号" clearable/>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input class="width-100Rate" v-model="queryParams.phone" placeholder="请输入手机号" clearable/>
        </el-form-item>
        <el-form-item label="是否临时工" prop="isTemp">
          <el-select v-model="queryParams.isTemp" class="width-100Rate" placeholder="请输入是否是临时工" >
            <el-option v-for="item in dict.type['sys_yes_no']" :key="item.value" :value="item.value" :label="item.label"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否离职" prop="isLeaving">
          <el-select v-model="queryParams.isLeaving" class="width-100Rate" placeholder="请输入是否离职" >
            <el-option v-for="item in dict.type['sys_yes_no']" :key="item.value" :value="item.value" :label="item.label"/>
          </el-select>
        </el-form-item>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button type="primary" icon="el-icon-search" size="mini" @click="queryChanged">搜索</el-button>
      <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
    </template>
    <template #btn>
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['wuye:wuye:add']">新增</el-button>
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['wuye:wuye:edit']">修改</el-button>
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['wuye:wuye:remove']">删除</el-button>
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['wuye:wuye:export']">导出</el-button>
      <right-toolbar style="padding-right: 14px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </template>
 
    <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属小区" align="center" prop="community.name" />
      <el-table-column label="人员姓名" align="center" prop="name" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="身份证号码" align="center" prop="certNo" />
      <el-table-column label="工作类型" align="center" prop="workType" />
      <el-table-column label="人脸图片" align="center" prop="faceImgUrl">
        <template v-slot="{row}">
          <image-preview :src="row.faceImgUrl" width="68px" height="98px" />
        </template>
      </el-table-column>
      <el-table-column label="是否临时工" align="center" prop="isTemp" >
        <template v-slot="{row}">
          <dict-tag :options="dict.type['sys_yes_no']" :value="row.isTemp" />
        </template>
      </el-table-column>
      <el-table-column label="是否离职" align="center" prop="isLeaving" >
        <template v-slot="{row}">
          <dict-tag :options="dict.type['sys_yes_no']" :value="row.isLeaving" />
        </template>
      </el-table-column>
      <el-table-column label="入职时间" align="center" prop="startTime" width="180" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['company:wuye:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['wuye:wuye:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['wuye:wuye:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
 
    <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" 
        :pageSizes="pageSizes" @pagination="queryChanged" #page></pagination>
 
    <!-- 添加或修改物业信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属小区" prop="communityId">
              <se-community v-model="form.communityId" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工作单位" prop="company">
              <el-input v-model="form.company" placeholder="请输入工作单位" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="工作职位" prop="company">
              <el-input v-model="form.workType" placeholder="请输入工作职位类型" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="人员姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入人员姓名" />
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
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="现居地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入现居地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="籍贯" prop="nativePlace">
              <el-input v-model="form.nativePlace" placeholder="请输入籍贯" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker clearable class="width-100Rate" v-model="form.startTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择开始工作时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否是临时工" prop="isTemp">
              <el-select v-model="form.isTemp" class="width-100Rate" placeholder="请输入是否是临时工" >
                <el-option v-for="item in dict.type['sys_yes_no']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否离职" prop="isLeaving">
              <el-select v-model="form.isLeaving" class="width-100Rate" placeholder="请输入是否离职" >
                <el-option v-for="item in dict.type['sys_yes_no']" :key="item.value" :value="item.value" :label="item.label"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-show="form.isLeaving === 'Y'" label="离职时间" :rules="[{required: form.isLeaving === 'Y', message: '已离职员工离职时间为必填！' }]">
              <el-date-picker clearable class="width-100Rate" v-model="form.endTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择工作结束时间/离职时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="人脸图片地址" prop="faceImgUrl">
              <image-upload v-model="form.faceImgUrl"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-show="isDisplay" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </table-panel>
</template>
 
<script>
import { listWuye, getWuye, delWuye, addWuye, updateWuye } from "@/api/community/wuye";
import TablePanel from '@/components/TablePanel/index.vue';
import tableListMixins from '@/mixins/tableListMixins.js';
import { validPpliceIdCard, validPhone } from "@/utils/validate";
 
export default {
  name: "Wuye",
  dicts: ['sys_yes_no'],
  components: { TablePanel },
  mixins: [tableListMixins],
  data() {
    return {
      //是否显示
      isDisplay: false,
      //是否禁用修改
      disabled: true,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        communityId: [{ required: true, message: "所属小区不能为空", trigger: "blur" }],
        company: [{ required: true, message: "工作单位不能为空", trigger: "blur" }],
        workType: [{ required: true, message: "工作类型不能为空", trigger: "change" }],
        name: [{ required: true, message: "人员姓名不能为空", trigger: "blur" }],
        certNo: [{ required: true, message: "身份证号不能为空", trigger: "blur" },
          { min: 18, max: 18, message: "身份证长度为18", trigger: "blur" },
          { validator: validPpliceIdCard, trigger: "blur" },
        ],
        phone: [{ required: true, message: "手机号不能为空", trigger: "blur" },
          { min: 11, max: 11, message: "手机号长度为11", trigger: "blur" },
          {validator : validPhone, trigger: "blur"}
        ],
        address: [{ required: true, message: "现居地址不能为空", trigger: "blur" }],
        startTime: [{ required: true, message: "开始工作时间不能为空", trigger: "blur" }],
        isLeaving: [{ required: true, message: "是否离职不能为空", trigger: "blur" }],
      }
    };
  },
  computed: {
    communityName() {
      return this.form.community?.name
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询物业信息管理列表 */
    getList() {
      this.initTableData(listWuye)
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {};
      this.resetForm("form");
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.disabled = false;
      this.isDisplay = true;
      this.title = "添加物业信息管理";
    },
    /** 查看按钮操作 */
    handleView(row) {
      this.reset();
      const id = row.id || this.ids
      getWuye(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.disabled = true;
        this.isDisplay = false;
        this.title = "查看物业信息管理";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWuye(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.disabled = false;
        this.isDisplay = true;
        this.title = "修改物业信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWuye(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWuye(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除物业人员名称为"' + row.name + '"的数据项？').then(function() {
        return delWuye(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('wuye/wuye/export', {
        ...this.queryParams
      }, `wuye_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
