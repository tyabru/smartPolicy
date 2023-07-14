<template>
  <table-panel :show-search="showSearch" :loading="loading">
    <template #search-form>
      <el-form size="mini" ref="queryForm" :model="queryParams" label-width="100px" inline>
        <el-row>
          <el-col :span="6">
            <el-form-item label="社区" class="width-100Rate">
              <se-community-dept v-model="queryParams.community" style="width: 193px" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="小区名称">
              <se-community v-model="queryParams.id" style="width: 193px" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="小区编码">
              <el-input v-model="queryParams.code" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="防范措施">
              <el-select v-model="queryParams.preventiveMeasures" clearable>
                <el-option :value="0" label="人防" />
                <el-option :value="1" label="物防" />
                <el-option :value="2" label="技防" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="是否安全小区">
              <el-select  v-model="queryParams.isSafeArea"  class="width-100Rate">
                <el-option :value="0" label="是" />
                <el-option :value="1" label="否" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button size="mini" type="primary" @click="queryChanged">查询</el-button>
      <el-button size="mini" type="info" @click="resetQuery">重置</el-button>
    </template>
    <template #btn>
      <el-button size="mini" type="primary" @click="goToEditPage">新增</el-button>
      <el-button size="mini" type="info">导出</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="queryChanged"></right-toolbar>
    </template>
    <el-table :data="tableData" emptyText="暂无数据">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="communityObj.deptName" label="所属社区" align="center"></el-table-column>
      <el-table-column prop="name" label="小区（村）名称" align="center"></el-table-column>
      <el-table-column prop="detail.person" label="总人口" align="center"></el-table-column>
      <el-table-column prop="detail.resident" label="常驻人口" align="center"></el-table-column>
      <el-table-column prop="detail.important" label="重点关注" align="center"></el-table-column>
      <el-table-column prop="detail.policeName" label="负责民警" align="center"></el-table-column>
      <el-table-column prop="detail.policePhone" label="联系电话" align="center"></el-table-column>
      <el-table-column prop="fileCount" label="相关文件数" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot="{ row }">
          <el-button size="mini" type="text" @click="goToEditPage(row)">更新</el-button>
          <el-button size="mini" type="text" @click="deleteVillage(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      :pageSizes="pageSizes" @pagination="queryChanged" #page></pagination>
  </table-panel>
</template>

<script>
import { Encrypt } from '@/utils/Aescrypt'
import TablePanel from '@/components/TablePanel/index.vue'
import { delCommunity, listCommunity } from '@/api/community/community'
import tableListMixins from '@/mixins/tableListMixins.js'
  export default {
    name: 'community-info',
    components: {TablePanel},
    mixins: [ tableListMixins ],
    data() {
      return {
      }
    },
    created() {
      this.initTableData(listCommunity);
    },
    methods: {
      goToEditPage(row, activeIndex = 0) {
        let title = "新增小区（村）信息"
        const params = { }
        if(row && row.id) {
          title = `修改[ ${row.name} ]信息`;
          params['sq_pk'] = Encrypt(JSON.stringify({id: row.id | 'unknown'}));
        }
        this.$tab.openPage(title, '/community/village/info-edit', params);
      },
      deleteVillage(item) {
        const _that = this;
        this.confirm("此操作将永久删除数据以及绑定的相关文件, 是否继续?",
          () => {
            _that.changeLoading();
            delCommunity(item.id).then(response => {
              if(response.code === 200) {
                _that.$message.success("删除成功")
                _that.queryChanged();
              } else if(response.msg){
                _that.$message.error(response.msg)
              }
            }).finally(() => {
              _that.changeLoading(false);
            })
          })

      },

    }
  }
</script>

