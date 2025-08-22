<template>
  <div class="min-h-screen bg-gray-50 p-6">
    <div class="max-w-4xl mx-auto">
      <!-- 헤더 -->
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-900 mb-2">카테고리 관리</h1>
        <p class="text-gray-600">할일의 카테고리를 관리하세요</p>
      </div>

      <!-- 카테고리 추가 폼 -->
      <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">새 카테고리 추가</h2>

        <form @submit.prevent="addCategory" class="flex flex-wrap gap-4 items-end">
          <div class="flex-1 min-w-48">
            <label class="block text-sm font-medium text-gray-700 mb-2">카테고리 이름</label>
            <input v-model="newCategory.name"
                   type="text"
                   placeholder="카테고리 이름을 입력하세요"
                   class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                   required>
          </div>

          <div class="w-32">
            <label class="block text-sm font-medium text-gray-700 mb-2">색상</label>
            <div class="flex gap-2">
              <input v-model="newCategory.color"
                     type="color"
                     class="w-12 h-10 border border-gray-300 rounded cursor-pointer">
              <input v-model="newCategory.color"
                     type="text"
                     placeholder="#FF6B6B"
                     class="flex-1 px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 text-sm">
            </div>
          </div>

          <button type="submit" :disabled="creating"
                  class="px-6 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 disabled:opacity-50">
            {{ creating ? '추가중...' : '추가' }}
          </button>
        </form>
      </div>

      <!-- 카테고리 목록 -->
      <div class="bg-white rounded-lg shadow-sm">
        <div class="px-6 py-4 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">카테고리 목록</h2>
        </div>

        <div v-if="loading" class="p-8 text-center">
          <div class="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600 mx-auto"></div>
          <p class="mt-2 text-gray-600">로딩중...</p>
        </div>

        <div v-else-if="categories.length === 0" class="p-8 text-center">
          <p class="text-gray-500 text-lg">등록된 카테고리가 없습니다.</p>
          <p class="text-gray-400">새로운 카테고리를 추가해보세요!</p>
        </div>

        <div v-else class="divide-y divide-gray-200">
          <div v-for="category in categories" :key="category.id"
               class="p-6 hover:bg-gray-50 transition-colors">

            <div v-if="editingId === category.id" class="flex flex-wrap gap-4 items-center">
              <!-- 편집 모드 -->
              <div class="flex items-center gap-3 flex-1 min-w-48">
                <div class="w-6 h-6 rounded-full border-2 border-gray-300"
                     :style="{ backgroundColor: editForm.color }"></div>
                <input v-model="editForm.name"
                       type="text"
                       class="flex-1 px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
              </div>

              <div class="w-32">
                <input v-model="editForm.color"
                       type="color"
                       class="w-full h-10 border border-gray-300 rounded cursor-pointer">
              </div>

              <div class="flex gap-2">
                <button @click="saveEdit(category.id)" :disabled="updating"
                        class="px-3 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 disabled:opacity-50 text-sm">
                  {{ updating ? '저장중...' : '저장' }}
                </button>
                <button @click="cancelEdit"
                        class="px-3 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600 text-sm">
                  취소
                </button>
              </div>
            </div>

            <div v-else class="flex items-center justify-between">
              <!-- 일반 모드 -->
              <div class="flex items-center gap-4">
                <div class="w-6 h-6 rounded-full border-2 border-gray-300"
                     :style="{ backgroundColor: category.color }"></div>
                <div>
                  <h3 class="text-lg font-medium text-gray-900">{{ category.name }}</h3>
                  <p class="text-sm text-gray-500">{{ category.color }}</p>
                </div>
              </div>

              <div class="flex gap-2">
                <button @click="startEdit(category)"
                        class="text-gray-500 hover:text-blue-600 p-2">
                  ✏️ 수정
                </button>
                <button @click="deleteCategory(category.id)"
                        class="text-gray-500 hover:text-red-600 p-2">
                  🗑️ 삭제
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 프리셋 색상 -->
      <div class="mt-6 bg-white rounded-lg shadow-sm p-6">
        <h3 class="text-md font-medium text-gray-900 mb-3">추천 색상</h3>
        <div class="flex flex-wrap gap-2">
          <button v-for="color in presetColors" :key="color"
                  @click="newCategory.color = color"
                  class="w-8 h-8 rounded-full border-2 border-gray-300 hover:border-gray-400 transition-colors"
                  :style="{ backgroundColor: color }"
                  :title="color"></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, inject } from 'vue'
