<template>
  <!-- <div class="app-container"> -->
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px"> -->
  <table-panel :show-search="showSearch">
    <template #search-form>
      <el-form size="mini" :model="queryParams" ref="queryForm" label-width="100px" inline>
        <el-row>
          <el-col :span="6">
            <el-form-item label="被检查单位" prop="chectedUnit">
              <el-input
                v-model="queryParams.chectedUnit"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="被检查单位负责人" prop="checkedUnitDirector">
              <el-input
                v-model="queryParams.checkedUnitDirector"
              />
            </el-form-item>
          </el-col>
<!--          
          <el-col :span="6">
            <el-form-item label="场所" prop="placeId">
              <el-select v-model="queryParams.placeId">
                <el-option
                    v-for="dict in dict.type.place_list"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  />
              </el-select>
            </el-form-item>
          </el-col> -->
          <!-- <el-col :span="6">
            <el-form-item label="检查日期" prop="checkDate">
              <el-date-picker clearable
                v-model="queryParams.checkDate"
                type="date"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col> -->
          
          <!-- <el-col :span="6">
            <el-form-item label="被检查人账号" prop="checkedUnitDirectorAcccoutNumber">
              <el-input
                v-model="queryParams.checkedUnitDirectorAcccoutNumber"
              />
            </el-form-item>
          </el-col> -->
          
          <!-- <el-col :span="6">
            <el-form-item label="完成时间" prop="finishDate">
              <el-date-picker clearable
                v-model="queryParams.finishDate"
                type="date"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col> -->

           <el-col :span="6">
            <el-form-item label="审核状态" prop="reviewStatus">
              <el-select v-model="queryParams.reviewStatus">
                <el-option :value="0" label="待审核" />
                <el-option :value="1" label="通过" />
                <el-option :value="2" label="未通过" />
              </el-select>
            </el-form-item>
          </el-col>
           <el-col :span="6">
          <el-form-item label="责任民警" prop="checkPolice">
            <el-input
              v-model="queryParams.checkPolice"
            />
          </el-form-item>
          </el-col>


        </el-row>
      </el-form>
    </template>

     <template #search-form-btn>
      <el-button size="mini" type="primary" @click="handleQuery">查询</el-button>
      <el-button size="mini" type="info"  @click="resetQuery">重置</el-button>
    </template>


     <template #btn>
      <!-- <el-button size="mini" type="primary" @click="handleAdd">新增</el-button> -->
      <el-button size="mini" type="success"  :disabled="single" @click="handleUpdate">修改</el-button>
      <el-button size="mini" type="danger" :disabled="multiple" @click="handleDelete">删除</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </template>

