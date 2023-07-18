<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="警号" prop="policeNumber">
        <el-input v-model="queryParams.policeNumber" placeholder="请输入警号" clearable/>
      </el-form-item>
      <el-form-item label="装备编码" prop="equipmentNumber">
        <el-input v-model="queryParams.equipmentNumber" placeholder="请输入装备编码" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="是否归还" prop="isReturn">
        <el-select  v-model="queryParams.isReturn" placeholder="请输入是否归还" style="width: 100%">
          <el-option v-for="item in dict.type.sys_yes_no" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否损坏" prop="isEquipmentDefective">
        <el-select  v-model="queryParams.isEquipmentDefective" placeholder="请输入装备是否损坏" style="width: 100%">
          <el-option v-for="item in dict.type.sys_yes_no" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="损坏原因" prop="causesOfDamage">
        <el-input v-model="queryParams.causesOfDamage" placeholder="请输入损坏原因" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['equipment:claimReturn:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['equipment:claimReturn:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['equipment:claimReturn:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['equipment:claimReturn:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="claimReturnList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="警号" align="center" prop="policeNumber" />
      <el-table-column label="装备编码" align="center" prop="equipmentNumber" />
      <el-table-column label="配发日期" align="center" prop="distributionDate"/>
      <el-table-column label="配发原因" align="center" prop="receivingReason" />
      <el-table-column label="是否归还" align="center" prop="isReturn" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.isReturn"/>
        </template>
      </el-table-column>
      <el-table-column label="归还日期" align="center" prop="returnDate" />
      <el-table-column label="是否损坏" align="center" prop="isEquipmentDefective" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.isEquipmentDefective"/>
        </template>
      </el-table-column>
      <el-table-column label="损坏原因" align="center" prop="causesOfDamage" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleSelect(scope.row)" 
            v-hasPermi="['polices:claimReturn:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['equipment:claimReturn:edit']">修改</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['equipment:claimReturn:remove']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改警用设备配发归还对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="警号" prop="policeNumber">
              <el-select  v-model="form.policeNumber" placeholder="请选择警号" style="width: 100%">
                <el-option v-for="item in policeInformationList" :key="item.policeNumber" :label="item.policeNumber" :value="item.policeNumber">
                {{item.policeNumber+"----------"+item.policeName}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="装备编码" prop="equipmentNumber">
              <el-select  v-model="form.equipmentNumber" placeholder="请输入装备编码" style="width: 100%">
                <el-option v-for="item in equipmentList" :key="item.equipmentNumber" :label="item.equipmentNumber" :value="item.equipmentNumber">
                {{item.equipmentNumber+"----------"+item.equipmentDescription}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="配发日期" prop="distributionDate">
              <el-date-picker style="width: 325px;" clearable v-model="form.distributionDate" type="datetime" 
                  value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择配发日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="配发原因" prop="receivingReason">
              <el-input v-model="form.receivingReason" placeholder="请输入配发原因" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否归还" prop="isReturn">
              <el-select  v-model="form.isReturn" placeholder="请输入是否归还" style="width: 100%">
                <el-option v-for="item in dict.type.sys_yes_no" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归还日期" prop="returnDate">
              <el-date-picker style="width: 325px;" clearable v-model="form.returnDate" type="datetime" 
                  value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择归还日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否损坏" prop="isEquipmentDefective">
              <el-select  v-model="form.isEquipmentDefective" placeholder="请输入装备是否损坏" style="width: 100%">
                <el-option v-for="item in dict.type.sys_yes_no" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="损坏原因" prop="causesOfDamage">
              <el-input v-model="form.causesOfDamage" placeholder="请输入损坏原因" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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
import { listClaimReturn, getClaimReturn, delClaimReturn, addClaimReturn, updateClaimReturn } from "@/api/equipment/claimReturn";
import { listEquipment } from "@/api/equipment/equipment";
import { listInformation } from "@/api/polices/policeInformation";
import { getUserProfile, deptTreeSelect } from "@/api/system/user";
import DictTag from '@/components/DictTag';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "ClaimReturn",
  dicts: [ 'equipment_type', 'sys_yes_no'],
  components: { DictTag, Treeselect },
  data() {
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
      // 警用设备管理表格数据
      claimReturnList: [],
      // 警员信息集合
      policeInformationList: [],
      // 装备集合
      equipmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        policeNumber: null,
        equipmentNumber: null,
        distributionDate: null,
        receivingReason: null,
        isReturn: null,
        returnDate: null,
        isEquipmentDefective: null,
        deptId: null,
        causesOfDamage: null,
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
        policeNumber: [
          { required: true, message: "警号不能为空", trigger: "blur" }
        ],
        equipmentNumber: [
          { required: true, message: "装备编码不能为空", trigger: "blur" }
        ],
        distributionDate: [
          { required: true, message: "配发日期不能为空", trigger: "blur" }
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
      this.getPoliceList();
    },
    /** 查询警用设备管理列表 */
    getList() {
      this.loading = true;
      listClaimReturn(this.queryParams).then(response => {
        this.claimReturnList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getPoliceList() {
      let dateForm = {
        deptId: this.queryParams.deptId
      }
      listInformation(dateForm).then(response => {
        this.policeInformationList = response.rows;
      });
      listEquipment(dateForm).then(response => {
        this.equipmentList = response.rows;
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
        equipmentNumber: null,
        distributionDate: null,
        receivingReason: null,
        isReturn: 'N',
        returnDate: null,
        isEquipmentDefective: null,
        deptId: null,
        causesOfDamage: null,
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
      this.title = "添加警用设备配发与归还记录";
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      const id = row.id || this.ids
      getClaimReturn(id).then(response => {
        this.form = response.data;
        this.disabled = true;
        this.open = true;
        this.title = "查看警用设备配发与归还记录";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClaimReturn(id).then(response => {
        this.form = response.data;
        this.disabled = false;
        this.open = true;
        this.title = "修改警用设备配发与归还记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateClaimReturn(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClaimReturn(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除警用设备管理编号为"' + ids + '"的数据项？').then(function() {
        return delClaimReturn(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equipment/claimReturn/export', {
        ...this.queryParams
      }, `claimReturn_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
