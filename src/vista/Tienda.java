/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.CompraDao;
import dao.boosterDao;
import dao.jugadorDao;
import entidades.Booster;
import entidades.Compra;
import entidades.Jugador;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ERROR;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author intel
 */
public class Tienda extends JFrame{
    
    private final int WIDTH=100;
    private final int HEIGHT=20;
    private JLabel[] labels;
    private JTextField cartera;
    private JLabel[] descripciones;
    private JButton comprarEst, comprarLlan, comprarComod;
    private Jugador jugador;
    private ArrayList<Booster> boosters;
    
    public Tienda(Jugador jugador){
        super("Tienda");
        this.jugador=jugador;
        boosterDao b = new boosterDao();
        boosters = b.getBoosters();
        initComponents();
        eventos();
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        labels = new JLabel[3];
        descripciones = new JLabel[3];
        comprarEst = new JButton("comprar");
        comprarComod = new JButton("comprar");
        comprarLlan = new JButton("comprar");
        cartera= new JTextField();
        cartera.setEditable(false);
        cartera.setText(Integer.toString(jugador.getCartera()));
        Container container = getContentPane();
        
        for (int i=0; i<3;i++){
            labels[i]=new JLabel();
            labels[i].setIcon(new ImageIcon(getClass().getResource(boosters.get(i).getNombre()+".png")));
            labels[i].setBounds(i*150+30,70, 100, 100);
            container.add(labels[i]);
            
            descripciones[i]=new JLabel("<html>"+boosters.get(i).getNombre()+"<br>"+"precio: "
                    +boosters.get(i).getPrecio()+"</html>");
            descripciones[i].setBounds(i*150+30, 180, WIDTH, 40);
            container.add(descripciones[i]);
        }
        
        cartera.setBounds(470, 10, WIDTH, HEIGHT);
        comprarEst.setBounds(30, 220, WIDTH, HEIGHT);
        comprarComod.setBounds(180, 220, WIDTH, HEIGHT);
        comprarLlan.setBounds(320, 220, WIDTH, HEIGHT);
        container.add(cartera);
        container.add(comprarEst);
        container.add(comprarComod);
        container.add(comprarLlan);
        setSize(600,600);
        setVisible(true);
    }

    
    private void eventos(){
    
        comprarEst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompraDao compraDao= new CompraDao();
                compraDao.insert(new Compra(jugador.getIdJugador(),1));
                jugador.setCartera(jugador.pagar(150));
                cartera.setText(Integer.toString(jugador.getCartera()));
            }
        });
        
        comprarComod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompraDao compraDao= new CompraDao();
                compraDao.insert(new Compra(jugador.getIdJugador(),2));
                jugador.setCartera(jugador.pagar(300));
                cartera.setText(Integer.toString(jugador.getCartera()));
            }
        });

        comprarLlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompraDao compraDao= new CompraDao();
                compraDao.insert(new Compra(jugador.getIdJugador(),3));
                jugador.setCartera(jugador.pagar(200));
                cartera.setText(Integer.toString(jugador.getCartera()));
            }
        });
    }
    
    
}
