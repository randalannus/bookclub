<template>
  <q-toolbar inset>
    <q-toolbar-title>
      <router-link to="/">BookClub</router-link>
    </q-toolbar-title>
    <q-separator dark vertical />
    <q-btn stretch flat label="All books" to="/books" />
    <q-separator dark vertical />
    <q-btn stretch flat label="Link" />
  </q-toolbar>
</template>

<script>
export default {}
</script>

<style>
a {
  color: inherit;
  text-decoration: inherit;
}
</style>
