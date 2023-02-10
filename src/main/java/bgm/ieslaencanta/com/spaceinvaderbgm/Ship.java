/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bgm.ieslaencanta.com.spaceinvaderbgm;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import java.awt.Toolkit;

/**
 *
 * @author DAWTarde
 */
public class Ship {

    private Point2D posicion;
    private int heigth = 2;
    private int width = 6;
    private Bullet bullets[];
    private static int max_bullets = 3;
    private String cartoon[] = {
        "⢀⣀⣾⣷⣀⡀",
        "⣿⣿⣿⣿⣿⣿"
    };
    private TextColor color;
    private TextColor backgroundcolor;

    public Ship() {
        this.posicion = new Point2D();
        this.bullets = new Bullet[Ship.max_bullets];
    //  this.init();

    }

    public Ship(Point2D posicion) {
        this.posicion = posicion;
        this.bullets = new Bullet[Ship.max_bullets];

      //this.init();
    }

    public Ship(int x, int y) {
        this.posicion = new Point2D(x,y);
        this.bullets = new Bullet[Ship.max_bullets];
           //this.init();
    }
/*
    private void init() {
        this.color = TextColor.ANSI.RED;
        this.backgroundcolor = TextColor.ANSI.BLACK;
       this.cartoon = TextCharacter.fromCharacter()[0].withForegroundColor(this.color).withBackgroundColor(this.backgroundcolor);
    }
*/
      /**
     * @return the posicion
     */
    public Point2D getPosicion() {
        return posicion;
    }

    /**
     * @return the heigth
     */
    public int getHeigth() {
        return heigth;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the bullets
     */
    public Bullet[] getBullets() {
        return bullets;
    }
    
    public void paint(Screen s) {
        char c;
        for (int i = 0; i < this.heigth; i++) {
            for (int j = 0; j < this.width; j++) {
                c = this.cartoon[i].charAt(j);
                s.setCharacter(this.posicion.getX() + j, this.posicion.getY() + i, 
                       new TextCharacter(c,TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));

            }
        }
        for (int i=0;i<this.bullets.length;i++){
            if (this.bullets[i]!=null){
            this.bullets[i].paint(s);
            }
        }
    }
public void shoot(){
    this.bullets[0]=new Bullet(this.posicion.getX()+this.width/2,
    this.posicion.getY()-1);
    
}
public void movebullet(){
   for (int i=0;i<this.bullets.length;i++){
       if(this.bullets[i] != null){
           this.bullets[i].moveVertical(-1, 0, Game.Rows);

       }
   }
    
}
    public void moveHorizontal(int incX, int minX, int maxX) {
        if (this.getPosicion().getX() + incX >= minX && this.getPosicion().getX() + incX + this.getWidth() <= maxX) {
            this.getPosicion().addX(incX);
        } else {
Toolkit.getDefaultToolkit().beep();
        }
    }

  

}
