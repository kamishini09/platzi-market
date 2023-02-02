package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    /*
    para que Spring sepa que debe utilizar agregamos la anotacion Autowired de esta forma
    Spring sabra que debe hacer, internamente inicializara un nuevo ProductoRepository
    que es la clase que en realidad esta implementanda.
    Podemos utilizar el Autowired porque aun que ProductRepository no es un componenete
    de Spring ProductoRepositoy que es su implementacion si lo es.
      */
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    };

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    };

    public Product save(Product product){
        return productRepository.save(product);
    };
    public Boolean delete(int productId){
        return getProduct(productId).map(product ->{
            productRepository.delete(productId);
            return true;
        }).orElse(false);
        /*Otra forma de hacerlo */
        /*
        isPresent es de lo Optional
        if(getProduct(productId).isPresent()){
            productRepository.delete(productId);
        }else{
            return false;
        } */

    };
}
