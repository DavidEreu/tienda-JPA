package com.latam.ereu.tienda.prueba;

import com.latam.ereu.tienda.dao.CategoriaDao;
import com.latam.ereu.tienda.dao.ClienteDao;
import com.latam.ereu.tienda.dao.PedidoDao;
import com.latam.ereu.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.*;
import com.latam.ereu.tienda.modelo.*;
import com.latam.ereu.tienda.utils.JPAUtils;
import com.latam.ereu.tienda.vo.RelatorioDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDePedido {

    public static void main(String[] args) {

        registrarProducto();

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaPorId(1l);

        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Cliente cliente = new Cliente("Juan", "k898k");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarItems(new ItemsPedido(5,producto,pedido));

        em.getTransaction().begin();

        clienteDao.guardar(cliente);
        pedidoDao.guardar(pedido);

        em.getTransaction().commit();

        BigDecimal  valorTotal = pedidoDao.valorTotalVendido();
        System.out.println("Valor Total: " + valorTotal);

        List<Object[]> relatorio = pedidoDao.relatorioDeVentas();

        for (Object[] obj : relatorio){
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
        }

        List<RelatorioDeVenta> relatorio2 = pedidoDao.relatorioDeVentasVO();

        relatorio2.forEach(System.out::println);
    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");

        Producto celular = new Producto("Xiaomi Redmi", "Muy bueno", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
