/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;
import controle.*;

/**
 *
 * @author gustavo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ExtraiPalavra palavras = new ExtraiPalavra (args[0], args[1]);
            String palavra = null; 
            int auxLine = 0; int col = 1;
            ArvorePatricia dict = new ArvorePatricia (80);
            while ((palavra = palavras.proximaPalavra())!=null){
                if(!palavra.equals("")){
                    if(auxLine != palavras.line){
                        col = 1;
                        auxLine = palavras.line;
                    }
                    //System.out.printf ("Palavra [%d,%d] = %s\n",palavras.line,col,palavra);
                    Posicao p = new Posicao(palavras.line,col);
                    dict.insere(palavra, p);
                    col++;
                }
            }
            palavras.fecharArquivos();
            String kkk = "que";
            dict.pesquisa(kkk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
