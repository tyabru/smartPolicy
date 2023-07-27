import request from '@/utils/request'

// 查询下发任务修改列表
export function listAllocateRectify(query) {
  return request({
    url: '/qf/allocateRectify/list',
    method: 'get',
    params: query
  })
}

// 查询下发任务修改详细
export function getAllocateRectify(id) {
  return request({
    url: '/qf/allocateRectify/' + id,
    method: 'get'
  })
}

// 新增下发任务修改
export function addAllocateRectify(data) {
  return request({
    url: '/qf/allocateRectify',
    method: 'post',
    data: data
  })
}

// 修改下发任务修改
export function updateAllocateRectify(data) {
  return request({
    url: '/qf/allocateRectify',
    method: 'put',
    data: data
  })
}

// 删除下发任务修改
export function delAllocateRectify(id) {
  return request({
    url: '/qf/allocateRectify/' + id,
    method: 'delete'
  })
}
