import request from '@/utils/request'

export function uploadFileDesc(data) {
  return request({
    url: '/company/desc/uploadFile',
    method: 'post',
    params: { repeat: true },
    data: data
  })
}

export function deleteByFileId(fileId, onlyNotDefined = 0) {
  return request({
    url: '/company/desc/'+fileId,
    headers: {
      'only-not-defined': onlyNotDefined
    },
    method: 'delete'
  })
}

export function getDescListByCompanyId(companyId) {
  return request({
    url: `/company/desc/${companyId}/list`,
    method: 'get'
  })
}
