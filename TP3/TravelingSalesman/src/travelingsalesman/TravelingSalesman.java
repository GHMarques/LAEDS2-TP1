/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author gusta
 */
public class TravelingSalesman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Exercicio 1
        /*for(int intAuxiliar = 2; intAuxiliar < 15; intAuxiliar++){
            Exercicio1 ex1 = new Exercicio1(intAuxiliar);
            ex1.IniciarExercicio();
        }*/
        
        //Exercicio 2
        Exercicio2 ex2 = new Exercicio2(args[0], args[1], args[2]);
        ex2.iniciarExercicio();
        
    }
}
