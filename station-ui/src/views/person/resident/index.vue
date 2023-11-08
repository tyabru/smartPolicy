<template>
  <table-panel :show-search="showSearch">
    <template #search-form>
      <el-form ref="queryForm" size="mini" :model="queryParams" label-width="80px" inline>
        <el-row>
          <el-col :span="6">
            <el-form-item label="所属部门">
              <el-input v-model="queryParams.dept"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="所属小区">
              <el-input v-model="queryParams.communityId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="姓名">
              <el-input v-model="queryParams.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="身份证号">
              <el-input v-model="queryParams.certNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="联系电话">
              <el-input v-model="queryParams.phone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="年龄范围">
              <el-input v-model="queryParams.age"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="重点人员">
              <el-select v-model="queryParams.attentionLevel" placeholder="请选择关注的级别">
                <el-option v-for="dict in dict.type.important_level" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </template>
    <template #search-form-btn>
      <el-button size="mini" type="primary" @click="queryChanged">查询</el-button>
      <el-button size="mini" type="info" @click="resetQuery">重置</el-button>
    </template>
    <template #btn>
      <el-button type="primary" plain icon="el-icon-plus"
        size="mini" @click="openEditDialog" v-hasPermi="['person:resident:add']">新增</el-button>
      <el-button type="warning" plain icon="el-icon-download" size="mini"
        @click="handleExport" v-hasPermi="['person:resident:export']">导出</el-button>
      <right-toolbar style="padding-right: 14px;" :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </template>
    <el-table :data="tableData" emptyText="暂无数据">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="name" label="姓名" align="center"></el-table-column>
      <el-table-column prop="certNo" label="身份证号码" align="center"></el-table-column>
      <el-table-column prop="phone" label="联系电话" align="center"></el-table-column>
      <el-table-column label="民族" align="center" prop="nation" >
        <template v-slot="{row}">
          <dict-tag :options="dict.type['sys_nation']" :value="row.nation" />
        </template>
      </el-table-column>
      <el-table-column label="人像图片" align="center" prop="faceImgUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.faceImgUrl" :width="60" :height="80"/>
        </template>
      </el-table-column>
      <el-table-column prop="isImportant" label="重点关注" align="center">
        <template v-slot="{row}">
          <dict-tag :options="dict.type['sys_yes_no']" :value="row.isImportant" />
        </template>
      </el-table-column>
      <el-table-column label="关注级别" align="center" prop="attentionLevel" >
        <template v-slot="{row}">
          <dict-tag :options="dict.type['important_level']" :value="row.attentionLevel" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot="{ row }">
          <el-button size="mini" type="text" icon="el-icon-view" v-hasPermi="['person:resident:query']" @click="openViewDialog(row)">查看</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" v-hasPermi="['person:resident:edit']" @click="openEditDialog(row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" v-hasPermi="['person:resident:remove']" @click="removeItem(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      :pageSizes="pageSizes" @pagination="queryChanged" #page></pagination>
      
    <el-dialog :visible.sync="editVisible" :close-on-click-modal="false" append-to-body
              width="80%"  :title="title" custom-class="dialog-mini">
      <el-row style="padding: 10px 20px">
        <el-form ref="form" :model="form" :rules="rules" label-width="100px" :disabled="disabled">
          <h4 class="title-h4">人员基本信息</h4>
          <el-row>
            <el-col :span="span">
              <el-form-item label="人员姓名" prop="name">
                <el-input v-model="form.name" placeholder="请输入人员姓名" />
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="证件类型" prop="certType">
                <el-select v-model="form.certType" class="width-100Rate" placeholder="请输入身份证件类型">
                  <el-option value="CN_CARD" label="中国身份证"></el-option>
                  <el-option value="PASS_PORT" label="护照"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="身份证号" prop="certNo">
                <el-input v-model="form.certNo" placeholder="请输入身份证号" />
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="联系方式" prop="phone">
                <el-input v-model="form.phone" placeholder="请输入联系方式" />
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="籍贯" prop="nativePlace">
                <el-input v-model="form.nativePlace" placeholder="请输入籍贯" />
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="民族" prop="nation">
                <el-select v-model="form.nation" class="width-100Rate" placeholder="请选择民族">
                  <el-option v-for="dict in dict.type.sys_nation" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="公司" class="width-100Rate" prop="company">
                <el-input v-model="form.company" placeholder="请输入公司" />
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="职业" class="width-100Rate" prop="career">
                <el-input v-model="form.career" placeholder="请输入职业" />
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="关注级别"  prop="attentionLevel">
                <el-select v-model="form.attentionLevel" class="width-100Rate" placeholder="请选择关注的级别">
                  <el-option v-for="dict in dict.type.important_level" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="重点人员" prop="isImportant">
                <el-select v-model="form.isImportant" class="width-100Rate" placeholder="请选择是否是重点人员">
                  <el-option v-for="dict in dict.type.sys_yes_no" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="关注原因" class="width-100Rate" prop="focusReason" v-show="form.isImportant == 'Y'"
                :rules="[{ required: form.isImportant == 'Y', message: '当该人员是重点关注人员时,关注原因不能为空', trigger: 'blur' }]">
                <el-select v-model="form.focusReason" class="width-100Rate" placeholder="请选择关注原因">
                  <el-option v-for="dict in dict.type.sys_is_important" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="补充描述" class="width-100Rate" prop="reasonDesc" v-show="form.isImportant == 'Y'">
                <el-input v-model="form.reasonDesc" placeholder="请输入补充描述" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="人脸图片" required prop="faceImgUrl">
                <image-upload v-model="form.faceImgUrl" :limit="1"/>
              </el-form-item>
            </el-col>
            <el-col :span="24" style="--el-table-min-height: auto">
              <h4 class="title-h4">管理房屋地址</h4>
              <el-button type="primary" plain @click="addAddress" style="margin-bottom: 8px" size="mini">新增地址</el-button>
              <el-table size="mini" :data="houseListData" >
                <el-table-column type="selection" />
                <el-table-column prop="communityName" label="所属小区" width="150px" show-overflow-tooltip />
                <el-table-column prop="shortName" label="地址简称" width="160px" />
                <el-table-column prop="fullAddress" label="地址全称" show-overflow-tooltip />
                <el-table-column prop="relationshipWithOwner" label="与房东关系" width="150px" >
                  <template v-slot="{row}">
                    <dict-tag :options="dict.type['family_member_relationship']" :value="row.relationshipWithOwner" />
                  </template>
                </el-table-column>
                <el-table-column label="操作" align="center" width="100px">
                  <template v-slot="scope">
                    <el-button type="text" @click="removeAddressData(scope)"><span class="text-danger">删除</span></el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-dialog :visible.sync="addrVisible" width="500px" title="新增关联地址" append-to-body :close-on-click-modal="false">
                <el-row style="padding: 10px 15px">
                  <el-form ref="addressForm" :model="addressForm" label-width="100px" :rules="rules" size="mini">
                    <el-form-item label="所属小区" prop="communityId">
                      <se-community v-model="addressForm.communityId"  class="width-100Rate" @change="communityChange" />
                    </el-form-item>
                    <el-form-item label="关联地址" prop="metaAddrId">
                      <se-room-select ref="roomSelect" @change="addressChanged" :disabled="!addressForm.communityCode"
                                      :community-code="addressForm.communityCode" class="width-100Rate" />
                    </el-form-item>
                    <el-form-item label="地址全称" prop="fullAddress">
                      <el-input :value="addressForm.fullAddress" class="width-100Rate" readonly placeholder="选择关联地址以填充地址全称" />
                    </el-form-item>
                    <el-form-item label="与房主关系" prop="relationshipWithOwner">
                      <el-select v-model="addressForm.relationshipWithOwner" class="width-100Rate" placeholder="请输入与房主的人际关系">
                        <el-option v-for="dict in dict.type.family_member_relationship" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="入住日期" prop="livingDate">
                      <el-date-picker clearable class="width-100Rate" v-model="addressForm.livingDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择入职日期">
                      </el-date-picker>
                    </el-form-item>
                  </el-form>
                </el-row>
                <template #footer>
                  <el-button size="mini" type="primary" icon="el-icon-plus" @click="confirmAddress">确定</el-button>
                </template>
              </el-dialog>
            </el-col>
          </el-row>
        </el-form>
      </el-row>
      <template #footer>
        <el-button v-show="isDisplay" size="mini" type="primary" v-hasPermi="['person:resident:edit']"
                   @click="submitForm">保存并关闭</el-button>
        <el-button size="mini" type="info" v-hasPermi="['person:resident:edit']"
                   @click="cancel">关闭表单</el-button>
      </template>
    </el-dialog>
 
  </table-panel>
</template>
 
<script>
import TablePanel from '@/components/TablePanel/index.vue'
import TableListMixins from '@/mixins/tableListMixins'
import editFormMixins from './editForm'
import EditForm from './editForm.js'
import { delResident, listResident } from '@/api/person/resident'
export default {
  name: 'resident-people',
  dicts: ['sys_yes_no', 'important_level', 'family_member_relationship', 'sys_is_important', 'sys_nation'],
  mixins: [TableListMixins, editFormMixins],
  components: { EditForm, TablePanel  },
  data() {
    return {
      span: 8,
      disabled: false,
      isDisplay: false,
      editVisible: false,
      selectId: null,
      tableData: [],
      title: ''
    }
  },
  mounted() {
    this.initTableData(listResident);
  },
  methods: {
    openEditDialog(item = {}) {
      this.editVisible = true;
      this.disabled = false;
      this.isDisplay = true;
      if(item.id) {
        this.title = "修改常驻人员信息"
        this.resetForm()
        this.loadDataById(item.id);
      } else {
        this.title = "新增常驻人员信息"
        this.resetForm()
      }
    },
    removeItem(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除社区常驻人员名为"' + row.name + '"的数据项？').then(function() {
        return delResident(ids);
      }).then(() => {
        this.queryChanged();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    //查看按钮
    openViewDialog(row) {
      this.editVisible = true;
      this.disabled = true;
      this.isDisplay = false;
      this.title = "查看常驻人员信息"
      this.resetForm()
      this.loadDataById(row.id);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('person/resident/export', {
        ...this.queryParams
      }, `resident_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
 
<style scoped lang="scss">
 
</style>
