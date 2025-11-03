<template>
    <div class="min-h-screen bg-white">
    <!-- Header -->
<header class="bg-white shadow-lg border-b border-indigo-100 sticky top-0 z-50">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <div class="flex items-center justify-between">
          <div class="flex items-center gap-4">
<div class="w-20 h-20 bg-gradient-to-br from-indigo-600 to-purple-600 rounded-3xl flex items-center justify-center shadow-lg transform hover:scale-105 transition-transform">
  <span class="text-white text-3xl"></span>
</div>

            <div>
             <h1 class="text-3xl font-bold text-indigo-800">
  Library Management
</h1>

              <p class="text-sm text-gray-500 mt-1">Manage your collection with ease</p>
            </div>
          </div>
          <div class="flex items-center gap-6">
            <div class="text-center px-6 py-3 bg-gradient-to-br from-indigo-500 to-indigo-600 rounded-xl shadow-lg transform hover:scale-105 transition-all">
              <p class="text-xs text-indigo-100 font-medium">Total Books</p>
              <p class="text-3xl font-bold text-white">{{ books.length }}</p>
            </div>
            <div class="text-center px-6 py-3 bg-gradient-to-br from-emerald-500 to-emerald-600 rounded-xl shadow-lg transform hover:scale-105 transition-all">
              <p class="text-xs text-emerald-100 font-medium">Total Members</p>
              <p class="text-3xl font-bold text-white">{{ members.length }}</p>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <main class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <!-- Tab Navigation -->
      <div class="bg-white/80 backdrop-blur-lg rounded-2xl shadow-xl mb-8 overflow-hidden border border-indigo-100">
        <nav class="flex">
          <button
            v-for="tab in tabs"
            :key="tab.id"
            @click="activeTab = tab.id"
            :class="[
              'flex-1 px-8 py-5 text-base font-semibold transition-all duration-300 relative',
              activeTab === tab.id
                ? 'text-indigo-600 bg-gradient-to-br from-indigo-50 to-purple-50'
                : 'text-gray-500 hover:text-gray-700 hover:bg-gray-50'
            ]"
          >
            <span class="relative z-10">{{ tab.label }}</span>
            <div
              v-if="activeTab === tab.id"
              class="absolute bottom-0 left-0 right-0 h-1 bg-gradient-to-r from-indigo-600 to-purple-600"
            ></div>
          </button>
        </nav>
      </div>

      <!-- Books Tab -->
      <div v-show="activeTab === 'books'" class="space-y-6 animate-fade-in">
        <div class="flex justify-between items-center">
          <div>
            <h2 class="text-3xl font-bold text-gray-800">Book Inventory</h2>
            <p class="text-gray-500 mt-1">Manage your book collection</p>
          </div>
          <button
            @click="showBookModal = true"
            class="px-8 py-4 bg-gradient-to-r from-indigo-600 to-purple-600 text-white rounded-xl hover:from-indigo-700 hover:to-purple-700 font-semibold transition-all duration-300 flex items-center gap-3 shadow-lg hover:shadow-xl transform hover:scale-105"
          >
            <span class="text-xl">➕</span>
            <span>Add New Book</span>
          </button>
        </div>
        <BookTable :books="books" @delete="handleDeleteBook" />
      </div>

      <!-- Members Tab -->
      <div v-show="activeTab === 'members'" class="space-y-6 animate-fade-in">
        <div class="flex justify-between items-center">
          <div>
            <h2 class="text-3xl font-bold text-gray-800">Member Directory</h2>
            <p class="text-gray-500 mt-1">Manage library members</p>
          </div>
          <button
            @click="showMemberModal = true"
            class="px-8 py-4 bg-gradient-to-r from-indigo-600 to-purple-600 text-white rounded-xl hover:from-indigo-700 hover:to-purple-700 font-semibold transition-all duration-300 flex items-center gap-3 shadow-lg hover:shadow-xl transform hover:scale-105"
          >
            <span class="text-xl">➕</span>
            <span>Add New Member</span>
          </button>
        </div>
        <MemberTable 
          :members="members" 
          @edit="handleEditMember" 
          @delete="handleDeleteMember" 
        />
      </div>

      <!-- Records Tab -->
      <div v-show="activeTab === 'records'" class="space-y-6 animate-fade-in">
        <div class="flex justify-between items-center">
          <div>
            <h2 class="text-3xl font-bold text-gray-800">Borrowing Records</h2>
            <p class="text-gray-500 mt-1">Track book loans and returns</p>
          </div>
          <div class="flex gap-4">
            <button
              @click="sortRecordsByDate"
              class="px-6 py-4 bg-gradient-to-r from-gray-600 to-gray-700 text-white rounded-xl hover:from-gray-700 hover:to-gray-800 font-semibold transition-all duration-300 flex items-center gap-3 shadow-lg hover:shadow-xl transform hover:scale-105"
            >
              <span class="text-xl">🔄</span>
              <span>Sort by Date</span>
            </button>
            <button
              @click="showBorrowModal = true"
              class="px-6 py-4 bg-gradient-to-r from-emerald-600 to-emerald-700 text-white rounded-xl hover:from-emerald-700 hover:to-emerald-800 font-semibold transition-all duration-300 flex items-center gap-3 shadow-lg hover:shadow-xl transform hover:scale-105"
            >
              <span class="text-xl">📖</span>
              <span>Borrow Book</span>
            </button>
          </div>
        </div>
        <RecordTable 
          :records="records" 
          @return="handleReturnBook" 
        />
      </div>
    </main>

    <!-- Modals -->
    <BookModal 
      :show="showBookModal" 
      @close="showBookModal = false" 
      @submit="handleAddBook" 
    />
    
    <MemberModal 
      :show="showMemberModal" 
      :edit-mode="editMode"
      :member="selectedMember"
      @close="closeMemberModal" 
      @submit="handleSubmitMember" 
    />
    
    <BorrowModal 
      :show="showBorrowModal" 
      @close="showBorrowModal = false" 
      @submit="handleBorrowBook" 
    />

    <!-- Notification -->
    <Notification 
      :show="notification.show" 
      :message="notification.message" 
      :type="notification.type" 
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { bookApi } from '../api/bookApi';
import { memberApi } from '../api/memberApi';
import { recordApi } from '../api/recordApi';
import BookTable from '../components/bookTable.vue';
import MemberTable from '../components/memberTable.vue';
import RecordTable from '../components/recordTable.vue';
import BookModal from '../components/bookModal.vue';
import MemberModal from '../components/memberModal.vue';
import BorrowModal from '../components/borrowModal.vue';
import Notification from '../components/notification.vue';
import type { 
  Book, 
  Member, 
  BookRecord, 
  BookFormData, 
  MemberFormData, 
  BorrowFormData,
  NotificationState 
} from '../types';

