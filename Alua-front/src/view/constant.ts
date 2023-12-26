export interface LoginForm {
    email: string,
    password: string 
}

export interface RegisterForm {
    username: string,
    email: string,
    password: string,
    checkpassword: string,
    code: string
}

export const AppConfig = {
    AppName: "ALua-GPT"
}

export let rules = {
    username: [
        { required: true, message: '请输入昵称', trigger: 'blur' }
    ],
    email: [
        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
        { type: 'email',  message: '请输入正确的邮箱地址',trigger: ['blur', 'change']},
    ],
    password: [{}],
    checkpassword: [{}]
}