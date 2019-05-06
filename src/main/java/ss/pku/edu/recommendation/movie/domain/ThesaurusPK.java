package ss.pku.edu.recommendation.movie.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ThesaurusPK implements Serializable {
    public String synonym;
    public String keyword;
}
