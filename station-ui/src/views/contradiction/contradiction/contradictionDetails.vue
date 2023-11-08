<template>
  <div class="edit-form">
    <el-row>
      <h4 class="title-h4">纠纷基本信息</h4>
      <el-container type="flex" justify="center" style="padding: 20px;overflow: hidden">
        <el-main class="no-padding no-scrollbar">
          <el-form ref="form" :model="form" label-width="120px">
            <el-descriptions class="margin-top" title="" :column="4" :size="size" border>
              <el-descriptions-item label="案件编码" prop="startTime">{{form.caseCode}}</el-descriptions-item>
              <el-descriptions-item label="调节警员警号" prop="policeNum">{{form.policeNum}}</el-descriptions-item>
              <el-descriptions-item label="纠纷级别" prop="disputeLevel">
                <span v-for="dict in dict.type.important_level" :key="dict.value" :label="dict.label" :value="dict.value">
                  <span v-if="form.disputeLevel == dict.value">{{dict.label}}</span>
                </span>
              </el-descriptions-item>
              <el-descriptions-item label="纠纷类型" prop="disputeType">
                <span v-for="dict in dict.type.dispute_type" :key="dict.value" :label="dict.label" :value="dict.value">
                  <span v-if="form.disputeType == dict.value">{{dict.label}}</span>
                </span>
              </el-descriptions-item>
              <el-descriptions-item label="参与人数(人)" prop="partakeNum">{{form.partakeNum}}</el-descriptions-item>
              <el-descriptions-item label="调解进度"  prop="mediationProgress">
                <span v-for="dict in dict.type.mediation_progress" :key="dict.value" :label="dict.label" :value="dict.value">
                  <span v-if="form.mediationProgress == dict.value">{{dict.label}}</span>
                </span>
              </el-descriptions-item>
              <el-descriptions-item label="发生地点"  prop="place">{{form.place}}</el-descriptions-item>
              <el-descriptions-item label="报警方式" prop="alarmMode">
                <span v-for="dict in dict.type.alarm_mode" :key="dict.value" :label="dict.label" :value="dict.value">
                  <span v-if="form.alarmMode == dict.value">{{dict.label}}</span>
                </span>
              </el-descriptions-item>
              <el-descriptions-item label="发生时间" prop="startTime">{{form.startTime}}</el-descriptions-item>
              <el-descriptions-item label="结束时间" prop="endTime">{{form.endTime}}</el-descriptions-item>
              <el-descriptions-item label="经度" prop="longitude">{{form.longitude}}</el-descriptions-item>
              <el-descriptions-item label="纬度" prop="latitude">{{form.latitude}}</el-descriptions-item>
              <el-descriptions-item label="是否转办"  prop="isTransfer">
                <span v-for="dict in dict.type.sys_yes_no" :key="dict.value" :label="dict.label" :value="dict.value">
                  <span v-if="form.isTransfer == dict.value">{{dict.label}}</span>
                </span>
              </el-descriptions-item>
              <el-descriptions-item label="转办人" prop="transferPoliceNum">{{form.transferPoliceNum}}</el-descriptions-item>
              <el-descriptions-item label="转办时间" prop="transferTime">{{form.transferTime}}</el-descriptions-item>
              <el-descriptions-item label="是否调解成功" prop="isMediateSuccessful">
                <span v-for="dict in dict.type.sys_yes_no" :key="dict.value" :label="dict.label" :value="dict.value">
                  <span v-if="form.isMediateSuccessful == dict.value">{{dict.label}}</span>
                </span>
              </el-descriptions-item>
              <el-descriptions-item label="标题内容" prop="title">{{form.title}}</el-descriptions-item>
            </el-descriptions>
          </el-form>
        </el-main>
      </el-container>
    </el-row>
    <el-row class="">
      <div class="split-container" style="min-height: 520px">
        <div class="form-panel-item basic-info left-aside">
          <h4 class="title-h4">纠纷文件</h4>
          <el-table :data="fileManagementsList">
            <el-table-column label="文件名称" align="center" prop="fileName" />
            <el-table-column label="文件类型" align="center" prop="fileType">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.file_type" :value="scope.row.fileType"/>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="form-panel-item basic-info left-aside">
          <h4 class="title-h4">纠纷人员</h4>
          <el-table :data="personnelList">
            <el-table-column label="人员姓名" align="center" prop="peopleName" />
            <el-table-column label="联系电话" align="center" prop="phone" />
            <el-table-column label="身份证号码" align="center" prop="cerNo" />
            <el-table-column label="居住地址" align="center" prop="residentialAddress" />
            <el-table-column label="参与原因" align="center" prop="partakePerson" />
            <el-table-column label="人员照片" align="center" prop="imageUrl">
              <template slot-scope="scope">
                <image-preview :src="scope.row.imageUrl" :width="60" :height="80"/>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="form-panel-item basic-info main-content">
          <h4 class="title-h4">调节记录</h4>
          <el-table :data="mediationRecordsList">
            <el-table-column label="纠纷人员" align="center" prop="participantsInDisputesName" />
            <el-table-column label="调解警员" align="center" prop="participatingPolice" />
            <el-table-column label="调解方式" align="center" prop="mediationMode">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.mediation_mode" :value="scope.row.mediationMode"/>
              </template>
            </el-table-column>
            <el-table-column label="调解时间" align="center" prop="mediateTime"/>
            <el-table-column label="调解地点" align="center" prop="mediationLocation" />
            <el-table-column label="调解结果" align="center" prop="mediationResults" />
          </el-table>
        </div>
      </div>
    </el-row>
  </div>
