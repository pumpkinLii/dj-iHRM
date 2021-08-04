<template>
  <div>
    <el-table :data="list" stripe border fit>
      <el-table-column label="管理机构代码" prop="manageComCode" />
      <el-table-column label="管理机构名称" prop="manageCom" />
      <el-table-column label="团队代码" prop="branchAttr" />
      <el-table-column label="团队名称" prop="branchName" />
      <el-table-column label="群聊名称" prop="chatName" />
      <el-table-column label="负责人代码" prop="branchManager" />
      <el-table-column label="负责人姓名" prop="branchManager" />
      <el-table-column label="负责人手机号" prop="branchManagerPhone" />
      <el-table-column label="当前在职代理人个数" prop="agentCount" />
      <el-table-column label="成立日期" prop="branchEffDate" />
      <el-table-column label="停业标志" prop="branchStatus" />
      <el-table-column label="停业时间" prop="branchTerminateEffDate" />
      <el-table-column label="操作员代码" prop="operator" />
      <el-table-column label="操作" width="180px" fixed="right">
        <template>
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showModifyDialog()">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->

    <GroupModifyDialog :visible="config.groupModifyDialogVisible" @CLOSE_GROUP_MODIFY_DIALOG="handleGroupModifyDialogClose" />
  </div>
</template>

<script>
import GroupModifyDialog from '@/components/GroupManagement/GroupModifyDialog'
export default {
  name: 'GroupTable',
  components: { GroupModifyDialog },
  data() {
    return {
      config: {
        groupModifyDialogVisible: false
      },
      list: [
        {
          manageComCode: 'test',
          manageCom: 'test',
          branchLevel: 'test',
          branchAttr: 'test',
          branchName: 'test',
          chatName: 'test',
          branchManager: 'test',
          branchManagerName: 'test',
          branchManagerPhone: 'test',
          agentCount: 'test',
          branchEffDate: 'test',
          branchStatus: 'test',
          branchStatusName: 'test',
          branchTerminateEffDate: 'test',
          operator: 'test'
        }
      ]
    }
  },
  methods: {
    showModifyDialog() {
      this.config.groupModifyDialogVisible = true
    },
    handleGroupModifyDialogClose() {
      this.config.groupModifyDialogVisible = false
    }
  }
}

</script>

<style scoped>

</style>
