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
     * Funcao principal
     * Recebe como parametro o arquivo contendo os caracteres delimtadores e o arquivo de texto que deve ser lido
     * e armazenado na arvore
     */
    public static void main(String[] args) {
        try {
            //Declaracao de variaveis
            ExtraiPalavra palavras = new ExtraiPalavra (args[0], args[1]);
            String palavra = null; 
            int auxLine = 0; int col = 1;
            ArvorePatricia dict = new ArvorePatricia (80);
            //Laco para percorrer o texto e pegar posicao (linha e coluna) de cada palavra
            while ((palavra = palavras.proximaPalavra())!=null){
                if(!palavra.equals("")){
                    if(auxLine != palavras.line){
                        col = 1;
                        auxLine = palavras.line;
                    }
                    //Cria o objeto posicao com informacoes retiradas do texto
                    Posicao p = new Posicao(palavras.line,col);
                    //Armazena na arvore a palavra e a sua respectiva posicao
                    dict.insere(palavra, p);
                    col++;
                }
            }
            palavras.fecharArquivos();
            ExtraiPalavra queroBuscar = new ExtraiPalavra(args[0],args[2]);
            //String que sera procurada no texto
            String busca = null;
            while ((busca = queroBuscar.proximaPalavra())!=null){
                if(!busca.equals("")){
                    dict.pesquisa(busca);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
