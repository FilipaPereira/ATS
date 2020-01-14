package main.java.UnitTests;
import main.java.*;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ParseDadosTest {

    @Test
    public void parseProprietario() {
        String linha = "Ana,12345,12345@gmail.com,Braga";
        Proprietario p = new Proprietario("Ana","12345","12345@gmail.com","12345","Braga", new GregorianCalendar());
        Proprietario pParsed = ParseDados.parseProprietario(linha);
        p.setDataNasc(pParsed.getDataNasc());
        assertEquals(p, pParsed);
    }

    @Test
    public void parseCliente() {
        String linha = "Bruna,54321,54321@gmail.com,Braga,12.3,-21.5";
        Coordinate cords = new Coordinate(12.3,-21.5);
        Cliente cl = new Cliente("Bruna", "54321", "54321@gmail.com", "54321", "Braga", new GregorianCalendar(), cords);
        Cliente clParsed = ParseDados.parseCliente(linha);
        cl.setDataNasc(clParsed.getDataNasc());
        assertEquals(cl, clParsed);
    }

    @Test
    public void parseVeiculo() {
        String linha = "Electrico,Ford,ME-59-70,12345,104,1.654,0.527,170,-18.2,43.982";
        Coordinate cords = new Coordinate(-18.2, 43.982);
        CarroEletrico car = new CarroEletrico("Ford", "ME-59-70", "12345", 104, 1.654, 0.527,
                170, cords, true);
        Veiculo carParser = ParseDados.parseVeiculo(linha);
        assertEquals(carParser,car);

    }
}