package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SecondaryTableTest extends EntityManagerTest {

    @Test
    public void salvarItem() {
        Cliente cliente = new Cliente();

        cliente.setNome("Carlos Finotti");
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(1900, 1, 1));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao.getSexo());
    }

}
