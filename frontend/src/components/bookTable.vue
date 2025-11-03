<template>
  <div class="bg-white/80 backdrop-blur-lg rounded-2xl shadow-xl overflow-hidden border border-indigo-100">
    <div class="overflow-x-auto">
      <table class="w-full">
        <thead class="bg-gradient-to-r from-indigo-50 to-purple-50 border-b-2 border-indigo-200">
          <tr>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Title</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Author</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Genre</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Price</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Stock</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Status</th>
            <th class="px-6 py-5 text-center text-sm font-bold text-indigo-900 uppercase tracking-wider">Actions</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-gray-100">
          <tr
            v-for="book in books"
            :key="book.bookId"
            class="hover:bg-gradient-to-r hover:from-indigo-50/50 hover:to-purple-50/50 transition-all duration-200"
          >
            <td class="px-6 py-5 text-sm font-semibold text-gray-900">{{ book.bookTitle }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ book.bookAuthor }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ book.bookGenre || 'N/A' }}</td>
            <td class="px-6 py-5 text-sm font-semibold text-emerald-600">${{ book.bookPrice }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ book.bookStockQuantity || 0 }}</td>
            <td class="px-6 py-5">
              <span
                :class="[
                  'px-4 py-2 rounded-full text-xs font-bold uppercase tracking-wide',
                  book.book_status === 'Available'
                    ? 'bg-gradient-to-r from-emerald-100 to-emerald-200 text-emerald-800 border border-emerald-300'
                    : 'bg-gradient-to-r from-red-100 to-red-200 text-red-800 border border-red-300'
                ]"
              >
                {{ book.book_status || 'Available' }}
              </span>
            </td>
            <td class="px-6 py-5 text-center">
              <button
                @click="emit('delete', book.bookId)"
                class="text-red-600 hover:text-white hover:bg-red-600 p-3 rounded-lg transition-all duration-200 transform hover:scale-110"
                title="Delete book"
              >
                🗑️
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="books.length === 0" class="text-center py-20">
      <div class="text-6xl mb-4">📚</div>
      <p class="text-gray-500 text-lg font-medium">No books found</p>
      <p class="text-gray-400 text-sm mt-2">Add your first book to get started</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Book } from '../types';

defineProps<{
  books: Book[];
}>();

const emit = defineEmits<{
  delete: [id: number];
}>();
</script>