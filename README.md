# 介绍(尚未完成，目前仅用于存储代码)

> 本项目是我的一个 SpringBoot 练手项目，本科期间软件架构、软件需求分析等课程的大作业。

&emsp;Alua 是一个简单的 AI 对话网站，采用**用户积分制对话、支持卡密积分充值、支持主流大语言模型对接**。

## 技术栈

&emsp;本项目采用`前后端分离架构`,身份校验采用 JWT 技术。

&emsp;相关环境信息如下：

- MySQL 8.0
- Redis 3.2.12
- JDK >= 17
- Node >= 19.9.0

#### 前端技术栈

- 整体框架：Vue 3.x + TypeScript + Vite
- 路由：Vue Router
- 请求框架：Axios
- 组件库：Element-Plus + Weui
- ...

#### 后端技术栈

- 整体框架：SpringBoot 3.x
- ORM 框架：Mybatis-plus
- 安全框架：Spring Security 6.x
- 对话传输协议：Websocket
- 邮箱工具：Spring Email
- ...
