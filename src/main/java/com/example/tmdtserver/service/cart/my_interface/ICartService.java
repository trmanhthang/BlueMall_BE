package com.example.tmdtserver.service.cart.my_interface;

import com.example.tmdtserver.model.product.Product;
import com.example.tmdtserver.model.product.ProductConvert;
import com.example.tmdtserver.model.cart.Cart;
import com.example.tmdtserver.service.shop_service.core.ICrudService;

import java.util.List;
import java.util.Map;

public interface ICartService extends ICrudService<Cart> {
    Cart findByIdAccount(Long id);
    //Kiểm tra xem sản phẩm có trong giỏ hàng hay chưa
    boolean checkItemInCart(Long id,Product product);
    // Phương thức trả ra sản phẩm có trong giỏ hàng
    Map.Entry<Product,Integer> selectItemInCart(Long id, Product product);
    // Thêm sản phẩm vào giỏ hàng
    void addProduct(Long id, Product product);

    // Bớt sản phẩm khỏi giỏ hàng
    void subProduct(Long id, Product product);
    // Tổng số lượng sản phẩm có trong giỏ hàng
    Integer countProductQuantity(Long id);

    // Đếm số sản phẩm có trong giỏ hàng
    Integer countItemQuantity(Long id);

    //Tính tổng số tiền cần phải thanh toán
    Double countTotalPayment(Long id);

    List<ProductConvert> convertMapToList(Map<Product,Integer> products);

//    Kiểm tra sản phẩm có trong giỏ hàng hay không

    Integer checkProductInCart(Long id, Product product);

//    Xóa 1 sản phẩm trong giỏ hàng
    void deleteProductInCart(Cart cart, Long idProduct);

}

