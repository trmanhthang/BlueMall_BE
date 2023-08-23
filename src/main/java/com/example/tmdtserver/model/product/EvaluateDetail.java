package com.example.tmdtserver.model.product;

import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.bill.Bill;
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
public class EvaluateDetail {
    @EmbeddedId
    private EvaluateId evaluateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "accountId")
    private Account account ;
    private Long rating;
}
