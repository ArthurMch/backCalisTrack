package back.SportApp.DataBase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="account")
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    @Getter
    private Long accountId;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Setter
    @Getter
    private String password;

    @Getter
    @OneToMany(mappedBy = "account")
    private Set<Training> training;

}

// LISTENER ON PORT 5432 ou 5433

