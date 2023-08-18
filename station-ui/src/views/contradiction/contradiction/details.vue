<template>
  <div class="edit-form">
    <el-form ref="editForm" :model="form" :rules="rules" label-width="125px" size="small" >
      <el-row class="form-panel-item basic-info">
        <h4 class="title-h4">基本信息</h4>
        <el-row>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="所属社区" prop="community">
              <se-community-dept ref="scdSelect" v-model="form.community" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="区域类型" prop="type">
              <el-select v-model="form.type" clearable class="width-100Rate">
                <el-option :value="0" label="小区" />
                <el-option :value="1" label="村" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="防范措施" prop="preventiveMeasures">
              <el-select v-model="form.preventiveMeasures" class="width-100Rate">
                <el-option :value="0" label="人防" />
                <el-option :value="1" label="物防" />
                <el-option :value="2" label="技防" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="所属派出所" prop="pcsName">
              <el-input v-model="form.pcsName" readonly/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="小区（村）名称" prop="name">
              <el-autocomplete v-model="form.name"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入内容"
                @select="handleSelect" class="width-100Rate"
              ></el-autocomplete>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="小区（村）编码" prop="code">
              <el-input v-model="form.code" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="12">
            <el-form-item label="小区（村）地址" prop="address">
              <el-input v-model="form.address" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="是否为安全小区" prop="isSafeArea">
              <el-select  v-model="form.isSafeArea"  class="width-100Rate">
                <el-option :value="0" label="是" />
                <el-option :value="1" label="否" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="命名机关"
                          :rules="[{required: form.isSafeArea ===0,
                            message: '当小区为安全小区时，该字段必填' }]"
                          prop="namingOrgan">
              <el-input v-model="form.namingOrgan" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="命名时间" :rules="[{required: form.isSafeArea ===0,
                            message: '当小区为安全小区时，该字段必填' }]" prop="namingTime">
              <el-date-picker v-model="form.namingTime" type="date" class="width-100Rate"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="负责民警" prop="police">
              <el-select v-model="form.detail.police"
                         @change="policeChange"
                         class="width-100Rate">
                <el-option v-for="item in policeList"
                           :key="item.userId" :value="item.userId"
                           :label="item.nickName" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="总面积" prop="fullArea">
              <el-input-number :min="0" controls-position="right" class="width-100Rate"
                               v-model="form.detail.fullArea"/>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="楼栋数" prop="buildingCount">
              <el-input-number :min="0" controls-position="right"
                               v-model="form.detail.buildingCount" class="width-100Rate" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="负责人姓名" prop="police">
              <el-input v-model="form.detail.headMaster" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="负责人联系电话" prop="police">
              <el-input v-model="form.detail.masterPhone" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="经度" prop="longitude">
              <el-input-number :min="0" controls-position="right" class="width-100Rate"
                               v-model="form.detail.longitude" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :lg="6">
            <el-form-item label="纬度" prop="latitude">
              <el-input-number :min="0" controls-position="right" class="width-100Rate"
                               v-model="form.detail.latitude" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-row>
      <el-row class="">

        <div class="split-container" style="min-height: 450px">
          <div class="form-panel-item basic-info left-aside">
            <h4 class="title-h4">小区（村）附件上传</h4>
            <upload-group ref="uploadTool" group-type="community" group-type-label="小区（村）相关文件"
                    :default-list="form.fileList" @file-changed="fileChanged"></upload-group>
          </div>
          <div class="form-panel-item basic-info main-content">
            <h4 class="title-h4">地图划分</h4>
          </div>
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
import { deleteByFileId, getDescListByVillageId, uploadFileDesc } from '@/api/community/communityFile'
import { addCommunity, getCommunity, updateCommunity } from '@/api/community/community'
import { queryBelongDeptByTypeAndId } from '@/api/system/dept'
import { queryPcsPoliceUser } from '@/api/system/user'
import _ from 'lodash'
import { loadXqxxByNameFormStructure } from '@/api/community/structure'
export default {
  name: 'details',
  components: { UploadGroup },
  data() {
    return {
      form: {
        id: null,
        detail: {},
        fileList: []
      },
      policeList: [],
      rules: {
        community: [ { required: true, message: '所属社区为必填项，不能为空！', trigger: 'blur'} ],
        type: [ { required: true, message: '区域类型为必填项，不能为空！'} ],
        name: [ { required: true, message: '小区地址为必填项，不能为空！'} ],
        code: [ { required: true, message: '小区编码为必填项，不能为空！'},
          { min: 21, max: 21, message: '小区编码长度为21为长度的编码。'}],
        preventiveMeasures: [ { required: true, message: '防范措施为必填项，不能为空！'} ],
      }
    }
  },
  watch: {
    'form.community'(newVal) {
      if(newVal) {
        this.communityChange(newVal)
      } else {
        this.form.pcsId = null;
        this.form.pcsName = null;
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
    },
    getFormById(id) {
      getCommunity(id).then(response => {
        if(response.code === 200 && response.data) {
          this.form = {...response.data}
          this.form.detail.police = parseInt(this.form.detail.police)
        } else {
          this.$message.error("获取表单数据失败！")
        }
      })
      getDescListByVillageId(id).then(response => {
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
    querySearchAsync: _.debounce((queryString, cb) => {
      let results = []
      loadXqxxByNameFormStructure(queryString).then(({code, data}) => {
        if(code === 200 && data ) {
          results = data
        }
      }).finally(() => { cb(results) })
    }, 1000),
    handleSelect(item) {
      this.form.code = item.code
    },
    submitForm(stayThisPage) {
      if(this.formLoad) {
        return
      } else {
        this.formLoad = true
      }
      if(this.form.detail.longitude && this.form.detail.latitude) {
        this.form.detail.point = this.form.detail.longitude+','+this.form.detail.latitude;
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
                response = await updateCommunity(this.form)
              } else {
                response = await addCommunity(this.form)
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
      queryBelongDeptByTypeAndId(value, '101').then(response => {
        if(response.code === 200 && response.data) {
          if(!this.form.detail) {
            this.form.detail = {}
          }
          this.form.pcsId = response.data.deptId;
          this.form.pcsName = response.data.deptName;
          this.queryPoliceById();
        }
      });
    },
    queryPoliceById() {
      if(!this.form.pcsId) {
        return
      }
      queryPcsPoliceUser(this.form.pcsId).then(response => {
        if(response.code === 200 && response.data) {
          this.policeList = response.data
          this.policeChange(this.form.detail.police)
        }
      })
    },
    policeChange(item) {
      const filter =this.policeList.filter(aa => aa.userId === item);
      if(filter && filter.length > 0) {
        this.form.detail.policeName = filter[0].nickName;
        this.form.detail.policePhone = filter[0].phonenumber;
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
    }
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
      --aside-width: 450px;
    }

    .footer {
      position: sticky;
      bottom: 0;
      right: 10px;
      left: 0;
    }
  }
</style>
