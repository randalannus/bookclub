<template>
  <div class="row justify-center">
    <div class="col col-2">
      <q-card> Filters </q-card>
    </div>
    <div class="col-grow">
      <book-card
        v-for="book in books"
        :key="book.bookId"
        :book="book"
      ></book-card>
    </div>
  </div>
</template>

<script>
import BookCard from '@/components/BookCard'

export default {
  name: 'BrowseBooks',
  components: {
    BookCard
  },
  data: function () {
    return {
      books: []
    }
  },
  created: function () {
    this.axios
      .get(this.bcBaseUrl + '/books')
      .then(response => (this.books = response.data))
  }
}
</script>
<style></style>
