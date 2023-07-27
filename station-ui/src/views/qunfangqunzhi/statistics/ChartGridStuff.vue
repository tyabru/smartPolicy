<template>
  <div ref="chart" :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts';
import {countGridStuff} from "@/api/qunfangqunzhi/statistics";
require('echarts/theme/macarons') // echarts theme


const animationDuration = 500

export default {
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
      chart: null,
      params:{},
      option:{
        title: {
          text: '网格员完成任务情况'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {},
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          name: '次',
          interval: 1,
          nameTextStyle: {
            align: 'center'
          },
        },
        yAxis: {
          name:'网格员用户名',
          type: 'category',
          data: [],

        },
        series: [
          {
            color:'0000FF',
            name: '完成任务次数',
            type: 'bar',
            data: []
          },
        ]
      }
    }
  },
  mounted() {
    this.initChart();
    this.getData();

  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    getData(params){
      countGridStuff(params || this.params).then(res=>{
        console.log(params+"666")
        let times = [];
        let labels = [];
        for(let a of res.data){
          times.push(a.times);
          labels.push(a.userName)
        }
        this.option.series[0].data = times;
        this.option.yAxis.data = labels;
        this.resetChart();
      })
    },
    initChart() {
      this.chart = echarts.init(this.$refs.chart, 'macarons');
      this.chart.setOption(this.option);
    },
    resetChart(){
      this.chart.setOption(this.option);
      this.chart.resize();
    }
  }
}
</script>
