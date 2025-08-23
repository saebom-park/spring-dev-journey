<template>
  <div class="min-h-screen bg-gray-50 p-6">
    <div class="max-w-4xl mx-auto">
      <!-- 헤더 -->
      <div class="mb-8 flex items-center justify-between">
        <div>
          <h1 class="text-3xl font-bold text-gray-900 mb-2">할일 관리</h1>
          <p class="text-gray-600">{{ currentUser }}님의 할일을 관리하세요</p>
        </div>
        <!-- 추가 버튼 -->
        <button @click="showCreateModal = true"
                class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 flex items-center gap-2">
          <span>+</span>
          할일 추가
        </button>
      </div>

      <!-- 필터 및 추가 버튼 -->
      <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
        <div class="flex flex-wrap gap-4 items-center justify-between">
          <div class="flex flex-wrap gap-4">
            <!-- 카테고리 필터 -->
            <select v-model="filters.categoryId" @change="fetchTodos"
                    class="px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
              <option value="">전체 카테고리</option>
              <option v-for="category in categories" :key="category.id" :value="category.id">
                {{ category.name }}
              </option>
            </select>

            <!-- 상태 필터 -->
            <select v-model="filters.status" @change="fetchTodos"
                    class="px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
              <option value="">전체 상태</option>
              <option value="PENDING">대기</option>
              <option value="IN_PROGRESS">진행중</option>
              <option value="COMPLETED">완료</option>
            </select>
          </div>
        </div>
      </div>

      <!-- 할일 목록 -->
      <div class="space-y-4">
        <div v-if="loading" class="text-center py-8">
          <div class="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600 mx-auto"></div>
          <p class="mt-2 text-gray-600">로딩중...</p>
        </div>

        <div v-else-if="todos.length === 0" class="text-center py-12 bg-white rounded-lg shadow-sm">
          <p class="text-gray-500 text-lg">할일이 없습니다.</p>
          <p class="text-gray-400">새로운 할일을 추가해보세요!</p>
        </div>

        <div v-else>
          <div v-for="todo in todos" :key="todo.id"
               class="bg-white rounded-lg shadow-sm border-l-4 p-6 hover:shadow-md transition-shadow"
               :class="getPriorityClass(todo.priority)"
               :style="{ borderLeftColor: todo.categoryDto?.color || '#e5e7eb' }">

            <div class="flex items-start justify-between">
              <div class="flex-1">
                <!-- 할일 내용 -->
                <div class="flex items-center gap-3 mb-2">
                  <button @click="toggleStatus(todo)"
                          class="w-5 h-5 rounded border-2 flex items-center justify-center"
                          :class="getStatusButtonClass(todo.status)">
                    <span v-if="todo.status === 'COMPLETED'" class="text-white text-xs">✓</span>
                  </button>
                  <h3 class="text-lg font-semibold"
                      :class="todo.status === 'COMPLETED' ? 'line-through text-gray-500' : 'text-gray-900'">
                    {{ todo.content }}
                  </h3>
                </div>

                <!-- 메타 정보 -->
                <div class="flex flex-wrap gap-4 text-sm text-gray-600 mb-3">
                  <span class="flex items-center gap-1">
                    <span class="w-3 h-3 rounded-full" :style="{ backgroundColor: todo.categoryDto?.color }"></span>
                    {{ todo.categoryDto?.name || '카테고리 없음' }}
                  </span>
                  <span class="px-2 py-1 rounded-full text-xs font-medium"
                        :class="getStatusClass(todo.status)">
                    {{ getStatusText(todo.status) }}
                  </span>
                  <span class="px-2 py-1 rounded-full text-xs font-medium"
                        :class="getPriorityBadgeClass(todo.priority)">
                    {{ getPriorityText(todo.priority) }}
                  </span>
                  <span>{{ formatDate(todo.createdAt) }}</span>
                </div>

                <!-- 추가 정보 -->
                <div class="flex gap-2 text-sm text-gray-500">
                  <span v-if="todo.hasSchedule" class="flex items-center gap-1">
                    📅 일정 있음
                  </span>
                  <span v-if="todo.hasRepeat" class="flex items-center gap-1">
                    🔄 반복 설정
                  </span>
                </div>
              </div>

              <!-- 액션 버튼 -->
              <div class="flex gap-2 ml-4">
                <button @click="editTodo(todo)"
                        class="text-gray-500 hover:text-blue-600 p-2">
                  ✏️
                </button>
                <button @click="deleteTodo(todo.id)"
                        class="text-gray-500 hover:text-red-600 p-2">
                  🗑️
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 페이지네이션 -->
      <!--<div v-if="pagination.totalPages > 1" class="mt-8 flex justify-center">
        <div class="flex gap-2">
          <button v-for="page in pagination.totalPages" :key="page"
                  @click="changePage(page - 1)"
                  class="px-3 py-2 rounded-md"
                  :class="pagination.number === page - 1
                    ? 'bg-blue-600 text-white'
                    : 'bg-white text-gray-700 hover:bg-gray-50'">
            {{ page }}
          </button>
        </div>
      </div>-->
    </div>

    <!-- 할일 생성/수정 모달 -->
    <TodoModal v-if="showCreateModal || editingTodo"
               :todo="editingTodo"
               :categories="categories"
               @close="closeModal"
               @save="handleSave" />
  </div>
