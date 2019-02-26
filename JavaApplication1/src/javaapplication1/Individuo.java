/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *
 * @author Uziel
 */
public class Individuo {
    public int puntuacion = 8;
    public int arreglo[] = new int[8];

    public Individuo() {
        arreglo = IntStream.rangeClosed(0, 7).toArray();
        //desordenando los elementos
        Random r = new Random();
        for (int i = arreglo.length; i > 0; i--) {
            int posicion = r.nextInt(i);
            int tmp = arreglo[i-1];
            arreglo[i - 1] = arreglo[posicion];
            arreglo[posicion] = tmp;
        }
    }

    public Individuo(int[] arr) {
        this.arreglo = arr;
    }
    public void printArr(){
        for (int i = 0;i<arreglo.length;i++) {
            System.out.println(arreglo[i]);
        }
    }
}
