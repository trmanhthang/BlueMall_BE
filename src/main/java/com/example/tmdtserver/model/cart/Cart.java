package com.example.tmdtserver.model.cart;

import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Account account;

    public Cart(Account account) {
        this.account = account;
    }
    @ElementCollection
    private Map<Product,Integer> products;

}