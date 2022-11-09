package Ventanas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.JFrame;

public class VentanaInicial extends JFrame{

public VentanaInicial() {
this.setSize(1000, 600);
this.setLocationRelativeTo(null);
this.setTitle("Fut Draft & Pack Opener 23");

this.addWindowListener((WindowListener) new WindowAdapter() {
@Override
public void windowClosing(WindowEvent e) {
System.exit(0);
} 
});
}

public static void main(String[] args) {
VentanaInicial v = new VentanaInicial();
EmbeddedMediaPlayerComponent comp = new EmbeddedMediaPlayerComponent() {
public void finished(MediaPlayer mediaPlayer) {
v.dispose();
VentanaLogIn vl = new VentanaLogIn();
vl.setVisible(true);
}
};
v.setContentPane(comp);
v.setVisible(true);
comp.mediaPlayer().media().play("media/deustovideo.m4v");
v.addKeyListener(new KeyAdapter() {
@Override
public void keyPressed(KeyEvent e) {
if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
comp.mediaPlayer().controls().pause();
v.dispose();
VentanaLogIn vl = new VentanaLogIn();
vl.setVisible(true);
} 
}

});


}
}
