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
        :current-page.sync="page.currentPage"
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
import { queryQualification } from '@/api/qualification'
import QualificationModifyDialog from '@/components/Qualification/QualificationModifyDialog'

export default {
  name: 'QualificationTable',
  components: { QualificationModifyDialog },
  data() {
    return {
      selectOption: [],
      list: [],
      page: {
        currentPage: 1,
        totalCount: 0,
        pageSize: 10
      }
    }
  },
  mounted() {
    // 无用注释
    this.$bus.$on('QUERY1', (data) => {
      // 把表单的数据,页面大小,当前页面传给服务器
      this.page.currentPage = 1
      this.handleQueryQualification(data)
    })
    this.$bus.$on('QUERY', (data) => {
      // 把表单的数据,页面大小,当前页面传给服务器
      this.handleQueryQualification(data)
    })
    // this.$bus.$on('QUALIFICATION_SUCCESS', (data) => {
    //   this.handleQueryQualification(data)
    // })
    this.$bus.$on('RESET_QUALIFICATION_TABLE', () => {
      this.list = []
      this.page.currentPage = 1
      this.page.totalCount = 0
    })
  },
  beforeDestroy() {
    this.$bus.$off('QUERY')
  },
  methods: {
    showModifyDialog(item) {
      // console.log(this.list[row].certificateCode)
      this.$bus.$emit('OPEN_QUALIFICATION_MODIFY_DIALOG', item)
    },
    // 发送请求进行查询 得到列表
    handleQueryQualification(data) {
      queryQualification(data, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
        .then(r => {
          this.list = []
          this.list = r.list
          this.page.totalCount = r.totalcount
          this.$message.success('查询完毕')
        }).catch(() => {
          this.page.totalCount = 0
        })
    },
    handleSizeChange(size) {
      this.page.pageSize = size
      this.$bus.$emit('QUALIFICATION_SUCCESS')
    },
    handleCurrentChange(page) {
      this.page.currentPage = page
      this.$bus.$emit('QUALIFICATION_SUCCESS')
    }
  }
}
</script>

<style scoped>

</style>
