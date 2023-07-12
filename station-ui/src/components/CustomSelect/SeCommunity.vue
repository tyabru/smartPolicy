<script>
import { listCommunity } from '@/api/community/community'
import * as _ from 'lodash'
export default {
  name: 'SeCommunity',
  props: {
    value: [String, Number],
    customClass: String,
  },
  data() {
    return {
      loading: false,
      options: [],
      selectItem: null,
      selection: {}
    }
  },
  methods: {
    querySearch: _.debounce(function(queryString) {
      if(!queryString || queryString.length < 1) {
        this.options = []
      } else {
        this.loading = true;
        listCommunity({ name: queryString }).then(({ code, rows = [] }) => {
          if(code === 200) {
            this.options = rows
          }
        }).finally(() => {
          this.loading = false
        })
      }
    }, 700),
    handleSelect(item) {
      const filter = this.options.filter(i => i.id === item)
      this.$emit('input', item);
      this.$emit('change', item, filter && filter.length > 0? filter[0]: null);
    }
  }

}
</script>

<template>
  <el-select
    v-model="selectItem"
    filterable
    remote
    clearable
    :class="['width-100Rate', customClass]"
    placeholder="请输入小区名称"
    :remote-method="querySearch"
    :loading="loading"
    @change="handleSelect">
    <el-option
      v-for="item in options"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
    <i class="el-icon-edit" slot="prefix"></i>
  </el-select>
</template>

<style scoped lang="scss">

</style>
