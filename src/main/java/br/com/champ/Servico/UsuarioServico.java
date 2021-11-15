/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import javax.ejb.Stateless;
import br.com.champ.Modelo.Usuario;
import java.util.List;
/**
 *
 * @author andre
 */
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
public class UsuarioServico extends ServicoGenerico<Usuario> {

    private Usuario usuario;

    public UsuarioServico() {
        super(Usuario.class);
    }

    public Long autenticar(String usuario, String senha) {
        String sql = "select usuario from Usuario usuario "
                + "where usuario.ativo = true and usuario.login = :usuario and usuario.senha = :senha";

        Query query = getEntityManager().createQuery(sql);
        if (usuario != null && senha != null) {
            query.setParameter("usuario", usuario);
            query.setParameter("senha", senha);
        } else {
            return null;
        }

        List<Usuario> list = query.getResultList();

        return list.get(0).getId();

    }

}
