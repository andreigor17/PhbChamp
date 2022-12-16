/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Utilitario;

import br.com.champ.Enums.Funcoes;
import br.com.champ.Enums.Game;
import br.com.champ.Enums.StatusCamp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class Enums implements Serializable{
    
    public List<SelectItem> funcoes() {
        List<SelectItem> itens = new ArrayList<SelectItem>();
        for (Funcoes funcao : Funcoes.values()) {
            itens.add(new SelectItem(funcao, funcao.getNome()));
        }
        return itens;
    }
    
    public List<SelectItem> statusCamp() {
        List<SelectItem> itens = new ArrayList<SelectItem>();
        for (StatusCamp status : StatusCamp.values()) {
            itens.add(new SelectItem(status, status.getNome()));
        }
        return itens;
    }
    
    public List<SelectItem> games() {
        List<SelectItem> itens = new ArrayList<SelectItem>();
        for (Game game : Game.values()) {
            itens.add(new SelectItem(game, game.getNome()));
        }
        return itens;
    }
    
}
