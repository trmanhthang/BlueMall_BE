package com.example.tmdtserver.service.shop_service.my_interface;

import com.example.tmdtserver.model.Account;
import com.example.tmdtserver.model.AccountConvert;
import com.example.tmdtserver.model.shop.Shop;
import com.example.tmdtserver.service.shop_service.core.ICrudService;

import java.util.List;
import java.util.Map;

public interface IShopService extends ICrudService<Shop> {
    Shop findByIdAccount(Long id);
    Shop findShopByIdProduct(Long id);

   Map<Account, Double> findTotalOfAccountBuyShop(Long idShop);
   List<AccountConvert> convertMapToList(Map<Account,Double> accounts );

}
