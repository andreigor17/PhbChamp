/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Configuracao;
import br.com.champ.Modelo.Jogo;
import br.com.champ.Modelo.Player;
import br.com.champ.Servico.AnexoServico;
import br.com.champ.Servico.ConfiguracaoServico;
import br.com.champ.Servico.JogoServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

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
    @EJB
    ConfiguracaoServico configuracaoServico;
    @EJB
    private JogoServico jogoServico;

    private Player p;
    private List<Player> players;
    ManagerAnexo arquivo = new ManagerAnexo();
    private UploadedFile file;
    private StreamedContent imagem;
    private Configuracao configuracao;
    private List<Jogo> jogos;
    private List<Jogo> jogosSelecionados;
    private Jogo jogo;

    @PostConstruct
    public void init() {
        try {
            instanciar();

            String visualizarPlayerId = FacesUtil
                    .getRequestParameter("id");

            if (visualizarPlayerId != null && !visualizarPlayerId.isEmpty()) {
                this.p = this.playerServico.buscaPlayer(Long.parseLong(visualizarPlayerId));
            }

            this.jogos = jogoServico.pesquisar();
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    public void instanciar() {
        this.p = new Player();
        this.players = null;
        this.configuracao = new Configuracao();
        this.jogo = new Jogo();
        this.jogos = new ArrayList<>();
        this.jogosSelecionados = new ArrayList<>();
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

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public void doUpload(FileUploadEvent event) {
        this.arquivo.fileUpload(event, ".png", "/image/"); //Tratamento de exceção.
        this.p.setAvatar(this.arquivo.getNome());
        imagem = new DefaultStreamedContent();
        this.setFile(event.getFile());

    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<Jogo> getJogosSelecionados() {
        return jogosSelecionados;
    }

    public void setJogosSelecionados(List<Jogo> jogosSelecionados) {
        this.jogosSelecionados = jogosSelecionados;
    }

    public void teste() {
        System.out.println("teste " + this.jogosSelecionados.size());
    }

    public void salvarPlayer() throws Exception {
        Player player = new Player();

        this.p.setJogos(this.jogosSelecionados);
        if (this.p.getId() == null) {

            player = playerServico.save(this.p, null, Url.SALVAR_PLAYER.getNome());
            this.arquivo.gravar();
        } else {
            player = playerServico.save(this.p, this.p.getId(), Url.ATUALIZAR_PLAYER.getNome());
        }

        Mensagem.successAndRedirect("Player salvo com sucesso", "visualizarPlayer.xhtml?id=" + player.getId());

    }

    public void pesquisarPlayer() throws Exception {        
        this.players = playerServico.pesquisar(this.p.getNome());
    }

    public void limpar() {
        instanciar();
    }

    public void removerPlayer() {
        //this.playerServico.delete(this.player);
        Mensagem.successAndRedirect("pesquisarPlayer.xhtml");
        init();
    }

    public void adicionarJogo() {
        this.jogosSelecionados.add(this.jogo);
        this.jogo = new Jogo();

    }

    public List<Jogo> jogos() {
        return jogos;
    }

}
