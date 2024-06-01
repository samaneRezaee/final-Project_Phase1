package org.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Serve extends BaseEntity<Long> {

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "serve", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SubServe> subServes = new ArrayList<>();
}
