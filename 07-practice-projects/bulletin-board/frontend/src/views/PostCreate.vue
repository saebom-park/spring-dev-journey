<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const title = ref('')
const content = ref('')
const isLoading = ref(false)
const isDark = ref(false)

const createPost = async () => {
  if (!title.value.trim() || !content.value.trim()) {
    alert('제목과 내용을 입력해주세요.')
    return
  }

  isLoading.value = true

  try {
    await axios.post('http://localhost:8080/api/posts', {
      title: title.value,
      content: content.value
    })

    alert('게시글이 작성되었습니다!')
    router.push('/') // 목록으로 이동
  } catch (error) {
    console.error('게시글 작성 실패:', error)
    alert('게시글 작성에 실패했습니다.')
  } finally {
    isLoading.value = false
  }
}

const goBack = () => {
  router.push('/')
}

const toggleTheme = () => {
  isDark.value = !isDark.value
}
</script>

<template>
  <div
      class="min-h-screen transition-all duration-500"
      :class="isDark
      ? 'bg-gradient-to-br from-gray-800 via-gray-900 to-gray-800'
      : 'bg-gradient-to-br from-blue-50 via-purple-50 to-pink-50'"
  >
    <!-- 헤더 -->
    <div
        class="sticky top-0 backdrop-blur-lg border-b transition-all duration-300"
        :class="isDark
        ? 'bg-gray-900/80 border-gray-700'
        : 'bg-white/80 border-gray-200'"
    >
      <div class="max-w-4xl mx-auto px-6 py-4 flex justify-between items-center">
        <h1
            class="text-2xl font-bold transition-colors"
            :class="isDark ? 'text-white' : 'text-gray-800'"
        >
          게시글 작성
        </h1>

        <!-- 다크/라이트 토글 -->
        <button
            @click="toggleTheme"
            class="relative w-16 h-8 rounded-full transition-all duration-300 focus:outline-none focus:ring-4"
            :class="isDark
            ? 'bg-purple-600 focus:ring-purple-400/50'
            : 'bg-gray-300 focus:ring-blue-400/50'"
        >
          <div
              class="absolute top-1 w-6 h-6 rounded-full transition-all duration-300 flex items-center justify-center text-xs"
              :class="isDark
              ? 'left-9 bg-yellow-300 text-gray-800'
              : 'left-1 bg-white text-gray-600'"
          >
            {{ isDark ? '◐' : '○' }}
          </div>
        </button>
      </div>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="max-w-4xl mx-auto px-6 py-8">
      <div
          class="backdrop-blur-sm rounded-2xl border shadow-lg p-8"
          :class="isDark
          ? 'bg-gray-800/50 border-gray-600'
          : 'bg-white/70 border-gray-200'"
      >
        <!-- 제목 입력 -->
        <div class="mb-6">
          <label
              class="block text-sm font-semibold mb-2"
              :class="isDark ? 'text-gray-300' : 'text-gray-700'"
          >
            제목
          </label>
          <input
              v-model="title"
              type="text"
              placeholder="게시글 제목을 입력하세요"
              class="w-full px-4 py-3 rounded-xl border transition-all duration-300 focus:outline-none focus:ring-4"
              :class="isDark
              ? 'bg-gray-700/50 border-gray-600 text-white placeholder-gray-400 focus:ring-purple-400/50 focus:border-purple-400'
              : 'bg-white/70 border-gray-300 text-gray-800 placeholder-gray-500 focus:ring-blue-400/50 focus:border-blue-400'"
          />
        </div>

        <!-- 내용 입력 -->
        <div class="mb-8">
          <label
              class="block text-sm font-semibold mb-2"
              :class="isDark ? 'text-gray-300' : 'text-gray-700'"
          >
            내용
          </label>
          <textarea
              v-model="content"
              rows="10"
              placeholder="게시글 내용을 입력하세요"
              class="w-full px-4 py-3 rounded-xl border transition-all duration-300 focus:outline-none focus:ring-4 resize-none"
              :class="isDark
              ? 'bg-gray-700/50 border-gray-600 text-white placeholder-gray-400 focus:ring-purple-400/50 focus:border-purple-400'
              : 'bg-white/70 border-gray-300 text-gray-800 placeholder-gray-500 focus:ring-blue-400/50 focus:border-blue-400'"
          ></textarea>
        </div>

        <!-- 버튼 그룹 -->
        <div class="flex gap-4 justify-end">
          <button
              @click="goBack"
              class="px-6 py-3 rounded-xl font-semibold transition-all duration-300 hover:scale-105"
              :class="isDark
              ? 'bg-gray-600/50 text-gray-300 hover:bg-gray-500/50'
              : 'bg-gray-200 text-gray-700 hover:bg-gray-300'"
          >
            취소
          </button>

          <button
              @click="createPost"
              :disabled="isLoading"
              class="px-6 py-3 rounded-xl font-semibold text-white transition-all duration-300 hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed"
              :class="isDark
              ? 'bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-600 hover:to-pink-600'
              : 'bg-gradient-to-r from-blue-500 to-purple-500 hover:from-blue-600 hover:to-purple-600'"
          >
            {{ isLoading ? '작성 중...' : '작성하기' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>