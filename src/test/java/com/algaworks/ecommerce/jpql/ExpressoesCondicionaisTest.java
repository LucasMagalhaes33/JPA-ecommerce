package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void usarExpressaoCondicionalLike() {

        String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("nome", "a");

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarIsNull() {

        String jpql = "select p from Produto p where p.foto is null";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);


        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarIsEmpty() {

        String jpql = "select p from Produto p where p.categorias is empty";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);


        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void selecionarUmAtributoParaRetorno() {

        String jpql = "select p.nome from Produto p";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);


        List<String> lista = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(lista.get(0).getClass()));

        String jpqlCliente = "select p.cliente from Pedido p";

        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);

        List<Cliente> listaCliente = typedQueryCliente.getResultList();
        Assert.assertTrue(Cliente.class.equals(listaCliente.get(0).getClass()));
    }

}
