package guru.springframework.netfluxexample.controllers;

import guru.springframework.netfluxexample.domain.Movie;
import guru.springframework.netfluxexample.domain.MovieEvent;
import guru.springframework.netfluxexample.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping("/movies")
public class MovieController {
	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping(value = "/{id}/events", produces = TEXT_EVENT_STREAM_VALUE)
	Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
		return movieService.events(id);
	}

	@GetMapping("/{id}")
	Mono<Movie> getMovieById(@PathVariable String id) {
		return movieService.getMovieById(id);
	}

	@GetMapping
	Flux<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}
}
