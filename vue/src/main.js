import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker"; 
import router from "./router/router"
import * as firebase from "firebase/app";
import "firebase/messaging";

Vue.use(firebase);

Vue.config.productionTip = false;
 /* eslint-disable no-new */
new Vue({
    router,
  render: h => h(App)
}).$mount("#app");
