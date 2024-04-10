package java1_2023_tur0183.entities;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Score {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player player;
    private int score;

    public Score() {
    }
}
