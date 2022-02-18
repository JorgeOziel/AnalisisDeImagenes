/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author oziel
 */
public class JFrameImagen extends JFrame{
    private JLabel etiqueta;
    public JFrameImagen(Image imagenOriginal){
        init();
        //agregamos la imagen a la etiqueta utilizando la clase ImageIcon
        this.etiqueta.setIcon(new ImageIcon(imagenOriginal));
    }
    
    private void init(){
        this.etiqueta = new JLabel();
        setSize(700, 600);
        add(this.etiqueta);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