// State
const activeTab = ref('books');
const books = ref<Book[]>([]);
const members = ref<Member[]>([]);
const records = ref<BookRecord[]>([]);

const showBookModal = ref(false);
const showMemberModal = ref(false);
const showBorrowModal = ref(false);
const editMode = ref(false);
const selectedMember = ref<Member | null>(null);

const notification = ref<NotificationState>({
  show: false,
  message: '',
  type: 'success'
});

const tabs = [
  { id: 'books', label: '📚 Books' },
  { id: 'members', label: '👥 Members' },
  { id: 'records', label: '📋 Records' }
];

// Notification helper
const showNotification = (message: string, type: 'success' | 'error' = 'success') => {
  notification.value = { show: true, message, type };
  setTimeout(() => {
    notification.value.show = false;
  }, 3000);
};

// Load data
const loadBooks = async () => {
  try {
    const response = await bookApi.getAllBooks();
    books.value = response.data;
  } catch (error) {
    showNotification('Failed to load books', 'error');
    console.error('Error loading books:', error);
  }
};

const loadMembers = async () => {
  try {
    const response = await memberApi.getAllMembers();
    members.value = response.data;
  } catch (error) {
    showNotification('Failed to load members', 'error');
    console.error('Error loading members:', error);
  }
};

const loadRecords = async () => {
  try {
    const response = await recordApi.getAllRecords();
    records.value = response.data;
  } catch (error) {
    showNotification('Failed to load records', 'error');
    console.error('Error loading records:', error);
  }
};

