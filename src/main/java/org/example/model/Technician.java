package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.enums.Role;
import org.example.model.enums.TechnicianStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@OnDelete(action = OnDeleteAction.CASCADE)
public class Technician extends Person {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TechnicianStatus status;

    @Column(nullable = false)
    private String skills;

    @Column(nullable = false)
    @Lob
    private byte[] picture;

    @OneToMany(mappedBy = "technician", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> commentList;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subserve_id")
    private SubServe subServe;

    @OneToMany(mappedBy = "technician", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Offer> offers=new ArrayList<>();

    public Technician(String firstname, String lastname, String email, String username
            , String password, LocalDate signUpTime, Role role) {
        super(firstname, lastname, email, username, password, signUpTime, role);
    }
}