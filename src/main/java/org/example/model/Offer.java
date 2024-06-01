package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.entity.BaseEntity;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Offer extends BaseEntity<Long> {
    @Column(nullable = false)
    private LocalDate localDateOffer;

    @Column(nullable = false)
    private double proposalPrice;

    @Column(nullable = false)
    private String estimatedTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "request_id")
    private Request request;




}
