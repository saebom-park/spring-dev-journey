<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const posts = ref([])

onMounted(async () => {
  const res = await axios.get('http://localhost:8080/api/posts')
  posts.value = res.data
})
</script>

<template>
  <div>
    <h1>📋 게시글 목록</h1>
    <ul>
      <li v-for="post in posts" :key="post.id">
        {{ post.id }}
        {{ post.title }}
        {{ post.author.nickName }}
        {{ post.createdAt }}
        {{ post.views }}
      </li>
    </ul>
  </div>
</template>