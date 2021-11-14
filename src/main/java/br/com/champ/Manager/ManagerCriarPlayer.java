/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Anexo;
import br.com.champ.Servico.AnexoServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerCriarPlayer implements Serializable {

    @EJB
    PlayerServico playerServico;
    @EJB
    AnexoServico anexoServico;

    private Player player;
    private List<Player> players;
    private ManagerAnexo arquivo = new ManagerAnexo();

    @PostConstruct
    public void init() {
        instanciar();

        String visualizarPlayerId = FacesUtil
                .getRequestParameter("id");

        if (visualizarPlayerId != null && !visualizarPlayerId.isEmpty()) {
            this.player = this.playerServico.find(Long.parseLong(visualizarPlayerId));
        }
    }

    public void instanciar() {
        this.player = new Player();
        this.players = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public void uploadAction (FileUploadEvent event){
        this.arquivo.fileUpload(event, ".png", "/image/");
        this.player.setAvatar(this.arquivo.getNome());
    }


    public void salvarPlayer() {

        this.playerServico.salvar(this.player);
        this.arquivo.gravar();
        Mensagem.successAndRedirect("Player salvo com sucesso", "visualizarPlayer.xhtml?id=" + this.player.getId());

    }

    public void pesquisarPlayer() {
        this.players = playerServico.pesquisar(this.player);
    }

    public void limpar() {
        instanciar();
    }

    public void removerPlayer() {
        this.playerServico.delete(this.player);
        Mensagem.successAndRedirect("pesquisarPlayer.xhtml");
        init();
    }

}
