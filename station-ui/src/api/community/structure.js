import request from '@/utils/request'

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
