package com.uba.check_book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;
    private String password;
    private  int totalLoginAttempt;
    private boolean locked;
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "createdByUserId")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="deleteByUserId")
    private User deletedBy;

    @ManyToOne
    @JoinColumn(name="validationAskedByUserId")
    private User validatedAskBy;

    @ManyToOne
    @JoinColumn(name="validatedByUserId")
    private User validatedBy;

    @ManyToOne
    @JoinColumn(name="branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "createdBy")
    private List<User> createdUsers;

    @OneToMany(mappedBy = "deletedBy")
    private List<User> deletedUsers;

    @OneToMany(mappedBy = "validatedAskBy")
    private List<User> validatedAskUsers;

    @OneToMany(mappedBy = "validatedBy")
    private List<User> validatedUsers;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Transaction>transactions;

    public void incrementLoginAttempt() {
        this.totalLoginAttempt += 1;
        if (this.totalLoginAttempt >= 3) {
            this.locked = true;
        }
    }
    public void resetLoginAttempt() {
        this.totalLoginAttempt = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id != 0 && id == user.id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
