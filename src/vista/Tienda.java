/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.CompraDao;
import dao.BoosterDao;
import dao.JugadorDao;
import entidades.Booster;
import entidades.Compra;
import entidades.Jugador;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
    private JButton comprarEst, comprarLlan, comprarComod, confirmar;
    private Jugador jugador;
    private List<Booster> boosters;
    
    public Tienda(Jugador jugador){
        super("Tienda");
        this.jugador=jugador;
        BoosterDao b = new BoosterDao();
        boosters = b.findAll();
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
        confirmar = new JButton("confirmar");
        cartera= new JTextField();
        cartera.setEditable(false);
        cartera.setText(Integer.toString(jugador.getCartera()));
        Container container = getContentPane();
        
        for (int i=0; i<3;i++){
            labels[i]=new JLabel();
            labels[i].setIcon(new ImageIcon(getClass().getResource(boosters.get(i).getNombre()+".png")));
            labels[i].setBounds(i*150+20,70, 100, 100);
            container.add(labels[i]);
            
            descripciones[i]=new JLabel("<html>"+boosters.get(i).getNombre()+"<br>"+"precio: "
                    +boosters.get(i).getPrecio()+"</html>");
            descripciones[i].setBounds(i*150+20, 180, WIDTH, 40);
            container.add(descripciones[i]);
        }
        
        cartera.setBounds(470, 10, WIDTH, HEIGHT);
        comprarEst.setBounds(20, 220, WIDTH, HEIGHT);
        comprarComod.setBounds(170, 220, WIDTH, HEIGHT);
        comprarLlan.setBounds(310, 220, WIDTH, HEIGHT);
        confirmar.setBounds(470, 510, WIDTH, HEIGHT);
        container.add(cartera);
        container.add(comprarEst);
        container.add(comprarComod);
        container.add(comprarLlan);
        container.add(confirmar);
        setSize(600,600);
        setVisible(true);
    }

    
    private void eventos(){
    
        comprarEst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int precio =150;
                if(jugador.verificarFondos(precio)){
                    CompraDao compraDao= new CompraDao();
                    JugadorDao jugadorDao= new JugadorDao();
                    compraDao.insert(new Compra(jugador.getIdJugador(),1));
                    jugador.setCartera(jugador.pagar(precio));
                    cartera.setText(Integer.toString(jugador.getCartera()));
                    jugadorDao.update(jugador);
                }
            }
        });
        
        comprarComod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int precio=300;
                if(jugador.verificarFondos(precio)){
                    CompraDao compraDao= new CompraDao();
                    JugadorDao jugadorDao= new JugadorDao();
                    compraDao.insert(new Compra(jugador.getIdJugador(),2));
                    jugador.setCartera(jugador.pagar(precio));
                    cartera.setText(Integer.toString(jugador.getCartera()));
                    jugadorDao.update(jugador);
                }
            }
        });

        comprarLlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int precio=200;
                if(jugador.verificarFondos(precio)){
                    CompraDao compraDao= new CompraDao();
                    JugadorDao jugadorDao= new JugadorDao();
                    compraDao.insert(new Compra(jugador.getIdJugador(),3));
                    jugador.setCartera(jugador.pagar(precio));
                    cartera.setText(Integer.toString(jugador.getCartera()));
                    jugadorDao.update(jugador);
                }
            }
        });
    }
    
    
}
