import request from '@/utils/request'

// 查询车载设备列表
export function listPoliceOnboardEquipment(query) {
  return request({
    url: '/polices/PoliceOnboardEquipment/list',
    method: 'get',
    params: query
  })
}

// 查询车载设备详细
export function getPoliceOnboardEquipment(id) {
  return request({
    url: '/polices/PoliceOnboardEquipment/' + id,
    method: 'get'
  })
}

// 查询警用装备
export function getPoliceOnboardEquipmentByDeviceCode(deviceCode) {
  return request({
    url: '/polices/PoliceOnboardEquipment/getPoliceOnboardEquipmentByDeviceCode/' + deviceCode,
    method: 'get'
  })
}

// 新增车载设备
export function addPoliceOnboardEquipment(data) {
  return request({
    url: '/polices/PoliceOnboardEquipment',
    method: 'post',
    data: data
  })
}

// 修改车载设备
export function updatePoliceOnboardEquipment(data) {
  return request({
    url: '/polices/PoliceOnboardEquipment',
    method: 'put',
    data: data
  })
}

// 删除车载设备
export function delPoliceOnboardEquipment(id) {
  return request({
    url: '/polices/PoliceOnboardEquipment/' + id,
    method: 'delete'
  })
}
