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
    //Menu bar
    private JLabel etiqueta;
    private JMenuBar mb;
    private JMenuItem menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8,menu9,menu10;
    
    //Buffered image para poder actualizar la imagen 
    private BufferedImage imagen;
    
    //histograma de colores
    private int auxR[] = new int[256];
    private int auxB[] = new int[256];
    private int auxG[] = new int[256];
    private int aux[]  = new int[256];
    ////////////////////////////////////////////////////////////////////////////
    //Constructores
    public JFrameImagenPro() {
        init();
    }
    
    public JFrameImagenPro(Image imagenOriginal) {
        imagen = AbrirImagen.toBufferedImage(imagenOriginal);
        init();
        this.etiqueta.setIcon(new ImageIcon(imagenOriginal));
    }
    ////////////////////////////////////////////////////////////////////////////
    //Iniciando atributos
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
        
        menu9 = new JMenuItem("Temperatura");
        menu1.add(menu9);
        menu9.addActionListener(this);
        
        menu10 = new JMenuItem("Umbralización automática");
        menu1.add(menu10);
        menu10.addActionListener(this);
        
        add(this.etiqueta);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    //Opciones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menu2) {     // guardar
            guardar();
        }
        if (e.getSource()==menu3) {     // convertir a escala de grises
            convertirAEscalaGris();
        }
        if(e.getSource()==menu4){       // histograma de frecuencias
            obtenerHistograma();
        }
        if(e.getSource()==menu5){       // modificar iluminación
            modificarIluminacion(100);
        }
        if(e.getSource()==menu6){       // umbralizacion
            umbralizacionDos(0,100);
        }
        if(e.getSource()==menu7){       // binarizacion
            binarizacion(100,255);
        }
        if(e.getSource()==menu8){       // negativo
            negativo();
        }
        if(e.getSource()==menu9){
            temperatura(100);
        }
        if(e.getSource()==menu10){
            System.out.println(umbralAutomatico(aux));
            umbralizacion(umbralAutomatico(aux));
        }
    }
    
    //Métodos del menú de opciones
    public void guardar(){
        AbrirImagen ab = new AbrirImagen();
        ab.guardarImagen(imagen);
    }
    public void convertirAEscalaGris(){
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
                aux[suma]++;
                imagen.setRGB(i,j,c.getRGB());   //modificamos el color de la imagen
                }
            }
            Image imagenResultante = AbrirImagen.toImage(imagen);
            JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
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
        CrearGrafica cg = new CrearGrafica(auxR,auxB,auxG);
        cg.crear();
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
    public void umbralizacion(int umbral){
        Color c;
        int red, blue, green;
        for(int i=0;i<imagen.getWidth();i++){
            for(int j=0;j<imagen.getHeight();j++){
                    red = imagen.getColorModel().getRed(imagen.getRGB(i,j));
                    blue = imagen.getColorModel().getBlue(imagen.getRGB(i,j));
                    green = imagen.getColorModel().getGreen(imagen.getRGB(i,j));
                    if (red > umbral) red = 255;
                    if (blue > umbral) blue = 255;
                    if (green > umbral) green = 255;
                    c = new Color(red,green,blue);
                    imagen.setRGB(i, j, c.getRGB());
            }
        }
        Image imagenResultante = AbrirImagen.toImage(imagen);
        JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
    }
    public void umbralizacionDos(int umbral1, int umbral2){
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
    public void temperatura(int mod){
        Color c;
        int red, blue, green;
        for(int i=0;i<imagen.getWidth();i++){
            for(int j=0;j<imagen.getHeight();j++){
                red = imagen.getColorModel().getRed(imagen.getRGB(i,j));
                blue = imagen.getColorModel().getBlue(imagen.getRGB(i,j));
                green = imagen.getColorModel().getGreen(imagen.getRGB(i,j));
                
                red+=mod;
                blue+=mod;
                if(red > 255) red = 255;
                else if(red < 0) red = 0;
                if(blue > 255) blue = 255;
                else if (blue < 0) blue=0;
                c = new Color(red,green,blue);
                imagen.setRGB(i, j, c.getRGB());
            }
        }
        Image imagenResultante = AbrirImagen.toImage(imagen);
        JFrameImagenPro auxResultante = new JFrameImagenPro(imagenResultante);
    }
    public int umbralAutomatico(int[] histograma){
        System.out.println("NOTA1");
        int ui = calcularUmbralInicial(histograma);
        int uNuevo=0;
        System.out.println(ui);
        do{
        uNuevo = ui;
        ui = reajustarUmbral(ui,histograma);
        System.out.println(ui);
        }while(uNuevo!=ui);
        
        return ui;
        
    }
    
    public int calcularUmbralInicial(int[] histograma){
        int numPixels = 0;
        int suma = 0;
        for(int x=0;x<histograma.length;x++){
        numPixels+=histograma[x];
        suma+=histograma[x]*x;
        }
        return (int)(suma/numPixels);
    }
    
    private int reajustarUmbral(int ui, int[] histograma) {
       int u1,u2;
       int a1=0,a2=0,n1=0,n2=0;
       a1+=histograma[0];
       for(int x=1;x<ui;x++){
        a1+=histograma[x]*x;
        n1+=histograma[x];
       }
       
        for(int y=ui;y<=255;y++){
        a2+=histograma[y]*y;
        n2+=histograma[y];
       }
        if (n1==0 || n2==0) return 0;
        u1 = a1/n1;
        u2 = a2/n2;
       return (int)((u1+u2)/2);
    }
}
