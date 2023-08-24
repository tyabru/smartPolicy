import request from '@/utils/request'

// 查询普通用户信息列表
export function listCommonUsers(query) {
  return request({
    url: '/qf/commonUsers/list',
    method: 'get',
    params: query
  })
}

// 查询普通用户信息详细
export function getCommonUsers(userId) {
  return request({
    url: '/qf/commonUsers/' + userId,
    method: 'get'
  })
}

// 新增普通用户信息
export function addCommonUsers(data) {
  return request({
    url: '/qf/commonUsers',
    method: 'post',
    data: data
  })
}


// 处理网格员申请
export function uploadApplication(data,applicationId,flag) {
  return request({
    url: '/qf/commonUsers/handleApplication',
    method: 'post',
    data: data,
    params:{
      applicationId,
      flag
    }
  })
}

// 修改普通用户信息
export function updateCommonUsers(data) {
  return request({
    url: '/qf/commonUsers',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeCommonUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/qf/commonUsers/changeStatus',
    method: 'put',
    data: data
  })
}
// 删除普通用户信息
export function delCommonUsers(userId) {
  return request({
    url: '/qf/commonUsers/' + userId,
    method: 'delete'
  })
}

// 查询网格员列表
export function listGridStuff(query) {
  return request({
    url: '/qf/commonUsers/listGridStaff',
    method: 'get',
    params: query
  })
}

