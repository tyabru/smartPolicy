<template>
  <el-card>
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="12">
        <el-card class="chart-wrapper" shadow="never">
          <template #header>
            <card-header icon="el-icon-edit-outline" header-text="优秀网格员"></card-header>
          </template>
          <div class="date-checker">
            <div style="margin-right: 15px">
              <el-radio-group v-model="checkData1" size="mini" @change="handleGridStuffSelect(checkData1)">
                <el-radio-button v-for="(t,index) in dateCheckGroup" :label="index" :key="t">{{ t }}</el-radio-button>
              </el-radio-group>
            </div>
            <div>
              <el-date-picker v-model="dateCheck1" size="mini"
                              @change="handleGridStuffSelect()"
                              type="daterange"
                              value-format="yyyy-MM-dd"
                              range-separator="至"
                              start-placeholder="开始日期"
                              end-placeholder="结束日期">
              </el-date-picker>
            </div>
          </div>
          <chart-grid-stuff ref="chartGridStuff"/>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="12">
        <el-card class="chart-wrapper" shadow="never">
          <template #header>
            <card-header icon="el-icon-edit-outline" header-text="优秀网格员长"></card-header>
          </template>
          <div class="date-checker">
            <div style="margin-right: 15px">
              <el-radio-group v-model="checkData2" size="mini" @change="handleGridStuffLeaderSelect(checkData2)">
                <el-radio-button v-for="(t,index) in dateCheckGroup" :label="index" :key="t">{{ t }}</el-radio-button>
              </el-radio-group>
            </div>
            <div>
              <el-date-picker v-model="dateCheck2" size="mini"
                              @change="handleGridStuffLeaderSelect()"
                              type="daterange"
                              value-format="yyyy-MM-dd"
                              range-separator="至"
                              start-placeholder="开始日期"
                              end-placeholder="结束日期">
              </el-date-picker>
            </div>
          </div>
          <chart-grid-stuff-leader ref="chartGridStuffLeader"/>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="12">
        <el-card class="chart-wrapper" shadow="never">
          <template #header>
            <card-header icon="el-icon-edit-outline" header-text="优秀社区"></card-header>
          </template>
          <div class="date-checker">
            <div style="margin-right: 15px">
              <el-radio-group v-model="checkData3" size="mini" @change="handleCommunitySelect(checkData3)">
                <el-radio-button v-for="(t,index) in dateCheckGroup" :label="index" :key="t">{{ t }}</el-radio-button>
              </el-radio-group>
            </div>
            <div>
              <el-date-picker v-model="dateCheck3" size="mini"
                              @change="handleCommunitySelect()"
                              type="daterange"
                              value-format="yyyy-MM-dd"
                              range-separator="至"
                              start-placeholder="开始日期"
                              end-placeholder="结束日期">
              </el-date-picker>
            </div>
          </div>
          <chart-community ref="chartCommunity"/>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="12">
        <el-card class="chart-wrapper" shadow="never">
          <template #header>
            <card-header icon="el-icon-edit-outline" header-text="优秀上报群众"></card-header>
          </template>
          <div class="date-checker">
            <div style="margin-right: 15px">
              <el-radio-group v-model="checkData4" size="mini" @change="handlePeopleSelect(checkData4)">
                <el-radio-button v-for="(t,index) in dateCheckGroup" :label="index" :key="t">{{ t }}</el-radio-button>
              </el-radio-group>
            </div>
            <div>
              <el-date-picker v-model="dateCheck4" size="mini"
                              @change="handlePeopleSelect()"
                              type="daterange"
                              value-format="yyyy-MM-dd"
                              range-separator="至"
                              start-placeholder="开始日期"
                              end-placeholder="结束日期">
              </el-date-picker>
            </div>
          </div>
          <chart-people ref="chartPeople"/>
        </el-card>
      </el-col>
    </el-row>
  </el-card>


</template>

<script>
  import ChartGridStuff from "@/views/qunfangqunzhi/statistics/ChartGridStuff";
  import ChartGridStuffLeader from "@/views/qunfangqunzhi/statistics/ChartGridStuffLeader";
  import ChartCommunity from "@/views/qunfangqunzhi/statistics/ChartCommunity";
  import ChartPeople from "@/views/qunfangqunzhi/statistics/ChartPeople";
  import Register from "@/views/register";

  export default {
    name: "",
    components: {ChartPeople, ChartCommunity, Register, ChartGridStuff, ChartGridStuffLeader},
    data() {
      return {
        params: {},
        dateCheck1: null,
        checkData1: null,
        dateCheck2: null,
        checkData2: null,
        dateCheck3: null,
        checkData3: null,
        dateCheck4: null,
        checkData4: null,
        dateCheckGroup: ['周', '月', '季', '年'],
      }
    },
    methods: {
      handleGridStuffSelect(val) {
        if (val != null) {
          this.dateCheck1 = null;
          let params = {
            tag: val
          }
          let req = {}
          req.params = params;
          this.$refs.chartGridStuff.getData(req);
        } else {
          this.checkData1 = null;
          let params = {
            tag: 4,
            beginTime: this.dateCheck1[0],
            endTime: this.dateCheck1[1]
          }
          let req = {}
          req.params = params;
          this.$refs.chartGridStuff.getData(req);
        }
      },
      handleGridStuffLeaderSelect(val) {
        if (val != null) {
          this.dateCheck2 = null;
          let params = {
            tag: val
          }
          let req = {}
          req.params = params;
          this.$refs.chartGridStuffLeader.getData(req);
        } else {
          this.checkData2 = null;
          let params = {
            tag: 4,
            beginTime: this.dateCheck2[0],
            endTime: this.dateCheck2[1]
          }
          let req = {}
          req.params = params;
          this.$refs.chartGridStuffLeader.getData(req);
        }
      },
      handleCommunitySelect(val) {
        if (val != null) {
          this.dateCheck3 = null;
          let params = {
            tag: val
          }
          let req = {}
          req.params = params;
          this.$refs.chartCommunity.getData(req);
        } else {
          this.checkData3 = null;
          let params = {
            tag: 4,
            beginTime: this.dateCheck3[0],
            endTime: this.dateCheck3[1]
          }
          let req = {}
          req.params = params;
          this.$refs.chartCommunity.getData(req);
        }
      },
      handlePeopleSelect(val) {
        if (val != null) {
          this.dateCheck4 = null;
          let params = {
            tag: val
          }
          let req = {}
          req.params = params;
          console.log(req.params)
          this.$refs.chartPeople.getData(req);
        } else {
          this.checkData4= null;
          let params = {
            tag: 4,
            beginTime: this.dateCheck4[0],
            endTime: this.dateCheck4[1]
          }
          let req = {}
          req.params = params;
          this.$refs.chartPeople.getData(req);
        }
      }
    }

  }
</script>

<style scoped>
  .date-checker {
    display: flex;
    justify-content: flex-start;
    align-items: stretch;
    margin-bottom: 10px;
  }
</style>
