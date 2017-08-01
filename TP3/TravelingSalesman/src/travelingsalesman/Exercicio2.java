/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;

/**
 *
 * @author gustavo
 */
public class Exercicio2 {
    private String arq565, arq561, arq1032;
    private int intTamanho;
    /**
     * Contrutor
     * @param arq1
     * @param arq2
     * @param arq3 
     */
    public Exercicio2(String arq1, String arq2, String arq3){
        this.arq565 = arq1;
        this.arq561 = arq2;
        this.arq1032 = arq3;
    }
    
    public void iniciarExercicio() throws Exception{
        int intCusto = 0;
        //Letra a
        intTamanho = 535;
        int mMatriz[][] = new int[intTamanho][intTamanho];
        ExtraiMatriz numeros = new ExtraiMatriz(this.arq565);
        montarMatrizDiagonalSuperior(mMatriz, numeros);
        //imprimirMatriz(mMatriz, intTamanho);
        //Resultado
        Heuristica heuristica = new Heuristica();
        heuristica.setPushInicial(0);
        intCusto = heuristica.EncontrarMenorCaminho(mMatriz);
        System.out.println("Custo 1:\t" + intCusto);
        
        //Letra b
        intTamanho = 561;
        mMatriz = new int[intTamanho][intTamanho];
        numeros = new ExtraiMatriz(this.arq561);
        montarMatrizDiagonalInferior(mMatriz, numeros);
        //imprimirMatriz(mMatriz, intTamanho);
        //Resultado
        heuristica.setPushInicial(intTamanho-1);
        intCusto = heuristica.EncontrarMenorCaminho(mMatriz);
        System.out.println("Custo 2:\t" + intCusto);
        
        //Letra c
        intTamanho = 1032;
        mMatriz= new int[intTamanho][intTamanho];
        numeros = new ExtraiMatriz(this.arq1032);
        montarMatrizDiagonalSuperior(mMatriz, numeros);
        //imprimirMatriz(mMatriz, intTamanho);
        //Resultado
        heuristica.setPushInicial(0);
        intCusto = heuristica.EncontrarMenorCaminho(mMatriz);
        System.out.println("Custo 3:\t" + intCusto);
    }
    
    /**
     * Procedimento responsavel por ler o arquivo fornecido e montar a matriz diagonal inferior
     * @param mMatriz
     * @throws Exception 
     */
    public void montarMatrizDiagonalInferior(int mMatriz[][], ExtraiMatriz numeros) throws Exception{
        int linha = 0, coluna = 0;
        String strNumero = null;
        int numero;
        while((strNumero = numeros.proximoNumero())!=null){
            if(!strNumero.equals("") && !strNumero.equals("EOF")){
                numero = Integer.parseInt(strNumero);    
                if(linha == coluna){
                    mMatriz[linha][coluna] = 0;
                    coluna++;
                }
                if(numero == 0){
                    linha++;
                    coluna = 0; 
                }
                else{
                    mMatriz[linha][coluna] = numero;
                    coluna++;
                }
            }  
        }
        numeros.FecharARquivo();
    }
    
    /**
     * Procedimento responsavel por ler o arquivo fornecido e montar a matriz diagonal superior
     * @param mMatriz
     * @throws Exception 
     */
    public void montarMatrizDiagonalSuperior(int mMatriz[][], ExtraiMatriz numeros) throws Exception{
        int linha = 0, coluna = 0;
        String strNumero = null;
        int numero;
        while((strNumero = numeros.proximoNumero())!=null){
            if(!strNumero.equals("") && !strNumero.equals("EOF")){
                numero = Integer.parseInt(strNumero);    
                if(linha == coluna){
                    mMatriz[linha][coluna] = 0;
                    coluna++;
                }
                if(numero == 0){
                    if (!(linha == 0 && coluna == 1)){
                        linha++;
                        coluna = linha+1;
                    } 
                }
                else{
                    mMatriz[linha][coluna] = numero;
                    coluna++;
                }
            }  
        }
        numeros.FecharARquivo();
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
                System.out.printf(" %3d", matrix[linha][coluna]);
            }
            System.out.print("\n");
        }
    }
}
