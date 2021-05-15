package com.anabneri.netlfix;

import com.anabneri.netlfix.model.Movie;
import com.anabneri.netlfix.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class NetlfixApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetlfixApplication.class, args);
	}

	CommandLineRunner init (ReactiveMongoOperations operations, MovieRepository repository) {

		return args -> {
			Flux<Movie> netflixFlux  = Flux.just(
				new Movie(null, "Matrix", "Acao", "Keanu Reeves", 1995),
				new Movie(null, "Diario de uma paixao", "Romance", "Ryan Gosling", 2003),
				new Movie(null, "Uma noite de crime", "Suspense", null, 1995),
				new Movie(null, "Jogos Mortais", "Terror", null, 1995),
				new Movie(null, "1907", "Drama", "George MacKay", 2020))

				.flatMap(repository::save);

			netflixFlux
				.thenMany(repository.findAll())
				.subscribe(System.out::println);

		};
	}
}
