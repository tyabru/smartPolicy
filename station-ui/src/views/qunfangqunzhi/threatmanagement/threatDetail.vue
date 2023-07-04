<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="提交用户id" prop="uploadUserId">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.uploadUserId }}</el-tag>
      </el-form-item>
      <el-form-item label="联系人姓名" prop="contactPersonName">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.contactPersonName }}</el-tag>
      </el-form-item>
      <el-form-item label="联系电话" prop="contactPhone">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.contactPhone }}</el-tag>
      </el-form-item>
      <el-form-item label="事件地址" prop="address">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.address }}</el-tag>
      </el-form-item>
      <!--      <el-form-item label="发生地址经纬度信息" prop="addressData">-->
      <!--        <el-input v-model="form.addressData" placeholder="请输入发生地址经纬度信息" />-->
      <!--      </el-form-item>-->
      <el-form-item label="事件详情" prop="eventDescription">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.eventDescription }}</el-tag>
      </el-form-item>
      <el-form-item label="发生时间" prop="occurTime">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.occurTime }}</el-tag>

      </el-form-item>
      <el-form-item label="上传时间" prop="occurTime">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.uploadTime }}</el-tag>
      </el-form-item>
      <!--      <el-form-item label="照片地址" prop="photoUrl">-->
      <!--        <el-input v-model="form.photoUrl" placeholder="请输入照片地址" />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="视频地址" prop="videoUrl">-->
      <!--        <el-input v-model="form.videoUrl" placeholder="请输入视频地址" />-->
      <!--      </el-form-item>-->

      <el-form-item v-if="form.reply!=null" label="反馈信息" prop="reply">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.reply }}</el-tag>
      </el-form-item>
      <el-form-item v-if="form.reply!=null" label="反馈信息" prop="reply">
        <el-tag :hit="true" type="info" class="my_tag">{{ form.reply }}</el-tag>
      </el-form-item>


      <el-divider content-position="center">处理信息</el-divider>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddEventUserAllocated">添加</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteEventUserAllocated">删除
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
        <el-table-column label="分配userid" align="center" prop="userId"/>
        <el-table-column label="事件id" align="center" prop="eventId"/>
        <el-table-column label="事件调查详情" align="center" prop="eventDetail"/>
        <el-table-column label="图片上传路径" align="center" prop="photoUrl"/>
        <el-table-column label="视频上传路径" align="center" prop="videoUrl"/>
        <el-table-column label="事件结果" align="center" prop="eventResult"/>
        <el-table-column label="结果照片地址" align="center" prop="resultPhotoUrl"/>
        <el-table-column label="结果视频地址" align="center" prop="resultVideoUrl"/>
        <el-table-column label="事件处理状态" align="center" prop="status">
          <template slot-scope="scope">
            <span>{{getStatusLabel(scope.row.status)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="分配这件事用户的id" align="center" prop="allocateUserId"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['threat:allocated:edit']"
            >修改
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['threat:allocated:remove']"
            >删除
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
  </div>
</template>

<script>
  import { getThreatmanagement } from '@/api/qunfangqunzhi/threatmanagement'
  import { addAllocated, updateAllocated, downloadEventAllocated } from '@/api/qunfangqunzhi/allocated'
  import { deptTreeSelect } from '@/api/system/user'
  import {listGridStuff} from  "@/api/qunfangqunzhi/CommonUsers"

  export default {
    name: 'threatDetail',
    dicts:['threat_allocate_status'],
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
        userIds: null
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
          console.log(response.data);
          this.form = response.data
          this.eventUserAllocatedList = response.data.eventUserAllocatedList
        })
      },
      rowEventUserAllocatedIndex({ row, rowIndex }) {
        row.index = rowIndex + 1
      },
      handleAddEventUserAllocated() {
        let obj = {}
        obj.userId = ''
        obj.eventDetail = ''
        obj.photoUrl = ''
        obj.videoUrl = ''
        obj.eventResult = ''
        obj.resultPhotoUrl = ''
        obj.resultVideoUrl = ''
        obj.status = ''
        obj.allocateUserId = ''
        this.eventUserAllocatedList.push(obj)
      },
      /** 复选框选中数据 */
      handleEventUserAllocatedSelectionChange(selection) {
        this.checkedEventUserAllocated = selection.map(item => item.index)
      },
      /**删除事件分配 */
      handleDeleteEventUserAllocated() {
        if (this.checkedEventUserAllocated.length == 0) {
          this.$modal.msgError('请先选择要删除的${subTable.functionName}数据')
        } else {
          const eventUserAllocatedList = this.eventUserAllocatedList
          const checkedEventUserAllocated = this.checkedEventUserAllocated
          this.eventUserAllocatedList = eventUserAllocatedList.filter(function(item) {
            return checkedEventUserAllocated.indexOf(item.index) == -1
          })
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
          allocateUserId: null
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
