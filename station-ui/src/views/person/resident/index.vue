<template>
  <div class="container">
    <el-row class="panel">
      <search-form-bar>
        <el-form size="mini" model="form" label-width="80px" inline>
          <el-row>
            <el-col :span="6">
              <el-form-item label="所属部门">
                <el-input v-model="form.input"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="所属小区">
                <el-input v-model="form.input"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="姓名">
                <el-input v-model="form.input"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="身份证号">
                <el-input v-model="form.input"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="联系电话">
                <el-input v-model="form.input"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="年龄范围">
                <el-input v-model="form.input"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="重点人员">
                <el-input v-model="form.input"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="姓名">
                <el-input v-model="form.input" ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <template #btn>
          <el-button size="mini" type="primary">查询</el-button>
          <el-button size="mini" type="info">重置</el-button>
        </template>
      </search-form-bar>
      <el-row class="table-btn">
        <el-button size="mini" type="primary">新增</el-button>
        <el-button size="mini" type="info">导出</el-button>
      </el-row>
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
      <div class="sticky-bottom-div">
        <el-pagination></el-pagination>
      </div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'resident-people',
  data() {
    return {
      form: {},
      tableData: []
    }
  },
  mounted() {
    this.queryTableData();
  },
  methods: {
    queryTableData() {
      for (let key = 0; key < 20; key++) {
        this.tableData.push({
          id: key,
          name: '张三',
          certNo: '372929199610100614',
          phone: '15634091838',
          age: 27,
          birthday: '1996-10-10',
          isImportant: key % 2
        })
      }
    }
  }
}
</script>

<style scoped lang="scss">

</style>
