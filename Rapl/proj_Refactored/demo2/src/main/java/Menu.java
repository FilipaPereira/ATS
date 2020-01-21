/**
 * Esta classe é responsável pela API de manuseamento do
 * Input de escolha do utilizador e pelo output para o ecrã dos
 * respetivos menus.
 * 
 * 
 * 
 * 
 * @version 20190415
 */

package main.java;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import static java.lang.System.*;


public class Menu{
    
    /** Variáveis de Instância */
    
    /* Opção introduzida pelo utilizador */
    private int opcao;
    /* Lista de todas as opções disponiveis do respetivo Menu */
    private List<String> opcoes;
    
    /**
     * Construtor por parâmetro.
     * @param opcoes
     * É criada uma instância da classe Menu.
     */
    public Menu(String[] opcoes) {
        this.opcao = 0;
        this.opcoes = Arrays.asList(opcoes);
    }
    
    /**
     * Método para obter a última opção lida.
     */
    public int getOpcao(){
        return this.opcao;
    }

    /**
     * Método para apresentar o menu para o ecrã e ler uma opção.
     */
    public void executa(){
        do{
            createMenu();
            this.opcao = lerOpcao();
        }while (this.opcao == -1);
    }
    
    /** 
     * Método responsável por fazer a apresentação do menu respetivo. 
     */
    private void createMenu(){
        out.println("\n **** " + this.opcoes.get(0) + " ****");
        for (int i=1; i < this.opcoes.size(); i++) {
            out.print(i);
            out.print(" - ");
            out.println(this.opcoes.get(i));
        }
        out.println("0 - Sair\n ");
        for (int i = 0; i < this.opcoes.get(0).length() + 10; i++){
            out.print("*");
        }
        out.print("\n");
    }
    
    /**
     * Método que recebe um número mínimo de opções e um máximo, uma mensagem 
     * a apresentar e uma mensagem de erro e lê a opção do utilizador.
     * @param min  Número de opções mínimo.
     * @param max  Número de opções máximo.
     * @param txt  String para mostrar ao utilizador a pedir um novo Int.
     * @param erro String que contém o erro a imprimir para o stdout.
     */
    public static int menuLerInt(int min, int max, String txt, String erro) {
        int valor = 0;
        
        do{
            out.print(txt);
            valor = Input.lerInt(erro, txt);
            if (valor < min || valor > max) {
                out.println(erro);
            }
        } while (valor < min || valor > max);
        return valor;
    }
    
    /** 
     * Método para ler o input do utilizador (opção) e que a valida. 
     */
    private int lerOpcao(){
        int opc;
        Scanner is = new Scanner(in);

        out.print("Escolha a sua opção: ");
        try {
            opc = is.nextInt();
        }
        catch (InputMismatchException e) {
            opc = -1;
        }

        if (opc < 0 || opc > this.opcoes.size()){
            out.println("Opção Inválida!");
            opc = -1;
        }
        return opc;
    }
}
