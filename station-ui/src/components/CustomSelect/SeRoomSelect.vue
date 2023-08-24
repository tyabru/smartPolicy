<script>
import { selectDistinctStructure } from '@/api/community/structure'

/** 只能选择房间的下拉选择框 */
export default {
  name: 'SeRoomSelect',
  props: {
    communityCode: {
      required: true
    },
    disabled: {
      type: Boolean,
      default: false
    },
    defaultValue: {
      type: String
    },
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
    communityCode: {
      handler(newVal) {
        if(newVal) {
          this.loading = true
          selectDistinctStructure(newVal).then(({ code, data }) => {
            if(code === 200 && data) {
              this.options = data
            }
          }).finally(() => {
            this.loading = false
          })
        }
      },
      immediate: true
    },
    // defaultValue: {
    //   handler(newVal) {
    //     if(newVal && this) {
    //       this.loading = true
    //       selectDistinctStructure(newVal).then(({ code, data }) => {
    //         if(code === 200 && data) {
    //           this.options = data
    //         }
    //       }).finally(() => {
    //         this.loading = false
    //       })
    //     }
    //   },
    //   immediate: true
    // }
  },
  methods: {
    handleSelect(item) {
      let raw = {}
      if(item) {
        let temp = this.options;
        for (let i = 0; i < item.length; i++) {
          const filter = temp.filter(aa => aa.value === item[i])
          temp = filter[0].node? filter: filter[0].children
        }
        raw = temp && temp.length > 0? temp[0].raw: {}
      }
      this.$emit('change', item[item.length - 1], raw);
    },
    resetFields() {
      this.selectItem = []
    }
  }
}
</script>

<template>
  <el-cascader v-model="selectItem" :loading="loading"
               :options="options"
               :disabled="disabled"
               @change="handleSelect"
               filterable></el-cascader>
</template>

<style scoped lang="scss">

</style>
