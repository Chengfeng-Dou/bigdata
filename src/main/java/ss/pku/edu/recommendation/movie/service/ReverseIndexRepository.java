package ss.pku.edu.recommendation.movie.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ss.pku.edu.recommendation.movie.domain.Index;
import ss.pku.edu.recommendation.movie.domain.IndexPk;

import java.util.List;

public interface ReverseIndexRepository extends JpaRepository<Index, IndexPk> {

    @Query(value = "select distinct * from reverse_index where key_word in :keywords", nativeQuery = true)
    List<Index> getMovieName(@Param("keywords") List<String> keyWords);
}
