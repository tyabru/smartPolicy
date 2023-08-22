import request from '@/utils/request'

// 查询整改通知书下发列表
export function listRectifynoticedistribute(query) {
  return request({
    url: '/safecheck/rectifynoticedistribute/list',
    // url: '/safecheck/CheckRectifyDistributeReview/list',
    method: 'get',
    params: query
  })
}

// 查询整改通知书下发详细
export function getRectifynoticedistribute(id) {
  return request({
    url: '/safecheck/rectifynoticedistribute/' + id,
    // url: '/safecheck/CheckRectifyDistributeReview/' + id,
    method: 'get'
  })
}

// 新增整改通知书下发
export function addRectifynoticedistribute(data) {
  return request({
    url: '/safecheck/rectifynoticedistribute',
    // url: '/safecheck/CheckRectifyDistributeReview',
    method: 'post',
    data: data
  })
}

// 修改整改通知书下发
export function updateRectifynoticedistribute(data) {
  return request({
    url: '/safecheck/rectifynoticedistribute',
    // url: '/safecheck/CheckRectifyDistributeReview',
    method: 'put',
    data: data
  })
}

// 删除整改通知书下发
export function delRectifynoticedistribute(id) {
  return request({
    url: '/safecheck/rectifynoticedistribute/' + id,
    // url: '/safecheck/CheckRectifyDistributeReview/' + id,
    method: 'delete'
  })
}

// 根据模板导出word
export function exportWord(query) {
  return request({
    url: '/safecheck/rectifynoticedistribute/exportWord',
    // url: '/safecheck/CheckRectifyDistributeReview/exportWord',
    method: 'get',
    params: query
  })
}

