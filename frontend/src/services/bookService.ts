import axios from 'axios';
import type { Book } from '../types';

const API_BASE_URL = 'http://localhost:8085/api';

export const getBooks = async (): Promise<Book[]> => {
  const response = await axios.get(`${API_BASE_URL}/books`);
  return response.data;
};