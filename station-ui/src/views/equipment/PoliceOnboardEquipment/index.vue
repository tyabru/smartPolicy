<template>
  <div class="app-container">
    <search-form-bar>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="queryParams.deviceName" placeholder="请输入设备名称" clearable/>
        </el-form-item>
        <el-form-item label="设备编码" prop="deviceCode">
          <el-input v-model="queryParams.deviceCode" placeholder="请输入设备编码" clearable/>
        </el-form-item>
        <el-form-item label="型号" prop="model">
          <el-input v-model="queryParams.model" placeholder="请输入型号" clearable/>
        </el-form-item>
        <el-form-item label="生产厂家" prop="manufacturer">
          <el-input v-model="queryParams.manufacturer" placeholder="请输入生产厂家" clearable/>
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
          v-hasPermi="['polices:PoliceOnboardEquipment:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['polices:PoliceOnboardEquipment:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['polices:PoliceOnboardEquipment:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['polices:PoliceOnboardEquipment:export']">导出</el-button>
      </el-col>
      <right-toolbar style="padding-right: 19px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="PoliceOnboardEquipmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="部门名称" align="center" prop="deptName" />
      <el-table-column label="设备名称" align="center" prop="deviceName" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column label="型号" align="center" prop="model" />
      <el-table-column label="生产厂家" align="center" prop="manufacturer" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleSelect(scope.row)" 
            v-hasPermi="['polices:PoliceOnboardEquipment:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['polices:PoliceOnboardEquipment:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['polices:PoliceOnboardEquipment:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改车载设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="设备名称" prop="deviceName">
              <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备编码" prop="deviceCode">
              <el-input v-model="form.deviceCode" placeholder="请输入设备编码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="型号" prop="model">
              <el-input v-model="form.model" placeholder="请输入型号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生产厂家" prop="manufacturer">
              <el-input v-model="form.manufacturer" placeholder="请输入生产厂家" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="部门ID" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
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
        <el-button v-show="isDisplay" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPoliceOnboardEquipment, getPoliceOnboardEquipment, delPoliceOnboardEquipment, getPoliceOnboardEquipmentByDeviceCode,
  addPoliceOnboardEquipment, updatePoliceOnboardEquipment } from "@/api/equipment/PoliceOnboardEquipment";
import { getUserProfile, deptTreeSelect } from "@/api/system/user";
import DictTag from '@/components/DictTag';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "PoliceOnboardEquipment",
  dicts: [ 'sys_networking' ],
  components: { DictTag ,Treeselect},
  data() {
    const validDeviceCode = (rules, value, callback) => {
      if (value == null || value == "") {
        callback("装备编码不能为空")
      } else {
        getPoliceOnboardEquipmentByDeviceCode(value).then(response => {
          console.log(response)
          if (this.form.id != null && response.data != null && this.form.id != response.data.id) {
            callback("装备编码已存在！")
          } else if (this.form.id == null && response.data != null){
            callback("装备编码已存在！")
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
      // 车载设备表格数据
      PoliceOnboardEquipmentList: [],
      // 部门树选项
      deptOptions: undefined,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceName: null,
        deviceCode: null,
        model: null,
        manufacturer: null,
        deptId: null,
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
        deviceName: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],
        deviceCode: [
          { required: true, message: "编码不能为空", trigger: "blur" },
          { validator : validDeviceCode, trigger: "blur" }
        ],
        model: [
          { required: true, message: "型号不能为空", trigger: "blur" }
        ],
        manufacturer: [
          { required: true, message: "生产厂家不能为空", trigger: "blur" }
        ],
        deptId: [
          { required: true, message: "部门ID不能为空", trigger: "blur" }
        ],
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
    /** 查询车载设备列表 */
    getList() {
      this.loading = true;
      listPoliceOnboardEquipment(this.queryParams).then(response => {
        this.PoliceOnboardEquipmentList = response.rows;
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
        deviceName: null,
        deviceCode: null,
        model: null,
        manufacturer: null,
        deptId: null,
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.disabled = false;
      this.open = true;
      this.isDisplay = true;
      this.title = "添加车载设备";
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      const id = row.id || this.ids
      getPoliceOnboardEquipment(id).then(response => {
        this.form = response.data;
        this.disabled = true;
        this.open = true;
        this.isDisplay = false;
        this.title = "查看车载设备";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPoliceOnboardEquipment(id).then(response => {
        this.form = response.data;
        this.disabled = false;
        this.open = true;
        this.isDisplay = true;
        this.title = "修改车载设备";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePoliceOnboardEquipment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPoliceOnboardEquipment(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除车载设备编号为"' + ids + '"的数据项？').then(function() {
        return delPoliceOnboardEquipment(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('polices/PoliceOnboardEquipment/export', {
        ...this.queryParams
      }, `PoliceOnboardEquipment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
