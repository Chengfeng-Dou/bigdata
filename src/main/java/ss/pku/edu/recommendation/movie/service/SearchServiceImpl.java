package ss.pku.edu.recommendation.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ss.pku.edu.recommendation.movie.controller.SearchService;
import ss.pku.edu.recommendation.movie.domain.Index;
import ss.pku.edu.recommendation.movie.domain.Thesaurus;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    private final ReverseIndexRepository reverseIndexRepository;

    private final ThesaurusRepository thesaurusRepository;

    private final static int FILTER = 30;

    @Autowired
    public SearchServiceImpl(ReverseIndexRepository repository, ThesaurusRepository thesaurusRepository) {
        this.reverseIndexRepository = repository;
        this.thesaurusRepository = thesaurusRepository;
    }

    @Override
    public List<String> searchMovieByDescription(String description) {
        List<String> originKeyWord = originKeyWord(description);
        Map<String, Integer> counter = new HashMap<>();
        originKeyWord.forEach(keyWord -> {
            List<String> thesaurus = thesaurus(keyWord);
            List<String> movies = getMovieNameByKeyWords(thesaurus);
            int weight = 30 - thesaurus.size();
            movies.forEach(movie -> {
                if (counter.containsKey(movie)) {
                    counter.put(movie, counter.get(movie) + weight);
                } else {
                    counter.put(movie, weight);
                }
            });
        });

        List<Map.Entry<String, Integer>> sortedMap = new ArrayList<>(counter.entrySet());
        sortedMap.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        List<String> ret = new LinkedList<>();

        sortedMap.forEach(entry -> ret.add(entry.getKey()));

        return ret.subList(0, FILTER);
    }

    private List<String> originKeyWord(String description) {
        return Arrays.asList(description.split(" "));
    }

    private List<String> thesaurus(String keyword) {
        List<Thesaurus> thesauruses = thesaurusRepository.findAllBySynonym(keyword);

        List<String> ret = new ArrayList<>(thesauruses.size() + 1);
        if (thesauruses.size() < 30) {
            thesauruses.forEach(thesaurus -> ret.add(thesaurus.keyword));
        }

        ret.add(keyword);
        return ret;
    }

    private List<String> getMovieNameByKeyWords(List<String> keyWords) {
        List<String> movies = new LinkedList<>();
        System.out.println(keyWords);

        List<Index> indices = reverseIndexRepository.getMovieName(keyWords);
        indices.forEach(index -> movies.add(index.movieName));
        return movies;
    }
}
