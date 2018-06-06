package guru.springframework.netfluxexample.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class MovieEvent {
	private String movieId;
	private Date date;
}
