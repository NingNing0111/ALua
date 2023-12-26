<template>
  <div class="qyai-page">
    <HeaderTabBar @select-page="selectEvent" @reset-all="resetAll" />

    <div class="chat-wrapper" v-if="currTab == 'ChatHome'">
      <Chat @del-ans="delAns" @do-ask="doAsk" :chat-list="chatList" />
      <TextArea @ask="ask" />
    </div>

    <div class="chat-wripper" v-if="currTab == 'Account'">
      <UserInfo />
    </div>
  </div>
</template>

<script setup lang="ts">
import Chat from "../components/Chat.vue";
import HeaderTabBar from "../components/HeaderTabBar.vue";
import TextArea from "../components/TextArea.vue";
import { ChatMessage } from "../components/constant";
import { Websocket, LocalDataGet } from "../utils";
import UserInfo from "./UserInfo.vue";
import { ref } from "vue";
const currTab = ref("ChatHome");

const selectEvent = (e: any) => {
  currTab.value = e;
};

const chatList = ref<ChatMessage[]>([]);
const doAsk = (e: any) => {
  ask(e);
};
const delAns = (e: any) => {
  chatList.value.splice(e, 1);
};
const ask = (e: any) => {
  console.log(e);

  Websocket(e, chatList, LocalDataGet("token"));
};
const resetAll = (b: boolean) => {
  if (b) {
    chatList.value = [];
  }
};
</script>

<style scoped lang="less">
.chat-wripper {
  height: 700px;
  width: 100%;
}
</style>
