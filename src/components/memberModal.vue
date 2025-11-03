<template>
  <div
    v-if="show"
    class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4"
    @click.self="emit('close')"
  >
    <div class="bg-white rounded-lg max-w-lg w-full">
      <div class="flex justify-between items-center p-6 border-b">
        <h2 class="text-2xl font-bold text-gray-800">
          {{ editMode ? 'Edit Member' : 'Add New Member' }}
        </h2>
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
            v-model="form.member_name"
            type="text"
            placeholder="Member Name *"
            required
            class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          />
          
          <input
            v-model="form.member_email"
            type="email"
            placeholder="Email"
            class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          />
          
          <input
            v-model="form.member_phone"
            type="tel"
            placeholder="Phone Number *"
            required
            class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          />
          
          <input
            v-model="form.joinDate"
            type="date"
            required
            class="w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          />

          <button
            type="submit"
            class="w-full px-6 py-3 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 font-medium transition"
          >
            {{ editMode ? 'Update Member' : 'Add Member' }}
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, watch } from 'vue';
import type { Member, MemberFormData } from '../types';

const props = defineProps<{
  show: boolean;
  editMode?: boolean;
  member?: Member | null;
}>();

const emit = defineEmits<{
  close: [];
  submit: [data: MemberFormData, memberId?: number];
}>();

const form = reactive<MemberFormData>({
  member_name: '',
  member_email: '',
  member_phone: '',
  joinDate: new Date().toISOString().split('T')[0] as string
});

watch(() => props.member, (newMember) => {
  if (newMember && props.editMode) {
    form.member_name = newMember.member_name;
    form.member_email = newMember.member_email || '';
    form.member_phone = newMember.member_phone;
    form.joinDate = (newMember.joinDate || new Date().toISOString().split('T')[0]) as string;
  }
}, { immediate: true });

watch(() => props.show, (show) => {
  if (!show && !props.editMode) {
    form.member_name = '';
    form.member_email = '';
    form.member_phone = '';
    form.joinDate = new Date().toISOString().split('T')[0] as string;
  }
});

const handleSubmit = () => {
  const memberId = props.member?.memberId;
  emit('submit', { ...form }, memberId);
};
</script>