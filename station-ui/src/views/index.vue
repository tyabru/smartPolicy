<template>
  <div class="dashboard-editor-container">
    <div class="date-checker">
      <div style="margin-right: 15px">
        <el-radio-group v-model="cyCheckData" size="mini">
          <el-radio-button v-for="t in dateCheckGroup" :label="t" :key="t"></el-radio-button>
        </el-radio-group>
      </div>
      <div>
        <el-date-picker v-model="cyDateCheck" size="mini" :key="cyDateCheckType"
              :type="cyDateCheckType" placeholder="选择日期">
        </el-date-picker>
      </div>
    </div>
    <div class="content">
      <div class="left-content">
        <el-row style="margin-bottom: 15px">
          <el-card class="dashboard-panel panel-1 " shadow="never">
            <template #header>
              <card-header header-text="张山，欢迎使用智慧派出所系统.."></card-header>
            </template>
            <div>
              <panel-group @handleSetLineChartData="handleSetLineChartData" />
              <el-row style="background:#fff; margin-top: 15px;">
                <card-header icon="el-icon-edit-outline" header-text="某未知的数据统计"></card-header>
                <line-chart :chart-data="lineChartData" />
              </el-row>
            </div>
          </el-card>
        </el-row>
        <el-row :gutter="32">
          <el-col :xs="24" :sm="24" :lg="12">
            <el-card class="chart-wrapper" shadow="never">
              <template #header>
                <card-header icon="el-icon-edit-outline" header-text="登记人口类型占比"></card-header>
              </template>
              <pie-chart />
            </el-card>
          </el-col>
          <!--        <el-col :xs="24" :sm="24" :lg="8">-->
          <!--          <el-card header="隐患类型分析" class="chart-wrapper">-->
          <!--            <raddar-chart />-->
          <!--          </el-card>-->
          <!--        </el-col>-->
          <el-col :xs="24" :sm="24" :lg="12">
            <el-card class="chart-wrapper" shadow="never">
              <template #header>
                <card-header icon="el-icon-edit-outline" header-text="矛盾纠纷立案分析"></card-header>
              </template>
              <bar-chart />
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div class="right-content">
        <el-row>
          <el-col class="panel" :span="24">
            <el-card class="dashboard-panel panel-2" shadow="never">
              <template #header>
                <card-header icon="el-icon-s-custom" header-text="我的贡献"></card-header>
              </template>
              <el-descriptions direction="vertical" :column="3" border>
                <el-descriptions-item label="出警次数">3</el-descriptions-item>
                <el-descriptions-item label="刑事案件">5</el-descriptions-item>
                <el-descriptions-item label="治安案件">15</el-descriptions-item>
                <el-descriptions-item label="调解">10</el-descriptions-item>
                <el-descriptions-item label="拘留">4</el-descriptions-item>
                <el-descriptions-item label="罚款">6</el-descriptions-item>
              </el-descriptions>
            </el-card>
          </el-col>
          <el-col class="panel" :span="24">
            <el-card class="dashboard-panel panel-2" shadow="never">
              <template #header>
                <card-header icon="el-icon-s-promotion" header-text="最新安全隐患"></card-header>
              </template>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag not-start">未完成</span>]龙腾小区消防设备损坏</div>
                <div class="time">1小时内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag not-start">未完成</span>]万达3栋楼消防通道被杂物阻挡，无法正常使用</div>
                <div class="time">12小时内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag running">处理中</span>]环城街道405号5楼时有高空抛物，无法调解。</div>
                <div class="time">1天内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag running">处理中</span>]环城街道405号5楼时有高空抛物，无法调解。</div>
                <div class="time">1天内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag running">处理中</span>]环城街道405号5楼时有高空抛物，无法调解。</div>
                <div class="time">1天内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag running">处理中</span>]环城街道405号5楼时有高空抛物，无法调解。</div>
                <div class="time">1天内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag running">处理中</span>]环城街道405号5楼时有高空抛物，无法调解。</div>
                <div class="time">1天内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag running">处理中</span>]环城街道405号5楼时有高空抛物，无法调解。</div>
                <div class="time">1天内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag running">处理中</span>]东尚小区有危险动物出没</div>
                <div class="time">2023-06-24</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag finished">已结束</span>]天都嘉苑有人违建</div>
                <div class="time">2023-06-15</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag finished">已结束</span>]有个凑数的安全隐患通知</div>
                <div class="time">2022-12-01</div>
              </div>
            </el-card>
          </el-col>
          <el-col class="panel" :span="24">
            <el-card header="" class="dashboard-panel panel-2" shadow="never">
              <template #header>
                <card-header icon="el-icon-s-comment" header-text="消息通知"></card-header>
              </template>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag tag-red">公告</span>]关于2023-06-25服务更新公告</div>
                <div class="time">1小时内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag tag-red">公告</span>]关于2023-06-25服务更新公告</div>
                <div class="time">1小时内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag tag-blue">通知</span>]新水派出所第二季度工作汇报上交通知</div>
                <div class="time">12小时内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag tag-blue">消息</span>]今日上班打卡失败的消息提醒</div>
                <div class="time">1天内</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag tag-blue">消息</span>]成功领取纠纷调解任务</div>
                <div class="time">2023-06-25</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag tag-blue">消息</span>]执行每日巡查巡逻任务打卡消息</div>
                <div class="time">2023-06-20</div>
              </div>
              <div class="info-item no-margin">
                <div class="info-title">[<span class="tag tag-blue">消息</span>]成功领取纠纷调解任务</div>
                <div class="time">2023-06-23</div>
              </div>
            </el-card>
          </el-col>
          <el-col class="panel" :span="24">
            <el-card header="" class="dashboard-panel panel-3" shadow="never">
