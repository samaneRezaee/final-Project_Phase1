package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.entity.BaseEntity;
import org.example.model.enums.RequestStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Request extends BaseEntity<Long> {
    @Column
    private double proposalCustomerPrice;

    @Column(nullable = false)
    private String descriptionOfRequest;

    @Column(nullable = false)
    private LocalDate dateToDoRequest;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "subserve_id")
    private SubServe subServe;

    @OneToMany(mappedBy = "request", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Offer> offerList = new ArrayList<>();

    public Request(double proposalCustomerPrice, String descriptionOfRequest, LocalDate dateToDoRequest,
                   RequestStatus requestStatus, Customer customer, SubServe subServe) {

        this.proposalCustomerPrice = proposalCustomerPrice;
        this.descriptionOfRequest = descriptionOfRequest;
        this.dateToDoRequest = dateToDoRequest;
        this.requestStatus = requestStatus;
        this.customer = customer;
        this.subServe = subServe;
    }
}