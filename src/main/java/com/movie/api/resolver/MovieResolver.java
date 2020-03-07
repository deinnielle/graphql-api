package com.movie.api.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.movie.api.model.Director;
import com.movie.api.model.Movie;
import com.movie.api.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieResolver implements GraphQLResolver<Movie> {
    @Autowired
    private DirectorRepository directorRepository;

    public MovieResolver(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Director getDirector(Movie movie) {
        return directorRepository.findById(movie.getDirector().getId()).orElseThrow(null);
    }
}
