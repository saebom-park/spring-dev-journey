<template>
  <div class="min-h-screen bg-gray-50 p-6">
    <div class="max-w-6xl mx-auto">
      <!-- 헤더 -->
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-900 mb-2">통계</h1>
        <p class="text-gray-600">할일 완료율과 카테고리별 통계를 확인하세요</p>
      </div>

      <!-- 로딩 상태 -->
      <div v-if="loading" class="text-center py-12">
        <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto"></div>
        <p class="mt-4 text-gray-600">통계를 불러오는 중...</p>
      </div>

      <!-- 통계 내용 -->
      <div v-else>
        <!-- 전체 통계 카드 -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <!-- 전체 할일 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <div class="flex items-center">
              <div class="p-3 rounded-full bg-blue-100">
                <span class="text-2xl">📝</span>
              </div>
              <div class="ml-4">
                <h3 class="text-sm font-medium text-gray-500">전체 할일</h3>
                <p class="text-2xl font-bold text-gray-900">{{ stats.totalTodos }}</p>
              </div>
            </div>
          </div>

          <!-- 완료된 할일 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <div class="flex items-center">
              <div class="p-3 rounded-full bg-green-100">
                <span class="text-2xl">✅</span>
              </div>
              <div class="ml-4">
                <h3 class="text-sm font-medium text-gray-500">완료됨</h3>
                <p class="text-2xl font-bold text-green-600">{{ stats.completedTodos }}</p>
              </div>
            </div>
          </div>

          <!-- 진행중인 할일 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <div class="flex items-center">
              <div class="p-3 rounded-full bg-yellow-100">
                <span class="text-2xl">⏳</span>
              </div>
              <div class="ml-4">
                <h3 class="text-sm font-medium text-gray-500">진행중</h3>
                <p class="text-2xl font-bold text-yellow-600">{{ stats.inProgressTodos }}</p>
              </div>
            </div>
          </div>

          <!-- 완료율 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <div class="flex items-center">
              <div class="p-3 rounded-full bg-purple-100">
                <span class="text-2xl">📊</span>
              </div>
              <div class="ml-4">
                <h3 class="text-sm font-medium text-gray-500">완료율</h3>
                <p class="text-2xl font-bold text-purple-600">{{ stats.completionRate }}%</p>
              </div>
            </div>
          </div>
        </div>

        <!-- 완료율 프로그래스 바 -->
        <div class="bg-white rounded-lg shadow-sm p-6 mb-8">
          <h2 class="text-lg font-semibold text-gray-900 mb-4">전체 진행률</h2>
          <div class="w-full bg-gray-200 rounded-full h-4">
            <div class="bg-blue-600 h-4 rounded-full transition-all duration-500 ease-out"
                 :style="{ width: stats.completionRate + '%' }"></div>
          </div>
          <div class="flex justify-between text-sm text-gray-600 mt-2">
            <span>0%</span>
            <span class="font-medium">{{ stats.completionRate }}% 완료</span>
            <span>100%</span>
          </div>
        </div>

        <!-- 카테고리별 통계 -->
        <div class="bg-white rounded-lg shadow-sm">
          <div class="px-6 py-4 border-b border-gray-200">
            <h2 class="text-lg font-semibold text-gray-900">카테고리별 통계</h2>
          </div>

          <div v-if="stats.categoryStats && stats.categoryStats.length > 0" class="p-6">
            <div class="space-y-6">
              <div v-for="category in stats.categoryStats" :key="category.categoryName"
                   class="border rounded-lg p-4">

                <!-- 카테고리 헤더 -->
                <div class="flex items-center justify-between mb-4">
                  <div class="flex items-center gap-3">
                    <div class="w-4 h-4 rounded-full bg-blue-500"></div>
                    <h3 class="font-medium text-gray-900">{{ category.categoryName }}</h3>
                  </div>
                  <div class="text-sm text-gray-600">
                    {{ category.completedCount }} / {{ category.totalCount }}
                  </div>
                </div>

                <!-- 진행률 바 -->
                <div class="w-full bg-gray-200 rounded-full h-3">
                  <div class="bg-blue-500 h-3 rounded-full transition-all duration-500"
                       :style="{ width: getCategoryProgress(category) + '%' }"></div>
                </div>

                <!-- 통계 정보 -->
                <div class="flex justify-between items-center mt-3 text-sm">
                  <span class="text-gray-600">
                    완료율: {{ getCategoryProgress(category) }}%
                  </span>
                  <div class="flex gap-4 text-xs">
                    <span class="text-gray-500">
                      완료: {{ category.completedCount }}개
                    </span>
                    <span class="text-gray-500">
                      전체: {{ category.totalCount }}개
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div v-else class="p-8 text-center">
            <p class="text-gray-500">카테고리별 통계가 없습니다.</p>
            <p class="text-gray-400 text-sm">할일을 추가하고 카테고리를 설정해보세요!</p>
          </div>
        </div>

        <!-- 추가 인사이트 -->
        <div class="mt-8 grid grid-cols-1 lg:grid-cols-2 gap-6">
          <!-- 최근 완료한 할일 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h3 class="text-lg font-semibold text-gray-900 mb-4">최근 활동</h3>
            <div class="space-y-3">
              <div v-if="recentCompletedTodos.length > 0">
                <div v-for="todo in recentCompletedTodos" :key="todo.id"
                     class="flex items-center gap-3 p-3 bg-green-50 rounded-lg">
                  <span class="text-green-600">✓</span>
                  <div class="flex-1">
                    <p class="text-sm font-medium text-gray-900">{{ todo.content }}</p>
                    <p class="text-xs text-gray-500">{{ formatDate(todo.completedAt) }} 완료</p>
                  </div>
                </div>
              </div>
              <div v-else class="text-center py-4">
                <p class="text-gray-500 text-sm">최근 완료한 할일이 없습니다.</p>
              </div>
            </div>
          </div>

          <!-- 우선순위별 분포 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h3 class="text-lg font-semibold text-gray-900 mb-4">우선순위별 분포</h3>
            <div class="space-y-3">
              <div class="flex items-center justify-between p-3 border rounded-lg">
                <div class="flex items-center gap-2">
                  <div class="w-3 h-3 bg-red-500 rounded-full"></div>
                  <span class="text-sm font-medium">높음</span>
                </div>
                <span class="text-sm text-gray-600">{{ priorityStats.high || 0 }}개</span>
              </div>

              <div class="flex items-center justify-between p-3 border rounded-lg">
                <div class="flex items-center gap-2">
                  <div class="w-3 h-3 bg-yellow-500 rounded-full"></div>
                  <span class="text-sm font-medium">보통</span>
                </div>
                <span class="text-sm text-gray-600">{{ priorityStats.medium || 0 }}개</span>
              </div>

              <div class="flex items-center justify-between p-3 border rounded-lg">
                <div class="flex items-center gap-2">
                  <div class="w-3 h-3 bg-green-500 rounded-full"></div>
                  <span class="text-sm font-medium">낮음</span>
                </div>
                <span class="text-sm text-gray-600">{{ priorityStats.low || 0 }}개</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 성취 배지 -->
        <div class="mt-8 bg-white rounded-lg shadow-sm p-6">
          <h3 class="text-lg font-semibold text-gray-900 mb-4">성취 현황</h3>
          <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
            <div class="text-center p-4 border rounded-lg"
                 :class="stats.completedTodos >= 1 ? 'bg-yellow-50 border-yellow-200' : 'bg-gray-50'">
              <div class="text-2xl mb-2">🌟</div>
              <p class="text-sm font-medium">첫 완료</p>
              <p class="text-xs text-gray-500">할일 1개 완료</p>
            </div>

            <div class="text-center p-4 border rounded-lg"
                 :class="stats.completedTodos >= 10 ? 'bg-yellow-50 border-yellow-200' : 'bg-gray-50'">
              <div class="text-2xl mb-2">🏆</div>
              <p class="text-sm font-medium">달성자</p>
              <p class="text-xs text-gray-500">할일 10개 완료</p>
            </div>

            <div class="text-center p-4 border rounded-lg"
                 :class="stats.completionRate >= 50 ? 'bg-yellow-50 border-yellow-200' : 'bg-gray-50'">
              <div class="text-2xl mb-2">📈</div>
              <p class="text-sm font-medium">절반 달성</p>
              <p class="text-xs text-gray-500">완료율 50% 달성</p>
            </div>

            <div class="text-center p-4 border rounded-lg"
                 :class="stats.completionRate >= 80 ? 'bg-yellow-50 border-yellow-200' : 'bg-gray-50'">
              <div class="text-2xl mb-2">🎯</div>
              <p class="text-sm font-medium">완벽주의자</p>
              <p class="text-xs text-gray-500">완료율 80% 달성</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, inject, computed } from 'vue'
