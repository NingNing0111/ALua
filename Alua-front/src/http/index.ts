import axios from "axios";
import { HTTP_URL } from "./constant";
import { ElMessage } from "element-plus";
import { LocalDataGet } from "../utils";

const NETWORK_ERROR = "网络请求异常，请稍后重试!";

// 创建axios实例对象
const service = axios.create({
  baseURL: HTTP_URL,
  headers: {
    "Content-Type": "application/json;charset=UTF-8",
  },
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
    const token = LocalDataGet("token");
    if (token !== null) {
      config.headers.Authorization = "Bearer " + token;
    }
    return config;
  },
  (error) => {
    ElMessage.error(NETWORK_ERROR);
    return Promise.reject(error);
  }
);

export default request;
