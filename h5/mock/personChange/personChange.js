const Mock = require('mockjs')

const queryData = Mock.mock({
  'list|5-10': [
    {
      'agentCode|+1': [
        '@id'
      ],
      'agentName|+1': [
        '@cname'
      ]
    }
  ],
  'totalCount|1': '@integer(5,100)'
})

module.exports = [
  {
    url: '/person/change/query',
    type: 'post',
    response: config => {
      return {
        code: 0,
        list: queryData.list,
        totalCount: queryData.totalCount
      }
    }
  }
]
