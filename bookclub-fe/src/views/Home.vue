<template>
  <div class="home">
    <book-list :books="books" />
  </div>
</template>

<script>
// @ is an alias to /src
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
