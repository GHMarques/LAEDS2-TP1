/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;

import java.util.Date;
import java.util.Random;

public class Exercicio1 {
    //Variavel que controle quantidade de instacias (numero de cidades, vertices)
    private int intCidades;
    
    public Exercicio1(int intVertices){
        this.intCidades = intVertices;
    }
    
    public void IniciarExercicio(){
        //Limite dos pesos
        int minimo = 0;
        int maximo = 10;
        Random random = new Random();
        //Gera matriz de adjacencia
        int mMatriz[][] = new int[intCidades][intCidades];
        int intAuxiliar;
        for(intAuxiliar = 0; intAuxiliar < intCidades; intAuxiliar++){
            for(int linha = 0; linha < intCidades; linha++){
                for(int coluna = 0; coluna < intCidades; coluna++){
                    if(linha == coluna)
                        mMatriz[linha][coluna] = 0; //A distancia entre uma cidade e ela mesmo sera sempre 0
                    else{
                        mMatriz[linha][coluna] = random.nextInt(maximo-minimo+1)+minimo; //Gera um numero aleatorio para o peso
                    }
                        
                }
            }
        }
        //Imprime a matriz
        imprimirMatriz(mMatriz, intCidades);
        
        //Menor caminho atraves de forca bruta
        Date dtInicio = new Date();
        long lgInicio = dtInicio.getTime();
        BruteForce forcaBruta = new BruteForce(mMatriz);
        forcaBruta.EncontrarMenorCaminho();
        // Mostra o tempo de execução
        Date dtFinal = new Date();
        long lgFinal = dtFinal.getTime();
        long lgTempo = (lgFinal-lgInicio);
        //Transforma em segundos
        Double dblSegundos = lgTempo/1000.0;
        //Imprime o tempo
        System.out.println("\nTempo: " + Double.toString(dblSegundos) + " segundos\n");
    }
    
    /**
     * Função para imprimir a matriz de adjacncia
     * @param matrix
     * @param intTamanho 
     */
    public void imprimirMatriz(int matrix[][], int intTamanho){
        System.out.println("Matriz de adjacencia");
        for(int linha = 0; linha < intTamanho; linha++){
            for(int coluna = 0; coluna < intTamanho; coluna++){
                System.out.printf(" %2d", matrix[linha][coluna]);
            }
            System.out.print("\n");
        }
    }
}
