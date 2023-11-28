package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Provincia;
import com.tallerwebi.dominio.RepositorioProvincia;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioProvinciaImpl implements RepositorioProvincia {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioProvinciaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void guardar(Provincia provincia) {
        sessionFactory.getCurrentSession().save(provincia);
    }

    @Override
    public List<Provincia> obtenerTodasLasProvincias() {
        return sessionFactory.getCurrentSession().createQuery("FROM Provincia", Provincia.class)
                .list();
    }

    @Override
    public Provincia buscarProvinciaPorNombre(String nombre) {
        return (Provincia) sessionFactory.getCurrentSession().createQuery("FROM Provincia P WHERE P.nombre = :nombre", Provincia.class)
                .setParameter("nombre",nombre)
                .uniqueResult();

    }

    @Override
    public List<Provincia> buscarProvinciasConImagenes() {
        String hqlQuery = "SELECT p.nombre, COUNT(v.id) as nroviajes FROM Viaje v " +
                "JOIN v.destino ciudad " +
                "JOIN ciudad.provincia p " +
                "GROUP BY p.nombre " +
                "ORDER BY COUNT(v.id) DESC";

        List<Object[]> resultados = sessionFactory.getCurrentSession().createQuery(hqlQuery, Object[].class)
                .setMaxResults(3)
                .getResultList();

        List<Provincia> provinciasTop = new ArrayList<>();

        for (Object[] obj : resultados) {
            provinciasTop.add(this.buscarProvinciaPorNombre((String) obj[0]));
        }

        return provinciasTop;

        /*return sessionFactory.getCurrentSession().createQuery("FROM Provincia WHERE imagen != NULL", Provincia.class)
                .list();*/
    }

    @Override
    public Provincia obtenerProvinciaPorId(Long id) {
        return (Provincia) sessionFactory.getCurrentSession().createQuery("FROM Provincia WHERE id = :id")
                .setParameter("id",id)
                .uniqueResult();
    }


}
