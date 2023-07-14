import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, handleTree } from "@/utils/ruoyi";
import { download } from '@/utils/request'

// 分页组件
import Pagination from "@/components/Pagination";
// 自定义表格工具组件
import RightToolbar from "@/components/RightToolbar"
// 富文本组件
import Editor from "@/components/Editor"
// 文件上传组件
import FileUpload from "@/components/FileUpload"
// 图片上传组件
import ImageUpload from "@/components/ImageUpload"
// 图片预览组件
import ImagePreview from "@/components/ImagePreview"
// 字典标签组件
import DictTag from '@/components/DictTag'
// 表格查询表单样式集成 仅样式无逻辑
import SearchBar from '@/components/SearchBar/index.vue'

import CardHeader from '@/components/CardHeader/index.vue'

import SeCommunity from '@/components/CustomSelect/SeCommunity.vue'
import SeCompany from '@/components/CustomSelect/SeCompany.vue'
import SeCommunityDept from '@/components/CustomSelect/SeCommunityDept.vue'

/** 全局的公用组件在这里导入
 *
 * */
const install = function(Vue) {

  // 全局组件挂载
  Vue.component('DictTag', DictTag)
  Vue.component('Pagination', Pagination)
  Vue.component('RightToolbar', RightToolbar)
  Vue.component('Editor', Editor)
  Vue.component('FileUpload', FileUpload)
  Vue.component('ImageUpload', ImageUpload)
  Vue.component('ImagePreview', ImagePreview)
  Vue.component('SearchFormBar', SearchBar);
  Vue.component('CardHeader', CardHeader)
  /* 小区（村）下拉选择框 */
  Vue.component('SeCommunity', SeCommunity)
  /* 单位下拉选择框 */
  Vue.component('SeCompany', SeCompany)
  /* 社区部门下拉选择框 名字起的太相似了 */
  Vue.component('SeCommunityDept', SeCommunityDept)

  // 全局方法挂载
  Vue.prototype.getDicts = getDicts
  Vue.prototype.getConfigKey = getConfigKey
  Vue.prototype.parseTime = parseTime
  Vue.prototype.resetForm = resetForm
  Vue.prototype.addDateRange = addDateRange
  Vue.prototype.selectDictLabel = selectDictLabel
  Vue.prototype.selectDictLabels = selectDictLabels
  Vue.prototype.download = download
  Vue.prototype.handleTree = handleTree
}

export default install;
