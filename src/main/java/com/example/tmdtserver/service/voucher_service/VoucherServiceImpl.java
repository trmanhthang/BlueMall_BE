package com.example.tmdtserver.service.voucher_service;

import com.example.tmdtserver.model.Voucher;
import com.example.tmdtserver.repository.IVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements IVoucherService {
    @Autowired
    private IVoucherRepository voucherRepository;

    @Override
    public Page<Voucher> findALl(Pageable pageable) {
        return voucherRepository.findAll(pageable);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher findById(Long id) {
        return voucherRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Page<Voucher> showAllVoucher(Pageable pageable, Long id) {
        return voucherRepository.showAllVoucher(pageable, id);
    }

    @Override
    public Double percentVoucher(Long id, String voucher) {
        List<Voucher> voucherList = voucherRepository.showAllListVoucher(id);
        boolean check = false;
        Double value = 0D;
        if (!voucherList.isEmpty()) {
            for (int i = 0; i < voucherList.size(); i++) {
                if (voucherList.get(i).getName().equals(voucher)) {
                    if (voucherList.get(i).getQuantity() != 0) {
                        check = true;
                        value = voucherList.get(i).getPercent();
                    }
                }
            }
            if (check) {
                return value;
            }
        }
        return 0D;
    }

    @Override
    public Voucher updateVoucher(Long id, String voucher) {
        List<Voucher> voucherList = voucherRepository.showAllListVoucher(id);
        Voucher voucherUpdate;
        if (!voucherList.isEmpty()) {
            for (int i = 0; i < voucherList.size(); i++) {
                if (voucherList.get(i).getName().equals(voucher)) {
                    voucherUpdate = voucherList.get(i);
                    if (voucherList.get(i).getQuantity() != 0) {
                        voucherUpdate.setQuantity(voucherUpdate.getQuantity() - 1);
                        voucherRepository.save(voucherUpdate);
                        return voucherUpdate;
                    } else {
                        return voucherUpdate;
                    }
                }
            }
        }
        return null;
    }
}
