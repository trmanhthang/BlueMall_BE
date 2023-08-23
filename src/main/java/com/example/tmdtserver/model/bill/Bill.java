package com.example.tmdtserver.model.bill;

import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.shop.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @ManyToOne
    private Shop shop;
    @ManyToOne
    private Account account;
    @ManyToOne
    private StatusBill statusBill;

}