/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import GUI.JFrameImagenPro;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import open.AbrirImagen;

/**
 *
 * @author Alumno
 */
public class Convolucion {
    private static int[][] matrizConvolucion;
    private static BufferedImage imagen;
    private static BufferedImage imagenAux;
    public Convolucion(int[][] matriz, BufferedImage imagen){
        this.matrizConvolucion = matriz;
        this.imagen = imagen;
        this.imagenAux = imagen;
    }
    
    public void convolucionMultiple(){
        for(int i=0; i<imagen.getWidth(); i++){
            for(int j=0; j<imagen.getHeight(); j++){
                aplicarConvolucion(i,j);
            }
        }
    }
    public void convolucionMultiple(int xMin, int xMax, int yMin, int yMax){
        xMin = validarX(xMin);
        xMax = validarX(xMax);
        yMin = validarY(yMin);
        yMax = validarY(yMax);
        for(int i=xMin; i<xMax; i++){
            for(int j=xMin; j<xMax; j++){
                aplicarConvolucion(i,j);
            }
        }
    }
    
    public void aplicarConvolucion(int x, int y){
        int medio = (matrizConvolucion.length/2);
        int horizontal=0,vertical=0;
        int sumaR=0,sumaB=0,sumaG=0,diferencia=0,cantidadUno=0;
        Color pixel;
            for(int f=0; f < matrizConvolucion.length;f++){
                for(int g=0; g < matrizConvolucion.length;g++){
                    if(medio > f){ 
                        diferencia = medio-f;
                        horizontal = x-diferencia;
                    }
                    else{
                        diferencia = f-medio;
                        horizontal = x+diferencia;
                    }
                    if(medio > g){ 
                        diferencia = medio-g;
                        vertical = y-diferencia;
                    }
                    else{
                        diferencia = g-medio;
                        vertical = y+diferencia;
                    }
                    if(matrizConvolucion[f][g]==1){
                        if(horizontal > 0 && horizontal < imagen.getWidth() && vertical > 0 && vertical < imagen.getHeight()){
                            cantidadUno++;
                            pixel = new Color(imagen.getRGB(horizontal, vertical));
                            sumaR += pixel.getRed();
                            sumaB += pixel.getBlue();
                            sumaG += pixel.getGreen();
                        }
                    }
                }
            }  
        sumaR /= cantidadUno;
        sumaG /= cantidadUno;
        sumaB /= cantidadUno;
        sumaR = validar(sumaR);
        sumaG = validar(sumaG);
        sumaB = validar(sumaB);
        pixel = new Color(sumaR, sumaG, sumaB);
        imagen.setRGB(x, y, pixel.getRGB());
    }
    
    public int validar(int aux){
        if(aux > 255) aux = 255;
        if(aux < 0) aux=0;
        return aux;
    }
    
    public int validarX(int x){
        if(x > imagen.getWidth()) x = imagen.getWidth();
        if(x < 0) x=0;
        return x;
    }
    
    public int validarY(int y){
        if(y > imagen.getHeight()) y = imagen.getHeight();
        if(y < 0) y = 0;
        return y;
    }
    
    public int[][] getMatrizConvolucion() {
        return matrizConvolucion;
    }
    public void setMatrizConvolucion(int[][] matrizConvolucion) {
        this.matrizConvolucion = matrizConvolucion;
    }
    public BufferedImage getImagen() {
        return imagen;
    }
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
    public BufferedImage getImagenAux() {
        return imagenAux;
    }
    public void setImagenAux(BufferedImage imagen) {
        this.imagenAux = imagen;
    }
    
}
