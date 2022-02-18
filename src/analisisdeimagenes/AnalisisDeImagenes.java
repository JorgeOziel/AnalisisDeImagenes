/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analisisdeimagenes;

import GUI.JFrameImagen;
import GUI.JFrameImagenPro;
import RGB.jFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import open.AbrirImagen;
/**
 *
 * @author oziel
 */
public class AnalisisDeImagenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Image imagenOriginal = AbrirImagen.openImage();
        JFrameImagenPro aux = new JFrameImagenPro(imagenOriginal);
    }
    
}
