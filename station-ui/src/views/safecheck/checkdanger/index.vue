<template>
  <!-- <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px"> -->
  <table-panel :show-search="showSearch">
    <template #search-form>
      <el-form size="mini" :model="queryParams" ref="queryForm" label-width="100px" inline>
        <el-row>
          
          <el-col :span="6">    
            <el-form-item label="被检查单位" prop="chectedUnit">
              <el-input v-model="queryParams.chectedUnit" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="被检查单位负责人" prop="checkedUnitDirector">
              <el-input
                v-model="queryParams.checkedUnitDirector"
              />
            </el-form-item>
          </el-col>
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
          </el-col>
          <el-col :span="6">
            <el-form-item label="检查人员" prop="checkPerson">
              <el-input
                v-model="queryParams.checkPerson"
              />
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
          
        </el-row>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button size="mini" type="primary" @click="handleQuery">查询</el-button>
      <el-button size="mini" type="info"  @click="resetQuery">重置</el-button>
    </template>
          <!-- <el-col :span="6">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
         </el-col> -->
         <!-- </el-row>
      </el-form> -->
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
          v-hasPermi="['safecheck:checkplacedict:list']"
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
          v-hasPermi="['safecheck:checkdanger:edit']"
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
          v-hasPermi="['safecheck:checkdanger:remove']"
        >删除</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['safecheck:checkdanger:exportWord']"
        >导出</el-button>
      </el-col> -->
      <!-- <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading" :data="checkdangerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="序号" align="center" prop="id" /> -->
      <el-table-column label="被检查单位" align="center" prop="chectedUnit" />
       <el-table-column label="被检查单位负责人" align="center" prop="checkedUnitDirector" />
      <el-table-column label="场所" align="center" prop="placeId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.place_list" :value="scope.row.placeId"/>
        </template>
      </el-table-column>
     
     
       <el-table-column label="检查日期" align="center" prop="checkDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检查人员" align="center" prop="checkPerson" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['safecheck:checkdanger:edit']"
          >更新</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['safecheck:checkdanger:remove']"
          >删除</el-button> -->
          <el-button
          type="text"
          plain
          size="mini"
          @click="handleExport(scope.row.id)"
        >预览</el-button>
        <el-button
          type="text"
          plain
          size="mini"
          @click="uploadForm(scope.row,scope.index)"
        >上传</el-button>
        </template>
      </el-table-column>
      <el-table-column label="检查记录表签名文件" align="center" prop="checkResult" >
        <template slot-scope="scope">
          <div v-show=scope.row.checkResult>
            <a @click="handlePreview(scope.row.checkResult)" target="_blank" class="buttonText" style="color: #00afff;">查看</a>
          </div>
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

    <!-- 添加或修改安全隐患检查登记对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="被检查单位" prop="chectedUnit">
          <el-input v-model="form.chectedUnit" placeholder="请输入被检查单位" />
        </el-form-item>
        <el-form-item label="被检查单位负责人" prop="checkedUnitDirector">
          <el-input v-model="form.checkedUnitDirector" placeholder="请输入被检查单位负责人" />
        </el-form-item>
        <el-form-item label="场所" prop="placeId">
          <el-select v-model="placeValue" placeholder="请选择场所">
            <el-option
              v-for="dict in dict.type.place_list"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <div v-show="placeValue">
          <PlaceType :placeId="placeValue" :checkItemsChoice="checkItemsChoice"  ref="PlaceType"/>
        </div>
        
        <!-- <el-form-item v-show='form.placeId' label="检查项填写结果" prop="checkItemResults"> 
          <el-form-item v-show='form.placeId'>
          <template v-for="(item,index) in checkdangerList"> 
            <dict-tag :options="dict.type.check_items" :value="item ? item : [] " :key="index"/> 
            <span>{{checkdangerList[0].checkItemResults}}</span>
            <input type="radio" name="choice" value="yes" checked :key="index+1">是
            <input type="radio" name="choice" value="no" :key="index+2">否
            <br :key="index+item"/>
          </template> 
          
          <br/>
           <el-input v-model="form.checkItemResults" placeholder="请输入检查项填写结果" /> 
        </el-form-item>-->
       <el-form-item label="其他安全隐患" prop="otherSaftyDanger">
          <el-input v-model="form.otherSaftyDanger" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        
         <el-form-item label="检查日期" prop="checkDate">
          <el-date-picker clearable
            v-model="form.checkDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检查人员" prop="checkPerson">
          <el-input v-model="form.checkPerson" placeholder="请输入检查人员" />
        </el-form-item>
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


     <el-dialog title="检查记录表签名文件" :visible.sync="open1" width="700px" append-to-body>
      <el-form ref="form1" :model="form1" label-width="100px" size="mini">
        <el-row>
           <el-form-item label="检查记录表签名文件" prop="checkResult">
            <file-upload v-model="form1.checkResult"/>
          </el-form-item>
        </el-row>
         </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm1">确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>



  <!-- </div> -->
   </table-panel>
</template>

<script>
import TablePanel from '@/components/TablePanel/index.vue'
import PlaceType from "@/components/PlaceType";
import { listCheckdanger, getCheckdanger, delCheckdanger, addCheckdanger, updateCheckdanger,exportWord } from "@/api/safecheck/checkdanger";
import { saveAs } from 'file-saver'

