package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioViajeDisplay;
import com.tallerwebi.dominio.ViajeDisplay;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RepositorioViajeDisplayImpl implements RepositorioViajeDisplay {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioViajeDisplayImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ViajeDisplay buscarPorId(Long id) {
        return sessionFactory.getCurrentSession().get(ViajeDisplay.class, id);
    }

    @Override
    public List<ViajeDisplay> listarViajeDisplay() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ViajeDisplay> criteria = builder.createQuery(ViajeDisplay.class);
        Root<ViajeDisplay> root = criteria.from(ViajeDisplay.class);
        criteria.select(root);

        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}
