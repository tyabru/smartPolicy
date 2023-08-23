<template>
  <div class="app-container">
    <search-form-bar>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="案件编码" prop="caseCode">
          <el-input v-model="queryParams.caseCode" placeholder="请选择案件编码" clearable/>
        </el-form-item>
        <el-form-item label="人员姓名" prop="peopleName">
          <el-input v-model="queryParams.peopleName" placeholder="请输入人员姓名" clearable/>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="queryParams.phone" placeholder="请输入联系电话" clearable/>
        </el-form-item>
        <el-form-item label="证件号码" prop="cerNo">
          <el-input v-model="queryParams.cerNo" placeholder="请输入证件号码" clearable/>
        </el-form-item>
        <el-form-item label="居住地址" prop="residentialAddress">
          <el-input v-model="queryParams.residentialAddress" placeholder="请输入居住地址" clearable/>
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
          v-hasPermi="['contradiction:personnel:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['contradiction:personnel:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['contradiction:personnel:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['contradiction:personnel:export']">导出</el-button>
      </el-col>
      <right-toolbar style="padding-right: 19px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="personnelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案件编码" align="center" prop="caseCode" />
      <el-table-column label="人员姓名" align="center" prop="peopleName" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="身份证号码" align="center" prop="cerNo" />
      <el-table-column label="居住地址" align="center" prop="residentialAddress" />
      <el-table-column label="参与原因" align="center" prop="partakePerson" />
      <el-table-column label="人员照片" align="center" prop="imageUrl">
        <template slot-scope="scope">
          <image-preview :src="scope.row.imageUrl" :width="60" :height="80"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['contradiction:personnel:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['contradiction:personnel:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['contradiction:personnel:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改参与人员信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="span">
            <el-form-item label="案件编码" prop="caseCode">
              <el-select  v-model="form.caseCode" placeholder="请选择案件编码" style="width: 100%">
                <el-option v-for="item in contradictionList" :key="item.caseCode" :label="item.caseCode" :value="item.caseCode"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="人员姓名" prop="peopleName">
              <el-input v-model="form.peopleName" placeholder="请输入人员姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="证件号码" prop="cerNo">
              <el-input v-model="form.cerNo" placeholder="请输入身份证号码" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="居住地址" prop="residentialAddress">
              <el-input v-model="form.residentialAddress" placeholder="请输入居住地址" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="参与原因" prop="partakePerson">
              <el-input v-model="form.partakePerson" placeholder="请输入参与原因" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="工作单位" prop="workUnit">
              <el-input v-model="form.workUnit" placeholder="请输入工作单位" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="单位地址" prop="workAddress">
              <el-input v-model="form.workAddress" placeholder="请输入工作单位地址" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="参与角色" prop="partakeRole">
              <el-input v-model="form.partakeRole" placeholder="请输入参与角色" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
          <el-col :span="span">
            <el-form-item label="人员照片" prop="imageUrl">
              <image-upload v-model="form.imageUrl" :limit="1"/>
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
import { listPersonnel, getPersonnel, delPersonnel, addPersonnel, updatePersonnel } from "@/api/contradiction/personnel";
import { getUserProfile } from "@/api/system/user";
import { listContradiction } from "@/api/contradiction/contradiction";
import { validPpliceIdCard, validPhone } from "@/utils/validate";

export default {
  name: "Personnel",
  data() {
    return {
      user: null,
      span: 12,
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
      // 参与人员信息表格数据
      personnelList: [],
      // 矛盾纠纷信息表格数据
      contradictionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseCode: null,
        peopleName: null,
        phone: null,
        cerNo: null,
        residentialAddress: null,
        workUnit: null,
        workAddress: null,
      },
      // 表单参数
      form: {
        operateName: null,
      },
      // 表单校验
      rules: {
        caseCode: [{ required: true, message: "案件编码不能为空", trigger: "blur" }],
        peopleName: [{ required: true, message: "人员姓名不能为空", trigger: "blur" }],
        phone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" },
          { min: 11, max: 11, message: "联系电话长度为11", trigger: "blur" },
          {validator : validPhone, trigger: "blur"}
        ],
        cerNo: [{ required: true, message: "身份证号码不能为空", trigger: "blur" },
          { min: 18, max: 18, message: "身份证长度为18", trigger: "blur" },
          { validator: validPpliceIdCard, trigger: "blur" },
        ],
        residentialAddress: [{ required: true, message: "居住地址不能为空", trigger: "blur" }],
        partakePerson: [{ required: true, message: "参与原因不能为空", trigger: "blur" }],
        imageUrl: [{ required: true, message: "人员照片不能为空", trigger: "blur" }],
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
      this.getContradictionList();
    },
    /** 查询参与人员信息列表 */
    getList() {
      this.loading = true;
      listPersonnel(this.queryParams).then(response => {
        this.personnelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询纠纷信息列表 */
    getContradictionList() {
      let form = {
        deptId: null
      }
      listContradiction(form).then(response => {
        this.contradictionList = response.rows;
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
        caseCode: null,
        disputeId: null,
        peopleName: null,
        phone: null,
        cerNo: null,
        residentialAddress: null,
        workUnit: null,
        workAddress: null,
        partakePerson: null,
        partakeRole: null,
        imageUrl: null,
        createTime: null,
        updateTime: null,
        operateTime: null,
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.disabled = false;
      this.isDisplay = true;
      this.open = true;
      this.title = "添加参与人员信息";
    },
    /** 查看按钮操作 */
    handleView(row) {
      this.reset();
      const id = row.id || this.ids
      getPersonnel(id).then(response => {
        this.form = response.data;
        this.disabled = true;
        this.isDisplay = false;
        this.open = true;
        this.title = "查看参与人员信息";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPersonnel(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.disabled = false;
        this.isDisplay = true;
        this.title = "修改参与人员信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePersonnel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPersonnel(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除案件编码为"' + row.caseCode + '"中的参与人员姓名为"' + row.peopleName + '"的数据项？').then(function() {
        return delPersonnel(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('contradiction/personnel/export', {
        ...this.queryParams
      }, `personnel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
