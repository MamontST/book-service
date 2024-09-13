package telran.java53.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@EqualsAndHashCode(of = "publisherName")
@Entity
@Table (name = "publisher")
public class Publisher implements Serializable {
	private static final long serialVersionUID = -4634176458163835044L;
	
	@Id
	@Column(name = "publisher_name")
	String publisherName;
	@OneToMany(mappedBy = "publisher")
	Set<Book> books;
	
	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return publisherName;
	}


}
