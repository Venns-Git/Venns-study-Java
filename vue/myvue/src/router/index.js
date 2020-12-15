import Vue from 'vue'
import VueRouter from 'vue-router'
import content from '../components/content'
import main from '../components/main'

//安装路由
Vue.use(VueRouter);

//配置导入路由
export default new VueRouter({
    routes: [
        {
            //路由路径
            path: '/content',
            name: 'content',
            //跳转的组件
            component: content
        },
        {
            path: '/main',
            name: 'main',
            component: main
        }
    ]
});