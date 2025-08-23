<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50" @click="closeOnOutside">
    <div class="bg-white rounded-lg shadow-xl max-w-2xl w-full mx-4 max-h-[90vh] overflow-y-auto" @click.stop>
      <!-- 헤더 -->
      <div class="px-6 py-4 border-b border-gray-200">
        <div class="flex items-center justify-between">
          <h2 class="text-xl font-semibold text-gray-900">
            {{ isEditing ? '할일 수정' : '새 할일 추가' }}
          </h2>
          <button @click="$emit('close')" class="text-gray-400 hover:text-gray-600">
            <span class="text-2xl">&times;</span>
          </button>
        </div>
      </div>

      <!-- 폼 내용 -->
      <form @submit.prevent="handleSubmit" class="p-6">
        <!-- 할일 내용 -->
        <div class="mb-6">
          <label class="block text-sm font-medium text-gray-700 mb-2">
            할일 내용 <span class="text-red-500">*</span>
          </label>
          <textarea v-model="form.content"
                    placeholder="할일을 입력하세요"
                    rows="3"
                    class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                    required></textarea>
        </div>

        <!-- 카테고리 & 우선순위 -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">카테고리</label>
            <select v-model="form.categoryId"
                    class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
              <option value="">카테고리 선택</option>
              <option v-for="category in categories" :key="category.id" :value="category.id">
                {{ category.name }}
              </option>
            </select>
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">우선순위</label>
            <select v-model="form.priority"
                    class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
              <option value="LOW">낮음</option>
              <option value="MEDIUM">보통</option>
              <option value="HIGH">높음</option>
            </select>
          </div>
        </div>

        <!-- 일정 설정 -->
        <div class="mb-6">
          <div class="flex items-center mb-3">
            <input type="checkbox" v-model="hasSchedule" id="hasSchedule"
                   class="rounded border-gray-300 text-blue-600 focus:ring-blue-500">
            <label for="hasSchedule" class="ml-2 text-sm font-medium text-gray-700">일정 설정</label>
          </div>

          <div v-if="hasSchedule" class="grid grid-cols-1 md:grid-cols-2 gap-4 pl-6 border-l-2 border-blue-200">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">시작일</label>
              <input type="date" v-model="schedule.startDate"
                     class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">마감일</label>
              <input type="date" v-model="schedule.dueDate"
                     class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
            </div>
          </div>
        </div>

        <!-- 반복 설정 -->
        <div class="mb-6">
          <div class="flex items-center mb-3">
            <input type="checkbox" v-model="hasRepeat" id="hasRepeat"
                   class="rounded border-gray-300 text-blue-600 focus:ring-blue-500">
            <label for="hasRepeat" class="ml-2 text-sm font-medium text-gray-700">반복 설정</label>
          </div>

          <div v-if="hasRepeat" class="pl-6 border-l-2 border-green-200 space-y-4">
            <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">반복 패턴</label>
                <select v-model="repeat.repeatPattern"
                        class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
                  <option value="DAILY">매일</option>
                  <option value="WEEKLY">매주</option>
                  <option value="MONTHLY">매월</option>
                </select>
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">반복 시작</label>
                <input type="date" v-model="repeat.repeatStart"
                       class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">반복 종료</label>
                <input type="date" v-model="repeat.repeatDue"
                       class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500">
              </div>
            </div>

            <!-- 요일 선택 (주간 반복일 때만) -->
            <div v-if="repeat.repeatPattern === 'WEEKLY'">
              <label class="block text-sm font-medium text-gray-700 mb-2">반복 요일</label>
              <div class="flex gap-2">
                <label v-for="(day, index) in weekDays" :key="index"
                       class="flex items-center">
                  <input type="checkbox" :value="index + 1" v-model="repeat.dayOfWeek"
                         class="rounded border-gray-300 text-blue-600 focus:ring-blue-500">
                  <span class="ml-1 text-sm">{{ day }}</span>
                </label>
              </div>
            </div>
          </div>
        </div>

        <!-- 버튼 -->
        <div class="flex justify-end gap-3 pt-4 border-t border-gray-200">
          <button type="button" @click="$emit('close')"
                  class="px-4 py-2 text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200">
            취소
          </button>
          <button type="submit" :disabled="saving"
                  class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 disabled:opacity-50">
            {{ saving ? '저장중...' : (isEditing ? '수정' : '생성') }}
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { ref, computed, watch, onMounted } from 'vue'
import axios from 'axios'

