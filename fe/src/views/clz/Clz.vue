<template>
  <div>
    <el-button type="primary" @click="showDialog(null)">新增班级</el-button>

    <el-table
      :data='table.records'
      border style='width: 100%'
    >
      <el-table-column label='编号' prop='no' />
      <el-table-column label='名称' prop='name' />
      <el-table-column label='班主任' prop='user' />
      <el-table-column label='地址' prop='address' />
      <el-table-column label='操作'>
        <template #default='{ row, $index }'>
          <el-button
            type='primary'
            @click='showDialog(row, $index)'
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
        <el-form-item label="编号" prop="no">
          <el-input v-model="dialog.form.model.no" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="dialog.form.model.name" />
        </el-form-item>
        <el-form-item label="班主任" prop="user">
          <el-input v-model="dialog.form.model.user" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
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
import { ElMessage, ElMessageBox } from 'element-plus'
import { deleteById, edit, list, save } from '@/api/clz.js'

defineOptions({
  name: 'Clz'
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
        { required: true, message: '请输入班级编号', trigger: 'blur' },
        { min: 5, max: 10, message: '班级编号必须是5-10位的字符', trigger: 'blur' }
      ],
      name: [
        { required: true, message: '请输入班级名称', trigger: 'blur' },
        { trigger: 'blur' }
      ],
      address: [
        { required: true, message: '请输入班级位置', trigger: 'blur' },
        { trigger: 'blur' }
      ],
      user: [
        { required: true, message: '请输入班级负责人', trigger: 'blur' },
        { trigger: 'blur' }
      ]
    }
  }
})

const showDialog = (row) => {
  if (row) {
    // edit
    dialog.title = '编辑班级'
    dialog.form.model = row
  } else {
    dialog.title = '新增班级'
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
  list()
    .then(resp => {
      table.value.records = resp.data.data
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
  await ElMessageBox.confirm('你确认要删除该班级么', '温馨提示', {
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
