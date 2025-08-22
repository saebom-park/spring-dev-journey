<template>
  <div id="app" class="min-h-screen bg-gray-50">
    <!-- 네비게이션 바 -->
    <nav class="bg-white shadow-sm border-b border-gray-200">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16">
          <div class="flex">
            <!-- 로고 -->
            <div class="flex-shrink-0 flex items-center">
              <router-link to="/" class="text-xl font-bold text-blue-600">
                📝 Todo App
              </router-link>
            </div>

            <!-- 네비게이션 링크 -->
            <div class="hidden md:ml-6 md:flex md:space-x-8">
              <router-link to="/todos"
                           class="border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300 whitespace-nowrap py-2 px-1 border-b-2 font-medium text-sm transition-colors"
                           :class="{ 'border-blue-500 text-blue-600': $route.name === 'todos' || $route.name === 'home' }">
                할일 관리
              </router-link>

              <router-link to="/categories"
                           class="border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300 whitespace-nowrap py-2 px-1 border-b-2 font-medium text-sm transition-colors"
                           :class="{ 'border-blue-500 text-blue-600': $route.name === 'categories' }">
                카테고리 관리
              </router-link>

              <router-link to="/stats"
                           class="border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300 whitespace-nowrap py-2 px-1 border-b-2 font-medium text-sm transition-colors"
                           :class="{ 'border-blue-500 text-blue-600': $route.name === 'statistics' }">
                통계
              </router-link>
            </div>
          </div>

          <!-- 사용자 정보 -->
          <div class="flex items-center">
            <div class="flex items-center space-x-4">
              <span class="text-sm text-gray-700">봄이님</span>
              <div class="w-8 h-8 bg-blue-500 rounded-full flex items-center justify-center">
                <span class="text-white text-sm font-medium">봄</span>
              </div>
            </div>
          </div>

          <!-- 모바일 메뉴 버튼 -->
          <div class="md:hidden flex items-center">
            <button @click="mobileMenuOpen = !mobileMenuOpen"
                    class="text-gray-500 hover:text-gray-700 focus:outline-none focus:text-gray-700">
              <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M4 6h16M4 12h16M4 18h16" />
              </svg>
            </button>
          </div>
        </div>
      </div>

      <!-- 모바일 메뉴 -->
      <div v-if="mobileMenuOpen" class="md:hidden">
        <div class="px-2 pt-2 pb-3 space-y-1 sm:px-3 bg-white border-t border-gray-200">
          <router-link to="/todos" @click="mobileMenuOpen = false"
                       class="block px-3 py-2 rounded-md text-base font-medium transition-colors"
                       :class="$route.name === 'todos' || $route.name === 'home'
                        ? 'text-blue-600 bg-blue-50'
                        : 'text-gray-700 hover:text-gray-900 hover:bg-gray-50'">
            할일 관리
          </router-link>

          <router-link to="/categories" @click="mobileMenuOpen = false"
                       class="block px-3 py-2 rounded-md text-base font-medium transition-colors"
                       :class="$route.name === 'categories'
                        ? 'text-blue-600 bg-blue-50'
                        : 'text-gray-700 hover:text-gray-900 hover:bg-gray-50'">
            카테고리 관리
          </router-link>

          <router-link to="/stats" @click="mobileMenuOpen = false"
                       class="block px-3 py-2 rounded-md text-base font-medium transition-colors"
                       :class="$route.name === 'statistics'
                        ? 'text-blue-600 bg-blue-50'
                        : 'text-gray-700 hover:text-gray-900 hover:bg-gray-50'">
            통계
          </router-link>
        </div>
      </div>
    </nav>

    <!-- 메인 컨텐츠 -->
    <main>
      <router-view />
    </main>

    <!-- 토스트 알림 -->
    <div v-if="notification.show"
         class="fixed bottom-4 right-4 bg-white rounded-lg shadow-lg border-l-4 p-4 max-w-sm z-50 transition-all"
         :class="notification.type === 'success' ? 'border-green-500' : 'border-red-500'">
      <div class="flex">
        <div class="flex-shrink-0">
          <span v-if="notification.type === 'success'" class="text-green-500">✓</span>
          <span v-else class="text-red-500">✕</span>
        </div>
        <div class="ml-3">
          <p class="text-sm font-medium text-gray-900">{{ notification.message }}</p>
        </div>
        <div class="ml-auto pl-3">
          <button @click="hideNotification" class="text-gray-400 hover:text-gray-600">
            <span>&times;</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, provide } from 'vue'

export default {
  name: 'App',
  setup() {
    const mobileMenuOpen = ref(false)

    // 전역 알림 시스템
    const notification = ref({
      show: false,
      message: '',
      type: 'success'
    })

    const showNotification = (message, type = 'success') => {
      notification.value = {
        show: true,
        message,
        type
      }

      // 3초 후 자동 숨김
      setTimeout(() => {
        hideNotification()
      }, 3000)
    }

    const hideNotification = () => {
      notification.value.show = false
    }

    // 하위 컴포넌트에서 사용할 수 있도록 제공
    provide('notification', {
      showNotification,
      hideNotification
    })

    return {
      mobileMenuOpen,
      notification,
      showNotification,
      hideNotification
    }
  }
}
</script>

<style>
/* 글로벌 스타일 */
#app {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

/* 라우터 링크 활성화 스타일 */
.router-link-active {
  color: #2563eb !important;
  border-color: #2563eb !important;
}

/* 스크롤바 스타일링 */
::-webkit-scrollbar {
  width: 6px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}

/* 애니메이션 */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>