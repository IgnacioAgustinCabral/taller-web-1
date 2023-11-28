package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario buscarUsuario(String email) {

        final Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Usuario V WHERE V.email = :email", Usuario.class).setParameter("email",email).uniqueResult();
    }

    @Override
    public void guardar(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }

    @Override
    public Usuario buscar(String email) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void modificar(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return sessionFactory.getCurrentSession().createQuery("FROM Usuario", Usuario.class).list();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return (Usuario) sessionFactory.getCurrentSession().createQuery("FROM Usuario WHERE id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Usuario buscarPorTokenValidacion(String token) {
        return (Usuario) sessionFactory.getCurrentSession().createQuery("FROM Usuario WHERE tokenValidacion = :token")
                .setParameter("token", token)
                .uniqueResult();
    }

    @Override
    public void actualizar(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public Usuario buscarPorTokenPassword(String tokenPassword) {
        return (Usuario) sessionFactory.getCurrentSession().createQuery("FROM Usuario WHERE tokenResetPassword = :tokenPassword")
                .setParameter("tokenPassword",tokenPassword)
                .uniqueResult();
    }

}
