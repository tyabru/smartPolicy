import { getConfigKey } from '@/api/system/config'

let addressRegx =
  /((?:[一二三四五六七八九]期)?(?:(?:\w*[负附-])?[0-9a-zA-Z一二三四五六七八九前后东西南北中综新旧小]+[院合号楼栋座幢车库排巷公寓住宅商铺]+|-\w+|[东西南北]区[大小院公寓]+|小[2-4二三四]层))?([0-9a-zA-Z东西南北中]+[单元梯]+)?(\d+层)?([负附-]?\d+[号室]?[\u4E00-\u9FFF]{0,3})?$/g
/** 根据地址获取地址中栋单元层等数据
* */
export async function matchFullAddress(address = '') {
  if(address.length < 1) {
    return [address, '', '', '', '']
  }
  // const response = await getConfigKey("address_regx")
  // if(response.code === 200) {
  //   addressRegx = eval(response.msg)
  // }
  addressRegx.lastIndex = 0
  return addressRegx.exec(address)
}
