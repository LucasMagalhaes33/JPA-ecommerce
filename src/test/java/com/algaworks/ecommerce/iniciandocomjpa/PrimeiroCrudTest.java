package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void create(){
        Cliente cliente = new Cliente();

        cliente.setId(3);
        cliente.setNome("Luquinha");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 3);
        Assert.assertNotNull(clienteVerificacao);

    }

    @Test
    public void read(){
        //Cliente cliente = new Cliente();

        //cliente.setId(3);
        //cliente.setNome("Luquinha");

        entityManager.getTransaction().begin();
        entityManager.find(Cliente.class, 1);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 1);
        Assert.assertNotNull(clienteVerificacao);

    }

    @Test
    public void update(){
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        cliente.setNome("Sávio Goudinho");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 2);
        Assert.assertNotNull(clienteVerificacao);
        Assert.assertEquals("Sávio Goudinho", cliente.getNome());

    }

    @Test
    public void delete() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 2);
        Assert.assertNull(clienteVerificacao);


    }

}
