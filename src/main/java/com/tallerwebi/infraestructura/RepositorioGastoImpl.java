package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Gasto;
import com.tallerwebi.dominio.RepositorioGasto;
import com.tallerwebi.dominio.Viaje;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioGastoImpl implements RepositorioGasto {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioGastoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void agregarGasto(Gasto gasto) {
        sessionFactory.getCurrentSession().save(gasto);
    }

    @Override
    public List<Gasto> listarGastos() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Gasto", Gasto.class)
                .list();
    }

    @Override
    public List<Gasto> listarGastosPorViaje(Viaje viaje) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Gasto WHERE viaje = :viaje", Gasto.class)
                .setParameter("viaje",viaje)
                .list();
    }
}
