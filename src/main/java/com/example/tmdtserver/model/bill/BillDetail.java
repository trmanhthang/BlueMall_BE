package com.example.tmdtserver.model.bill;

import com.example.tmdtserver.model.product.Product;
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
public class BillDetail {
    @EmbeddedId
    private BillDetailId billDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "billId")
    private Bill bill ;

    private Integer quantity;
    private Double total;
}