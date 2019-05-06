package ss.pku.edu.recommendation.movie.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "thesaurus")
@IdClass(ThesaurusPK.class)
public class Thesaurus {
    @Id
    public String synonym;
    @Id
    public String keyword;


}
