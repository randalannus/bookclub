<template>
  <div class="row">
    <div class="col-4 separator">
      <q-img :src="url" spinner-color="black" fit="scale-down" />
    </div>
    <div class="col-grow">
      <template v-if="book == null">
        <q-skeleton></q-skeleton>
      </template>
      <template v-else>
        <div class="text-h2">{{ book.title }}</div>
        <div class="text-subtitle2">by {{ authorName }}</div>
        <div class="text-body2">{{ book.description }}</div>
      </template>
    </div>
  </div>
</template>

<script>
import Utils from '@/utils'
export default {
  name: 'BookView',
  components: {},
  props: {
    bookId: Number
  },
  data: function () {
    return {
      book: null,
      url: 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1600357110i/55361205.jpg'
    }
  },
  computed: {
    authorName() {
      return Utils.formatAuthorName(this.book.authorId, this.book.authorName)
    }
  },
  created: function () {
    setTimeout(() => {
      this.axios
        .get(this.bcBaseUrl + `/books/${this.bookId}`)
        .then(response => (this.book = response.data))
    }, 1000)
  }
}
</script>
<style scoped>
.separator {
  margin-right: 48px;
}
.text-subtitle2 {
  color: grey;
  padding-top: 10px;
  padding-bottom: 20px;
}
</style>
