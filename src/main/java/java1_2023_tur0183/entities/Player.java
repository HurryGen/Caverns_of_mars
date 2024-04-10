package java1_2023_tur0183.entities;
import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "player")
    private List<Score> scores;


    public Player() {
    }
}
