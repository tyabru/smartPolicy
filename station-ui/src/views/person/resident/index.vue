<template>
  <table-panel :show-search="showSearch">
    <template #search-form>
      <el-form size="mini" :model="queryParams" label-width="80px" inline>
        <el-row>
          <el-col :span="6">
            <el-form-item label="所属部门">
              <el-input v-model="queryParams.dept"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="所属小区">
              <el-input v-model="queryParams.communityId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="姓名">
              <el-input v-model="queryParams.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="身份证号">
              <el-input v-model="queryParams.certNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="联系电话">
              <el-input v-model="queryParams.phone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="年龄范围">
              <el-input v-model="queryParams.age"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="重点人员">
              <el-select v-model="queryParams.attentionLevel" placeholder="请选择关注的级别 0绿 1黄 2红">
                <el-option
                  v-for="dict in dict.type.important_level"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button size="mini" type="primary" @click="queryChanged">查询</el-button>
      <el-button size="mini" type="info">重置</el-button>
    </template>
    <template #btn>
      <el-button size="mini" type="primary" @click="openEditDialog">新增</el-button>
      <el-button size="mini" type="info">导出</el-button>
    </template>
    <el-table :data="tableData" emptyText="暂无数据">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="name" label="姓名" align="center"></el-table-column>
      <el-table-column prop="certNo" label="身份证号码" align="center"></el-table-column>
      <el-table-column prop="phone" label="联系电话" align="center"></el-table-column>
      <el-table-column prop="age" label="年龄" align="center"></el-table-column>
      <el-table-column prop="birthday" label="出生日期" align="center"></el-table-column>
      <el-table-column prop="isImportant" label="重点关注" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot="{ row }">
          <el-button size="mini" type="text" @click="openEditDialog(row)">更新</el-button>
          <el-button size="mini" type="text" @click="removeItem(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      :pageSizes="pageSizes" @pagination="queryChanged" #page></pagination>
    <el-dialog :visible.sync="editVisible" custom-class="dialog-mini">
      <edit-form :form-id="selectId" :submit-finish="submitFinish"></edit-form>
    </el-dialog>

  </table-panel>
</template>

<script>
import TablePanel from '@/components/TablePanel/index.vue'
import TableListMixins from '@/mixins/tableListMixins'
import EditForm from './editForm.vue'
import { delResident, listResident } from '@/api/person/resident'
export default {
  name: 'resident-people',
  dicts: ['important_level'],
  mixins: [TableListMixins],
  components: { EditForm, TablePanel },
  data() {
    return {
      editVisible: false,
      selectId: null,
      tableData: []
    }
  },
  mounted() {
    this.initTableData(listResident);
  },
  methods: {
    openEditDialog(item = {}) {
      this.editVisible = true
      if(item.id) {
        this.selectId = item.id
      } else {
        this.selectId = null
      }
    },
    submitFinish(flag) {
      this.editVisible = false;
      this.selectId = null;
    },
    removeItem(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除社区常驻人员为"' + row.name + '"的数据项？').then(function() {
        return delResident(ids);
      }).then(() => {
        this.queryChanged();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('person/resident/export', {
        ...this.queryParams
      }, `resident_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>

<style scoped lang="scss">

</style>
