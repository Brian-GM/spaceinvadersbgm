/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bgm.ieslaencanta.com.spaceinvaderbgm;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 *
 * @author DAWTarde
 */
public class Wall {

    private Point2D posicion;
    private int heigth = 2;
    private int width = 7;
    private char cartoon[][] = {
        {'M', 'M', 'M', 'M', 'M', 'M', 'M'},
        {'M', 'M', 'M', 'M', 'M', 'M', 'M'}
    };

    public Wall() {
    }

    public Wall(Point2D posicion) {
        this.posicion = posicion;
    }

    public Wall(int x, int y) {
        this.posicion = new Point2D(x, y);

    }

    /**
     * @return the posicion
     */
    public Point2D getPosicion() {
        return posicion;
    }

    public void paint(Screen s) {
        char c;
        for (int i = 0; i < this.heigth; i++) {
            for (int j = 0; j < this.width; j++) {
                c = this.cartoon[i][j];
                s.setCharacter(this.getPosicion().getX() + j, this.getPosicion().getY() + i,
                        new TextCharacter(c, TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));

            }
        }

    }
    public boolean collision(Bullet b) {
        boolean collision = false;
        int coordenadax, coordenaday;
        if (this.posicion.getY() <= b.getPosicion().getY()
                && this.posicion.getY() + this.heigth > b.getPosicion().getY()
                && this.posicion.getX() <= b.getPosicion().getX()
                && this.posicion.getX() + this.width >b.getPosicion().getX()) {
            coordenadax = b.getPosicion().getX() - this.posicion.getX();
            coordenaday = b.getPosicion().getY() - this.posicion.getY();

            if (this.cartoon[coordenaday][coordenadax] != ' ') {
                collision = true;
                this.cartoon[coordenaday][coordenadax]= ' ';

            }
        }
        return collision;
    }

}
