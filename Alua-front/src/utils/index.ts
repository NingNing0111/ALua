import { Ref } from "vue";
import { ChatMessage } from "../components/constant";
import { ElMessage } from "element-plus";
import { BASE_URL, ChatApi } from "../http/constant";
import "highlight.js/styles/github.css";
import MarkdownIt from "markdown-it";

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

export const GetChatId = (len: number): string => {
  len = len || 32;
  const chatList = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
  const maxPos = chatList.length;
  let id = "";
  for (var i = 0; i < len; i++) {
    id += chatList.charAt(Math.floor(Math.random() * maxPos));
  }
  return id;
};

export const ParseMarkdown = (markStr: string) => {
  return md.render(markStr);
};

export const ChatAI = async (
  prompt: string,
  chatList: Ref<ChatMessage[]>,
  size: number
) => {
  // 如果prompt为空，则提示需要输入问题
  if (prompt.length < 1) {
    ElMessage({
      message: "请输入问题！",
      type: "error",
    });
  }
  chatList.value.push({
    prompt: prompt,
    message: "",
    html: "<p><i class='el-icon-loading'></i>AI思考中...</p>",
    done: false,
  });
  const id = GetChatId(14);
  let pending = false;
  const token = LocalDataGet("token");
  const url =
    BASE_URL +
    ChatApi.Chat +
    "/" +
    id +
    "?prompt=" +
    prompt +
    "?size=" +
    size +
    "&token=" +
    token;
  if (chatList.value.length === 0) {
    chatList.value.push({ message: "", done: false, html: "" } as ChatMessage);
  }
  const eventSource = new EventSource(url);
  console.log(prompt);

  eventSource.onopen = (event) => {
    console.log(event, "对话开启");
  };
  eventSource.addEventListener("open", (event) => {
    pending = true;
    console.log(event, "对话开启");
  });

  eventSource.addEventListener("message", (event) => {
    if (pending) {
      pending = false;
      chatList.value[chatList.value.length - 1].message = "";
    }
    console.log(event);

    try {
      let result: any = JSON.parse(event.data);
      if (result.content) {
        if (chatList.value.length === 0) {
          chatList.value.push({
            message: "",
            done: false,
            html: "",
          } as ChatMessage);
        }
        chatList.value[chatList.value.length - 1].message += result.content;
        chatList.value[chatList.value.length - 1].html = ParseMarkdown(
          chatList.value[chatList.value.length - 1].message
        );
        const articleWrapper = document.getElementById("article-wrapper");
        if (articleWrapper) {
          articleWrapper.scrollTop = 100000;
        } else {
          console.error("Element with ID `article-wrapper` not found.");
        }
      }
    } catch (error) {
      console.error(error, "接收数据过程中异常！");
    }
  });

  eventSource.addEventListener("error", (event) => {
    console.error(event);

    chatList.value[chatList.value.length - 1].done = true;

    eventSource.close();
  });

  eventSource.addEventListener("close", (event) => {
    console.log(event);
    if (chatList.value.length === 0) {
      chatList.value.push({
        message: "",
        done: false,
        html: "",
      } as ChatMessage);
    }
    chatList.value[chatList.value.length - 1].done = true;
    eventSource.close();
    console.log("对话结束");
  });
};

export const Websocket = async (
  prompt: string,
  chatList: Ref<ChatMessage[]>,
  id: string
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
  const url = "ws://127.0.0.1:8800/websocket/" + id;
  const socket = new WebSocket(url);
  socket.onopen = () => {
    console.log("websocket已连接");
    console.log(prompt, "========");
    socket.send(prompt);
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
  };
  socket.onclose = () => {
    console.log("Socket已关闭");
  };
  socket.onerror = () => {
    alert("服务异常！");
  };
  window.onunload = () => {
    socket.close();
  };
};
