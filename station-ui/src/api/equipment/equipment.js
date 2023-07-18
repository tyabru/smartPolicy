import request from '@/utils/request'

// 查询警用装备配发领取记录列表
export function listEquipment(query) {
  return request({
    url: '/equipment/equipment/list',
    method: 'get',
    params: query
  })
}

// 查询警用装备配发领取记录详细
export function getEquipment(id) {
  return request({
    url: '/equipment/equipment/' + id,
    method: 'get'
  })
}

// 新增警用装备配发领取记录
export function addEquipment(data) {
  return request({
    url: '/equipment/equipment',
    method: 'post',
    data: data
  })
}

// 修改警用装备配发领取记录
export function updateEquipment(data) {
  return request({
    url: '/equipment/equipment',
    method: 'put',
    data: data
  })
}

// 删除警用装备配发领取记录
export function delEquipment(id) {
  return request({
    url: '/equipment/equipment/' + id,
    method: 'delete'
  })
}
