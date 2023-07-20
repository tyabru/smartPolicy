<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="装备编码" prop="equipmentNumber">
        <el-input v-model="queryParams.equipmentNumber" placeholder="请输入装备编码" clearable/>
      </el-form-item>
      <el-form-item label="装备类型" prop="equipmentType">
        <el-select  v-model="queryParams.equipmentType" placeholder="请选择装备类型" style="width: 100%">
          <el-option v-for="item in dict.type.equipment_type" :key="item.value" :label="item.label" :value="Number(item.value)"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="装备描述" prop="equipmentDescription">
        <el-input v-model="queryParams.equipmentDescription" placeholder="请输入装备描述" clearable/>
      </el-form-item>
      <el-form-item label="采购时间" prop="procurementTime">
        <el-date-picker clearable v-model="queryParams.procurementTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择采购时间">
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
          v-hasPermi="['equipment:equipment:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['equipment:equipment:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['equipment:equipment:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['equipment:equipment:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="equipmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="装备编码" align="center" prop="equipmentNumber" />
      <el-table-column label="装备类型" align="center" prop="equipmentType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.equipment_type" :value="scope.row.equipmentType"/>
        </template>
      </el-table-column>
      <el-table-column label="装备描述" align="center" prop="equipmentDescription" />
      <el-table-column label="部门ID" align="center" prop="deptId" />
      <el-table-column label="采购时间" align="center" prop="procurementTime" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleSelect(scope.row)" 
            v-hasPermi="['polices:equipment:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['equipment:equipment:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['equipment:equipment:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改警用装备配发领取记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="装备编码" prop="equipmentNumber">
              <el-input v-model="form.equipmentNumber" placeholder="请输入装备编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="装备描述" prop="equipmentDescription">
              <el-input v-model="form.equipmentDescription" placeholder="请输入装备描述" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="装备类型" prop="equipmentType">
              <el-select  v-model="form.equipmentType" placeholder="请选择装备类型" style="width: 100%">
                <el-option v-for="item in dict.type.equipment_type" :key="item.value" :label="item.label" :value="Number(item.value)">
                  <el-tooltip placement="top">
                    <div slot="content">{{item.raw.remark}}</div>
                    <span style="float: left">{{ item.label }}</span>
                  </el-tooltip>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采购时间" prop="procurementTime">
              <el-date-picker style="width: 325px;" clearable v-model="form.procurementTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择采购时间">
              </el-date-picker>
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
import { listEquipment, getEquipment, delEquipment, addEquipment, updateEquipment } from "@/api/equipment/equipment";
import { getUserProfile, deptTreeSelect } from "@/api/system/user";
import DictTag from '@/components/DictTag';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Equipment",
  dicts: [ 'equipment_type' ],
  components: { DictTag ,Treeselect},
  data() {
    const validEquipmentNumber = async (rules, value, callback) => {
      if (value == null) {
        
      }
    }
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
      // 警用装备配发领取记录表格数据
      equipmentList: [],
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
        equipmentNumber: null,
        equipmentType: null,
        equipmentDescription: null,
        deptId: null,
        procurementTime: null,
        deleteTime: null,
        operateTime: null,
        operateName: null,
        operateType: null,
      },
      // 表单参数
      form: {
        operateName: null,
      },
      // 表单校验
      rules: {
        equipmentNumber: [
          { required: true, message: "装备编码不能为空", trigger: "blur" },
          { validator: validEquipmentNumber, trigger: 'blur' }
        ],
        equipmentType: [
          { required: true, message: "装备类型不能为空", trigger: "change" }
        ],
        equipmentDescription: [
        { required: true, message: "装备描述不能为空", trigger: "change" }
        ],
        procurementTime: [
          { required: true, message: "采购时间不能为空", trigger: "blur" }
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
    /** 查询警用装备配发领取记录列表 */
    getList() {
      this.loading = true;
      listEquipment(this.queryParams).then(response => {
        this.equipmentList = response.rows;
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
        equipmentNumber: null,
        equipmentType: null,
        equipmentDescription: null,
        deptId: null,
        procurementTime: null,
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.disabled = false;
      this.open = true;
      this.title = "添加警用装备信息";
    },
     /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      const id = row.id || this.ids
      getEquipment(id).then(response => {
        this.form = response.data;
        this.disabled = true;
        this.open = true;
        this.title = "查看警用装备信息";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEquipment(id).then(response => {
        this.form = response.data;
        this.disabled = false;
        this.open = true;
        this.title = "修改警用装备信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateEquipment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEquipment(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除警用装备编号为"' + row.equipmentNumber + '"的数据项？').then(function() {
        return delEquipment(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equipment/equipment/export', {
        ...this.queryParams
      }, `equipment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
