<template>
  <div>
    <h1>报销管理</h1>

    <el-button type="primary" @click="showDialog(null)" v-if="role !== 'admin'">新增报销</el-button>

    <el-table :data="table.records">
      <el-table-column label="报销编号" prop="no" />
      <el-table-column label="用户账号ID" prop="userNo" />
      <el-table-column label="报销类型" prop="typeId" />
      <el-table-column label="报销事由" prop="bz" />
      <el-table-column label="申请状态" prop="status" />
      <el-table-column label="审批理由" prop="noAgree" />
      <el-table-column label="创建时间" prop="createTime" />
      <el-table-column label="修改时间" prop="updateTime" />
      <el-table-column label="操作" width="150">
        <template #default="{ row, $index }">
          <el-button
            :icon="Edit"
            circle
            plain
            type="primary"
            @click="showDialog(row, $index)"
          ></el-button>
          <el-button
            :icon="Delete"
            circle
            plain
            type="danger"
            @click="del(row, $index)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :page-size="table.size"
      :total="table.total"
      :current-page="table.current"
      @size-change="listAll"
      @current-change="listAll"
    />

    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="30%"
      @close="closeDialog"
    >
      <el-form
        :model="dialog.form.model"
        :rules="dialog.form.rules"
        label-width="100px"
        style="padding-right: 30px"
        ref="dialogFormRef"
      >
        <el-form-item label="报销编号" prop="no">
          <el-input v-model="dialog.form.model.no" />
        </el-form-item>
        <el-form-item label="报销类型" prop="typeId">
          <el-input v-model="dialog.form.model.typeId" />
        </el-form-item>
        <el-form-item label="报销事由" prop="bz">
          <el-input v-model="dialog.form.model.bz" />
        </el-form-item>
        <el-form-item label="申请状态" prop="status">
          <el-input v-model="dialog.form.model.status" />
        </el-form-item>
        <el-form-item label="审批理由" prop="noAgree">
          <el-input v-model="dialog.form.model.noAgree" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="saveOrEdit"> 提交 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import { Delete, Edit } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { deleteById, edit, list, save } from '@/api/reimburse.js'
import { storeToRefs } from 'pinia'
import { useUserStore } from '@/stores/index.js'

defineOptions({
  name: 'ReimburseManagement'
})

const { role } = storeToRefs(useUserStore())

const dialogFormRef = ref()
const table = ref({
  records: [],
  loading: false,
  current: 1,
  size: 10,
  total: 0
})
const dialog = reactive({
  visible: false,
  title: '',
  form: {
    model: {},
    rules: {
      no: [
        { required: true, message: '请输入报销编号', trigger: 'blur' },
      ],
      userNo: [
        { required: true, message: '请输入用户账号ID', trigger: 'blur' },
      ],
      typeId: [
        { required: true, message: '请输入报销类型', trigger: 'blur' },
      ],
      bz: [
        { required: true, message: '请输入报销事由', trigger: 'blur' },
      ],
      status: [
        { required: true, message: '请输入申请状态', trigger: 'blur' },
      ],
      noAgree: [
        { required: true, message: '请输入审批理由', trigger: 'blur' },
      ],
    }
  }
})

const showDialog = (row) => {
  if (row) {
    // edit
    dialog.title = '编辑报销信息'
    dialog.form.model = row
  } else {
    dialog.title = '新增报销'
    dialog.form.model = {}
  }
  dialog.visible = true
}

const closeDialog = () => {
  dialog.title = ''
  dialog.form.model = {}
  dialogFormRef.value.resetFields()
  dialog.visible = false
}

const listAll = async () => {
  table.value.loading = true
  const data = {
    pageNum: table.value.current,
    pageSize: table.value.size
  }
  list(data)
    .then(resp => {
      table.value = resp.data.data
      table.value.loading = false
    })
}

const saveOrEdit = () => {
  const data = dialog.form.model
  if (data.id) {
    // edit
    edit(data)
      .then(() => {
        listAll()
        closeDialog()
        ElMessage.success('更新成功')
      })
  } else {
    // save
    save(data)
      .then(() => {
        listAll()
        closeDialog()
        ElMessage.success('保存成功')
      })
  }
}

const del = async (row) => {
  await ElMessageBox.confirm('你确认要删除该报销信息么', '温馨提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  deleteById(row.id)
    .then(() => {
      ElMessage.success('删除成功')
      listAll()
    })
}

onMounted(() => {
  listAll()
})
</script>

<style scoped lang="scss">

</style>
