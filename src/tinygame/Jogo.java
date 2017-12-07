package tinygame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Jogo extends JFrame {
    
    ImageIcon s_left = new ImageIcon(this.getClass().getResource("Snail_l.png"));
    ImageIcon s_right = new ImageIcon(this.getClass().getResource("Snail_r.png"));
    ImageIcon background_img = new ImageIcon(this.getClass().getResource("background.png"));
    
    JLabel back_img = new JLabel(background_img);
    JLabel snail = new JLabel(s_left);
    
    int pos_x = 400;
    int pos_y = 400;
    

    public Jogo(){
        editarJanela();
        editarComponentes();
        
    }
    
    public void editarComponentes(){
        back_img.setBounds(0, 0, 826, 600);
        snail.setBounds(pos_x, pos_y, 200, 135);
    }
    
    public void editarJanela() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(826, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        add(snail);
        add(back_img);
        addMovimento();
    }
    

    public void addMovimento(){
        addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==39&&pos_x<=626){
                    snail.setIcon(s_right);
                    pos_x += 10;
                }
                if(e.getKeyCode()==37&&pos_x>=0){
                    snail.setIcon(s_left);
                    pos_x -= 10;
                }
                if(e.getKeyCode()==38){
                    new Pulo().start();
                    
                }
                if(snail.getY()>=600){
                    pos_y = 600;
                }
                snail.setBounds(pos_x , pos_y , 200, 135);
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
    }
    
    public class Pulo extends Thread{
        public void run(){
            
            int speed = 40;
            
            while(snail.getY()>=100&&speed>0){
                try{sleep(20);}catch(Exception erro){}
                snail.setBounds(pos_x, snail.getY()-speed , 200, 135);
                pos_y = snail.getY();
                speed-=2;
            }
            speed = 0;
            while(snail.getY()<=300||snail.getY()>=100){
                try{sleep(20);}catch(Exception erro){}
                snail.setBounds(pos_x, snail.getY()+speed , 200, 135);
                pos_y = snail.getY();
                speed+=2;
            }
        }
    }



    public static void main(String[] args){

        Jogo jogo = new Jogo();

    }
}