package main.java.UnitTests;
import main.java.*;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class VeiculoTest {


    @Test
    public void tempoAteVeiculoPeJa() {
        Veiculo v = new Veiculo();
        Coordinate pos = new Coordinate(45,18.9);
        assertEquals(613,v.tempoAteVeiculoPeJa(pos));

    }

    @Test
    public void tempoViagemCarroJa() {
        Veiculo v = new Veiculo();
        Coordinate destino = new Coordinate(45,18.9);
        assertEquals(2450,v.tempoViagemCarroJa(destino));
    }

    @Test
    public void custoViagem() {
        Veiculo v = new Veiculo();
        v.setPreco(2.5);
        assertEquals(58,v.custoViagem(23.2),0.0);
    }

    @Test
    public void abastecerVeiculo() {
        Veiculo v = new Veiculo();
        v.setConsumo(0.52);
        v.setAutonomia(15);

        v.abastecerVeiculo(25);

        assertEquals(63,v.getAutonomia());

    }

    @Test
    public void verificaAutonomia() {
        Veiculo v = new Veiculo();
        assertFalse(v.verificaAutonomia(10,60));

    }

    @Test
    public void addDatas() {
        Veiculo v = new Veiculo();

        GregorianCalendar dInicio = new GregorianCalendar();
        GregorianCalendar dFim = new GregorianCalendar();
        ParDatas pd = new ParDatas(dInicio,dFim);

        v.addDatas(dInicio,dFim);
        assertTrue(v.getDatasAlugueres().contains(pd));

    }
}