<!--              <template #header>-->
<!--                <card-header icon="el-icon-s-comment" header-text="消息通知"></card-header>-->
<!--              </template>-->
              <raddar-chart  height="200px"/>
            </el-card>
          </el-col>
        </el-row>

      </div>
    </div>
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      cyDateCheck: null,
      cyCheckData: '周',
      cyDateCheckType: 'week',
      cyDateType: ['week', 'month', 'months', 'year'],
      dateCheckGroup: ['周', '月', '季', '年'],
      lineChartData: lineChartData.newVisitis
    }
  },
  watch: {
    cyCheckData() {
      this.cyDateCheckType = this.cyDateType[this.dateCheckGroup.indexOf(this.cyCheckData[0])]
    }
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 16px 20px;
  background-color: rgb(240, 242, 245);
  position: relative;


  .date-checker {
    display: flex;
    justify-content: flex-start;
    align-items: stretch;
    margin-bottom: 10px;
  }

  & ::v-deep .el-card__header {
    border-bottom: 0;
  }

  & ::v-deep .el-radio-button--mini .el-radio-button__inner {
    padding: 7px 15px;
    font-size: 10px;
  }

  .content {
    display: flex;
  }
  .left-content {
    flex: 1;
    overflow: hidden;
  }
  .right-content {
    width: 300px;
    margin-left: 16px;
  }

  .chart-wrapper {
    margin-bottom: 15px;
    background: #fff;
  }
  .panel {
    border-radius: 4px;
    overflow: auto;
    margin-bottom: 15px;
  }
}

.app-wrapper.mobile{
  .content {
    display: block;
    .right-content {
      width: 100%;
      margin-left: 0;
      margin-top: 16px;
    }
  }

}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: stretch;
  font-size: 0.75rem;
  line-height: 1.5;

  &.no-margin {
    margin-left: -10px;
    margin-right: -10px;
  }

  .info-title {
    flex: 1;
    overflow: hidden;
    margin-right: 10px;
    white-space: nowrap;
    text-overflow: ellipsis;
    cursor: pointer;
    &:hover {
      text-decoration: underline;
      color: #1c84c6;
    }
    & .tag {
      &.not-start {
        color: #2b2f3a;
      }
      &.running {
        color: #1ab394;
      }
      &.finished {
        color: #99a9bf;
      }
    }
  }

  .time {
    width: 80px;
    color: #99a9bf;
    text-align: right;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
