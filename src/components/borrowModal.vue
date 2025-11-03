<template>
  <div
    v-if="show"
    class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4"
    @click.self="emit('close')"
  >
    <div class="bg-white rounded-lg max-w-2xl w-full max-h-[90vh] overflow-y-auto">
      <div class="flex justify-between items-center p-6 border-b">
        <h2 class="text-2xl font-bold text-gray-800">Borrow Book</h2>
        <button
          @click="emit('close')"
          class="text-gray-500 hover:text-gray-700 text-2xl"
        >
          ✕
        </button>
      </div>

      <div class="p-6">
        <form @submit.prevent="handleSubmit" class="space-y-4">
          <input
            v-model="form.member_id"
            type="number"
            placeholder="Member ID *"
            required
            class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none"
          />
          <input
            v-model="form.book_title"
            type="text"
            placeholder="Book Title *"
            required
            class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none"
          />
          <button
            type="submit"
            class="w-full px-6 py-3 bg-green-600 text-white rounded-lg hover:bg-green-700 font-medium transition"
          >
            Borrow Book
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
import type { BorrowFormData } from '../types';

defineProps<{
  show: boolean;
}>();

const emit = defineEmits<{
  close: [];
  submit: [data: BorrowFormData];
}>();

const form = reactive<BorrowFormData>({
  member_id: '',
  book_title: ''
});

const handleSubmit = () => {
  emit('submit', { ...form });
  form.member_id = '';
  form.book_title = '';
};
</script>