/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 *
 * @author gustavo
 */
public class ExtraiMatriz {
    private BufferedReader arqTsp;
    private StringTokenizer elementos;
    
    public ExtraiMatriz(String arqTsp) throws Exception{
        this.arqTsp = new BufferedReader (new FileReader(arqTsp));
        this.elementos = null;
    }
    
    public String proximoNumero() throws Exception{
        if(elementos == null || !elementos.hasMoreTokens()){
            String linha = arqTsp.readLine();
            if (linha == null)
                return null;
            this.elementos = new StringTokenizer(linha, " ");
            if(!elementos.hasMoreElements())
                return "";
        }
        return this.elementos.nextToken();
    }
    
    
    /**
     * Metodo para fechar arquivo
     */
    public void FecharARquivo() throws Exception{
        this.arqTsp.close();
    }
}
