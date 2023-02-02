package com.platzi.market.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

	private  String nombre;

    private  String apellidos;

    private  String celular;

    private  String direccion;

    @Column(name = "correo_electronico")
    private  String correoElectronico;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
}
