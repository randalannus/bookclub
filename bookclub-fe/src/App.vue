<template>
  <q-layout view="hHh lpr fFf">
    <menu-bar />
    <q-page-container>
      <q-page padding class="content">
        <router-view />
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import MenuBar from './components/MenuBar.vue'
export default {
  components: {
    MenuBar
  }
}
</script>

<style scoped>
.content {
  max-width: 1000px;
  margin: 0 auto;
}
</style>
