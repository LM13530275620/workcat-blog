import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Blogs from '../views/Blogs.vue'
import BlogEdit from '../views/BlogEdit.vue'
import BlogDetail from '../views/BlogDetail.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: {name: "Blogs"}
  },
  {
    path: '/blogs',
    name: 'Blogs',
    component: Blogs
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/blog/add',
    name: 'BlogAdd',
    component: BlogEdit,
    //路由拦截
    meta: {
      requireAuth: true//等于true处于登录状态
    }
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: BlogDetail
  },
  {
    path: '/blog/:blogId/edit', // 注意放在 path: '/blog/:blogId'之前
    name: 'BlogEdit',
    component: BlogEdit,
    meta: {
      requireAuth: true//等于true处于登录状态
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
