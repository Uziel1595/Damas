/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

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
        for (int i = 0; i < 10; i++) {
            poblacion[i] = new Individuo();
        }
        poblacion[0].printArr();
        calcularPuntos();
        System.out.println("padres"+padre1+"  "+padre2);
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
        
    }
    public static void calcularPuntos(){
        int mayor = 0;
        for (int i = 0; i < 10; i++) {
            String[] casillas = new String[8];
            for (int j = 0; j < 8; j++) {//CHECAMOS COOREDENADAS DE CASILLA OCUPADAS
                casillas[j]=""+j+""+poblacion[i].arreglo[j];
                System.out.println(casillas[j]);
            }
            System.out.println("-------");
            for (int j = 0; j < 8; j++) {//RECORREMOS LAS COLUMNAS
                //ADEANTE     COMPROVAMOS LAS DIAGONLES
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
            if(poblacion[i].puntuacion>mayor){
                mayor = poblacion[i].puntuacion;
                padre2 = padre1;
                padre1 = i;
            }
        }
    }
    public static void cruzarPadres(){
        int j=0;
        for (int i = 0; i < 8; i++) {
            if(i==padre1||i==padre2)
                continue;
            poblacion[i].arreglo[0] = poblacion[padre1].arreglo[i];
            poblacion[i].arreglo[1] = poblacion[padre1].arreglo[i+1];
            poblacion[i].arreglo[2] = poblacion[padre1].arreglo[i+2];
            //Arrays.asList(poblacion[padre1].arreglo).ind
            j++;
            
        }
 
    }
}
