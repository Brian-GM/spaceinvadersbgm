/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bgm.ieslaencanta.com.spaceinvaderbgm;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAWTarde
 */
public class Game {

    private Terminal terminal;
    private Screen screen;
    private boolean exit_key;
    private Bullet bala;
    private Ship ship;
    //Muros
    private Wall walls[];
    private Wall wall;
    private Wall wall2;
    private Wall wall3;
    private Wall wall4;
    //Enemigos fila 1
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;
    private Enemy enemy6;
    private Enemy enemy7;
    private Enemy enemy8;
    private Enemy enemy9;
    //Enemigos fila 2
    private Enemy enemy10;
    private Enemy enemy11;
    private Enemy enemy12;
    private Enemy enemy13;
    private Enemy enemy14;
    private Enemy enemy15;
    private Enemy enemy16;
    private Enemy enemy17;

    public static int Columns = 80;
    public static int Rows = 80;

    public Game() {
        this.exit_key = false;
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(this.terminal);
            this.screen.setCursorPosition(null);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
               
       
        
         

        bala = new Bullet(40, 12);
        ship = new Ship(38, 20);
        enemy1 = new Enemy(0, 1);
        enemy2 = new Enemy(10, 1);
        enemy3 = new Enemy(20, 1);
        enemy4 = new Enemy(30, 1);
        enemy5 = new Enemy(40, 1);
        enemy6 = new Enemy(50, 1);
        enemy7 = new Enemy(60, 1);
        enemy8 = new Enemy(70, 1);
        enemy9 = new Enemy(80, 1);
        //Enemigos fila 2
        enemy10 = new Enemy(5, 8);
        enemy11 = new Enemy(15, 8);
        enemy12 = new Enemy(25, 8);
        enemy13 = new Enemy(35, 8);
        enemy14 = new Enemy(45, 8);
        enemy15 = new Enemy(55, 8);
        enemy16 = new Enemy(65, 8);
        enemy17 = new Enemy(73, 8);
        this.CreteWalls();

    }

    public void loop() {
        int x = 0;
        int y = 0;
        try {
            screen.startScreen();
            screen.clear();
            while (!this.exit_key) {
                x = (int) Math.random() * 80;
                y = (int) Math.random() * 80;

                //se procesa la entrada
                process_input();
                update();
                paint(screen);
                try {
                    Thread.sleep((1 / 60) * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//Al pulsar escape se cierra la terminal y la ventana
            screen.close();
            terminal.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void CreteWalls(){
          this.walls = new Wall[4];
            for (int i=0;i<this.walls.length;i++){
                this.walls[i] = new Wall (new Point2D(((i+1)*15),15));
            }
        }    
    

    private void update() {
        this.ship.movebullet();
        this.enemy1.moveEnemy();
        this.enemy2.moveEnemy();
        this.enemy3.moveEnemy();
        this.enemy4.moveEnemy();
        this.enemy5.moveEnemy();
        this.enemy6.moveEnemy();
        this.enemy7.moveEnemy();
        this.enemy8.moveEnemy();
        this.enemy9.moveEnemy();
        //Enemigos fila 2
        this.enemy10.moveEnemy();
        this.enemy11.moveEnemy();
        this.enemy12.moveEnemy();
        this.enemy13.moveEnemy();
        this.enemy14.moveEnemy();
        this.enemy15.moveEnemy();
        this.enemy16.moveEnemy();
        this.enemy17.moveEnemy();
        //Crear muro
        this.collisions();
    }

    public void collisions() {
        Bullet[] ship_Bullets = this.ship.getBullets();
        for (int i = 0; i < this.walls.length; i++) {
            for (int j = 0; j < ship_Bullets.length; j++) {
                if (this.walls[i] != null && ship_Bullets[j] != null) {
                    if (this.walls[i].collision(ship_Bullets[j])) {
                        ship_Bullets[j] = null;
                    }
                }
            }
        }
    }
    private void paintWalls(){
          
            for (int i=0;i<this.walls.length;i++){
                this.walls[i].paint(screen );
            }
        }    
    private void paint(Screen s) {
        try {
            TerminalSize terminalSize = s.getTerminalSize();
            for (int column = 0; column < terminalSize.getColumns(); column++) {
                for (int row = 0; row < terminalSize.getRows(); row++) {
                    s.setCharacter(column, row, new TextCharacter(' ', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                }
            }
            screen.clear();
            this.ship.paint(this.screen);
            this.enemy1.paint(this.screen);
            this.enemy2.paint(this.screen);
            this.enemy3.paint(this.screen);
            this.enemy4.paint(this.screen);
            this.enemy5.paint(this.screen);
            this.enemy6.paint(this.screen);
            this.enemy7.paint(this.screen);
            this.enemy8.paint(this.screen);
            this.enemy9.paint(this.screen);
            //Enemigos fila 2
            this.enemy10.paint2(this.screen);
            this.enemy11.paint2(this.screen);
            this.enemy12.paint2(this.screen);
            this.enemy13.paint2(this.screen);
            this.enemy14.paint2(this.screen);
            this.enemy15.paint2(this.screen);
            this.enemy16.paint2(this.screen);
            this.enemy17.paint2(this.screen);
            //Muros
            this.paintWalls();
            screen.refresh();

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void process_input() {
        KeyStroke keyStroke = null;
        try {
            keyStroke = screen.pollInput();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (keyStroke != null) {
            if (keyStroke.getKeyType() == KeyType.Escape) {
                this.exit_key = true;
            }
            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                this.bala.moveVertical(-1, 0, Game.Rows);

            }
            if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                this.bala.moveVertical(1, 0, Game.Rows);
            }
            if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                this.ship.moveHorizontal(-1, 0, Game.Columns);

            }
            if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                this.ship.moveHorizontal(1, 0, Game.Columns);
            }
            if (keyStroke.getKeyType() == KeyType.Enter) {
                this.ship.shoot();
            }

        }

    }

}
