import request from '@/utils/request'

// 查询执法仪记录仪列表
export function listPoliceEnforcementRecorder(query) {
  return request({
    url: '/polices/PoliceEnforcementRecorder/list',
    method: 'get',
    params: query
  })
}

// 查询执法仪记录仪详细
export function getPoliceEnforcementRecorder(id) {
  return request({
    url: '/polices/PoliceEnforcementRecorder/' + id,
    method: 'get'
  })
}

// 新增执法仪记录仪
export function addPoliceEnforcementRecorder(data) {
  return request({
    url: '/polices/PoliceEnforcementRecorder',
    method: 'post',
    data: data
  })
}

// 修改执法仪记录仪
export function updatePoliceEnforcementRecorder(data) {
  return request({
    url: '/polices/PoliceEnforcementRecorder',
    method: 'put',
    data: data
  })
}

// 删除执法仪记录仪
export function delPoliceEnforcementRecorder(id) {
  return request({
    url: '/polices/PoliceEnforcementRecorder/' + id,
    method: 'delete'
  })
}
