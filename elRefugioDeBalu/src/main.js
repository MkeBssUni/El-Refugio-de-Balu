import Vue from 'vue'
import App from './App.vue'
import router from './router/index.js'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

//Axios
import axios from 'axios'
import VueAxios from 'vue-axios'

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import './styles/styles.scss'
import './styles/styles.css'
// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.use(VueSweetalert2);
//Axios
Vue.use(VueAxios, axios)

import '@asika32764/vue-animate/dist/vue-animate.css';

new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')
