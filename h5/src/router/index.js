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
        title: '首页',
        icon: 'dashboard'
      }
    }]
  },
  {
    path: '/employeeEntry',
    component: Layout,
    children: [{
      path: '',
      name: 'PersonImport',
      component: () => import ('@/views/employeeEntry/index'),
      meta: {
        title: '人员录入',
        icon: 'el-icon-user'
      }
    }],
    hidden: false
  },
  {
    path: '/personhold',
    component: Layout,
    children: [{
      path: '',
      name: 'PersonHold',
      component: () => import ('@/views/personhold/index'),
      meta: {
        title: '人员维护',
        icon: 'el-icon-ship'
      }
    }]
  },
  {
    path: '/agentadjust',
    component: Layout,
    children: [{
      path: '',
      name: 'Agentadjust',
      component: () => import ('@/views/agentadjust/index'),
      meta: {
        title: '职级调整',
        icon: 'el-icon-s-data'
      }
    }]
  },
  {
    path: '/leaveCompany',
    component: Layout,
    children: [{
      path: '',
      name: 'leaveCompany',
      component: () => import ('@/views/leaveCompany/index'),
      meta: {
        title: '离司申请',
        icon: 'el-icon-user'
      }
    }],
    hidden: false
  },
  {
    path: '/leave',
    component: Layout,
    children: [{
      path: '',
      name: 'leave',
      component: () => import ('@/views/leave/index2'),
      meta: {
        title: '离职确认', // hhhhhhhh
        icon: 'el-icon-document-checked'
      }
    }]
  },
  {
    path: '/personelChange',
    component: Layout,
    children: [{
      path: '',
      name: 'personelChange',
      component: () => import ('@/views/personnelChange/index'),
      meta: {
        title: '人员异动',
        icon: 'el-icon-suitcase-1'
      }
    }]
  },
  {
    path: '/teamMaintenance',
    component: Layout,
    children: [{
      path: '',
      name: 'TeamHold',
      component: () => import ('@/views/teamMaintenance/index'),
      meta: {
        title: '团队维护',
        icon: 'el-icon-s-cooperation'
      }
    }],
    hidden: false
  },
  {
    path: '/qualificationManagement',
    component: Layout,
    children: [{
      path: '',
      name: 'Qualification',
      component: () => import ('@/views/qualificationManagement/index'),
      meta: {
        title: '资格证管理',
        icon: 'el-icon-s-management'
      }
    }],
    hidden: false
  },
  // {
  //   path: '/leaderChange',
  //   component: Layout,
  //   children: [{
  //     path: '',
  //     name: 'LeaderChange',
  //     component: () => import ('@/views/leaderChange/index'),
  //     meta: {
  //       title: '团队主管任命免职',
  //       icon: 'el-icon-s-check'
  //     }
  //   }],
  //   hidden: false
  // },
  // 404 page must be placed at the end !!!
  //
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
