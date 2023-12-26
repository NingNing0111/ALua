export const BASE_URL = "http://127.0.0.1:8887/"

export const enum EmailApi {
    GenCode = "email/register"
}

export const enum UserApi {
    Register = "user/register",
    Login = "user/login",
    ChangeUse = "user/changeUse",
    All = "user/all",
    GetBalance = "user/getBalance"
}

export const enum ChatApi {
    Chat = "chat/chat3.5"
}

export const enum AccountApi {
    Recharge = "account/recharge"
}

export const enum MessageApi {
    Recharge = "message/query"
}