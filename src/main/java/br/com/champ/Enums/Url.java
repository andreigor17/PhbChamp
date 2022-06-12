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

    SALVAR_PLAYER(1, "/players"),
    ATUALIZAR_PLAYER(2, "/players/"),
    SALVAR_TIME(3, "/teams"),
    ATUALIZAR_TIME(4, "/teams/"),
    SALVAR_CAMPEONATO(5, "/campeonatos"),
    ATUALIZAR_CAMPEONATO(6, "/campeonatos/"),
    SALVAR_PARTIDA(7, "/partidas"),
    ATUALIZAR_PARTIDA(8, "/partidas/"),
    SALVAR_ESTATISTICA(9, "/estatisticas"),
    ATUALIZAR_ESTATISTICA(10, "/estatisticas/"),
    SALVAR_ITEM_PARTIDA(11, "/itemPartidas"),
    ATUALIZAR_ITEM_PARTIDA(12, "/itemPartidas"),
    SALVAR_MAPA(13, "/mapas"),
    ATUALIZAR_MAPA(14, "/mapas");

    private String nome;
    private Integer valor;

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    private Url(Integer valor, String nome) {
        this.nome = nome;
        this.valor = valor;
    }

}
