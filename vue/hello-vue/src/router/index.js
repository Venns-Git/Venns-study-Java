import Vue from 'vue'
import Router from 'vue-router'
import Main from '../views/Main'
import Login from '../views/Login'

import UserList from '../views/user/List'
import UserProfile from '../views/user/Profile'
import NotFound from '../views/NotFound'

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes:[
        {
            path: '/main',
            component: Main,
            props: true,
            children: [
                {
                    path: '/user/profile/:id',
                    name: 'UserProfile',
                    props: true,
                    component: UserProfile
                },
                {
                    path: '/user/list',
                    name: UserList,
                    component: UserList
                }
            ]
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/goHome',
            redirect: '/main'
        },
        {
            path: '*',
            component: NotFound
        }
    ]
});