</template>
<script>
import { getContradiction } from "@/api/contradiction/contradiction";
import { listInformation } from "@/api/polices/policeInformation";
import { listFileManagements } from "@/api/polices/fileManagements";
import { listPersonnel } from "@/api/contradiction/personnel";
import { listMediationRecords } from "@/api/contradiction/MediationRecords";
import { Decrypt } from '@/utils/Aescrypt'
import DictTag from '@/components/DictTag';
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import _ from 'lodash'
 
export default {
  name: 'contradictionDetails',
  dicts: ['sys_yes_no', 'dispute_type', 'alarm_mode', 'mediation_progress', 'important_level', 'mediation_mode', 'file_type'],
  components: { DictTag, Treeselect },
  data() {
    return {
      size: 'medium',
      form: {},
      //警员信息集合
      policeInformationList: [],
      //纠纷参与人员集合
      personnelList: [],
      //纠纷调解记录集合
      mediationRecordsList: [],
      //纠纷相关文件集合
      fileManagementsList: [],
    }
  },
  created() {
    this.loadFormData()
  },
  methods: {
    loadFormData() {
      const query = this.$route.query
      if(query && query.sq_pk) {
        try {
          const params = JSON.parse(Decrypt(query.sq_pk));
          this.form.id = params.id
        } catch (e) {
          throw e;
        }
        this.contradictionChange(this.form.id);
        this.getPoliceList();
      }
    },
    //查询纠纷信息
    contradictionChange(value) {
      getContradiction(value).then(response => {
        this.form = response.data;
        this.getListPersonnel();
        this.getListMediationRecords();
        this.getListFileManagements();
      });
    },
    //查询警员信息
    getPoliceList() {
      const form = {
        deptId : null
      }
      listInformation(form).then(response => {
        this.policeInformationList = response.rows;
      })
    },
    //查询纠纷参与人员
    getListPersonnel() {
      const form = {
        caseCode : this.form.caseCode
      }
      listPersonnel(form).then( response => {
        this.personnelList = response.rows;
      })
    },
    //查询纠纷调解记录
    getListMediationRecords() {
      const form = {
        caseCode : this.form.caseCode
      }
      listMediationRecords(form).then( response => {
        this.mediationRecordsList = response.rows;
      })
    },
    //查询纠纷相关文件
    getListFileManagements() {
      const form = {
        folderName : this.form.caseCode
      }
      listFileManagements(form).then( response => {
        this.fileManagementsList = response.rows;
      })
    },
    closePage() {
      this.$tab.closePage()
    },
  }
}
</script>
 
<style scoped lang="scss">
  .edit-form {
    min-height: calc(100vh - 85px);
    background-color: var(--page-background-color);
 
    padding: 10px;
    position: relative;
    overflow: inherit!important;
 
    .form-panel-item {
      background-color: #fff;
      border-radius: 4px;
 
      padding: 10px 20px;
      margin-bottom: 15px;
    }
 
    .split-container {
      --aside-width: 32.8%;
    }
 
    .footer {
      position: sticky;
      bottom: 0;
      right: 10px;
      left: 0;
    }
  }
</style>
