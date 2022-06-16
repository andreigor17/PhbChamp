/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Estatisticas;
import br.com.champ.Modelo.ItemPartida;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.EstatisticaServico;
import br.com.champ.Servico.ItemPartidaServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.util.ArrayList;
import java.util.List;
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
public class ManagerPesquisarEstatisticas {

    @EJB
    private EstatisticaServico estatisticasServico;
    @EJB
    private ItemPartidaServico itemPartidaServico;
    private Estatisticas estatisticas;
    private ItemPartida itemPartida;
    private List<Estatisticas> ests;

    @PostConstruct
    public void init() {
        instanciar();
        String visualizarItemId = FacesUtil
                .getRequestParameter("id");
        if (visualizarItemId != null && !visualizarItemId.isEmpty()) {
            this.itemPartida = this.itemPartidaServico.buscaItem(Long.parseLong(visualizarItemId));

        }

    }

    public void instanciar() {
        this.itemPartida = new ItemPartida();
        this.estatisticas = new Estatisticas();
        this.ests = new ArrayList<>();
    }

    public Estatisticas getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
    }

    public ItemPartida getItemPartida() {
        return itemPartida;
    }

    public void setItemPartida(ItemPartida itemPartida) {
        this.itemPartida = itemPartida;
    }

    public List<Estatisticas> getEsts() {
        return ests;
    }

    public void setEsts(List<Estatisticas> ests) {
        this.ests = ests;
    }

    public List<Estatisticas> estsGerais(Team team) {
        this.ests = estatisticasServico.estatisticaPorItemPartida(team.getId(), this.itemPartida.getId());
        return this.ests;
    }

    public void salvar() {
        for (Estatisticas e : this.ests) {
            try {
                estatisticasServico.salvar(e, e.getId(), Url.SALVAR_ESTATISTICA.getNome());
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        Mensagem.successAndRedirect("Dados atualizados com sucesso!", "visualizarPartida.xhtml?id=" + this.itemPartida.getPartida());
    }

}
