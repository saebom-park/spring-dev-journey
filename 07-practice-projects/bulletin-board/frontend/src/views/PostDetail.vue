<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
const post = ref(null)
const isLoading = ref(true)
const isDark = ref(false)
const newComment = ref('')
const isSubmitting = ref(false)
const isDeleting = ref(false)

onMounted(async () => {
  await fetchPost()
})

const fetchPost = async () => {
  try {
    const postId = route.params.id
    const res = await axios.get(`http://localhost:8080/api/posts/${postId}`)
    post.value = res.data
  } catch (error) {
    console.error('게시글 조회 실패:', error)
    alert('게시글을 찾을 수 없습니다.')
    router.push('/')
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

const createComment = async () => {
  if (!newComment.value.trim()) {
    alert('댓글 내용을 입력해주세요.')
    return
  }

  isSubmitting.value = true

  try {
    await axios.post(`http://localhost:8080/api/posts/${route.params.id}/comments`, {
      comment: newComment.value
    })

    newComment.value = ''
    await fetchPost() // 게시글 다시 조회해서 댓글 목록 업데이트
    alert('댓글이 작성되었습니다!')
  } catch (error) {
    console.error('댓글 작성 실패:', error)
    alert('댓글 작성에 실패했습니다.')
  } finally {
    isSubmitting.value = false
  }
}

const deleteComment = async (commentId) => {
  if (!confirm('정말로 이 댓글을 삭제하시겠습니까?')) {
    return
  }

  try {
    await axios.delete(`http://localhost:8080/api/comments/${commentId}`)
    await fetchPost() // 게시글 다시 조회해서 댓글 목록 업데이트
    alert('댓글이 삭제되었습니다!')
  } catch (error) {
    console.error('댓글 삭제 실패:', error)
    alert('댓글 삭제에 실패했습니다.')
  }
}

const editPost = () => {
  router.push(`/edit/${route.params.id}`)
}

const deletePost = async () => {
  if (!confirm('정말로 이 게시글을 삭제하시겠습니까?')) {
    return
  }

  isDeleting.value = true

  try {
    await axios.delete(`http://localhost:8080/api/posts/${route.params.id}`)
    alert('게시글이 삭제되었습니다.')
    router.push('/')
  } catch (error) {
    console.error('게시글 삭제 실패:', error)
    alert('게시글 삭제에 실패했습니다.')
  } finally {
    isDeleting.value = false
  }
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
          <button
              @click="goBack"
              class="flex items-center gap-2 px-4 py-2 rounded-xl transition-all duration-300 hover:scale-105"
              :class="isDark
              ? 'bg-gray-700/50 text-gray-300 hover:bg-gray-600/50'
              : 'bg-gray-200 text-gray-700 hover:bg-gray-300'"
          >
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
            </svg>
          </button>

          <h1
              class="text-2xl font-bold transition-colors"
              :class="isDark ? 'text-white' : 'text-gray-800'"
          >
            게시글 상세
          </h1>
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
      <!-- 로딩 상태 -->
      <div
          v-if="isLoading"
          class="text-center py-12"
          :class="isDark ? 'text-gray-400' : 'text-gray-500'"
      >
        <div class="text-lg">로딩 중...</div>
      </div>

      <!-- 게시글 내용 -->
      <div
          v-else-if="post"
          class="backdrop-blur-sm rounded-2xl border shadow-lg overflow-hidden"
          :class="isDark
          ? 'bg-gray-800/50 border-gray-600'
          : 'bg-white/70 border-gray-200'"
      >
        <!-- 게시글 헤더 -->
        <div class="p-8 border-b" :class="isDark ? 'border-gray-600' : 'border-gray-200'">
          <div class="flex items-center gap-3 mb-4">
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
          </div>

          <h1
              class="text-3xl font-bold mb-4"
              :class="isDark ? 'text-white' : 'text-gray-800'"
          >
            {{ post.title }}
          </h1>

          <div class="flex items-center gap-3">
            <div
                class="w-10 h-10 rounded-full flex items-center justify-center text-lg font-bold text-white"
                :class="isDark
                ? 'bg-gradient-to-r from-purple-500 to-pink-500'
                : 'bg-gradient-to-r from-blue-500 to-purple-500'"
            >
              {{ post.author.nickName[0] }}
            </div>
            <span
                class="font-semibold text-lg"
                :class="isDark ? 'text-gray-200' : 'text-gray-700'"
            >
              {{ post.author.nickName }}
            </span>
          </div>
        </div>

        <!-- 게시글 본문 -->
        <div class="p-8">
          <div
              class="text-lg leading-relaxed whitespace-pre-wrap"
              :class="isDark ? 'text-gray-300' : 'text-gray-800'"
          >
            {{ post.content }}
          </div>
        </div>

        <!-- 수정/삭제 버튼 -->
        <div class="px-8 pb-6 border-b" :class="isDark ? 'border-gray-600' : 'border-gray-200'">
          <div class="flex gap-3 justify-end">
            <button
                @click="editPost"
                class="px-4 py-2 rounded-xl font-semibold transition-all duration-300 hover:scale-105"
                :class="isDark
                ? 'bg-gray-600/50 text-gray-300 hover:bg-gray-500/50'
                : 'bg-gray-200 text-gray-700 hover:bg-gray-300'"
            >
              수정
            </button>

            <button
                @click="deletePost"
                :disabled="isDeleting"
                class="px-4 py-2 rounded-xl font-semibold text-white transition-all duration-300 hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed"
                :class="isDark
                ? 'bg-gradient-to-r from-red-500 to-pink-500 hover:from-red-600 hover:to-pink-600'
                : 'bg-gradient-to-r from-red-500 to-red-600 hover:from-red-600 hover:to-red-700'"
            >
              {{ isDeleting ? '삭제 중...' : '삭제' }}
            </button>
          </div>
        </div>

        <!-- 댓글 섹션 -->
        <div class="border-t p-8" :class="isDark ? 'border-gray-600' : 'border-gray-200'">
          <h2
              class="text-xl font-bold mb-6"
              :class="isDark ? 'text-white' : 'text-gray-800'"
          >
            댓글 {{ post.comments.length }}개
          </h2>

          <!-- 댓글 목록 -->
          <div v-if="post.comments.length > 0" class="space-y-4">
            <div
                v-for="comment in post.comments"
                :key="comment.id"
                class="p-4 rounded-xl"
                :class="isDark
                ? 'bg-gray-700/30'
                : 'bg-gray-50'"
            >
              <div class="flex items-center justify-between mb-2">
                <div class="flex items-center gap-3">
                  <div
                      class="w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold text-white"
                      :class="isDark
                      ? 'bg-gradient-to-r from-purple-500 to-pink-500'
                      : 'bg-gradient-to-r from-blue-500 to-purple-500'"
                  >
                    {{ comment.author.nickName[0] }}
                  </div>
                  <span
                      class="font-medium"
                      :class="isDark ? 'text-gray-300' : 'text-gray-700'"
                  >
                    {{ comment.author.nickName }}
                  </span>
                  <span
                      class="text-sm"
                      :class="isDark ? 'text-gray-500' : 'text-gray-500'"
                  >
                    {{ comment.createdAt }}
                  </span>
                </div>

                <!-- 댓글 삭제 버튼 -->
                <button
                    @click="deleteComment(comment.id)"
                    class="px-3 py-1 text-sm rounded-lg transition-all duration-300 hover:scale-105"
                    :class="isDark
                    ? 'text-red-400 hover:bg-red-500/20'
                    : 'text-red-500 hover:bg-red-50'"
                >
                  삭제
                </button>
              </div>
              <p
                  class="ml-11"
                  :class="isDark ? 'text-gray-300' : 'text-gray-800'"
              >
                {{ comment.comment }}
              </p>
            </div>
          </div>

          <!-- 댓글 없음 -->
          <div
              v-else
              class="text-center py-8"
              :class="isDark ? 'text-gray-500' : 'text-gray-400'"
          >
            첫 번째 댓글을 작성해보세요!
          </div>

          <!-- 댓글 작성 폼 -->
          <div class="mt-8 pt-6 border-t" :class="isDark ? 'border-gray-600' : 'border-gray-200'">
            <h3
                class="text-lg font-semibold mb-4"
                :class="isDark ? 'text-white' : 'text-gray-800'"
            >
              댓글 작성
            </h3>

            <div class="space-y-4">
              <textarea
                  v-model="newComment"
                  placeholder="댓글을 입력하세요..."
                  rows="3"
                  class="w-full px-4 py-3 rounded-xl border transition-all duration-300 focus:outline-none focus:ring-4 resize-none"
                  :class="isDark
                  ? 'bg-gray-700/50 border-gray-600 text-white placeholder-gray-400 focus:ring-purple-400/50 focus:border-purple-400'
                  : 'bg-white/70 border-gray-300 text-gray-800 placeholder-gray-500 focus:ring-blue-400/50 focus:border-blue-400'"
              ></textarea>

              <div class="flex justify-end">
                <button
                    @click="createComment"
                    :disabled="isSubmitting || !newComment.trim()"
                    class="px-6 py-2 rounded-xl font-semibold text-white transition-all duration-300 hover:scale-105 disabled:opacity-50 disabled:cursor-not-allowed"
                    :class="isDark
                    ? 'bg-gradient-to-r from-purple-500 to-pink-500 hover:from-purple-600 hover:to-pink-600'
                    : 'bg-gradient-to-r from-blue-500 to-purple-500 hover:from-blue-600 hover:to-purple-600'"
                >
                  {{ isSubmitting ? '작성 중...' : '댓글 작성' }}
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>