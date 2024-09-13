package telran.java53.book.dao;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.java53.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {
	@Query("select distinct p.publisherName from Book b join b.publisher p join b.authors a where a.name = ?1")
	List<String> findPublisherByAuthor(String authorName);
//	Set<String> findPublisherByAuthor(String authorName);
	
	Stream<Publisher> findDistinctByBooksAuthorsName(String authorName);
}
