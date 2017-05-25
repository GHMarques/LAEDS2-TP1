package controle;

/**
 *
 * @author pedro-castro
 */
public class Posicao {
    public int coluna,linha;

    public Posicao(int l,int c) {
        this.coluna = c;
        this.linha = l;
    }
    
    @Override
    public String toString(){
        String retorno = "{[" + this.linha + "]" + "[" + this.coluna + "]}";
        return retorno;
    }
    
    
}
