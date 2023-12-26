export const userSvg = new URL("../assets/user.svg", import.meta.url).href;
export const openaiSvg = new URL("../assets/openai.svg", import.meta.url).href;
export const trashSvg = new URL("../assets/trash.svg", import.meta.url).href;
export const copySvg = new URL("../assets/copy.svg", import.meta.url).href;
export const sendSvg = new URL("../assets/send.svg", import.meta.url).href;
export const githubSvg = new URL("../assets/github.svg", import.meta.url).href;
export const logoSvg = new URL("../assets/logo.svg", import.meta.url).href;
export interface User {
  uId: number;
  uName: string;
  uBalance: number;
  uRole: String;
  uEmail: string;
}

export const enum Tip {
  LogoutMessage = "退出成功！",
}

export const AskDemos = [
  "推荐几个送给父母的生日礼物",
  "写一份婚礼邀请函。",
  "清朝倒数第二个皇帝是谁？",
  "写一个产品经理招聘JD",
  "写一个vue.js 组件模板",
  "三体小说讲了个什么故事？",
  "周杰伦一共几张专辑",
  "AI会替代人类工作吗？",
  "写一首赞美春天的诗",
];

export interface ChatMessage {
  prompt: string;
  message: string;
  html: string;
  done: boolean;
}
const AuthorInfo = {
  Name: "NingNing0111",
  GitHub: "https://github.com/ningning0111",
  Blog: "https://pgthinker.me",
};
export const VisitGitHub = () => {
  window.open(AuthorInfo.GitHub);
  // window.location.href = AuthorInfo.GitHub;
};
