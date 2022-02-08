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
import java.io.ByteArrayInputStream;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andre
 */
@SessionScoped
@ManagedBean
public class ManagerCriarPlayer implements Serializable {

    @EJB
    PlayerServico playerServico;
    @EJB
    AnexoServico anexoServico;

    private Player p;
    private List<Player> players;
    ManagerAnexo arquivo = new ManagerAnexo();
    private UploadedFile file;
    private StreamedContent imagem;

    @PostConstruct
    public void init() {
        instanciar();
    }

    public void instanciar() {
        this.p = new Player();
        this.players = null;
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }

    public void doUpload(FileUploadEvent event) {
        try {
            this.arquivo.fileUpload(event, ".png", "/image/");
            this.p.setAvatar(this.arquivo.getNome());
            imagem = new DefaultStreamedContent(event.getFile().getInputstream());
            this.setFile(event.getFile());
        } catch (IOException e) {
            //Tratamento de exceção.
        }

    }

    public void salvarPlayer() throws Exception {

        this.playerServico.save(this.p);
        System.out.println("carai " + p.toString());
//        this.arquivo.gravar();
        //Mensagem.successAndRedirect("Player salvo com sucesso", "visualizarPlayer.xhtml?id=" + this.player.getId());
        
        //layer = new Player();
    }

    public void pesquisarPlayer() throws Exception {
        this.players = playerServico.pesquisar(this.p);
    }

    public void limpar() {
        instanciar();
    }

    public void removerPlayer() {
        //this.playerServico.delete(this.player);
        Mensagem.successAndRedirect("pesquisarPlayer.xhtml");
        init();
    }

}
