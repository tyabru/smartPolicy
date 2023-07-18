<template>
  <div class="app-container">
    <el-calendar v-model="dateValue" class="calendar-main">
      <template slot="dateCell" slot-scope="{date, data}">
        <p style="text-align: center;" :class="data.isSelected ? 'is-selected' : ''">
          {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
        </p>
        <span v-if="managementsList.find(item => item.dutyDate == data.day)">
          <p style="text-align: center;color: blue;" @click = "handleUpdate(data)">{{managementsList.find(item => item.dutyDate == data.day).policeNames}}</p>
        </span>
        <span v-else>
          <p style="text-align: center;color: red;" @click = "handleAdd(data)">未排班</p>
        </span>
      </template>
    </el-calendar>

    <!-- <el-table v-loading="loading" :data="managementsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="警号" align="center" prop="policeNumber" />
      <el-table-column label="值班名称" align="center" prop="dutyType" />
      <el-table-column label="值班开始时间" align="center" prop="startTime" />
      <el-table-column label="值班结束时间" align="center" prop="endTime" />
      <el-table-column label="带班领导" align="center" prop="shiftLeaderPoliceNumber" />
      <el-table-column label="值班情况" align="center" prop="dutySituation" />
      <el-table-column label="交接事项" align="center" prop="handoverMatters" />
      <el-table-column label="接班人警号" align="center" prop="successorPoliceNumber" />
      <el-table-column label="接班时间" align="center" prop="successionTime" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['polices:managements:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['polices:managements:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/> -->

    <!-- 添加或修改排班管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="值班组" prop="dutyGroupId">
              <el-select  v-model="form.dutyGroupId" placeholder="请选择值班组" style="width: 100%">
                <el-option v-for="item in policesDutyGroupList" :key="item.id" :label="item.groupName" :value="item.id">
                {{item.groupName+"-----"+item.policeNames}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="值班名称" prop="dutyType">
              <el-input v-model="form.dutyType" placeholder="请输入值班名称(例:主班,辅班)" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="带班领导" prop="shiftLeaderPoliceNumber">
              <el-select  v-model="form.shiftLeaderPoliceNumber" placeholder="请选择带班领导" style="width: 100%">
                <el-option v-for="item in policeInformationList" :key="item.policeNumber" :label="item.policeName" :value="item.policeNumber">
                {{item.policeName+"----------"+item.policeNumber}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="值班日期" prop="dutyDate">
              <el-date-picker style="width: 325px;" clearable v-model="form.dutyDate" type="date" :disabled="true"
              value-format="yyyy-MM-dd" placeholder="请选择值班开始时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="值班情况" prop="dutySituation">
              <el-input v-model="form.dutySituation" placeholder="请输入值班情况" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="交接事项" prop="handoverMatters">
              <el-input v-model="form.handoverMatters" placeholder="请输入交接事项" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="接班组" prop="successorGroupId">
              <el-select  v-model="form.successorGroupId" placeholder="请选择值班组" style="width: 100%">
                <el-option v-for="item in policesDutyGroupList" :key="item.id" :label="item.groupName" :value="item.id">
                {{item.groupName+"----------"+item.policeNames}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="接班时间" prop="successionTime">
              <el-date-picker style="width: 325px;" clearable v-model="form.successionTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择接班时间">
              </el-date-picker>
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
import { listManagements, getManagements, getManagementsByDutyDate, delManagements, addManagements, updateManagements } from "@/api/polices/managements";
import { getUserProfile  } from "@/api/system/user";
import { listPolices } from "@/api/polices/policeDutyGroups";
import { listInformation } from "@/api/polices/policeInformation";

export default {
  name: "Managements",
  data() {
    return {
      user: null,
      dateValue: new Date(),
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
      managements: null,
      // 排班管理表格数据
      managementsList: [],
      // 警员分组集合
      policesDutyGroupList: [],
      // 警员信息集合
      policeInformationList: [],
      dateOperateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dutyGroupId: null,
        policeNumber: null,
        dutyType: null,
        dutyDate: null,
        shiftLeaderPoliceNumber: null,
        dutySituation: null,
        handoverMatters: null,
        successorGroupId: null,
        successorPoliceNumber: null,
        successionTime: null,
        deleteTime: null,
        operateName: null,
        operateTime: null,
        operateType: null,
      },
      // 表单参数
      form: {
        operateName: null,
      },
      // 表单校验
      rules: {
        dutyGroupId: [
          { required: true, message: "值班组不能为空", trigger: "blur" }
        ],
        shiftLeaderPoliceNumber: [
          { required: true, message: "带班领导不能为空", trigger: "blur" }
        ],
        dutyType: [
          { required: true, message: "值班名称不能为空", trigger: "blur" }
        ],
        successorGroupId: [
          { required: true, message: "值班组不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getUser();
  },
  watch: {
    dateValue: {
      handler(newVal) {
        if (newVal) {
          this.$emit('changeCalendar',newVal)
        }
      }
    }
  },
  methods: {
    getUser () {
      getUserProfile().then(response => {
        this.user = response.data;
        //this.queryParams.deptId = response.data.deptId;
        this.form.operateName = response.data.nickName;
      })
      this.getList();
      this.getpolicesDutyGroup();
      this.getPoliceInformationList();
    },
    //获取警员分组信息
    getpolicesDutyGroup() {
      let dataForm = {}
      listPolices(dataForm).then(response => {
        this.policesDutyGroupList = response.rows;
      });
    },
    //获取警员信息
    getPoliceInformationList() {
      listInformation(this.queryParams).then(response => {
        this.policeInformationList = response.rows;
      });
    },
    //获取排班信息
    getManagementByDate(date) {
      getManagementsByDutyDate(date.day).then(response => {
        this.managements = response.data;
      })
    },
    /** 查询排班管理列表 */
    getList() {
      this.loading = true;
      listManagements(this.queryParams).then(response => {
        this.managementsList = response.rows;
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
        dutyGroupId: null,
        policeNumber: null,
        dutyType: null,
        dutyDate: null,
        shiftLeaderPoliceNumber: null,
        dutySituation: null,
        handoverMatters: null,
        successorGroupId: null,
        successorPoliceNumber: null,
        successionTime: null,
        createTime: null,
        updateTime: null,
        deleteTime: null,
        operateName: this.form.operateName,
        operateTime: null,
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
    handleAdd(date) {
      this.reset();
      this.form.dutyDate = date.day
      this.open = true;
      this.title = "添加排班管理";
    },
    /** 修改按钮操作 */
    handleUpdate(date) {
      this.reset();
      getManagementsByDutyDate(date.day).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看或修改排班管理";
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateManagements(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManagements(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除排班管理编号为"' + ids + '"的数据项？').then(function() {
        return delManagements(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('polices/managements/export', {
        ...this.queryParams
      }, `managements_${new Date().getTime()}.xlsx`)
    },
  },
};
</script>
