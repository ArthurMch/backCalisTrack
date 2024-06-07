package back.SportApp.DataBase;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue
    @Column(name ="training_id")
    @Getter
    private Long trainingId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="date")
    @Getter
    @Setter
    private Date date;

    @Column(name="number_of_exercise")
    @Getter
    @Setter
    private Integer numberOfExercise;

    @Column(name = "total_minutes_of_rest")
    @Getter
    @Setter
    private Integer totalMinutesOfRest;

    @Column(name = "total_minutes_of_training")
    @Getter
    @Setter
    private Integer totalMinutesOfTraining;

    @OneToMany(mappedBy = "training")
    private Set<Exercise> exercise;

    @ManyToOne
    @JoinColumn(name="accountId", nullable = false)
    @Getter
    private Account account;

}
