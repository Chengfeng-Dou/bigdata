package ss.pku.edu.recommendation.movie.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ss.pku.edu.recommendation.movie.domain.Index;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReverseIndexTest {
    private String[] files = {"part-00001"};
    private static final String path = "/home/douchengfeng/out/1556895189846";

    @Autowired
    private ReverseIndexRepository repository;

    @Test
    public void testInsert() throws IOException {
        for (String file : files) {
            String fileName = path + "/" + file;
            readFileAndInsert(fileName);
        }
    }

    private void readFileAndInsert(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int lineNumber = 1;
        Set<Index> indexSet = new HashSet<>();
        while ((line = reader.readLine()) != null) {
            String[] tmp = line.split("\t");
            String keyWord = tmp[0];
            String[] movieNames = tmp[1].split("&spark&");


            for (String movieName : movieNames) {
                Index index = new Index();
                index.keyWord = keyWord;
                index.movieName = movieName;
                indexSet.add(index);
            }


            System.out.println("line " + lineNumber);
            lineNumber ++;

            if(lineNumber % 100 == 0){
                try {
                    repository.saveAll(indexSet);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                indexSet.clear();
            }
        }


    }



}
