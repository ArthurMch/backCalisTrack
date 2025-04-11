package back.SportApp.User;

import back.SportApp.Spring.model.CalistrackEntity;
import back.SportApp.Spring.model.TrackingColumnListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(TrackingColumnListener.class)
@Table(name = "user_password")
public class UserPassword extends CalistrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_id")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password_value")
    private String password;

    @Column(name = "password_expiration")
    private Date expiration;

    @Override
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(final Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj == this || obj instanceof final UserPassword userPassword && userPassword.getId() == getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "id=" + id + " / userId=" + userId;
    }
}