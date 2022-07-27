import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Layout extends JPanel implements ActionListener,KeyListener{
    private boolean play=false;
    private int score=0;
    private int totalbrick =21;
    private Timer timer;
    private int delay =8;
    private int playerX =310;
    private int spheresiteX =120;
    private int spheresiteY =350;
    private int spherelocX =-1;
    private int spherelocY = -2;
    private Guide map;
    public Layout() {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer=new Timer(delay,this);
        timer.start();
        map=new Guide(3,7);
    }

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);

        map.draw((Graphics2D) g);

        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,590,30);
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        g.setColor(Color.yellow);
        g.fillOval(spheresiteX,spheresiteY,20,20);

        if (totalbrick==0){
            play = false;
           spherelocX=-2;
            spherelocY=-1;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GAME OVER SCORE:"+score,190,300);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart",190,340);
        }



        if(spheresiteY > 570) {
            play=false;
           spherelocX=0;
            spherelocY=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GAME OVER SCORE:"+score,190,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart",230,350);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play){
            if(new Rectangle(spheresiteX,spheresiteY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                spherelocY=-spherelocY;
            }
            A:for(int i=0;i<map.map.length;i++) {
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j*map.brickwidth+80;
                        int brickY=i*map.brickheight+50;
                        int brickwidth=map.brickwidth;
                        int brickheight= map.brickheight;

                        Rectangle rect = new Rectangle(brickX,brickY,brickwidth,brickheight);
                        Rectangle ballrect = new Rectangle(spheresiteX,spheresiteY,20,20);
                        Rectangle brickrect=rect;

                        if (ballrect.intersects(brickrect)){
                            map.setbrickvalue(0,i,j);
                            totalbrick--;
                            score+=5;
                            if(spheresiteX+19<=brickrect.x ||spheresiteX+1>=brickrect.x+brickrect.width) {
                               spherelocX= -spherelocX;
                            }
                            else{
                                spherelocY= -spherelocY;
                            }
                            break A;
                        }
                    }
                }
            }

            spheresiteX +=spherelocX;
            spheresiteY+=spherelocY;
            if(spheresiteX<0){
               spherelocX=-spherelocX;
            }

            if(spheresiteY<0){
                spherelocY=-spherelocY;
            }
            if(spheresiteX >670){
               spherelocX=-spherelocX;
            }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(playerX>=600){
                playerX=600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<10){
                playerX=10;
            }
            else{
                moveLeft();
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            if(!play){
                spheresiteX=120;
                spheresiteY=350;
               spherelocX=-1;
                spherelocY=-2;
                score=0;
                playerX=310;
                totalbrick=21;
                map=new Guide(3,7);
                repaint();
            }
        }

    }
    public void moveRight(){
        play=true;
        playerX+=20;
    }
    public void moveLeft(){
        play=true;
        playerX-=20;
    }


}


