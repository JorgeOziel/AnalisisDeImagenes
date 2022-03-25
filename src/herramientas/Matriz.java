/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herramientas;

import java.util.Random;

/**
 *
 * @author oziel
 */
public class Matriz {
    private int[][] matriz;
    public Matriz(int tam){
        matriz = new int[tam][tam];
        llenarMatriz();
    }
    public void llenarMatriz(){
        Random random = new Random();
        for(int i=0; i<getMatriz().length; i++){
            for(int j=0; j<getMatriz().length;j++){
                matriz[i][j] = random.nextInt(2);
            }
        }
    }
    public void leerMatriz(){
        String cadena = "";
        for(int i=0; i<getMatriz().length; i++){
            for(int j=0; j<getMatriz().length;j++){
                cadena += matriz[i][j];
                if(j != getMatriz().length-1) cadena+= ", ";
            }
            cadena+= "\n";
        }
        System.out.println(cadena);
    }

    public int[][] getMatriz() {
        return matriz;
    }
}
