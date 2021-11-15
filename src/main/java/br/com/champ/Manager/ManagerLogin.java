/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Modelo.Usuario;
import br.com.champ.Servico.UsuarioServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerLogin implements Serializable {

    @EJB
    UsuarioServico usuarioServico;
    private Usuario usuario;
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        instanciar();

    }

    public void instanciar() {
        this.usuario = new Usuario();
        this.usuarios = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String envia() {

        this.usuario.setId(usuarioServico.autenticar(usuario.getLogin(), usuario.getSenha()));
        if (usuario == null) {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                            "Erro no Login!"));
            return null;
        } else {
            Mensagem.successAndRedirect("Login realizado com sucesso", "index.xhtml");
        }
        return null;

    }

}
