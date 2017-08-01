/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.StringTokenizer;
import java.io.*;

/**
 *
 * @author gustavo
 */
public class ExtraiPalavra {
    private BufferedReader arqDelim, arqTxt;
    private StringTokenizer palavras;
    private String delimitadores;
    public int line;
    /**
     * Funcao que le todas as palavras do arquivo de texto fornecido obedecendo os delimitadores
     * @param nomeArqDelim
     * @param nomeArqTxt
     * @throws Exception 
     */
    public ExtraiPalavra (String nomeArqDelim, String nomeArqTxt) throws Exception {
        this.arqDelim = new BufferedReader (new FileReader (nomeArqDelim));
        this.arqTxt = new BufferedReader (new FileReader (nomeArqTxt));
        // Armazena os delimitadores
        this.delimitadores = arqDelim.readLine() + "\r\n"; // Os delimitadores devem estar juntos em uma unica linha do arquivo
        this.palavras = null;
        this.line = 0;
    }
    
    /**
     * Funcao que retorna a proxima palavra
     * @return String
     * @throws Exception 
     */
    public String proximaPalavra () throws Exception{
        if (palavras == null || !palavras.hasMoreTokens()) {
            String linha = arqTxt.readLine();
            this.line++;
            if (linha == null) 
                return null; 
            this.palavras = new StringTokenizer (linha, this.delimitadores);
            if (!palavras.hasMoreTokens()) 
                return ""; // Ignora delimitadores
        }
        return this.palavras.nextToken ();
    }
    
    //Metodo que fecha o arquivo de delimitadores e o de texto
    public void fecharArquivos () throws Exception {
        this.arqDelim.close(); 
        this.arqTxt.close();
    }
}
