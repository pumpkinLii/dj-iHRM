<template>
  <div>
    <el-table :data="list" stripe border fit height="300">
      <el-table-column type="selection" width="50" />
      <el-table-column label="人员工号" prop="agentCode" />
      <el-table-column label="人员姓名" prop="agentName" />
      <el-table-column label="分公司" prop="gradeName" />
      <el-table-column label="中心支公司" prop="manageCom2" />
      <el-table-column label="团队代码" prop="manageCom3" />
      <el-table-column label="团队名称" prop="manageCom4" />
      <el-table-column label="入司日期" prop="employDate" />
      <el-table-column label="人员状态" prop="stateName" />
      <el-table-column label="合同类型" prop="stateName" />
      <el-table-column label="职级" prop="stateName" />
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
    <PersonChangeDialog />
  </div>
</template>

<script>
import PersonChangeDialog from '@/components/PersonChange/PersonChangeDialog'
import { query } from '@/api/personChange'
export default {
  name: 'PersonChangeTable',
  components: { PersonChangeDialog },
  data() {
    return {
      list: [{}, {}, {}, {}, {}, {}, {}],
      page: {
        currentPage: 1, // 当前第几页
        totalCount: 0, // 总共有几条
        pageSize: 10 // 每页的项目数
      }
    }
  },
  mounted() {
    this.$bus.$on('RESET_PERSON_CHANGE_TABLE', () => {
      this.list = []
    })
    this.$bus.$on('QUERY_PERSON_CHANGE', data => {
      this.list = []
      query(data, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          console.log(r)
          this.list = r['list']
          this.page.totalCount = r['totalCount']
          this.$message.success('查询完毕')
        })
    })
    this.$bus.$on('OPEN_PERSON_CHANGE_DIALOG_FETCH', () => {
      this.$bus.$emit('OPEN_PERSON_CHANGE_DIALOG', this.selected)
    })
  },
  beforeDestroy() {
    this.$bus.$off('QUERY_PERSON_CHANGE')
    this.$bus.$off('RESET_PERSON_CHANGE_TABLE')
    this.$bus.$off('OPEN_PERSON_CHANGE_DIALOG_FETCH')
  },
  methods: {
    showModifyDialog(item) {
      this.$bus.$emit('agentcode', item)
    },
    // 一页有几行
    handleSizeChange(size) {
      this.page.pageSize = size
      this.$bus.$emit('refresh')
    },
    // 第几页
    handleCurrentChange(page) {
      this.page.currentPage = page
      this.$bus.$emit('refresh')
    }
  }
}
</script>

<style scoped>

</style>
