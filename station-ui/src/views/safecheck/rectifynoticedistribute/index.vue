<template>
  <!-- <div class="app-container"> -->
  <table-panel :show-search="showSearch">
    <template #search-form>
      <el-form size="mini" :model="queryParams" ref="queryForm" label-width="100px" inline>
        <el-row>
          <el-col :span="6">
            <el-form-item label="被检查单位" prop="chectedUnit">
              <el-input v-model="queryParams.chectedUnit"/>
            </el-form-item>
          </el-col>
           <el-col :span="6">
            <el-form-item label="被检查单位负责人" prop="checkedUnitDirector">
              <el-input v-model="queryParams.checkedUnitDirector"/>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="6">
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
          <el-col :span="6">
            <el-form-item label="责任民警" prop="checkPolice">
              <el-input v-model="queryParams.checkPolice"/>
            </el-form-item>
          </el-col>
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
            <el-form-item label="完成时间" prop="finishDate">
              <el-date-picker clearable
                v-model="queryParams.finishDate"
                type="date"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col> -->

           <el-col :span="6">
            <el-form-item label="完成状态" prop="finishStatus">
              <el-select v-model="queryParams.finishStatus">
                <el-option :value="0" label="待下发" />
                <el-option :value="1" label="已下发" />
                <el-option :value="2" label="已提交" />
                <el-option :value="3" label="未提交" />
              </el-select>
            </el-form-item>
          </el-col>
          
         
          
        </el-row>
      </el-form>
    </template>
      <!-- <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <template #search-form-btn>
      <el-button size="mini" type="primary" @click="handleQuery">查询</el-button>
      <el-button size="mini" type="info"  @click="resetQuery">重置</el-button>
    </template>


     <template #btn>
      <el-button size="mini" type="primary" @click="handleAdd">新增</el-button>
      <el-button size="mini" type="success"  :disabled="single" @click="handleUpdate">修改</el-button>
      <el-button size="mini" type="danger" :disabled="multiple" @click="handleDelete">删除</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </template>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['safecheck:rectifynoticedistribute:add']"
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
          v-hasPermi="['safecheck:rectifynoticedistribute:edit']"
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
          v-hasPermi="['safecheck:rectifynoticedistribute:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['safecheck:rectifynoticedistribute:export']"
        >导出</el-button>
      </el-col> 
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading" :data="rectifynoticedistributeList" @selection-change="handleSelectionChange">
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
      <el-table-column label="完成状态" align="center" prop="finishStatus"> 
        <template scope="scope">
          <span v-if="scope.row.finishStatus==='0'">待下发</span>
          <span v-if="scope.row.finishStatus==='1'">已下发</span>
          <span v-if="scope.row.finishStatus==='2'">已提交</span>
          <span v-if="scope.row.finishStatus==='3'">未提交</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_common_status" :value="scope.row.status"/>
        </template>
      </el-table-column> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['safecheck:rectifynoticedistribute:edit']"
          >更新</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['safecheck:rectifynoticedistribute:remove']"
          >删除</el-button> -->
            <el-button
            type="text"
            plain
            size="mini"
            @click="handleExport(scope.row.id)"
          >预览</el-button>
            <el-button
            v-show="scope.row.finishStatus==='0' || scope.row.finishStatus==='3'"
            type="text"
            plain
            size="mini"
            @click="handledDistribute(scope.row)"
          >下发</el-button>
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

    <!-- 添加或修改整改通知书下发对话框 -->
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
          <el-input v-model="form.checkedUnitDirectorAcccoutNumber" placeholder="请输入被检查人账号"/>
        </el-form-item> -->
         <el-form-item label="被检查人电话号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入被检查人电话号码"  maxlength="11"/>
        </el-form-item>
        
        <el-form-item label="问题隐患" prop="checkSaftyDanger">
          <el-input v-model="form.checkSaftyDanger" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="整改措施" prop="rectifyMeasure">
          <el-input v-model="form.rectifyMeasure" type="textarea" placeholder="请输入内容" />
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
import { listRectifynoticedistribute, getRectifynoticedistribute, delRectifynoticedistribute, addRectifynoticedistribute, updateRectifynoticedistribute ,exportWord} from "@/api/safecheck/rectifynoticedistribute";

export default {
  name: "Rectifynoticedistribute",
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
      // 整改通知书下发表格数据
      rectifynoticedistributeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chectedUnit: null,
        placeId: null,
        checkDate: null,
        checkedUnitDirector: null,
        // checkedUnitDirectorAcccoutNumber: null,
        phoneNumber: null,
        checkPolice: null,
        checkSaftyDanger: null,
        rectifyMeasure: null,
        finishDate: null,
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
        checkPolice: [
          { required: true, message: "责任民警不能为空", trigger: "blur" }
        ],
        finishDate: [
          { required: true, message: "完成时间不能为空", trigger: "blur" }
        ],
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

    /** 查询整改通知书下发列表 */
    getList() {
      this.loading = true;
      listRectifynoticedistribute(this.queryParams).then(response => {
        this.rectifynoticedistributeList = response.rows;
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
        phoneNumber: null,
        checkPolice: null,
        checkSaftyDanger: null,
        rectifyMeasure: null,
        finishDate: null,
        finishStatus: null,
        noticeStatus: null
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
      this.title = "添加整改通知书下发";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRectifynoticedistribute(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改整改通知书下发";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRectifynoticedistribute(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRectifynoticedistribute(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除整改通知书下发编号为"' + ids + '"的数据项？').then(function() {
        return delRectifynoticedistribute(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport(id) {
      // this.download('safecheck/rectifynoticedistribute/export', {
      //   ...this.queryParams
      // }, `rectifynoticedistribute_${new Date().getTime()}.xlsx`)
    
      const queryParams = {
        id:id
      }
      this.$confirm('是否确认跳转至新的页面进行预览？', {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        // console.log(this.checkdangerList)
        return exportWord(queryParams);
      }).then(response => {
        window.open(process.env.VUE_APP_BASE_API+"/profile/upload/"+response.msg)
        // window.open(this.$store.state.settings.base_url+"/profile/upload/"+response.msg)
        }).catch(() => {});
    },
    handledDistribute(row){
      row.finishStatus = '1';
      updateCheckdanger(row).then(response => {
        // this.$modal.msgSuccess("上传成功");
        // this.open1 = false;
        // this.getList();
        // this.isShow=false
      })
    }
  },
  components:{TablePanel}
};
</script>

<style scoped>
  .noticeStatus0{
    color: white;
    font: 13px Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
    background-color: green;
    border:2px solid green;
    margin: 5px 5px;
    padding: 4px 4px;
  }
  .noticeStatus1{
    color: white;
    font: 13px Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
    background-color: red;
    border:2px solid red;
    margin: 5px 5px;
    padding: 4px 4px;
  }
</style>
