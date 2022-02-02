<template>
  <div class="col">
    <div class="row">
      <div class="col-4 separator">
        <q-img
          src="@/assets/avatar.svg"
          spinner-color="black"
          fit="scale-down"
        />
      </div>
      <div class="col-grow">
        <template v-if="author == null">
          <q-skeleton></q-skeleton>
        </template>
        <template v-else>
          <div class="text-h2">{{ authorName }}</div>
          <div class="text-subtitle2" v-if="birthDate">
            Born {{ birthDate }}
          </div>
          <div class="text-subtitle2" v-if="deathDate">
            Died {{ deathDate }}
          </div>
        </template>
      </div>
    </div>
    <div class="text-h5">Books by {{ authorName }}</div>
    <div class="column items-stretch">
      <div v-for="book in books" :key="book.bookId" class="col">
        <book-card :book="book"></book-card>
      </div>
    </div>
  </div>
</template>

<script>
import BookCard from '@/components/BookCard'
import Utils from '@/utils'

export default {
  name: 'AuthorView',
  components: {
    BookCard
  },
  props: {
    authorId: Number
  },
  data: function () {
    return {
      author: null,
      books: null
    }
  },
  computed: {
    authorName() {
      const authorName = this.author ? this.author.name : null
      return Utils.formatAuthorName(this.authorId, authorName)
    },
    birthDate() {
      if (!this.author.birthDate) return null
      return Utils.formatDate(new Date(this.author.birthDate))
    },
    deathDate() {
      if (!this.author.deathDate) return null
      return Utils.formatDate(new Date(this.author.deathDate))
    }
  },
  created: function () {
    setTimeout(() => {
      this.axios
        .get(this.bcBaseUrl + `/authors/${this.authorId}`)
        .then(response => (this.author = response.data))
    }, 1000)
    this.axios
      .get(this.bcBaseUrl + `/authors/${this.authorId}/books`)
      .then(response => (this.books = response.data))
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
.text-h5 {
  margin-top: 30px;
}
</style>
