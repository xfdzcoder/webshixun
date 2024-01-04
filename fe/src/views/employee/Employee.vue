<script setup>
import {ref, onMounted} from 'vue'
import {Delete,Edit} from '@element-plus/icons-vue'
import DeptSelect from './components/DeptSelect.vue'
import EmployeeEdit from './components/EmployeeEdit.vue'
import {employeeGetListService, employeeDelService} from '@/api/employee.js'
import {formatTime} from '@/utils/format.js'


const employeeList = ref([]) // 员工列表
const total = ref(0) // 总条数
const loading = ref(false) // loading状态

// 定义请求参数对象
const params = ref({
  pagenum: 1, // 当前页
  pagesize: 5, // 当前生效的每页条数
  deptId: ''
})

// 基于params参数，获取员工列表
const getEmployeeList = () => {
  loading.value = true
  employeeGetListService(params.value)
      .then(res => {
        employeeList.value = res.data.data.records
        total.value = res.data.data.total
      })
      .finally(() => {
        loading.value = false
      })
  // const res = await employeeGetListService(params.value)
  // employeeList.value = res.data.data
  // total.value = res.data.data.total
  // loading.value = false
}


// 处理分页逻辑
const onSizeChange = (size) => {
  // console.log('当前每页条数', size)
  // 只要是每页条数变化了，那么原本正在访问的当前页意义不大了，数据大概率已经不在原来那一页了
  // 重新从第一页渲染即可
  params.value.pagenum = 1
  params.value.pagesize = size
  // 基于最新的当前页 和 每页条数，渲染数据
  getEmployeeList()
}
const onCurrentChange = (page) => {
  // 更新当前页
  params.value.pagenum = page
  // 基于最新的当前页，渲染数据
  getEmployeeList()
}

// 搜索逻辑 => 按照最新的条件，重新检索，从第一页开始展示
const onSearch = () => {
  params.value.pagenum = 1 // 重置页面
  getEmployeeList()
}

// 重置逻辑 => 将筛选条件清空，重新检索，从第一页开始展示
const onReset = () => {
  params.value.pagenum = 1 // 重置页面
  params.value.deptId = ''
  getEmployeeList()
}

const employeeEditRef = ref()
// 添加逻辑
const onAddEmployee = () => {
  employeeEditRef.value.open({})
}
// 编辑逻辑
const onEditEmployee = (row) => {
  employeeEditRef.value.open(row)
}

// 删除逻辑
const onDeleteEmployee = async (row) => {
  // 提示用户是否要删除
  await ElMessageBox.confirm('此操作将永久删除该员工, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  await employeeDelService(row.id)
  ElMessage.success('删除成功')
  // 重新渲染列表
  getEmployeeList()
}

// 添加或者编辑 成功的回调
const onSuccess = (type) => {
  if (type === 'add') {
    // 如果是添加，最好渲染最后一页
    const lastPage = Math.ceil((total.value + 1) / params.value.pagesize)
    // 更新成最大页码数，再渲染
    params.value.pagenum = lastPage
  }

  getEmployeeList()
}

onMounted(() => {
  getEmployeeList()
})
</script>

<template>
  <page-container title="员工管理">
    <template #extra>
      <el-button @click="onAddEmployee">添加员工</el-button>
    </template>

    <!-- 表单区域 -->
    <el-form inline>
      <el-form-item label="部门:">
        <!-- Vue2 => v-model :value 和 @input 的简写 -->
        <!-- Vue3 => v-model :modelValue 和 @update:modelValue 的简写 -->
        <dept-select v-model="params.deptId"></dept-select>

        <!-- Vue3 => v-model:cid  :cid 和 @update:cid 的简写 -->
        <!-- <channel-select v-model:cid="params.cate_id"></channel-select> -->
      </el-form-item>

      <el-form-item>
        <el-button @click="onSearch" type="primary">搜索</el-button>
        <el-button @click="onReset">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格区域 -->
    <el-table :data="employeeList" v-loading="loading">
      <el-table-column label="员工编号" prop="no">
        <template #default="{ row }">
          <el-link type="primary" :underline="false">{{ row.no }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="员工姓名" prop="name">
        <template #default="{ row }">
          <el-link type="primary" :underline="false">{{ row.name }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="性别" prop="sex">
        <template #default="{ row }">
          <el-link type="primary" :underline="false">{{ row.sex }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="部门ID" prop="deptId"></el-table-column>
      <el-table-column label="入职时间" prop="entryTime">
        <template #default="{ row }">
          {{ formatTime(row.entryTime) }}
        </template>
      </el-table-column>
      <!-- 利用作用域插槽 row 可以获取当前行的数据 => v-for 遍历 item -->
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button
              :icon="Edit"
              circle
              plain
              type="primary"
              @click="onEditEmployee(row)"
          ></el-button>
          <el-button
              circle
              plain
              type="danger"
              :icon="Delete"
              @click="onDeleteEmployee(row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页区域 -->
    <el-pagination
        v-model:current-page="params.pagenum"
        v-model:page-size="params.pagesize"
        :page-sizes="[2, 3, 5, 10]"
        :background="true"
        layout="jumper, total, sizes, prev, pager, next"
        :total="total"
        @size-change="onSizeChange"
        @current-change="onCurrentChange"
        style="margin-top: 20px; justify-content: flex-end"
    />

    <!-- 添加编辑的抽屉 -->
    <employee-edit ref="employeeEditRef" @success="onSuccess"></employee-edit>
  </page-container>
</template>

<style lang="scss" scoped></style>
