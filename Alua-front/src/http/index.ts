import axios from "axios";
import { BASE_URL } from "./constant";
import { ElMessage } from "element-plus";

const NETWORK_ERROR = "网络请求异常，请稍后重试!";

// 创建axios实例对象
const service = axios.create({
  baseURL: BASE_URL,
  // headers: { "Content-Type": "application/json" },
});

// 封装通用请求方法
const request = {
  get(url: string, params?: any) {
    return service.get(url, { params });
  },
  post(url: string, data?: any) {
    return service.post(url, data);
  },
  put(url: string, data?: any) {
    return service.put(url, data);
  },
  delete(url: string, params?: any) {
    return service.delete(url, { params });
  },
};

// 请求拦截
service.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    ElMessage.error(NETWORK_ERROR);
    return Promise.reject(error);
  }
);

// 响应拦截
service.interceptors.response.use(
  (response) => {
    const data = response.data;
    // 这里可以根据后端的约定来处理响应
    if (data.code !== 200) {
      ElMessage.error(data.message || NETWORK_ERROR);
      return Promise.reject(new Error(data.message || NETWORK_ERROR));
    }
    return data;
  },
  (error) => {
    ElMessage.error(error.message || NETWORK_ERROR);
    return Promise.reject(error);
  }
);

export default request;
