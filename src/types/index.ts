export interface Book {
  bookId: number;
  bookTitle: string;
  bookAuthor: string;
  bookPublisher?: string;
  bookGenre?: string;
  bookPublicationYear?: number;
  bookLanguage?: string;
  bookPrice: number;
  bookStockQuantity?: number;
  bookPages?: number;
  bookIsbn?: string;
  book_status?: string;
}

export interface Member {
  memberId: number;
  member_name: string;
  member_email?: string;
  member_phone: string;
  joinDate: string;
}

export interface BookRecord {
  bookRecord_id: number;
  member: Member;
  book: Book;
  borrow_date: string;
  return_date?: string;
  bookRecord_status: string;
}

export interface BookFormData {
  bookTitle: string;
  bookAuthor: string;
  bookPublisher: string;
  bookGenre: string;
  bookPublicationYear: string;
  bookLanguage: string;
  bookPrice: string;
  bookStockQuantity: string;
  bookPages: string;
  bookIsbn: string;
}

export interface MemberFormData {
  member_name: string;
  member_email: string;
  member_phone: string;
  joinDate: string;
}

export interface BorrowFormData {
  member_id: string;
  book_title: string;
}

export interface NotificationState {
  show: boolean;
  message: string;
  type: 'success' | 'error';
}