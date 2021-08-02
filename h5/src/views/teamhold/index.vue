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
                        <el-input type="text" style="width:100%;" v-model="teamForm.manage"/>
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
                    <el-button type="primary">新增</el-button>
                    <el-button type="primary">下载</el-button>
                </el-col>
            </el-row>
            <!-- 表格 -->
        </el-form>

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
    </div>
</template>

<script>
    export default {
        name: 'TeamHold',
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
                        }
                    ],
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
        }
    }
</script>

<style>

</style>