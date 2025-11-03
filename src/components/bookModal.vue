<template>
  <div
    v-if="show"
    class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4"
    @click.self="emit('close')"
  >
    <div class="bg-white rounded-lg max-w-2xl w-full max-h-[90vh] overflow-y-auto">
      <div class="flex justify-between items-center p-6 border-b">
        <h2 class="text-2xl font-bold text-gray-800">Add New Book</h2>
        <button
          @click="emit('close')"
          class="text-gray-500 hover:text-gray-700 text-2xl"
        >
          ✕
        </button>
      </div>

      <div class="p-6">
        <form @submit.prevent="handleSubmit" class="space-y-4">
          <div class="grid grid-cols-2 gap-4">
            <input
              v-model="form.bookTitle"
              type="text"
              placeholder="Book Title *"
              required
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
            <input
              v-model="form.bookAuthor"
              type="text"
              placeholder="Author *"
              required
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
          </div>

          <div class="grid grid-cols-2 gap-4">
            <input
              v-model="form.bookPublisher"
              type="text"
              placeholder="Publisher"
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
            <input
              v-model="form.bookGenre"
              type="text"
              placeholder="Genre"
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
          </div>

          <div class="grid grid-cols-2 gap-4">
            <input
              v-model="form.bookPublicationYear"
              type="number"
              placeholder="Publication Year"
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
            <input
              v-model="form.bookLanguage"
              type="text"
              placeholder="Language"
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
          </div>

          <div class="grid grid-cols-2 gap-4">
            <input
              v-model="form.bookPrice"
              type="number"
              step="0.01"
              placeholder="Price *"
              required
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
            <input
              v-model="form.bookStockQuantity"
              type="number"
              placeholder="Stock Quantity"
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
          </div>

          <div class="grid grid-cols-2 gap-4">
            <input
              v-model="form.bookPages"
              type="number"
              placeholder="Pages"
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
            <input
              v-model="form.bookIsbn"
              type="text"
              placeholder="ISBN"
              maxlength="13"
              class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
          </div>

          <button
            type="submit"
            class="w-full px-6 py-3 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 font-medium transition"
          >
            Add Book
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
import type { BookFormData } from '../types';

defineProps<{
  show: boolean;
}>();

const emit = defineEmits<{
  close: [];
  submit: [data: BookFormData];
}>();

const form = reactive<BookFormData>({
  bookTitle: '',
  bookAuthor: '',
  bookPublisher: '',
  bookGenre: '',
  bookPublicationYear: '',
  bookLanguage: '',
  bookPrice: '',
  bookStockQuantity: '',
  bookPages: '',
  bookIsbn: ''
});

const handleSubmit = () => {
  emit('submit', { ...form });
  Object.keys(form).forEach(key => {
    form[key as keyof BookFormData] = '';
  });
};
</script>