import request from '@/utils/request'
import { blobValidate } from '@/utils/ruoyi'

export async function selectDistinctStructure(code) {
  if(!code) {
    console.warn("查询地址用的小区编码不能为空！")
    return { code: 200, data: [] }
  }
  return request({
    url: '/community/structure/room_nodes',
    method: 'get',
    params: { 'communityCode': code }
  })
}

// 查询小区房屋结构和地址信息列表
export function listStructure(query) {
  return request({
    url: '/community/structure/list',
    method: 'get',
    params: query
  })
}

// 查询小区房屋结构和地址信息详细
export function getStructure(id) {
  return request({
    url: '/community/structure/' + id,
    method: 'get'
  })
}

// 新增小区房屋结构和地址信息
export function addStructure(data) {
  return request({
    url: '/community/structure',
    method: 'post',
    data: data
  })
}

// 修改小区房屋结构和地址信息
export function updateStructure(data) {
  return request({
    url: '/community/structure',
    method: 'put',
    data: data
  })
}

// 删除小区房屋结构和地址信息
export function delStructure(id) {
  return request({
    url: '/community/structure/' + id,
    method: 'delete'
  })
}

export function importFormExcel(fileUrl) {
  return request({
    url: '/community/structure/importFormExcel',
    method: 'post',
    data: fileUrl
  })
}

export function downloadExample() {
  return request({
    url: '/community/structure/downloadExample',
    method: 'get',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    responseType: 'blob'
  }).then(response => {
    const isBlob = blobValidate(response);
    if(isBlob) {
      const blob = new Blob([response])
      saveAs(blob, "标准地址批量导入模板.xlsx")
    }
  })
}

/** 查询在标准地址表中存在小区列表
 * @param queryString 小区名称模糊查询字符串
 * @return AjaxResult(data,code,msg)
 * */
export async function loadXqxxByNameFormStructure(queryString) {
  if(!queryString || queryString.length < 1) {
    return { code: 200, data: [] }
  }
  return request({
    url: '/community/structure/loadXqxxByName',
    method: 'get',
    params: { queryString }
  })
}
