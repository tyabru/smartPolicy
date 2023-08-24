import request from '@/utils/request'

export function listHouse(queryParams) {
  return request({
    url: `/system/house/list`,
    method: 'get',
    params: queryParams
  })
}

export function listGroupByAddress(queryParams) {
  return request({
    url: `/system/house/listGroupByAddress`,
    method: 'get',
    params: queryParams
  })
}

// 查询house列表
export function queryResidentHouseList(formId) {
  return request({
    url: `/system/house/${formId}/resident`,
    method: 'get'
  })
}

export function queryFlowPHouseList(formId) {
  return request({
    url: `/system/house/${formId}/flows`,
    method: 'get'
  })
}

