<template>
  <div>
    <el-descriptions
      title="账户信息"
      :column="1"
      style="line-height: 2rem; font-size: 1.2rem; color: #666"
    >
      <template slot="extra">
        <el-button type="primary" size="small" @click="updateUser"
          >更新</el-button
        >
      </template>
      <el-descriptions-item label="用户名">
        <div class="info-item">
          {{ currUser?.uName }}
        </div>
      </el-descriptions-item>

      <el-descriptions-item label="邮箱">{{
        currUser?.uName
      }}</el-descriptions-item>
      <el-descriptions-item label="积分余额"
        >{{ currUser?.uBalance
        }}<el-button
          style="margin-left: 10px"
          size="mini"
          round
          type="primary"
          @click="handleMenuClick"
          >点击充值</el-button
        ></el-descriptions-item
      >
      <el-descriptions-item label="身份">
        <el-tag size="medium ">{{ currUser?.uRole }}</el-tag>
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { User } from "./constant";
import { CheckUserInfo, LocalDataGet } from "../utils";
const currUser = ref<User>({
  uName: "admin",
  uBalance: 20.4,
  uEmail: "zdncode@gmail.com",
  uId: 5,
  uRole: "Admin",
});
const emit = defineEmits(["recharge"]);
onMounted(() => {
  if (!CheckUserInfo()) {
    currUser.value = LocalDataGet("currUser");
  }
});

const updateUser = () => {};

const handleMenuClick = () => {
  emit("recharge", "recharge");
};
</script>

<style scoped>
.el-descriptions-item {
  margin: 20px;
  height: 50px;
  background-color: #e9eef3;
}
</style>
