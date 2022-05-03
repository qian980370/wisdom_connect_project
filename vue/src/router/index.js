import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/FacilityLogin'
import Layout from "../layout/Layout";
import RegisterView from "@/views/FacilitySignup";
import First from "@/views/First";
import FacilityLogin from "@/views/FacilityLogin";
import FacilitySignup from "@/views/FacilitySignup";
import ResetPassword from "@/views/ResetPassword";
import BlockedList from "@/views/BlockedList";
import InterestList from "@/views/InterestList";
import InterestListRemove from "@/views/InterestListRemove";
import NewProfile from "@/views/NewProfile";
import OtherInterest from "@/views/OtherInterest";
import Personal from "@/views/Personal";
import PersonalChange from "@/views/PersonalChange";
import ProfileLogin from "@/views/ProfileLogin";
import TapOnFriend from "@/views/TapOnFriend";
import VideoCall from "@/views/VideoCall";
import Second from "@/views/Second";
import Flayout from "@/layout/Flayout";


const routes = [
    {
        path: '/',
        name: 'First',
        component: First,
      },
  {
    path: '/facilitylogin',
    name: 'FacilityLogin',
    component: FacilityLogin
  },
  {
    path: '/facilitysignup',
    name: 'FacilitySignup',
    component: FacilitySignup
  },
  {
    path: '/resetpassword',
    name: 'ResetPassword',
    component: ResetPassword
  },
  {
    path: '/blockedlist',
    name: 'BlockedList',
    component: BlockedList
  },
  {
    path: '/homeview',
    name: 'HomeView',
    component: BlockedList
  },
  {
    path: '/interestlist',
    name: 'InterestList',
    component: InterestList
  },
  {
    path: '/interestlistremove',
    name: 'InterestListRemove',
    component: InterestListRemove
  },
  {
    path: '/newprofile',
    name: 'NewProfile',
    component: NewProfile
  },
  {
    path: '/otherinterest',
    name: 'OtherInterest',
    component: OtherInterest
  },
  {
    path: '/personal',
    name: 'Personal',
    component: Personal
  },
  {
    path: '/personalchange',
    name: 'PersonalChange',
    component: PersonalChange
  },
  {
    path: '/profilelogin',
    name: 'ProfileLogin',
    component: ProfileLogin
  },
  {
    path: '/taponfriend',
    name: 'TapOnFriend',
    component: TapOnFriend
  },
  {
    path: '/videocall',
    name: 'VideoCall',
    component: VideoCall
  },
  {
    path: '/second',
    name: 'Second',
    component: Second
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
