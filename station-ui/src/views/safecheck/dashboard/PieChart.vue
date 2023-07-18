<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts';
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          // data: ['加油站', '液化气站', '培训机构', '小区', '商场超市','宾馆','寄递物流','九小场所','企事业单位','娱乐场所','校园']
           data: ['周一', '周二', '周三', '周四', '周五', '周六', '周天'],
        },
        series: [
          {
            name: 'WEEKLY WRITE ARTICLES',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: [
              { value: 320, name: '周一' },
              { value: 240, name: '周二' },
              { value: 149, name: '周三' },
              { value: 100, name: '周四' },
              { value: 59, name: '周五' },
              { value: 320, name: '周六' },
              { value: 240, name: '周天' },
              // { value: 320, name: '加油站' },
              // { value: 240, name: '液化气站' },
              // { value: 149, name: '培训机构' },
              // { value: 100, name: '小区' },
              // { value: 59, name: '商场超市' },
              // { value: 320, name: '宾馆' },
              // { value: 240, name: '寄递物流' },
              // { value: 149, name: '九小场所' },
              // { value: 100, name: '企事业单位' },
              // { value: 59, name: '娱乐场所' },
              // { value: 59, name: '校园' }
            ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
