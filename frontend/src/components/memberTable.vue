<template>
  <div class="bg-white/80 backdrop-blur-lg rounded-2xl shadow-xl overflow-hidden border border-indigo-100">
    <div class="overflow-x-auto">
      <table class="w-full">
        <thead class="bg-gradient-to-r from-indigo-50 to-purple-50 border-b-2 border-indigo-200">
          <tr>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">ID</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Name</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Email</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Phone</th>
            <th class="px-6 py-5 text-left text-sm font-bold text-indigo-900 uppercase tracking-wider">Join Date</th>
            <th class="px-6 py-5 text-center text-sm font-bold text-indigo-900 uppercase tracking-wider">Actions</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-gray-100">
          <tr
            v-for="member in members"
            :key="member.memberId"
            class="hover:bg-gradient-to-r hover:from-indigo-50/50 hover:to-purple-50/50 transition-all duration-200"
          >
            <td class="px-6 py-5 text-sm font-semibold text-indigo-600">{{ member.memberId }}</td>
            <td class="px-6 py-5 text-sm font-semibold text-gray-900">{{ member.member_name }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ member.member_email || 'N/A' }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ member.member_phone }}</td>
            <td class="px-6 py-5 text-sm text-gray-700">{{ member.joinDate }}</td>
            <td class="px-6 py-5 text-center">
              <div class="flex justify-center gap-2">
                <button
                  @click="emit('edit', member)"
                  class="text-blue-600 hover:text-white hover:bg-blue-600 p-3 rounded-lg transition-all duration-200 transform hover:scale-110"
                  title="Edit member"
                >
                  ✏️
                </button>
                <button
                  @click="emit('delete', member.memberId)"
                  class="text-red-600 hover:text-white hover:bg-red-600 p-3 rounded-lg transition-all duration-200 transform hover:scale-110"
                  title="Delete member"
                >
                  🗑️
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="members.length === 0" class="text-center py-20">
      <div class="text-6xl mb-4">👥</div>
      <p class="text-gray-500 text-lg font-medium">No members found</p>
      <p class="text-gray-400 text-sm mt-2">Add your first member to get started</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Member } from '../types';

defineProps<{
  members: Member[];
}>();

const emit = defineEmits<{
  edit: [member: Member];
  delete: [id: number];
}>();
</script>