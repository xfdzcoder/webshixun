<script setup>
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
</script>

<template>
  <div>
    <!--    App.vue只需要留一个路由出口 router-view即可-->
    <!--    设置全部组件中的element-plus的语言包类型为中文 el-config-provider :locale="zhCn"-->
    <el-config-provider :locale="zhCn">
      <router-view></router-view>
    </el-config-provider>
  </div>
</template>

<style scoped>

</style>
