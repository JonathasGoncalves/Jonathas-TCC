/**
 * ****************************************************************************
 *  Compilação:  javac Aresta.java
 *  Execução:    java Aresta
 *
 *  Aresta Ponderada ou Não.
 *
 *****************************************************************************
 */
package grafos.model;



/**
 * Cada aresta é composta por dois números (que representam os vértices)
 */
public class Aresta implements Comparable<Aresta> {

    private final Vertice v1;   //vértice 1 da aresta
    private final Vertice v2;   // vértice 2 da aresta
    private int id;             // id da aresta
    private boolean utilizada;  // váriavel que verifica se ela foi utilizada na solução do algoritmo
    

    /**
     * Inicializa uma aresta (sem peso) entre vértices.
     * @param v1 vértice 1 (origem)
     * @param v2 vértice 2 (destino)
     * @param id id da aresta
     * @throws IndexOutOfBoundsException se o vértice 1 ou vértice 2 forem negativos
     * @throws IllegalArgumentException se o peso for um valor não numérico
     */
    public Aresta(Vertice v1, Vertice v2, int id) {
       
        this.v1 = v1;
        this.v2 = v2;
        this.id = id;
        this.utilizada = false;
    }

    /**
     * 
     * @return returna o valor do atributo utilizada 
     */
    public boolean isUtilizada() {
        return utilizada;
    }

    /**
     * 
     * @param utilizada recebe true quando a aresta foi utilizada
     */
    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public Vertice getV1() {
        return v1;
    }

    public Vertice getV2() {
        return v2;
    }

    
    

    /**
     * Retorna um vértice qualquer desta aresta (origem desta aresta).
     * @return vértice 1 desta aresta
     */
    public Vertice umVertice() {
        return getV1();
    }

  

    
    

    /**
     * Retorna o outro vértice da aresta. Ou seja, o vértice diferente do recebido como parâmetro.
     * @param vertice um vértice qualquer desta aresta
     * @return o vértice diferente do recebido como parâmetro.
     * @throws IllegalArgumentException se o vértico do parâmetro não for um dos vértices da aresta
     */
    public Vertice outroVertice(Vertice vertice) {
        if (vertice == getV1()) {
            return getV2();
        } else if (vertice == getV2()) {
            return getV1();
        } else {
            throw new IllegalArgumentException("Vértice inválido");
        }
    }

    @Override
    public int compareTo(Aresta t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
