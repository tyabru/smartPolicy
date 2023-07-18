import request from '@/utils/request'

// 查询排班管理列表
export function listManagements(query) {
  return request({
    url: '/polices/managements/list',
    method: 'get',
    params: query
  })
}

// 查询排班管理详细
export function getManagements(id) {
  return request({
    url: '/polices/managements/' + id,
    method: 'get'
  })
}

// 查询排班管理详细
export function getManagementsByDutyDate(date) {
  return request({
    url: '/polices/managements/getManagementsByDutyDate/' + date,
    method: 'get'
  })
}

// 新增排班管理
export function addManagements(data) {
  return request({
    url: '/polices/managements',
    method: 'post',
    data: data
  })
}

// 修改排班管理
export function updateManagements(data) {
  return request({
    url: '/polices/managements',
    method: 'put',
    data: data
  })
}

// 删除排班管理
export function delManagements(id) {
  return request({
    url: '/polices/managements/' + id,
    method: 'delete'
  })
}
