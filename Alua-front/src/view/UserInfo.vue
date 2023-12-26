<template>
  <el-container v-if="isLogin">
    <el-aside style="margin: 10px; width: 200px; border-radius: 2%">
      <el-menu default-active="1" open="1" :default-openeds="['1']">
        <el-sub-menu index="1" :acative="1 - 1">
          <template #title>
            <el-icon><User /></el-icon>个人中心
          </template>
          <el-menu-item index="1-1" @click="handleMenuClick('account')"
            >账户信息</el-menu-item
          >
          <el-menu-item index="1-2" @click="handleMenuClick('chatInfo')"
            >对话记录</el-menu-item
          >
          <el-menu-item index="1-3" @click="handleMenuClick('recharge')"
            >余额充值</el-menu-item
          >
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-main>
      <el-row type="flex" justify="center">
        <el-col :span="24" v-if="currWindows === 'account'">
          <Account @recharge="handleAccount" />
        </el-col>
        <el-col :span="24" v-if="currWindows === 'chatInfo'">
          <HistoryPage />
        </el-col>
        <el-col :span="24" v-if="currWindows === 'recharge'">
          <Recharge />
        </el-col>
      </el-row>
    </el-main>
  </el-container>

  <el-container v-else>
    <el-main>
      <router-link to="/login">您尚未登录，请点击登录</router-link>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { CheckUserInfo } from "../utils";
import Account from "../components/Account.vue";
import HistoryPage from "../components/HistoryPage.vue";
import Recharge from "../components/Recharge.vue";

const isLogin = ref(false);
const currWindows = ref("account");
onMounted(() => {
  if (CheckUserInfo()) {
    isLogin.value = true;
  }
});
const handleMenuClick = (indexName: string) => {
  currWindows.value = indexName;
};
const handleAccount = (e: string) => {
  currWindows.value = e;
};
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  height: 700px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.el-descriptions-item {
  margin: 20px;
  height: 50px;
  background-color: #e9eef3;
}
</style>
