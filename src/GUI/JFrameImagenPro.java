/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import open.AbrirImagen;

/**
 *
 * @author oziel
 */
public class JFrameImagenPro extends JFrame implements ActionListener{
    private JLabel etiqueta;
    private JMenuBar mb;
    private JMenuItem menu1,menu2,menu3;
    private BufferedImage imagen;
    public JFrameImagenPro() {
        init();
    }
    public JFrameImagenPro(Image imagenOriginal) {
        imagen = AbrirImagen.toBufferedImage(imagenOriginal);
        init();
        this.etiqueta.setIcon(new ImageIcon(imagenOriginal));
    }
    private void init(){
        this.etiqueta = new JLabel();
        mb=new JMenuBar();
        setJMenuBar(mb);
        menu1=new JMenu("Opciones");
        mb.add(menu1);
        menu2=new JMenuItem("Guardar");
        menu1.add(menu2);
        menu2.addActionListener(this);
        menu3=new JMenuItem("Convertir a escala de grises");
        menu1.add(menu3);
        menu3.addActionListener(this);
        add(this.etiqueta);
        setSize(700, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menu2) {
            AbrirImagen ab = new AbrirImagen();
            ab.guardarImagen(imagen);
        }
        if (e.getSource()==menu3) {
            Color c;
            for(int i=0;i<imagen.getWidth();i++){
                for(int j=0;j<imagen.getHeight();j++){
                int suma=0;
                //sumamos el RGB
                suma = suma + imagen.getColorModel().getRed(imagen.getRGB(i,j));
                suma = suma + imagen.getColorModel().getBlue(imagen.getRGB(i,j));
                suma = suma + imagen.getColorModel().getGreen(imagen.getRGB(i,j));
                suma = suma/3;          //promedio
                c = new Color(suma,suma,suma);  //creamos el color de cada pixel
                imagen.setRGB(i,j,c.getRGB());   //modificamos el color de la imagen
                }
            }
            Image imagenResultante = AbrirImagen.toImage(imagen);
            JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
        }
    }
}
