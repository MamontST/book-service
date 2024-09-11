package telran.java53.book.service;

import telran.java53.book.dto.AuthorDto;
import telran.java53.book.dto.BookDto;

public interface BookService {
	Boolean addBook(BookDto bookDto);

	BookDto findBookByIsbn(String isbn);

	BookDto remove(String isbn);

	BookDto updateBookTitle(String isbn, String title);

	Iterable<BookDto> findBooksByAuthor(String authorName);

	Iterable<BookDto> findBooksByPublisher(String publisherName);

	Iterable<AuthorDto> findBooksByAuthors(String isbn);

	Iterable<String> findPublishersByAuthor(String authorName);

	AuthorDto removeAuthor(String authorName);
}
