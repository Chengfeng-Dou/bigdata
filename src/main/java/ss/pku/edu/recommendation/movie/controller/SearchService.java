package ss.pku.edu.recommendation.movie.controller;

import java.util.List;

public interface SearchService {

    List<String> searchMovieByDescription(String description);
}
