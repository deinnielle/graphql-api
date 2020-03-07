package com.movie.api.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.movie.api.model.Director;
import com.movie.api.model.Movie;
import com.movie.api.repository.DirectorRepository;
import com.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;

    @Autowired
    public Query(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    public Iterable<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Iterable<Director> findAllDirectors() {
        return directorRepository.findAll();
    }

    public long countMovies() {
        return movieRepository.count();
    }

    public long countDirectors() {
        return directorRepository.count();
    }
}
