<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="组名" prop="groupName">
        <el-input v-model="queryParams.groupName" placeholder="请输入组名" clearable/>
      </el-form-item>
      <el-form-item label="组员" prop="teamMembers">
        <el-select  v-model="queryParams.teamMembers" placeholder="请选择组员" style="width: 100%" clearable>
          <el-option v-for="item in policeInformationUpdate" :key="item.policeNumber" :label="item.policeNumber" :value="item.policeNumber">
          {{item.policeNumber+"----------"+item.policeName}}
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" 
          @click="handleAdd" v-hasPermi="['polices:polices:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" 
          v-hasPermi="['polices:polices:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['polices:polices:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['polices:polices:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="policesDutyGroupList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="组名" align="center" prop="groupName" />
      <el-table-column label="组员警号" align="center" prop="teamMembers" />
      <el-table-column label="组员姓名" align="center" prop="policeNames" />
      <el-table-column label="负责区域" align="center" prop="responsibleArea" />
      <el-table-column label="值班类型" align="center" prop="dutyType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.police_type" :value="scope.row.dutyType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" align="center" prop="operateTime" width="180"/>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleSelect(scope.row)" 
            v-hasPermi="['polices:polices:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['polices:polices:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['polices:polices:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <!-- 添加或修改警员分组详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="850px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="disabled">
        <el-row>
          <el-col :span="12">
            <el-form-item label="组名" prop="groupName">
              <el-input v-model="form.groupName" placeholder="请输入组名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="组员" prop="teamMembers">
              <el-select  v-model="form.teamMembers" placeholder="请选择组员" style="width: 100%" multiple>
                <el-option v-for="item in policeInformationList" :key="item.policeNumber" :label="item.policeNumber" :value="item.policeNumber">
                {{item.policeNumber+"----------"+item.policeName}}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="值班类型" prop="dutyType">
              <el-select  v-model="form.dutyType" placeholder="请选择值班类型" style="width: 100%">
                <el-option v-for="item in dict.type.police_type" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责区域" prop="responsibleArea">
              <el-input v-model="form.responsibleArea" placeholder="请输入负责区域" />
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
import { listPolices, getPolices, delPolices, addPolices, updatePolices } from "@/api/polices/policeDutyGroups";
import { listInformation } from "@/api/polices/policeInformation";
import { getUserProfile } from "@/api/system/user";
import DictTag from '@/components/DictTag';

export default {
  name: "Polices",
  dicts: ['police_type'],
  components: { DictTag },
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
      // 警员分组详情表格数据
      policesDutyGroupList: [],
      // 警员信息集合
      policeInformationList: [],
      // 修改时全部警员信息集合
      policeInformationListUpdate: [],
      policeInformationUpdate:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupName: null,
        teamMembers: null,
      },
      // 表单参数
      form: {
        operateName: null,
      },
      // 表单校验
      rules: {
        groupName: [
          { required: true, message: "名不能为空", trigger: "blur" }
        ],
        teamMembers: [
          { required: true, message: "组员不能为空", trigger: "blur" }
        ],
        dutyType: [
          { required: true, message: "值班类型不能为空", trigger: "change" }
        ],
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
        //this.queryParams.deptId = response.data.deptId;
        this.form.operateName = response.data.nickName;
      })
      this.getList();
    },
    /** 查询警员分组详情列表 */
    getList() {
      this.loading = true;
      listPolices(this.queryParams).then(response => {
        this.policesDutyGroupList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.getPoliceList();
      });
    },
    /** 查询警员 */
    getPoliceList() {
      let dateForm = {
        deptId: this.queryParams.deptId
      }
      let allPoliceNumberList = []
      listInformation(dateForm).then(response => {
        this.policeInformationList = response.rows;
        this.policeInformationUpdate = response.rows;
        for (let i = 0; i < this.policeInformationList.length; i++) {
          allPoliceNumberList.push(this.policeInformationList[i].policeNumber)
        }
        this.policeInformationListUpdate = allPoliceNumberList;
        this.dataHandle(allPoliceNumberList);
      });
    },
    
    dataHandle(allPoliceNumberList) {
      let policeNumberList = [];
      for (let i = 0; i < this.policesDutyGroupList.length; i++) {
        let teamMembers = this.policesDutyGroupList[i].teamMembers.split(',')
        for (let j = 0; j < teamMembers.length; j++) {
          policeNumberList.push(teamMembers[j])
        }
      }
      let policeInformations = [];
      let flage = true
      for (let i = 0; i < allPoliceNumberList.length; i++) {
        for (let j = 0; j < policeNumberList.length; j++) {
          flage = true
          if (allPoliceNumberList[i] == policeNumberList[j]) {
            flage = false
            break
          }
        }
        if (flage) {
          for (let k = 0; k < this.policeInformationList.length; k++) {
            if (this.policeInformationList[k].policeNumber == allPoliceNumberList[i]) {
              policeInformations.push(this.policeInformationList[k])
            }
          }
        }
      }
      this.policeInformationList = policeInformations
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
        groupName: null,
        teamMembers: null,
        responsibleArea: null,
        dutyType: null,
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
      this.dataHandle(this.policeInformationListUpdate)
      this.open = true;
      this.title = "添加警员分组详情";
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      const id = row.id || this.ids
      this.policeInformationList = this.policeInformationUpdate
      getPolices(id).then(response => {
        this.form = response.data;
        let policeNumberList = [];
        let teamMembersList = this.form.teamMembers.split(',')
        for (let i = 0; i < teamMembersList.length; i++) {
          policeNumberList.push(teamMembersList[i])
        }
        policeNumberList = policeNumberList.filter((item) => {
          return item !== ''
        })
        this.form.teamMembers = policeNumberList;
        this.disabled = true;
        this.open = true;
        this.title = "查看警员分组详情";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      this.policeInformationList = this.policeInformationUpdate
      getPolices(id).then(response => {
        this.form = response.data;
        let policeNumberList = [];
        let teamMembersList = this.form.teamMembers.split(',')
        for (let i = 0; i < teamMembersList.length; i++) {
          policeNumberList.push(teamMembersList[i])
        }
        policeNumberList = policeNumberList.filter((item) => {
          return item !== ''
        })
        this.form.teamMembers = policeNumberList;
        this.disabled = false;
        this.open = true;
        this.title = "修改警员分组详情";
      });
    },
    arrayToString() {
      let noticeTargetbm = "";
      for (let i = 0; i < this.form.teamMembers.length; i++) {
        noticeTargetbm = noticeTargetbm + this.form.teamMembers[i] + ","
      }
      noticeTargetbm.substring(noticeTargetbm.length-1);
      this.form.teamMembers = noticeTargetbm;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.arrayToString();
          if (this.form.id != null) {
            updatePolices(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPolices(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除警员组名为"' + row.groupName + '"的数据项？').then(function() {
        return delPolices(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('polices/polices/export', {
        ...this.queryParams
      }, `polices_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
