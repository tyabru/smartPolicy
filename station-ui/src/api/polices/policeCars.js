import request from '@/utils/request'

// 查询警用车辆列表
export function listCars(query) {
  return request({
    url: '/polices/cars/list',
    method: 'get',
    params: query
  })
}

// 查询警用车辆详细
export function getCars(id) {
  return request({
    url: '/polices/cars/' + id,
    method: 'get'
  })
}

// 查询警用车辆详细
export function getPoliceCarByLicenseNumber(licenseNumber) {
  return request({
    url: '/polices/cars/getPoliceCarByLicenseNumber/' + licenseNumber,
    method: 'get'
  })
}

// 查询警用车辆详细
export function getPoliceCarByCarCode(carCode) {
  return request({
    url: '/polices/cars/getPoliceCarByCarCode/' + carCode,
    method: 'get'
  })
}

// 查询警用车辆详细
export function getPoliceCarByEquipmentNumber(equipmentNumber) {
  return request({
    url: '/polices/cars/getPoliceCarByEquipmentNumber/' + equipmentNumber,
    method: 'get'
  })
}

// 新增警用车辆
export function addCars(data) {
  return request({
    url: '/polices/cars',
    method: 'post',
    data: data
  })
}

// 修改警用车辆
export function updateCars(data) {
  return request({
    url: '/polices/cars',
    method: 'put',
    data: data
  })
}

// 删除警用车辆
export function delCars(id) {
  return request({
    url: '/polices/cars/' + id,
    method: 'delete'
  })
}
