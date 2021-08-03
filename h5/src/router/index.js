import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: () => import ('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import ('@/views/404'),
    hidden: true
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [{
      path: '',
      component: () => import ('@/views/dashboard/index'),
      meta: {
        title: '仪表板',
        icon: 'dashboard'
      }
    }]
  },
  {
    path: '/test',
    component: Layout,
    meta: {
      title: '测试内容',
      icon: 'el-icon-view'
    },
    children: [
      {
        path: '/test/text',
        name: 'text',
        component: () => import ('@/views/text/text'),
        meta: {
          title: 'text',
          icon: 'el-icon-document'
        }
      },
      {
        path: '/test/sample',
        name: 'Sample',
        component: () => import ('@/views/sample/index'),
        meta: {
          title: 'sample',
          icon: 'el-icon-eleme'
        }
      }
    ],
    hidden: false
  },
  {
    path: '/personImport',
    component: Layout,
    children: [{
      path: '',
      component: () => import ('@/views/personImport/index'),
      meta: {
        title: '人员录入',
        icon: 'el-icon-user'
      }
    }]
  },{
    path: '/teamhold',
    component: Layout,
    children: [{
      path: '',
      name: 'TeamHold',
      component: () =>
        import ('@/views/teamhold/index'),
      meta: { title: '团队维护', icon: 'text' }
    }],
    hidden: false
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export default router
