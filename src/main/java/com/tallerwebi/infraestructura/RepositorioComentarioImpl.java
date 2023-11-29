package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Comentario;
import com.tallerwebi.dominio.RepositorioComentario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Viaje;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioComentarioImpl implements RepositorioComentario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioComentarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarComentario(Comentario comentario) {
        this.sessionFactory.getCurrentSession().save(comentario);
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        this.sessionFactory.getCurrentSession().delete(comentario);
    }

    @Override
    public Comentario buscarComentarioPorId(Comentario id) {
        return sessionFactory.getCurrentSession().get(Comentario.class, id.getId());
    }

    @Override
    public List<Comentario> obtenerComentarios() {
        return sessionFactory.getCurrentSession().createQuery("FROM Comentario", Comentario.class).list();
    }

    @Override
    public List<Comentario> buscarComentariosPorUsuario(Usuario usuario) {

        List<Comentario> comentarios = sessionFactory.getCurrentSession()
                .createQuery("FROM Comentario C WHERE C.usuarioDestino = :usuario_destino_id", Comentario.class)
                .setParameter("usuario_destino_id",usuario)
                .list();
        return comentarios;
    }
}
