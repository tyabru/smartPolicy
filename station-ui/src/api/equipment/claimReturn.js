import request from '@/utils/request'

// 查询警用设备管理列表
export function listClaimReturn(query) {
  return request({
    url: '/equipment/claimReturn/list',
    method: 'get',
    params: query
  })
}

// 查询警用设备管理详细
export function getClaimReturn(id) {
  return request({
    url: '/equipment/claimReturn/' + id,
    method: 'get'
  })
}

// 新增警用设备管理
export function addClaimReturn(data) {
  return request({
    url: '/equipment/claimReturn',
    method: 'post',
    data: data
  })
}

// 修改警用设备管理
export function updateClaimReturn(data) {
  return request({
    url: '/equipment/claimReturn',
    method: 'put',
    data: data
  })
}

// 删除警用设备管理
export function delClaimReturn(id) {
  return request({
    url: '/equipment/claimReturn/' + id,
    method: 'delete'
  })
}
