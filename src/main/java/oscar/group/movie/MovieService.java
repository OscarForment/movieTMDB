package oscar.group.movie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.cache.annotation.Cacheable;

@Service
public class MovieService {

    private final WebClient webClient;


    public MovieService(@Value("${tmdb.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                                  .baseUrl("https://api.themoviedb.org/3")
                                  .defaultHeader("Authorization", "Bearer " + apiKey)
                                  .build();
    }

    @Cacheable("movies")
    public Mono<MovieResponse> searchMovies(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/search/movie")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(MovieResponse.class);
    }
}


