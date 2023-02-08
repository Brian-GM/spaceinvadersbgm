/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bgm.ieslaencanta.com.spaceinvaderbgm;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 *
 * @author DAWTarde
 */
public class Ship {

    private Point2D posicion;
    private int heigth = 2;
    private int width = 4;
    private static int max_bullets=3;
    private TextColor color;
    private TextColor backgroundcolor;
    private TextCharacter shipsymbol;
    private Bullet bullets[];

    public Ship() {
        this.posicion = new Point2D();
        this.init();

    }

    public Ship(Point2D posicion) {
        this.posicion = posicion;
        this.init();
    }
    public Ship(int x, int y) {
        this.posicion = posicion;
        this.init();
    }
    
    private void init() {
        this.color = TextColor.ANSI.RED;
        this.backgroundcolor = TextColor.ANSI.BLACK;
        this.shipsymbol = TextCharacter.fromCharacter(Symbols.BLOCK_MIDDLE)[0].withForegroundColor(this.color).withBackgroundColor(this.backgroundcolor);
    }

    public void paint(Screen s) {
        for(int i=0;i<this.heigth;i++){
            for (int j=0;j<this.width;j++){
                 s.setCharacter(this.posicion.getX(), this.posicion.getY(),new TexCharacter('*',texcolor));

            }
        }
    } 
       

    public void moveHorizontal(int incX, int minX, int maxX) {
        if (this.posicion.getX() + incX >= minX && this.posicion.getX()+ incX+this.width <= maxX) {
            this.posicion.addX(incX);
        }else{
            
        }
    }

}
