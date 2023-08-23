package com.example.tmdtserver.service.shop_service.impl;
import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.AccountConvert;
import com.example.tmdtserver.model.bill.BillDetail;
import com.example.tmdtserver.model.shop.Shop;
import com.example.tmdtserver.repository.IAccountRepository;
import com.example.tmdtserver.repository.IShopRepository;
import com.example.tmdtserver.service.shop_service.my_interface.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopRepository shopRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public Page<Shop> findALl(Pageable pageable) {
        return shopRepository.findAll(pageable);
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public Shop findByIdAccount(Long id) {
        return shopRepository.findShopByAccount(id);
    }

    @Override
    public Shop findShopByIdProduct(Long id) {
        return shopRepository.findShopByProduct(id);
    }

    @Override
    public Map<Account, Double> findTotalOfAccountBuyShop(Long idShop) {
        Map<Account,Double> mapAccount = new HashMap<>();
        List<Account> accounts = accountRepository.findAccountBuyProductOfShop(idShop);
        List<BillDetail> billDetails = shopRepository.findTotalOfAccountBuyShop(idShop);
        double sum = 0;
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            for (int j = 0; j < billDetails.size(); j++) {
                BillDetail billDetail = billDetails.get(j);
                if (account.getId().equals(billDetail.getBill().getAccount().getId())){
                      sum += billDetail.getTotal();
                }
            }
            mapAccount.put(account,sum);
        }
        return mapAccount;
    }

    @Override
    public List<AccountConvert> convertMapToList(Map<Account, Double> accounts) {
        List<AccountConvert> accountConverts = new ArrayList<>();
        for (Map.Entry<Account,Double> entry: accounts.entrySet()){
            AccountConvert account = new AccountConvert();
            account.setAccount(entry.getKey());
            account.setTotal(entry.getValue());
            accountConverts.add(account);
        }
        return accountConverts;
    }

}
