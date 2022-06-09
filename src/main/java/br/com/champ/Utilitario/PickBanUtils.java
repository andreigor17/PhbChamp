/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.champ.Utilitario;

import br.com.champ.Enums.TipoPickBan;
import br.com.champ.Modelo.Team;
import br.com.champ.vo.PickBanVo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class PickBanUtils {

    public static List<PickBanVo> gerarListaPB(Team team1, Team team2, int md) {
        List<PickBanVo> pickBanVo = new ArrayList<>();

        if (md == 1) {
            PickBanVo pb1 = new PickBanVo(team1, TipoPickBan.BAN);
            pickBanVo.add(pb1);
            PickBanVo pb2 = new PickBanVo(team2, TipoPickBan.BAN);
            pickBanVo.add(pb2);
            PickBanVo pb3 = new PickBanVo(team1, TipoPickBan.BAN);
            pickBanVo.add(pb3);
            PickBanVo pb4 = new PickBanVo(team2, TipoPickBan.BAN);
            pickBanVo.add(pb4);
            PickBanVo pb5 = new PickBanVo(team1, TipoPickBan.BAN);
            pickBanVo.add(pb5);
            PickBanVo pb6 = new PickBanVo(team2, TipoPickBan.BAN);
            pickBanVo.add(pb6);
            PickBanVo pb7 = new PickBanVo(team1, TipoPickBan.PICK);
            pickBanVo.add(pb7);

        } else if (md == 2) {

        } else if (md == 3) {
            PickBanVo pb1 = new PickBanVo(team1, TipoPickBan.BAN);
            pickBanVo.add(pb1);
            PickBanVo pb2 = new PickBanVo(team2, TipoPickBan.BAN);
            pickBanVo.add(pb2);
            PickBanVo pb3 = new PickBanVo(team1, TipoPickBan.PICK);
            pickBanVo.add(pb3);
            PickBanVo pb4 = new PickBanVo(team2, TipoPickBan.PICK);
            pickBanVo.add(pb4);
            PickBanVo pb5 = new PickBanVo(team1, TipoPickBan.BAN);
            pickBanVo.add(pb5);
            PickBanVo pb6 = new PickBanVo(team2, TipoPickBan.BAN);
            pickBanVo.add(pb6);
            PickBanVo pb7 = new PickBanVo(team1, TipoPickBan.PICK);
            pickBanVo.add(pb7);

        } else if (md == 5) {

        } else {
            return null;
        }
        return pickBanVo;
    }

}
