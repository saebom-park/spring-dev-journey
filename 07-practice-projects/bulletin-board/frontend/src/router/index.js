import { createRouter, createWebHistory } from 'vue-router'
import PostList from '../views/PostList.vue'
import PostCreate from '../views/PostCreate.vue'
import PostDetail from '../views/PostDetail.vue'
import PostEdit from '../views/PostEdit.vue'

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
    },
    {
        path: '/post/:id',
        name: 'PostDetail',
        component: PostDetail
    },
    {
        path: '/edit/:id',
        name: 'PostEdit',
        component: PostEdit
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router