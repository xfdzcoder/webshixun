<template>
  <page-container title="角色分类" >
    <el-button @click="onAddRole" type="primary">添加角色</el-button>
    <el-table v-loading="loading" :data="roleList" border style='width: 100%'>
      <el-table-column type="index" label="序号" width="100"></el-table-column>
      <el-table-column prop="id" label="角色ID"></el-table-column>
      <el-table-column prop="name" label="角色名称"></el-table-column>
      <el-table-column label="操作" >
        <!-- row 就是 channelList 的一项， $index 下标 -->
        <template #default="{ row, $index }">
          <el-button
            type="primary"
            @click="onEditRole(row, $index)"
          >编辑</el-button>
          <el-button
            type="danger"
            @click="onDelRole(row, $index)"
          >删除</el-button>
        </template>
      </el-table-column>

      <template #empty>
        <el-empty description="没有数据"></el-empty>
      </template>
    </el-table>

    <role-edit ref="dialog" @success="onSuccess"></role-edit>
  </page-container>
</template>

<script setup>
import { ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import { getRoleService, delRoleService } from '../../api/role'
import RoleEdit from './components/RoleEdit.vue'
const roleList = ref([])
const loading = ref(false)
const dialog = ref()

const getRoleList = async () => {
  loading.value = true
  const res = await getRoleService()
  roleList.value = res.data.data
  loading.value = false
}
getRoleList()

const onDelRole = async (row) => {
  await ElMessageBox.confirm('你确认要删除该角色么', '温馨提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  await delRoleService(row.id)
  ElMessage.success('删除成功')
  getRoleList()
}
const onEditRole = (row) => {
  dialog.value.open(row)
}
const onAddRole = () => {
  dialog.value.open({})
}
const onSuccess = () => {
  getRoleList()
}
</script>



<style lang="scss" scoped></style>
