package br.com.champ.Manager;

import br.com.champ.Servico.CampeonatoServico;
import java.io.Serializable;
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
public class ManagerIndex implements Serializable{
    @EJB
    CampeonatoServico campServico;
    
    @PostConstruct
    public void init() {
        
        
    }
    
    public void instanciar(){
        
        
    }
    
}
