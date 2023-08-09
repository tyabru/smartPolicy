<template>
  <div class="app-container">
    <search-form-bar>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="所属单位" prop="companyId">
          <el-input v-model="queryParams.companyId" placeholder="请输入所属单位" clearable/>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="queryParams.name" placeholder="请输入名称" clearable/>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="queryParams.phone" placeholder="请输入联系方式" clearable/>
        </el-form-item>
        <el-form-item label="证件编号" prop="identityCode">
          <el-input v-model="queryParams.identityCode" placeholder="请输入证件编号" clearable/>
        </el-form-item>
        <el-form-item label="居住地址" prop="livingAddres">
          <el-input v-model="queryParams.livingAddres" placeholder="请输入居住地址" clearable/>
        </el-form-item>
        <el-form-item label="籍贯" prop="nativePalce">
          <el-input v-model="queryParams.nativePalce" placeholder="请输入籍贯" clearable/>
        </el-form-item>
        <el-form-item label="入职日期" prop="joinedDate">
          <el-date-picker clearable v-model="queryParams.joinedDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择入职日期">
          </el-date-picker>
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
          v-hasPermi="['company:employee:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['company:employee:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['company:employee:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['company:employee:export']">导出</el-button>
      </el-col>
      <right-toolbar style="padding-right: 19px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employeeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属单位" align="center" prop="companyName" />
      <el-table-column label="员工名称" align="center" prop="name" />
      <el-table-column label="联系方式" align="center" prop="phone" />
      <el-table-column label="证件类型" align="center" prop="identityType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_id_type" :value="scope.row.identityType"/>
        </template>
      </el-table-column>
      <el-table-column label="证件编号" align="center" prop="identityCode" />
      <el-table-column label="居住地址" align="center" prop="livingAddres" />
      <el-table-column label="籍贯" align="center" prop="nativePalce" />
      <el-table-column label="职位类型" align="center" prop="positionType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.position_type" :value="scope.row.positionType"/>
        </template>
      </el-table-column>
      <el-table-column label="入职日期" align="center" prop="joinedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.joinedDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人像图片" align="center" prop="faceImgUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.faceImgUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['company:employee:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['company:employee:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['company:employee:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改单位员工对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属单位" prop="companyId">
              <se-company v-model="form.companyId" placeholder="请输入所属单位" style="width: 325px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="员工名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="联系方式" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系方式" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证件类型" prop="identityType">
              <el-select  v-model="form.identityType" placeholder="请选择证件类型" style="width: 100%">
                <el-option v-for="item in dict.type.sys_id_type" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="证件编号" prop="identityCode">
              <el-input v-model="form.identityCode" placeholder="请输入证件编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="居住地址" prop="livingAddres">
              <el-input v-model="form.livingAddres" placeholder="请输入居住地址" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="籍贯" prop="nativePalce">
              <el-input v-model="form.nativePalce" placeholder="请输入籍贯" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入职日期" prop="joinedDate">
              <el-date-picker style="width: 325px;" clearable v-model="form.joinedDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择入职日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="人像图片" prop="faceImgUrl">
              <image-upload v-model="form.faceImgUrl"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职位" prop="positionType">
              <el-select  v-model="form.positionType" placeholder="请选择职位" style="width: 100%">
                <el-option v-for="item in dict.type.position_type" :key="item.value" :label="item.label" :value="item.value"></el-option>
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
import { listEmployee, getEmployee, delEmployee, addEmployee, updateEmployee } from "@/api/person/employee";
import DictTag from '@/components/DictTag';
import { validPpliceIdCard, validPhone } from "@/utils/validate";

export default {
  name: "Employee",
  dicts: ['sys_id_type', 'position_type' ],
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
      // 单位员工表格数据
      employeeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyId: null,
        name: null,
        phone: null,
        identityType: null,
        identityCode: null,
        livingAddres: null,
        nativePalce: null,
        positionType: null,
        joinedDate: null,
        faceImgUrl: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        companyId: [
          { required: true, message: "所属单位不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
          { min: 11, max: 11, message: "手机号长度为11", trigger: "blur" },
          {validator : validPhone, trigger: "blur"}
        ],
        identityType: [
          { required: true, message: "证件类型不能为空", trigger: "change" }
        ],
        identityCode: [
          { required: true, message: "证件编号不能为空", trigger: "blur" },
          { min: 18, max: 18, message: "身份证长度为18", trigger: "blur" },
          { validator: validPpliceIdCard, trigger: "blur" },
        ],
        joinedDate: [
          { required: true, message: "入职时间不能为空", trigger: "blur" }
        ],
        livingAddres: [
          { required: true, message: "居住地址不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询单位员工列表 */
    getList() {
      this.loading = true;
      listEmployee(this.queryParams).then(response => {
        this.employeeList = response.rows;
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
        companyId: null,
        name: null,
        phone: null,
        identityType: "10",
        identityCode: null,
        livingAddres: null,
        nativePalce: null,
        positionType: null,
        joinedDate: null,
        faceImgUrl: null,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null
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
      this.title = "添加单位员工";
    },
    /** 查看按钮操作 */
    handleView(row) {
      this.reset();
      const id = row.id || this.ids
      getEmployee(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.disabled = true;
        this.isDisplay = false;
        this.title = "查看单位员工";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEmployee(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.disabled = false;
        this.isDisplay = true;
        this.title = "修改单位员工";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEmployee(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmployee(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除单位员工名称为"' + row.name + '"的数据项？').then(function() {
        return delEmployee(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('company/employee/export', {
        ...this.queryParams
      }, `employee_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
