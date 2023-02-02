package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    /*
    de esta forma se crea un query nativo, el nombre de la funcion puede ser cualquiera
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> consultaPorIdCategoria(int id);
    */

     //Obtener las categorias en orden asc
     List<Producto> findByidCategoriaOrderByNombreAsc(int idCategoria);

     //Obtener los productos escasos y que yo estoy vendiendo (estado activo)... el operador (LessThan = menor que)
     Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