export default {
  name: 'TodoModal',
  props: {
    todo: {
      type: Object,
      default: null
    },
    categories: {
      type: Array,
      default: () => []
    }
  },
  emits: ['close', 'save'],
  setup(props, { emit }) {
    const saving = ref(false)
    const hasSchedule = ref(false)
    const hasRepeat = ref(false)

    const form = ref({
      content: '',
      priority: 'MEDIUM',
      categoryId: ''
    })

    const schedule = ref({
      startDate: '',
      dueDate: ''
    })

    const repeat = ref({
      isRepeated: true,
      repeatStart: '',
      repeatDue: '',
      repeatPattern: 'DAILY',
      dayOfWeek: []
    })

    const weekDays = ['월', '화', '수', '목', '금', '토', '일']

    const isEditing = computed(() => !!props.todo?.id)

    // 편집 모드일 때 기존 데이터 로드
    const loadTodoData = async () => {
      if (!isEditing.value) return

      try {
        const response = await axios.get(`http://localhost:8080/api/todos/${props.todo.id}`)

        if (response.data) {
          const todoData = response.data

          // 기본 정보
          form.value = {
            content: todoData.content,
            priority: todoData.priority,
            categoryId: todoData.categoryDto?.id || ''
          }

          // 일정 정보
          if (todoData.scheduleDto) {
            hasSchedule.value = true
            schedule.value = {
              startDate: todoData.scheduleDto.startDate,
              dueDate: todoData.scheduleDto.dueDate
            }
          }

          // 반복 설정
          if (todoData.repeatSettingDto && todoData.repeatSettingDto.isRepeated) {
            hasRepeat.value = true
            repeat.value = {
              isRepeated: true,
              repeatStart: todoData.repeatSettingDto.repeatStart,
              repeatDue: todoData.repeatSettingDto.repeatDue,
              repeatPattern: todoData.repeatSettingDto.repeatPattern,
              dayOfWeek: todoData.repeatSettingDto.dayOfWeek || []
            }
          }
        }
      } catch (error) {
        console.error('할일 상세 정보 조회 실패:', error)
      }
    }

    const handleSubmit = async () => {
      if (!form.value.content.trim()) {
        alert('할일 내용을 입력하세요.')
        return
      }

      saving.value = true

      try {
        let todoResponse

        // 할일 생성/수정
        if (isEditing.value) {
          todoResponse = await axios.put(`http://localhost:8080/api/todos/${props.todo.id}`, form.value)
        } else {
          todoResponse = await axios.post('http://localhost:8080/api/todos', form.value)
        }

        if (!todoResponse.data) {
          throw new Error('할일 저장 실패')
        }

        const todoId = todoResponse.data.id

        // 일정 설정 처리
        if (hasSchedule.value && schedule.value.startDate) {
          if (isEditing.value && props.todo.hasSchedule) {
            // 기존 일정 수정 (scheduleId가 필요하지만 여기서는 간단히 처리)
            await axios.post(`http://localhost:8080/api/todos/${todoId}/schedule`, schedule.value)
          } else {
            // 새 일정 추가
            schedule.value.startDate += "T00:00:00";
            schedule.value.dueDate += "T23:59:59";
            await axios.post(`http://localhost:8080/api/todos/${todoId}/schedule`, schedule.value)
          }
        }

        // 반복 설정 처리
        if (hasRepeat.value && repeat.value.repeatStart) {
          console.log(hasRepeat.value + ": " + repeat.value.repeatStart);
          const repeatData = {
            ...repeat.value,
            dayOfWeek: repeat.value.repeatPattern === 'WEEKLY' ? repeat.value.dayOfWeek : []
          }

          if (isEditing.value && props.todo.hasRepeat) {
            // 기존 반복 설정 수정
            await axios.post(`http://localhost:8080/api/todos/${todoId}/repeat`, repeatData)
          } else {
            // 새 반복 설정 추가
            await axios.post(`http://localhost:8080/api/todos/${todoId}/repeat`, repeatData)
          }
        }

        emit('save')
      } catch (error) {
        console.error('할일 저장 실패:', error)
        alert('할일 저장에 실패했습니다.')
      } finally {
        saving.value = false
      }
    }

    const closeOnOutside = (event) => {
      if (event.target === event.currentTarget) {
        emit('close')
      }
    }

    // 반복 패턴이 변경될 때 요일 초기화
    watch(() => repeat.value.repeatPattern, (newPattern) => {
      if (newPattern !== 'WEEKLY') {
        repeat.value.dayOfWeek = []
      }
    })

    onMounted(() => {
      if (isEditing.value) {
        loadTodoData()
      } else {
        // 새 할일의 기본값 설정
        const today = new Date().toISOString().split('T')[0]
        schedule.value.startDate = today
        repeat.value.repeatStart = today
      }
    })

    return {
      saving,
      hasSchedule,
      hasRepeat,
      form,
      schedule,
      repeat,
      weekDays,
      isEditing,
      handleSubmit,
      closeOnOutside
    }
  }
}
</script>