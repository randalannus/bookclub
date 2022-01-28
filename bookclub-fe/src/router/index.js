import { createRouter, createWebHashHistory } from 'vue-router'
import BrowseBooks from '../views/BrowseBooks.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/books'
  },
  {
    path: '/books',
    name: 'Books',
    component: BrowseBooks
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
