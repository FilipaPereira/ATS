
/**
 * Não existe qualquer veiculo para a datas escolhidas e com disponibilidade.
 * 
 * 
 * @version (número de versão ou data)
 */
package main.java;

public class NaoExistemVeiculosDisponiveisException extends Exception{
    public NaoExistemVeiculosDisponiveisException (String s){
        super(s);
    }  
}
