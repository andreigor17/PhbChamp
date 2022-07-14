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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private int qtdItensPartidas;
    private List<ItemPartida> itemPartidas;
    private Partida partida;
    private List<Estatisticas> estsGerais;

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

    public void salvarCampeonato() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String dataFormatada = formatter.format(this.dataCamp);

        this.camp.setDataCamp(dataFormatada);

        this.camp.setTeams(this.times);

        this.camp.setStatus(StatusCamp.EM_ANDAMENTO);

        Campeonato c = new Campeonato();
        c = campeonatoServico.save(this.camp, null, Url.SALVAR_CAMPEONATO.getNome());
        System.out.println("id " + c.getId());
        c.setPartidas(gerarPartidas(c.getId(), c));

        for (Team timess : this.camp.getTeams()) {
            this.estatistica = new Estatisticas();
            this.estatistica.setTeam(timess);
            this.estatistica.setCampeonato(c);
            this.estatisticaServico.salvar(estatistica, null, Url.SALVAR_ESTATISTICA.getNome());
            this.estatisticasTime.add(estatistica);
            timess.setEstatisticas(estatisticasTime);
            //this.teamServico.update(timess);

        }

        Campeonato c2 = new Campeonato();
        c2 = campeonatoServico.save(c, c.getId(), Url.ATUALIZAR_CAMPEONATO.getNome());

        System.out.println("id2 " + c2.getId());
        Mensagem.successAndRedirect("Camp salvo", "visualizarCampeonato.xhtml?id=" + c2.getId());
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
        for (int i = 0; i < this.times.size() - 1; i++) {
            for (int j = i + 1; j < this.times.size(); j++) {
                match = salvarPartidaClassica(this.times.get(i), this.times.get(j), id, camp);
                matches.add(match);
            }
        }
        System.out.println("Partidas " + matches.size());
        return matches;
    }

    public Partida salvarPartidaClassica(Team t1, Team t2, Long id, Campeonato camp) {
        try {

            Partida partidaX5 = new Partida();

            List<Estatisticas> estsTeam1 = new ArrayList<Estatisticas>();
            List<Estatisticas> estsTeam2 = new ArrayList<Estatisticas>();

            Team team1 = t1;
            Team team2 = t2;

            this.itemPartidas = PartidaUtils.gerarPartidas(partidaX5, id, team1, team2, this.qtdItensPartidas);
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

        } catch (Exception ex) {
            System.err.println(ex);
        }

        return partida;
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
        this.camps = campeonatoServico.pesquisar(this.camp);
    }

}
