<template>
  <div>
    <el-table :data="list" stripe border fit height="300">
      <el-table-column label="管理机构代码" prop="manageComCode" />
      <el-table-column label="管理机构名称" prop="manageCom" />
      <el-table-column label="团队代码" prop="branchAttr" />
      <el-table-column label="团队名称" prop="branchName" />
      <el-table-column label="群聊名称" prop="chatName" />
      <el-table-column label="负责人代码" prop="branchManager" />
      <el-table-column label="负责人姓名" prop="branchManagerName" />
      <el-table-column label="负责人手机号" prop="branchManagerPhone" />
      <el-table-column label="当前在职代理人个数" prop="agentCount" />
      <el-table-column label="成立日期" prop="branchEffDate" />
      <el-table-column label="停业标志" prop="branchStatusName" />
      <el-table-column label="停业时间" prop="branchTerminateEffDate" />
      <el-table-column label="操作员代码" prop="operator" />
      <el-table-column label="操作" width="100px" fixed="right">
        <template scope="scope">
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showModifyDialog(list[scope.$index])">修改</el-button>

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
    <GroupModifyDialog ref="groupModifyDialog" :visible="config.groupModifyDialogVisible" @REFRESH_QUERY="$emit('QUERY_GROUP')" @CLOSE_GROUP_MODIFY_DIALOG="handleGroupModifyDialogClose" />
  </div>
</template>

<script>
import GroupModifyDialog from '@/components/GroupManagement/GroupModifyDialog'
import { queryGroup } from '@/api/group'
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
      this.$refs.groupModifyDialog.form = { ...item }
      // 显示窗口
      this.config.groupModifyDialogVisible = true
    },
    handleGroupModifyDialogClose() {
      // 隐藏窗口
      this.config.groupModifyDialogVisible = false
    },
    handleQueryGroup(data) {
      this.list = []
      queryGroup(data, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          this.list = r.list
          this.page.totalCount = r.totalcount
          this.$message.success('查询完毕')
        }).catch(err => {
          this.page.totalCount = err['totalCount']
        })
      this.page.currentPage = 1
    },
    handleSizeChange(size) {
      this.page.pageSize = size
      this.$emit('QUERY_GROUP')
    },
    handleCurrentChange(page) {
      this.page.currentPage = page
      this.$emit('QUERY_GROUP')
    }
  }
}
</script>

<style scoped>

</style>
