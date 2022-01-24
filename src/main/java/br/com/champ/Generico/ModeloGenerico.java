/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Generico;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 *
 * @author andre
 */
@MappedSuperclass
public abstract class ModeloGenerico implements Serializable {


    private Long id;

    /**
     * Quando false, o objeto encontra-se excluido/cancelado. Caso contrário
     * encontra-se ativo
     */
    private Boolean ativo = true;
    /**
     * Controle de versionamento da entidade, faz o controle de alterações
     * concorrentes
     */
    private int versao;

    public ModeloGenerico() {
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

}
