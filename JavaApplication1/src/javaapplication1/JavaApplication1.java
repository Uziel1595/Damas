/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author IMac
 */
public class JavaApplication1 {
    static Individuo[] poblacion;
    static int padre1 = 0;
    static int padre2 = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        poblacion =  new Individuo[10];
        for (int i = 0; i < 10; i++) {//CREEACION DE LA POBLACION
            poblacion[i] = new Individuo();
        }
        //poblacion[0].printArr();
        int contadorDeIteraciones = 0;
        do{
            contadorDeIteraciones++;
            calcularPuntos();
            System.out.println("padres "+padre1+"  "+padre2);
            System.out.println(poblacion[0].puntuacion);
            System.out.println(poblacion[1].puntuacion);
            System.out.println(poblacion[2].puntuacion);
            System.out.println(poblacion[3].puntuacion);
            System.out.println(poblacion[4].puntuacion);
            System.out.println(poblacion[5].puntuacion);
            System.out.println(poblacion[6].puntuacion);
            System.out.println(poblacion[7].puntuacion);
            System.out.println(poblacion[8].puntuacion);
            System.out.println(poblacion[9].puntuacion);
            cruzarPadres();
            calcularPuntos();
        }while(poblacion[padre1].puntuacion!=8);//DETENMOS CUANDO LA PUNTUACION DE UN PADRE SEA 8
        System.out.println("padres "+padre1+"  "+padre2);
        System.out.println(poblacion[0].puntuacion);
        System.out.println(poblacion[1].puntuacion);
        System.out.println(poblacion[2].puntuacion);
        System.out.println(poblacion[3].puntuacion);
        System.out.println(poblacion[4].puntuacion);
        System.out.println(poblacion[5].puntuacion);
        System.out.println(poblacion[6].puntuacion);
        System.out.println(poblacion[7].puntuacion);
        System.out.println(poblacion[8].puntuacion);
        System.out.println(poblacion[9].puntuacion);
        System.out.println("-------");
        poblacion[padre1].printArr();
        System.out.println("Iteraciones  "+contadorDeIteraciones);
        
    }
    public static void calcularPuntos(){//CALCULAR PUNTOS
        int mayor = -24; //PUNTUACION MINIMA
        int mayor2 = -24;
        for (int i = 0; i < 10; i++) {
            String[] casillas = new String[8];
            for (int j = 0; j < 8; j++) {//CHECAMOS COOREDENADAS DE CASILLA OCUPADAS
                casillas[j]=""+j+""+poblacion[i].arreglo[j];
                //System.out.println(casillas[j]);
            }
            //System.out.println("-------");
            for (int j = 0; j < 8; j++) {//RECORREMOS LAS COLUMNAS
                //ADELANTE     COMPROVAMOS LAS DIAGONLES
                int pr = j;
                int sg = poblacion[i].arreglo[j];
                while(pr<=6&&sg<=6){
                   pr++;
                   sg++;
                   for (int k = 0; k < 8; k++) {
                       if(casillas[k].equals(""+pr+""+sg)){
                           poblacion[i].puntuacion--;
                           break;
                       }
                   }
                }
                //ATRAS
                pr = j;
                sg = poblacion[i].arreglo[j];
                while(pr>=1&&sg>=1){
                   pr--;
                   sg--;
                   for (int k = 0; k < 8; k++) {
                       if(casillas[k].equals(""+pr+""+sg)){
                           poblacion[i].puntuacion--;
                           break;
                       }
                   }
                }
                //DERECHA
                pr = j;
                sg = poblacion[i].arreglo[j];
                while(pr<=6&&sg>=1){
                   pr++;
                   sg--;
                   for (int k = 0; k < 8; k++) {
                       if(casillas[k].equals(""+pr+""+sg)){
                           poblacion[i].puntuacion--;
                           break;
                       }
                   }
                }
                //IZQUIERDA
                pr = j;
                sg = poblacion[i].arreglo[j];
                while(pr>=1&&sg<=6){
                   pr--;
                   sg++;
                   for (int k = 0; k < 8; k++) {
                       if(casillas[k].equals(""+pr+""+sg)){
                           poblacion[i].puntuacion--;
                           break;
                       }
                   }
                }
            }
            if(poblacion[i].puntuacion>mayor){//SELECCIONAMOS A LOS PADRES
                mayor = poblacion[i].puntuacion;
                padre1 = i;
            }
            if(poblacion[i].puntuacion>mayor2&&poblacion[i].puntuacion<=mayor){
                if(i!=padre1){
                    mayor2 = poblacion[i].puntuacion;
                    padre2 = i;
                }
            }
        }
    }
    public static void cruzarPadres(){
        int j=0;//CONTADOR PARA CREAR 6 HIJOS
        for (int i = 0; i < 10; i++) {//RECORREMOS LA POBLACION MENOS LOS 2 PADRES
            poblacion[i].puntuacion=8;
            if(i==padre1||i==padre2)//SI ES ALGUNO DE LOS PADRES, NO PROCEDEMOS
                continue;
            if(j>=5){
                poblacion[i].arreglo = IntStream.rangeClosed(0, 7).toArray();
                //desordenando los elementos
                Random r = new Random();
                for (int h = poblacion[i].arreglo.length; h > 0; h--) {
                    int posicion = r.nextInt(h);
                    int tmp = poblacion[i].arreglo[h-1];
                    poblacion[i].arreglo[h - 1] = poblacion[i].arreglo[posicion];
                    poblacion[i].arreglo[posicion] = tmp;
                }
            }else{
                int hold1 = poblacion[padre1].arreglo[j];
                int hold2 = poblacion[padre1].arreglo[j+1];
                int hold3 = poblacion[padre1].arreglo[j+2];
                poblacion[i].arreglo[j] = hold1;
                poblacion[i].arreglo[j+1] = hold2;
                poblacion[i].arreglo[j+2] = hold3;
                //Arrays.asList(poblacion[padre1].arreglo).ind
                for (int k = 0; k < 8; k++) {//RECORREMOS PADRE 2
                    int temp = poblacion[padre2].arreglo[k];
                    int indexInsert = 0;
                    if(temp==hold1||temp==hold2||temp==hold3)
                        continue;
                    for (int l = 0; l < 8; l++) {//RECORREMOS PADRE 1
                        int temp2 = poblacion[padre1].arreglo[l];
                        if(temp==hold1||temp==hold2||temp==hold3){
                            indexInsert++;
                            continue;
                        } 
                        if(temp==temp2){
                            poblacion[i].arreglo[indexInsert] = temp;
                            indexInsert++;
                        }
                    }

                }
                j++;
            }
        }
 
    }
}
