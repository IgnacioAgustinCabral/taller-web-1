package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Ciudad;
import com.tallerwebi.dominio.RepositorioCiudad;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCiudadImpl implements RepositorioCiudad {

    private  SessionFactory sessionFactory;

    public RepositorioCiudadImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void guardar(Ciudad ciudad) {
        sessionFactory.getCurrentSession().save(ciudad);
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return sessionFactory.getCurrentSession().createQuery("FROM Ciudad", Ciudad.class)
                .list();
    }

    @Override
    public Ciudad obtenerCiudadPorId(Long id) {
        return (Ciudad) sessionFactory.getCurrentSession().createQuery("FROM Ciudad WHERE id = :id")
                .setParameter("id",id)
                .uniqueResult();
    }
}
