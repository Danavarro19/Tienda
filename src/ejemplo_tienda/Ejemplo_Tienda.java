/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_tienda;

import dao.boosterDao;
import dao.jugadorDao;
import entidades.Booster;
import entidades.Jugador;
import java.util.ArrayList;
import vista.Tienda;

/**
 *
 * @author intel
 */
public class Ejemplo_Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boosterDao b = new boosterDao();
        jugadorDao j = new jugadorDao();
        ArrayList<Booster> boosters=b.getBoosters();
        for (Booster bst: boosters){
            System.out.println(bst.getNombre());
        }
        Jugador jugador = j.findAll().get(1);
        Tienda tienda = new Tienda(jugador);
    }
    
}
