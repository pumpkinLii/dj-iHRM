const Mock = require('mockjs')

const data = Mock.mock({ id: '@id' })

module.exports = [
  {
    url: '/test/login',
    type: 'post',
    response: config => {
      return {
        code: 0,
        data: data
      }
    }
  }
]
