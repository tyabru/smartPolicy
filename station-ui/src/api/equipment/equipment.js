import request from '@/utils/request'

// 查询警用装备
export function listEquipment(query) {
  return request({
    url: '/equipment/equipment/list',
    method: 'get',
    params: query
  })
}

// 查询警用装备
export function getEquipment(id) {
  return request({
    url: '/equipment/equipment/' + id,
    method: 'get'
  })
}

// 查询警用装备
export function getPoliceEquipmentByEquipmentNumber(equipmentNumber) {
  return request({
    url: '/equipment/equipment/getPoliceEquipmentByEquipmentNumber/' + equipmentNumber,
    method: 'get'
  })
}

// 新增警用装备
export function addEquipment(data) {
  return request({
    url: '/equipment/equipment',
    method: 'post',
    data: data
  })
}

// 修改警用装备
export function updateEquipment(data) {
  return request({
    url: '/equipment/equipment',
    method: 'put',
    data: data
  })
}

// 删除警用装备
export function delEquipment(id) {
  return request({
    url: '/equipment/equipment/' + id,
    method: 'delete'
  })
}
