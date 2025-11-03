import axios from 'axios';
import type { Book } from '../types';

const API_URL = 'http://localhost:8080/Book';

export const bookApi = {
  getAllBooks() {
    return axios.get<Book[]>(`${API_URL}/getAllBook`);
  },

  getBookById(id: number) {
    return axios.get<Book>(`${API_URL}/getBookById/${id}`);
  },

  addBook(book: Partial<Book>) {
    return axios.post(`${API_URL}/addNewBook`, book);
  },

  updateBook(price: number, id: number) {
    return axios.put(`${API_URL}/updateBook`, null, {
      params: { price, id }
    });
  },

  deleteBook(id: number) {
    return axios.delete(`${API_URL}/deleteBook/${id}`);
  }
};