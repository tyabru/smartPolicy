<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="场所行业名称" prop="placeName"> -->
        <el-select v-model="queryParams.placeName" placeholder="请选择场所行业名称" clearable>
          <el-option
            v-for="dict in dict.type.place_list"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      <!-- </el-form-item> -->
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
          v-hasPermi="['safecheck:checkplacedict:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['safecheck:checkplacedict:edit']"
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
          v-hasPermi="['safecheck:checkplacedict:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['safecheck:checkplacedict:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="checkplacedictList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="场所行业名称" align="center" prop="placeName" >
        <template slot-scope="scope">
         <dict-tag :options="dict.type.place_list" :value="scope.row.placeName"/>
        </template>
      </el-table-column>
      <el-table-column label="检查项列表" align="center" prop="checkItems">
        <!--  <template slot-scope="scope">
         <dict-tag :options="dict.type.check_items" :value="scope.row.checkItems ? scope.row.checkItems.split(',') : []"/>
        </template> -->
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row,scope.index)"
          >详细</el-button>
        </template>

         <!-- <template slot-scope="scope">
          <router-link :to="'/safecheck/checkplacedictcheckitems' + scope.row.Id" class="link-type">
            <span>查看</span>
          </router-link> -->
           <!-- <template slot-scope="scope">
          <router-link :to="'/safecheck/checkplacedictcheckitems/index/' + scope.row.id" class="link-type">
            <span>查看</span>
          </router-link> -->
      
          
   
      </el-table-column> 
      <el-table-column label="整改通知书模板参数" align="center" prop="rectifyNoticeTemplateParams" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['safecheck:checkplacedict:edit']"
          >更新</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['safecheck:checkplacedict:remove']"
          >删除</el-button>
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

    <!-- 添加或修改安全隐患检查场所字典对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <!-- <el-form-item label="场所行业名称" prop="placeName">
          <el-input v-model="form.placeName" placeholder="请输入场所行业名称" />
        </el-form-item> -->
        <el-form-item label="场所行业名称" prop="placeName">
          <el-select v-model="form.placeName" placeholder="请选择场所行业名称">
            <el-option
              v-for="dict in dict.type.place_list"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="检查项列表" prop="checkItems">
          <el-checkbox-group v-model="form.checkItems">
            <el-checkbox
              v-for="dict in dict.type.check_items"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="整改通知书模板参数" prop="rectifyNoticeTemplateParams">
          <el-input v-model="form.rectifyNoticeTemplateParams" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

     <el-dialog title="场所检查项列表" :visible.sync="open1" width="700px" append-to-body>
      <el-form ref="form1" :model="form1" label-width="100px" size="mini">
        <el-row>
          <!-- <el-col :span="12">
            <el-form-item label="操作模块：">{{ form.title }} / {{ typeFormat(form) }}</el-form-item>
            <el-form-item
              label="登录信息："
            >{{ form.operName }} / {{ form.operIp }} / {{ form.operLocation }}</el-form-item>
          </el-col> -->
        
       
          <!-- <template v-for="(item,index) in scope.row.checkItems.split(',') "> -->
          <template v-for="(item,index) in form1.checkItems.split(',')">
            <dict-tag :options="dict.type.check_items" :value="item ? item : [] " :key="index"/>
            <br :key="index+item"/>
          </template>
         
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open1 = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>
  </div>
</template>

<script>
import { listCheckplacedict, getCheckplacedict, delCheckplacedict, addCheckplacedict, updateCheckplacedict } from "@/api/safecheck/checkplacedict";

export default {
  name: "Checkplacedict",
  dicts: ['check_items','place_list'],
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
      // 安全隐患检查场所字典表格数据
      checkplacedictList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        placeName: null,
        rectifyNoticeTemplateParams: null
      },
      // 表单参数
      form: {},
      form1:{
        checkItems:"0,1,2,3,4,5,6,7,8"
      },
      // 表单校验
      rules: {
        placeName: [
          { required: true, message: "场所行业名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    
  },
  methods: {
    /** 查询安全隐患检查场所字典列表 */
    getList() {
      this.loading = true;
      listCheckplacedict(this.queryParams).then(response => {
        this.checkplacedictList = response.rows;
        this.total = response.total;
        this.loading = false;
        // console.log(dict.type.place_lis)
        // console.log("ooooooo   "+this.checkplacedictList)
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
        checkItems: [],
        rectifyNoticeTemplateParams: null
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
      // console.log("qqqqqqq   "+row)
      // this.form1 = Array(this.form1.checkItems.split(","))
    }



    
  }
};
</script>