import request from '@/utils/request'

// 查询安全隐患检查项字典列表
export function listCheckitemdict(query) {
  return request({
    url: '/safecheck/checkitemdict/list',
    method: 'get',
    params: query
  })
}

// 查询安全隐患检查项字典详细
export function getCheckitemdict(id) {
  return request({
    url: '/safecheck/checkitemdict/' + id,
    method: 'get'
  })
}

// 新增安全隐患检查项字典
export function addCheckitemdict(data) {
  return request({
    url: '/safecheck/checkitemdict',
    method: 'post',
    data: data
  })
}

// 修改安全隐患检查项字典
export function updateCheckitemdict(data) {
  return request({
    url: '/safecheck/checkitemdict',
    method: 'put',
    data: data
  })
}

// 删除安全隐患检查项字典
export function delCheckitemdict(id) {
  return request({
    url: '/safecheck/checkitemdict/' + id,
    method: 'delete'
  })
}
