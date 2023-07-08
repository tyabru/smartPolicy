import request from '@/utils/request'

// 查询安全隐患检查场所字典列表
export function listCheckplacedict(query) {
  return request({
    url: '/safecheck/checkplacedict/list',
    method: 'get',
    params: query
  })
}

// 查询安全隐患检查场所字典详细
export function getCheckplacedict(id) {
  return request({
    url: '/safecheck/checkplacedict/' + id,
    method: 'get'
  })
}

// 新增安全隐患检查场所字典
export function addCheckplacedict(data) {
  return request({
    url: '/safecheck/checkplacedict',
    method: 'post',
    data: data
  })
}

// 修改安全隐患检查场所字典
export function updateCheckplacedict(data) {
  return request({
    url: '/safecheck/checkplacedict',
    method: 'put',
    data: data
  })
}

// 删除安全隐患检查场所字典
export function delCheckplacedict(id) {
  return request({
    url: '/safecheck/checkplacedict/' + id,
    method: 'delete'
  })
}
