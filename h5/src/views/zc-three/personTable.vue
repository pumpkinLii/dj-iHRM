<template>
  <div>
    <el-table :data="list" stripe border fit height="300">
      <el-table-column label="人员工号" prop="agentCode" />
      <el-table-column label="人员姓名" prop="agentName" />
      <el-table-column label="当前职级" prop="agentGrade" />
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
    <GroupModifyDialog  :visible="config.groupModifyDialogVisible" @REFRESH_QUERY="$emit('QUERY_GROUP')" @CLOSE_GROUP_MODIFY_DIALOG="handleGroupModifyDialogClose" />
  </div>
</template>

<script>
import GroupModifyDialog from '@/components/GroupManagement/GroupModifyDialog'
import * as V from '@/api/personhold'
export default {
  name: 'GroupTable',
  components: { GroupModifyDialog },
  data() {
    return {
      config: {
        groupModifyDialogVisible: false
      },
      list: [],
      page: {
        currentPage: 1,
        totalCount: 0,
        pageSize: 10
      }
    }
  },
  methods: {
    showModifyDialog(item) {
      // 告知子组件的传入项目 即需要修改的数据
      this.$bus.$emit("agentcode",item)
      // 显示窗口
      this.config.groupModifyDialogVisible = true
    },
    handleGroupModifyDialogClose() {
      // 隐藏窗口
      this.config.groupModifyDialogVisible = false
    },
    handleQueryGroup(data) {
      queryGroup(data, this.page, this)
        .then(() => {
          this.$message.success('查询完毕')
        })
    },
    handleSizeChange(size) {
      this.page.pageSize = size
      this.$emit('QUERY_GROUP')
    },
    handleCurrentChange(page) {
      this.page.currentPage = page
      this.$emit('QUERY_GROUP')
    }
  },
  mounted() {
    // 查询结果  res 是传过来的数据
    this.$bus.$on("form2",res =>{
      V.queryPerson(res,{ pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          this.list = r.list
          this.page.totalCount = r.totalCount
          this.$message.success('查询完毕')
        });
      console.log("传入成功")
    })
  }
}
</script>

<style scoped>

</style>
