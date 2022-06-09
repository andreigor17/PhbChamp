/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.champ.Utilitario;

import br.com.champ.Enums.TipoPickBan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class PickBanUtils {

    public static List<String> gerarListaPB(int md) {
        String ban = "BAN";
        String pick = "PICK";
        List<String> pb = new ArrayList<>();
        if (md == 1) {
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(pick);
        } else if (md == 2) {
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(pick);
            pb.add(pick);
        } else if (md == 3) {
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(pick);
        } else if (md == 5) {
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(ban);
            pb.add(pick);
        } else {
            return null;
        }
        return pb;
    }

}
