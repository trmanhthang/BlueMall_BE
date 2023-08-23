package com.example.tmdtserver.repository;

import com.example.tmdtserver.model.Voucher;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IVoucherRepository extends JpaRepository<Voucher,Long> {
    @Query(value = "select vo from  Voucher  vo join Shop s on s.id = vo.shop.id where s.id = :id")
    Page<Voucher> showAllVoucher(Pageable pageable, @Param("id")Long id);
    @Query(value = "select vo from  Voucher  vo join Shop s on s.id = vo.shop.id where s.id = :id")
    List<Voucher> showAllListVoucher(@Param("id")Long id);
}
