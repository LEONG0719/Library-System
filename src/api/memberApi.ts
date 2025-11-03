import axios from 'axios';
import type { Member } from '../types';

const API_URL = 'http://localhost:8080/Member';

export const memberApi = {
  getAllMembers() {
    return axios.get<Member[]>(`${API_URL}/getAllMember`);
  },

  getMemberById(id: number) {
    return axios.get<Member>(`${API_URL}/getMemberById/${id}`);
  },

  addMember(member: Partial<Member>) {
    return axios.post(`${API_URL}/addNewMember`, member);
  },

  updateMember(memberId: number, member: Partial<Member>) {
    return axios.put(`${API_URL}/updateMember/${memberId}`, member);
  },

  deleteMember(memberId: number) {
    return axios.delete(`${API_URL}/deleteMember/${memberId}`);
  }
};