package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ICategoryRepository extends JpaRepository<Category,Long> {
//    Truy xuất thể loại sản phẩm trong 1 shop
    @Query(value = "select c from Category c join Product p on c.id = p.category.id join " +
            "Shop s on s.id = p.shop.id where s.id = :id group by c.id")
    List<Category> findCategoryOfShop(@Param("id")Long id);
}

