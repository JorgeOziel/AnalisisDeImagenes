/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herramientas;

/**
 *
 * @author oziel
 */
public class CrearGrafica {
    Grafica gr = new Grafica("Histograma", "Pixel", "Color");
    int auxR[] = new int[256];
    int auxB[] = new int[256];
    int auxG[] = new int[256];
    public CrearGrafica(int[] auxR, int[] auxB, int[] auxG){
        this.auxR = auxR;
        this.auxB = auxB;
        this.auxG = auxG;
    }
    public void crear(){
        gr.agregarSerie(this.auxR, "Red");
        gr.agregarSerie(this.auxB, "Blue");
        gr.agregarSerie(this.auxG, "Green");
        gr.creaYmuestraGrafica();
    }
}
