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
            String palavra = null; int i = 1;
            while ((palavra = palavras.proximaPalavra())!=null)
                System.out.println ("Palavra"+ (i++) +": " + palavra); 
            palavras.fecharArquivos();
        } catch (Exception e) {System.out.println (e.getMessage ());}
    }
    
}
