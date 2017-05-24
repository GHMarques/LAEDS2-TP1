package tp1;

import java.util.List;
import controle.Posicao;
import java.util.ArrayList;
public class ArvorePatricia {

    private static abstract class PatNo {
    }

    private static class PatNoInt extends PatNo {

        int index;
        PatNo esq, dir;
    }

    private static class PatNoExt extends PatNo {

        String chave;
        List<Posicao> pos;
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
    // @{\it Retorna o i-\'esimo bit da chave k a partir da esquerda}@
    private int bit(int pos, String palavra) {
        int []bin;
        bin = binGen(palavra);
        //System.out.println(pos);
        return bin[pos];
    }

    // @{\it Verifica se p \'e n\'o externo}@
    private boolean eExterno(PatNo p) {
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    private PatNo criaNoInt(int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt();
        p.index = i;
        p.esq = esq;
        p.dir = dir;
        return p;
    }

    private PatNo criaNoExt(String palavra,Posicao posicao) {
        PatNoExt p = new PatNoExt();
        p.chave = palavra;
        p.pos = new ArrayList<>();
        p.pos.add(posicao);
        return p;
    }

    private void pesquisa(String k, PatNo t) {
        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt) t;
            if (aux.chave.equalsIgnoreCase(k)) {
                System.out.println("Elemento encontrado");
            } else {
                System.out.println("Elemento nao encontrado");
            }
        } else {
            PatNoInt aux = (PatNoInt) t;
            if (this.bit(aux.index, k) == 0) {
                pesquisa(k, aux.esq);
            } else {
                pesquisa(k, aux.dir);
            }
        }
    }

    private PatNo insereEntre(String k, PatNo t, int i,Posicao pos) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) {
            aux = (PatNoInt) t;
        }
        if (this.eExterno(t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
            PatNo p = criaNoExt(k,pos);
            if (this.bit(i, k) == 1) {
                return this.criaNoInt(i, t, p);
            } else {
                return this.criaNoInt(i, p, t);
            }
        } else {
            if (this.bit(aux.index, k) == 1) {
                aux.dir = this.insereEntre(k, aux.dir, i,pos);
            } else {
                aux.esq = this.insereEntre(k, aux.esq, i,pos);
            }
            return aux;
        }
    }

    private PatNo insere(String k, PatNo t, Posicao pos) {
        if (t == null) {
            return this.criaNoExt(k,pos);
        } else {
            PatNo p = t;
            while (!this.eExterno(p)) {
                PatNoInt aux = (PatNoInt) p;
                if (this.bit(aux.index, k) == 1) {
                    p = aux.dir;
                } else {
                    p = aux.esq;
                }
            }
            PatNoExt aux = (PatNoExt) p;
            int i = 1; // @{\it acha o primeiro bit diferente}@
            while ((i <= this.nbitsChave)
                    && (this.bit(i, k) == this.bit(i, aux.chave))) {
                i++;
            }
            if (i > this.nbitsChave) {
                System.out.println("Erro: chave ja esta na arvore");
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

    public void imprime() {
        this.central(null, this.raiz, "RAIZ");
    }

    public ArvorePatricia(int nbitsChave) {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    public void pesquisa(String k) {
        this.pesquisa(k, this.raiz);
    }

    public void insere(String k, Posicao pos) {
        this.raiz = this.insere(k, this.raiz,pos);
    }
}
