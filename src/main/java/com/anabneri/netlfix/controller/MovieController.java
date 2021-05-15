package com.anabneri.netlfix.controller;

import com.anabneri.netlfix.model.Movie;
import com.anabneri.netlfix.model.NetflixEvent;
import com.anabneri.netlfix.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/netflix")
public class MovieController {

    private MovieRepository movieRepository;
    public MovieController(MovieRepository movieRepository) {this.movieRepository = movieRepository;}

    @GetMapping
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Movie>> getPokemon(@PathVariable String id) {
        return movieRepository.findById(id)
            .map(movie -> ResponseEntity.ok(movie))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Movie> savePokemon(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Movie>> updatePokemon(@PathVariable(value="id")
        String id,
        @RequestBody Movie movie) {
        return movieRepository.findById(id)
            .flatMap(existingMovie -> {
                existingMovie.setMovieName(movie.getMovieName());
                existingMovie.setMovieType(movie.getMovieType());
                existingMovie.setPrincipalActor(movie.getPrincipalActor());
                existingMovie.setCreated_at(movie.getCreated_at());
                return movieRepository.save(existingMovie);
            })
            .map(updateMovie -> ResponseEntity.ok(updateMovie))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteMovieNetflix(@PathVariable(value = "id") String id) {
        return movieRepository.findById(id)
            .flatMap(existingMovie ->
                    movieRepository.delete(existingMovie)
                        .then(Mono.just(ResponseEntity.ok().<Void>build()))
                    )
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllMoviesFromDatabase() {
        return movieRepository.deleteAll();
    }

    @GetMapping(value = "/netflix-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<NetflixEvent> getNetflixEvents() {
        return Flux.interval(Duration.ofSeconds(5))
            .map(val ->
                    new NetflixEvent(val, "Netlifx Event")
                );
    }
}
