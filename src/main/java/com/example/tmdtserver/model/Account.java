package com.example.tmdtserver.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Role role;
    @OneToOne
    private Users users;

    private boolean status=true;

    public Account(Role role, Users users, boolean status) {
        this.role = role;
        this.users = users;
        this.status = status;
    }
}