import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import("../view/Layout.vue")
        },
        {
            path: '/login',
            name: 'login',
            component: () => import("../view/Login.vue")
        },
        {
            path: '/register',
            name: 'register',
            component: () => import("../view/Register.vue")
        }
    ]
})

export default router;