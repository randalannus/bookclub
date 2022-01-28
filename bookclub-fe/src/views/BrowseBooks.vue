<template>
  <div class="row justify-center">
    <div class="col col-2">
      <q-card> Filters </q-card>
    </div>
    <div class="col-6">
      <book-list :books="books" />
    </div>
  </div>
</template>

<script>
import BookList from '@/components/BookList'

export default {
  name: 'Home',
  components: {
    BookList
  },
  data: function () {
    return {
      books: []
    }
  },
  created: function () {
    fetch('http://localhost:8080/books')
      .then(response => response.json())
      .then(data => (this.books = data))
  }
}
</script>
