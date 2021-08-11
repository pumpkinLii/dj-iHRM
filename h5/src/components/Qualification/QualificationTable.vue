<template>
  <div>
    <el-table :data="list" stripe border fit height="300">
      <el-table-column label="人员工号" prop="agentCode" />
      <el-table-column label="人员姓名" prop="agentName" />
      <el-table-column label="二级管理机构" prop="manageComName2" />
      <el-table-column label="三级管理机构" prop="manageComName3" />
      <el-table-column label="四级管理机构" prop="manageComName4" />
      <el-table-column label="团队" prop="branchName" />
      <el-table-column label="资格证书名称" prop="certificateName" />
      <el-table-column label="资格证书号" prop="certificateNo" />
      <el-table-column label="发放日期" prop="releaseDate" />
      <el-table-column label="补发日期" prop="reissueDate" />
      <el-table-column label="有效起期" prop="startEffectiveDate" />
      <el-table-column label="有效止期" prop="endEffectiveDate" />
      <el-table-column label="批准单位" prop="approveBy" />
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
    <QualificationModifyDialog />
  </div>
</template>

<script>
import { queryGroup } from '@/api/group'
import QualificationModifyDialog from '@/components/Qualification/QualificationModifyDialog'
export default {
  name: 'QualificationTable',
  components: { QualificationModifyDialog },
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
      this.$refs.groupModifyDialog.form = item
      // 显示窗口
      this.config.groupModifyDialogVisible = true
    },
    handleGroupModifyDialogClose() {
      // 隐藏窗口
      this.config.groupModifyDialogVisible = false
    },
    handleQueryGroup(data) {
      queryGroup(data, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          this.list = r.list
          this.page.totalCount = r.totalcount
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
  }
}
</script>

<style scoped>

</style>
