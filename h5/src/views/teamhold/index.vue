<template>

    <div class="app-container">
        <el-form :rules="rules" :model="teamForm" label-width="180px">
            <!-- 上部分表单 -->
            <el-row>
                <el-col :span="8">
                    <el-form-item label="管理机构" prop="manage">
                        <el-select placeholder="大家人寿总公司">
                            <el-option label="大家人寿总公司" value=""></el-option>
                            <el-option label="..." value=""></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="团队级别">
                        <el-select placeholder="请选择">
                            <el-option label="..." value=""></el-option>
                            <el-option label="..." value=""></el-option>
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
                        <el-input type="text" style="width:100%;" v-model="teamForm.manage" />
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
                        <el-input type="text" style="width:100%;" v-model="teamForm.leadmobile" />
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
                <el-col :span="24" style="padding-left:50px;">
                    <el-button type="primary">查询</el-button>
                    <el-button type="primary" @click="addDialogVisible = true">新增</el-button>
                    <el-button type="primary">下载</el-button>
                </el-col>
            </el-row>

        </el-form>
        <!-- 表格 -->
        <el-table :data="userlist" stripe border style="font-size: 8px;">
            <el-table-column label="管理机构代码"></el-table-column>
            <el-table-column label="管理机构名称"></el-table-column>
            <el-table-column label="团队代码"></el-table-column>
            <el-table-column label="团队名称"></el-table-column>
            <el-table-column label="群聊名称"></el-table-column>
            <el-table-column label="负责人代码"></el-table-column>
            <el-table-column label="负责人姓名"></el-table-column>
            <el-table-column label="负责人手机号"></el-table-column>
            <el-table-column label="当前在职代理人个数"></el-table-column>
            <el-table-column label="成立日期"></el-table-column>
            <el-table-column label="停业标志"></el-table-column>
            <el-table-column label="停业时间"></el-table-column>
            <el-table-column label="操作" width="180px">
                <template>
                    <!-- 修改 -->
                    <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog()"></el-button>
                    <!-- 删除 -->
                    <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
                    <el-tooltip class="item" effect="dark" content="" placement="top" :enterable="false">
                        <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page="queryInfo.pagenum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pagesize"
            layout="total, sizes, prev, pager, next, jumper" :total="total" style="float: right;">
        </el-pagination>

        <!-- 新增 -->
        <el-dialog title="新增" :visible.sync="addDialogVisible" width="80%" @close="addDialogClosed">
            <!-- 主体 -->
            <span>
                <el-form ref="addTeamFormRef" :model="addTeamForm" :rules="addTeamFormRules" label-width="100px">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="管理机构" prop="manage">
                                <el-select placeholder="大家人寿" v-model="addTeamForm.manage">
                                    <el-option label="大家人寿总公司" value=""></el-option>
                                    <el-option label="..." value=""></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="团队级别" prop="teamlevel">
                                <el-select placeholder="请选择" v-model="addTeamForm.teamlevel">
                                    <el-option label="..." value=""></el-option>
                                    <el-option label="..." value=""></el-option>
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
                                <el-input type="text" style="width:60%;" v-model="addTeamForm.teamname" />
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
                                <el-input type="text" style="width:60%;" v-model="teamForm.leadmobile" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="成立时间" prop="setdate">
                                <el-date-picker type="date" placeholder="选择成立时间" style="width:60%;"
                                    v-model="addTeamForm.setdate" />
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="停业标志">
                                <el-select placeholder="否" v-model="addTeamForm.teamlevel" disabled>
                                    <el-option label="是" value="yes"></el-option>
                                    <el-option label="否" value="no"></el-option>
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
                                <el-input type="text" style="width:60%;" v-model="addTeamForm.groupname" />
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
            //验证手机号的规则
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
                    leadmobile: '',
                },
                rules: {
                    manage: [{
                        required: true,
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
                //获取用户列表的参数对象
                queryInfo: {
                    query: '',
                    pagenum: 1,
                    pagesize: 2
                },
                userlist: [],
                total: 0,
                //控制添加用户对话框的显示与隐藏
                addDialogVisible: false,
                //添加用户的表单数据
                addTeamForm: {
                    manage: '',
                    teamlevel: '',
                    teamname: '',
                    setdate: '',
                    groupname: '',
                },
                addTeamFormRules: {
                    manage: [{
                        required: true,
                    }],
                    teamlevel: [{
                        required: true,
                    }],
                    teamname: [{
                        required: true,
                    }],
                    setdate: [{
                        required: true,
                    }],
                    groupname: [{
                        required: true,
                    }, ]
                }
            }
        },
        methods: {
            //监听pagesize改变的事件
            handleSizeChange(newSize) {
                // console.log(newSize)
                this.queryInfo.pagesize = newSize
                this.getUserList()
            },
            //监听 页码值 改变的事件
            handleCurrentChange(newPage) {
                // console.log(newPage)
                this.queryInfo.pagenum = newPage
                this.getUserList()
            },
            //监听添加对话框的关闭事件
            addTeamDialogClosed() {
                this.$refs.addFormRef.resetFields()
            },
            addteam() {
                this.$refs.addTeamFormRef.validate(async valid => {
                    if (!valid) return
                    //可以进行添加用户
                    const {
                        data: res
                    } = await this.$http.post("/teamManagement/save", this.addTeamForm)
                    if (res.meta.status !== 201) {
                        return this.$message.error('添加团队失败！')
                    }
                    this.$message.success('添加团队成功！')
                    //隐藏用户对话框
                    this.addTeamDialogVisible = false
                    //重新获取用户
                    // this.getUserList()
                })
            },
            //监听添加对话框的关闭事件
            addDialogClosed() {
                this.$refs.addTeamFormRef.resetFields()
            },
        }
    }
</script>

<style>

</style>