import request from '@/utils/request'

// 查询整改结果审核列表
export function listRectifyresultreview(query) {
  return request({
    url: '/safecheck/rectifyresultreview/list',
    method: 'get',
    params: query
  })
}

// 查询整改结果审核详细
export function getRectifyresultreview(id) {
  return request({
    url: '/safecheck/rectifyresultreview/' + id,
    method: 'get'
  })
}

// 新增整改结果审核
export function addRectifyresultreview(data) {
  return request({
    url: '/safecheck/rectifyresultreview',
    method: 'post',
    data: data
  })
}

// 修改整改结果审核
export function updateRectifyresultreview(data) {
  return request({
    url: '/safecheck/rectifyresultreview',
    method: 'put',
    data: data
  })
}

// 删除整改结果审核
export function delRectifyresultreview(id) {
  return request({
    url: '/safecheck/rectifyresultreview/' + id,
    method: 'delete'
  })
}
