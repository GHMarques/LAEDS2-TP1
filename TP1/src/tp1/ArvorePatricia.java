package tp1;

import java.util.List;
import controle.Posicao;
import java.util.ArrayList;
public class ArvorePatricia {

    private static abstract class PatNo {
    }
    
    //no interno da arvore
    private static class PatNoInt extends PatNo {

        int index;
        PatNo esq, dir;
    }
    
    //no externo da arvore
    private static class PatNoExt extends PatNo {

        String chave;
        List<Posicao> pos; //guarda todas posicoes de certa palavra
    }

    private PatNo raiz;
    private int nbitsChave;
    
    
    private int[] binGen(String palavra){
        int binario[] = new int[100];
        //cada letra da palavra sera um bin de 5 bits
        for (int i = 0; i < Math.min(palavra.length(),16); i++){
            int caracter = (int)palavra.charAt(i);
            for (int j = 4; j >=0; j--){
                binario[i*5+j] = caracter % 2;
                caracter /= 2;
            }
        }
        return binario;
    }
    //Retorna o i-esimo bit da chave k a partir da esquerda
    private int bit(int pos, String palavra) {
        int []bin;
        bin = binGen(palavra);
        return bin[pos];
    }

    // Verifica se p é nó externo
    private boolean eExterno(PatNo p) {
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }
    
    //cria um nó interno
    private PatNo criaNoInt(int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt();
        p.index = i;
        p.esq = esq;
        p.dir = dir;
        return p;
    }
    
    //cria nó externo
    private PatNo criaNoExt(String palavra,Posicao posicao) {
        PatNoExt p = new PatNoExt();
        p.chave = palavra;
        p.pos = new ArrayList<>();
        p.pos.add(posicao);
        return p;
    }
    
    /**
     * Metodo de busca principal
     * @param k : String
     * @param t  : PatNo
     */
    private void pesquisa(String k, PatNo t) {
        if (this.eExterno(t)) { //se estou em um no externo posso testar a chave
            PatNoExt aux = (PatNoExt) t;
            if (aux.chave.equalsIgnoreCase(k)) { //achou o elemento nesse no
                System.out.println("Elemento '" + k + "' encontrado");
                for(int i = 0; i < aux.pos.size() ; i++ ){ //imprime as posições onde essa palavra esta no texto
                    System.out.print( aux.pos.get(i).toString());
                    if(i != aux.pos.size()-1)
                        System.out.print(", ");
                }
                System.out.print("\n");
            } else {
                System.out.println("Elemento '" + k + "' não encontrado");
            }
        } else { //se estou em nó interno desço a arvore fazendo chamada recursiva
            PatNoInt aux = (PatNoInt) t;
            if (this.bit(aux.index, k) == 0) {
                pesquisa(k, aux.esq);
            } else {
                pesquisa(k, aux.dir);
            }
        }
    }
    
    /**
     * Metodo de inserção auxiliar
     * @param k : String
     * @param t : PatNo
     * @param i : int
     * @param pos : Posicao
     * @return PatNo
     */
    private PatNo insereEntre(String k, PatNo t, int i,Posicao pos) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) {
            aux = (PatNoInt) t;
        }
        if (this.eExterno(t) || (i < aux.index)) { // Cria um novo no externo
            PatNo p = criaNoExt(k,pos);
            if (this.bit(i, k) == 1) {
                return this.criaNoInt(i, t, p);
            } else {
                return this.criaNoInt(i, p, t);
            }
        } else { //move na arvore via recursao ate encontrar um no externo 't'
            if (this.bit(aux.index, k) == 1) {
                aux.dir = this.insereEntre(k, aux.dir, i,pos);
            } else {
                aux.esq = this.insereEntre(k, aux.esq, i,pos);
            }
            return aux;
        }
    }
    
    /**
     * Méotodo de inserção principal
     * @param k : String
     * @param t : PatNo
     * @param pos : Posicao
     * @return PatNo
     */
    private PatNo insere(String k, PatNo t, Posicao pos) {
        if (t == null) { //cheguei em uma folha
            return this.criaNoExt(k,pos); //cria no externo
        } else {
            PatNo p = t;
            while (!this.eExterno(p)) { //pecorre a arvore ate encontrar um nó externo
                PatNoInt aux = (PatNoInt) p;
                if (this.bit(aux.index, k) == 1) {
                    p = aux.dir;
                } else {
                    p = aux.esq;
                }
            }
            PatNoExt aux = (PatNoExt) p;
            int i = 1; //acha o primeiro bit diferente
            while ((i <= this.nbitsChave)
                    && (this.bit(i, k) == this.bit(i, aux.chave))) {
                i++;
            }
            if (i > this.nbitsChave) { //a chave que estou tentando inserir já está na arvore
                aux.pos.add(pos); //adiciona a posicao dessa chave ao no externo já exsitente
                return t;
            } else {
                return this.insereEntre(k, t, i, pos);
            }
        }
    }

    private void central(PatNo pai, PatNo filho, String msg) {
        if (filho != null) {
            if (!this.eExterno(filho)) {
                PatNoInt aux = (PatNoInt) filho;
                central(filho, aux.esq, "ESQ");
                if (pai != null) {
                    System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Int: " + aux.index);
                } else {
                    System.out.println("Pai: " + pai + " " + msg + " Int: " + aux.index);
                }
                central(filho, aux.dir, "DIR");
            } else {
                PatNoExt aux = (PatNoExt) filho;
                if (pai != null) {
                    System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Ext: " + aux.chave);
                } else {
                    System.out.println("Pai: " + pai + " " + msg + " Ext: " + aux.chave);
                }
            }
        }
    }

    /**
     * Metodo de impressao
     */
    public void imprime() {
        this.central(null, this.raiz, "RAIZ");
    }

    /**
     * Construtor
     * @param nbitsChave 
     */
    public ArvorePatricia(int nbitsChave) {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    /**
     * Metodo de pesquisa
     * @param k = string que sera procurada no texto
     */
    public void pesquisa(String k) {
        this.pesquisa(k, this.raiz);
    }

    /**
     * Metodo de insercao na arvore
     * @param k = string que sera inserida
     * @param pos = posicao da string (linha, coluna)
     */
    public void insere(String k, Posicao pos) {
        this.raiz = this.insere(k, this.raiz,pos);
    }
}
