<template>
  <page-container title='用户管理'>

    <div>
      <el-button @click='onAddUser' type='primary'>添加用户</el-button>
    </div>
    <el-table v-loading='loading' :data='userList' border style='width: 100%'>
      <el-table-column type='index' label='序号' width='100'></el-table-column>
      <el-table-column prop='account' label='用户名称'></el-table-column>
      <el-table-column prop='roleId' label='角色ID'>
        <template v-slot='scope'>
          <el-tag type='success' v-if="scope.row.roleId ==='1'">学生</el-tag>
          <el-tag type='info' v-if="scope.row.roleId === '2'">老师</el-tag>
          <el-tag type='danger' v-if="scope.row.roleId === '3'">管理员</el-tag>
        </template>

      </el-table-column>
      <el-table-column label='操作'>
        <!-- row 就是 channelList 的一项， $index 下标 -->
        <template #default='{ row, $index }'>
          <el-button
            type='primary'
            @click='onEditUser(row, $index)'
          >编辑
          </el-button>
          <el-button
            type='danger'
            @click='onDelUser(row, $index)'
          >删除
          </el-button>
        </template>
      </el-table-column>

      <template #empty>
        <el-empty description='没有数据'></el-empty>
      </template>
    </el-table>

    <user-edit ref='dialog' @success='onSuccess'></user-edit>
  </page-container>
</template>
<script setup>
import { ref } from 'vue'
import { delUserService, getUserService } from '../../api/user.js'
import UserEdit from './components/UserEdit.vue'
import { ElMessageBox } from 'element-plus'

const userList = ref([])
const loading = ref(false)
const dialog = ref()

const getUserList = async () => {
  loading.value = true
  const res = await getUserService()
  userList.value = res.data.data
  loading.value = false
}
getUserList()

const onDelUser = async (row) => {
  await ElMessageBox.confirm('你确认要删除该用户么', '温馨提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  await delUserService(row.id)
  ElMessage.success('删除成功')
  getUserList()
}
const onEditUser = (row) => {
  dialog.value.open(row)
}
const onAddUser = () => {
  dialog.value.open({})
}
const onSuccess = () => {
  getUserList()
}
</script>


<style lang="scss" scoped></style>
