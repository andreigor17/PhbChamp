/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Manager;

import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Estatisticas;
import br.com.champ.Modelo.ItemPartida;
import br.com.champ.Modelo.Mapas;
import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.EstatisticaServico;
import br.com.champ.Servico.ItemPartidaServico;
import br.com.champ.Servico.MapaServico;
import br.com.champ.Servico.PartidaServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Servico.TeamServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import br.com.champ.Utilitario.PartidaUtils;
import br.com.champ.Utilitario.PickBanUtils;
import br.com.champ.vo.PickBanVo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerPartida {

    @EJB
    private PartidaServico partidaServico;
    @EJB
    ItemPartidaServico itemPartidaServico;
    @EJB
    PlayerServico playerServico;
    @EJB
    TeamServico teamServico;
    @EJB
    EstatisticaServico estatisticasServico;
    @EJB
    MapaServico mapaServico;
    private Partida partida;
    private Partida partidaPesquisar;
    private List<Player> playersTime1;
    private List<Player> playersTime2;
    private List<Partida> partidas;
    private List<ItemPartida> itensPartidas;
    private ItemPartida itemPartida;
    private int qtdItensPartidas;
    private String capitaoTime1;
    private String capitaoTime2;
    private String nomeTime1;
    private String nomeTime2;
    private List<ItemPartida> itemPartidas;
    private List<Player> allPlayers;
    private List<Player> selectedPlayers;
    private DualListModel<Player> playerGroupList;
    private Player player;
    private List<Player> players;
    private List<Estatisticas> estsGerais;
    private boolean skip;
    private List<Mapas> mapas;
    private List<Mapas> pickedMaps;
    List<String> picksbans;
    private List<PickBanVo> pickBanVo;
    private PickBanVo pbItem;
    private boolean classica = false;
    private int tipoEscolhaCapitaes;
    private List<Player> droppedPlayers1;
    private List<Player> droppedPlayers2;
    private Player selectedPlayer;
    private List<Player> pickedPlayers;

    @PostConstruct
    public void init() {
        try {
            instanciar();
            String visualizarPartidaId = FacesUtil
                    .getRequestParameter("id");
            String gerarMapasId = FacesUtil
                    .getRequestParameter("partidaId");

            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            this.classica = parametros.get("classica") != null;

            if (this.classica) {
                PrimeFaces.current().executeScript("PF('selecaoDeCapitaesDialog').show();");
            }

            if (visualizarPartidaId != null && !visualizarPartidaId.isEmpty()) {
                this.partida = this.partidaServico.pesquisar(Long.parseLong(visualizarPartidaId));
            } else if (gerarMapasId != null && !gerarMapasId.isEmpty()) {
                this.partida = this.partidaServico.pesquisar(Long.parseLong(gerarMapasId));
            } else {
                try {
                    instanciar();
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }

            if (this.partida.getId() != null) {
                try {
                    this.itensPartidas = this.partida.getItemPartida();
                    this.mapas = mapaServico.pesquisar();
                    this.pickBanVo = PickBanUtils.gerarListaPB(this.partida.getItemPartida().get(0).getTeam1(), this.partida.getItemPartida().get(0).getTeam2(), this.partida.getItemPartida().size());
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    public void instanciar() throws Exception {
        this.partida = new Partida();
        this.partidas = new ArrayList<>();
        this.partidaPesquisar = new Partida();
        this.itemPartida = new ItemPartida();
        this.itensPartidas = new ArrayList<>();
        this.selectedPlayers = new ArrayList<Player>();
        this.allPlayers = playerServico.pesquisar(this.player);
        this.playerGroupList = new DualListModel<>(this.allPlayers, this.selectedPlayers);
        this.itemPartidas = new ArrayList<>();
        this.itemPartida = new ItemPartida();
        this.estsGerais = new ArrayList<Estatisticas>();
        this.mapas = new ArrayList<>();
        this.pickedMaps = new ArrayList<>();
        this.droppedPlayers1 = new ArrayList<>();
        this.droppedPlayers2 = new ArrayList<>();
        this.selectedPlayer = new Player();
        this.selectedPlayers = new ArrayList<>();
    }

    public List<Estatisticas> estsGerais(Team team, ItemPartida item) {
        List<Estatisticas> e = estatisticasServico.estatisticaPorItemPartida(team.getId(), item.getId());
        return e;
    }

    public List<Estatisticas> somaEsts(Team team) {
        List<Estatisticas> soma = new ArrayList<>();
        Estatisticas est = new Estatisticas();
        Integer kills = 0;
        Integer deaths = 0;
        Integer assists = 0;
        List<Estatisticas> ests = estatisticasServico.estatisticaPorPartida(team.getId(), this.partida.getId());
        for (Player p : team.getPlayers()) {
            for (Estatisticas e : ests) {
                if (e.getPlayer().getId().equals(p.getId())) {
                    kills += e.getKills();
                    deaths += e.getDeaths();
                    assists += e.getAssists();

                }

            }
            est.setKills(kills);
            est.setAssists(assists);
            est.setDeaths(deaths);
            est.setPlayer(p);
            est.setTeam(team);
            soma.add(est);
            kills = 0;
            deaths = 0;
            assists = 0;
            est = new Estatisticas();
        }
        return soma;

    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public List<Player> getPlayersTime1() {
        return playersTime1;
    }

    public void setPlayersTime1(List<Player> playersTime1) {
        this.playersTime1 = playersTime1;
    }

    public List<Player> getPlayersTime2() {
        return playersTime2;
    }

    public void setPlayersTime2(List<Player> playersTime2) {
        this.playersTime2 = playersTime2;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public Partida getPartidaPesquisar() {
        return partidaPesquisar;
    }

    public void setPartidaPesquisar(Partida partidaPesquisar) {
        this.partidaPesquisar = partidaPesquisar;
    }

    public void pesquisar() {
        this.partidas = partidaServico.pesquisarPartidas(this.partidaPesquisar);
    }

    public List<ItemPartida> getItensPartidas() {
        return itensPartidas;
    }

    public void setItensPartidas(List<ItemPartida> itensPartidas) {
        this.itensPartidas = itensPartidas;
    }

    public ItemPartida getItemPartida() {
        return itemPartida;
    }

    public void setItemPartida(ItemPartida itemPartida) {
        this.itemPartida = itemPartida;
    }

    public int getQtdItensPartidas() {
        return qtdItensPartidas;
    }

    public void setQtdItensPartidas(int qtdItensPartidas) {
        this.qtdItensPartidas = qtdItensPartidas;
    }

    public String getCapitaoTime1() {
        return capitaoTime1;
    }

    public void setCapitaoTime1(String capitaoTime1) {
        this.capitaoTime1 = capitaoTime1;
    }

    public String getCapitaoTime2() {
        return capitaoTime2;
    }

    public void setCapitaoTime2(String capitaoTime2) {
        this.capitaoTime2 = capitaoTime2;
    }

    public String getNomeTime1() {
        return nomeTime1;
    }

    public void setNomeTime1(String nomeTime1) {
        this.nomeTime1 = nomeTime1;
    }

    public String getNomeTime2() {
        return nomeTime2;
    }

    public void setNomeTime2(String nomeTime2) {
        this.nomeTime2 = nomeTime2;
    }

    public List<ItemPartida> getItemPartidas() {
        return itemPartidas;
    }

    public void setItemPartidas(List<ItemPartida> itemPartidas) {
        this.itemPartidas = itemPartidas;
    }

    public List<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public List<Player> getSelectedPlayers() {
        return selectedPlayers;
    }

    public void setSelectedPlayers(List<Player> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    public DualListModel<Player> getPlayerGroupList() {
        return playerGroupList;
    }

    public void setPlayerGroupList(DualListModel<Player> playerGroupList) {
        this.playerGroupList = playerGroupList;
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<Estatisticas> getEstsGerais() {
        return estsGerais;
    }

    public void setEstsGerais(List<Estatisticas> estsGerais) {
        this.estsGerais = estsGerais;
    }

    public List<Mapas> getMapas() {
        return mapas;
    }

    public void setMapas(List<Mapas> mapas) {
        this.mapas = mapas;
    }

    public List<String> getPicksbans() {
        return picksbans;
    }

    public void setPicksbans(List<String> picksbans) {
        this.picksbans = picksbans;
    }

    public List<PickBanVo> getPickBanVo() {
        return pickBanVo;
    }

    public void setPickBanVo(List<PickBanVo> pickBanVo) {
        this.pickBanVo = pickBanVo;
    }

    public List<Mapas> getPickedMaps() {
        return pickedMaps;
    }

    public void setPickedMaps(List<Mapas> pickedMaps) {
        this.pickedMaps = pickedMaps;
    }

    public PickBanVo getPbItem() {
        return pbItem;
    }

    public void setPbItem(PickBanVo pbItem) {
        this.pbItem = pbItem;
    }

    public void limpar() throws Exception {
        instanciar();
    }

    public boolean isClassica() {
        return classica;
    }

    public void setClassica(boolean classica) {
        this.classica = classica;
    }

    public int getTipoEscolhaCapitaes() {
        return tipoEscolhaCapitaes;
    }

    public void setTipoEscolhaCapitaes(int tipoEscolhaCapitaes) {
        this.tipoEscolhaCapitaes = tipoEscolhaCapitaes;
    }

    public List<Player> getDroppedPlayers1() {
        return droppedPlayers1;
    }

    public void setDroppedPlayers1(List<Player> droppedPlayers1) {
        this.droppedPlayers1 = droppedPlayers1;
    }

    public List<Player> getDroppedPlayers2() {
        return droppedPlayers2;
    }

    public void setDroppedPlayers2(List<Player> droppedPlayers2) {
        this.droppedPlayers2 = droppedPlayers2;
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public List<Player> getPickedPlayers() {
        return pickedPlayers;
    }

    public void setPickedPlayers(List<Player> pickedPlayers) {
        this.pickedPlayers = pickedPlayers;
    }

    public void excluir() {

    }

    public void pickarMapa(Mapas mapa) {
        if (pbItem.getTipoPickBan().getNome().equalsIgnoreCase("PICK")) {
            this.pickedMaps.add(mapa);
        }
        pbItem.setMapas(mapa);
        this.mapas.remove(mapa);

    }

    public void finalizarPicks() {
        try {
            List<ItemPartida> itensAtualizados = new ArrayList<>();
            itensAtualizados = PickBanUtils.setarMapas(this.pickedMaps, this.partida.getItemPartida());
            Partida p = new Partida();
            this.partida.setItemPartida(itensAtualizados);
            p = partidaServico.salvar(this.partida, this.partida.getId(), Url.ATUALIZAR_PARTIDA.getNome());
            Mensagem.successAndRedirect("Picks realizados com sucesso!", "visualizarPartida.xhtml?id=" + p.getId());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void retornarPartida() {
        Mensagem.successAndRedirect("Operação cancelada com sucesso!", "visualizarPartida.xhtml?id=" + this.partida.getId());
    }

    public void verificarPlayers() {
        this.selectedPlayers = this.playerGroupList.getTarget();
        if (this.selectedPlayers.size() % 2 == 0) {
            PrimeFaces.current().executeScript("PF('confirmarCriacaoX5Dialog').show();");
        } else {
            Mensagem.error("Para formar 2 times, adicione uma quantidade par de jogadores!");
            return;
        }
    }

    public void sorteioX5() {
        try {
            List<Player> time1 = new ArrayList<>();
            List<Player> time2 = new ArrayList<>();
            Team t1 = new Team();
            Team t2 = new Team();
            Partida partidaX5 = new Partida();
            ItemPartida itemPartidaAtual = new ItemPartida();
            Partida partida = new Partida();
            List<Partida> partidas = new ArrayList<>();
            List<Estatisticas> estsTeam1 = new ArrayList<Estatisticas>();
            List<Estatisticas> estsTeam2 = new ArrayList<Estatisticas>();

            this.selectedPlayers = this.playerGroupList.getTarget();

            Collections.shuffle(this.selectedPlayers);
            for (int i = 0; i < this.selectedPlayers.size() / 2; i++) {
                this.selectedPlayers.get(i).setPossuiTime(true);
                time1.add(this.selectedPlayers.get(i));
            }

            for (Player p : this.selectedPlayers) {
                if (!p.isPossuiTime()) {
                    time2.add(p);
                }

            }

            Team team1 = new Team();
            t1.setAtivo(true);
            t1.setNome(nomeTime1);
            t1.setPlayers(time1);
            team1 = teamServico.save(t1, null, Url.SALVAR_TIME.getNome());

            Team team2 = new Team();
            t2.setAtivo(true);
            t2.setNome(nomeTime2);
            t2.setPlayers(time2);
            team2 = teamServico.save(t2, null, Url.SALVAR_TIME.getNome());

            this.itemPartidas = PartidaUtils.gerarPartidas(partidaX5, null, team1, team2, this.qtdItensPartidas);
            partidaX5.setItemPartida(this.itemPartidas);
            partida = partidaServico.salvar(partidaX5, null, Url.SALVAR_PARTIDA.getNome());
            List<ItemPartida> it = partida.getItemPartida();

            List<ItemPartida> newItem = new ArrayList<>();

            for (ItemPartida ip : partida.getItemPartida()) {
                ip.setPartida(partida.getId());
                newItem.add(ip);
            }

            partida.setItemPartida(newItem);

            partida = partidaServico.salvar(partida, partida.getId(), Url.ATUALIZAR_PARTIDA.getNome());

            for (ItemPartida i : it) {
                for (Player playerTime1 : team1.getPlayers()) {
                    Estatisticas estatisticas = new Estatisticas();
                    estatisticas.setPlayer(playerTime1);
                    estatisticas.setTeam(team1);
                    estatisticas.setItemPartida(i);
                    estsTeam1.add(estatisticas);

                }
                this.estsGerais.addAll(estsTeam1);

                for (Player playerTime2 : team2.getPlayers()) {
                    Estatisticas estatisticas = new Estatisticas();
                    estatisticas.setPlayer(playerTime2);
                    estatisticas.setTeam(team2);
                    estatisticas.setItemPartida(i);
                    estsTeam2.add(estatisticas);
                }
                this.estsGerais.addAll(estsTeam2);

                for (Estatisticas e : this.estsGerais) {
                    estatisticasServico.salvar(e, null, Url.SALVAR_ESTATISTICA.getNome());
                }

                estsTeam1 = new ArrayList<Estatisticas>();
                estsTeam2 = new ArrayList<Estatisticas>();
                this.estsGerais = new ArrayList<Estatisticas>();

            }

            Mensagem.successAndRedirect("Partida criada com sucesso", "visualizarPartida.xhtml?id=" + partida.getId());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void redirecionarPartida(ItemPartida item) {
        Mensagem.successAndRedirect("editarItem.xhtml?id=" + item.getId());
    }

    public void onPlayerDrop1(DragDropEvent<Player> ddEvent) {
        Player player = ddEvent.getData();

        droppedPlayers1.add(player);
        this.pickedPlayers.remove(player);
    }

    public void onPlayerDrop2(DragDropEvent<Player> ddEvent) {
        Player player = ddEvent.getData();

        droppedPlayers2.add(player);
        this.pickedPlayers.remove(player);
    }

    public void selecionarPartidaClassica() {
        this.pickedPlayers = this.playerGroupList.getTarget();
        if (this.tipoEscolhaCapitaes == 1) {
            Collections.shuffle(this.pickedPlayers);
            droppedPlayers1.add(this.pickedPlayers.get(0));
            this.pickedPlayers.remove(0);
            droppedPlayers2.add(this.pickedPlayers.get(1));
            this.pickedPlayers.remove(1);
        }
        PrimeFaces.current().executeScript("PF('selecaoDeCapitaesDialog').hide();");
    }
}
