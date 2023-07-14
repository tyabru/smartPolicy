<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="mini" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属小区" prop="communityId">
        <el-input
          v-model="queryParams.communityId"
          placeholder="请输入所属小区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入人员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="certNo">
        <el-input
          v-model="queryParams.certNo"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="临时工" prop="isTemp">
        <el-input
          v-model="queryParams.isTemp"
          placeholder="请输入是否是临时工"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否离职" prop="isLeaving">
        <el-input
          v-model="queryParams.isLeaving"
          placeholder="请输入是否离职"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['wuye:wuye:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wuye:wuye:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wuye:wuye:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['wuye:wuye:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wuyeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属小区" align="center" prop="communityId" />
      <el-table-column label="工作类型" align="center" prop="workType" />
      <el-table-column label="人员姓名" align="center" prop="name" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="是否是临时工" align="center" prop="isTemp" />
      <el-table-column label="籍贯" align="center" prop="nativePlace" />
      <el-table-column label="是否离职" align="center" prop="isLeaving" />
      <el-table-column label="开始工作时间" align="center" prop="startTime" width="180" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['wuye:wuye:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['wuye:wuye:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改物业信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="所属小区" prop="communityId">
          <se-community v-model="form.communityId" style="width: 100%" />
        </el-form-item>
        <el-form-item label="工作单位" prop="company">
          <el-input v-model="form.company" placeholder="请输入工作单位" />
        </el-form-item>
        <el-form-item label="人员姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入人员姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="certNo">
          <el-input v-model="form.certNo" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="现居地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入现居地址" />
        </el-form-item>
        <el-form-item label="籍贯" prop="nativePlace">
          <el-input v-model="form.nativePlace" placeholder="请输入籍贯" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable class="width-100Rate"
                          v-model="form.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开始工作时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否是临时工" prop="isTemp">
          <el-input v-model="form.isTemp" placeholder="请输入是否是临时工" />
        </el-form-item>
        <el-form-item label="是否离职" prop="isLeaving">
          <el-input v-model="form.isLeaving" placeholder="请输入是否离职" />
        </el-form-item>
        <el-form-item label="离职时间" prop="endTime">
          <el-date-picker clearable class="width-100Rate"
                          v-model="form.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择工作结束时间/离职时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listWuye, getWuye, delWuye, addWuye, updateWuye } from "@/api/community/wuye";

export default {
  name: "Wuye",
  data() {
    return {
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
      // 物业信息管理表格数据
      wuyeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        communityId: null,
        company: null,
        workType: null,
        name: null,
        certNo: null,
        phone: null,
        address: null,
        nativePlace: null,
        startTime: null,
        isTemp: null,
        endTime: null,
        isLeaving: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "所属小区不能为空", trigger: "blur" }
        ],
        company: [
          { required: true, message: "工作单位不能为空", trigger: "blur" }
        ],
        workType: [
          { required: true, message: "工作类型不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "人员姓名不能为空", trigger: "blur" }
        ],
        certNo: [
          { required: true, message: "身份证号不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "现居地址不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "开始工作时间不能为空", trigger: "blur" }
        ],
        isLeaving: [
          { required: true, message: "是否离职不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询物业信息管理列表 */
    getList() {
      this.loading = true;
      listWuye(this.queryParams).then(response => {
        this.wuyeList = response.rows;
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
        communityId: null,
        company: null,
        workType: null,
        name: null,
        certNo: null,
        phone: null,
        address: null,
        nativePlace: null,
        startTime: null,
        isTemp: null,
        endTime: null,
        isLeaving: null,
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
      this.title = "添加物业信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWuye(id).then(response => {
        this.form = response.data;
        this.open = true;
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
      this.$modal.confirm('是否确认删除物业信息管理编号为"' + ids + '"的数据项？').then(function() {
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
