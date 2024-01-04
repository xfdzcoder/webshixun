import axios from 'axios'
import { useUserStore,useOKStore } from "@/stores";
import { ElMessage } from 'element-plus'
import router from '@/router'
const baseURL = 'http://localhost:8080'

const instance = axios.create({
    // TODO 1. 基础地址，超时时间
    baseURL,
    timeout: 100000
})

//请求拦截器
instance.interceptors.request.use(
    (config) => {
        // TODO 2. 携带token
        const useStore = useUserStore()
        // 判断是否存在token，如果存在的话，则每个http header都加上token
        if (useStore.token) {//条件这么写是因为在login.vue的页面把token存入了localStorage的wxToken中
            config.headers.token = useStore.token
        }
        return config //赋值完后把config返回回去
    },
    (err) => Promise.reject(err)   // 请求错误后把我们的error返回回去
)

//响应拦截器
instance.interceptors.response.use(
    (res) => {

        // TODO 4. 摘取核心响应数据
        if (res.data.code === 0) {
            return res
        }
        // TODO 3. 处理业务失败
        ElMessage.error(res.data.message || '服务异常')
        return Promise.reject(res.data)
    },
    (err) => {
        const useStore = useUserStore()
        const ok = useOKStore()
        // TODO 5. 处理401错误
        //错误的特殊情况 => 401 后端定义401为：权限不足 或 token过期 => 拦截到登录
        //err.response.data?.code 判断data对象，如果err.response.data是undefind将不再往下"."
        if (err.response?.status === 401 || err.response.data?.code === '50000') {
            // 清空本地存储的 token 和 个人信息
            useStore.setToken('')
            useStore.setUser({})
            ok.setok('')
            // 重定向到登录页面
            router.push('/login')
        }

        //错误的默认情况 => 只要给提示就可以了
        ElMessage.error(err.response.data.message || '服务异常')
        return Promise.reject(err)
    }
)

export default instance
export { baseURL }