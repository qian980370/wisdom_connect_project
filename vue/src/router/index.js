import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView'
import Layout from "../layout/Layout";
import RegisterView from "@/views/RegisterView";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/home",
    children: [
      {
        path: 'home',
        name: 'Home',
        component: HomeView,
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/register') {
    next()
    return
  }
  let user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
  console.log(user);
  if (!user) {
    next('/login')
  }else if(user === {}){
    next('/login')
  }  else {
    next()
  }
})

export default router
