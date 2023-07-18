<template>
  <table-panel :show-search="showSearch" :loading="loading">
    <template #search-form>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="小区编码" prop="communityCode">
          <el-input
            v-model="queryParams.communityCode"
            placeholder="请输入小区编码"
            clearable
          />
        </el-form-item>
        <el-form-item label="小区名称" prop="communityName">
          <el-input
            v-model="queryParams.communityName"
            placeholder="请输入小区名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="地址编码" prop="metaAddrId">
          <el-input
            v-model="queryParams.metaAddrId"
            placeholder="请输入地址编码"
            clearable
          />
        </el-form-item>
        <el-form-item label="地址全称" prop="fullAddress">
          <el-input
            v-model="queryParams.fullAddress"
            placeholder="请输入地址全称"
            clearable
          />
        </el-form-item>
        <el-form-item label="地址级别" prop="metaLevel">
          <el-select v-model="queryParams.metaLevel" clearable placeholder="请输入地址级别">
            <el-option v-for="item in dict.type['structure_level']"
                       :key="item.value" :value="item.value" :label="item.label" />
          </el-select>
        </el-form-item>
        <el-form-item label="所属区域" prop="region">
          <el-input
            v-model="queryParams.region"
            placeholder="请输入所属区域"
            clearable
          />
        </el-form-item>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button type="primary" icon="el-icon-search" size="mini" @click="queryChanged">搜索</el-button>
      <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
    </template>
    <template #btn>
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['community:structure:add']"
        >新增</el-button>
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['community:structure:edit']"
        >修改</el-button>
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['community:structure:remove']"
        >删除</el-button>
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['community:structure:export']"
        >导出</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="queryChanged"></right-toolbar>
    </template>
    <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="小区名称" align="center" prop="communityName" />
      <el-table-column label="地址编码" align="center" prop="metaAddrId" />
      <el-table-column label="地址全称" align="center" prop="fullAddress" />
      <el-table-column label="地址级别" align="center" prop="metaLevel" />
      <el-table-column label="区域名称" align="center" prop="regionName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['community:structure:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['community:structure:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="queryChanged"
    />

    <!-- 添加或修改小区房屋结构和地址信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属小区" prop="communityId">
          <se-community v-model="form.communityId" @change="communityChange"
                        placeholder="请输入小区外键" class="width-100Rate" />
        </el-form-item>
        <el-form-item label="地址编码" prop="metaAddrId">
          <el-input v-model="form.metaAddrId" placeholder="请输入小区地址编码" />
        </el-form-item>
        <el-form-item label="地址全称" prop="fullAddress">
          <el-input v-model="form.fullAddress" placeholder="请输入地址全称" />
        </el-form-item>
        <el-form-item label="地址级别" prop="metaLevel">
          <el-select v-model="form.metaLevel" class="width-100Rate" @change="levelChange" placeholder="请输入地址级别">
            <el-option v-for="item in dict.type['structure_level']"
                       :key="item.value" :value="item.value" :label="item.label" />
          </el-select>
        </el-form-item>
        <el-form-item label="上级地址" prop="parentId">
          <el-select v-model="form.parentId"
                     :disabled="!form.communityId || !form.metaLevel || form.metaLevel == 10"
                     placeholder="请输入上级编码" class="width-100Rate" >
            <el-option v-for="item in parentList"
                       :key="item.id" :value="item.id" :label="item.fullAddress" />
          </el-select>
        </el-form-item>
        <el-form-item label="所属部门" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入所属部门" />
        </el-form-item>
        <el-form-item label="地址简称" prop="shortName">
          <el-input v-model="form.shortName" placeholder="请输入地址简称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </table-panel>
</template>

<script>
import { listStructure, getStructure, delStructure, addStructure, updateStructure } from "@/api/community/structure";
import TablePanel from '@/components/TablePanel/index.vue'
import tableListMixins from '@/mixins/tableListMixins.js'
export default {
  name: "Structure",
  dicts: ['structure_level'],
  components: { TablePanel },
  mixins: [ tableListMixins ],
  data() {
    return {
      // 选中数组
      ids: [],
      // 非多个禁用
      multiple: true,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      parentList: [],
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "小区外键不能为空", trigger: "blur" }
        ],
        metaAddrId: [
          { required: true, message: "地址编码不能为空", trigger: "blur" }
        ],
        fullAddress: [
          { required: true, message: "地址全称不能为空", trigger: "blur" }
        ],
        metaLevel: [
          { required: true, message: "地址级别不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.initTableData(listStructure);
  },
  methods: {
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryChanged();
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
      this.title = "添加小区房屋结构和地址信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStructure(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改小区房屋结构和地址信息";
      });
    },
    communityChange(key, item) {
      this.form.communityCode = item.code
      this.form.communityName = item.name
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStructure(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.queryChanged();
            });
          } else {
            addStructure(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.queryChanged();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除小区房屋结构和地址信息编号为"' + ids + '"的数据项？').then(function() {
        return delStructure(ids);
      }).then(() => {
        this.queryChanged();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('community/structure/export', {
        ...this.queryParams
      }, `structure_${new Date().getTime()}.xlsx`)
    },
    levelChange(item) {
      if(this.form.communityId && item > 10 && item < 15) {
        listStructure({ communityId: this.form.communityId, metaLevel: item - 1}).then(response => {
          if(response.code === 200 && response.data) {
            this.parentList = response.data
          }
        })
      }
    }
  }
};
</script>
