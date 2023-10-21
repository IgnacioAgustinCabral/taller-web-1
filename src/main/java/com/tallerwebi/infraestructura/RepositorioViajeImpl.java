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
import java.time.LocalDate;
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
        return sessionFactory.getCurrentSession().get(Viaje.class,id);
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
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje V WHERE V.origen = :origen_viaje", Viaje.class)
                .setParameter("origen_viaje",origen)
                .list();
    }

    @Override
    public List buscarPorFecha(LocalDate fecha) {
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje WHERE fecha = :fecha ", Viaje.class)
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
    public List buscarPorOrigenDestinoYfecha(Ciudad origen, Ciudad destino, LocalDate fecha) {
        return sessionFactory.getCurrentSession().createQuery("FROM Viaje WHERE origen = :origen OR destino = :destino OR fecha = :fecha", Viaje.class)
                .setParameter("origen", origen)
                .setParameter("destino", destino)
                .setParameter("fecha", fecha)
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
}
