import axios from 'axios';
import type { BookRecord } from '../types';

const API_URL = 'http://localhost:8080/BookRecord';

export const recordApi = {
  getAllRecords() {
    return axios.get<BookRecord[]>(`${API_URL}/getAllBookRecord`);
  },

  borrowBook(memberId: number, bookTitle: string) {
    return axios.post<BookRecord>(`${API_URL}/borrowRecord/${memberId}/${bookTitle}`);
  },

  returnBook(bookId: number) {
    return axios.put(`${API_URL}/returnRecord/${bookId}`);
  },

  getRecordByDate(date: string) {
    return axios.get<BookRecord[]>(`${API_URL}/getBookRecordByDate/${date}`);
  },

  sortRecordsByDate() {
    return axios.get<BookRecord[]>(`${API_URL}/sortBookRecordByDate`);
  }
};