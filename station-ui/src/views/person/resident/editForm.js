import { addResident, getResident, listResident, updateResident } from '@/api/person/resident'
import { validIdCodeByType, validPhone } from '@/utils/validate'
import { queryResidentHouseList } from '@/api/person/house'
 
export default {
  data() {
    const validIdCode = async (rule, value, callback)=>  {
      if(value && value.includes("**")) {
        callback();
      }
      if(!value || value.length < 1){
        callback("身份证号为必填项！");
      }else {
        if(!validIdCodeByType(value, this.form.certType)){
          callback("身份证号不符合验证规则！");
        } else {
          const { code, rows } = await listResident({certNo: value})
          if(code === 200  && rows) {
            if(rows.length > 0 && (!this.form.id || (rows[0].id !== this.form.id && rows[0].certNo === value))) {
              callback("身份证号已存在！无法继续录入！");
            }
          }
          callback()
        }
      }
    }
    return {
      addrVisible: false,
      // 表单参数
      form: {
        isImportant: 'N',
        certType: 'CN_CARD',
        attentionLevel: 'green',
        nation: '01',
      },
      houseList: [],
      addressForm: {},
      // 表单校验
      rules: {
        bm: [
          { required: true, message: "人员编码不能为空", trigger: "blur" }
        ],
        communityId: [
          { required: true, message: "社区主键id不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "人员姓名不能为空", trigger: "blur" }
        ],
        certType: [
          { required: true, message: "身份证件类型不能为空", trigger: "blur" }
        ],
        certNo: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" },
          { min: 18, max: 18, message: "身份证长度为18", trigger: "blur" },
          { validator: validIdCode, trigger: 'blur' }
        ],
        phone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
          { min: 11, max: 11, message: "手机号长度为11", trigger: "blur" },
          {validator : validPhone, trigger: "blur"}
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
          { required: true, message: "关注级别不能为空", trigger: "blur" }
        ],
        livingDate: [
          { required: true, message: "入住时间不能为空", trigger: "blur" }
        ],
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
  methods: {
    loadDataById(formId) {
      getResident(formId).then(response => {
        this.form = {... response.data}
      })
      queryResidentHouseList(formId).then(response => {
        this.houseList = response.data
      })
    },
    resetForm() {
      this.form = {
        isImportant: 'N',
        certType: 'CN_CARD',
        attentionLevel: 'green',
        houseList: [],
        nation: '01',
      }
      this.houseList = []
      this.$refs['form']?.resetFields();
    },
    addAddress() {
      this.resetAddressForm();
      this.addrVisible = true
    },
    resetAddressForm() {
      this.addressForm = {}
      if(this.$refs['addressForm']) {
        this.$refs['addressForm'].resetFields();
        this.$refs['roomSelect'].resetFields();
      }
      this.addressForm.communityId = null
    },
    cancel() {
      this.editVisible = false
      this.addrVisible = false
      this.resetForm()
      this.resetAddressForm()
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if(this.houseList && this.houseList.length > 0) {
            this.form.houseList = this.houseList;
          }
          if (this.form.id != null) {
            updateResident(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.queryChanged();
            }).finally(() => { this.editVisible = false });
          } else {
            addResident(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.queryChanged();
            }).finally(() => { this.editVisible = false });
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
            const form = {...this.addressForm,...{ delFlag: 0, personType: 1 }}
            this.houseList = [...[form], ...this.houseList]
            this.addrVisible = false
          }
        }
      })
    },
    houseIsDelete(delFlag) {
      return delFlag === 1
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
}