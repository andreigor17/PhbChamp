/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Modelo.Configuracao;
import br.com.champ.Servico.ConfiguracaoServico;
import br.com.champ.Utilitario.Mensagem;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerConfiguracao implements Serializable {

    private String menu = "GERAL";
    private Configuracao configuracao;
    @EJB
    private ConfiguracaoServico configuracaoServico;

    @PostConstruct
    public void init() {
        try {
            this.configuracao = this.configuracaoServico.buscaConfig();

        } catch (Exception ex) {
            Logger.getLogger(ManagerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void instanciar() throws Exception {
        this.configuracao = new Configuracao();

    }

    public void renderizarMenu(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public void salvar() {
        try {
            Configuracao c = new Configuracao();

            if (this.configuracao.getCaminhoApi() == null) {
                Mensagem.error("O caminho da API não pode estar vazio!");
                return;
            }
            c = configuracaoServico.save(this.configuracao, this.configuracao.getId());

            Mensagem.successAndRedirect("Configuração salva com  sucesso", "configuracao.xhtml");

        } catch (Exception ex) {
            Logger.getLogger(ManagerConfiguracao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
