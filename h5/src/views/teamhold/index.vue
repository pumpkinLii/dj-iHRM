<template>
  <div class="app-container">
    <h4>团队维护</h4>
    <el-form :rules="rules" :model="teamForm" label-width="180px">
      <!-- 上部分表单 -->
      <el-row>
        <el-col :span="8">
          <el-form-item label="管理机构" prop="manage">
            <el-select placeholder="大家人寿总公司">
              <el-option label="大家人寿总公司" value="" />
              <el-option label="..." value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队级别">
            <el-select placeholder="请选择">
              <el-option label="..." value="" />
              <el-option label="..." value="" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队代码" prop="teamcode">
            <el-input type="text" style="width:100%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="团队名称" prop="teamname">
            <el-input v-model="teamForm.manage" type="text" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="负责人代码" prop="leadcode">
            <el-input type="text" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="负责人姓名" prop="leadname">
            <el-input type="text" style="width:100%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="负责人手机号" prop="leadmobile">
            <el-input v-model="teamForm.leadmobile" type="text" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="成立时间" prop="setdate">
            <el-date-picker type="date" placeholder="选择成立时间" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="负责人姓名" prop="leadname">
            <el-input type="text" style="width:100%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col style="text-align:center">
          <el-button type="primary">查询</el-button>
          <el-button type="success" @click="addDialogVisible = true">新增</el-button>
        </el-col>
      </el-row>
    </el-form>
    <!-- 表格 -->
    <el-divider />
    <el-table :data="userlist" stripe border fit>
      <el-table-column label="管理机构代码" />
      <el-table-column label="管理机构名称" />
      <el-table-column label="团队代码" />
      <el-table-column label="团队名称" />
      <el-table-column label="群聊名称" />
      <el-table-column label="负责人代码" />
      <el-table-column label="负责人姓名" />
      <el-table-column label="负责人手机号" />
      <el-table-column label="当前在职代理人个数" />
      <el-table-column label="成立日期" />
      <el-table-column label="停业标志" />
      <el-table-column label="停业时间" />
      <el-table-column label="操作" width="180px" fixed="right">
        <template>
          <!-- 修改 -->
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog()" />
          <!-- 删除 -->
          <el-button type="danger" icon="el-icon-delete" size="mini" />
          <el-tooltip class="item" effect="dark" content="" placement="top" :enterable="false">
            <el-button type="warning" icon="el-icon-setting" size="mini" />
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="queryInfo.pagenum"
      :page-sizes="[1, 2, 5, 10]"
      :page-size="queryInfo.pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      style="float: right;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <!-- 新增 -->
    <el-dialog title="新增" :visible.sync="addDialogVisible" width="80%" @close="addDialogClosed">
      <!-- 主体 -->
      <span>
        <el-form ref="addTeamFormRef" :model="addTeamForm" :rules="addTeamFormRules" label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="管理机构" prop="manage">
                <el-select v-model="addTeamForm.manage" placeholder="大家人寿">
                  <el-option label="大家人寿总公司" value="" />
                  <el-option label="..." value="" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="团队级别" prop="teamlevel">
                <el-select v-model="addTeamForm.teamlevel" placeholder="请选择">
                  <el-option label="..." value="" />
                  <el-option label="..." value="" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="团队代码">
                <el-input type="text" style="width:60%;" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="团队名称" prop="teamname">
                <el-input v-model="addTeamForm.teamname" type="text" style="width:60%;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="负责人代码" prop="leadcode">
                <el-input type="text" style="width:60%;" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="负责人姓名" prop="leadname">
                <el-input type="text" style="width:60%;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="负责人手机号" prop="leadmobile">
                <el-input v-model="teamForm.leadmobile" type="text" style="width:60%;" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="成立时间" prop="setdate">
                <el-date-picker
                  v-model="addTeamForm.setdate"
                  type="date"
                  placeholder="选择成立时间"
                  style="width:60%;"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="停业标志">
                <el-select v-model="addTeamForm.teamlevel" placeholder="否" disabled>
                  <el-option label="是" value="yes" />
                  <el-option label="否" value="no" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="操作员">
                <el-input type="text" style="width:60%;" placeholder="admin" disabled />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="停业日期" prop="stopdate">
                <el-date-picker type="date" placeholder="选择成立时间" style="width:60%;" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="群聊名称" prop="groupname">
                <el-input v-model="addTeamForm.groupname" type="text" style="width:60%;" />
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
      </span>
      <!-- 底部 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addteam">保 存</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Sample',
  data() {
    // 验证手机号的规则
    var checkMobile = (rule, value, cb) => {
      const regMobile = /^1[34578]\d{9}$/
      if (regMobile.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的手机号'))
    }
    return {
      teamForm: {
        manage: '',
        leadmobile: ''
      },
      rules: {
        manage: [{
          required: true
        }],
        leadmobile: [{
          message: '请输入手机号',
          trigger: 'blur'
        },
        {
          validator: checkMobile,
          trigger: 'blur'
        }
        ]
      },
      // 获取用户列表的参数对象
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 2
      },
      userlist: [],
      total: 0,
      // 控制添加用户对话框的显示与隐藏
      addDialogVisible: false,
      // 添加用户的表单数据
      addTeamForm: {
        manage: '',
        teamlevel: '',
        teamname: '',
        setdate: '',
        groupname: ''
      },
      addTeamFormRules: {
        manage: [{
          required: true,
          message: '请选择管理机构'
        }],
        teamlevel: [{
          required: true,
          message: '请选择团队级别'
        }],
        teamname: [{
          required: true,
          message: '请输入团队名称'
        }],
        setdate: [{
          required: true,
          message: '请确定成立时间'
        }],
        groupname: [{
          required: true,
          message: '请输入群聊名称'
        }]
      }
    }
  },
  methods: {
    // 监听pagesize改变的事件
    handleSizeChange(newSize) {
      // console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getUserList()
    },
    // 监听 页码值 改变的事件
    handleCurrentChange(newPage) {
      // console.log(newPage)
      this.queryInfo.pagenum = newPage
      this.getUserList()
    },
    // 监听添加对话框的关闭事件
    addTeamDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    addteam() {
      this.$refs.addTeamFormRef.validate(async valid => {
        if (!valid) return
        // 可以进行添加团队
        const {
          data: res
        } = await this.$http.post('/teamManagement/save', this.addTeamForm)
        if (res.meta.status !== 201) {
          return this.$message.error('添加团队失败！')
        }
        this.$message.success('添加团队成功！')
        // 隐藏用户对话框
        this.addTeamDialogVisible = false
        // 重新获取用户
        // this.getUserList()
      })
    },
    // 监听添加对话框的关闭事件
    addDialogClosed() {
      this.$refs.addTeamFormRef.resetFields()
    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
