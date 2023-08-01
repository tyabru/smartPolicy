import request from '@/utils/request'

// 查询上报事件管理列表
export function listThreatmanagement(query) {
  return request({
    url: '/qf/threatmanagement/list',
    method: 'get',
    params: query
  })
}

// 查询上报事件管理详细
export function getThreatmanagement(id) {
  return request({
    url: '/qf/threatmanagement/' + id,
    method: 'get'
  })
}

// 新增上报事件管理
export function addThreatmanagement(data) {
  return request({
    url: '/qf/threatmanagement',
    method: 'post',
    data: data
  })
}

// 修改上报事件管理
export function updateThreatmanagement(data) {
  return request({
    url: '/qf/threatmanagement',
    method: 'put',
    data: data
  })
}

// 删除上报事件管理
export function delThreatmanagement(id) {
  return request({
    url: '/qf/threatmanagement/' + id,
    method: 'delete'
  })
}

// 归档上报事件管理
export function finishThreatmanagement(data) {
  return request({
    url: '/qf/threatmanagement/finish',
    method: 'post',
    data:data
  })
}

export function transferEvent(id) {
  return request({
    url: '/qf/threatmanagement/transferEvent/'+id,
    method: 'get'
  })
}
