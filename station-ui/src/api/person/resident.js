import request from '@/utils/request'

// 查询社区常驻人口列表
export function listResident(query) {
  return request({
    url: '/person/resident/list',
    method: 'get',
    params: query
  })
}

// 查询社区常驻人口详细
export function getResident(id) {
  return request({
    url: '/person/resident/' + id,
    method: 'get'
  })
}

// 新增社区常驻人口
export function addResident(data) {
  return request({
    url: '/person/resident',
    method: 'post',
    data: data
  })
}

// 修改社区常驻人口
export function updateResident(data) {
  return request({
    url: '/person/resident',
    method: 'put',
    data: data
  })
}

// 删除社区常驻人口
export function delResident(id) {
  return request({
    url: '/person/resident/' + id,
    method: 'delete'
  })
}
