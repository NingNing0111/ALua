export const BASE_URL = "localhost:8801/api/v1/";
export const HTTP_URL = "http://" + BASE_URL;
export const WEBSOCKET_URL = "ws://" + BASE_URL;

export const enum EmailApi {
  GenCode = "email/code",
}

export const enum UserApi {
  Register = "account/register",
  Login = "account/login",
  ChangeUse = "user/changeUse",
  All = "user/all",
  GetBalance = "user/getBalance",
  Logout = "account/logout",
}

export const enum ChatApi {
  Chat = "socket/",
  ChatId = "chat/create",
}

export const enum AccountApi {
  Recharge = "account/recharge",
}

export const enum MessageApi {
  Recharge = "message/query",
}
