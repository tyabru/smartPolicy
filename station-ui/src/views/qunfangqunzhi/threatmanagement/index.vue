<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="200px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="上报事件编号" prop="contactPersonName">
            <el-input
              v-model="queryParams.id"
              placeholder="请输入事件编号"
              clearable
              onkeyup="value=value.replace(/[^\d]/g,'')"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="事件状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="请选择事件状态" clearable>
              <el-option
                v-for="dict in dict.type.event_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="上传时间"  >
            <el-date-picker
              v-model="daterangeUploadTime"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="发生时间">
            <el-date-picker
              v-model="daterangeOccurTime"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="事件类别" prop="eventType">
            <el-select v-model="queryParams.eventType" placeholder="请选择事件类别" clearable>
              <el-option
                v-for="dict in dict.type.event_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      <el-col :span="8">
        <el-form-item label="归属部门" prop="deptId" >
          <treeselect style="width: 200px" v-model="queryParams.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门"/>
        </el-form-item>
      </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['qunfangqunzhi:threatmanagement:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['qunfangqunzhi:threatmanagement:remove']"
        >删除
        </el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="warning"-->
      <!--          plain-->
      <!--          icon="el-icon-download"-->
      <!--          size="mini"-->
      <!--          @click="handleExport"-->
      <!--          v-hasPermi="['threat:threatmanagement:export']"-->
      <!--        >导出</el-button>-->
      <!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="threatmanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="事件编号" width="100" align="center" prop="id"/>
      <el-table-column label="事件类别" align="center" prop="eventType">
        <template slot-scope="scope">
          <span>{{ getTypeLabel(scope.row.eventType) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件概述" align="center" prop="eventSummarize"/>
      <el-table-column label="所属辖区" align="center" prop="dept.deptName" />
      <el-table-column label="联系人姓名" align="center" prop="contactPersonName"/>
      <el-table-column label="联系电话" align="center" prop="contactPhone"/>
      <el-table-column label="发生地点" align="center" prop="address"/>
      <el-table-column label="发生时间" align="center" prop="occurTime" width="180">
      </el-table-column>
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
      </el-table-column>
      <el-table-column label="处理状态" align="center" prop="status">
        <template slot-scope="scope">
          <span>{{ getStatusLabel(scope.row.status) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-if="scope.row.status !=='2'"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-zoom-in"
            @click="goDetail(scope.row)"
          >详情
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-zoom-in"
            @click="transferEvent(scope.row)"
            v-if="scope.row.status !=='2'"
          >上报
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改群防群治对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="归属部门" prop="deptId">
          <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门"/>
        </el-form-item>
        <el-form-item label="联系人姓名" prop="contactPersonName">
          <el-input v-model="form.contactPersonName" placeholder="请输入联系人姓名"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话"/>
        </el-form-item>
        <el-form-item label="事件类型" prop="eventType">
          <el-select v-model="form.eventType" clearable placeholder="请选择事件类型">
            <el-option
              v-for="item in this.dict.type.event_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="事件发生地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入发生地址"/>
        </el-form-item>
        <el-form-item label="事件概述" prop="eventSummarize">
          <el-input v-model="form.eventSummarize" placeholder="请输入事件概述"/>
        </el-form-item>
        <el-form-item label="事件描述" prop="eventDescription">
          <el-input type="textarea" v-model="form.eventDescription" placeholder="请输入事件描述"/>
        </el-form-item>
        <el-form-item label="发生时间" prop="occurTime">
          <el-date-picker clearable
                          v-model="form.occurTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择发生时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item  label="图片说明" prop="photoUrl" >
          <my-image-up-load  v-model="form.photoUrl" ></my-image-up-load>
        </el-form-item>
        <el-form-item  label="视频信息" prop="videoUrl"  >
          <file-upload v-model="form.videoUrl" :file-size="20" :file-type="fileType" :limit="1" ></file-upload>
        </el-form-item>
        <el-form-item label="反馈信息" v-if="form.status == '2'" prop="reply">
          <el-input v-model="form.reply" placeholder="请输入完成时提供反馈信息"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改群防群治信息对话框 -->
    <!--    <el-dialog title="修改事件信息" :visible.sync="updateOpen" width="500px" append-to-body>-->
    <!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
    <!--        <el-form-item label="提交用户id" prop="uploadUserId">-->
    <!--          <el-input v-model="form.uploadUserId" placeholder="请输入提交用户id" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="联系人姓名" prop="contactPersonName">-->
    <!--          <el-input v-model="form.contactPersonName" placeholder="请输入联系人姓名" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="联系电话" prop="contactPhone">-->
    <!--          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="发生地址" prop="address">-->
    <!--          <el-input v-model="form.address" placeholder="请输入发生地址" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="发生地址经纬度信息" prop="addressData">-->
    <!--          <el-input v-model="form.addressData" placeholder="请输入发生地址经纬度信息" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="事件描述" prop="eventDescription">-->
    <!--          <el-input v-model="form.eventDescription" placeholder="请输入事件描述" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="发生时间" prop="occurTime">-->
    <!--          <el-date-picker clearable-->
    <!--                          v-model="form.occurTime"-->
    <!--                          type="datetime"-->
    <!--                          placeholder="请选择发生时间">-->
    <!--          </el-date-picker>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="照片地址" prop="photoUrl">-->
    <!--          <el-input v-model="form.photoUrl" placeholder="请输入照片地址" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="视频地址" prop="videoUrl">-->
    <!--          <el-input v-model="form.videoUrl" placeholder="请输入视频地址" />-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="完成时提供反馈信息" prop="reply">-->
    <!--          <el-input v-model="form.reply" placeholder="请输入完成时提供反馈信息" />-->
    <!--        </el-form-item>-->
    <!--        <el-divider content-position="center">${subTable.functionName}信息</el-divider>-->
    <!--        <el-row :gutter="10" class="mb8">-->
    <!--          <el-col :span="1.5">-->
    <!--            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddEventUserAllocated">添加</el-button>-->
    <!--          </el-col>-->
    <!--          <el-col :span="1.5">-->
    <!--            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteEventUserAllocated">删除</el-button>-->
    <!--          </el-col>-->
    <!--        </el-row>-->
    <!--        <el-table :data="eventUserAllocatedList" :row-class-name="rowEventUserAllocatedIndex" @selection-change="handleEventUserAllocatedSelectionChange" ref="eventUserAllocated">-->
    <!--          <el-table-column type="selection" width="50" align="center" />-->
    <!--          <el-table-column label="序号" align="center" prop="index" width="50"/>-->
    <!--          <el-table-column label="分配userid" prop="userId" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.userId" placeholder="请输入分配userid" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="事件调查详情" prop="eventDetail" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.eventDetail" placeholder="请输入事件调查详情" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="图片上传路径" prop="photoUrl" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.photoUrl" placeholder="请输入图片上传路径" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="视频上传路径" prop="videoUrl" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.videoUrl" placeholder="请输入视频上传路径" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="事件结果" prop="eventResult" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.eventResult" placeholder="请输入事件结果" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="结果照片地址" prop="resultPhotoUrl" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.resultPhotoUrl" placeholder="请输入结果照片地址" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="结果视频地址" prop="resultVideoUrl" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.resultVideoUrl" placeholder="请输入结果视频地址" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="事件处理状态" prop="status" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-select v-model="scope.row.status" placeholder="请选择事件处理状态，0未确认，1已确认，2已取消，3已完成">-->
    <!--                <el-option label="请选择字典生成" value="" />-->
    <!--              </el-select>-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--          <el-table-column label="分配这件事用户的id" prop="allocateUserId" width="150">-->
    <!--            <template slot-scope="scope">-->
    <!--              <el-input v-model="scope.row.allocateUserId" placeholder="请输入分配这件事用户的id" />-->
    <!--            </template>-->
    <!--          </el-table-column>-->
    <!--        </el-table>-->
    <!--      </el-form>-->
    <!--      <div slot="footer" class="dialog-footer">-->
    <!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
    <!--        <el-button @click="cancel">取 消</el-button>-->
    <!--      </div>-->
    <!--    </el-dialog>-->
  </div>
</template>

<script>
  import {
    listThreatmanagement,
    getThreatmanagement,
    delThreatmanagement,
    addThreatmanagement,
    updateThreatmanagement,
    transferEvent
  } from "@/api/qunfangqunzhi/threatmanagement";
  import {deptTreeSelect} from "@/api/system/user";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import myImageUpLoad from "@/views/qunfangqunzhi/myImageUpLoad";
  import {countUnconfirmedEvents} from "@/api/qunfangqunzhi/statistics";

  export default {
    name: "Threatmanagement",
    dicts: ['event_status', 'event_type'],
    components: {Treeselect,myImageUpLoad},
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 子表选中数据
        checkedEventUserAllocated: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 群防群治表格数据
        threatmanagementList: [],
        // ${subTable.functionName}表格数据
        eventUserAllocatedList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 分配这件事用户的id时间范围
        daterangeOccurTime: [],
        daterangeUploadTime: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          id: null,
          uploadUserId: null,
          eventType: null,
          contactPersonName: null,
          contactPhone: null,
          address: null,
          occurTime: null,
          status: null,
          reply: null,
          uploadTime: null,
          deptId:null
        },
        // 表单参数
        form: {},
        // 表单校验
        // 表单校验
        rules: {
          deptId: [
            {required: true, message: "辖区选项不能为空", trigger: "blur"}
          ],
          eventType: [
            {required: true, message: "事件类型不能为空", trigger: "blur"}
          ],
          contactPersonName: [
            {required: true, message: "联系人姓名不能为空", trigger: "blur"},
            { max: 18, message: '长度在18个字符以内', trigger: 'blur' }
          ],
          contactPhone: [
            { required: true, message: "手机号码不能为空", trigger: "blur" },
            {
              pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
              message: "请输入正确的手机号码",
              trigger: "blur"
            }
          ],
          eventDescription: [
            { required: true, message: "事件描述不能为空", trigger: "blur" },
          ],
          occurTime: [
            { required: true, message: "事件发生时间不能为空", trigger: "blur" },
          ],
          photoUrl:[
            {required: true, message: "至少上传一张图片", trigger: "change"},
            { min: 18, message: '至少上传一张图片', trigger: 'blur' }
          ]
        },
        updateOpen: false,
        deptOptions: undefined,
        fileType:["avi", "mp4", "3gp","flv","f4v","3g2","rmvb","wmv"],
      };
    },
    created() {
      this.getList();

    },
    mounted() {
      this.getDeptTree();
      this.getUnconfirmedEvents();
    },
    methods: {
      /** 查询群防群治列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeOccurTime && '' != this.daterangeOccurTime) {
          this.queryParams.params["beginOccurTime"] = this.daterangeOccurTime[0];
          this.queryParams.params["endOccurTime"] = this.daterangeOccurTime[1];
        }
        if (null != this.daterangeUploadTime && '' != this.daterangeUploadTime) {
          this.queryParams.params["beginUploadTime"] = this.daterangeUploadTime[0];
          this.queryParams.params["endUploadTime"] = this.daterangeUploadTime[1];
        }
        listThreatmanagement(this.queryParams).then(response => {

          this.threatmanagementList = response.rows;
          this.total = response.total;
          this.loading = false;

        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          uploadUserId: null,
          eventType: null,
          contactPersonName: null,
          contactPhone: null,
          address: null,
          addressData: null,
          eventDescription: null,
          occurTime: null,
          photoUrl: null,
          videoUrl: null,
          status: null,
          reply: null
        };
        this.eventUserAllocatedList = [];
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeOccurTime = [];
        this.resetForm("queryForm");
        this.queryParams.eventType = null;
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "新增事件信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getThreatmanagement(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改事件信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form.eventUserAllocatedList = null;
            if (this.form.id != null) {
              console.log(this.form + "修改")
              updateThreatmanagement(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              console.log(this.form + "新增")
              addThreatmanagement(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除群防群治编号为"' + ids + '"的数据项？').then(function () {
          return delThreatmanagement(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** ${subTable.functionName}序号 */
      rowEventUserAllocatedIndex({row, rowIndex}) {
        row.index = rowIndex + 1;
      },
      /** ${subTable.functionName}添加按钮操作 */
      handleAddEventUserAllocated() {
        let obj = {};
        obj.userId = "";
        obj.eventDetail = "";
        obj.photoUrl = "";
        obj.videoUrl = "";
        obj.eventResult = "";
        obj.resultPhotoUrl = "";
        obj.resultVideoUrl = "";
        obj.status = "";
        obj.allocateUserId = "";
        this.eventUserAllocatedList.push(obj);
      },
      /** ${subTable.functionName}删除按钮操作 */
      handleDeleteEventUserAllocated() {
        if (this.checkedEventUserAllocated.length == 0) {
          this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
        } else {
          const eventUserAllocatedList = this.eventUserAllocatedList;
          const checkedEventUserAllocated = this.checkedEventUserAllocated;
          this.eventUserAllocatedList = eventUserAllocatedList.filter(function (item) {
            return checkedEventUserAllocated.indexOf(item.index) == -1
          });
        }
      },
      /** 复选框选中数据 */
      handleEventUserAllocatedSelectionChange(selection) {
        this.checkedEventUserAllocated = selection.map(item => item.index)
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('threat/threatmanagement/export', {
          ...this.queryParams
        }, `threatmanagement_${new Date().getTime()}.xlsx`)
      },

      //获取状态标签内容
      getStatusLabel(val) {
        for (let dict of this.dict.type.event_status) {
          if (dict.value == val) {
            return dict.label;
          }
        }
      },
      ////获取类别标签内容
      getTypeLabel(val) {
        for (let dict of this.dict.type.event_type) {
          if (dict.value == val) {
            return dict.label;
          }
        }
      },
      goDetail(row) {
        this.$router.push("/qunfangqunzhi/threatManage/detail/" + row.id);
      },
      /** 查询部门下拉树结构 */
      getDeptTree() {
        deptTreeSelect().then(response => {
          this.deptOptions = response.data;
        });
      },
      //吧任务转移到上级
      transferEvent(item) {
        this.$modal.confirm('如果该事件不属于您所属辖区范围内，确定将提交到您的上级单位!').then(() => {
          transferEvent(item.id).then(res => {
            if (res.data == "error") {
              this.$message.warning(res.msg)
            } else {
              this.$message.success(res.msg)
            }
            this.getList();
          })
        }).catch(() => {
        });
      },
     //
      getUnconfirmedEvents(){
        setTimeout(()=>countUnconfirmedEvents().then(res=>{
          let list = res.data
          if(list!=null && list.length !==0){
            let str = "当前用户所属辖区下所属未确认事件有类别为"
            for(let a in list){
              let label = this.getTypeLabel(list[a].eventType);
              str += label;
              str += "事件";
              str += list[a].times;
              str += "件"
              if(a+1<list.length){
                str+=","
              }
            }
            str += "。"
            this.$notify({
              type:"warning",
              title: '待处理事件清单',
              duration:0,
              message: str
            });
          }
        }),500)



      }
    }
  };
</script>
