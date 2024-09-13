package telran.java53.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@Entity
@Table(name = "book")
public class Book implements Serializable {
	private static final long serialVersionUID = 1854903549995255372L;
	
	@Id
	@Column(name = "isbn")
	String isbn;
	@Column(name = "title")
	String title;
	@ManyToMany
	@JoinTable(
			name = "book_authors",
			joinColumns = @JoinColumn(name = "book_isbn"),
			inverseJoinColumns = @JoinColumn(name = "authors_name")
	)
	Set<Author> authors;
	@ManyToOne
	@JoinColumn(name = "publisher_publisher_name")
	Publisher publisher;
}
