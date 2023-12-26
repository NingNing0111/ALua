<template>
  <div class="app">
    <el-container>
      <el-header style="background-color: transparent">
        <div class="header-text">
          <h1>ChatGPT 充值</h1>
        </div>
      </el-header>
      <el-row type="flex" class="row-bg" justify="center">
        <el-col :span="8" style="margin: 20px">
          <el-form :model="rechargeForm">
            <el-form-item
              label="邮箱:"
              label-position="left"
              prop="uEmail"
              :rules="[
                {
                  type: 'email',
                  message: '请输入正确的邮箱地址',
                  trigger: ['blur', 'change'],
                },
              ]"
            >
              <el-input
                v-model="rechargeForm.uEmail"
                placeholder="请输入充值邮箱"
              ></el-input>
            </el-form-item>
            <el-form-item label="卡密:" label-position="left">
              <el-input
                v-model="rechargeForm.key"
                placeholder="请输入充值卡密"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <div class="btn">
                <el-button type="primary" @click="recharge" size="large"
                  >充值</el-button
                >
              </div>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const rechargeForm = ref({
  uEmail: "",
  key: "",
});

const recharge = () => {};
</script>

<style scoped>
.container {
  margin: auto;
  width: 40%;
  height: 40%;
  padding: 20px;
  position: relative;
  top: 12%;
}

.input {
  margin-bottom: 10px;
  width: 80%;
  height: 50px;
}
.btn {
  display: flex;
  width: 100%;
  justify-content: center;
}

h1 {
  position: relative;
  left: 25%;
  width: 50%;
  text-align: center;
  padding-top: 20px;
}

.header-text {
  font-size: 20px;
}
</style>
