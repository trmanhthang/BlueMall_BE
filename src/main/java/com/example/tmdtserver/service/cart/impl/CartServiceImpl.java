package com.example.tmdtserver.service.cart.impl;

import com.example.tmdtserver.model.product.ProductConvert;
import com.example.tmdtserver.model.product.Product;
import com.example.tmdtserver.model.cart.Cart;
import com.example.tmdtserver.repository.ICartRepository;
import com.example.tmdtserver.repository.IProductRepository;
import com.example.tmdtserver.service.cart.my_interface.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Page<Cart> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
    @Override
    public void deleteProductInCart(Cart cart,Long idProduct) {
        cart.getProducts().entrySet().removeIf(entry -> entry.getKey().getId().equals(idProduct));
        save(cart);
    }

    @Override
    public Cart findByIdAccount(Long id) {
        return cartRepository.findByIdAccount(id);
    }
//    Kiểm tra số lượng của sản phẩm có trong giỏ hàng
    @Override
    public Integer checkProductInCart(Long id, Product product) {
        for (Map.Entry<Product, Integer> entry : findByIdAccount(id).getProducts().entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return entry.getValue();
            }
        }
        return 0;
    }




    //Kiểm tra sản phẩm có trong giỏ hàng hay chưa
    @Override
    public boolean checkItemInCart(Long id,Product product) {
        for (Map.Entry<Product, Integer> entry : findByIdAccount(id).getProducts().entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return false;
            }
        }
        return true;
    }
    // Phương thức trả ra sản phẩm có trong giỏ hàng
    @Override
    public Map.Entry<Product, Integer> selectItemInCart(Long id,Product product) {
        for (Map.Entry<Product, Integer> entry : findByIdAccount(id).getProducts().entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return entry;
            }
        }
        return null;
    }
    // Thêm sản phẩm vào giỏ hàng
    @Override
    public void addProduct(Long id, Product product) {
        // Kiểm tra product có tồn tại trong giỏ hàng. Nếu chưa tồn tại thì value = 1
        if (checkItemInCart(id, product)){
            findByIdAccount(id).getProducts().put(product,1);
            cartRepository.save(findByIdAccount(id));
        } else {
            // Nếu product đã  tồn tại trong giỏ hàng thì xét lại value cộng thêm với 1
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(id,product);
            assert itemEntry != null;
            Integer newQuantity = itemEntry.getValue() + 1;
            findByIdAccount(id).getProducts().replace(itemEntry.getKey(),newQuantity);
            cartRepository.save(findByIdAccount(id));
        }
    }
    // Bớt sản phẩm khỏi giỏ hàng
    @Override
    public void subProduct(Long id, Product product) {
        // Kiểm tra product có tồn tại trong giỏ hàng. Nếu chưa tồn tại thì value = 1
        if (checkItemInCart(id, product)){
            findByIdAccount(id).getProducts().put(product,1);
            cartRepository.save(findByIdAccount(id));
        } else {
            // Nếu product đã  tồn tại trong giỏ hàng thì xét lại value trừ với 1
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(id,product);
            assert itemEntry != null;
            Integer newQuantity = itemEntry.getValue() - 1;
            findByIdAccount(id).getProducts().replace(itemEntry.getKey(),newQuantity);
            cartRepository.save(findByIdAccount(id));
        }
    }
    // Tổng số lượng sản phẩm có trong giỏ hàng
    @Override
    public Integer countProductQuantity(Long id) {
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : findByIdAccount(id).getProducts().entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }
    // Đếm số sản phẩm có trong giỏ hàng
    @Override
    public Integer countItemQuantity(Long id) {
        return findByIdAccount(id).getProducts().size();
    }
    //Tính tổng số tiền cần phải thanh toán
    @Override
    public Double countTotalPayment(Long id) {
        double payment = 0;
        for (Map.Entry<Product, Integer> entry : findByIdAccount(id).getProducts().entrySet()) {
            // Số tiền phải trả = giá sản phẩm * số lượng sản phẩm
            payment += entry.getKey().getPrice() * entry.getValue();
        }
        return payment;
    }

    // Chuyển map thành list để duyệt bên JS
    @Override
    public List<ProductConvert> convertMapToList(Map<Product,Integer> products){
        List<ProductConvert> productConverts = new ArrayList<>();
        for (Map.Entry<Product,Integer> entry:products.entrySet()){
            ProductConvert product = new ProductConvert();
            product.setProduct(entry.getKey());
            product.setQuantity(entry.getValue());
            productConverts.add(product);
        }
        return productConverts;
    }

}
