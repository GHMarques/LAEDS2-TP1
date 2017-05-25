package controle;

/**
 *
 * @author pedro-castro
 */
public class Posicao {
    //Declaracao de variaveis
    public int coluna,linha;

    /**
     * Construtor
     * @param l = linha
     * @param c = coluna
     */
    public Posicao(int l,int c) {
        this.coluna = c;
        this.linha = l;
    }
    
    @Override
    /**
     * Funcao que retorna a string formatada
     * Formato: {[linha],[coluna]}
     */
    public String toString(){
        String retorno = "{[" + this.linha + "]" + "[" + this.coluna + "]}";
        return retorno;
    }
    
    
}
