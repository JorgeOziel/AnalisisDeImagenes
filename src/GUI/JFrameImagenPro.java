/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import herramientas.CrearGrafica;
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
    //MENÚ BAR
    private JLabel etiqueta;
    private JMenuBar mb;
    private JMenuItem menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8;
    private BufferedImage imagen;
    private int auxR[] = new int[256];
    private int auxB[] = new int[256];
    private int auxG[] = new int[256];
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
        
        menu4 = new JMenuItem("Histograma de frecuencias");
        menu1.add(menu4);
        menu4.addActionListener(this);
        
        menu5 = new JMenuItem("Modificar iluminación");
        menu1.add(menu5);
        menu5.addActionListener(this);
        
        menu6 = new JMenuItem("Umbralización");
        menu1.add(menu6);
        menu6.addActionListener(this);
        
        menu7 = new JMenuItem("binarizacion");
        menu1.add(menu7);
        menu7.addActionListener(this);
        
        
        menu8 = new JMenuItem("Negativo");
        menu1.add(menu8);
        menu8.addActionListener(this);
        
        add(this.etiqueta);
        setSize(700, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menu2) {     // guardar
            AbrirImagen ab = new AbrirImagen();
            ab.guardarImagen(imagen);
        }
        if (e.getSource()==menu3) {     // convertir a escala de grises
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
        if(e.getSource()==menu4){       // histograma de frecuencias
            obtenerHistograma();
            CrearGrafica cg = new CrearGrafica(auxR,auxB,auxG);
            cg.crear();
        }
        if(e.getSource()==menu5){       // modificar iluminación
            modificarIluminacion(100);
        }
        if(e.getSource()==menu6){       // umbralizacion
            umbralizacion(100,255);
        }
        if(e.getSource()==menu7){       // binarizacion
            binarizacion(100,255);
        }
        if(e.getSource()==menu8){       // negativo
            negativo();
        }
    }
    public void obtenerHistograma(){
        for(int i=0;i<imagen.getWidth();i++){
                for(int j=0;j<imagen.getHeight();j++){
                    Color c = new Color(imagen.getRGB(i,j));
                    auxR[c.getRed()]++;
                    auxB[c.getBlue()]++;
                    auxG[c.getGreen()]++;
            }
        }
    }
    public void modificarIluminacion(int cam){
        Color c;
        int red, blue, green;
        for(int i=0;i<imagen.getWidth();i++){
                for(int j=0;j<imagen.getHeight();j++){
                    red = imagen.getColorModel().getRed(imagen.getRGB(i,j));
                    blue = imagen.getColorModel().getBlue(imagen.getRGB(i,j));
                    green = imagen.getColorModel().getGreen(imagen.getRGB(i,j));
                    red += cam;
                    blue += cam;
                    green += cam;
                    if(red > 255) red = 255;
                    else if (red <0) red = 255;
                    if(blue > 255) blue = 255;
                    else if (blue <0) blue = 255;
                    if(green > 255) green = 255;
                    else if (green <0) green = 255;
                    c = new Color(red,green,blue);  //creamos el color de cada pixel
                    imagen.setRGB(i,j,c.getRGB());   //modificamos el color de la imagen
            }
        }
        Image imagenResultante = AbrirImagen.toImage(imagen);
        JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
    }
    public void umbralizacion(int umbral1, int umbral2){
        Color c;
        int red, blue, green;
        for(int i=0;i<imagen.getWidth();i++){
            for(int j=0;j<imagen.getHeight();j++){
                red = imagen.getColorModel().getRed(imagen.getRGB(i,j));
                blue = imagen.getColorModel().getBlue(imagen.getRGB(i,j));
                green = imagen.getColorModel().getGreen(imagen.getRGB(i,j));
                if(red < umbral1) red = 255;
                else if (red > umbral2) red = 255;
                if(blue < umbral1) blue = 255;
                else if (blue > umbral2) blue = 255;
                if(green < umbral1) green = 255;
                else if (green > umbral2) green = 255;
                c = new Color(red,green,blue);
                imagen.setRGB(i, j, c.getRGB());
            }
        }
        Image imagenResultante = AbrirImagen.toImage(imagen);
        JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
    }
    public void binarizacion(int umbral1, int umbral2){
        Color c;
        int red, blue, green;
        for(int i=0;i<imagen.getWidth();i++){
            for(int j=0;j<imagen.getHeight();j++){
                red = imagen.getColorModel().getRed(imagen.getRGB(i,j));
                blue = imagen.getColorModel().getBlue(imagen.getRGB(i,j));
                green = imagen.getColorModel().getGreen(imagen.getRGB(i,j));
                if(red < umbral1) red = 255;
                else if (red > umbral2) red = 255;
                else red = 0;
                if(blue < umbral1) blue = 255;
                else if (blue > umbral2) blue = 255;
                else blue = 0;
                if(green < umbral1) green = 255;
                else if (green > umbral2) green = 255;
                else green = 0;
                c = new Color(red,green,blue);
                imagen.setRGB(i, j, c.getRGB());
            }
        }
        Image imagenResultante = AbrirImagen.toImage(imagen);
        JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
    }
    public void negativo(){
        Color c;
        int red, blue, green;
        for(int i=0;i<imagen.getWidth();i++){
            for(int j=0;j<imagen.getHeight();j++){
                red = imagen.getColorModel().getRed(imagen.getRGB(i,j));
                blue = imagen.getColorModel().getBlue(imagen.getRGB(i,j));
                green = imagen.getColorModel().getGreen(imagen.getRGB(i,j));
                c = new Color(255-red,255-green,255-blue);
                imagen.setRGB(i, j, c.getRGB());
            }
        }
        Image imagenResultante = AbrirImagen.toImage(imagen);
        JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
    }
}
