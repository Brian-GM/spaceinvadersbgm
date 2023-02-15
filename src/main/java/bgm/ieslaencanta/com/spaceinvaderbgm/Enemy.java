package bgm.ieslaencanta.com.spaceinvaderbgm;

import bgm.ieslaencanta.com.spaceinvaderbgm.Bullet;
import bgm.ieslaencanta.com.spaceinvaderbgm.Game;
import bgm.ieslaencanta.com.spaceinvaderbgm.Point2D;
import bgm.ieslaencanta.com.spaceinvaderbgm.Ship;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import java.awt.Toolkit;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author brian
 */
public class Enemy {

    private Point2D posicion;
    private int heigth = 2;
    private int width = 6;
    private Bullet bullets[];
    private static int max_bullets = 3;
    private static int topehorizontal_max = 200; //500
    private int topehorizontal = topehorizontal_max;
    private boolean vivo = true;
    private boolean bajar=false;
    private boolean borde = true;
    private String cartoon[] = {
        "⣆⡵⣤⡴⣅⡆",
        "⢘⠟⠛⠛⢟⠀",
        "      "
    };
        private String cartoon2[] = {
     
          "⢀⡴⣿⢦  ",
          "⢈⢝⠭⡫⡁  ",
          "      "
        
    };
     private static int topevertical_max = 200; //500
    private int topevertical = topevertical_max;
    private TextColor color;
    private TextColor backgroundcolor;

    public Enemy() {
        this.posicion = new Point2D();
        this.bullets = new Bullet[Enemy.max_bullets];
    }

    public Enemy(Point2D posicion) {
        this.posicion = posicion;
        this.bullets = new Bullet[Enemy.max_bullets];
        //this.init();
    }

    public Enemy(int x, int y) {
        this.posicion = new Point2D(x, y);
        this.bullets = new Bullet[Enemy.max_bullets];
        //this.init();
    }

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

    /**
     * @return the vivo
     */
    public boolean isVivo() {
        return vivo;
    }

    /**
     * @param vivo the vivo to set
     */
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int x, int y) {
        this.posicion = posicion;
    }

    public void paint(Screen s) {
        char c1;
        for (int i = 0; i < this.heigth; i++) {
            for (int j = 0; j < this.width; j++) {
                c1 = this.cartoon[i].charAt(j);
                s.setCharacter(this.posicion.getX() + j, this.posicion.getY() + i,
                        new TextCharacter(c1, TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
            }
        }
    }
        
             public void paint2(Screen s) {
        char c2;
        for (int i = 0; i < this.heigth; i++) {
            for (int j = 0; j < this.width; j++) {
                c2 = this.cartoon2[i].charAt(j);
                s.setCharacter(this.posicion.getX() + j, this.posicion.getY() + i,
                        new TextCharacter(c2, TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
            }
        }
    
            
        for (int i = 0; i < this.bullets.length; i++) {
            if (this.bullets[i] != null) {
                this.bullets[i].paint(s);

            }

        }
             }
    

    public void shoot() {
        this.bullets[0] = new Bullet(this.posicion.getX() + this.width / 2,
                this.posicion.getY() - 1);

    }

    public void movebullet() {
        for (int i = 0; i < this.bullets.length; i++) {
            if (this.bullets[i] != null) {
                this.bullets[i].moveVertical(-1, 0, Game.Rows);

            }
        }

    }

    public void moveHorizontal(int incX, int minX, int maxX) {
        this.topehorizontal--;
        if (this.topehorizontal == 0) {
            this.topehorizontal = this.topehorizontal_max;
            if (this.getPosicion().getX() + incX >= minX && this.getPosicion().getX() + incX + this.getWidth() <= maxX) {
                this.getPosicion().addX(incX);

            } else {
            }

        }
    }

    public void moveVertical(int incY, int minY, int maxY) {

        if (this.getPosicion().getY() + incY >= minY && this.getPosicion().getY() + incY + this.getWidth() <= maxY) {
            this.getPosicion().addY(incY);

        } else {
        }

    }

    public void moveEnemy() {
        if (this.getPosicion().getX() == 0) {
            borde = false;
            bajar = true;
            
        } else if (this.getPosicion().getX() == Game.Columns - this.width) {
            borde = true;
            bajar = true;

           // moveVertical(+1, 0, Game.Rows);

        }
        if(bajar == true){
             this.topevertical--;
        if (this.topevertical == 0) {
            this.topevertical = this.topevertical_max;
                        moveVertical(+4, 0, Game.Rows);
                        bajar=false;
        }
        
        }
        if (vivo == true && borde == true) {
            moveHorizontal(-1, 0, Game.Columns);
        } else {
            moveHorizontal(+1, 0, Game.Columns);

        }
    }

}
