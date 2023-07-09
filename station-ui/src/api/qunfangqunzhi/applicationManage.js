import request from '@/utils/request'

// 查询申请管理列表
export function listApplicationManage(query) {
  return request({
    url: '/qunfangqunzhi/applicationManage/list',
    method: 'get',
    params: query
  })
}

// 查询申请管理详细
export function getApplicationManage(id) {
  return request({
    url: '/qunfangqunzhi/applicationManage/' + id,
    method: 'get'
  })
}

// 新增申请管理
export function addApplicationManage(data) {
  return request({
    url: '/qunfangqunzhi/applicationManage',
    method: 'post',
    data: data
  })
}


// 修改申请管理
export function updateApplicationManage(data) {
  return request({
    url: '/qunfangqunzhi/applicationManage',
    method: 'put',
    data: data
  })
}

// 删除申请管理
export function delApplicationManage(id) {
  return request({
    url: '/qunfangqunzhi/applicationManage/' + id,
    method: 'delete'
  })
}
