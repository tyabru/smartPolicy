<template>
  <table-panel :show-search="showSearch" :loading="loading">
    <template #search-form>
      <el-form :model="queryParams" ref="queryForm" inline label-width="100px">
        <el-row>
          <el-col  :span="6" :xs="24">
            <el-form-item label="单位编码" prop="companyCode" class="width-100Rate">
              <el-input v-model="queryParams.companyCode" placeholder="请输入单位编码"
                        clearable />
            </el-form-item>
          </el-col>
          <el-col  :span="6" :xs="24">
            <el-form-item label="单位名称" prop="companyName" class="width-100Rate">
              <el-input v-model="queryParams.companyName" placeholder="请输入单位名称"
                        clearable />
            </el-form-item>
          </el-col>
          <el-col  :span="6" :xs="24">
            <el-form-item  label="联系电话" prop="phone" class="width-100Rate">
              <el-input v-model="queryParams.phone" placeholder="请输入联系电话"
                        clearable />
            </el-form-item>
          </el-col>
          <el-col  :span="6" :xs="24">
            <el-form-item  label="营业执照号" prop="tradeCode" class="width-100Rate">
              <el-input v-model="queryParams.tradeCode" placeholder="请输入营业执照号"
                        clearable />
            </el-form-item>
          </el-col>
          <el-col  :span="6" :xs="24">
            <el-form-item label="组织机构代码" prop="organizationCode" class="width-100Rate">
              <el-input
                v-model="queryParams.organizationCode"
                placeholder="请输入组织机构代码"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col  :span="6" :xs="24">
            <el-form-item label="信用编号" prop="creditCode" class="width-100Rate">
              <el-input
                v-model="queryParams.creditCode"
                placeholder="请输入信用编号"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button type="primary" icon="el-icon-search" size="mini" @click="queryChanged">搜索</el-button>
      <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
    </template>
    <template #btn>
      <el-button plain size="mini" type="primary"
         v-hasPermi="['community:company:add']" @click="goToEditPage">新增</el-button>
      <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
         v-hasPermi="['community:company:remove']">删除</el-button>
      <el-button plain size="mini" type="info" icon="el-icon-download"
         v-hasPermi="['community:company:export']" @click="handleExport">导出</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="queryChanged"></right-toolbar>
    </template>
    <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属社区" align="center" prop="communityObj.deptName" />
      <el-table-column label="单位编码" align="center" prop="companyCode" />
      <el-table-column label="单位名称" align="center" prop="companyName" />
      <el-table-column label="单位类型" align="center" prop="type">
        <template v-slot="{ row }">
          <dict-tag  :value="row.type" :options="dict.type['place_list']" />
        </template>
      </el-table-column>
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="注册地址" align="center" prop="address" />
      <el-table-column label="负责人姓名" align="center" prop="headMaster" />
      <el-table-column label="相关文件" align="center" prop="fileCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text"
            icon="el-icon-edit"
            @click="goToEditPage(scope.row)"
            v-hasPermi="['community:company:edit']"
          >修改</el-button>
          <el-button size="mini" type="text"
            icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['community:company:remove']" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      :pageSizes="pageSizes" @pagination="queryChanged" #page></pagination>
  </table-panel>
</template>

<script>
import { listCompany, delCompany } from "@/api/community/company";
import TablePanel from '@/components/TablePanel/index.vue'
import tableListMixins from '@/mixins/tableListMixins.js'
import { Encrypt } from '@/utils/Aescrypt'
export default {
  name: "Company",
  dicts: ['place_list'],
  components: { TablePanel },
  mixins: [ tableListMixins ],
  data() {
    return {
      ids: [],
      // 非多个禁用
      multiple: true
    };
  },
  created() {
    this.initTableData(listCompany);
  },
  methods: {
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    goToEditPage(row, activeIndex = 0) {
      let title = "新增单位信息"
      const params = { }
      if(row && row.id) {
        title = `修改[ ${row.companyName} ]信息`;
        params['sq_pk'] = Encrypt(JSON.stringify({id: row.id | 'unknown'}));
      }
      this.$tab.openPage(title, '/community/page/company-edit', params);
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除单位基本信息编号为"' + ids + '"的数据项？').then(function() {
        return delCompany(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('company/company/export', {
        ...this.queryParams
      }, `company_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
