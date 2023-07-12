<script>
import { checkDictIsExists, getDicts } from '@/api/system/dict/data'
import { addType } from '@/api/system/dict/type'

export default {
  name: 'index',
  props: {
    groupType: {
      require: true
    },
    groupTypeLabel: {
      require: true
    },
    defaultList: {
      default() {
        return []
      }
    }
  },
  data() {
    return {
      visible: false,
      form: {},
      fileChangeFlag: -1,
      fileList: [],
      dictType: null,
      groupOptions: []
    }
  },
  watch: {
    groupType: {
      immediate: true,
      handler(newVal) {
        if(newVal) {
          this.refreshDictList()
        }
      }
    },
    defaultList(newVal) {
      if(newVal && newVal.length > 0) {
        this.clearFiles();
        this.fileList = newVal
      }
    }
  },
  computed: {
    titleGroup() {
      const groupArr = this.fileList.map(item => item.groupTitle)
      return [...new Set(groupArr)]
    },
    fileListTab() {
      const flag = this.fileChangeFlag;
      if(flag < 0) {
        console.log("嘟嘟嘟~");
      }
      return this.fileList?.filter(item => !item.delFlag || item.delFlag !== 1);
    }
  },
  methods: {
    clearFiles() {
      this.form = {
        fileList: []
      }
      this.$refs['uploadTool']?.clearFiles();
      this.fileList = []
    },
    handleRemove(file, fileList) {
      this.form.fileList = fileList
    },
    handlePreview(file, fileList) {
      this.form.fileList = fileList
    },
    confirmForm() {
      this.form.fileList.forEach(item => {
        this.fileList.push({
          groupTitle: this.form.groupTitle,
          fileDesc: item.name,
          fileType: item.raw.type,
          raw: item
        })
      })
      this.titleGroup.forEach(item => {
        const form = {
          dictType: this.dictType,
          dictValue: item,
          dictName: this.groupTypeLabel,
          dictLabel: item
        }
        checkDictIsExists(form).then(isSuccess => {
          if(isSuccess) {
            this.refreshDictList();
          }
        }).finally(() => {
          this.changeUploadModelStatus();
          this.$emit('file-changed', this.fileList)
        })
      })
    },
    refreshDictList() {
      this.dictType = 'sq_group_' + this.groupType;
      getDicts('sq_group_' + this.groupType).then(response => {
        this.groupOptions = response.data
      })
    },
    changeUploadModelStatus() {
      this.form = {
        fileList: []
      }
      this.$refs['uploadTool']?.clearFiles();
      this.visible = !this.visible
    },
    removeFile(scope) {
      if(scope.row.id) {
        this.fileList[scope.$index].delFlag = 1;
        this.fileChangeFlag = scope.$index
      } else {
        this.fileList.splice(scope.$index, 1)
      }
      this.$emit('file-changed', this.fileList, this.titleGroup)
    }
  }
}
</script>

<template>
  <div>
    <el-button @click="changeUploadModelStatus" style="margin-bottom: 10px">点击上传</el-button>
    <el-table :data="fileListTab" height="350px">
      <el-table-column label="文件类别" prop="groupTitle"  width="110px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column label="文件名称" prop="fileDesc"
                       show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" width="60px">
        <template v-slot="scope">
          <el-button type="text" @click="removeFile(scope)"><span class="text-danger">删除</span></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="visible" title="新增附件" close-on-click-modal>
      <el-form :model="form" >
        <el-form-item label="文件类别">
          <el-select v-model="form.groupTitle" allow-create filterable
                     default-first-option placeholder="请选择文件标签">
            <el-option value="" disabled label="可手动添加新的分类" />
            <el-option v-for="item in groupOptions" :key="item.dictId" :value="item.dictValue"
                       :label="item.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择文件">
          <el-upload class="group-item" ref="uploadTool" action=""
            :on-change="handlePreview"
            :on-remove="handleRemove"
            :file-list="form.fileList"
            :auto-upload="false">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">
              1. 文件类别可以自行创建
              2. 可多选文件上传
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="confirmForm">确定</el-button>
        <el-button type="info" @click="changeUploadModelStatus">取消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">

</style>
