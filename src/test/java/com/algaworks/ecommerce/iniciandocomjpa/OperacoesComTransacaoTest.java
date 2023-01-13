package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirTransacaoBancoDeDados(){

        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle paperwhite 10ª gen");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle", produtoVerificacao.getNome());

    }

    @Test
    public void mostrarDiferencaMergePersist(){
        Produto produtoPersist = new Produto();

        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus ");
        produtoPersist.setDescricao("O processador mais rápido");
        produtoPersist.setPreco(new BigDecimal( 2000));

        entityManager.getTransaction().begin();

        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone two plus.");

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produtoPersist.getId());
        Assert.assertNotNull(produtoVerificacao);

        Produto produtoMerge = new Produto();

        produtoMerge.setId(6);
        produtoMerge.setNome("Smartphone Three Plus ");
        produtoMerge.setDescricao("O processador mais rápido");
        produtoMerge.setPreco(new BigDecimal( 3000));

        entityManager.getTransaction().begin();

        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Smartphone four plus.");

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assert.assertNotNull(produtoVerificacaoMerge);

    }

    @Test
    public void InserirOPrimeiroObjetoComMerge(){
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone ");
        produto.setDescricao("Melhor definição do som");
        produto.setPreco(new BigDecimal( 1000));

        entityManager.getTransaction().begin();

        entityManager.merge(produto);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);

    }

    @Test
    public void atualizarPrimeiroObjetoGerenciado(){

        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle paperwhite 10ª gen");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle paperwhite 10ª gen", produtoVerificacao.getNome());

    }

    @Test
    public void atualizarPrimeiroObjeto(){

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Kindle 11 gen");
        produto.setDescricao("Conheça o novo kindle");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle 11 gen", produtoVerificacao.getNome());

    }

    @Test
    public void removerPrimeiroObjeto(){

        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificacao);

    }

    @Test
    public void InserirOPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("Melhor definição em suas fotos");
        produto.setPreco(new BigDecimal( 5000));

        entityManager.getTransaction().begin();

        entityManager.persist(produto);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);

    }

    @Test
    public void abrirEFecharATransacao(){

        //nao mostrar erros
//        Produto produto = new Produto();

        entityManager.getTransaction().begin();


//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();

    }

}
