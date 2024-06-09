package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.entity.BaseEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address extends BaseEntity<Long> {
    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String avenue;

    @Column(nullable = false)
    private String allay;

    @Column(nullable = false)
    private String doorplate;

    @OneToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;


}
