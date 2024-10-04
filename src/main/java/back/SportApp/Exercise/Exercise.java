package back.SportApp.DataBase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue
    @Getter
    private Long exerciseId;

    @Column(name="name")
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column(name="set")
    private Integer set;

    @Getter
    @Setter
    @Column(name="rep")
    private Integer rep;

    @Getter
    @Column(name = "rest_time_in_minutes")
    private Integer restTimeInMinutes;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="trainingId", nullable = false)
    private Training training;



}
