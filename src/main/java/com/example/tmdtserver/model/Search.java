package com.example.tmdtserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    private String name;
    private Long idCategory;
    private Double priceMin;
    private Double priceMax;
    private List<String> arrayCity;

}
