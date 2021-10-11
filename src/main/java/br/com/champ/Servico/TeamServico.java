package br.com.champ.Servico;

import br.com.champ.Generico.ServicoGenerico;
import javax.ejb.Stateless;
import br.com.champ.Modelo.Team;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author andre
 */
@Stateless
public class TeamServico extends ServicoGenerico<Team>{
    
    private Team team;
    
    public void instanciar(){
        this.team = new Team();
    }
    
    public TeamServico(){
        super(Team.class);
    }
    
    public void salvar(Team team) {
        if (team.getId() == null) {
            save(team);
        } else {
            update(team);
        }
    }

    @Override
    public void delete(Team team) {
        team.setAtivo(false);
        super.remove(team);
    }

    public List<Team> pesquisar(Team team) {

        String sql = "select t from Team t where ";

        sql += "t.ativo = true";

        Query query = getEntityManager().createQuery(sql);

        return query.getResultList();

    }
    

    public List<Team> autoCompleteTime() {
        return buscaTimes();
    }

    private List<Team> buscaTimes() {
        String sql = "select team from Team team where team.ativo = true";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
