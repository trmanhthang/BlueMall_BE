package com.example.tmdtserver.model.bill;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BillDetailId implements Serializable {
    private Long billId;
    private Long productId;
}
