<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const posts = ref([])
const isDark = ref(false)

onMounted(async () => {
  const res = await axios.get('http://localhost:8080/api/posts')
  posts.value = res.data
})

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
        <div class="flex items-center gap-4">
          <h1
              class="text-2xl font-bold transition-colors"
              :class="isDark ? 'text-white' : 'text-gray-800'"
          >
            게시글 목록
          </h1>

          <!-- 글쓰기 버튼 -->
          <button
              @click="router.push('/create')"
              class="px-4 py-2 rounded-xl font-semibold text-white transition-all duration-300 hover:scale-105"
              :class="isDark
              ? 'bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-600 hover:to-pink-600'
              : 'bg-gradient-to-r from-blue-500 to-purple-500 hover:from-blue-600 hover:to-purple-600'"
          >
            글쓰기
          </button>
        </div>

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
      <div class="grid gap-6">
        <div
            v-for="post in posts"
            :key="post.id"
            @click="router.push(`/post/${post.id}`)"
            class="group cursor-pointer transform transition-all duration-300 hover:-translate-y-2 hover:scale-[1.02] backdrop-blur-sm rounded-2xl border shadow-lg hover:shadow-2xl"
            :class="isDark
            ? 'bg-gray-800/50 hover:bg-gray-700/60 border-gray-600'
            : 'bg-white/70 hover:bg-white/90 border-gray-200'"
        >
          <div class="p-6">
            <!-- 헤더 영역 -->
            <div class="flex justify-between items-start mb-4">
              <div class="flex-1">
                <div class="flex items-center gap-3 mb-2">
                  <span
                      class="px-3 py-1 rounded-full text-xs font-semibold"
                      :class="isDark
                      ? 'bg-purple-500/20 text-purple-300'
                      : 'bg-blue-500/10 text-blue-600'"
                  >
                    #{{ post.id }}
                  </span>
                  <span
                      class="text-sm"
                      :class="isDark ? 'text-gray-400' : 'text-gray-500'"
                  >
                    {{ post.createdAt }}
                  </span>
                </div>

                <h3
                    class="text-xl font-bold group-hover:text-transparent group-hover:bg-clip-text group-hover:bg-gradient-to-r transition-all duration-300"
                    :class="isDark
                    ? 'text-white group-hover:from-purple-400 group-hover:to-pink-400'
                    : 'text-gray-800 group-hover:from-blue-600 group-hover:to-purple-600'"
                >
                  {{ post.title }}
                </h3>
              </div>
            </div>

            <!-- 하단 정보 -->
            <div class="flex justify-between items-center">
              <div class="flex items-center gap-3">
                <div
                    class="w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold text-white"
                    :class="isDark
                    ? 'bg-gradient-to-r from-purple-500 to-pink-500'
                    : 'bg-gradient-to-r from-blue-500 to-purple-500'"
                >
                  {{ post.author.nickName[0] }}
                </div>
                <span
                    class="font-medium"
                    :class="isDark ? 'text-gray-300' : 'text-gray-700'"
                >
                  {{ post.author.nickName }}
                </span>
              </div>

              <div class="flex items-center gap-4">
                <div
                    class="flex items-center gap-1"
                    :class="isDark ? 'text-gray-400' : 'text-gray-500'"
                >
                  <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M10 12a2 2 0 100-4 2 2 0 000 4z"/>
                    <path fill-rule="evenodd" d="M.458 10C1.732 5.943 5.522 3 10 3s8.268 2.943 9.542 7c-1.274 4.057-5.064 7-9.542 7S1.732 14.057.458 10zM14 10a4 4 0 11-8 0 4 4 0 018 0z" clip-rule="evenodd"/>
                  </svg>
                  <span class="text-sm">{{ post.views }}</span>
                </div>

                <div
                    class="opacity-0 group-hover:opacity-100 transition-opacity duration-300"
                    :class="isDark ? 'text-purple-400' : 'text-blue-600'"
                >
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
                  </svg>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 빈 상태 -->
      <div
          v-if="posts.length === 0"
          class="text-center py-12"
          :class="isDark ? 'text-gray-400' : 'text-gray-500'"
      >
        <div class="text-4xl mb-4 font-light">Empty</div>
        <p class="text-lg">아직 게시글이 없습니다.</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Tailwind CSS 클래스들이 없다면 여기에 CSS 추가 */
</style>