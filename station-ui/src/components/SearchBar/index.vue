

<template>
  <el-row class="search-form-bar" type="flex" justify="start" style="">
    <el-col style="flex: 1;">
      <slot></slot>
    </el-col>
    <el-divider direction="vertical"></el-divider>
    <el-col class="btn-column">
      <slot name="btn"></slot>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: 'searchFormBar'
  }
</script>

<style scoped lang="scss">
.search-form-bar {
  padding: 0.625rem;
  align-items: stretch;
  -webkit-align-items: stretch;

  .el-divider.el-divider--vertical {
    height: auto!important;
  }
}
.btn-column {
  width: 86px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  & ::v-deep .el-button + .el-button {
    margin: 10px 0 0 0;
  }
}
</style>
