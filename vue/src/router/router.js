import Vue from 'vue'
import Router from 'vue-router'
import demo from '../component/demo.vue'

Vue.use(Router)

export default new Router({
    mode: "history",
    linkActiveClass: 'open active',
    routes: [{
        path: '/demo',
        component: demo
        }
        
            
    ]
})