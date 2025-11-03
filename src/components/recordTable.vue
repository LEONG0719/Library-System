<template>
  <div class="bg-white/80 backdrop-blur-lg rounded-2xl shadow-xl overflow-hidden border border-indigo-100">
    <div class="overflow-x-auto">
      <table class="w-full">
        <thead class="bg-gradient-to-r from-indigo-50 to-purple-50 border-b-2 border-indigo-200">
          <tr>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Member</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Book</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Borrow Date</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Return Date</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Status</th>
            <th class="px-6 py-5 text-center text-sm font-bold text-indigo-900 uppercase tracking-wider">Actions</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-gray-100">
          <tr
            v-for="record in records"
            :key="record.bookRecord_id"
            class="hover:bg-gradient-to-r hover:from-indigo-50/50 hover:to-purple-50/50 transition-all duration-200"
          >
            <td class="px-6 py-5 text-sm font-semibold text-gray-900">{{ record.member?.member_name }}</td>
            <td class="px-6 py-5 text-sm font-semibold text-gray-900">{{ record.book?.bookTitle }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ record.borrow_date }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ record.return_date || 'Not returned' }}</td>
            <td class="px-6 py-5">
              <span
                :class="[
                  'px-4 py-2 rounded-full text-xs font-bold uppercase tracking-wide',
                  record.bookRecord_status === 'borrowed'
                    ? 'bg-gradient-to-r from-amber-100 to-amber-200 text-amber-800 border border-amber-300'
                    : 'bg-gradient-to-r from-emerald-100 to-emerald-200 text-emerald-800 border border-emerald-300'
                ]"
              >
                {{ record.bookRecord_status }}
              </span>
            </td>
            <td class="px-6 py-5 text-center">
              <button
                v-if="record.bookRecord_status === 'borrowed'"
                @click="emit('return', record.book.bookId)"
                class="px-6 py-3 bg-gradient-to-r from-emerald-600 to-emerald-700 text-white rounded-lg hover:from-emerald-700 hover:to-emerald-800 text-sm font-semibold transition-all duration-200 transform hover:scale-105 shadow-md"
              >
                Return Book
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="records.length === 0" class="text-center py-20">
      <div class="text-6xl mb-4">📋</div>
      <p class="text-gray-500 text-lg font-medium">No records found</p>
      <p class="text-gray-400 text-sm mt-2">Borrow a book to create your first record</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { BookRecord } from '../types';

defineProps<{
  records: BookRecord[];
}>();

const emit = defineEmits<{
  return: [bookId: number];
}>();
</script>