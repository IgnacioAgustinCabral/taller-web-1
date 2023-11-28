package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Ciudad;
import com.tallerwebi.dominio.RepositorioViaje;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Viaje;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RepositorioViajeImpl implements RepositorioViaje {

    private  SessionFactory sessionFactory;

    @Autowired
    public RepositorioViajeImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;

    }
    @Override
    public void guardar(Viaje viaje) {
        sessionFactory.getCurrentSession().save(viaje);
    }

    @Override
    public Viaje buscarPorId(Long id) {
       Viaje viaje = sessionFactory.getCurrentSession().get(Viaje.class,id);
       return viaje;
    }


    @Override
    public List<Viaje> buscarPorDestino(Ciudad destino) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Viaje> criteria = builder.createQuery(Viaje.class);
        Root<Viaje> root = criteria.from(Viaje.class);
        criteria.select(root).where(builder.equal(root.get("destino"), destino));

        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }

    @Override
    public void actualizar(Viaje viaje) {
        sessionFactory.getCurrentSession().save(viaje);
    }

    @Override
    public List<Viaje> listarViajes() {
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje", Viaje.class).list();
    }

    @Override
    public List<Viaje> buscarPorOrigen(Ciudad origen) {
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje V WHERE V.origen.id = :origen_viaje", Viaje.class)
                .setParameter("origen_viaje",origen.getId())
                .list();
    }




    @Override
    public List<Viaje> buscarPorFecha(String fecha) {
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje WHERE fecha = :fecha", Viaje.class)
                .setParameter("fecha",fecha)
                .list();
    }

    @Override
    public void eliminar(Viaje viaje) {sessionFactory.getCurrentSession().delete(viaje);}

    @Override
    public List<Viaje> buscarPorUsuario(Usuario usuario) {
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje V WHERE V.usuario = :id_usuario", Viaje.class)
                .setParameter("id_usuario",usuario)
                .list();

    }

    @Override
    public List buscarPorOrigenDestinoYfecha(Ciudad origen, Ciudad destino, String fecha) {
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje WHERE origen.id = :origen AND destino.id = :destino AND fecha = :fecha ",Viaje.class)
                .setParameter("origen", origen.getId())
                .setParameter("destino",destino.getId())
                .setParameter("fecha",fecha)
                .list();
                    }

    @Override
    public List <Viaje> listarPorProvincia(String provincia) {
        return sessionFactory.getCurrentSession().createQuery(
                "SELECT V FROM Viaje V INNER JOIN Ciudad C " +
                        "ON V.destino.id = C.id INNER JOIN Provincia P ON " +
                        "C.provincia.id = P.id WHERE P.nombre = :provincia", Viaje.class)
                .setParameter("provincia", provincia)
                .list();
    }

    @Override
    public void eliminarViaje(Long viajeId) {
        Viaje viaje = buscarPorId(viajeId);

        if (viaje != null) {
            eliminar(viaje);
        }
    }




}

