package com.platzi.market.domain.repository;

import com.platzi.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    /*
     *Este va a ser implementado por el repository de persistence para asi hablar en terminos de dominio y no de la persistencia es decir
     * hablar en terminos de Prodcut y no de producto
     * */
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);

}
