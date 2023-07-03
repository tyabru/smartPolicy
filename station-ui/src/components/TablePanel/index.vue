<template>
  <div class="container">
    <el-row class="panel">
      <search-form-bar :class="{ 'search-bar': true, 'hidden': !showSearch }">
        <slot name="search-form"></slot>
        <template #btn>
          <slot name="search-form-btn"></slot>
        </template>
      </search-form-bar>
      <div class="content">
        <el-row class="table-btn">
          <slot name="btn"></slot>
        </el-row>
        <slot></slot>
        <div class="sticky-bottom-div">
          <slot name="page"></slot>
        </div>
      </div>
    </el-row>
  </div>
</template>
<script>
  export default {
    props: {
      showSearch: {
        default() {
          return true
        }
      }
    }
  }
</script>
<style scoped lang="scss">
.container {
  overflow: auto;
  padding: 10px 10px 10px 10px;
  background-color: var(--page-background-color);
  min-height: var(--page-content-min-height);

  .panel {
    padding: 10px;
    border-radius: 3px;
    background-color: var(--page-panel-background-color);

    & ::v-deep .el-table {
      min-height: 400px;
    }
  }

  .table-btn {
    margin-bottom: 10px;
  }
  .sticky-bottom-div {
    background-color: #1ab394;

    position: sticky;
    bottom: 0;
    left: 0;
    width: 100%;
  }
  .content {
    transition:all 1s;
    -webkit-transition:all 1s;
  }

  .search-bar {
    opacity: 1;
    height: 100%;
    transition:all 0.5s;
    -webkit-transition:all 0.5s; /* Safari */

    &.hidden {
      height: 0;
      opacity: 0;
    }
  }
}
</style>
