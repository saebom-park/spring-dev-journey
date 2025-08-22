import { createRouter, createWebHistory } from 'vue-router'
import TodoList from '../views/TodoList.vue'
import CategoryManage from '../views/CategoryManage.vue'
import Statistics from '../views/Statistics.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: TodoList
    },
    {
      path: '/todos',
      name: 'todos',
      component: TodoList
    },
    {
      path: '/categories',
      name: 'categories',
      component: CategoryManage
    },
    {
      path: '/stats',
      name: 'statistics',
      component: Statistics
    }
  ]
})

export default router