import request from '@/utils/request'

// 查询单位员工列表
export function listEmployee(query) {
  return request({
    url: '/company/employee/list',
    method: 'get',
    params: query
  })
}

// 查询单位员工详细
export function getEmployee(id) {
  return request({
    url: '/company/employee/' + id,
    method: 'get'
  })
}

// 新增单位员工
export function addEmployee(data) {
  return request({
    url: '/company/employee',
    method: 'post',
    data: data
  })
}

// 修改单位员工
export function updateEmployee(data) {
  return request({
    url: '/company/employee',
    method: 'put',
    data: data
  })
}

// 删除单位员工
export function delEmployee(id) {
  return request({
    url: '/company/employee/' + id,
    method: 'delete'
  })
}
