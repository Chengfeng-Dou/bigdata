package ss.pku.edu.recommendation.movie.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IndexPk implements Serializable {
    public String keyWord;
    public String movieName;
}
