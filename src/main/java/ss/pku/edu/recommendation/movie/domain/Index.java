package ss.pku.edu.recommendation.movie.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reverse_index")
@IdClass(IndexPk.class)
public class Index {
    @Id
    public String keyWord;
    @Id
    public String movieName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return Objects.equals(keyWord, index.keyWord) &&
                Objects.equals(movieName, index.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyWord, movieName);
    }
}

