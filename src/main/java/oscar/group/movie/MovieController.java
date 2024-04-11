package oscar.group.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/movies")
    public Mono<MovieResponse> searchMovies(@RequestParam String query) {
        return movieService.searchMovies(query);
    }
}

