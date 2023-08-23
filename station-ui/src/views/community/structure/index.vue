<template>
  <table-panel :show-search="showSearch" :loading="loading">
    <template #search-form>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="小区编码" prop="communityCode">
          <el-input v-model="queryParams.communityCode" placeholder="请输入小区编码" clearable/>
        </el-form-item>
        <el-form-item label="小区名称" prop="communityName">
          <el-input v-model="queryParams.communityName" placeholder="请输入小区名称" clearable/>
        </el-form-item>
        <el-form-item label="地址编码" prop="metaAddrId">
          <el-input v-model="queryParams.metaAddrId" placeholder="请输入地址编码" clearable/>
        </el-form-item>
        <el-form-item label="地址全称" prop="fullAddress">
          <el-input v-model="queryParams.fullAddress" placeholder="请输入地址全称" clearable/>
        </el-form-item>
        <el-form-item label="地址级别" prop="metaLevel">
          <el-select v-model="queryParams.metaLevel" clearable placeholder="请输入地址级别">
            <el-option v-for="item in dict.type['structure_level']" :key="item.value" :value="item.value" :label="item.label" />
          </el-select>
        </el-form-item>
        <el-form-item label="所属区域" prop="region">
          <el-input v-model="queryParams.region" placeholder="请输入所属区域" clearable/>
        </el-form-item>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button type="primary" icon="el-icon-search" size="mini" @click="queryChanged">搜索</el-button>
      <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
    </template>
    <template #btn>
        <el-button type="primary" plain icon="el-icon-plus"
          size="mini" @click="handleAdd" v-hasPermi="['community:structure:add']" >新增</el-button>
        <el-button type="success" plain icon="el-icon-edit" size="mini"
          :disabled="single" @click="handleUpdate" v-hasPermi="['community:structure:edit']">修改</el-button>
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
          @click="handleDelete" v-hasPermi="['community:structure:remove']">删除</el-button>
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['community:structure:export']">导出</el-button>
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleImport"
                 v-hasPermi="['community:structure:export']">批量导入</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="queryChanged"></right-toolbar>
    </template>
    <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="小区名称" align="center" prop="communityName" />
      <el-table-column label="地址编码" align="center" prop="metaAddrId" />
      <el-table-column label="地址全称" align="left" prop="fullAddress" width="240px" />
      <el-table-column label="地址级别" align="center" prop="metaLevel">
        <template v-slot="{ row }">
          <dict-tag :options="dict.type['structure_level']" :value="row.metaLevel" />
        </template>
      </el-table-column>
      <el-table-column label="地址简称" align="left" prop="shortName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['community:structure:query']">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['community:structure:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['community:structure:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="queryChanged"/>

    <!-- 添加或修改小区房屋结构和地址信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" @close="reset" width="50vw" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :loading="dialogLoading" :model="form" :rules="rules" label-width="110px" :disabled="disabled">
        <el-row>
          <el-col :span="24">
            <el-form-item label="地址全称" prop="fullAddress">
              <el-input v-model="form.fullAddress" @input="changeAddress" placeholder="请输入地址全称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属小区" prop="communityId">
              <se-community  ref="form-community" v-model="form.communityId" @change="communityChange" placeholder="请输入小区外键" class="width-100Rate" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属派出所" prop="pcsId">
              <el-input :value="form.pcsName" readonly placeholder="请输入所属派出所" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址编码" prop="metaAddrId">
              <el-input v-model="form.metaAddrId" placeholder="请输入小区地址编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址简称" prop="shortName">
              <el-input v-model="form.shortName" placeholder="请输入地址简称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址级别" prop="metaLevel">
              <el-select v-model="form.metaLevel" class="width-100Rate" placeholder="请输入地址级别">
                <el-option v-for="item in dict.type['structure_level']"
                           :key="item.value" :value="Number(item.value)" :label="item.label" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="楼栋" prop="dong">
              <el-input v-model="form.dong" placeholder="请输入地址楼栋" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单元" prop="unit">
              <el-input v-model="form.unit" placeholder="请输入地址单元" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="楼层" prop="ceng">
              <el-input v-model="form.ceng" placeholder="请输入地址楼层" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="门牌" prop="room">
              <el-input v-model="form.room" placeholder="请输入地址门牌号" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-show="isDisplay" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="importDialog" title="标准地址导入" @close="cancel" :close-on-click-modal="false">
      <file-upload v-model="fileUrl" :limit="1" base-dir="temp-upload"
                   drag :file-size="30" :file-type="['xls', 'xlsx', 'csv']" />
      <div style="background-color: #e8e8e8; margin-top: 20px; border-radius: 4px; padding: 10px">
        导入文件类型为Excel表格或CSV文件，文档导入的Excel模板可以
        <el-button type="text" @click="downloadExample" style="text-decoration: underline">点此下载</el-button>。列标题不可更改以免导入失败。
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :disabled="btnVisible" :loading="btnVisible" @click="importForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </table-panel>
</template>

<script>
import {
  listStructure,
  getStructure,
  delStructure,
  addStructure,
  updateStructure,
  downloadExample, importFormExcel
} from '@/api/community/structure'
import TablePanel from '@/components/TablePanel/index.vue'
import tableListMixins from '@/mixins/tableListMixins.js'
import { queryBelongDeptByTypeAndId } from '@/api/system/dept'
import { getCommunity, listCommunity } from '@/api/community/community'
import { matchFullAddress } from '@/views/community/structure/js'
import * as _ from 'lodash'
import { deleteFileByResource } from '@/api/tool/file'
export default {
  name: "Structure",
  dicts: ['structure_level'],
  components: { TablePanel },
  mixins: [ tableListMixins ],
  data() {
    const validUniqueMetaAddrId = async (rules, value, callback) => {
      if(!value || value === '') {
        callback('地址编码是唯一的值且不能为空！')
      } else {
        const response = await listStructure({ metaAddrId: value });
        if(response.code === 200) {
          if(response.rows && response.rows.length > 0) {
            if(!this.form.id || value !== response.rows[0].metaAddrId) {
              callback('地址编码在数据库中已存在。')
            }
          }
        }
        callback()
      }
    }
    return {
      //是否显示
      isDisplay: false,
      //是否禁用修改
      disabled: true,
      importDialog: false,
      // 选中数组
      ids: [],
      // 非多个禁用
      multiple: true,
      // 弹出层标题
      title: "",
      fileUrl: null,
      // 是否显示弹出层
      open: false,
      dialogLoading: false,
      // 表单参数
      form: {},
      parentList: [],
      // 表单校验
      rules: {
        communityId: [
          { required: true, message: "小区外键不能为空", trigger: "blur" }
        ],
        metaAddrId: [
          { validator: validUniqueMetaAddrId, trigger: "blur" }
        ],
        fullAddress: [
          { required: true, message: "地址全称不能为空", trigger: "blur" }
        ],
        metaLevel: [
          { required: true, message: "地址级别不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.initTableData(listStructure);
  },
  methods: {
    // 取消按钮
    cancel() {
      this.importDialog = false;
      this.open = false;
      this.reset();
      if(this.fileUrl) {
        deleteFileByResource(this.fileUrl);
      }
    },
    // 表单重置
    reset() {
      this.form = {};
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryChanged();
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
    changeAddress: _.debounce(function(item) {
      matchFullAddress(item).then(arr => {
        if(arr && arr.length > 3) {
          for(let i = arr.length;i > 1; i--) {
            if(arr[i-1] && arr[i-1].length > 0) {
              this.form.metaLevel = i - 1 + 10
              break
            }
          }
          this.$set(this.form, 'shortName', arr[0])
          this.$set(this.form, 'dong', arr[1])
          this.$set(this.form, 'unit', arr[2])
          this.$set(this.form, 'ceng', arr[3])
          this.$set(this.form, 'room', arr[4])
        }
      });
    }, 400),
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.disabled = false;
      this.isDisplay = true;
      this.title = "添加小区房屋结构和地址信息";
    },
    /** 查看按钮操作 */
    handleView(row) {
      this.reset();
      const id = row.id || this.ids
      getStructure(id).then(response => {
        this.open = true;
        this.title = "查看小区房屋结构和地址信息";
        this.form = response.data;
        this.dialogLoading = true
        this.disabled = true;
        this.isDisplay = false;
        listCommunity({ code: this.form.communityCode }).then(({ code, rows }) => {
          if(code === 200 && rows && rows.length > 0) {
            const data = rows[0]
            this.form.communityId = data.id
            this.form.pcsId = data.pcsId
            this.form.pcsName = data.pcsName
          }
        }).finally(() => { this.dialogLoading = false })
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStructure(id).then(response => {
        this.open = true;
        this.title = "修改小区房屋结构和地址信息";
        this.form = response.data;
        this.dialogLoading = true;
        this.disabled = false;
        this.isDisplay = true;
        listCommunity({ code: this.form.communityCode }).then(({ code, rows }) => {
          if(code === 200 && rows && rows.length > 0) {
            const data = rows[0]
            this.form.communityId = data.id
            this.form.pcsId = data.pcsId
            this.form.pcsName = data.pcsName
            // this.form.region = data.communityObj?.deptId
            // this.form.regionName = data.communityObj?.deptName
          }
        }).finally(() => { this.dialogLoading = false })
      });
    },
    communityChange(key, item) {
      this.form.communityCode = item.code
      this.form.communityName = item.name
      this.form.pcsId = item.pcsId
      this.form.pcsName = item.pcsName
      this.form.region = item.communityObj?.deptId
      this.form.regionName = item.communityObj?.deptName
    },
    handleImport() {
      this.importDialog = true
      if(this.fileUrl) {
        deleteFileByResource(this.fileUrl)
        this.fileUrl = null
      }
    },
    importForm() {
      if(this.fileUrl) {
        if(this.btnVisible) {
          return;
        }
        this.btnVisible = true
        importFormExcel(this.fileUrl).then(response => {
          this.$message.success(response.msg);
          this.cancel()
        }).finally(() => {
          this.btnVisible = false
        })
      } else {
        this.$message.success("还未上传需要导入的文件！");
      }

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStructure(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.queryChanged();
            });
          } else {
            addStructure(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.queryChanged();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除小区房屋结构和地址编号为"' + row.metaAddrId + '"的数据项？').then(function() {
        return delStructure(ids);
      }).then(() => {
        this.queryChanged();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('community/structure/export', {
        ...this.queryParams
      }, `structure_${new Date().getTime()}.xlsx`)
    },
    downloadExample() {
      downloadExample()
    },
  }
};
</script>
