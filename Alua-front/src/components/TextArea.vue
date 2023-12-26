<template>
  <div class="chat-footer">
    <div class="chat-footer-bar">
      <div class="chat-left">
        <textarea
          class="chat-input"
          v-model="prompt"
          :placeholder="prompt_holder"
          rows="1"
        ></textarea>
      </div>
      <div class="chat-right">
        <div class="chat-submit" @click="ask">
          <img :src="sendSvg" width="100%" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { CheckUserInfo } from "../utils";
import { ElMessage } from "element-plus";
import { sendSvg } from "./constant";

const emit = defineEmits(["ask"]);
const prompt = ref("");
const prompt_holder = ref("请输入你的问题");

const ask = () => {
  if (!CheckUserInfo()) {
    ElMessage({
      message: "您尚未登录，请先登录再发起对话",
      type: "error",
    });
  } else {
    emit("ask", prompt.value);
    prompt.value = "";
  }
};
</script>

<style scoped lang="less"></style>
