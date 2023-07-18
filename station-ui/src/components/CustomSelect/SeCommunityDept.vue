<script>
import { listCommunity } from '@/api/community/community'
import * as _ from 'lodash'
import { listCommunityDept, listDept } from '@/api/system/dept'
export default {
  name: 'SeCommunityDept',
  props: {
    value: [String, Number],
    customClass: String,
    defaultLabel: String
  },
  data() {
    return {
      loading: false,
      options: [],
      selectItem: null,
      selection: {}
    }
  },
  watch: {
    value: {
      handler(newVal) {
        this.selectItem = newVal
      },
      immediate: true
    }
  },
  mounted() {
    if(this.defaultLabel) {
      this.querySearch(this.defaultLabel)
    }
  },
  methods: {
    querySearch: _.debounce(function(queryString) {
      if(!queryString || queryString.length < 1) {
        this.options = []
      } else {
        this.loading = true;
        listCommunityDept({ deptName: queryString }).then(({ code, data = [] }) => {
          if(code === 200) {
            this.options = data
          }
        }).finally(() => {
          this.loading = false
        })
      }
    }, 700),
    handleSelect(item) {
      const filter = this.options.filter(i => i.deptId === item)
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
    :class="[ customClass ]"
    placeholder="请输入社区名称"
    :remote-method="querySearch"
    :loading="loading"
    @change="handleSelect">
    <el-option
      v-for="item in options"
      :key="item.deptId"
      :label="item.deptName"
      :value="item.deptId">
    </el-option>
    <i class="el-icon-edit" slot="prefix" style="padding-left: 5px"></i>
  </el-select>
</template>

<style scoped lang="scss">

</style>
