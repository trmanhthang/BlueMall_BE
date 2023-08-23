package com.example.tmdtserver.service.product_service.my_interface;

import com.example.tmdtserver.model.product.EvaluateDetail;
import com.example.tmdtserver.model.product.Product;
import com.example.tmdtserver.model.Search;
import com.example.tmdtserver.service.shop_service.core.ICrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends ICrudService<Product> {
    Page<Product> showProductOfShop(Long id, Pageable pageable);
    Page<Product> showProductOfShopAll(Long id, Pageable pageable);

    Page<Product> showProductBySearch(Pageable pageable,Search search);
    Page<Product> showProductShopBySearch(Long id,Pageable pageable,Search search);
    Page<Product> findProductShopByName(Long id,Search search,Pageable pageable);
    Page<Product> showProductBySearchName(Pageable pageable,String name);


    List<EvaluateDetail> showRating(Long id);

    EvaluateDetail saveRating(EvaluateDetail evaluateDetail);

    void rating (Long id,EvaluateDetail evaluateDetail);
    Double totalQuantity(Long id);

}
