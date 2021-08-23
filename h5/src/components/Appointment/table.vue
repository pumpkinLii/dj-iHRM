<template>
  <div>
    <el-table :data="list" stripe border fit height="300">
      <el-table-column label="二级管理机构" prop="agentCode" />
      <el-table-column label="三级管理机构" prop="agentName" />
      <el-table-column label="团队架构代码" prop="manageCom3" />
      <el-table-column label="团队架构名称" prop="manageCom4" />
      <el-table-column label="成立时间" prop="agentGroup" />
      <el-table-column label="操作" width="100px" fixed="right">
        <template scope="scope">
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showModifyDialog(scope.row)">主管任命</el-button>
        </template>
      </el-table-column>
    </el-table>
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
import GroupModifyDialog1 from '@/components/Appointment/AppointModifyDialog'
import * as V from '@/api/agent'

export default {
  name: 'Table1',
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
    this.$bus.$on('something', res => {
      this.list = []
      const res2 = { ...res }
      res2.manageCom = res2.manageCom[res2.manageCom.length - 1]
      V.find(res2, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          this.list = r.list
          this.page.totalCount = r.totalCount
          this.$message.success('查询完毕')
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

