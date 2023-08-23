package com.example.tmdtserver.model.product;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EvaluateId implements Serializable {
    private Long productId;
    private Long accountId;
}
