package com.anabneri.netlfix.repository;

import com.anabneri.netlfix.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository <Movie, String> {

}
