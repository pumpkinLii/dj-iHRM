<template>
  <div>
    <el-table :data="list" stripe border fit height="300" :default-sort="{prop: 'agentStateName'}">
      <el-table-column label="人员工号" prop="agentCode" />
      <el-table-column label="人员姓名" prop="agentName" />
      <el-table-column label="分工司" prop="manageCom3" />
      <el-table-column label="中心支公司" prop="manageCom4" />
      <el-table-column label="团队代码" prop="branchAttr" />
      <el-table-column label="团队名称" prop="branchName" />
      <el-table-column label="入司日期" prop="employDate" />
      <el-table-column label="人员状态" prop="agentStateName" sortable />
      <el-table-column label="合同类型" prop="contractTypeName" />
      <el-table-column label="职级" prop="agentGradeName" />
      <el-table-column label="操作" width="100px" fixed="right">
        <template scope="scope">
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showModifyDialog(scope.row)">职级调整</el-button>
        </template>
      </el-table-column>
    </el-table>s
    <!-- 分页 -->
    <div class="block" style="text-align: right;margin-top: 1rem">
      <el-pagination
        :current-page="page.currentPage"
        :page-sizes="[10, 20, 50, 100, 200, 500]"
        :page-size="page.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.totalCount"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <!--     弹窗-->
    <GroupModifyDialog1 />

  </div>
</template>

<script>
import GroupModifyDialog1 from '@/components/Agentadjust/AgentModifyDialog'
import * as V from '@/api/agent'

export default {
  name: 'GroupTable',
  components: { GroupModifyDialog1 },
  data() {
    return {
      list: [],
      page: {
        currentPage: 1, // 当前第几页
        totalCount: 0, // 总共有几条
        pageSize: 10 // 每页的项目数
      }
    }
  },
  mounted() {
    // 查询结果  res 是传过来的数据
    this.$bus.$on('something2', res => {
      this.list = []
      const res2 = { ...res }
      res2.manageCom = res2.manageCom[res2.manageCom.length - 1]
      V.find(res2, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          this.list = r.list
          this.page.totalCount = r.totalCount
          this.$message.success('查询完毕')
        }).catch(() => {
          this.page.totalCount = 0
        })
    })
    this.$bus.$on('something', res => {
      this.list = []
      const res2 = { ...res }
      res2.manageCom = res2.manageCom[res2.manageCom.length - 1]
      V.find(res2, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          this.list = r.list
          this.page.totalCount = r.totalCount
          this.$message.success('查询完毕')
        }).catch(() => {
          this.page.totalCount = 0
        })
    })
    this.$bus.$on('something1', () => {
      this.list = []
      this.$message.error('请先选择管理机构')
    })
  },
  beforeDestroy() {
    this.$bus.$off('something')
    this.$bus.$off('something1')
  },
  methods: {
    showModifyDialog(item) {
      this.$bus.$emit('agent1', item)
    },
    // 一页有几行
    handleSizeChange(size) {
      this.page.pageSize = size
      this.$bus.$emit('refreshAgent')
    },
    // 第几页
    handleCurrentChange(page) {
      this.page.currentPage = page
      this.$bus.$emit('refreshAgent')
    }
  }
}
</script>

<style  scoped>
/deep/ .el-table th{
  text-align: center;
}
/deep/ .el-table td{
  text-align: center;
}
</style>

