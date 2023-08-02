import request from '@/utils/request'

// 查询流动人口列表
export function listFlow(query) {
  return request({
    url: '/person/flow/list',
    method: 'get',
    params: query
  })
}

// 查询流动人口详细
export function getFlow(id) {
  return request({
    url: '/person/flow/' + id,
    method: 'get'
  })
}

// 新增流动人口
export function addFlow(data) {
  return request({
    url: '/person/flow',
    method: 'post',
    data: data
  })
}

// 修改流动人口
export function updateFlow(data) {
  return request({
    url: '/person/flow',
    method: 'put',
    data: data
  })
}

// 删除流动人口
export function delFlow(id) {
  return request({
    url: '/person/flow/' + id,
    method: 'delete'
  })
}
