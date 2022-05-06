import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/BackendManagerViews/HomeView.vue'
import LoginView from '../views/LoginView'
import Layout from "../layout/Layout";
import RegisterView from "@/views/RegisterView";
import WebLogin from "@/views/WebLogin";
import Webregister from "@/views/WebRegister";
import WebRegister from "@/views/WebRegister";
import UserControllerView from "@/views/BackendManagerViews/UserControllerView";
import FileControllerView from "@/views/BackendManagerViews/FileControllerView";
import ProfileControllerView from "@/views/BackendManagerViews/ProfileControllerView";
import HobbyControllerView from "@/views/BackendManagerViews/HobbyControllerView";


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
      {
        path: 'user-controller',
        name: 'UserControllerView',
        component: UserControllerView,
      },
      {
        path: 'file-controller',
        name: 'FileControllerView',
        component: FileControllerView,
      },
      {
        path: 'hobby-controller',
        name: 'HobbyControllerView',
        component: HobbyControllerView,
      },
      {
        path: 'profile-controller',
        name: 'ProfileControllerView',
        component: ProfileControllerView,
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
  {
    path: '/weblogin',
    name: 'Weblogin',
    component: WebLogin
  },
  {
    path: '/webregister',
    name: 'Webregister',
    component: WebRegister
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
  let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null;
  // let user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
  console.log(user);
  if (!user) {
    next('/login')
  } else {
    console.log("pass")
    next()
  }
})

export default router
