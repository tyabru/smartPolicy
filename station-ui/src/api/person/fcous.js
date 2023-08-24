import request from '@/utils/request'

// 查询重点关注人口列表
export function listFcous(query) {
  return request({
    url: '/person/fcous/list',
    method: 'get',
    params: query
  })
}

// 查询重点关注人口详细
export function getFcous(id) {
  return request({
    url: '/person/fcous/' + id,
    method: 'get'
  })
}

// 新增重点关注人口
export function addFcous(data) {
  return request({
    url: '/person/fcous',
    method: 'post',
    data: data
  })
}

// 修改重点关注人口
export function updateFcous(data) {
  return request({
    url: '/person/fcous',
    method: 'put',
    data: data
  })
}

// 删除重点关注人口
export function delFcous(id) {
  return request({
    url: '/person/fcous/' + id,
    method: 'delete'
  })
}
