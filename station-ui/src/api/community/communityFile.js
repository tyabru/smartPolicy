import request from '@/utils/request'

export function uploadFileDesc(data) {
  return request({
    url: '/community/desc/uploadFile',
    method: 'post',
    params: { repeat: true },
    data: data
  })
}

export function deleteByFileId(fileId, onlyNotDefined = 0) {
  return request({
    url: '/community/desc/'+fileId,
    headers: {
      'only-not-defined': onlyNotDefined
    },
    method: 'delete'
  })
}

export function getDescListByVillageId(villageId) {
  return request({
    url: `/community/desc/${villageId}/list`,
    method: 'get'
  })
}
