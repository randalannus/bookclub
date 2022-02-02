<template>
  <div class="row justify-center">
    <div class="col col-2">
      <q-card> Filters </q-card>
    </div>
    <div class="col col-grow">
      <book-list :books="books" />
    </div>
  </div>
</template>

<script>
import BookList from '@/components/BookList'

export default {
  name: 'BrowseBooks',
  components: {
    BookList
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
