<script setup>
import { getDeptService } from '@/api/dept.js'
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
const deptList = ref([])
const getDeptList = async () => {
  const res = await getDeptService()
  deptList.value = res.data.data
}
//页面加载时就执行这个函数
getDeptList()
</script>

<template>
  <!-- label 展示给用户看的，value 收集起来提交给后台的 -->
  <el-select
      :modelValue="modelValue"
      @update:modelValue="emit('update:modelValue', $event)"
      :style="{ width }"
  >
    <el-option
        v-for="dept in deptList"
        :key="dept.id"
        :label="dept.name"
        :value="dept.id"
    ></el-option>
  </el-select>
</template>