import axios from 'axios'

export default {
  name: 'CategoryManage',
  setup() {
    const categories = ref([])
    const loading = ref(false)
    const creating = ref(false)
    const updating = ref(false)
    const editingId = ref(null)

    const notification = inject('notification')

    const newCategory = ref({
      name: '',
      color: '#FF6B6B'
    })

    const editForm = ref({
      name: '',
      color: ''
    })

    const presetColors = [
      '#FF6B6B', '#4ECDC4', '#45B7D1', '#96CEB4', '#FECA57',
      '#FF9FF3', '#54A0FF', '#5F27CD', '#00D2D3', '#FF9F43',
      '#10AC84', '#EE5A6F', '#C44569', '#F8B500', '#6C5CE7'
    ]

    const userId = 1 // 임시 사용자 ID

    const fetchCategories = async () => {
      loading.value = true
      try {
        const response = await axios.get(`http://localhost:8080/api/categories`)

        if (response.data) {
          categories.value = response.data
        }
      } catch (error) {
        console.error('카테고리 조회 실패:', error)
        notification?.showNotification('카테고리를 불러오는데 실패했습니다.', 'error')
      } finally {
        loading.value = false
      }
    }

    const addCategory = async () => {
      if (!newCategory.value.name.trim()) {
        notification?.showNotification('카테고리 이름을 입력하세요.', 'error')
        return
      }

      creating.value = true
      try {
        const response = await axios.post('http://localhost:8080/api/categories', {
          name: newCategory.value.name.trim(),
          color: newCategory.value.color
        })

        if (response.data) {
          await fetchCategories()
          newCategory.value = {
            name: '',
            color: '#FF6B6B'
          }
          notification?.showNotification('카테고리가 추가되었습니다.')
        }
      } catch (error) {
        console.error('카테고리 추가 실패:', error)
        notification?.showNotification('카테고리 추가에 실패했습니다.', 'error')
      } finally {
        creating.value = false
      }
    }

    const startEdit = (category) => {
      editingId.value = category.id
      editForm.value = {
        name: category.name,
        color: category.color
      }
    }

    const cancelEdit = () => {
      editingId.value = null
      editForm.value = {
        name: '',
        color: ''
      }
    }

    const saveEdit = async (categoryId) => {
      if (!editForm.value.name.trim()) {
        notification?.showNotification('카테고리 이름을 입력하세요.', 'error')
        return
      }

      updating.value = true
      try {
        const response = await axios.put(`http://localhost:8080/api/categories/${categoryId}`, {
          name: editForm.value.name.trim(),
          color: editForm.value.color
        })

        if (response.data) {
          await fetchCategories()
          cancelEdit()
          notification?.showNotification('카테고리가 수정되었습니다.')
        }
      } catch (error) {
        console.error('카테고리 수정 실패:', error)
        notification?.showNotification('카테고리 수정에 실패했습니다.', 'error')
      } finally {
        updating.value = false
      }
    }

    const deleteCategory = async (categoryId) => {
      if (!confirm('정말 삭제하시겠습니까? 이 카테고리를 사용하는 할일들은 카테고리가 제거됩니다.')) {
        return
      }

      try {
        await axios.delete(`http://localhost:8080/api/categories/${categoryId}`)
        await fetchCategories()
        notification?.showNotification('카테고리가 삭제되었습니다.')
      } catch (error) {
        console.error('카테고리 삭제 실패:', error)
        notification?.showNotification('카테고리 삭제에 실패했습니다.', 'error')
      }
    }

    onMounted(() => {
      fetchCategories()
    })

    return {
      categories,
      loading,
      creating,
      updating,
      editingId,
      newCategory,
      editForm,
      presetColors,
      fetchCategories,
      addCategory,
      startEdit,
      cancelEdit,
      saveEdit,
      deleteCategory
    }
  }
}
</script>