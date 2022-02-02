import { createRouter, createWebHashHistory } from 'vue-router'
import BrowseBooks from '../views/BrowseBooks.vue'
import BookView from '../views/BookView.vue'
import AuthorView from '../views/AuthorView.vue'

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
  },
  {
    path: '/authors/:authorId',
    name: 'Author',
    component: AuthorView,
    props: true
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
