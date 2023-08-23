package com.example.tmdtserver.model.product;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class ProductConvertId implements Serializable {
    private Long accountId;
    private Long productId;
}