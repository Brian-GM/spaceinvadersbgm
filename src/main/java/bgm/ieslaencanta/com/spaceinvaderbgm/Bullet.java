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
public class Bullet {
    private Point2D posicion;
    private TextColor color;
    private TextColor backgroundcolor;
    private TextCharacter bulletsymbol;

    public Bullet() {
        this.posicion=new Point2D();
        this.init();
    }

    public Bullet(Point2D posicion) {
        this.posicion = posicion;
    }
        public Bullet(int x,int y) {
        this.posicion = new Point2D(x,y);

        }
        private void init(){
            this.color = TextColor.ANSI.GREEN;
            this.backgroundcolor = TextColor.ANSI.BLACK;
            this.bulletsymbol = TextCharacter.fromCharacter(Symbols.ARROW_UP)[0].withForegroundColor(this.color).withBackgroundColor(this.backgroundcolor);
        }
        public void paint(Screen s){
            s.setCharacter(this.posicion.getX(),this.posicion.getY(),this.bulletsymbol);
        }
public void moveVertical(int incy,int miny,int maxy){
    if (this.posicion.getY()+incy>=miny && this.posicion.getY()+incy<=maxy ){
        this.posicion.addY(incy);
    }
}
    
}
