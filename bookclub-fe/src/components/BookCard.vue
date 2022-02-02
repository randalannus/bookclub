<template>
  <q-card
    square
    flat
    bordered
    class="cursor-pointer card-height"
    @click="onClick()"
  >
    <div class="row items-center card-height" style="">
      <div class="col-2">
        <div id="img-padding">
          <q-img
            :src="url"
            spinner-color="black"
            fit="scale-down"
            class="my-custom-image"
          />
        </div>
      </div>
      <q-separator vertical />
      <div class="col-grow">
        <q-card-section class="card-height">
          <div class="text-h6">{{ book.title }}</div>
          <div class="text-subtitle2">
            by
            <router-link
              v-if="book.authorId"
              :to="'/authors/' + book.authorId"
              @click.stop
              class="text-subtitle2"
              >{{ authorName }}</router-link
            >
            <template v-else>{{ authorName }}</template>
          </div>
          <div class="text-body2">{{ book.description }}</div>
        </q-card-section>
      </div>
    </div>
  </q-card>
</template>

<script>
import Utils from '@/utils'
export default {
  data: function () {
    return {
      url: 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1600357110i/55361205.jpg'
    }
  },
  props: {
    book: Object
  },
  computed: {
    authorName() {
      return Utils.formatAuthorName(this.book.authorId, this.book.authorName)
    }
  },
  methods: {
    onClick() {
      this.$router.push('/books/' + this.book.bookId)
    }
  }
}
</script>
<style scoped>
.text-subtitle2 {
  color: grey;
  padding-bottom: 10px;
}
.card-height {
  height: 15em;
}
.my-custom-image {
  box-sizing: border-box;
  max-height: 13em;
}
#img-padding {
  padding: 1em;
  max-height: 15em;
}
a:hover {
  color: #2ec4b6;
}
</style>
