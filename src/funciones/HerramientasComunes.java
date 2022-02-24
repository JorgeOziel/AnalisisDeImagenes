/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

/**
 *
 * @author oziel
 */
public class HerramientasComunes {
    public static void imprimirArreglo(int[] arreglo){
        String aux = "";
        for(int i=0;i<arreglo.length;i++){
            aux+= arreglo[i];
        }
        System.out.println(aux);
    }
    public static void imprimirSumaArreglo(int[] arreglo){
        int aux=0;
        for(int i=0;i<arreglo.length;i++){
            aux+= arreglo[i]*i;
        }
        System.out.println(aux);
    }
}
