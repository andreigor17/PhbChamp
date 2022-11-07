/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.champ.Manager;

import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Jogo;
import br.com.champ.Servico.JogoServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.util.ArrayList;
import java.util.List;
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
public class ManagerJogo {

    @EJB
    private JogoServico jogoServico;
    private Jogo jogo;
    private List<Jogo> jogos;

    @PostConstruct
    public void init() {
        String visualizarMapaId = FacesUtil
                .getRequestParameter("id");

        if (visualizarMapaId != null && !visualizarMapaId.isEmpty()) {
            this.jogo = this.jogoServico.pesquisarJogo(Long.parseLong(visualizarMapaId));

        } else {
            instanciar();
        }

    }

    public void instanciar() {
        this.jogo = new Jogo();
        this.jogos = new ArrayList<>();
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public void limpar() {
        instanciar();
    }

    public void pesquisarJogos() {
        try {
            this.jogos = jogoServico.pesquisar();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void salvar() {
        Jogo j = new Jogo();
        try {
            j = jogoServico.save(this.jogo, null, Url.SALVAR_JOGO.getNome());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        Mensagem.successAndRedirect("Jogo criado com sucesso", "visualizarJogo.xhtml?id=" + j.getId());

    }

    public void excluir() {
        try {
            Jogo j = new Jogo();
            this.jogo.setActive(Boolean.FALSE);
            j = jogoServico.save(this.jogo, null, Url.ATUALIZAR_JOGO.getNome());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        Mensagem.successAndRedirect("Jogo excluído com sucesso!", "pesquisarJogos.xhtml");
    }

}
