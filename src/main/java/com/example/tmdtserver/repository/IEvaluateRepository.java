package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.product.EvaluateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IEvaluateRepository extends JpaRepository<EvaluateDetail,Long> {
    @Query(value = "select e from EvaluateDetail  e where e.product.id = :id")
    List<EvaluateDetail> showRating(@Param("id")Long id);

    @Modifying
    @Query(value = "update EvaluateDetail  e set e.rating = :number where e.product.id = :idProduct and e.account.id = :idAccount")
    void updateRating(@Param("number")Long number, @Param("idProduct")Long idProduct,
                                @Param("idAccount")Long idAccount);
}
