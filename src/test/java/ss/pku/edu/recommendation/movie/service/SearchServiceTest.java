package ss.pku.edu.recommendation.movie.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ss.pku.edu.recommendation.movie.controller.SearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private SearchService searchService;

    @Test
    public void test(){
        String word = "林正英 僵尸";
        System.out.println(searchService.searchMovieByDescription(word).toString());
    }
}
