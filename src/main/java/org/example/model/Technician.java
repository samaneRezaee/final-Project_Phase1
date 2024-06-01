package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.model.enums.TechnicianStatus;
import org.hibernate.engine.jdbc.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Technician extends Person {
    @Column(nullable = false)
    private TechnicianStatus status;

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
}