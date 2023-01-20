package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvarItem() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarNotaFiscal());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaVerificacao =  entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assert.assertNotNull(notaFiscal.getXml());
        Assert.assertTrue(notaFiscal.getXml().length > 0);

        /*
        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(System.getProperty("user.home") + "/xml.xml")).toFile());
                    out.write(notaVerificacao.getXml());
        } catch (Exception e){
            throw new RuntimeException(e);
        }*/
    }

    @Test
    public void salvarFotoProduto() {
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setImagem(carregarFoto());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao =  entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produto.getImagem());
        Assert.assertTrue(produto.getImagem().length > 0);


        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(System.getProperty("user.home") + "/jpg.jpg")).toFile());
                    out.write(produtoVerificacao.getImagem());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static byte[] carregarNotaFiscal() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(
                    "/nota-fiscal.xml").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] carregarFoto() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(
                    "/foto-kindle.jpg").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
