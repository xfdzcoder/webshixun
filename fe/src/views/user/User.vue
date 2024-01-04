<script setup>
import { ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import { getUserService, delUserService } from '../../api/user.js'
import UserEdit from './components/UserEdit.vue'
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

<template>
  <page-container title="用户管理">
    <template #extra>
      <el-button @click="onAddUser">添加用户</el-button>
    </template>

    <el-table v-loading="loading" :data="userList" style="width: 100%">
      <el-table-column type="index" label="序号" width="100"></el-table-column>
      <el-table-column prop="account" label="用户名称"></el-table-column>
      <el-table-column prop="roleId" label="角色ID"></el-table-column>
      <el-table-column label="操作" width="150">
        <!-- row 就是 channelList 的一项， $index 下标 -->
        <template #default="{ row, $index }">
          <el-button
              :icon="Edit"
              circle
              plain
              type="primary"
              @click="onEditUser(row, $index)"
          ></el-button>
          <el-button
              :icon="Delete"
              circle
              plain
              type="danger"
              @click="onDelUser(row, $index)"
          ></el-button>
        </template>
      </el-table-column>

      <template #empty>
        <el-empty description="没有数据"></el-empty>
      </template>
    </el-table>

    <user-edit ref="dialog" @success="onSuccess"></user-edit>
  </page-container>
</template>

<style lang="scss" scoped></style>
