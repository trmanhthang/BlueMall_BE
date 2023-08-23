package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.bill.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IBillRepository extends JpaRepository<Bill, Long> {
    @Query(value = "select b from Bill  b where b.statusBill.id = 1 and b.account.id = :id")
    Page<Bill> showAllBill(@Param("id")Long id, Pageable pageable);

//    Xét lại trạng thái đơn hàng khi sản phẩm bị xóa khỏi shop
    @Modifying
    @Query(value = "update Bill b set b.statusBill.id = 5 where b.id = :id and b.statusBill.id = 1")
    void updateBillById5(@Param("id")Long id);

//    Truy xuất hóa đơn theo id Product
    @Query(value = "select b from  Bill b join  BillDetail bd on b.id = bd.bill.id where bd.product.id = :id")
    Bill getBillByIdProduct(@Param("id")Long id);

}

