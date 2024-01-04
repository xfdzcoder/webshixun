<script setup>
import { ref } from 'vue'
import { Edit, Delete } from '@element-plus/icons-vue'
import { getDeptService, delDeptService } from '../../api/dept.js'
import DeptEdit from './components/DeptEdit.vue'
const deptList = ref([])
const loading = ref(false)
const dialog = ref()

const getDeptList = async () => {
  loading.value = true
  const res = await getDeptService()
  deptList.value = res.data.data
  loading.value = false
}
getDeptList()

const onDelDept = async (row) => {
  await ElMessageBox.confirm('你确认要删除该部门么', '温馨提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  await delDeptService(row.id)
  ElMessage.success('删除成功')
  getDeptList()
}
const onEditDept = (row) => {
  dialog.value.open(row)
}
const onAddDept = () => {
  dialog.value.open({})
}
const onSuccess = () => {
  getDeptList()
}
</script>

<template>
  <page-container title="部门管理">
    <template #extra>
      <el-button @click="onAddDept">添加部门</el-button>
    </template>

    <el-table v-loading="loading" :data="deptList" style="width: 100%">
      <el-table-column type="index" label="序号" width="100"></el-table-column>
      <el-table-column prop="no" label="部门编号"></el-table-column>
      <el-table-column prop="name" label="部门名称"></el-table-column>
      <el-table-column prop="address" label="部门位置"></el-table-column>
      <el-table-column prop="user" label="部门负责人"></el-table-column>
      <el-table-column label="操作" width="150">
        <!-- row 就是 channelList 的一项， $index 下标 -->
        <template #default="{ row, $index }">
          <el-button
              :icon="Edit"
              circle
              plain
              type="primary"
              @click="onEditDept(row, $index)"
          ></el-button>
          <el-button
              :icon="Delete"
              circle
              plain
              type="danger"
              @click="onDelDept(row, $index)"
          ></el-button>
        </template>
      </el-table-column>

      <template #empty>
        <el-empty description="没有数据"></el-empty>
      </template>
    </el-table>

    <dept-edit ref="dialog" @success="onSuccess"></dept-edit>
  </page-container>
</template>

<style lang="scss" scoped></style>
