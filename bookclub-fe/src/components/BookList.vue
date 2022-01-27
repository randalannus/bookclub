<template>
  <div v-for="book in books" :key="book.bookId">
    {{ book.bookName }}
  </div>
</template>

<script>
export default {
  name: 'BooksList',
  props: {
    books: Array
  }
}
</script>

<style></style>
