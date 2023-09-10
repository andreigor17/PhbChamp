/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Enums;

/**
 *
 * @author andre
 */
public enum Url {

    SALVAR_PLAYER("/players"),
    ATUALIZAR_PLAYER("/players/"),
    SALVAR_TIME("/teams"),
    ATUALIZAR_TIME("/teams/"),
    SALVAR_CAMPEONATO("/campeonatos"),
    ATUALIZAR_CAMPEONATO("/campeonatos/"),
    EXCLUIR_CAMPEONATO("/campeonatos/excluir/"),
    CAMPEONATOS_PLAYERS("/campeonatos/camp_players"),
    SALVAR_PARTIDA("/partidas"),
    ATUALIZAR_PARTIDA("/partidas/"),
    SALVAR_ESTATISTICA("/estatisticas"),
    ATUALIZAR_ESTATISTICA("/estatisticas/"),
    SALVAR_ITEM_PARTIDA("/itemPartidas"),
    ATUALIZAR_ITEM_PARTIDA("/itemPartidas/"),
    SALVAR_MAPA("/mapas"),
    ATUALIZAR_MAPA("/mapas"),
    SALVAR_JOGO("/jogo"),
    ATUALIZAR_JOGO("/jogo"),
    EXCLUIR_JOGO("/jogo/excluir/"),
    SALVAR_ANEXO("/anexos"),
    ATUALIZAR_ANEXO("/anexos"),
    EXCLUIR_ANEXO("/anexos/excluir/"),
    SALVAR_VERSAO("/versao"),
    ATUALIZAR_VERSAO("/versao"),
    EXCLUIR_VERSAO("/versao/excluir/");

    private String nome;    

    public String getNome() {
        return nome;
    }


    private Url(String nome) {
        this.nome = nome;        
    }

}