export default {
  name: "Checkdanger",
  dicts: ['place_list'],
  data() {
    return {
      updateStates:0,
      isShow:false,
      placeValue:null,
      checkItemsChoice:[],
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
      // 安全隐患检查登记表格数据
      checkdangerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chectedUnit: null,
        placeId: null,
        checkDate: null,
        checkedUnitDirector: null,
        checkPerson: null,
      },
      // 表单参数
      form: {},
      form1:{},
      // 表单校验
      // checkItems:[1,2,3,4,5,6],
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
        checkPerson: [
          { required: true, message: "检查人员不能为空", trigger: "blur" }
        ],
        checkItemResults: [
          { required: true, message: "检查项填写结果不能为空", trigger: "blur" }
        ],
      }
    };
  },
  watch:{
    placeValue:{
      // immediate:true,
      handler(newvalue,oldvalue){
        this.form.placeId=newvalue;
        // console.log(newvalue,oldvalue)
        // this.$refs.PlaceType.$on('checkItemsResults',this.getcheckItemsResults)
        // this.isShow=true && this.placeValue!=''
          // console.log(this.$refs.PlaceType)
          this.$nextTick(()=>{
               this.$refs.PlaceType.$on('checkItemsResults',this.getcheckItemsResults)
          })
        // this.checkItemsChoice=this.checkdangerList[this.updateUnitId].checkItemResults
        // console.log(this.checkdangerList[this.updateUnitId].checkItemResults)
        // console.log(newvalue,oldvalue)
      }
    },
  },
  created() {
    this.getList();
  },
  mounted(){
   

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
    handlePreview(row) {
      window.open(process.env.VUE_APP_BASE_API+row)
      //  window.open(this.$store.state.settings.base_url+row)
    },
    getcheckItemsResults(PlaceTypeCheckItemsResults){
      // console.log(checkItemsResults)
      try{
         this.form.checkItemResults = PlaceTypeCheckItemsResults.join(",")
      }catch{}
      // this.form.checkItemResults = PlaceTypeCheckItemsResults.join(",")
    },
    /** 查询安全隐患检查登记列表 */
    getList() {
      this.loading = true;
      listCheckdanger(this.queryParams).then(response => {
        this.checkdangerList = response.rows;
        this.total = response.total;
        this.loading = false;
        // console.log(dict.type.place_list)
        // console.log(this.checkdangerList);
        // console.log(this.checkdangerList[0].checkItemResults.split(','))
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
      // this.placeValue=''
      
    },
    cancel1() {
      this.open1 = false;
      this.reset();
      // this.placeValue=''
      
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        chectedUnit: null,
        placeId: null,
        checkDate: null,
        checkedUnitDirector: null,
        checkPerson: null,
        checkItemResults: null,
        otherSaftyDanger: null,
      };
      this.resetForm("form");
      this.placeValue=null
      this.checkItemsChoice=[]
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
      this.form.checkPerson =  this.$store.state.user.name;
      this.open = true;
      this.title = "添加安全隐患检查登记";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const id = row.id || this.ids
      // this.updateUnitId =  row.id || this.ids
      // const id = this.updateUnitId
      // this.checkItemsChoice=this.checkdangerList[id].checkItemResults
      // console.log(this.checkdangerList[id])
      getCheckdanger(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改安全隐患检查登记";
        // console.log(this.form.checkItemResults.split(','))
        this.placeValue=this.form.placeId
        this.checkItemsChoice=this.form.checkItemResults.split(',')
        this.isShow=true
      });
      // this.reset();
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCheckdanger(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.isShow=false
            });
          } else {
            addCheckdanger(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.isShow=false
            });
          }
        }
      });
      // this.reset();
    },
    /** 提交按钮 */
    submitForm1() {
      if((!this.form1.checkResult) || this.form1.checkResult.split(",").length>1)
        alert("文件上传错误")
      else{
        this.$refs["form1"].validate(valid => {
          if (valid) {
            if (this.form1.id != null) {
              updateCheckdanger(this.form1).then(response => {
                this.$modal.msgSuccess("上传成功");
                this.open1 = false;
                // this.getList();
                // this.isShow=false
              })
            }
          }
        })
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除安全隐患检查登记编号为"' + ids + '"的数据项？').then(function() {
        return delCheckdanger(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport(id) {
      // this.download('safecheck/checkdanger/export', {
      //   ...this.queryParams
      // }, `checkdanger_${new Date().getTime()}.xlsx`)

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
        // console.log(this.$store.state.settings.base_url)
        window.open(process.env.VUE_APP_BASE_API+"/profile/upload/"+response.msg)
        //  window.open(this.$store.state.settings.base_url+"/profile/upload/"+response.msg)
        // window.open("http://10.181.155.115:8080/profile/upload/"+response.msg)
        }).catch(() => {});
    //     const isBlob = blobValidate(data);
    // if (isBlob) {
    //   const blob = new Blob([data])
    //   saveAs(blob, filename)
    // } else {
    //   const resText = data.text();
    //   const rspObj = JSON.parse(resText);
    //   const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
    //   Message.error(errMsg);
    // }
    // downloadLoadingInstance.close();
    //     // this.download('',response.msg);
    //     console.log(response)
    //     this.exportLoading = false;
    //   }).catch(() => {});
    // window.open("http://10.181.155.115:8080/profile/upload/未命名1.pdf")
    },

    uploadForm(row) {
      this.open1 = true;
      this.form1 = row;
      // console.log(this.form1)
      // console.log("qqqqqqq   "+row)
      // this.form1 = Array(this.form1.checkItems.split(","))
    }

  },
  components:{PlaceType,TablePanel}
};
</script>