// Book handlers
const handleAddBook = async (data: BookFormData) => {
  try {
    const bookData: Partial<Book> = {
      bookTitle: data.bookTitle,
      bookAuthor: data.bookAuthor,
      bookPrice: parseFloat(data.bookPrice)
    };

    if (data.bookPublisher) bookData.bookPublisher = data.bookPublisher;
    if (data.bookGenre) bookData.bookGenre = data.bookGenre;
    if (data.bookPublicationYear) bookData.bookPublicationYear = parseInt(data.bookPublicationYear);
    if (data.bookLanguage) bookData.bookLanguage = data.bookLanguage;
    if (data.bookStockQuantity) bookData.bookStockQuantity = parseInt(data.bookStockQuantity);
    if (data.bookPages) bookData.bookPages = parseInt(data.bookPages);
    if (data.bookIsbn) bookData.bookIsbn = data.bookIsbn;
    
    await bookApi.addBook(bookData);
    showNotification('Book added successfully!');
    showBookModal.value = false;
    await loadBooks();
  } catch (error) {
    showNotification('Failed to add book', 'error');
    console.error('Error adding book:', error);
  }
};

const handleDeleteBook = async (id: number) => {
  if (!confirm('Are you sure you want to delete this book?')) return;
  
  try {
    await bookApi.deleteBook(id);
    showNotification('Book deleted successfully!');
    await loadBooks();
  } catch (error) {
    showNotification('Failed to delete book', 'error');
    console.error('Error deleting book:', error);
  }
};

// Member handlers
const handleEditMember = (member: Member) => {
  selectedMember.value = member;
  editMode.value = true;
  showMemberModal.value = true;
};

const closeMemberModal = () => {
  showMemberModal.value = false;
  editMode.value = false;
  selectedMember.value = null;
};

const handleSubmitMember = async (data: MemberFormData, memberId?: number) => {
  try {
    const memberData: Partial<Member> = {
      member_name: data.member_name,
      member_phone: data.member_phone,
      joinDate: data.joinDate
    };

    if (data.member_email) {
      memberData.member_email = data.member_email;
    }

    if (editMode.value && memberId) {
      await memberApi.updateMember(memberId, memberData);
      showNotification('Member updated successfully!');
    } else {
      await memberApi.addMember(memberData);
      showNotification('Member added successfully!');
    }
    
    closeMemberModal();
    await loadMembers();
  } catch (error) {
    showNotification(editMode.value ? 'Failed to update member' : 'Failed to add member', 'error');
    console.error('Error submitting member:', error);
  }
};

const handleDeleteMember = async (id: number) => {
  if (!confirm('Are you sure you want to delete this member?')) return;
  
  try {
    await memberApi.deleteMember(id);
    showNotification('Member deleted successfully!');
    await loadMembers();
  } catch (error) {
    showNotification('Failed to delete member', 'error');
    console.error('Error deleting member:', error);
  }
};

// Record handlers
const handleBorrowBook = async (data: BorrowFormData) => {
  try {
    const memberId = parseInt(data.member_id);
    const bookTitle = data.book_title;
    
    await recordApi.borrowBook(memberId, bookTitle);
    showNotification('Book borrowed successfully!');
    showBorrowModal.value = false;
    await loadRecords();
    await loadBooks();
  } catch (error) {
    showNotification('Failed to borrow book', 'error');
    console.error('Error borrowing book:', error);
  }
};

const handleReturnBook = async (bookId: number) => {
  try {
    await recordApi.returnBook(bookId);
    showNotification('Book returned successfully!');
    await loadRecords();
    await loadBooks();
  } catch (error) {
    showNotification('Failed to return book', 'error');
    console.error('Error returning book:', error);
  }
};

const sortRecordsByDate = async () => {
  try {
    const response = await recordApi.sortRecordsByDate();
    records.value = response.data;
    showNotification('Records sorted by date');
  } catch (error) {
    showNotification('Failed to sort records', 'error');
    console.error('Error sorting records:', error);
  }
};

// Initialize
onMounted(async () => {
  await Promise.all([
    loadBooks(),
    loadMembers(),
    loadRecords()
  ]);
});
</script>

<style scoped>
@keyframes fade-in {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.animate-fade-in {
  animation: fade-in 0.5s ease-out;
}
</style>