<!-- 
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
          v-hasPermi="['safecheck:rectifyresultreview:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['safecheck:rectifyresultreview:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['safecheck:rectifyresultreview:remove']"
        >删除</el-button>
      </el-col>
       <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['safecheck:rectifyresultreview:export']"
        >导出</el-button>
      </el-col> 
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading" :data="rectifyresultreviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="序号" align="center" prop="id" /> -->
      <el-table-column label="被检查单位" align="center" prop="chectedUnit" />
      <el-table-column label="场所" align="center" prop="placeId">
         <template slot-scope="scope">
          <dict-tag :options="dict.type.place_list" :value="scope.row.placeId"/>
        </template>
      </el-table-column>
      
      <el-table-column label="被检查单位负责人" align="center" prop="checkedUnitDirector" />
      <!-- <el-table-column label="被检查人账号" align="center" prop="checkedUnitDirectorAcccoutNumber" /> -->
      <el-table-column label="被检查人电话号码" align="center" prop="phoneNumber" />
      <el-table-column label="问题隐患" align="center" prop="checkSaftyDanger" />
      <el-table-column label="整改措施" align="center" prop="rectifyMeasure" />
      
      <!-- <el-table-column label="整改通知书签字照片" align="center" prop="rectifyNoticePhoto" width="100"> -->
        <!-- <template slot-scope="scope">
          <image-preview :src="scope.row.rectifyNoticePhoto" :width="50" :height="50"/>
        </template> 
      </el-table-column>-->
      <el-table-column label="整改通知书" align="center" prop="rectifyNoticeSigned" width="100">
        <!-- <transition-group class="upload-file-list el-upload-list el-upload-list--text" name="el-fade-in-linear" tag="ul">
          <li :key="file.url" class="el-upload-list__item ele-upload-list__item-content" v-for="(file, index) in fileList">
            <el-link :href="`${baseUrl}${file.url}`" :underline="false" target="_blank">
              <span class="el-icon-document"> {{ getFileName(file.name) }} </span>
            </el-link>
            <div class="ele-upload-list__item-content-action">
              <el-link :underline="false" @click="handleDelete(index)" type="danger">删除</el-link>
            </div>
          </li>
        </transition-group> -->
        
         <template slot-scope="scope">
          <div v-show=scope.row.rectifyNoticeSigned>
            <a @click="handlePreview(scope.row.rectifyNoticeSigned)" target="_blank" class="buttonText" style="color: #00afff;">查看</a>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="整改结果现场照片" align="center" prop="rectifyResultSitePhoto" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.rectifyResultSitePhoto" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="检查日期" align="center" prop="checkDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成日期" align="center" prop="finishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="责任民警" align="center" prop="checkPolice" />
       <el-table-column label="审核状态" align="center" prop="reviewStatus" >
        <template scope="scope">
          <span v-if="scope.row.reviewStatus==='0'">待审核</span>
          <span v-if="scope.row.reviewStatus==='1'">通过</span>
          <span v-if="scope.row.reviewStatus==='2'">未通过</span>
        </template>
      </el-table-column>
      <el-table-column label="审核" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
          v-show="scope.row.reviewStatus==='0'"
            size="mini"
            type="text"
            @click="handleReviewPass(scope.row)"
          >通过</el-button>
          <el-button
            v-show="scope.row.reviewStatus==='0'"
            size="mini"
            type="text"
            @click="handleReviewRefuse(scope.row)"
          >不通过</el-button>
          <el-button
            v-show="scope.row.reviewStatus==='2'"
            size="mini"
            type="text"
            @click="handleRedistribute(scope.row)"
          >重新下发</el-button>
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

    <!-- 添加或修改整改结果审核对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="被检查单位" prop="chectedUnit">
          <el-input v-model="form.chectedUnit" placeholder="请输入被检查单位" />
        </el-form-item>
        <el-form-item label="场所" prop="placeId">
          <el-select v-model="form.placeId" placeholder="请选择场所">
            <el-option
              v-for="dict in dict.type.place_list"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        
        <el-form-item label="被检查单位负责人" prop="checkedUnitDirector">
          <el-input v-model="form.checkedUnitDirector" placeholder="请输入被检查单位负责人" />
        </el-form-item>
        <!-- <el-form-item label="被检查人账号" prop="checkedUnitDirectorAcccoutNumber">
          <el-input v-model="form.checkedUnitDirectorAcccoutNumber" placeholder="请输入被检查人账号" />
        </el-form-item> -->
        <el-form-item label="被检查人电话号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入被检查人电话号码" />
        </el-form-item>
        <el-form-item label="问题隐患" prop="checkSaftyDanger">
          <el-input v-model="form.checkSaftyDanger" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="整改措施" prop="rectifyMeasure">
          <el-input v-model="form.rectifyMeasure" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        
        <!-- <el-form-item label="整改通知书签字照片" prop="rectifyNoticePhoto"> -->
          <!-- <image-upload v-model="form.rectifyNoticePhoto"/> -->
         <el-form-item label="整改通知书" prop="rectifyNoticeSigned">
           <file-upload v-model="form.rectifyNoticeSigned"/>
        </el-form-item>
        <el-form-item label="整改结果现场照片(3-5张)" prop="rectifyResultSitePhoto">
          <image-upload v-model="form.rectifyResultSitePhoto"/>
        </el-form-item>
        <el-form-item label="检查日期" prop="checkDate">
          <el-date-picker clearable
            v-model="form.checkDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成日期" prop="finishDate">
          <el-date-picker clearable
            v-model="form.finishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择完成日期">
          </el-date-picker>
        </el-form-item>
         <el-form-item label="责任民警" prop="checkPolice">
          <el-input v-model="form.checkPolice" placeholder="请输入责任民警" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  <!-- </div> -->
  </table-panel>
</template>

<script>
import TablePanel from '@/components/TablePanel/index.vue'
import { listRectifyresultreview, getRectifyresultreview, delRectifyresultreview, addRectifyresultreview, updateRectifyresultreview } from "@/api/safecheck/rectifyresultreview";

