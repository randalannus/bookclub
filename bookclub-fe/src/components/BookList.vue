<template>
  <div class="column items-stretch">
    <template v-for="book in books" :key="book.bookId">
      <div class="col">
        <book-card :book="book"></book-card>
      </div>
    </template>
  </div>
</template>

<script>
import BookCard from './BookCard.vue'
export default {
  name: 'BooksList',
  props: {
    books: Array
  },
  components: {
    BookCard
  }
}
</script>

<style></style>
