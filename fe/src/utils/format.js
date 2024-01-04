import { dayjs } from 'element-plus'

export const formatTime = (time) => dayjs(time).format('YYYY-MM-DD')
export const formatTime1 = (time) => dayjs(time).format('YYYY-MM-DD hh:mm:ss')