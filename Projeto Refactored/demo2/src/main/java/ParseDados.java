/**
 * Classe que contém métodos para efetuar parsing de dados.
 *
 * 
 * 
 * 
 * @version 20190415
 */

package main.java;

import java.util.GregorianCalendar;
import java.util.Random;
import java.util.InputMismatchException;
import static java.lang.System.*;

public class ParseDados{

    private ParseDados(){}

    public static Proprietario parseProprietario(String linha){
        String nome;
        String nif;
        String email;
        String morada;
        String [] dados = linha.split(",");
        GregorianCalendar date = generateDate();

        nome = dados[0];
        nif = dados[1];
        email = dados[2];
        morada = dados[3];

        Proprietario prop = new Proprietario(nome, nif, email, nif, morada, date);

        return prop.clone();
    }

    public static Cliente parseCliente(String linha){
        String nome;
        String nif;
        String email;
        String morada;
        String [] dados = linha.split(",");
        GregorianCalendar date = generateDate();
        double x = 0.0;
        double y = 0.0;


        nome = dados[0];
        nif = dados[1];
        email = dados[2];
        morada = dados[3];

        try {
            x = Double.parseDouble(dados[4]);
            y = Double.parseDouble(dados[5]);
        }
        catch(InputMismatchException exc){
            out.println(exc.getMessage());
        }

        Coordinate cords = new Coordinate(x,y);

        Cliente cli = new Cliente(nome, nif, email, nif, morada, date, cords);

        return cli.clone();

    }

    public static GregorianCalendar generateDate() {
        int ano = new Random().ints(1950, 2000).findFirst().getAsInt();
        int mes = new Random().ints(1, 12).findFirst().getAsInt();
        int dia = new Random().ints(1, 31).findFirst().getAsInt();
        return new GregorianCalendar(ano, mes, dia);
    }

    public static Veiculo parseVeiculo(String linha){
        String [] dados = linha.split(",");

        switch(dados[0]){
            case "Electrico":
                Veiculo ce = parseCarro(linha, 1);
                return ce.clone();
            case "Hibrido":
                Veiculo ch = parseCarro(linha,2);
                return ch.clone();
            case "Gasolina":
                Veiculo cg = parseCarro(linha,3);
                return cg.clone();
            default: break;
        }
        return new Veiculo();
    }

    private static Veiculo parseCarro(String linha, int type){
        String marca;
        String matricula;
        String nif;
        String [] dados = linha.split(",");
        int velocidade;
        int autonomia;
        double x;
        double y;
        double preco;
        double consumo;

        marca = dados[1];
        matricula = dados[2];
        nif = dados[3];

        try {
            velocidade = Integer.parseInt(dados[4]);
            preco = Double.parseDouble(dados[5]);
            consumo = Double.parseDouble(dados[6]);
            autonomia = Integer.parseInt(dados[7]);
            x = Double.parseDouble(dados[8]);
            y = Double.parseDouble(dados[9]);
        }
        catch(InputMismatchException exc){
            out.println(exc.getMessage());
            return new Veiculo();
        }

        Coordinate cords = new Coordinate(x,y);

        if(type == 1){
            //Eletrico
            return new CarroEletrico(marca, matricula, nif, velocidade, preco, consumo,
                    autonomia, cords, true);
        }
        else if(type == 2){
            // Hibrido
            return new CarroHibrido(marca, matricula, nif, velocidade, preco, consumo,
                    autonomia, cords, true);
        }
        else if (type == 3){
            //Gasolina
            return new CarroGasolina(marca, matricula, nif, velocidade, preco, consumo,
                    autonomia, cords, true);
        }
        else return new Veiculo();
    }
}
