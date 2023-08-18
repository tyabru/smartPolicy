import request from '@/utils/request'

// 查询参与人员信息列表
export function listPersonnel(query) {
  return request({
    url: '/contradiction/personnel/list',
    method: 'get',
    params: query
  })
}

// 查询参与人员信息详细
export function getPersonnel(id) {
  return request({
    url: '/contradiction/personnel/' + id,
    method: 'get'
  })
}

// 查询参与人员信息详细
export function getContradictionPersonByCaseCode(caseCode) {
  return request({
    url: '/contradiction/personnel/getContradictionPersonByCaseCode/' + caseCode,
    method: 'get'
  })
}

// 新增参与人员信息
export function addPersonnel(data) {
  return request({
    url: '/contradiction/personnel',
    method: 'post',
    data: data
  })
}

// 修改参与人员信息
export function updatePersonnel(data) {
  return request({
    url: '/contradiction/personnel',
    method: 'put',
    data: data
  })
}

// 删除参与人员信息
export function delPersonnel(id) {
  return request({
    url: '/contradiction/personnel/' + id,
    method: 'delete'
  })
}
