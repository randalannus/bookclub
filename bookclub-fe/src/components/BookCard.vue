<template>
  <q-card square flat bordered>
    <q-card-section>
      <div class="text-h6">{{ book.title }}</div>
      <div class="text-subtitle2">by John Doe</div>
    </q-card-section>
  </q-card>
</template>

<script>
export default {
  props: {
    book: Object
  }
}
</script>

<style></style>
