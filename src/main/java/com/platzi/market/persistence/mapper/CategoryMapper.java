package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Category;
import com.platzi.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    /*debe ser llamado asi to*** Nombre a convertir
    * es decir recibimos una Categoria y devolvemos una Category
    * en source agregamos el origiden de Categori y en target como se traduce a Category
    * */

    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);

    /*Esta anotacion InheritInverseConfiguration le indica a mapper struct que la siguiente transformacion
    * es la inversa de la anterior.
    * Como tenemos el campo productos en categoria y no tiene equivalente en Category colocamos el target
    * del campo y declaramos  ignore = true
    * */

    @InheritInverseConfiguration
    @Mapping( target = "productos", ignore = true)
    Categoria toCategoria(Category category);


    /*
    * Mapping = cuando es solo un elemento a mapear
    * Mappings = cuando son varios elementos a mapear
    * */
}
