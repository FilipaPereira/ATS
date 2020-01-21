/**
 * Classe responsável por enviar uma excepção, caso o aluguer
 * não exista. Esta invoca a super classe exception e devolve uma
 * exception para o ecrã.
 * 
 */
package main.java;

public class AluguerNaoExisteException extends Exception{
    public AluguerNaoExisteException (String s){
        super(s);
    }
}
