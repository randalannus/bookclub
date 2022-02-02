import { createRouter, createWebHashHistory } from 'vue-router'
import BrowseBooks from '../views/BrowseBooks.vue'
import BookView from '../views/BookView.vue'

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
  },
  {
    path: '/books/:bookId',
    name: 'Book',
    component: BookView,
    props: true
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
