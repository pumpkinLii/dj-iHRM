import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

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
      component: () => import ('@/views/dajia/index'),
      meta: {
        title: '大家首页',
        icon: 'dashboard'
      }
    }]
  },
  // {
  //   path: '/dajia',
  //   component: Layout,
  //   children: [{
  //     path: '',
  //     component: () => import ('@/views/dajia/index'),
  //     meta: {
  //       title: '大家首页',
  //       icon: 'el-icon-s-home'
  //     }
  //   }]
  // },
  {
    path: '/person-management',
    component: Layout,
    name: 'PersonImport',
    meta: {
      title: '人员管理',
      icon: 'el-icon-user'
    },
    children: [
      {
        path: 'entry',
        name: 'PersonImport',
        component: () => import ('@/views/employeeEntry/index'),
        meta: {
          title: '人员录入'
        }
      },
      {
        path: 'maintenance',
        name: 'PersonHold',
        component: () => import ('@/views/personhold/index'),
        meta: {
          title: '人员维护'
        }
      },
      {
        path: 'change',
        name: 'personelChange',
        component: () => import ('@/views/personnelChange/index'),
        meta: {
          title: '人员异动'
        }
      },
      {
        path: 'adjust',
        name: 'Agentadjust',
        component: () => import ('@/views/agentadjust/index'),
        meta: {
          title: '职级调整'
        }
      }
    ]
  },
  {
    path: '/team-maintenance',
    component: Layout,
    children: [{
      path: '',
      name: 'TeamHold',
      component: () => import ('@/views/teamMaintenance/index'),
      meta: {
        title: '团队维护',
        icon: 'el-icon-s-cooperation'
      }
    }]
  },
  {
    path: '/qualification-management',
    component: Layout,
    children: [{
      path: '',
      name: 'Qualification',
      component: () => import ('@/views/qualificationManagement/index'),
      meta: {
        title: '资格证管理',
        icon: 'el-icon-s-management'
      }
    }]
  },
  {
    path: '/leave-company',
    component: Layout,
    meta: {
      title: '离司管理',
      icon: 'el-icon-s-check'
    },
    children: [
      {
        path: 'apply',
        name: 'leaveCompany',
        component: () => import ('@/views/leaveCompany/index'),
        meta: {
          title: '离司申请'
        }
      },
      {
        path: 'confirm',
        name: 'leave',
        component: () => import ('@/views/leave/index2'),
        meta: {
          title: '离职确认'
        }
      }
    ]
  },
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
