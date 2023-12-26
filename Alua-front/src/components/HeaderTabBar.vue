<template>
  <div id="page-header">
    <img
      class="header-logo"
      :src="logoSvg"
      @click="selectPage('ChatHome'), resetAll()"
    />
    <div class="header-right">
      <div class="header-user-wrapper">
        <div class="header-user-btn" @click="VisitGitHub">
          <img :src="githubSvg" alt="" />
          <div class="header-user-name">GitHub</div>
        </div>
      </div>
      <div class="header-user-wrapper">
        <div
          class="header-user-btn"
          @click="selectPage('Account')"
          v-if="isLogin"
        >
          <img :src="userSvg" alt="" />
          <div class="header-user-name">
            {{ currUser?.uName }}
          </div>
        </div>
        <div style="margin-left: 10px" v-else>
          <el-button type="primary" @click="toLogin">点击登录</el-button>
          <!-- <div class="header-user-name">点击登录</div> -->
        </div>
      </div>

      <div class="header-user-wrapper">
        <div v-if="isLogin" style="margin: 10px">
          <el-button @click="logout" type="primary"> 退出 </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import {
  User,
  Tip,
  VisitGitHub,
  githubSvg,
  logoSvg,
  userSvg,
} from "./constant";
import router from "../router";
import { ElMessage } from "element-plus";
import { LocalDataGet, CheckUserInfo, DeleteUserInfo } from "../utils";
const emit = defineEmits(["select-page", "reset-all"]);

const isLogin = ref(false);
const currUser = ref<User>({
  uId: -1,
  uName: "未知",
  uBalance: 0,
  uRole: "",
  uEmail: "",
});

onMounted(() => {
  checkInfo();
});

const checkInfo = () => {
  if (CheckUserInfo()) {
    const user: any = LocalDataGet("currUser");
    if (user !== null) {
      currUser.value.uBalance = user.ubalance;
      currUser.value.uId = user.uid;
      currUser.value.uName = user.uname;
      currUser.value.uRole = user.upermission;
      currUser.value.uEmail = user.uemail;
      isLogin.value = true;
    }
  }
};

const logout = () => {
  DeleteUserInfo();
  setTimeout(() => {
    router.push("/");
  }, 1000);
  ElMessage({
    message: Tip.LogoutMessage,
    type: "success",
  });
};

const toLogin = () => {
  router.push("/login");
};

const selectPage = (pageName: string) => {
  emit("select-page", pageName);
};
const resetAll = () => {
  emit("reset-all", true);
};
</script>

<style scoped lang="less"></style>
