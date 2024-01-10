import { Ref } from "vue";
import { ChatMessage } from "../components/constant";
import { ElMessage } from "element-plus";
import { ChatApi, WEBSOCKET_URL } from "../http/constant";
import request from "../http";
import "highlight.js/styles/github.css";
import MarkdownIt from "markdown-it";
import { log } from "console";

const md = MarkdownIt();
export const LocalDataSave = (key: string, value: any) => {
  localStorage.setItem(key, JSON.stringify(value));
};

export const LocalDataGet = (key: string): any => {
  const result = localStorage.getItem(key);
  if (result === null) {
    return null;
  } else {
    return JSON.parse(result);
  }
};

export const CheckUserInfo = () => {
  const token = localStorage.getItem("token");
  const currUser = localStorage.getItem("currUser");
  return token !== null && currUser !== null;
};

export const DeleteUserInfo = () => {
  if (CheckUserInfo()) {
    localStorage.removeItem("token");
    localStorage.removeItem("currUser");
  }
};

export const ParseMarkdown = (markStr: string) => {
  return md.render(markStr);
};

export const WebSocketChat = async (
  prompt: string,
  chatList: Ref<ChatMessage[]>
) => {
  if (prompt.length < 1) {
    ElMessage({
      message: "请输入问题！",
      type: "error",
    });
    return;
  }
  chatList.value.push({
    prompt: prompt,
    message: "",
    html: "<p><i class='el-icon-loading'></i>AI思考中...</p>",
    done: false,
  });

  const currUser: string = LocalDataGet("currUser");
  const chatId: string = LocalDataGet("chatId");

  if (chatId == null) {
    request
      .get(ChatApi.ChatId, {
        username: currUser,
      })
      .then((res) => {
        console.log(res);
        if (res.data.status === "success") {
          LocalDataSave("chatId", res.data.chatId);
          openSocket(prompt, chatList, res.data.chatId);
        }
      })
      .catch((err) => {
        console.log(err);
      });
    return;
  }
  openSocket(prompt, chatList, chatId);
};

export const openSocket = async (
  prompt: string,
  chatList: Ref<ChatMessage[]>,
  chatId: string
) => {
  console.log("连接ID:", chatId);

  const socket = new WebSocket(WEBSOCKET_URL + ChatApi.Chat + chatId);

  const timeoutId = setTimeout(() => {
    if (socket.readyState === WebSocket.CONNECTING) {
      console.log("连接超时");
      socket.close();
    }
  }, 10000);

  socket.onopen = () => {
    console.log("websocket已连接");
    socket.send(prompt);
    clearTimeout(timeoutId);
  };

  socket.onmessage = (msg) => {
    if (msg.data === "[DONE]") {
      return;
    } else {
      let jsonData = JSON.parse(msg.data);
      if (jsonData.content) {
        chatList.value[chatList.value.length - 1].message += jsonData.content;
        chatList.value[chatList.value.length - 1].html = ParseMarkdown(
          chatList.value[chatList.value.length - 1].message
        );
      }
    }

    const articleWrapper = document.getElementById("article-wrapper");
    if (articleWrapper) {
      articleWrapper.scrollTop = 100000;
    } else {
      console.error("Element with ID `article-wrapper` not found.");
    }
    clearTimeout(timeoutId);
  };
  socket.onclose = () => {
    console.log("Socket已关闭");
    localStorage.removeItem("chatId");
    clearTimeout(timeoutId);
  };
  socket.onerror = () => {
    alert("服务异常！");
    clearTimeout(timeoutId);
  };
  window.onunload = () => {
    socket.close();
    clearTimeout(timeoutId);
  };
};
