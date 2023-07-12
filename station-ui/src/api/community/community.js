import request from '@/utils/request'

// 查询小区（村）基本信息列表
export function listCommunity(query) {
  return request({
    url: '/community/community/list',
    method: 'get',
    params: query
  })
}

export function listCommunityByRole(query) {
  return request({
    url: '/community/community/listByRole',
    method: 'get',
    params: query
  })
}

// 查询小区（村）基本信息详细
export function getCommunity(id) {
  return request({
    url: '/community/community/' + id,
    method: 'get'
  })
}

// 新增小区（村）基本信息
export function addCommunity(data) {
  return request({
    url: '/community/community',
    method: 'post',
    data: data
  })
}

// 修改小区（村）基本信息
export function updateCommunity(data) {
  return request({
    url: '/community/community',
    method: 'put',
    data: data
  })
}

// 删除小区（村）基本信息
export function delCommunity(id) {
  return request({
    url: '/community/community/' + id,
    method: 'delete'
  })
}
