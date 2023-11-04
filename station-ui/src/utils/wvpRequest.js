import axios from 'axios';
import userService from "@/utils/service/UserService";
window.baseUrl = "http://localhost:18080"

axios.defaults.baseURL = window.baseUrl
axios.defaults.withCredentials = true;
// api 返回401自动回登陆页面
axios.interceptors.response.use((response) => {
  // 对响应数据做点什么
  let token = response.headers["access-token"];
  if (token) {
    userService.setToken(token)
  }
  return response;
}, (error) => {
  // 对响应错误做点什么
  // if (error.response.status === 401) {
  //   console.log("Received 401 Response")
  //   router.push('/login');
  // }
  console.log(error);
  return Promise.reject(error);
});
axios.interceptors.request.use(
  config => {
    if (userService.getToken() != null && config.url !== "/api/user/login") {
      config.headers['access-token'] = `${userService.getToken()}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default axios
