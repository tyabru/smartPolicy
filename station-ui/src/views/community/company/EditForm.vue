<template>
  <div class="edit-form">
    <el-form ref="editForm" :model="form" :rules="rules" label-width="125px" size="small" >
      <el-row class="form-panel-item basic-info">
        <h4 class="title-h4">基本信息</h4>
        <el-row>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="所属社区" prop="community">
              <se-community-dept ref="scdSelect" v-model="form.communityId" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="单位类型" prop="type">
              <el-select v-model="form.type" clearable class="width-100Rate" @change="createCompanyCode">
                <el-option v-for="item in dict.type['place_list']" :key="item.value" :value="item.value" :label="item.label" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="所属派出所" prop="pcsName">
              <el-input v-model="form.pcsName" readonly/>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="创建日期" prop="registrationTime">
              <el-date-picker clearable style="width: 100%" @change="createCompanyCode"
                              v-model="form.registrationTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择创建日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="单位名称" prop="companyName">
              <el-input v-model="form.companyName" placeholder="请输入单位名称" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="单位编码" prop="companyCode">
              <el-input v-model="form.companyCode" placeholder="请输入单位编码" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>

          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="注册地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入注册地址" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="营业执照号" prop="tradeCode" :rules="[{required: !form.organizationCode && !form.creditCode,
                  message: '执照号、机构代码、信用代码三选一' }]">
              <el-input v-model="form.tradeCode" placeholder="请输入营业执照号" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="组织机构代码" prop="organizationCode" :rules="[{required: !form.tradeCode && !form.creditCode,
                  message: '执照号、机构代码、信用代码三选一' }]">
              <el-input v-model="form.organizationCode" placeholder="请输入组织机构代码" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="统一信用代码" prop="creditCode" :rules="[{required: !form.tradeCode && !form.organizationCode,
                  message: '执照号、机构代码、信用代码三选一' }]">
              <el-input v-model="form.creditCode" placeholder="请输入统一信用代码" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="负责民警" prop="police">
              <el-select v-model="form.police" @change="policeChange" class="width-100Rate">
                <el-option v-for="item in policeList" :key="item.userId" :value="item.userId" :label="item.nickName" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="负责人姓名" prop="headMaster">
              <el-input v-model="form.headMaster" placeholder="请输入负责人姓名" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="负责人联系方式" prop="contactPhone" :rules="[{required: form.headMaster && form.headMaster.length > 0,
                message: '在填写负责人后，联系电话为必填项。'}]">
              <el-input v-model="form.contactPhone" placeholder="请输入负责人联系方式" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="证件类型" prop="identityType" :rules="[{required: form.headMaster && form.headMaster.length > 0 ,
                message: '在填写负责人后，证件类型为必填项。'}]">
              <el-select v-model="form.identityType" class="width-100Rate" placeholder="请输入身份证件类型">
                <el-option value="CN_CARD" label="中国身份证"></el-option>
                <el-option value="PASS_PORT" label="护照"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="负责人证件号码" prop="identityCode" :rules="[{required: form.headMaster && form.headMaster.length > 0,
                message: '在填写负责人后，负责人证件号码为必填项。'}]">
              <el-input v-model="form.identityCode" placeholder="请输入负责人证件号码" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="电子邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入电子邮箱" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="经度" prop="longitude">
              <el-input-number :min="0" controls-position="right" class="width-100Rate" v-model="form.longitude" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="纬度" prop="latitude">
              <el-input-number :min="0" controls-position="right" class="width-100Rate" v-model="form.latitude" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-row>
      <el-row class="">
        <div class="form-panel-item basic-info">
          <h4 class="title-h4">单位附件上传</h4>
          <upload-group ref="uploadTool" group-type="company" group-type-label="单位信息相关文件"
                        :default-list="form.fileList" @file-changed="fileChanged"></upload-group>
        </div>
      </el-row>
      <el-row class="footer" style="text-align: right">
        <el-button type="primary" @click="submitForm(true)">保存</el-button>
        <el-button type="primary" @click="submitForm(false)">保存并关闭</el-button>
        <el-button type="info" @click="closePage">取消并关闭表单</el-button>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import { Decrypt } from '@/utils/Aescrypt'
