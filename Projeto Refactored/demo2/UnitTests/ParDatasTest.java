package main.java.UnitTests;
import main.java.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ParDatasTest {

    @Test //Já houver aluguer para os dias do pedido
    public void isAvailable() {
        GregorianCalendar dataInicioPed = new GregorianCalendar(2019, Calendar.MARCH,20);
        GregorianCalendar dataFimPed = new GregorianCalendar(2019, Calendar.MARCH,21);
        ParDatas pdPedido = new ParDatas(dataInicioPed,dataFimPed);
        GregorianCalendar dataInicioAlug = new GregorianCalendar(2019, Calendar.MARCH,19);
        GregorianCalendar dataFimAlug = new GregorianCalendar(2019, Calendar.MARCH,22);
        ParDatas pdAluguer = new ParDatas(dataInicioAlug,dataFimAlug);

        assertFalse(pdAluguer.isAvailable(pdPedido));

    }

    @Test //Não houver aluguer para os dias do pedido
    public void isAvailable2() {
        GregorianCalendar dataInicioPed = new GregorianCalendar(2019, Calendar.MARCH,22);
        GregorianCalendar dataFimPed = new GregorianCalendar(2019, Calendar.MARCH,23);
        ParDatas pdPedido = new ParDatas(dataInicioPed,dataFimPed);
        GregorianCalendar dataInicioAlug = new GregorianCalendar(2019, Calendar.MARCH,20);
        GregorianCalendar dataFimAlug = new GregorianCalendar(2019, Calendar.MARCH,21);
        ParDatas pdAluguer = new ParDatas(dataInicioAlug,dataFimAlug);

        assertTrue(pdAluguer.isAvailable(pdPedido));

    }

    @Test //Houver sobreposição entre os dias do aluguer e do pedido
    public void isAvailable3() {
        GregorianCalendar dataInicioPed = new GregorianCalendar(2019, Calendar.MARCH,20);
        GregorianCalendar dataFimPed = new GregorianCalendar(2019, Calendar.MARCH,21);
        ParDatas pdPedido = new ParDatas(dataInicioPed,dataFimPed);
        GregorianCalendar dataInicioAlug = new GregorianCalendar(2019, Calendar.MARCH,21);
        GregorianCalendar dataFimAlug = new GregorianCalendar(2019, Calendar.MARCH,22);
        ParDatas pdAluguer = new ParDatas(dataInicioAlug,dataFimAlug);

        assertFalse(pdAluguer.isAvailable(pdPedido));

    }
}