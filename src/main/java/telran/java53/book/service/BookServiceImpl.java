package telran.java53.book.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java53.book.dao.AuthorRepository;
import telran.java53.book.dao.BookRepository;
import telran.java53.book.dao.PublisherRepository;
import telran.java53.book.dto.AuthorDto;
import telran.java53.book.dto.BookDto;
import telran.java53.book.dto.exception.EntityNotFoundException;
import telran.java53.book.model.Author;
import telran.java53.book.model.Book;
import telran.java53.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addBook(BookDto bookDto) {
		if(bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		//Publisher
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElseGet(() -> publisherRepository.save(new Publisher(bookDto.getPublisher())));
		
		
		//Authors
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElseGet(()-> authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto remove(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		bookRepository.deleteById(isbn);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto updateBookTitle(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		book.setTitle(title);
		return modelMapper.map(book, BookDto.class);
	}
	@Transactional(readOnly = true)
	@Override
	public Iterable<BookDto> findBooksByAuthor(String authorName) {
		return bookRepository.findByAuthorsNameIgnoreCase(authorName)
				.map(b-> modelMapper.map(b, BookDto.class))
				.toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<BookDto> findBooksByPublisher(String publisherName) {
		return bookRepository.findByPublisherPublisherNameIgnoreCase(publisherName)
				.map(b-> modelMapper.map(b, BookDto.class))
				.toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<AuthorDto> findBooksByAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return book.getAuthors().stream()
				.map(a -> modelMapper.map(a, AuthorDto.class))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	@Override
	public Iterable<String> findPublishersByAuthor(String authorName) {
		return publisherRepository.findPublisherByAuthor(authorName);
	}

	@Transactional
	@Override
	public AuthorDto removeAuthor(String authorName) {
	    Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFoundException::new);
	    List<Book> booksDelete = bookRepository.findByAuthorsNameIgnoreCase(authorName)
	    		.peek(b -> b.getAuthors().remove(author))
	    		.collect(Collectors.toList());

	    bookRepository.deleteAll(booksDelete);
	    authorRepository.delete(author);
	    
		return modelMapper.map(author, AuthorDto.class);
	}

}
