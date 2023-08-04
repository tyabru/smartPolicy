import request from '@/utils/request'

// 查询日常文件管理列表
export function listFileManagements(query) {
  return request({
    url: '/polices/fileManagements/list',
    method: 'get',
    params: query
  })
}

// 查询日常文件管理详细
export function getFileManagements(id) {
  return request({
    url: '/polices/fileManagements/' + id,
    method: 'get'
  })
}

// 新增日常文件管理
export function addFileManagements(data) {
  return request({
    url: '/polices/fileManagements',
    method: 'post',
    data: data
  })
}

// 修改日常文件管理
export function updateFileManagements(data) {
  return request({
    url: '/polices/fileManagements',
    method: 'put',
    data: data
  })
}

// 删除日常文件管理
export function delFileManagements(id) {
  return request({
    url: '/polices/fileManagements/' + id,
    method: 'delete'
  })
}

// 上传文件
export function uploadFile(data,folderName,deptId) {
  return request({
    url: '/polices/fileManagements/fileUpload',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data',
      'commonFilePath': 'fileManagement', // 公共的存储文件夹
      'folderName': folderName,
      'deptId': deptId,
      'uploadType': 'syrk'
    },
    data: data    // 参数需要是单一的formData形式
  })
}

// 下载日常文件
export function fileDownload(id) {
  return request({
    url: '/polices/fileManagements/fileDownload/' + id,
    method: 'get',
  })
}


// 下载日常文件
export function downloadFile(id) {
  return request({
    url: '/polices/fileManagements/downloadFile/' + id,
    method: 'get',
    responseType: 'blob'
  })
}