<template>
  <div>
    <h1>学生管理</h1>

    <el-button type="primary" @click="showDialog(null)">新增学生</el-button>

    <el-table :data="table.records"       border style='width: 100%'>
      <el-table-column label="学号" prop="no" />
      <el-table-column label="姓名" prop="name" />
      <el-table-column label="班级编号" prop="classNo" />
      <el-table-column label="性别" prop="sex" />
      <el-table-column label="学历" prop="education" />
      <el-table-column label="邮箱" prop="email" />
      <el-table-column label="联系方式" prop="phone" />
      <el-table-column label="家庭住址" prop="address" />
      <el-table-column label="创建时间" prop="createTime" />
      <el-table-column label="操作" width='200px'>
        <template #default="{ row, $index }">
          <el-button
            type="primary"
            @click="showDialog(row, $index)"
          >编辑</el-button>
          <el-button
            type="danger"
            @click="del(row, $index)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
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
        <el-form-item label="学号" prop="no">
          <el-input v-model="dialog.form.model.no" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="dialog.form.model.name" />
        </el-form-item>
        <el-form-item label="班级编号" prop="classNo">
          <el-input v-model="dialog.form.model.classNo" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="dialog.form.model.sex" />
        </el-form-item>
        <el-form-item label="学历" prop="education">
          <el-input v-model="dialog.form.model.education" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="dialog.form.model.email" />
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="dialog.form.model.phone" />
        </el-form-item>
        <el-form-item label="家庭住址" prop="address">
          <el-input v-model="dialog.form.model.address" />
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
import { list, edit, save, deleteById } from '@/api/student.js'

defineOptions({
  name: 'StudentManagement'
})

const dialogFormRef = ref()
const table = ref({
  records: [],
  loading: false
})
const dialog = reactive({
  visible: false,
  title: '',
  form: {
    model: {},
    rules: {
      no: [
        { required: true, message: '请输入学号', trigger: 'blur' },
      ],
      name: [
        { required: true, message: '请输入姓名', trigger: 'blur' },
      ],
      classNo: [
        { required: true, message: '请输入班级编号', trigger: 'blur' },
      ],
      sex: [
        { required: true, message: '请输入性别', trigger: 'blur' },
      ],
      education: [
        { required: true, message: '请输入学历', trigger: 'blur' },
      ],
      email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
      ],
      phone: [
        { required: true, message: '请输入联系方式', trigger: 'blur' },
      ],
      address: [
        { required: true, message: '请输入家庭住址', trigger: 'blur' },
      ],
    }
  }
})

const showDialog = (row) => {
  if (row) {
    // edit
    dialog.title = '编辑学生信息'
    dialog.form.model = row
  } else {
    dialog.title = '新增学生'
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
  const data = {}
  list(data)
    .then(resp => {
      table.value.records = resp.data.data
      console.log('xues',resp)
      table.value = resp.data.data
      table.value.records = resp.data.data
      console.log('xues',resp)
      table.value.loading = false
    })
}

const saveOrEdit = () => {
  const data = dialog.form.model
  // Check if it's an edit or save operation
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
  await ElMessageBox.confirm('你确认要删除该学生信息么', '温馨提示', {
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
