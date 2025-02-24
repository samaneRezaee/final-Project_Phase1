package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubServe extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "subServe", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Technician> technicians = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "serve_id")
    private Serve serve;

    @OneToMany(mappedBy = "subServe", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Request> requests = new ArrayList<>();

    public SubServe(String title, double price, String description, Serve serve) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.serve = serve;
    }

    public SubServe(Long aLong, String title, double price, String description, Serve serve) {
        super(aLong);
        this.title = title;
        this.price = price;
        this.description = description;
        this.serve = serve;
    }
}
