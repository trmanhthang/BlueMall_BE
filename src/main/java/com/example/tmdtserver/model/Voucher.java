package com.example.tmdtserver.model;


import com.example.tmdtserver.model.shop.Shop;
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
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double percent;
    private Long quantity;
    @ManyToOne
    private Shop shop;
    private boolean status=true;
}