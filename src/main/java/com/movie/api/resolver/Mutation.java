package com.movie.api.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.movie.api.model.Director;
import com.movie.api.model.Movie;
import com.movie.api.repository.DirectorRepository;
import com.movie.api.repository.MovieRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;

    @Autowired
    public Mutation(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    public Movie createMovie(String title, Float rating, String genre, Long directorId) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setRating(rating);
        movie.setGenre(genre);
        movie.setDirector(new Director(directorId));

        movieRepository.save(movie);

        return movie;
    }

    public Director createDirector(String name, Integer age) {
        Director director = new Director();
        director.setName(name);
        director.setAge(age);

        directorRepository.save(director);

        return director;
    }

    public Movie updateMovie(Long id, String title, Float rating, String genre) throws NotFoundException {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();

            if (title != null) {
                movie.setTitle(title);
            }

            if (rating != null) {
                movie.setRating(rating);
            }

            if (genre != null) {
                movie.setGenre(genre);
            }

            movieRepository.save(movie);

            return movie;
        }

        throw new NotFoundException("No Movie to update");
    }

    public boolean deleteMovie(Long id) {
        movieRepository.deleteById(id);
        return true;
    }
}