export default {
  name: "Rectifyresultreview",
  dicts: ['place_list'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 整改结果审核表格数据
      rectifyresultreviewList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chectedUnit: null,
        checkDate: null,
        checkedUnitDirector: null,
        // checkedUnitDirectorAcccoutNumber: null,
        checkPolice: null,
        finishDate: null,
        phoneNumber:null,
        finishStatus:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        chectedUnit: [
          { required: true, message: "被检查单位不能为空", trigger: "blur" }
        ],
        placeId: [
          { required: true, message: "场所不能为空", trigger: "change" }
        ],
        checkDate: [
          { required: true, message: "检查日期不能为空", trigger: "blur" }
        ],
        checkedUnitDirector: [
          { required: true, message: "被检查单位负责人不能为空", trigger: "blur" }
        ],
        // checkedUnitDirectorAcccoutNumber: [
        //   { required: true, message: "被检查人账号不能为空", trigger: "blur" }
        // ],
        checkPolice: [
          { required: true, message: "责任民警不能为空", trigger: "blur" }
        ],
         checkSaftyDanger: [
          { required: true, message: "问题隐患不能为空", trigger: "blur" }
        ],
         phoneNumber: [
          { required: true, message: "被检查人电话号码不能为空", trigger: "blur" },
           {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        // checkSaftyDanger: [
        //   { required: true, message: "问题隐患不能为空", trigger: "blur" }
        // ],
        // rectifyMeasure: [
        //   { required: true, message: "整改措施不能为空", trigger: "blur" }
        // ],
        finishDate: [
          { required: true, message: "完成时间不能为空", trigger: "blur" }
        ],
        rectifyNoticeSigned: [
          { required: true, message: "整改通知书不能为空", trigger: "blur" }
        ],
        rectifyResultSitePhoto: [
          { required: true, message: "整改结果现场照片不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getCheckDate() {
      var now = new Date();
      var year = now.getFullYear(); //得到年份
      var month = now.getMonth(); //得到月份
      var date = now.getDate(); //得到日期
      var hour = " 00:00:00"; //默认时分秒 如果传给后台的格式为年月日时分秒，就需要加这个，如若不需要，此行可忽略
      month = month + 1;
      month = month.toString().padStart(2, "0");
      date = date.toString().padStart(2, "0");
      var defaultDate = `${year}-${month}-${date}`;//
      return defaultDate;
    },
    getFinishDate(){
      var now = new Date();
      var year = now.getFullYear(); //得到年份
      var month = now.getMonth(); //得到月份
      var date = now.getDate()+3; //得到日期
      var hour = " 00:00:00"; //默认时分秒 如果传给后台的格式为年月日时分秒，就需要加这个，如若不需要，此行可忽略
      month = month + 1;
      month = month.toString().padStart(2, "0");
      date = date.toString().padStart(2, "0");
      var defaultDate = `${year}-${month}-${date}`;//
      return defaultDate;
    },
        /**文档预览**/
    //我是在组件el-table里面绑定该方法 根据需求你们随意
    handlePreview(row) {
      window.open(process.env.VUE_APP_BASE_API+row)
      //  window.open(this.$store.state.settings.base_url+row)
    },
    /** 查询整改结果审核列表 */
    getList() {
      this.loading = true;
      listRectifyresultreview(this.queryParams).then(response => {
        this.rectifyresultreviewList = response.rows;
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
        chectedUnit: null,
        placeId: null,
        checkDate: null,
        checkedUnitDirector: null,
        // checkedUnitDirectorAcccoutNumber: null,
        checkPolice: null,
        checkSaftyDanger: null,
        rectifyMeasure: null,
        finishDate: null,
        rectifyNoticeSigned: null,
        rectifyResultSitePhoto: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.checkDate = this.getCheckDate();
      this.form.finishDate = this.getFinishDate();
      this.form.checkPolice =  this.$store.state.user.name;
      this.open = true;
      this.title = "添加整改结果审核";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRectifyresultreview(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改整改结果审核";
      });
    },
    /** 提交按钮 */
    submitForm() {
       if((!this.form.rectifyNoticeSigned) || this.form.rectifyNoticeSigned.split(",").length>1)
        alert("请上传一份文件！")
      else{
        if(!(this.form.rectifyResultSitePhoto)|| this.form.rectifyResultSitePhoto.split(",").length<3 || this.form.rectifyResultSitePhoto.split(",").length>5)
        alert("请上传3-5张照片！")
        else{
          this.$refs["form"].validate(valid => {
            if (valid) {
              if (this.form.id != null) {
                this.form.finishStatus='2'
                updateRectifyresultreview(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                });
              } else {
                addRectifyresultreview(this.form).then(response => {
                  this.form.finishStatus='2'
                  this.$modal.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });
              }
            }
          });
        }
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除整改结果审核编号为"' + ids + '"的数据项？').then(function() {
        return delRectifyresultreview(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('safecheck/rectifyresultreview/export', {
        ...this.queryParams
      }, `rectifyresultreview_${new Date().getTime()}.xlsx`)
    },
    handleReviewPass(row){
      row.reviewStatus = '1'
      updateRectifyresultreview(row)
    },
    handleReviewRefuse(row){
      row.reviewStatus = '2'
      updateRectifyresultreview(row)
    },
    handleRedistribute(row){
      row.finishStatus = '0'
      row.reviewStatus='0'
      updateRectifyresultreview(row)
      this.getList()
    }
  },
  components:{TablePanel}
};
</script>

<style scoped lang="scss">
.upload-file-uploader {
  margin-bottom: 5px;
}
.upload-file-list .el-upload-list__item {
  border: 1px solid #e4e7ed;
  line-height: 2;
  margin-bottom: 10px;
  position: relative;
}
.upload-file-list .ele-upload-list__item-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: inherit;
}
.ele-upload-list__item-content-action .el-link {
  margin-right: 10px;
}
</style>

