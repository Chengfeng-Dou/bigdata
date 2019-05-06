package ss.pku.edu.recommendation.movie.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ss.pku.edu.recommendation.movie.domain.Thesaurus;
import ss.pku.edu.recommendation.movie.domain.ThesaurusPK;

import java.util.List;

public interface ThesaurusRepository extends JpaRepository<Thesaurus, ThesaurusPK> {

    List<Thesaurus> findAllBySynonym(String synonym);
}
