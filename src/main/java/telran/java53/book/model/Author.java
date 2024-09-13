package telran.java53.book.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@EqualsAndHashCode(of = "name")
@Entity
@Table (name = "author")
public class Author implements Serializable {
	private static final long serialVersionUID = 5375585597132626470L;

	@Id
	@Column(name = "name")
	String name;
	@Column(name = "birth_date")
	LocalDate birthDate;
	@ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE)
	Set<Book> books;
	
	public Author(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}
}
