<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="提交用户id" prop="uploadUserId">
        <el-input v-model="form.uploadUserId"  readonly></el-input>
      </el-form-item>
      <el-form-item label="联系人姓名" prop="contactPersonName">
        <el-input v-model="form.contactPersonName"  readonly></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="contactPhone">
        <el-input v-model="form.contactPhone"  readonly></el-input>
      </el-form-item>
      <el-form-item label="事件地址" prop="address">
        <el-input v-model=" form.address"  readonly></el-input>
      </el-form-item>
      <el-form-item label="事件概要" prop="eventDescription">
        <el-input v-model=" form.eventSummarize"   readonly></el-input>
      </el-form-item>
      <el-form-item label="事件详情" prop="eventDescription">
        <el-input v-model="form.eventDescription"  type="textarea"  readonly></el-input>
      </el-form-item>
      <el-form-item label="发生时间" prop="occurTime">
        <el-input v-model=" form.occurTime" readonly></el-input>
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-input v-model=" form.uploadTime"  readonly></el-input>
      </el-form-item>

      <el-form-item v-if="this.photoUrls &&this.photoUrls.length!==0" label="图片说明" prop="photoUrls">
        <el-image v-for="url in photoUrls" :src="url" style="width: 300px; height: 180px" :preview-src-list="[url]"/>
      </el-form-item>
      <!--      <el-form-item label="照片地址" prop="photoUrl">-->
      <!--        <el-input v-model="form.photoUrl" placeholder="请输入照片地址" />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="视频地址" prop="videoUrl">-->
      <!--        <el-input v-model="form.videoUrl" placeholder="请输入视频地址" />-->
      <!--      </el-form-item>-->
      <el-form-item v-if="this.videoUrl && this.videoUrl!==''"   label="视频信息" prop="videoUrl" style="width: 600px; ">
        <vue-aliplayer-v2
          :source="this.videoUrl"
          ref="VueAliplayerV2"
        />
      </el-form-item>
      <el-form-item v-if="form.reply!=null" label="反馈信息" prop="reply">
        <el-input v-model="form.reply " readonly></el-input>
      </el-form-item>
      <el-form-item  v-if="form.remark!=null" label="备注" prop="reply">
        <el-input v-model="form.remark " readonly></el-input>
      </el-form-item>


      <el-divider content-position="center">处理信息</el-divider>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteEventUserAllocated">批量取消
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-delete" size="mini" @click="openDialog">下发</el-button>
        </el-col>
      </el-row>
      <el-table v-loading="loading" :data="eventUserAllocatedList"
                @selection-change="handleEventUserAllocatedSelectionChange" ref="eventUserAllocated"
      >
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="处理人员编号" align="center" prop="userId"/>
        <el-table-column label="检查时间" align="center" prop="inspectDateTime"/>
        <el-table-column label="整改意见" align="center" prop="rectifyOpinion"/>
        <el-table-column label="整改结果" align="center" prop="rectifyResult"/>
        <el-table-column label="事件处理状态" align="center" prop="status">
          <template slot-scope="scope">
            <span>{{getStatusLabel(scope.row.status)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="下发用户编号" align="center" prop="allocateUserId"/>
        <el-table-column label="下发用户类型" align="center" prop="allocatedUserType"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-zoom-in"
              @click="handleAllocateDetail(scope.row.id)"
              v-hasPermi="['threat:allocated:query']"
            >详情
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleCancel(scope.row.id)"
              v-hasPermi="['threat:allocated:remove']"
            >取消
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
    <!--    下发事件窗口-->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-row :gutter="20">
          <!--部门数据-->
          <el-col :span="4" :xs="24">
            <div class="head-container">
              <el-input
                v-model="deptName"
                placeholder="请输入部门名称"
                clearable
                size="small"
                prefix-icon="el-icon-search"
                style="margin-bottom: 20px"
              />
            </div>
            <div class="head-container">
              <el-tree
                :data="deptOptions"
                :props="defaultProps"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                ref="tree"
                node-key="id"
                default-expand-all
                highlight-current
                @node-click="handleNodeClick"
              />
            </div>
          </el-col>
          <!--用户数据-->
          <el-col :span="20" :xs="24">
            <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
              <el-table-column type="selection" width="50" align="center"/>
              <el-table-column label="用户编号" align="center" key="userId" prop="userId"/>
              <el-table-column label="用户名称" align="center" key="userName" prop="userName"
                               :show-overflow-tooltip="true"
              />
              <el-table-column label="用户昵称" align="center" key="nickName" prop="nickName"
                               :show-overflow-tooltip="true"
              />
              <el-table-column label="手机号码" align="center" key="phonenumber" prop="phonenumber" width="120"/>
            </el-table>
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
            />
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <el-dialog :title="allocatedTitle" :visible.sync="allpcatedFormOpen" v-if="allpcatedFormOpen" width="1200px" append-to-body >
      <el-form ref="form" :model="allocatedForm" label-width="120px">
        <el-form-item label="网格员编号">
          <el-input v-model="allocatedForm.userId"  readonly></el-input>
        </el-form-item>
        <el-form-item label="网格员账号">
          <el-input v-model="allocatedForm.params.userName"  readonly></el-input>
        </el-form-item>
        <el-form-item label="网格员姓名">
          <el-input v-model="allocatedForm.params.realName"  readonly></el-input>
        </el-form-item>
        <el-form-item label="事件编号">
          <el-input v-model="allocatedForm.eventId"  readonly></el-input>
        </el-form-item>
        <el-form-item label="检查时间">
          <el-input v-model="allocatedForm.inspectDatetime"  readonly></el-input>
        </el-form-item>
        <el-form-item label="检查位置">
          <el-input v-model="allocatedForm.inspectLocation"  readonly></el-input>
        </el-form-item>
        <el-form-item label="检查细节">
          <el-input v-model="allocatedForm.inspectDetail"  type="textarea" readonly></el-input>
        </el-form-item >
        <el-form-item label="存在问题">
          <el-input v-model="allocatedForm.existProblem"   type="textarea" readonly></el-input>
        </el-form-item >
        <el-form-item label="整改意见">
          <el-input v-model="allocatedForm.rectifyOpinion" type="textarea"  readonly></el-input>
        </el-form-item >
        <el-form-item label="整改结果">
          <el-input v-model="allocatedForm.rectifyResult"  type="textarea" readonly></el-input>
        </el-form-item >
        <el-form-item v-if="this.photoUrls &&this.photoUrls.length!==0" label="图片说明" prop="photoUrls">
          <el-image v-for="url in photoUrls" :src="url" style="width: 300px; height: 180px" :preview-src-list="[url]"/>
        </el-form-item>
        <el-form-item v-if="this.videoUrl && this.videoUrl!==''"   label="视频信息" prop="videoUrl" style="width: 600px; ">
          <vue-aliplayer-v2
            :source="this.videoUrl"
            ref="VueAliplayerV2"
          />
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
  import { getThreatmanagement } from '@/api/qunfangqunzhi/threatmanagement'
  import { addAllocated, updateAllocated, downloadEventAllocated,cancelEventAllocated,getAllocated} from '@/api/qunfangqunzhi/allocated'
  import { deptTreeSelect } from '@/api/system/user'
  import {listGridStuff} from  "@/api/qunfangqunzhi/CommonUsers"
  import VueAliplayerV2 from "vue-aliplayer-v2";

  export default {
    name: 'threatDetail',
    dicts:['threat_allocate_status'],
    components: {
        VueAliplayerV2
    },
    data() {
      return {
        form: {},
        id: null,
        eventUserAllocatedList: null,
        open: false,
        rules: {},
        title: '下发时间',
        deptName: '',
        deptOptions: null,
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          userName: undefined,
          phonenumber: undefined,
          status: undefined,
          deptId: undefined
        },
        userList: [],
        total: null,
        loading: false,
        //用户ID组
        userIds: null,
        photoUrls:[],
        videoUrl:null,

        //下发情况详细表单
        allocatedForm:{},
        allpcatedFormOpen:false,
        allocatedTitle:"处理详情"
      }
    },
    created() {
      this.id = this.$route.params.id
      this.getDetail()
      this.getList()
    },
    watch: {
      // 根据名称筛选部门树
      deptName(val) {
        this.$refs.tree.filter(val)
      }
    },
    methods: {
      getDetail() {
        getThreatmanagement(this.id).then(response => {
          this.form = response.data
          if(response.data.eventUserAllocatedList != null){
            this.eventUserAllocatedList = response.data.eventUserAllocatedList;
          }

          if(response.data.photoUrl  && response.data.photoUrl !== ""){
              let str = response.data.photoUrl;
              let strings = str.split(",");
               this.photoUrls= strings.map((string)=>{
                return process.env.VUE_APP_BASE_API +string;
              })

          }
          if(response.data.videoUrl && response.data.videoUrl!==""){
            this.videoUrl = process.env.VUE_APP_BASE_API +response.data.videoUrl;
          }
        })
      },
      rowEventUserAllocatedIndex({ row, rowIndex }) {
        row.index = rowIndex + 1
      },

      /** 复选框选中数据 */
      handleEventUserAllocatedSelectionChange(selection) {
        this.checkedEventUserAllocated = selection.map(item => {
          if (item.status !=="3"){
            return item.id;
          }

        })
      },
      /**批量取消下发数据 */
      handleDeleteEventUserAllocated() {
        if (!this.checkedEventUserAllocated||this.checkedEventUserAllocated.length=== 0) {
          this.$modal.msgError('请先选择要取消的数据')
        } else {
          this.$confirm("确定要取消下发事件吗？",'提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(()=>{
            cancelEventAllocated(this.checkedEventUserAllocated).then(res=>{
              this.$modal.msg("取消成功!");
              this.getDetail();
            })
          }).catch(() => {})
        }
      },
      /** 查询部门下拉树结构 */
      getDeptTree() {
        deptTreeSelect().then(response => {
          this.deptOptions = response.data
        })
      },
      /** 打开下发界面*/
      openDialog() {
        this.open = true
        this.getDeptTree()
        this.getList()
      },
      /** 提交按钮 */
      submitForm() {
        if (this.userIds != null || this.userIds === []) {
          downloadEventAllocated(this.userIds, this.id).then(response => {
            this.$message(response.msg)
            this.getList()
            this.getDetail()
          })
        } else {
          this.$alert('下发用户选择不能为空')
        }
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 筛选节点
      filterNode(value, data) {
        if (!value) return true
        return data.label.indexOf(value) !== -1
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          userId: null,
          eventId: null,
          eventDetail: null,
          photoUrl: null,
          videoUrl: null,
          eventResult: null,
          resultPhotoUrl: null,
          resultVideoUrl: null,
          status: null,
          allocateUserId: null,
          eventSummarize: null
        }
        this.resetForm('form')
      },
      /** 查询用户列表 */
      getList() {
        this.loading = true
        this.queryParams.eventID = this.id
        listGridStuff(this.queryParams).then(response => {
            this.userList = response.rows
            this.total = response.total
            this.loading = false
          }
        )
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.userIds = selection.map(item => item.userId)
      },
      // 节点单击事件
      handleNodeClick(data) {
        this.queryParams.deptId = data.id
        this.handleQuery()
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1
        this.getList()
      },
      //获取下发事件处理情况标签内容
      getStatusLabel(val){
        for(let dict of this.dict.type.threat_allocate_status){
          if(dict.value == val){
            return dict.label;
          }
        }
      },
      //单个取消
      handleCancel(id){
        this.$confirm("确定要取消下发事件吗？",'提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          cancelEventAllocated(id).then(res=>{
            this.$modal.msg("取消成功!");
            this.getDetail();
          })
        }).catch(() => {})

      },
      handleAllocateDetail(id){
        this.allpcatedFormOpen = true;
        getAllocated(id).then(res=>{
          this.allocatedForm = res.data;
        })
      }
    }

  }
</script>

<style scoped>
  .my_tag {
    width: 100%;
    color: black;

  }

</style>
