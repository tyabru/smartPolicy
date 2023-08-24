import request from '@/utils/request'

// 查询警员分组详情列表
export function listPolices(query) {
  return request({
    url: '/polices/polices/list',
    method: 'get',
    params: query
  })
}

// 查询警员分组详情详细
export function getPolices(id) {
  return request({
    url: '/polices/polices/' + id,
    method: 'get'
  })
}

// 新增警员分组详情
export function addPolices(data) {
  return request({
    url: '/polices/polices',
    method: 'post',
    data: data
  })
}

// 修改警员分组详情
export function updatePolices(data) {
  return request({
    url: '/polices/polices',
    method: 'put',
    data: data
  })
}

// 删除警员分组详情
export function delPolices(id) {
  return request({
    url: '/polices/polices/' + id,
    method: 'delete'
  })
}
