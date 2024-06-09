package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.model.enums.Role;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "person_id")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Customer extends Person {

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Request> requestList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> commentList = new ArrayList<>();

    public Customer(String firstname, String lastname, String email, String username, String password, LocalDate signUpTime, Role role) {
        super(firstname, lastname, email, username, password, signUpTime, role);
    }
}
