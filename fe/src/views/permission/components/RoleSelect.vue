<script setup>
import { getRoleService } from '@/api/role.js'
import { ref } from 'vue'

defineProps({
  modelValue: {
    type: [Number, String]
  },
  width: {
    type: String
  }
})
const emit = defineEmits(['update:modelValue'])
const roleList = ref([])
const getRoleList = async () => {
  const res = await getRoleService()
  roleList.value = res.data.data
}
//页面加载时就执行这个函数
getRoleList()
</script>

<template>
  <!-- label 展示给用户看的，value 收集起来提交给后台的 -->
  <el-select
      :modelValue="modelValue"
      @update:modelValue="emit('update:modelValue', $event)"
      :style="{ width }"
  >
    <el-option
        v-for="role in roleList"
        :key="role.id"
        :label="role.name"
        :value="role.id"
    ></el-option>
  </el-select>
</template>
