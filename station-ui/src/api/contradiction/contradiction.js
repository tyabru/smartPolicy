import request from '@/utils/request'

// 查询矛盾纠纷基本信息列表
export function listContradiction(query) {
  return request({
    url: '/contradiction/contradiction/list',
    method: 'get',
    params: query
  })
}

// 查询矛盾纠纷基本信息详细
export function getContradiction(id) {
  return request({
    url: '/contradiction/contradiction/' + id,
    method: 'get'
  })
}

// 查询矛盾纠纷基本信息详细
export function getContradictionByCaseCode(caseCode) {
  return request({
    url: '/contradiction/contradiction/getContradictionByCaseCode/' + caseCode,
    method: 'get'
  })
}

// 新增矛盾纠纷基本信息
export function addContradiction(data) {
  return request({
    url: '/contradiction/contradiction',
    method: 'post',
    data: data
  })
}

// 修改矛盾纠纷基本信息
export function updateContradiction(data) {
  return request({
    url: '/contradiction/contradiction',
    method: 'put',
    data: data
  })
}

// 删除矛盾纠纷基本信息
export function delContradiction(id) {
  return request({
    url: '/contradiction/contradiction/' + id,
    method: 'delete'
  })
}
