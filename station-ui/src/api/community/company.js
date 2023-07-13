import request from '@/utils/request'

// 查询单位基本信息列表
export function listCompany(query) {
  return request({
    url: '/community/company/list',
    method: 'get',
    params: query
  })
}

// 查询单位基本信息详细
export function getCompany(id) {
  return request({
    url: '/community/company/' + id,
    method: 'get'
  })
}

// 新增单位基本信息
export function addCompany(data) {
  return request({
    url: '/community/company',
    method: 'post',
    data: data
  })
}

// 修改单位基本信息
export function updateCompany(data) {
  return request({
    url: '/community/company',
    method: 'put',
    data: data
  })
}

// 删除单位基本信息
export function delCompany(id) {
  return request({
    url: '/community/company/' + id,
    method: 'delete'
  })
}
