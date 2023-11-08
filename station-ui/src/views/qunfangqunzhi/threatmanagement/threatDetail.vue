<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="提交用户账号" prop="userName">
        <el-input v-model="form.userName"  readonly></el-input>
      </el-form-item>
      <el-form-item label="提交用户类型" prop="uploadUserType">
        <el-input v-model="form.uploadUserType"  readonly></el-input>
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
        <el-image v-for="url in photoUrls" :src="url" style="width: 300px; height: 180px;margin-right: 20px" :key='url' :preview-src-list="[url]"/>
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
        <el-input v-model="form.reply "  readonly></el-input>
      </el-form-item>
      <el-form-item  v-if="form.remark!=null" label="备注" prop="reply">
        <el-input v-model="form.remark "   readonly></el-input>
      </el-form-item>


      <el-divider content-position="center">处理信息</el-divider>
      <el-row :gutter="10" class="mb8" v-if="form.status !== '2'"  v-hasPermi="['qunfangqunzhi:threatmanagement:query']">
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteEventUserAllocated">批量取消
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-user" size="mini" @click="openDialog">下发</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="openDeal">处置</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button  type="primary" icon="el-icon-s-order" size="mini" @click="openFinish">归档</el-button>
        </el-col>
      </el-row>
      <el-table v-loading="loading" :data="eventUserAllocatedList"
                @selection-change="handleEventUserAllocatedSelectionChange" ref="eventUserAllocated"
      >
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="处理人员账号" align="center" prop="params.userName"/>
        <el-table-column label="处理人员类型" align="center" prop="userType">
          <template slot-scope="scope">
            <span>{{getUserTypeLabel(scope.row.userType)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="下发用户账号" align="center" prop="params.allocatedUserName"/>
        <el-table-column label="下发用户类型" align="center" prop="allocatedUserType"/>
        <el-table-column label="下发时间" align="center" prop="allocateTime"/>
        <el-table-column label="事件处理状态" align="center" prop="status">
          <template slot-scope="scope">
            <span>{{getStatusLabel(scope.row.status)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-zoom-in"
              @click="handleAllocateDetail(scope.row.id)"
              v-hasPermi="['qunfangqunzhi:threatmanagement:query']"
            >详情
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleCancel(scope.row.id)"
              v-if="form.status !== '2'"
              v-hasPermi="['qunfangqunzhi:threatmanagement:query']"
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
              <el-table-column label="用户类型" align="center" prop="userType" >
                <template slot-scope="scope">
                  <span>{{getUserTypeLabel(scope.row.userType)}}</span>
                </template>
              </el-table-column>
              <el-table-column label="用户账号" align="center" key="userName" prop="userName"
                               :show-overflow-tooltip="true"
              />
              <el-table-column label="姓名" align="center" key="realName" prop="realName"
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

<!--    下发详情窗口-->
    <el-dialog :title="allocatedTitle" :visible.sync="allocatedFormOpen"  v-if="allocatedFormOpen" width="1200px" append-to-body >
      <el-form ref="form" :model="allocatedForm" label-width="120px">
        <el-form-item :label="detailUser+'编号'">
          <el-input v-model="allocatedForm.userId"  readonly></el-input>
        </el-form-item>
        <el-form-item :label="detailUser+'账号'">
          <el-input v-model="allocatedForm.params.userName"  readonly></el-input>
        </el-form-item>
        <el-form-item :label="detailUser+'姓名'" v-if="allocatedForm.dealFlag == '0' ">
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
        <el-form-item v-if="this.detailPhotoUrl &&this.detailPhotoUrl.length!==0" label="图片说明" prop="detailPhotoUrl">
          <el-image v-for="url in detailPhotoUrl" :src="url" :key='url' style="width: 200px; height: 120px;margin-right: 20px" :preview-src-list="[url]"/>
        </el-form-item>
        <el-form-item v-if="this.detailVideoUrl && this.detailVideoUrl!==''"   label="视频信息" prop="detailVideoUrl" style="width: 600px; ">
          <vue-aliplayer-v2
            :source="this.detailVideoUrl"
            ref="VueAliplayerV2"
          />
        </el-form-item>
      </el-form>

      <el-divider>修改意见</el-divider>
      <el-row>
        <el-button v-if=" allocatedForm.status==='1' || allocatedForm.status==='0' " style="margin-bottom: 20px" @click="redoAllocate(allocatedForm.id)">增加处理意见</el-button>
      </el-row>

      <el-table
        :data="rectifyOpinions"
        height="250"
        border
        style="width: 100%">
        <el-table-column
          align="center"
          prop="sendTime"
          label="发送日期"
          width="150px"
          >
        </el-table-column>
        <el-table-column
          align="center"
          prop="rectifyOpinion"
          label="修改意见"
          width="850px"
          >
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-zoom-in"
              @click="deleteRectifyOpinion(scope.row.id)"
              v-hasPermi="['threat:allocated:query']"
            >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmAllocate" v-if="this.allocatedForm.status == 1">确 认</el-button>
        <el-button @click="allocatedFormOpen = false">关 闭</el-button>
      </div>

    </el-dialog>
<!--    处置窗口-->
    <el-dialog :title="dealTitle" :visible.sync="dealFormOpen" v-if="dealFormOpen" width="1200px" append-to-body >
      <el-form ref="form" :rules="rules" :model="dealForm" label-width="120px">
        <el-form-item label="检查时间" prop="inspectDatetime">
          <el-date-picker
            v-model="dealForm.inspectDatetime"
            type="datetime"
            placeholder="选择日期时间"
            align="right"
            value-format="yyyy-MM-dd HH:mm:ss"
            :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检查位置" prop="inspectLocation">
          <el-input v-model="dealForm.inspectLocation"  ></el-input>
        </el-form-item>
        <el-form-item label="检查细节" prop="inspectDetail">
          <el-input v-model="dealForm.inspectDetail"  type="textarea" ></el-input>
        </el-form-item >
        <el-form-item label="存在问题" prop="existProblem">
          <el-input v-model="dealForm.existProblem"   type="textarea" ></el-input>
        </el-form-item >
        <el-form-item label="整改意见" prop="rectifyOpinion">
          <el-input v-model="dealForm.rectifyOpinion" type="textarea" ></el-input>
        </el-form-item >
        <el-form-item label="整改结果" prop="rectifyResult">
          <el-input v-model="dealForm.rectifyResult"  type="textarea" ></el-input>
        </el-form-item >
        <el-form-item  label="图片说明" prop="photoUrl" >
          <my-image-up-load  v-model="dealForm.photoUrl" ></my-image-up-load>
        </el-form-item>
        <el-form-item  label="视频信息" prop="videoUrl" style="width: 600px;" >
          <file-upload v-model="dealForm.videoUrl" :file-size="20" :file-type="fileType" :limit="1" ></file-upload>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDealForm">确 定</el-button>
        <el-button @click="cancelDealForm">取 消</el-button>
      </div>
    </el-dialog>
<!--    归档窗口-->
    <el-dialog :title="finishTitle" :visible.sync="finishFormOpen" v-if="finishFormOpen" width="1200px" append-to-body >
      <el-form ref="form" :model="finishForm" label-width="120px">
        <el-form-item label="反馈信息">
          <el-input v-model="finishForm.reply"  placeholder="请填写反馈给上报事件群众的反馈信息" type="textarea" ></el-input>
        </el-form-item >
        <el-form-item label="事件备注">
          <el-input v-model="finishForm.userName"   type="textarea" ></el-input>
        </el-form-item >
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFinishForm">确 定</el-button>
        <el-button @click="cancelFinishForm">取 消</el-button>
      </div>
    </el-dialog>


    <!--    回退窗口-->
    <el-dialog title="修改说明" :visible.sync="redoFormOpen" v-if="redoFormOpen" width="1200px" append-to-body >
      <el-form ref="form" :model="redoForm" label-width="120px">
        <el-form-item label="修改意见">
          <el-input v-model="redoForm.rectifyOpinion"  type="textarea"  required="true"></el-input>
        </el-form-item >
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRedoForm">确 定</el-button>
        <el-button @click="cancelRedoForm">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { getThreatmanagement,finishThreatmanagement } from '@/api/qunfangqunzhi/threatmanagement'
  import { addAllocated, updateAllocated, downloadEventAllocated,cancelEventAllocated,getAllocated} from '@/api/qunfangqunzhi/allocated'
  import { deptTreeSelect } from '@/api/system/user'
  import {listGridStuff} from  "@/api/qunfangqunzhi/CommonUsers"
  import VueAliplayerV2 from "vue-aliplayer-v2";
  import {getToken} from "@/utils/auth";
  import {addAllocateRectify,listAllocateRectify,delAllocateRectify} from "@/api/qunfangqunzhi/allocateRectify";
  import myImageUpLoad from "@/views/qunfangqunzhi/myImageUpLoad";

  export default {
    name: 'threatDetail',
    dicts:['threat_allocate_status','common_user_type'],
    components: {
        VueAliplayerV2,
        myImageUpLoad
    },
    data() {
      return {
        form: {},
        id: null,
        eventUserAllocatedList: null,
        open: false,
        rules: {
          inspectDatetime: [
            { required: true, message: "检查时间不能为空", trigger: "change" }
          ],
          inspectLocation:[
            {required: true, message: "检查地点不能为空", trigger: "change"}
          ],
          inspectDetail:[
            {required: true, message: "检查细节不能为空", trigger: "change"},
          ],
          rectifyOpinion:[
            {required: true, message: "整改意见不能为空", trigger: "change"},
          ],
          rectifyResult:[
            {required: true, message: "整改结果不能为空", trigger: "change"},
          ],
          existProblem:[
            {required: true, message: "存在问题不能为空", trigger: "change"},
          ],
          photoUrl:[
            {required: true, message: "至少上传一张图片", trigger: "change"},
          ]
        },
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
        allocatedFormOpen:false,
        allocatedTitle:"处理详情",
        //处置窗口表单
        dealForm: {
          photoUrl:null,
          videoUrl:null
        },
        dealFormOpen:false,
        dealTitle:"事件处置信息",
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        //上传照片地址
        uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload",
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        // 上传文件类型, 例如['png', 'jpg', 'jpeg']
        fileType:["avi", "mp4", "3gp","flv","f4v","3g2","rmvb","wmv"],
        fileList:[],
        //归档数据项
        finishForm:{},
        finishFormOpen:false,
        finishTitle:"事件归档",
        detailUser:"网格员",
        //归档数据项
        redoForm:{
          rectifyOpinion:null
        },
        redoFormOpen:false,
        rectifyOpinions:null,
        //下发详情中的图片和视频数据
        detailVideoUrl:null,
        detailPhotoUrl:null,
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
          return item.id;

        })
      },
      /**批量取消下发数据 */
      handleDeleteEventUserAllocated(){
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
          if(dict.value === val){
            return dict.label;
          }
        }
        return val;
      },
      //获取用户类型处理标签内容
      getUserTypeLabel(val){
        if(val == null || val ==""){
          return "管理员";
        }else{
          for(let dict of this.dict.type.common_user_type){
            if(dict.value === val){
              return dict.label;
            }
          }
        }
        return val;

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
        this.detailVideoUrl = null;
        this.detailPhotoUrl =null;
        getAllocated(id).then(res=>{
          this.allocatedForm = res.data;
          if(this.allocatedForm.dealFlag == "0"){
            this.detailUser = "网格员";
          }else{
            this.detailUser = "管理员";
          }
          this.allocatedFormOpen = true;
          if(res.data.photoUrl  && res.data.photoUrl !== ""){
            let str = res.data.photoUrl;
            let strings = str.split(",");
            this.detailPhotoUrl= strings.map((string)=>{
              return process.env.VUE_APP_BASE_API +string;
            })

          }
          if(res.data.videoUrl && res.data.videoUrl!==""){
            this.detailVideoUrl = process.env.VUE_APP_BASE_API +res.data.videoUrl;
          }
        });
        this.getRectifyOpinions(id);
      },
      getRectifyOpinions(id){
        let param = {
          allocateId:id
        }
        listAllocateRectify(param).then(res=>{
          this.rectifyOpinions = res.rows;
        })
      },
      openDeal(){
        this.dealForm = {}
        this.dealFormOpen = true;
      },
      // 提交处置信息
      submitDealForm(){
        this.$refs['form'].validate(valid=>{
          if (valid){
            this.dealForm.eventId = this.id;
            addAllocated(this.dealForm).then(res=>{
              this.$message.success("提交处置信息成功");
            }).then(()=>{
              this.getDetail(this.id);
            });
            this.dealFormOpen = false;
          }
        })
      },
      cancelDealForm(){
        this.dealFormOpen = false;
        this.dealForm = {}
      },
      //归档处理
      openFinish(){
        this.finishForm = {};
        this.finishFormOpen = true;
      },
      submitFinishForm(){
        this.$confirm("归档后不能再对事件进行处置或下发，未完成以及未确认的下发事件将会被取消，确定要归档事件吗？",'提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          this.finishForm.id = this.id;
          finishThreatmanagement(this.finishForm).then(res=>{
            this.$message.success("归档事件成功");
            this.finishFormOpen = false;
            this.getDetail();
          })
        }).catch(()=>{})
      },
      cancelFinishForm(){
          this.finishFormOpen = false;
          this.finishForm = {};
      },
      //获取下用户类型标签内容
      redoAllocate(id){
        this.redoFormOpen = true;
        this.redoForm = {
          rectifyOpinion:null
        };
        this.redoForm.allocateId = id;
      },
      //提交
      submitRedoForm(){
        addAllocateRectify(this.redoForm).then(res=>{
          this.$message.success("成功下发修改意见")
          this.redoFormOpen = false
          this.getRectifyOpinions(this.allocatedForm.id);
        })
      },
      deleteRectifyOpinion(id){
        delAllocateRectify(id).then(res=>{
          this.$message.success("删除修改意见成功")
          this.handleAllocateDetail(this.allocatedForm.id)
        })
      },
      confirmAllocate(){
        let item ={
          id:this.allocatedForm.id,
          status:3
        }
        this.$confirm("点击确定后网格员不能再对任务信息进行修改！",'提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(i=>{
          updateAllocated(item).then(res=>{
            this.$message.success("归档下发任务成功")
          });
        }).catch(()=>{});
      },
      cancelRedoForm(){
        this.redoFormOpen=false;
        this.redoForm = {};
      }
    },


  }
</script>

<style scoped>

</style>
