<template>
  <!-- <el-table v-loading="loading" :data="checkplacedictList">
    <el-table-column  align="center" :prop="checkItems"> -->
      <el-form ref="form1" :model="form1" label-width="100px" size="mini">
          <!-- <el-col :span="12">
            <el-form-item label="操作模块：">{{ form.title }} / {{ typeFormat(form) }}</el-form-item>
            <el-form-item
              label="登录信息："
            >{{ form.operName }} / {{ form.operIp }} / {{ form.operLocation }}</el-form-item>
          </el-col> -->
        
        
          <!-- <template v-for="(item,index) in scope.row.checkItems.split(',') "> -->
          <!-- <el-radio-group v-model="form1.checkItemResults">
           <el-radio  v-for="dict in dict.type.check_items"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}</el-radio>
         </el-radio-group> -->
         <div v-if="placeId">
         <div style="height: 300px; overflow-y:scroll;" :key="nanoid" v-if="form1.checkItems">
          <template v-for="(item,index) in form1.checkItems.split(',')" >
            <div :key="item.nanoid" style="padding:5px 5px 5px 40px;width:90%;border: 1px solid #DCDFE6;border-radius: 3px;">
            <dict-tag :options="dict.type.check_items" :value="item ? item : [] " :key="item.nanoid" style="" />
            
               <input type="radio" :name="'choice'+index" value="1" v-model="checkItemsChoice[index]" :key="item.nanoid">是
              <input type="radio" :name="'choice'+index" value="0" v-model="checkItemsChoice[index]" :key="item.nanoid ">否          
            </div>
            <!-- <br :key="index+item"/> -->
        
          </template>
            </div>
            </div>
      </el-form>
    <!-- </el-table-column>
  </el-table> -->
</template>

<script>
import { nanoid } from 'nanoid'
import { listCheckplacedict, getCheckplacedict, delCheckplacedict, addCheckplacedict, updateCheckplacedict } from "@/api/safecheck/checkplacedict";

export default {
  name: "PlaceType",
  dicts: ['check_items'],
  data() {
    return {
      nanoid: nanoid(),
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
      // 安全隐患检查场所字典表格数据
      checkplacedictList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10000,
        placeName: null,
      },
      // 表单参数
      form: {},
      form1:{
        // checkItems:null
      },
      // 表单校验
      rules: {
        placeName: [
          { required: true, message: "场所行业名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  props:['placeId','checkItemsChoice'],
  created() {
    this.getList();
    // console.log(this.checkItemsChoice)
    // this.form1=this.checkplacedictList[this.placeId-1]
    // console.log(this.checkplacedictList)
  },
   watch:{
    placeId:{
      handler(newvalue,oldvalue){
        // this.getList();
        try{
          if(newvalue)
          // console.log(this.checkplacedictList)
           this.form1=this.checkplacedictList[this.placeId-1]
        }catch{}
        // this.form1=this.checkplacedictList[this.placeId]
        // console.log(this.checkItemsChoice)
        // console.log(this.form1)
      }
    },
     checkItemsChoice:{
      handler(newvalue,oldvalue){
        // this.getList();
        try{
          this.$emit('checkItemsResults',this.checkItemsChoice)
        }catch{}
        // this.$emit('checkItemsResults',this.checkItemsChoice)
        // console.log(this.checkItemsChoice)
        // console.log(this.form1)
      }
    }
  },
  mounted(){
    //  this.form1=this.checkplacedictList[this.placeId-1]
  //   this.form1 = this.checkplacedictList;
  //   console.log("ttttt    "+this.checkplacedictList)
  //   console.log("我是placetype"+this.placeId)
  },
  methods: {
    /** 查询安全隐患检查场所字典列表 */
    getList() {
      this.loading = true;
      listCheckplacedict(this.queryParams).then(response => {
        this.checkplacedictList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.form1=this.checkplacedictList[this.placeId-1]
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
        placeName: null,
        checkItems: []
      };
      this.resetForm("form");
      this.checkItemsChoice=[];
      this.placeId=null
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
      this.open = true;
      this.title = "添加安全隐患检查场所字典";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCheckplacedict(id).then(response => {
        this.form = response.data;
        this.form.checkItems = this.form.checkItems.split(",");
        this.open = true;
        this.title = "修改安全隐患检查场所字典";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.checkItems = this.form.checkItems.join(",");
          if (this.form.id != null) {
            updateCheckplacedict(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCheckplacedict(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除安全隐患检查场所字典编号为"' + ids + '"的数据项？').then(function() {
        return delCheckplacedict(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('safecheck/checkplacedict/export', {
        ...this.queryParams
      }, `checkplacedict_${new Date().getTime()}.xlsx`)
    },



     /** 详细按钮操作 */
    handleView(row) {
      this.open1 = true;
      this.form1 = row;
      // this.form1 = Array(this.form1.checkItems.split(","))
    }



    
  }
};
</script>