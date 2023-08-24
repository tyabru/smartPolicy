import request from '@/utils/request'

// 查询物业信息管理列表
export function listWuye(query) {
  return request({
    url: '/wuye/wuye/list',
    method: 'get',
    params: query
  })
}

// 查询物业信息管理详细
export function getWuye(id) {
  return request({
    url: '/wuye/wuye/' + id,
    method: 'get'
  })
}

// 新增物业信息管理
export function addWuye(data) {
  return request({
    url: '/wuye/wuye',
    method: 'post',
    data: data
  })
}

// 修改物业信息管理
export function updateWuye(data) {
  return request({
    url: '/wuye/wuye',
    method: 'put',
    data: data
  })
}

// 删除物业信息管理
export function delWuye(id) {
  return request({
    url: '/wuye/wuye/' + id,
    method: 'delete'
  })
}
