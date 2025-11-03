<template>
  <transition name="slide-fade">
    <div
      v-if="show"
      :class="[
        'fixed top-4 right-4 px-6 py-3 rounded-lg shadow-lg z-50 flex items-center gap-2',
        type === 'error' ? 'bg-red-500 text-white' : 'bg-green-500 text-white'
      ]"
    >
      <span>{{ type === 'error' ? '✕' : '✓' }}</span>
      <span>{{ message }}</span>
    </div>
  </transition>
</template>

<script setup lang="ts">
defineProps<{
  show: boolean;
  message: string;
  type: 'success' | 'error';
}>();
</script>

<style scoped>
.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}
</style>