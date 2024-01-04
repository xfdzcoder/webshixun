<template>
  <page-container title="部门管理">
    <el-button @click="onAddDept"  type="primary">添加部门</el-button>
    <el-table v-loading="loading" :data="deptList" border style='width: 100%'>
      <el-table-column type="index" label="序号" width="100"></el-table-column>
      <el-table-column prop="no" label="部门编号"></el-table-column>
      <el-table-column prop="name" label="部门名称"></el-table-column>
      <el-table-column prop="address" label="部门位置"></el-table-column>
      <el-table-column prop="user" label="部门负责人"></el-table-column>
      <el-table-column label="操作" >
        <!-- row 就是 channelList 的一项， $index 下标 -->
        <template #default="{ row, $index }">
          <el-button
              type="primary"
              @click="onEditDept(row, $index)"
          >编辑</el-button>
          <el-button
              type="danger"
              @click="onDelDept(row, $index)"
          >删除</el-button>
        </template>
      </el-table-column>

      <template #empty>
        <el-empty description="没有数据"></el-empty>
      </template>
    </el-table>

    <dept-edit ref="dialog" @success="onSuccess"></dept-edit>
  </page-container>
</template>

<script setup>
import {ref} from 'vue'
import {Delete, Edit} from '@element-plus/icons-vue'
import {delDeptService, getDeptService} from '../../api/dept.js'
import DeptEdit from './components/DeptEdit.vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const deptList = ref([])
const loading = ref(false)
const dialog = ref()

const getDeptList = async () => {
  loading.value = true
  const res = await getDeptService()
  console.log("部门",res)
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



<style lang="scss" scoped></style>
