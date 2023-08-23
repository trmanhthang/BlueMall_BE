package com.example.tmdtserver.model.product;

import com.example.tmdtserver.model.Category;
import com.example.tmdtserver.model.shop.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    @ElementCollection
    private List<String> imagePath;

    @ElementCollection
    private List<Long> evaluate;  // đánh giá

    @ManyToOne
    private Category category;

    @ManyToOne
    private Shop shop;

    private Boolean status = true;
    private Long views = 0L;
    private Date date ;
    private Long rating = 0L;
    private Long numberPeople = 0L;
    private Double totalQuantity = 0D;

}