import axios from 'axios'

export default {
  name: 'Statistics',
  setup() {
    const stats = ref({
      totalTodos: 0,
      completedTodos: 0,
      pendingTodos: 0,
      inProgressTodos: 0,
      completionRate: 0,
      categoryStats: []
    })

    const recentCompletedTodos = ref([])
    const loading = ref(false)
    const notification = inject('notification')

    const userId = 1 // 임시 사용자 ID

    // 우선순위별 통계 계산
    const priorityStats = computed(() => {
      // 실제로는 API에서 받아와야 하지만 임시로 계산
      return {
        high: Math.floor(stats.value.totalTodos * 0.3),
        medium: Math.floor(stats.value.totalTodos * 0.5),
        low: Math.floor(stats.value.totalTodos * 0.2)
      }
    })

    const fetchStats = async () => {
      loading.value = true
      try {
        const response = await axios.get(`http://localhost:8080/api/todos/stats?userId=${userId}`)

        if (response.data) {
          stats.value = response.data
        }
      } catch (error) {
        console.error('통계 조회 실패:', error)
        notification?.showNotification('통계를 불러오는데 실패했습니다.', 'error')
      } finally {
        loading.value = false
      }
    }

    const fetchRecentCompletedTodos = async () => {
      try {
        // 최근 완료된 할일 조회 (완료된 것만, 최신순으로 5개)
        const response = await axios.get(`http://localhost:8080/api/todos?userId=${userId}&status=COMPLETED&size=5`)

        if (response.data) {
          recentCompletedTodos.value = response.data.content
        }
      } catch (error) {
        console.error('최근 완료 할일 조회 실패:', error)
      }
    }

    const getCategoryProgress = (category) => {
      if (category.totalCount === 0) return 0
      return Math.round((category.completedCount / category.totalCount) * 100)
    }

    const formatDate = (dateString) => {
      const date = new Date(dateString)
      const now = new Date()
      const diffTime = Math.abs(now - date)
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))

      if (diffDays === 1) return '오늘'
      if (diffDays === 2) return '어제'
      if (diffDays <= 7) return `${diffDays}일 전`

      return date.toLocaleDateString('ko-KR')
    }

    onMounted(() => {
      fetchStats()
      fetchRecentCompletedTodos()
    })

    return {
      stats,
      recentCompletedTodos,
      loading,
      priorityStats,
      getCategoryProgress,
      formatDate
    }
  }
}
</script>