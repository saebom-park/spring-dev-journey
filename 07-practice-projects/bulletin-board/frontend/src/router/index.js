import { createRouter, createWebHistory } from 'vue-router'
import PostList from '../views/PostList.vue'
import PostCreate from '../views/PostCreate.vue'

const routes = [
    {
        path: '/',
        name: 'PostList',
        component: PostList
    },
    {
        path: '/create',
        name: 'PostCreate',
        component: PostCreate
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router