<template>
  <page-container title='请假管理'>
    <!-- 表单区域 -->
    <el-form inline>
        <el-button type='primary' @click='onAddHoliday'>添加请假</el-button>
    </el-form>

    <!-- 表格区域 -->
    <el-table :data='holidayList' v-loading='loading' border style='width: 100%'>
      <el-table-column label='请假编号' prop='no'/>
      <el-table-column label='申请人' prop='userNo'/>
      <el-table-column label='请假类型' prop='typeId'></el-table-column>
      <el-table-column label='请假事由' prop='bz'/>
      <el-table-column label='开始时间' prop='startTime'/>
      <el-table-column label='结束时间' prop='endTime'/>
      <el-table-column label='提交时间' prop='createTime'/>
      <!-- 利用作用域插槽 row 可以获取当前行的数据 => v-for 遍历 item -->
      <el-table-column label='操作'>
        <template #default='{ row }'>
          <el-button
            type='primary'
            @click='onEditHoliday(row)'
          >编辑</el-button>
          <el-button
            type='danger'
            :icon='Delete'
            @click='onDeleteHoliday(row)'
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <holiday-edit ref='holidayEditRef' @success='onSuccess'></holiday-edit>
  </page-container>
</template>
<script setup>
import { onMounted, ref } from 'vue'
import { Delete, Edit } from '@element-plus/icons-vue'
import ConfigSelect from './components/ConfigSelect.vue'
import HolidayEdit from './components/HolidayEdit.vue'
import { holidayDelService, holidayGetListService, holidayGetListService1 } from '@/api/holiday.js'
import { formatTime, formatTime1 } from '@/utils/format.js'
import { useOKStore, useUserStore } from '@/stores/index.js'

const holidayList = ref([]) //请假列表
const total = ref(0) // 总条数
const loading = ref(false) // loading状态
const userStore = useUserStore()
const useOk = useOKStore()

const flag = ref(false)
onMounted(() => {
  if (useOk.ok8 || useOk.ok9) {
    flag.value = true
  }
})

// 定义请求参数对象
const params = ref({
  pagenum: 1, // 当前页
  pagesize: 5, // 当前生效的每页条数
  typeId: '',
  status: '',
  userNo: userStore.user.account
})

// 基于params参数，获取文章列表
const getHolidayList = () => {
  loading.value = true
  if (useOk.ok8 || useOk.ok9) {
    holidayGetListService1(params.value)
      .then(res => {
        holidayList.value = res.data.data.records
        total.value = res.data.data.total
      })
      .finally(() => {
        loading.value = false
      })
  }else {
    holidayGetListService(params.value)
      .then(res => {
        holidayList.value = res.data.data.records
        total.value = res.data.data.total
      })
      .finally(() => {
        loading.value = false
      })
  }
}

// 处理分页逻辑
const onSizeChange = (size) => {
  // console.log('当前每页条数', size)
  // 只要是每页条数变化了，那么原本正在访问的当前页意义不大了，数据大概率已经不在原来那一页了
  // 重新从第一页渲染即可
  params.value.pagenum = 1
  params.value.pagesize = size
  // 基于最新的当前页 和 每页条数，渲染数据
  getHolidayList()
}
const onCurrentChange = (page) => {
  // 更新当前页
  params.value.pagenum = page
  // 基于最新的当前页，渲染数据
  getHolidayList()
}

// 搜索逻辑 => 按照最新的条件，重新检索，从第一页开始展示
const onSearch = () => {
  params.value.pagenum = 1 // 重置页面
  getHolidayList()
}

// 重置逻辑 => 将筛选条件清空，重新检索，从第一页开始展示
const onReset = () => {
  params.value.pagenum = 1 // 重置页面
  params.value.typeId = ''
  params.value.state = ''
  getHolidayList()
}

const holidayEditRef = ref()
// 添加逻辑
const onAddHoliday = () => {
  holidayEditRef.value.open({})
}
// 编辑逻辑
const onEditHoliday = (row) => {
  holidayEditRef.value.open(row)
}

// 删除逻辑
const onDeleteHoliday = async (row) => {
  // 提示用户是否要删除
  await ElMessageBox.confirm('此操作将永久删除该记录, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
  await holidayDelService(row.id)
  ElMessage.success('删除成功')
  // 重新渲染列表
  getHolidayList()
}

// 添加或者编辑 成功的回调
const onSuccess = (type) => {
  if (type === 'add') {
    // 如果是添加，最好渲染最后一页
    const lastPage = Math.ceil((total.value + 1) / params.value.pagesize)
    // 更新成最大页码数，再渲染
    params.value.pagenum = lastPage
  }

  getHolidayList()
}

onMounted(() => {
  getHolidayList()
})
</script>


<style lang="scss" scoped></style>
