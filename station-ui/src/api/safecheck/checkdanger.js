import request from '@/utils/request'

// 查询安全隐患检查登记列表
export function listCheckdanger(query) {
  return request({
    url: '/safecheck/checkdanger/list',
    method: 'get',
    params: query
  })
}

// 查询安全隐患检查登记详细
export function getCheckdanger(id) {
  return request({
    url: '/safecheck/checkdanger/' + id,
    method: 'get'
  })
}

// 新增安全隐患检查登记
export function addCheckdanger(data) {
  return request({
    url: '/safecheck/checkdanger',
    method: 'post',
    data: data
  })
}

// 修改安全隐患检查登记
export function updateCheckdanger(data) {
  return request({
    url: '/safecheck/checkdanger',
    method: 'put',
    data: data
  })
}

// 删除安全隐患检查登记
export function delCheckdanger(id) {
  return request({
    url: '/safecheck/checkdanger/' + id,
    method: 'delete'
  })
}

// 根据模板导出word
export function exportWord(query) {
  return request({
    url: '/safecheck/checkdanger/exportWord',
    method: 'get',
    params: query
  })
}
