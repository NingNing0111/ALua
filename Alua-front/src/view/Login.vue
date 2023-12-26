<template>
  <div class="login">
    <el-form
      class="login-container"
      :model="loginForm"
      label-width="0px"
      lable-position="left"
    >
      <el-form-item size="large">
        <h1>
          <router-link to="/">{{ appName }}</router-link>
        </h1>
      </el-form-item>
      <el-form-item size="large">
        <el-input
          type="text"
          v-model="loginForm.email"
          placeholder="邮箱"
        ></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-input
          type="password"
          v-model="loginForm.password"
          placeholder="密码"
        ></el-input>
      </el-form-item>

      <el-form-item size="large">
        <el-button
          type="primary"
          class="login-btn"
          @click="onLogin"
          :disabled="isLogin"
          >登录</el-button
        >
        <span class="to-register">
          <router-link to="/register">点击注册</router-link>
        </span>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { LoginForm, AppConfig } from "./constant";
import request from "../http";
import { UserApi } from "../http/constant";
import { ElMessage } from "element-plus";
import { LocalDataSave } from "../utils";
import router from "../router";
const loginForm = ref<LoginForm>({
  email: "",
  password: "",
});
const appName = AppConfig.AppName;
const isLogin = ref(false);

const onLogin = () => {
  isLogin.value = true;
  request
    .post(UserApi.Login, loginForm.value)
    .then((res) => {
      if (res.data.state === "error") {
        ElMessage({
          message: res.data.message,
          type: res.data.state,
        });
      } else if (res.data.state === "success") {
        const token = res.data.token;
        const user = res.data.user;
        LocalDataSave("token", token);
        LocalDataSave("currUser", user);
        setTimeout(() => {
          router.push("/");
        }, 1000);
        ElMessage({
          message: res.data.message,
          type: res.data.state,
        });
        console.log(res);
      }
      isLogin.value = false;
    })
    .catch((err) => {
      console.log(err);
      isLogin.value = false;
    });
};
</script>

<style scoped>
.login {
  width: 100%;
  height: 100%;
}
body {
  margin: 0px;
  padding: 0px;
}
.login-container {
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 20px #cac6c6;
}

.to-register {
  font-size: 14px;
  font-weight: 400;
}
.to-register:hover {
  font-weight: 800;
  font-size: 15px;
  clear: (30, 30, 30);
}
.login-btn {
  width: 270px;
  border: none;
  margin: 10px;
}

h1 {
  margin: auto;
}
</style>
