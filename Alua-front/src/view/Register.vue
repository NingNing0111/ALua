<template>
  <div class="register">
    <el-form
      class="register-container"
      :model="registerForm"
      lable-position="left"
      :ref="ruleFormRef"
      :rules="rules"
    >
      <el-form-item>
        <h1>
          <router-link to="/">{{ appName }}</router-link>
        </h1>
      </el-form-item>

      <el-form-item size="large" label="" prop="username">
        <el-input
          type="text"
          v-model="registerForm.username"
          placeholder="请输入用户名称"
        ></el-input>
      </el-form-item>

      <el-form-item size="large" label="" prop="email">
        <el-input
          type="email"
          v-model="registerForm.email"
          placeholder="请输入邮箱"
        ></el-input>
      </el-form-item>

      <el-form-item size="large" label="" prop="password">
        <el-input
          type="password"
          v-model="registerForm.password"
          placeholder="请输入密码"
          show-password
        ></el-input>
      </el-form-item>

      <el-form-item size="large" label="" prop="checkpassword">
        <el-input
          type="password"
          v-model="registerForm.checkpassword"
          placeholder="请确认密码"
          show-password
        ></el-input>
      </el-form-item>

      <el-form-item size="large" label="">
        <div style="display: flex; align-items: center; width: 100%">
          <el-input
            type="password"
            v-model="registerForm.code"
            placeholder="请输入验证码"
          ></el-input>
          <el-button
            style="margin: 10px"
            @click="sendCode"
            :disabled="disSend"
            type="primary"
          >
            {{ BtnName }}
          </el-button>
        </div>
      </el-form-item>

      <el-form-item size="large">
        <el-button type="primary" class="register-btn" @click="register"
          >注册</el-button
        >
        <span class="to-register">
          <router-link to="/">返回</router-link>
        </span>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { RegisterForm, AppConfig, rules } from "./constant";
import { ElMessage, FormInstance } from "element-plus";
import request from "../http";
import { EmailApi, UserApi } from "../http/constant";
import router from "../router";
const registerForm = ref<RegisterForm>({
  username: "",
  email: "",
  password: "",
  checkpassword: "",
  code: "",
});
const ruleFormRef = ref<FormInstance>();
const appName = AppConfig.AppName;
const BtnName = ref("发送验证码");
const disSend = ref(false);
let timer: ReturnType<typeof setInterval> | null = null;
const sendCode = () => {
  if (!checkInfo()) {
    countdown(60);
    request
      .post(EmailApi.GenCode, {
        targetEmail: registerForm.value.email,
      })
      .then((res) => {
        ElMessage({
          message: res.data.message,
          type: res.data.status,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  } else {
    ElMessage({
      message: "请填写完整信息！",
      type: "error",
    });
  }
};

const countdown = (count: number) => {
  if (!timer) {
    disSend.value = true;
    BtnName.value = `${count}秒后重发`;

    timer = setInterval(() => {
      if (count > 1) {
        count--;
        BtnName.value = `${count}秒后重发`;
      } else {
        clearInterval(timer);
        timer = null;
        disSend.value = false;
        BtnName.value = "发送验证码";
      }
    }, 1000);
  }
};

const register = () => {
  if (!checkInfo()) {
    request
      .post(UserApi.Register, registerForm)
      .then((res) => {
        ElMessage({
          message: res.data.message,
          type: res.data.status,
        });
        router.push("/");
      })
      .catch((err) => {
        console.log(err);
      });
  }
};

const checkInfo = () => {
  return (
    registerForm.value.username === "" ||
    registerForm.value.checkpassword === "" ||
    registerForm.value.email === "" ||
    registerForm.value.password === ""
  );
};

const checkPassword = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请输入密码"));
  } else {
    if (registerForm.value.checkpassword !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkpassword", () => null);
    }
    callback();
  }
};

const checkPassword2 = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请重新输入密码"));
  } else if (value !== registerForm.value.password) {
    callback(new Error("密码不相符"));
  } else {
    callback();
  }
};
rules.password = [{ validator: checkPassword, trigger: "blur" }];
rules.checkpassword = [{ validator: checkPassword2, trigger: "blur" }];
</script>

<style scoped>
.register {
  /* background-position: center; */
  height: 100%;
  width: 100%;
  /* background-size: cover; */
  /* position: fixed; */
}
body {
  margin: 0px;
  padding: 0px;
}
.register-container {
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 20px #cac6c6;
}

.register-btn {
  width: 270px;
  border: none;
  margin: 10px;
}

.to-register {
  font-size: 14px;
  font-weight: 400;
}
.to-register:hover {
  font-weight: 700;
  clear: (30, 30, 30);
}
h1 {
  margin: auto;
}
</style>
