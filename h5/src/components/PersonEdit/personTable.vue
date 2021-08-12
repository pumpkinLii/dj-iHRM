<template>
  <div>
    <el-table :data="list" stripe border fit height="300">
      <el-table-column label="人员工号" prop="agentCode" />
      <el-table-column label="人员姓名" prop="agentName" />
      <el-table-column label="当前职级" prop="gradeName" />
      <el-table-column label="二级管理机构" prop="manageCom2" />
      <el-table-column label="三级管理机构" prop="manageCom3" />
      <el-table-column label="四级管理机构" prop="manageCom4" />
      <el-table-column label="入司日期" prop="employDate" />
      <el-table-column label="人员状态" prop="agentState" />
      <el-table-column label="操作" width="100px" fixed="right">
        <template scope="scope">
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showModifyDialog(scope.row.agentCode)">修改</el-button>
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
    <GroupModifyDialog1 />

  </div>
</template>

<script>
import GroupModifyDialog1 from '@/components/PersonEdit/PersonEdit'
import * as V from '@/api/personhold'
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
    this.$bus.$on('form2', res => {
      this.list = []
      V.queryPerson(res, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          console.log(r)
          this.list = r.list
          this.page.totalCount = r.totalCount
          this.$message.success('查询完毕')
        })
    })
  },
  beforeDestroy() {
    this.$bus.$off('form2')
  },
  methods: {
    showModifyDialog(item) {
      this.$bus.$emit('agentcode', item)
    },
    // 一页有几行
    handleSizeChange(size) {
      this.page.pageSize = size
    },
    // 第几页
    handleCurrentChange(page) {
      this.page.currentPage = page
    }
  }
}
</script>

<style scoped>

</style>
