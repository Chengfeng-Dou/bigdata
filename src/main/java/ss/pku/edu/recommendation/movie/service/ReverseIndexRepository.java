package ss.pku.edu.recommendation.movie.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.pku.edu.recommendation.movie.domain.Index;
import ss.pku.edu.recommendation.movie.domain.IndexPk;

import java.util.List;

public interface ReverseIndexRepository extends JpaRepository<Index, IndexPk> {

    List<Index> findAllByKeyWord(String keyWord);
}
