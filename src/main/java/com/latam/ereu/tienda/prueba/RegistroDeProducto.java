package com.latam.ereu.tienda.prueba;

import com.latam.ereu.tienda.dao.CategoriaDao;
import com.latam.ereu.tienda.dao.ProductoDao;
import com.latam.ereu.tienda.modelo.Categoria;
import com.latam.ereu.tienda.modelo.CategoriaId;
import com.latam.ereu.tienda.modelo.Producto;
import com.latam.ereu.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDeProducto {

    public static void main(String[] args) {
        /*
        Categoria celulares = new Categoria("CELULARES");
        EntityManager em = JPAUtils.getEntityManager();

        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNombre("LIBROS");
        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNombre("SOFTWARES");
        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        em.remove(celulares);
        em.flush();
         */

        registrarProducto();
        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        Producto producto =productoDao.consultaPorId(1l);
        System.out.println(producto.getNombre());

        List<Producto>productos = productoDao.consultarTodos();
        productos.forEach(prod -> System.out.println(prod.getDescripcion()));

        List<Producto>productos2 = productoDao.consultaPorNombre("Xiaomi Redmi");
        productos2.forEach(prod -> System.out.println(prod.getDescripcion()));

        List<Producto>productos3 = productoDao.consultaPorNombreDeCategoria("CELULARES");
        productos3.forEach(prod -> System.out.println(prod.getDescripcion()));

        BigDecimal precio = productoDao.consultaPrecioPorNombreDeProducto("Xiaomi Redmi");
        System.out.println(precio);

        Categoria find = em.find(Categoria.class,new CategoriaId("Celulares","222"));

        System.out.println(find.getNombre());
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
