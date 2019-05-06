package ss.pku.edu.recommendation.movie.service;

import org.springframework.stereotype.Service;
import ss.pku.edu.recommendation.movie.controller.SearchService;
import ss.pku.edu.recommendation.movie.domain.Index;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    private final ReverseIndexRepository repository;

    public SearchServiceImpl(ReverseIndexRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> searchMovieByDescription(String description) {
        List<String> keyWords = extractKeyWord(description);

        HashMap<String, Integer> movieCounter = new HashMap<>();
        keyWords.forEach(keyWord -> {
            Set<String> movies = getMovieNameByKeyWords(keyWord);
            movies.forEach(name -> {
                if (movieCounter.containsKey(name)) {
                    movieCounter.put(name, 1);
                } else {
                    movieCounter.put(name, movieCounter.get(name) + 1);
                }
            });
        });

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(movieCounter.entrySet());
        sortedList.sort((Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<String> ret = new LinkedList<>();
        sortedList.forEach(entry -> ret.add(entry.getKey()));
        return ret.subList(0, 10);
    }

    private List<String> extractKeyWord(String description) {
        return new LinkedList<>(Arrays.asList(description.split(" ")));
    }

    private Set<String> getMovieNameByKeyWords(String keyWords) {
        Set<String> movieSet = new HashSet<>();
        List<Index> indices = repository.findAllByKeyWord(keyWords);

        indices.forEach(index -> movieSet.add(index.movieName));

        return movieSet;
    }
}
