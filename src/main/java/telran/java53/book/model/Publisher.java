package telran.java53.book.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Publisher implements Serializable {
	private static final long serialVersionUID = -4634176458163835044L;
	
	@Id
	String publisherName;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return publisherName;
	}
}
