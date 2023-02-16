package br.com.champ.Manager;

import br.com.champ.Enums.StatusCamp;
import br.com.champ.Enums.Url;
import br.com.champ.Modelo.Campeonato;
import br.com.champ.Modelo.Estatisticas;
import br.com.champ.Modelo.ItemPartida;
import br.com.champ.Modelo.Partida;
import br.com.champ.Modelo.Player;
import br.com.champ.Modelo.Team;
import br.com.champ.Servico.CampeonatoServico;
import br.com.champ.Servico.EstatisticaServico;
import br.com.champ.Servico.PartidaServico;
import br.com.champ.Servico.PlayerServico;
import br.com.champ.Servico.TeamServico;
import br.com.champ.Utilitario.FacesUtil;
import br.com.champ.Utilitario.Mensagem;
import br.com.champ.Utilitario.PartidaUtils;
import br.com.champ.Utilitario.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerCriarCampeonato implements Serializable {
    
    @EJB
    TeamServico teamServico;
    @EJB
    PlayerServico playerServico;
    @EJB
    CampeonatoServico campeonatoServico;
    @EJB
    EstatisticaServico estatisticaServico;
    @EJB
    PartidaServico partidaServico;
    @EJB
    EstatisticaServico estatisticasServico;
    private Campeonato camp;
    private List<Campeonato> camps;
    private List<Team> times;
    private Team time;
    private List<Estatisticas> estatisticasTime;
    private Estatisticas estatistica;
    private Date dataCamp;
    private Date dataFinal;
    private int qtdItensPartidas;
    private List<ItemPartida> itemPartidas;
    private Partida partida;
    private List<Estatisticas> estsGerais;
    private boolean skip;
    private Player membro;
    private List<Player> membros;
    
    @PostConstruct
    public void init() {
        instanciar();
        
        String visualizarCampId = FacesUtil
                .getRequestParameter("id");
        
        if (visualizarCampId != null && !visualizarCampId.isEmpty()) {
            this.camp = this.campeonatoServico.buscaCamp(Long.parseLong(visualizarCampId));
        }
        
    }
    
    public void instanciar() {
        this.camp = new Campeonato();
        this.camps = new ArrayList<>();
        this.time = new Team();
        this.times = new ArrayList<>();
        this.estatisticasTime = new ArrayList<>();
        this.dataCamp = new Date();
        this.itemPartidas = new ArrayList<>();
        this.partida = new Partida();
        this.estsGerais = new ArrayList<>();
        this.membro = new Player();
        this.membros = new ArrayList<>();
        
    }
    
    public Campeonato getCamp() {
        return camp;
    }
    
    public void setCamp(Campeonato camp) {
        this.camp = camp;
    }
    
    public List<Campeonato> getCamps() {
        return camps;
    }
    
    public void setCamps(List<Campeonato> camps) {
        this.camps = camps;
    }
    
    public List<Team> getTimes() {
        return times;
    }
    
    public void setTimes(List<Team> times) {
        this.times = times;
    }
    
    public Team getTime() {
        return time;
    }
    
    public void setTime(Team time) {
        this.time = time;
    }
    
    public List<Estatisticas> getEstatisticasTime() {
        return estatisticasTime;
    }
    
    public void setEstatisticasTime(List<Estatisticas> estatisticasTime) {
        this.estatisticasTime = estatisticasTime;
    }
    
    public Estatisticas getEstatistica() {
        return estatistica;
    }
    
    public void setEstatistica(Estatisticas estatistica) {
        this.estatistica = estatistica;
    }
    
    public Date getDataCamp() {
        return dataCamp;
    }
    
    public void setDataCamp(Date dataCamp) {
        this.dataCamp = dataCamp;
    }
    
    public int getQtdItensPartidas() {
        return qtdItensPartidas;
    }
    
    public void setQtdItensPartidas(int qtdItensPartidas) {
        this.qtdItensPartidas = qtdItensPartidas;
    }
    
    public List<ItemPartida> getItemPartidas() {
        return itemPartidas;
    }
    
    public void setItemPartidas(List<ItemPartida> itemPartidas) {
        this.itemPartidas = itemPartidas;
    }
    
    public Partida getPartida() {
        return partida;
    }
    
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    public List<Estatisticas> getEstsGerais() {
        return estsGerais;
    }
    
    public void setEstsGerais(List<Estatisticas> estsGerais) {
        this.estsGerais = estsGerais;
    }
    
    public void adicionarMembro() {
        this.membros.add(this.membro);
        this.membro = new Player();
        
    }
    
    public void adicionarTime() {
        this.times.add(this.time);
        this.time = new Team();
        
    }
    
    public List<Player> autoCompletarPlayer() {
        return playerServico.autoCompletePessoa();
    }
    
    public void salvarCampeonato() throws Exception {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        String dataFormatada = formatter.format(this.dataCamp);
        String dataFormatadaFinal = formatter.format(this.dataFinal);
        
        this.camp.setDataCamp(dataFormatada);
        this.camp.setDataFinal(dataFormatadaFinal);
        
        this.camp.setTeams(this.times);
        this.camp.setPlayers(this.membros);
        
        this.camp.setStatus(StatusCamp.EM_ANDAMENTO);
        
        
        this.camp = campeonatoServico.save(this.camp, null, Url.SALVAR_CAMPEONATO.getNome());
        this.camp = campeonatoServico.buscaCamp(this.camp.getId());
        this.camp.setPartidas(gerarPartidas(this.camp.getId(), this.camp));
                
        this.camp = campeonatoServico.save(this.camp, this.camp.getId(), Url.ATUALIZAR_CAMPEONATO.getNome());
        Mensagem.successAndRedirect("Camp salvo", "visualizarCampeonato.xhtml?id=" + this.camp.getId());
    }
    
    public List<Team> autoCompletarTime() throws Exception {
        return teamServico.autoCompleteTime();
    }
    
    public void adicionarCamp() {
        this.times.add(this.time);
        this.time = new Team();
        
    }
    
    public List<Partida> gerarPartidas(Long id, Campeonato camp) throws Exception {
        List<Partida> matches = new ArrayList<>();
        Partida match = new Partida();
        if (Utils.isNotEmpty(this.times)) {
            for (int i = 0; i < this.times.size() - 1; i++) {
                for (int j = i + 1; j < this.times.size(); j++) {
                    match = salvarPartidaClassica(this.times.get(i), this.times.get(j), id, camp, null, null);                    
                    matches.add(match);
                }
            }
        }
        if (Utils.isNotEmpty(this.membros)) {
            for (int i = 0; i < this.membros.size() - 1; i++) {
                for (int j = i + 1; j < this.membros.size(); j++) {
                    match = salvarPartidaClassica(null, null, id, camp, this.membros.get(i), this.membros.get(j));                    
                    matches.add(match);
                }
            }
        }
        return matches;
    }
    
    public Partida salvarPartidaClassica(Team t1, Team t2, Long id, Campeonato camp, Player p1, Player p2) {
        try {
                                    
            List<Estatisticas> estsTeam1 = new ArrayList<Estatisticas>();
            List<Estatisticas> estsTeam2 = new ArrayList<Estatisticas>();
            
            Team team1 = t1;
            Team team2 = t2;
            
            if (Utils.isNotEmpty(t1) && Utils.isNotEmpty(t2)) {
                this.itemPartidas = PartidaUtils.gerarPartidasTimes(this.partida, id, team1, team2, this.qtdItensPartidas);
            }
            
            if (Utils.isNotEmpty(p1) && Utils.isNotEmpty(p2)) {
                this.itemPartidas = PartidaUtils.gerarPartidasPlayers(this.partida, id, p1, p2, this.qtdItensPartidas);
            }
            
            this.partida.setItemPartida(this.itemPartidas);
            this.partida.setJogo(camp.getJogo());
            this.partida = partidaServico.salvar(this.partida, null, Url.SALVAR_PARTIDA.getNome());
            
            this.partida = partidaServico.pesquisar(this.partida.getId());
            List<ItemPartida> it = this.partida.getItemPartida();
            
            List<ItemPartida> newItem = new ArrayList<>();
            
            for (ItemPartida ip : this.partida.getItemPartida()) {
                ip.setPartida(this.partida.getId());
                newItem.add(ip);
            }
            
            this.partida.setItemPartida(newItem);
            
            this.partida = partidaServico.salvar(this.partida, this.partida.getId(), Url.ATUALIZAR_PARTIDA.getNome());
            
            if (Utils.isNotEmpty(t1) && Utils.isNotEmpty(t2)) {
                for (ItemPartida i : it) {
                    for (Player playerTime1 : team1.getPlayers()) {
                        Estatisticas estatisticas = new Estatisticas();
                        estatisticas.setPlayer(playerTime1);
                        estatisticas.setTeam(team1);
                        estatisticas.setItemPartida(i);
                        estatisticas.setCampeonato(camp);
                        estsTeam1.add(estatisticas);
                        
                    }
                    this.estsGerais.addAll(estsTeam1);
                    
                    for (Player playerTime2 : team2.getPlayers()) {
                        Estatisticas estatisticas = new Estatisticas();
                        estatisticas.setPlayer(playerTime2);
                        estatisticas.setTeam(team2);
                        estatisticas.setItemPartida(i);
                        estatisticas.setCampeonato(camp);
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
            }
            
            if (Utils.isNotEmpty(p1) && Utils.isNotEmpty(p2)) {
                for (ItemPartida i : it) {
                    
                    Estatisticas estatisticas = new Estatisticas();
                    estatisticas.setPlayer(p1);
                    estatisticas.setItemPartida(i);
                    estatisticas.setCampeonato(camp);
                    estsTeam1.add(estatisticas);
                    
                    this.estsGerais.addAll(estsTeam1);
                    
                    Estatisticas estatisticas2 = new Estatisticas();
                    estatisticas2.setPlayer(p2);
                    estatisticas2.setTeam(team2);
                    estatisticas2.setItemPartida(i);
                    estatisticas2.setCampeonato(camp);
                    estsTeam2.add(estatisticas2);
                    
                    this.estsGerais.addAll(estsTeam2);
                    
                    for (Estatisticas e : this.estsGerais) {
                        estatisticasServico.salvar(e, null, Url.SALVAR_ESTATISTICA.getNome());
                    }
                    
                    estsTeam1 = new ArrayList<Estatisticas>();
                    estsTeam2 = new ArrayList<Estatisticas>();
                    this.estsGerais = new ArrayList<Estatisticas>();
                    
                }
            }
            
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        return this.partida;
    }
    
    public void limpar() {
        instanciar();
    }

//    public void removeCamp() {
//        this.campeonatoServico.delete(this.camp);
//        Mensagem.successAndRedirect("pesquisarCampeonato.xhtml");
//        init();
//    }
    public void pesquisarCamp() throws Exception {
        this.camps = campeonatoServico.pesquisar();
    }
    
    public boolean isSkip() {
        return skip;
    }
    
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
    
    public Date getDataFinal() {
        return dataFinal;
    }
    
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    public Player getMembro() {
        return membro;
    }
    
    public void setMembro(Player membro) {
        this.membro = membro;
    }
    
    public List<Player> getMembros() {
        return membros;
    }
    
    public void setMembros(List<Player> membros) {
        this.membros = membros;
    }
    
}
