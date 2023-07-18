import request from '@/utils/request'
import { formatDate } from '@/utils'
import { downloadFile } from 'image-conversion'
import { blobValidate } from '@/utils/ruoyi'
// 获取路由
export const getResource = (resource) => {
  return request({
    url: '/common/download/resource',
    method: 'get',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    responseType: 'blob',
    params: {
      resource: resource
    }
  })
}

export const download = (resource, filename = formatDate(new Date())) => {
  return getResource(resource) .then(response => {
    const isBlob = blobValidate(response);
    if(isBlob) {
      const blob = new Blob([response])
      saveAs(blob, filename)
    }
  })
}

// export const deleteFileByResource = (resource) => {
//   if(!resource || resource.length < 1) {
//     return new Promise(resolve => {resolve()})
//   } else {
//     return request({
//       url: '/common/download/resource',
//       method: 'get',
//       params: {
//         resource: resource
//       }
//     })
//   }
// }
