import request from "@/utils/request";

export function countGridStuff(data) {
  return request({
    url: '/qf/statistics/gridStuff' ,
    method: 'post',
    data:data
  })
}

export function countGridStuffLeader(data) {
  return request({
    url: '/qf/statistics/gridStuffLeader' ,
    method: 'post',
    data:data
  })
}
export function countCommunity(data) {
  return request({
    url: '/qf/statistics/community' ,
    method: 'post',
    data:data
  })
}

export function countPeople(data) {
  return request({
    url: '/qf/statistics/people' ,
    method: 'post',
    data:data
  })
}

