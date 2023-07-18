import request from '@/utils/request'

// 查询警员基本信息列表
export function listInformation(query) {
  return request({
    url: '/polices/information/list',
    method: 'get',
    params: query
  })
}

// 查询警员基本信息详细
export function getInformation(id) {
  return request({
    url: '/polices/information/' + id,
    method: 'get'
  })
}

// 新增警员基本信息
export function addInformation(data) {
  return request({
    url: '/polices/information',
    method: 'post',
    data: data
  })
}

// 修改警员基本信息
export function updateInformation(data) {
  return request({
    url: '/polices/information',
    method: 'put',
    data: data
  })
}

// 删除警员基本信息
export function delInformation(id) {
  return request({
    url: '/polices/information/' + id,
    method: 'delete'
  })
}

// 上传图片
export function addImgData(data) {
  return request({
    url: '/common/imgHandler/imgUpload',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data',
      'commonFilePath': 'identificationPhotos', // 公共的存储文件夹
      'uploadType': 'syrk'
    },
    data: data    // 参数需要是单一的formData形式
  })
}
