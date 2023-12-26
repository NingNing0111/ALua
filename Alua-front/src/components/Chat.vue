<template>
  <div class="chat-body" id="article-wrapper">
    <div class="chat-questions" v-if="chatList.length > 0">
      <div class="ask-item" v-for="(item, index) in chatList" :key="index">
        <div class="ask-question">
          <div class="ask-photo">
            <img :src="userSvg" alt="" />
          </div>
          <div class="ask-text">{{ item.prompt }}</div>
        </div>
        <div class="ask-answer" @click="scrollToBottom">
          <div class="ask-photo">
            <div class="ask-photo-ai">
              <img :src="openaiSvg" alt="" />
            </div>
          </div>
          <div class="ask-text" :id="'text-' + index" v-html="item.html"></div>
        </div>

        <div class="ask-operations" v-if="item.done">
          <div
            class="operations_item copy"
            :data-clipboard-target="'#text-' + index"
            title="复制文本"
          >
            <img :src="copySvg" />
            <span>复制</span>
          </div>
          <div class="operations_item" title="删除" @click="delAns(index)">
            <img :src="trashSvg" />
            <span>删除</span>
          </div>
        </div>
      </div>
    </div>

    <div class="chat-tutorial" v-else>
      <div class="chat-examples">
        <header>
          <h1 style="left: 0%">您可以这样提问</h1>
        </header>
      </div>
      <div class="chat-examples">
        <div
          class="chat-example"
          v-for="(item, index) in AskDemos"
          :key="index"
          @click="doAsk(item)"
        >
          <div class="chat-exp-text">{{ item }}</div>
        </div>
      </div>
      <div class="chat-about">
        <span>单次提问消耗</span>
        <div>3点积分</div>
      </div>
      <div class="chat-about">
        <span>对话记录数越多所需积分也会越高</span>
      </div>
      <div class="chat-notice">
        <div>
          禁止提交违规内容，违规内容会被系统拦截，严重者可能会被封禁账号。
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from "element-plus";
import { CheckUserInfo } from "../utils";
import {
  AskDemos,
  ChatMessage,
  copySvg,
  userSvg,
  trashSvg,
  openaiSvg,
} from "./constant";
defineProps({
  chatList: {
    type: Array as () => ChatMessage[],
    required: true,
  },
});

const emit = defineEmits(["del-ans", "do-ask"]);

const scrollToBottom = () => {
  window.scrollTo(0, document.documentElement.scrollHeight);
};

const delAns = (index: number) => {
  emit("del-ans", index);
};
const doAsk = (question: string) => {
  if (!CheckUserInfo()) {
    ElMessage({
      message: "您尚未登录，请先登录再发起对话",
      type: "error",
    });
  } else {
    emit("do-ask", question);
  }
};
</script>

<style scoped lang="less"></style>
