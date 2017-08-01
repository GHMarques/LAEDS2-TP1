/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;

import java.util.Stack;

public class Heuristica {
    private int numberOfNodes;
    private Stack<Integer> stack;
    private int pushInicial = 0;

    /**
     * Caso a matriz seja diagonal superior o push inicial e' zero. Caso contrario e' tamanho-1
     * @param pushInicial 
     */
    public void setPushInicial(int pushInicial) {
        this.pushInicial = pushInicial;
    }

    /**
     * Construtor
     */
    public Heuristica() {
        stack = new Stack<Integer>();
    }

    /**
     * Funcao para retornar o custo do menor caminho
     * @param mMatriz
     * @return int
     */
    public int EncontrarMenorCaminho(int[][] mMatriz) {
        int intCusto = 0;
        
        numberOfNodes = mMatriz[1].length;
        int[] visited = new int[numberOfNodes];
        visited[0] = 1;
        stack.push(pushInicial);
        int element, dst = 0, last = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 0;
            min = Integer.MAX_VALUE;
            while (i < numberOfNodes) {
                if (mMatriz[element][i] > 1 && visited[i] == 0) {
                    if (min > mMatriz[element][i]) {
                        min = mMatriz[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                intCusto += min;
                last = dst;
                visited[dst] = 1;
                stack.push(dst);
                minFlag = false;
                continue;
            }
            stack.pop();
        }
        
        intCusto += mMatriz[0][last];
        
        return intCusto;
    }
}