package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer pedidoId;

    private StatusPagamento status;

    private String codigoBarras;

}
