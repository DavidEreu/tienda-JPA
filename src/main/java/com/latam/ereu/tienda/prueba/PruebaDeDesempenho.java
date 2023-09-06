package com.latam.ereu.tienda.prueba;

import com.latam.ereu.tienda.dao.PedidoDao;
import com.latam.ereu.tienda.modelo.Pedido;
import com.latam.ereu.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class PruebaDeDesempenho {
    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.cargarRegistros();

        EntityManager em = JPAUtils.getEntityManager();

        PedidoDao pedidoDao = new PedidoDao(em);

        Pedido pedido = em.find(Pedido.class,3l);

        Pedido pedidoConCliente = pedidoDao.consultarPedidoConCliente(2l);

        em.close();
        //System.out.println(pedido.getFecha());
        //System.out.println(pedido.getItems().size());
        //System.out.println(pedido.getCliente().getNombre());

        System.out.println(pedidoConCliente.getCliente().getNombre());

    }
}
