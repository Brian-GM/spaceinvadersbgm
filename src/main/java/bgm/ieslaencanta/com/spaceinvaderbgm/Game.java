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
    public static int Columns=80;
    public static int Rows=80;
  //  public static int FinalColumns=80;
    // public static int FinalRows=80;


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
        ship = new Ship (38,20);
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
                    Thread.sleep((1/60)*1000);
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

    private void update() {

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
            this.bala.paint(this.screen);
            this.ship.paint(this.screen);
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

        }
    }
}
