import request from '@/utils/request'

// 查询下发事件列表
export function listAllocated(query) {
  return request({
    url: '/qf/allocated/list',
    method: 'get',
    params: query
  })
}

// 查询下发事件详细
export function getAllocated(id) {
  return request({
    url: '/qf/allocated/' + id,
    method: 'get'
  })
}
//批量下发
export function downloadEventAllocated(userIds,eventId) {
  return request({
    url: '/qf/allocated/downloadEvent/'+userIds+"/"+eventId,
    method: 'get'
  })
}

// 新增下发事件
export function addAllocated(data) {
  return request({
    url: '/qf/allocated',
    method: 'post',
    data: data
  })
}

// 修改下发事件
export function updateAllocated(data) {
  return request({
    url: '/qf/allocated',
    method: 'put',
    data: data
  })
}

// 删除下发事件
export function delAllocated(id) {
  return request({
    url: '/qf/allocated/' + id,
    method: 'delete'
  })
}
