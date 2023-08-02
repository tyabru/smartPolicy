<script>
import * as _ from 'lodash'
import { getCompany, listCompany } from '@/api/community/company'
export default {
  name: 'SeCompany',
  props: {
    value: Number,
    customClass: String
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
        if(newVal && this.options.length < 1) {
          getCompany(newVal).then(({ code, data }) => {
            if(code === 200 && data) {
              this.options = [ data ]
            }
          }).finally(() => { this.selectItem = newVal })
        } else {
          this.selectItem = newVal
        }
      },
      immediate: true
    }
  },
  methods: {
    querySearch: _.debounce(function(queryString) {
      if(!queryString || queryString.length < 1) {
        this.options = []
      } else {
        this.loading = true;
        listCompany({ companyName: queryString }).then(({ code, rows = [] }) => {
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
    :class="[ customClass ]"
    placeholder="请输入小区名称"
    :remote-method="querySearch"
    :loading="loading"
    @change="handleSelect">
    <el-option
      v-for="item in options"
      :key="item.id"
      :label="item.companyName"
      :value="item.id">
    </el-option>
    <i class="el-icon-edit" slot="prefix"></i>
  </el-select>
</template>

<style scoped lang="scss">

</style>