import UploadGroup from '@/components/UploadGroup/index.vue'
import { deleteByFileId, getDescListByCompanyId, uploadFileDesc } from '@/api/community/companyFile.js'
import { addCompany, getCompany, updateCompany } from '@/api/community/company'
import { queryBelongDeptByTypeAndId } from '@/api/system/dept'
import { getUserProfile, queryPcsPoliceUser } from "@/api/system/user";
import { fixedString } from '@/utils'
import { formatDateByPattern } from '@/utils'
import { validIdCodeByType, validPhone } from '@/utils/validate'

export default {
  name: 'edit-form',
  dicts: ['place_list'],
  components: { UploadGroup },
  data() {
    const validIdCode = (rule, value, callback) => {
      const isRequired = this.form.headMaster && this.form.headMaster.length > 0
      if(isRequired || !value && value.length < 1){
        callback("填写负责人后，身份证号为必填项！");
      }else if(value && !validIdCodeByType(value, this.form.identityType)){
        callback("身份证号不符合验证规则！");
      } else {
        callback()
      }
    }
    return {
      user: null,
      form: {
        id: null,
        identityType: 'CN_CARD',
        fileList: []
      },
      policeList: [],
      rules: {
        communityId: [ { required: true, message: '所属社区为必填项，不能为空！', trigger: 'blur'} ],
        identityCode: [ { validator: validIdCode, trigger: "blur" } ],
        companyName: [ { required: true, message: '单位名称为必填项，不能为空！', trigger: 'blur'} ],
        companyCode: [ { required: true, message: '单位编码为必填项，不能为空！', trigger: 'blur'} ],
        phone: [ { required: true, message: '单位联系电话为必填项，不能为空！', trigger: 'blur'} ],
        address: [ { required: true, message: '单位注册地址为必填项，不能为空！', trigger: 'blur'} ],
        police: [ { required: true, message: '负责民警为必填项，不能为空！', trigger: 'blur'} ],
        headMaster: [ { required: true, message: '单位负责人为必填项，不能为空！', trigger: 'blur'} ],
        contactPhone: [ { validator : validPhone, trigger: "blur"} ],
      }
    }
  },
  watch: {
    'form.communityId'(newVal) {
      if(newVal) {
        this.communityChange(newVal)
      } else {
        this.form.detail.pcsId = null;
        this.form.detail.pcsName = null;
        this.form.detail.police = null
        this.form.detail.policeName = null
        this.form.detail.policePhone = null
        this.form.detail.policePhone = null
      }
    }
  },
  created() {
    this.loadFormData()
  },
  methods: {
    loadFormData() {
      const query = this.$route.query
      if(query && query.sq_pk) {
        try {
          const params = JSON.parse(Decrypt(query.sq_pk));
          this.form.id = params.id
        } catch (e) {
          throw e;
        }
        this.getFormById(this.form.id);
      }
      this.getUser();
    },
    getUser () {
      getUserProfile().then(response => {
        this.user = response.data;
        this.queryPoliceById();
      })
    },
    createCompanyCode() {
      if(this.form.type && this.form.registrationTime) {
        const date = new Date(this.form.registrationTime);
        const time = new Date().getTime() + '';
        const code = '61' + fixedString(this.form.type, 2)
          + formatDateByPattern(date, 'yyyyMMdd')
          + time.substring(time.length - 9);
        this.$set(this.form, 'companyCode', code)
      }
    },
    getFormById(id) {
      getCompany(id).then(response => {
        if(response.code === 200 && response.data) {
          this.form = {...response.data}
          this.form.police = parseInt(this.form.police)
          if(this.form.centerPoint && this.form.centerPoint.split(',').length > 1) {
            const s = this.form.centerPoint.split(',')
            this.form.longitude = s[0]
            this.form.latitude = s[1]
          }
        } else {
          this.$message.error("获取表单数据失败！")
        }
      })
      getDescListByCompanyId(id).then(response => {
        if(response.code === 200 && response.data) {
          this.form.fileList = response.data
        } else {
          this.$message.error("获取文件列表失败！")
        }
      })
    },
    fileChanged(fileList) {
      this.fileList = fileList;
    },
    submitForm(stayThisPage) {
      if(this.formLoad) {
        return
      } else {
        this.formLoad = true
      }
      if(this.form.longitude && this.form.latitude) {
        this.form.centerPoint = this.form.longitude+','+this.form.latitude;
      }
      const isUpdate = this.form.id && this.form.id != null;
      this.$refs['editForm'].validate(async validate => {
        if(validate) {
          const { code, ids, msg = '' } = await this.uploadDescFileList();
          try {
            if(code !== 200) {
              this.$message.error(msg);
              await this.deleteByFileIds(ids, true)
            } else {
              this.form.descIds = ids.join(',');
              let response;
              if(isUpdate) {
                response = await updateCompany(this.form)
              } else {
                response = await addCompany(this.form)
              }
              if(response && response.code === 200) {
                this.$message.success("保存成功");
                if(!stayThisPage) {
                  this.goBackPage();
                } else {
                  this.clearFormData();
                }
              } else {
                await this.deleteByFileIds(ids, true)
              }
            }
          } catch (error) {
            console.log(error)
            await this.deleteByFileIds(ids, true)
          } finally {
            this.formLoad = false
          }
        } else {
          this.formLoad = false
        }
      })
    },
    async uploadDescFileList() {
      if(this.fileList && this.fileList.length > 0) {
        let ids = [], errMsg = [];
        try {
          for (let i = 0; i < this.fileList.length; i++) {
            const item = this.fileList[i];
            if(item.delFlag === 1) {
              // 删除列
              deleteByFileId(item.id);
            } else {
              if(!item.id) {
                const formData = new FormData();
                formData.set("groupType", "community");
                formData.set("groupTitle", item.groupTitle);
                formData.set("fileDesc", item.fileDesc);
                formData.set("fileFormat", item.fileType);
                formData.set("file", item.raw.raw);
                const response = await uploadFileDesc(formData);
                if(response.code === 200 && response.data) {
                  ids.push(response.data);
                } else {
                  errMsg.push({ row: row.fileDesc, msg: response.msg })
                }
              }
            }
          }
        } catch (e) {
          console.error(e)
          return { code: 500, ids: ids, msg: '文件上传异常: ' + e.message }
        }
        if(errMsg.length > 0) {
          return { code: 500, ids: ids, msg: '文件上传异常。' }
        }
        return { code: 200, ids: ids };
      } else {
        return { code: 200, ids: [] }
      }
    },
    async deleteByFileIds(ids, onlyNotDefined) {
      if(!ids) return;
      for (let i = 0; i < ids.length; i++) {
        await deleteByFileId(ids[i], onlyNotDefined)
      }
    },
    goBackPage() {
      this.$router.back()
    },
    closePage() {
      this.$tab.closePage()
    },
    communityChange(value) {
      queryBelongDeptByTypeAndId(this.form.communityId, '101').then(response => {
        if(response.code === 200 && response.data) {
          this.$set(this.form, 'pcsId', response.data.deptId);
          this.$set(this.form, 'pcsName', response.data.deptName);
        }
      });
    },
    queryPoliceById() {
      queryPcsPoliceUser(this.user.deptId ).then(response => {
        if(response.code === 200 && response.data) {
          this.policeList = response.data
          this.policeChange(this.form.police)
        }
      })
    },
    policeChange(item) {
      const filter =this.policeList.filter(aa => aa.userId === item);
      if(filter && filter.length > 0) {
        this.form.policeName = filter[0].nickName;
        this.form.policePhone = filter[0].phonenumber;
      }
    },
    clearFormData() {
      if(this.form.id) {
        this.loadFormData()
      } else {
        this.form = {
          id: null,
          detail: {},
          fileList: []
        };
        this.$refs['uploadTool'].clearFiles();
      }
    },
  }
}
</script>

<style scoped lang="scss">
  .edit-form {
    min-height: calc(100vh - 85px);
    background-color: var(--page-background-color);

    padding: 10px;
    position: relative;
    overflow: inherit!important;

    .form-panel-item {
      background-color: #fff;
      border-radius: 4px;

      padding: 10px 20px;
      margin-bottom: 15px;
    }

    .split-container {
      --aside-width: 550px;
    }

    .footer {
      position: sticky;
      bottom: 0;
      right: 10px;
      left: 0;
    }
  }
</style>
