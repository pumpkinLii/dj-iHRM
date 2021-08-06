import request from '@/utils/request'

// 用于获得下拉菜单选项
export function getSelectOptional() {
  return request({
    url: '/start/get',
    method: 'post'
  })
}
// 检查身份证号
//  data:{
//   idNo:123,
//   idType:234
// }

// 王佳智的ip
export function check(data) {
  const Url = '/test/idCheck' + '?' + 'idNo=' + data.idNo + '&idType=' + data.idType
  return request({
    url: Url,
    method: 'post',
    // data:data
    params: { data }
  })
}

// 获得城市列表  //王欣艺的ip
export function getcity() {
  return request({
    url: '/area/get',
    method: 'post'
  })
}

export function getCode(name, buffer) {
  request({
    url: '/start/get',
    method: 'post'
  }).then(
    r => {
      console.log(r)
      console.log(buffer)
      if (!r.resource[name]) {
        return
      }
      Object.keys(r.resource[name])
        .forEach((key) => {
          buffer.push({
            label: r.resource[name][key],
            value: key
          })
        })
    }
  )
}
