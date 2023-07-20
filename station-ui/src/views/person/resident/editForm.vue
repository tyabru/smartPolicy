<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <h4 class="title-h4">人员基本信息</h4>
      <el-row>
<!--        <el-col :span="12">-->
<!--          <el-form-item label="所属小区" prop="xqId">-->
<!--            <se-community v-model="form.communityId" class="width-100Rate" />-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="12">-->
<!--          <el-form-item label="地址编码" prop="metaAddrId">-->
<!--            <el-input v-model="form.metaAddrId" placeholder="请输入地址编码" />-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="12">-->
<!--          <el-form-item label="与房东关系" prop="identity">-->
<!--            <el-select v-model="form.identity" class="width-100Rate" placeholder="请选择与房东关系">-->
<!--              <el-option v-for="dict in dict.type.family_member_relationship"-->
<!--                         :key="dict.value"-->
<!--                         :label="dict.label"-->
<!--                         :value="dict.value"-->
<!--              ></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--        </el-col>-->

        <el-col :span="12">
          <el-form-item label="人员姓名" prop="name">
            <el-input v-model="form.name" placeholder="请输入人员姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="证件类型" prop="certType">
            <el-select v-model="form.certType" class="width-100Rate" placeholder="请输入身份证件类型">
              <el-option value="CN_CARD" label="中国身份证"></el-option>
              <el-option value="PASS_PORT" label="护照"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="证件类型" prop="certType">
            <el-select v-model="form.certType" class="width-100Rate" placeholder="请输入身份证件类型">
              <el-option value="CN_CARD" label="中国身份证"></el-option>
              <el-option value="PASS_PORT" label="护照"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="身份证号" prop="certNo">
            <el-input v-model="form.certNo" placeholder="请输入身份证号" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系方式" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入联系方式" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="籍贯" prop="nativePlace">
            <el-input v-model="form.nativePlace" placeholder="请输入籍贯" />
          </el-form-item>
        </el-col>
<!--        <el-col :span="12">-->
<!--          <el-form-item label="入住日期" prop="livingDate">-->
<!--            <el-date-picker clearable style="width: 100%;"-->
<!--                            v-model="form.livingDate"-->
<!--                            type="date"-->
<!--                            value-format="yyyy-MM-dd"-->
<!--                            placeholder="请选择入住日期">-->
<!--            </el-date-picker>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :span="12">
          <el-form-item label="公司" class="width-100Rate" prop="company">
            <el-input v-model="form.company" placeholder="请输入公司" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="职业" class="width-100Rate" prop="career">
            <el-input v-model="form.career" placeholder="请输入职业" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
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

        <el-col :span="12">
          <el-form-item label="关注级别"  prop="attentionLevel">
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
          <el-form-item label="人脸图片地址" required prop="faceImgUrl">
            <image-upload v-model="form.faceImgUrl" :limit="1"/>
          </el-form-item>
        </el-col>
<!--        <el-col :span="24" style="&#45;&#45;el-table-min-height: auto">-->
<!--          <h4 class="title-h4">管理房屋地址</h4>-->
<!--          <el-table size="mini" :data="form.houseList" >-->
<!--            <el-table-column prop="communityName" label="所属小区" />-->
<!--            <el-table-column prop="addressName" label="地址名称"-->
<!--                             show-overflow-tooltip width="200px" />-->
<!--            <el-table-column prop="livingFlag" label="居住地址" />-->
<!--            <el-table-column prop="relationshipWithOwner" label="与房东关系" />-->
<!--            <el-table-column label="操作" align="center">-->
<!--              <el-button type="text" >删除</el-button>-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </el-col>-->
      </el-row>
      <el-row style="margin-top: 20px">
        <el-button type="primary" @click="submitForm">保存</el-button>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import { addResident, listResident, updateResident } from '@/api/person/resident'
import { validIdCodeByType } from '@/utils/validate'

export default {
  name: "Resident",
  dicts: ['sys_yes_no', 'important_level', 'family_member_relationship'],
  data() {
    const validIdCode = async (rule, value, callback) => {
      if(!value || value.length < 1){
        callback("身份证号为必填项！");
      }else {
        if(!validIdCodeByType(value, this.form.certType)){
          callback("身份证号不符合验证规则！");
        } else {
          const { code, data } = listResident({certNo: value})
          if(code == 200  && data  && data.rows) {
            if(data.rows.length > 0 && (!this.form.id || data.rows[0].certNo === value)) {
              callback("身份证号已存在！无法继续录入！");
            }
          }
          callback()
        }
      }
    }
    return {
      // 遮罩层
      loading: true,
      // 表单参数
      form: {
        certType: 'CN_CARD',
        attentionLevel: 'green',
        houseList: []
      },
      // 表单校验
      rules: {
        bm: [
          { required: true, message: "人员编码不能为空", trigger: "blur" }
        ],
        xqId: [
          { required: true, message: "社区主键id不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "人员姓名不能为空", trigger: "blur" }
        ],
        certType: [
          { required: true, message: "身份证件类型不能为空", trigger: "blur" }
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
        address: [
          { required: true, message: "居住地址不能为空", trigger: "blur" }
        ],
        metaAddrId: [
          { required: true, message: "地址编码不能为空", trigger: "blur" }
        ],
        identity: [
          { required: true, message: "与房东关系不能为空", trigger: "blur" }
        ],
        livingDate: [
          { required: true, message: "入住日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
  },
  methods: {
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateResident(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResident(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).finally(response => {
              if(this.form.faceImgUrl) {
                deleteByFileId
              }
            });
          }
        }
      });
    }
  }
};
</script>
