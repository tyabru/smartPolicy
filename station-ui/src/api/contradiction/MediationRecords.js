import request from '@/utils/request'

// 查询纠纷调解记录列表
export function listMediationRecords(query) {
  return request({
    url: '/contradiction/MediationRecords/list',
    method: 'get',
    params: query
  })
}

// 查询纠纷调解记录详细
export function getMediationRecords(id) {
  return request({
    url: '/contradiction/MediationRecords/' + id,
    method: 'get'
  })
}

// 新增纠纷调解记录
export function addMediationRecords(data) {
  return request({
    url: '/contradiction/MediationRecords',
    method: 'post',
    data: data
  })
}

// 修改纠纷调解记录
export function updateMediationRecords(data) {
  return request({
    url: '/contradiction/MediationRecords',
    method: 'put',
    data: data
  })
}

// 删除纠纷调解记录
export function delMediationRecords(id) {
  return request({
    url: '/contradiction/MediationRecords/' + id,
    method: 'delete'
  })
}
