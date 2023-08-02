<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="mini" :inline="true" v-show="showSearch" label-width="110 px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="certNo">
        <el-input
          v-model="queryParams.certNo"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="民族" prop="nation">
        <el-input
          v-model="queryParams.nation"
          placeholder="请输入民族"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入住日期" prop="livingDate">
        <el-date-picker clearable
          v-model="queryParams.livingDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择入住日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否是重点人员" prop="isImportant">
        <el-select v-model="queryParams.isImportant" placeholder="请选择是否是重点人员" clearable>
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="关注级别" prop="attentionLevel">
        <el-select v-model="queryParams.attentionLevel" class="width-100Rate" placeholder="请选择关注的级别">
          <el-option v-for="dict in dict.type.important_level"
                     :key="dict.value"
                     :label="dict.label"
                     :value="dict.value"
          ></el-option>
        </el-select>
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
          v-hasPermi="['person:flow:add']"
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
          v-hasPermi="['person:flow:edit']"
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
          v-hasPermi="['person:flow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['person:flow:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="queryChanged"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="身份证号" align="center" prop="certNo" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="来此的原因或目的" align="center" prop="remark" />
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['person:flow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['person:flow:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="queryChanged"
    />

    <!-- 添加或修改流动人口对话框 -->
    <el-dialog :visible.sync="open" :close-on-click-modal="false" append-to-body
               width="80%"  :title="title" custom-class="dialog-mini">
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row style="padding: 10px 23px">
          <el-col :span="8">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="证件类型" prop="certType">
              <el-select v-model="form.certType" class="width-100Rate" placeholder="请输入身份证件类型">
                <el-option value="CN_CARD" label="中国身份证"></el-option>
                <el-option value="PASS_PORT" label="护照"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="身份证号" prop="certNo">
              <el-input v-model="form.certNo" placeholder="请输入身份证号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="籍贯" prop="nativePlace">
              <el-input v-model="form.nativePlace" placeholder="请输入籍贯" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="民族" prop="nation">
              <el-input v-model="form.nation" placeholder="请输入民族" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="公司" prop="company">
              <el-input v-model="form.company" placeholder="请输入公司" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="职业" prop="career">
              <el-input v-model="form.career" placeholder="请输入职业" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="重点人员" prop="isImportant">
              <el-select v-model="form.isImportant" class="width-100Rate" placeholder="请选择是否是重点人员">
                <el-option
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="关注级别" prop="attentionLevel">
              <el-select v-model="form.attentionLevel" class="width-100Rate" placeholder="请选择关注的级别">
                <el-option v-for="dict in dict.type.important_level"
                           :key="dict.value"
                           :label="dict.label"
                           :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="来此的原因或目的" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="人脸图片地址" required prop="faceImgUrl">
              <image-upload v-model="form.faceImgUrl" :limit="1"/>
            </el-form-item>
          </el-col>
          <el-col :span="24" style="--el-table-min-height: auto">
            <h4 class="title-h4">管理房屋地址</h4>
            <el-button type="primary" plain @click="addAddress"
                       style="margin-bottom: 8px" size="mini">新增地址</el-button>
            <el-table size="mini" :data="houseListData" >
              <el-table-column type="selection" />
              <el-table-column prop="communityName" label="所属小区" width="150px" show-overflow-tooltip />
              <el-table-column prop="shortName" label="地址简称" width="160px" />
              <el-table-column prop="fullAddress" label="地址全称"
                               show-overflow-tooltip />
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
            <el-dialog :visible.sync="addrVisible" width="500px"
                       title="新增关联地址" append-to-body :close-on-click-modal="false">
              <el-row style="padding: 10px 15px">
                <el-form ref="addressForm" :model="addressForm" label-width="100px" :rules="rules" size="mini">
                  <el-form-item label="所属小区" prop="communityId">
                    <se-community v-model="addressForm.communityId"  class="width-100Rate"
                                  @change="communityChange" />
                  </el-form-item>
                  <el-form-item label="关联地址" prop="metaAddrId">
                    <se-room-select ref="roomSelect" @change="addressChanged" :disabled="!addressForm.communityCode"
                                    :community-code="addressForm.communityCode" class="width-100Rate" />
                  </el-form-item>
                  <el-form-item label="地址全称" prop="fullAddress">
                    <el-input :value="addressForm.fullAddress" class="width-100Rate" readonly
                              placeholder="选择关联地址以填充地址全称" />
                  </el-form-item>
                  <el-form-item label="与房主关系" prop="relationshipWithOwner">
                    <el-select v-model="addressForm.relationshipWithOwner" class="width-100Rate" placeholder="请输入与房主的人际关系">
                      <el-option v-for="dict in dict.type.family_member_relationship"
                                 :key="dict.value"
                                 :label="dict.label"
                                 :value="dict.value"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="入住日期" prop="startTime">
                    <el-date-picker clearable class="width-100Rate"
                                    v-model="addressForm.startTime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择入职日期">
                    </el-date-picker>
                  </el-form-item>
                </el-form>
              </el-row>
              <template #footer>
                <el-button size="mini" type="primary" icon="el-icon-plus"
                           @click="confirmAddress">确定</el-button>
              </template>
            </el-dialog>
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
import { listFlow, getFlow, delFlow, addFlow, updateFlow } from "@/api/person/flow";
import TableListMixins from '@/mixins/tableListMixins'
import { validIdCodeByType } from '@/utils/validate'
import { listResident } from '@/api/person/resident'
import { queryFlowPHouseList, queryResidentHouseList } from '@/api/person/house'
export default {
  name: "Flow",
  dicts: ['sys_yes_no', 'important_level', 'family_member_relationship'],
  mixins: [ TableListMixins ],
  data() {
    const validIdCode = async (rule, value, callback)=>  {
      if(!value || value.length < 1){
        callback("身份证号为必填项！");
      }else {
        if(!validIdCodeByType(value, this.form.certType)){
          callback("身份证号不符合验证规则！");
        } else {
          const { code, rows } = await listFlow({certNo: value})
          if(code == 200  && rows) {
            if(rows.length > 0 && (!this.form.id || (rows[0].id !== this.form.id && rows[0].certNo === value))) {
              callback("身份证号已存在！无法继续录入！");
            }
          }
          callback()
        }
      }
    }
    return {
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      addrVisible: false,
      // 表单参数
      form: {
        isImportant: 'N',
        certType: 'CN_CARD',
        attentionLevel: 'green',
        houseList: []
      },
      houseList: [],
      addressForm: {},
      // 表单校验
      rules: {
        bm: [
          { required: true, message: "编码不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        certType: [
          { required: true, message: "证件类型不能为空", trigger: "change" }
        ],
        certNo: [
          { required: true, message: "身份证号不能为空", trigger: "blur" },
          { validator: validIdCode, trigger: 'blur' }
        ],
        phone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        startTime: [
          { required: true, message: "入住日期不能为空", trigger: "blur" }
        ],
        faceImgUrl: [
          { required: true, message: "人脸图片不能为空", trigger: "blur" }
        ],
        fullAddress: [
          { required: true, message: "居住地址不能为空", trigger: "blur" }
        ],
        metaAddrId: [
          { required: true, message: "地址编码不能为空", trigger: "blur" }
        ],
        relationshipWithOwner: [
          { required: true, message: "与房东关系不能为空", trigger: "blur" }
        ],
        isImportant: [
          { required: true, message: "是否重点人员不能为空", trigger: "blur" }
        ],
        attentionLevel: [
          { required: true, message: "关注的级别 0绿 1黄 2红不能为空", trigger: "blur" }
        ]
      }
    };
  },
  computed: {
    houseListData() {
      if(!this.houseList || this.houseList.length < 1) {
        return []
      }
      return this.houseList.filter(item => !this.houseIsDelete(item.delFlag));
    }
  },
  created() {
    this.initTableData(listFlow);
  },
  methods: {
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        isImportant: 'N',
        certType: 'CN_CARD',
        attentionLevel: 'green',
        houseList: []
      };
      this.houseList = []
      this.resetForm("form");
      this.resetAddressForm();
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加流动人口";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFlow(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改流动人口";
      });
      // TODO
      queryFlowPHouseList(id).then(response => {
        this.houseList = response.data
      })
    },
    addAddress() {
      this.resetAddressForm()
      this.addrVisible = true
    },
    resetAddressForm() {
      this.addressForm = {}
      if(this.$refs['addressForm']) {
        this.$refs['addressForm'].resetFields();
        this.$refs['roomSelect'].resetFields();
      }
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if(this.houseList && this.houseList.length > 0) {
            this.form.houseList = this.houseList;
          }
          if (this.form.id != null) {
            updateFlow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.queryChanged();
            });
          } else {
            addFlow(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.queryChanged();
            });
          }
        }
      });
    },
    confirmAddress() {
      this.$refs['addressForm'].validate(valid => {
        if(valid) {
          const vv = this.houseList.filter(item => item.metaAddrId === this.addressForm.metaAddrId)
          if(vv && vv.length > 0) {
            if(vv[0].id && vv[0].delFlag === 1) {
              this.houseList.forEach(item => {
                Object.keys(this.addressForm).forEach(key => {
                  if(key !== 'id') {
                    item[key] = this.addressForm[key]
                  }
                })
                item.delFlag = 0
              })
              this.addrVisible = false
            } else {
              this.$message.warning("已选择此地址。")
            }
          } else {
            const form = {...this.addressForm,...{ delFlag: 0, personType: 2 }}
            this.houseList = [form, ...this.houseList]
            this.addrVisible = false
          }
        }
      })
    },
    houseIsDelete(delFlag) {
      return delFlag === 1
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除流动人口编号为"' + ids + '"的数据项？').then(function() {
        return delFlow(ids);
      }).then(() => {
        this.queryChanged();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('flow/flow/export', {
        ...this.queryParams
      }, `flow_${new Date().getTime()}.xlsx`)
    },
    communityChange(item, raw) {
      this.$set(this.addressForm, 'communityName', raw.name)
      this.$set(this.addressForm, 'communityCode', raw.code)
    },
    addressChanged(item, raw) {
      this.$set(this.addressForm, 'metaAddrId', item)
      this.$set(this.addressForm, 'fullAddress', raw.fullAddress)
      this.$set(this.addressForm, 'shortName', raw.shortName)
      this.$set(this.addressForm, 'id', raw.id)
    },
    removeAddressData(scope) {
      if(scope.row.id) {
        this.houseList[scope.$index].delFlag = 1;
      } else {
        this.houseList.splice(scope.$index, 1)
      }
    }
  }
};
</script>
