import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { Quasar } from 'quasar'
import quasarUserOptions from './quasar-user-options'
import axios from 'axios'
import VueAxios from 'vue-axios'

const app = createApp(App)
app.use(Quasar, quasarUserOptions)
app.use(router)
app.use(VueAxios, axios)
app.config.globalProperties.bcBaseUrl = process.env.VUE_APP_BC_BASE_URL
app.mount('#app')
