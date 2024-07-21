package ozdemirozdemir.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;
    private Boolean enabled;

    @Column(nullable = true)
    private Long verification;

    public ApplicationUser() {
        this.authorities = new HashSet<>();
        this.enabled = false;
    }

}
