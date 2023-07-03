<template>
  <table-panel :show-search="showSearch">
    <template #search-form>
      <el-form size="mini" :model="queryParams" label-width="100px" inline>
        <el-row>
          <el-col :span="6">
            <el-form-item label="派出所名称">
              <el-input v-model="queryParams.input"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="小区名称">
              <el-input v-model="queryParams.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="小区编码">
              <el-input v-model="queryParams.code"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="防范措施">
              <el-select v-model="queryParams.preventiveMeasures">
                <el-option :value="0" label="人防" />
                <el-option :value="1" label="物防" />
                <el-option :value="2" label="技防" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="是否安全小区">
              <el-input v-model="queryParams.isSafeArea"></el-input>
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
      <el-button size="mini" type="primary">新增</el-button>
      <el-button size="mini" type="info">导出</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="queryChanged"></right-toolbar>
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
        <el-button size="mini" type="text">更新</el-button>
        <el-button size="mini" type="text">删除</el-button>
      </el-table-column>
    </el-table>
    <pagination
      :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      :pageSizes="pageSizes" @pagination="queryChanged" #page></pagination>
  </table-panel>
</template>

<script>
import TablePanel from '@/components/TablePanel/index.vue'
import { listUser } from '@/api/system/user'
import tableListMixins from '@/mixins/tableListMixins.js'
  export default {
    name: 'community-info',
    components: {TablePanel},
    mixins: [ tableListMixins ],
    data() {
      return {
        showSearch: true
      }
    },
    mounted() {
      this.initTableData(listUser);
    }
  }
</script>

