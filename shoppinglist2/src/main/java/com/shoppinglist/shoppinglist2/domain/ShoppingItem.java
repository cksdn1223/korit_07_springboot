package com.shoppinglist.shoppinglist2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private String amount;

    private boolean purchased = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public ShoppingItem(String product, String amount, boolean purchased, User user) {
        this.product = product;
        this.amount = amount;
        this.purchased = purchased;
        this.user = user;
    }
}
