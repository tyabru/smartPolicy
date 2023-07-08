<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="被检查单位" prop="chectedUnit">
        <el-input
          v-model="queryParams.chectedUnit"
          placeholder="请输入被检查单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查日期" prop="checkDate">
        <el-date-picker clearable
          v-model="queryParams.checkDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择检查日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="被检查单位负责人" prop="checkedUnitDirector">
        <el-input
          v-model="queryParams.checkedUnitDirector"
          placeholder="请输入被检查单位负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被检查人账号" prop="checkedUnitDirectorAcccoutNumber">
        <el-input
          v-model="queryParams.checkedUnitDirectorAcccoutNumber"
          placeholder="请输入被检查人账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="责任民警" prop="checkPolice">
        <el-input
          v-model="queryParams.checkPolice"
          placeholder="请输入责任民警"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="完成时间" prop="finishDate">
        <el-date-picker clearable
          v-model="queryParams.finishDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择完成时间">
        </el-date-picker>
      </el-form-item>
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
    </el-row>

    <el-table v-loading="loading" :data="rectifyresultreviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="被检查单位" align="center" prop="chectedUnit" />
      <el-table-column label="检查日期" align="center" prop="checkDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="被检查单位负责人" align="center" prop="checkedUnitDirector" />
      <el-table-column label="被检查人账号" align="center" prop="checkedUnitDirectorAcccoutNumber" />
      <el-table-column label="责任民警" align="center" prop="checkPolice" />
      <el-table-column label="问题隐患" align="center" prop="checkSaftyDanger" />
      <el-table-column label="整改措施" align="center" prop="rectifyMeasure" />
      <el-table-column label="完成时间" align="center" prop="finishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="整改通知书签字照片" align="center" prop="rectifyNoticePhoto" width="100"> -->
        <!-- <template slot-scope="scope">
          <image-preview :src="scope.row.rectifyNoticePhoto" :width="50" :height="50"/>
        </template> 
      </el-table-column>-->
      <el-table-column label="整改通知书" align="center" prop="rectifyNoticePhoto" width="100">
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
      </el-table-column>
      <el-table-column label="整改结果现场照片" align="center" prop="rectifyResultSitePhoto" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.rectifyResultSitePhoto" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="审核" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="button"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['safecheck:rectifyresultreview:edit']"
          >通过</el-button>
          <el-button
            size="mini"
            type="button"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['safecheck:rectifyresultreview:remove']"
          >不通过</el-button>
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
        <el-form-item label="检查日期" prop="checkDate">
          <el-date-picker clearable
            v-model="form.checkDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="被检查单位负责人" prop="checkedUnitDirector">
          <el-input v-model="form.checkedUnitDirector" placeholder="请输入被检查单位负责人" />
        </el-form-item>
        <el-form-item label="被检查人账号" prop="checkedUnitDirectorAcccoutNumber">
          <el-input v-model="form.checkedUnitDirectorAcccoutNumber" placeholder="请输入被检查人账号" />
        </el-form-item>
        <el-form-item label="责任民警" prop="checkPolice">
          <el-input v-model="form.checkPolice" placeholder="请输入责任民警" />
        </el-form-item>
        <el-form-item label="问题隐患" prop="checkSaftyDanger">
          <el-input v-model="form.checkSaftyDanger" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="整改措施" prop="rectifyMeasure">
          <el-input v-model="form.rectifyMeasure" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="完成时间" prop="finishDate">
          <el-date-picker clearable
            v-model="form.finishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择完成时间">
          </el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="整改通知书签字照片" prop="rectifyNoticePhoto"> -->
          <!-- <image-upload v-model="form.rectifyNoticePhoto"/> -->
         <el-form-item label="整改通知书" prop="rectifyNoticePhoto">
           <file-upload v-model="form.rectifyNoticePhoto"/>
        </el-form-item>
        <el-form-item label="整改结果现场照片" prop="rectifyResultSitePhoto">
          <image-upload v-model="form.rectifyResultSitePhoto"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
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
        checkedUnitDirectorAcccoutNumber: null,
        checkPolice: null,
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
        checkedUnitDirectorAcccoutNumber: [
          { required: true, message: "被检查人账号不能为空", trigger: "blur" }
        ],
        checkPolice: [
          { required: true, message: "责任民警不能为空", trigger: "blur" }
        ],
        checkSaftyDanger: [
          { required: true, message: "问题隐患不能为空", trigger: "blur" }
        ],
        rectifyMeasure: [
          { required: true, message: "整改措施不能为空", trigger: "blur" }
        ],
        finishDate: [
          { required: true, message: "完成时间不能为空", trigger: "blur" }
        ],
        rectifyNoticePhoto: [
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
        checkedUnitDirectorAcccoutNumber: null,
        checkPolice: null,
        checkSaftyDanger: null,
        rectifyMeasure: null,
        finishDate: null,
        rectifyNoticePhoto: null,
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
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRectifyresultreview(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRectifyresultreview(this.form).then(response => {
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
    }
  }
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