</template>

<script>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'
import TodoModal from '../components/TodoModal.vue'

export default {
  name: 'TodoList',
  components: {
    TodoModal
  },
  setup() {
    const todos = ref([])
    const categories = ref([])
    const loading = ref(false)
    const showCreateModal = ref(false)
    const editingTodo = ref(null)

    const filters = ref({
      userId: 1, // 임시 사용자 ID
      categoryId: '',
      status: ''
    })

    const pagination = ref({
      number: 0,
      size: 10,
      totalElements: 0,
      totalPages: 0
    })

    const currentUser = computed(() => '봄이') // 임시 사용자명

    // API 호출
    const fetchTodos = async () => {
      loading.value = true
      try {
        const params = new URLSearchParams()
        params.append('userId', filters.value.userId)
        if (filters.value.categoryId) params.append('categoryId', filters.value.categoryId)
        if (filters.value.status) params.append('status', filters.value.status)

        const response = await axios.get(`http://localhost:8080/api/todos?${params}`)

        // 수정: 바로 배열 할당
        todos.value = response.data || []

      } catch (error) {
        console.error('할일 조회 실패:', error)
        alert('할일을 불러오는데 실패했습니다.')
      } finally {
        loading.value = false
      }
    }

    const fetchCategories = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/categories`)
        if (response.data) {
          categories.value = response.data
        }
      } catch (error) {
        console.error('카테고리 조회 실패:', error)
      }
    }

    const toggleStatus = async (todo) => {
      const newStatus = todo.status === 'COMPLETED' ? 'PENDING' : 'COMPLETED'
      try {
        const response = await axios.patch(`http://localhost:8080/api/todos/${todo.id}/status`, {
          status: newStatus
        })

        if (response.data) {
          await fetchTodos() // 목록 새로고침
        }
      } catch (error) {
        console.error('상태 변경 실패:', error)
        alert('상태 변경에 실패했습니다.')
      }
    }

    const deleteTodo = async (todoId) => {
      if (!confirm('정말 삭제하시겠습니까?')) return

      try {
        await axios.delete(`http://localhost:8080/api/todos/${todoId}`)
        await fetchTodos() // 목록 새로고침
      } catch (error) {
        console.error('할일 삭제 실패:', error)
        alert('할일 삭제에 실패했습니다.')
      }
    }

    const editTodo = (todo) => {
      editingTodo.value = { ...todo }
    }

    const closeModal = () => {
      showCreateModal.value = false
      editingTodo.value = null
    }

    const handleSave = async () => {
      await fetchTodos()
      closeModal()
    }

    const changePage = (page) => {
      filters.value.page = page
      fetchTodos()
    }

    // 스타일 헬퍼 함수들
    const getPriorityClass = (priority) => {
      const classes = {
        'HIGH': 'border-red-500',
        'MEDIUM': 'border-yellow-500',
        'LOW': 'border-green-500'
      }
      return classes[priority] || 'border-gray-300'
    }

    const getPriorityBadgeClass = (priority) => {
      const classes = {
        'HIGH': 'bg-red-100 text-red-800',
        'MEDIUM': 'bg-yellow-100 text-yellow-800',
        'LOW': 'bg-green-100 text-green-800'
      }
      return classes[priority] || 'bg-gray-100 text-gray-800'
    }

    const getStatusClass = (status) => {
      const classes = {
        'PENDING': 'bg-gray-100 text-gray-700',
        'IN_PROGRESS': 'bg-blue-100 text-blue-700',
        'COMPLETED': 'bg-green-100 text-green-700'
      }
      return classes[status] || 'bg-gray-100 text-gray-700'
    }

    const getStatusButtonClass = (status) => {
      const classes = {
        'PENDING': 'border-gray-300 hover:border-blue-500',
        'IN_PROGRESS': 'border-blue-500 bg-blue-50',
        'COMPLETED': 'border-green-500 bg-green-500'
      }
      return classes[status] || 'border-gray-300'
    }

    const getPriorityText = (priority) => {
      const texts = {
        'HIGH': '높음',
        'MEDIUM': '보통',
        'LOW': '낮음'
      }
      return texts[priority] || priority
    }

    const getStatusText = (status) => {
      const texts = {
        'PENDING': '대기',
        'IN_PROGRESS': '진행중',
        'COMPLETED': '완료'
      }
      return texts[status] || status
    }

    const formatDate = (dateString) => {
      return new Date(dateString).toLocaleDateString('ko-KR')
    }

    onMounted(() => {
      fetchCategories()
      fetchTodos()
    })

    return {
      todos,
      categories,
      loading,
      showCreateModal,
      editingTodo,
      filters,
      pagination,
      currentUser,
      fetchTodos,
      toggleStatus,
      deleteTodo,
      editTodo,
      closeModal,
      handleSave,
      changePage,
      getPriorityClass,
      getPriorityBadgeClass,
      getStatusClass,
      getStatusButtonClass,
      getPriorityText,
      getStatusText,
      formatDate
    }
  }
}
</script>