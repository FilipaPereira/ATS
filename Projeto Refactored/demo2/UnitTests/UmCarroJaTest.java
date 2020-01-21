package main.java.UnitTests;
import main.java.*;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class UmCarroJaTest {

    @Test
    public void registarUtilizador() throws UtilizadorNaoExisteException, UtilizadorJaExisteException {
        Coordinate cords = new Coordinate(12.3,-21.5);
        Cliente cl = new Cliente("Bruna", "54321", "54321@gmail.com", "54321", "Braga", new GregorianCalendar(), cords);
        UmCarroJa ucj = new UmCarroJa();
        ucj.registarUtilizador(cl);
        assertEquals(ucj.getUtilizador("54321@gmail.com"),cl);
    }

    @Test(expected = UtilizadorNaoExisteException.class)
    public void iniciarSessao() throws UtilizadorNaoExisteException, PasswordIncorretaException {
        Coordinate cords = new Coordinate(12.3,-21.5);
        Cliente cl = new Cliente("Bruna", "54321", "54321@gmail.com", "54321", "Braga", new GregorianCalendar(), cords);
        UmCarroJa ucj = new UmCarroJa();

        ucj.iniciarSessao(cl.getEmail(),cl.getPassword());
    }

    @Test(expected = PasswordIncorretaException.class)
    public void iniciarSessao2() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException {
        Coordinate cords = new Coordinate(12.3,-21.5);
        Cliente cl = new Cliente("Bruna", "54321", "54321@gmail.com", "54321", "Braga", new GregorianCalendar(), cords);
        UmCarroJa ucj = new UmCarroJa();
        ucj.registarUtilizador(cl);

        ucj.iniciarSessao(cl.getEmail(),"lala");
    }

    @Test
    public void existeUtilizador() {
        Coordinate cords = new Coordinate(12.3,-21.5);
        Cliente cl = new Cliente("Bruna", "54321", "54321@gmail.com", "54321", "Braga", new GregorianCalendar(), cords);
        UmCarroJa ucj = new UmCarroJa();

        try {
            ucj.registarUtilizador(cl);
        } catch (UtilizadorJaExisteException e) { System.out.println(e.getMessage()); }

        assertTrue(ucj.existeUtilizador(cl.getEmail()));
    }


    @Test
    public void get10ClientesKM() throws NaoExistemClientesException {
        Cliente c1 = new Cliente("", "1", "1@gmail.com", "1", "", new GregorianCalendar(), new Coordinate(1,1));
        Cliente c2 = new Cliente("", "2", "2@gmail.com", "2", "", new GregorianCalendar(), new Coordinate(1,1));
        Cliente c3 = new Cliente("", "3", "3@gmail.com", "3", "", new GregorianCalendar(), new Coordinate(1,1));
        c1.setNKM(12);
        c2.setNKM(110);
        c3.setNKM(50);
        UmCarroJa ucj = new UmCarroJa();

        try {
            ucj.registarUtilizador(c1);
            ucj.registarUtilizador(c2);
            ucj.registarUtilizador(c3);
        } catch (UtilizadorJaExisteException e) { System.out.println(e.getMessage()); }
        List<Cliente> res = ucj.get10ClientesKm();
        assertEquals(c2,res.get(0));
    }

    @Test(expected = NaoExistemClientesException.class)
    public void get10ClientesKM2() throws NaoExistemClientesException {
        Proprietario p1 = new Proprietario();
        Proprietario p2 = new Proprietario();
        UmCarroJa ucj = new UmCarroJa();

        try {
            ucj.registarUtilizador(p1);
            ucj.registarUtilizador(p2);
        } catch (UtilizadorJaExisteException e) { System.out.println(e.getMessage()); }
        ucj.get10ClientesKm();
    }

    @Test
    public void get10ClientesAlugueres() throws UtilizadorJaExisteException, NaoExistemClientesException {
        UmCarroJa ucj = new UmCarroJa();
        Cliente c1 = new Cliente("", "1", "1@gmail.com", "1", "", new GregorianCalendar(), new Coordinate(1,1));
        Cliente c2 = new Cliente("", "2", "2@gmail.com", "2", "", new GregorianCalendar(), new Coordinate(1,1));
        Cliente c3 = new Cliente("", "3", "3@gmail.com", "3", "", new GregorianCalendar(), new Coordinate(1,1));
        Cliente c4 = new Cliente("", "4", "4@gmail.com", "4", "", new GregorianCalendar(), new Coordinate(1,1));
        Cliente c5 = new Cliente("", "5", "5@gmail.com", "5", "", new GregorianCalendar(), new Coordinate(1,1));
        c1.setNAlugueres(2);
        c3.setNAlugueres(10);
        c4.setNAlugueres(8);
        c5.setNAlugueres(8);
        ucj.registarUtilizador(c1);
        ucj.registarUtilizador(c2);
        ucj.registarUtilizador(c3);
        ucj.registarUtilizador(c4);
        ucj.registarUtilizador(c5);

        List<Cliente> res = ucj.get10ClientesAlugueres();
        assertEquals(c3,res.get(0));

    }


    @Test
    public void registarVeiculo() {
        UmCarroJa ucj = new UmCarroJa();
        Veiculo v = new Veiculo("Mazda","FF-23-12","54321",95,1.35,0.5432,150,
                        new Coordinate(-13.9,24-67),false);
        try {
          ucj.registarVeiculo(v);
        }catch (VeiculoJaExisteException e) { System.out.println(e.getMessage()); }
        assertTrue(ucj.existeVeiculo("FF-23-12"));
    }


    @Test
    public void existeVeiculo() throws VeiculoJaExisteException{
        UmCarroJa ucj = new UmCarroJa();
        Veiculo v = new Veiculo();

        ucj.registarVeiculo(v);
        assertTrue(ucj.existeVeiculo(v.getMatricula()));
    }

    @Test
    public void alugueresClassificarCliente() throws UtilizadorJaExisteException, VeiculoJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, NaoExistemAlugueresException {
        UmCarroJa ucj = new UmCarroJa();
        List<Aluguer> als = new ArrayList<>();
        //Criação de Proprietário e Veiculo
        Proprietario p = new Proprietario();
        Veiculo v = new Veiculo();
        p.setNIF("123");
        v.setNIF("123");
        v.setMatricula("AA-12-12");
        ucj.registarVeiculo(v);
        ucj.registarUtilizador(p);
        //Criação de Alugueres
        Aluguer a1 = new Aluguer("","AA-12-12",new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a2 = new Aluguer("","AA-12-12",new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a3 = new Aluguer("","AA-12-12",new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a4 = new Aluguer("","AA-12-12",new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));//N realizado
        a1.setEstadoClassificacao(3); //Já foram efetuadas as classificações
        a2.setEstadoClassificacao(2); //Só o cliente é que classificou
        a1.setRealizado(true);
        a2.setRealizado(true);
        a3.setRealizado(true);
        ucj.registaAluguer(a1);
        ucj.registaAluguer(a2);
        ucj.registaAluguer(a3);
        ucj.registaAluguer(a4);

        //Inicio de sessao e colocar o resultado esperado no array als
        ucj.iniciarSessao("","");
        als.add(a2);
        als.add(a3);

        assertEquals(als,ucj.alugueresClassificarCliente());
    }

    @Test(expected = NaoExistemAlugueresException.class)
    public void alugueresClassificarCliente2() throws UtilizadorJaExisteException, VeiculoJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, NaoExistemAlugueresException {
        UmCarroJa ucj = new UmCarroJa();

        Proprietario p = new Proprietario();
        Veiculo v = new Veiculo();
        p.setNIF("123");
        v.setNIF("123");
        v.setMatricula("AA-12-12");
        ucj.registarVeiculo(v);
        ucj.registarUtilizador(p);

        ucj.iniciarSessao(p.getEmail(),p.getPassword());

        ucj.alugueresClassificarCliente();
    }

    @Test
    public void classificarCliente() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException {
        UmCarroJa ucj = new UmCarroJa();
        Coordinate cords = new Coordinate(12.3,-21.5);
        Cliente cl = new Cliente("Bruna", "54321", "54321@gmail.com", "54321", "Braga", new GregorianCalendar(), cords);
        cl.setClassificacao(90);
        cl.setNAlugueres(5);
        ucj.registarUtilizador(cl);
        ucj.iniciarSessao(cl.getEmail(),cl.getPassword());
        //Aluguer
        Aluguer a = new Aluguer(cl.getEmail(),"AA-12-12",new GregorianCalendar(),new GregorianCalendar(),cords);
        ucj.classificarCliente(a,70);
        Cliente c = (Cliente) ucj.getUtilizador(cl.getEmail());
        assertEquals(32, c.getClassificacao());
        assertEquals(1,a.getEstadoClassificacao());
    }


    @Test(expected = VeiculoNaoExisteException.class)
    public void sinalizarDisponibilidade() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoNaoESeuException, VeiculoNaoExisteException {
        Proprietario p = new Proprietario();
        UmCarroJa ucj = new UmCarroJa();
        ucj.registarUtilizador(p);
        ucj.iniciarSessao(p.getEmail(),p.getPassword());
        ucj.sinalizarDisponibilidade("AA-12-12",false);

    }

    @Test(expected = VeiculoNaoESeuException.class)
    public void abastecerVeiculo() throws UtilizadorJaExisteException, VeiculoJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoNaoESeuException, VeiculoNaoExisteException {
        UmCarroJa ucj = new UmCarroJa();
        //Criar 2 proprietarios e 1 veiculo para o 1º proprietario
        Proprietario p1 = new Proprietario("a","1234","a","123","",new GregorianCalendar());
        Proprietario p2 = new Proprietario("b","4567","b","456","",new GregorianCalendar());
        ucj.registarUtilizador(p1);
        ucj.registarUtilizador(p2);
        Veiculo v = new Veiculo();
        v.setMatricula("AA-12-12");
        v.setNIF(p1.getNIF());
        ucj.registarVeiculo(v);
        //Iniciar sessão com o 2º proprietario que vai tentar abastecer
        ucj.iniciarSessao(p2.getEmail(),p2.getPassword());
        ucj.abastecerVeiculo(v.getMatricula(),15.8);


    }

    @Test
    public void determinarListaEspera() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoJaExisteException {
        UmCarroJa ucj = new UmCarroJa();
        List<Aluguer> alugs = new ArrayList<>();

        Proprietario p = new Proprietario();
        ucj.registarUtilizador(p);
        ucj.iniciarSessao(p.getEmail(),p.getPassword());

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Aluguer a1 = new Aluguer("",v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a2 = new Aluguer("",v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a3 = new Aluguer("",v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        a1.setListaEspera(false);
        alugs.add(a1);
        alugs.add(a2);
        alugs.add(a3);

        //Adicionar os que estao na lista de espera
        ucj.registaAluguer(a2);
        ucj.registaAluguer(a3);

        List<Aluguer> res = ucj.determinarListaEspera(p.getNIF());

        assertEquals(ucj.determinarListaEspera(p.getNIF()),alugs);

    }

    @Test
    public void respostaProp() throws VeiculoJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Proprietario p = new Proprietario();
        ucj.registarUtilizador(p);
        ucj.iniciarSessao(p.getEmail(),p.getPassword());

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Aluguer a1 = new Aluguer("",v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a2 = new Aluguer("",v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));

        ucj.registaAluguer(a1);
        ucj.registaAluguer(a2);

        ucj.respostaProp(true, a2);

        assertFalse(a2.getListaEspera());
        assertTrue(a2.getAceite());
    }

    @Test
    public void determinarListaAlugCli() throws VeiculoNaoESeuException, UtilizadorNaoExisteException, VeiculoJaExisteException, PasswordIncorretaException, UtilizadorJaExisteException {
        UmCarroJa ucj = new UmCarroJa();
        List<Aluguer> alugs = new ArrayList<>();

        Proprietario p = new Proprietario();
        ucj.registarUtilizador(p);
        ucj.iniciarSessao(p.getEmail(),p.getPassword());

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Cliente c1 = new Cliente("a","123","a","","",new GregorianCalendar(),new Coordinate(1,1));
        Cliente c2 = new Cliente("b","456","b","","",new GregorianCalendar(),new Coordinate(1,1));
        ucj.registarUtilizador(c1);
        ucj.registarUtilizador(c2);

        Aluguer a1 = new Aluguer(c1.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a2 = new Aluguer(c2.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));

        ucj.registaAluguer(a1);
        ucj.registaAluguer(a2);
        alugs.add(a1); //Aluguer referente ao cliente 1

        List<Aluguer> res = ucj.determinarListaAlugCli(v.getMatricula(),c1.getEmail());

        assertEquals(res,alugs);
    }

    @Test
    public void altPrecoAluguer() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoJaExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Proprietario p = new Proprietario();
        ucj.registarUtilizador(p);
        ucj.iniciarSessao(p.getEmail(),p.getPassword());

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Aluguer a1 = new Aluguer("",v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        ucj.registaAluguer(a1);

        ucj.altPrecoAluguer(13.25,a1);

        assertTrue(a1.getAlteraPreco());
        assertEquals(13.25, a1.getCustoViagem(), 0.0);
    }

    @Test
    public void totalFactBDates() throws VeiculoJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoNaoESeuException {
        UmCarroJa ucj = new UmCarroJa();

        Proprietario p = new Proprietario();
        ucj.registarUtilizador(p);
        ucj.iniciarSessao(p.getEmail(),p.getPassword());

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        GregorianCalendar dInicio = new GregorianCalendar(2019,Calendar.JANUARY,1);
        GregorianCalendar dFim = new GregorianCalendar(2019,Calendar.DECEMBER, 31);

        Aluguer a1 = new Aluguer("",v.getMatricula(),new GregorianCalendar(2019,Calendar.OCTOBER,1),new GregorianCalendar(2019,Calendar.OCTOBER,2),new Coordinate(1,1));
        Aluguer a2 = new Aluguer("",v.getMatricula(),new GregorianCalendar(2019,Calendar.MARCH,12),new GregorianCalendar(2019,Calendar.MARCH,12),new Coordinate(2,4));
        Aluguer a3 = new Aluguer("",v.getMatricula(),new GregorianCalendar(2018,Calendar.MARCH,2),new GregorianCalendar(2018,Calendar.MARCH,2),new Coordinate(3,4));
        a1.setCustoViagem(23);
        a2.setCustoViagem(42);
        a3.setCustoViagem(61); //Não esta entre as datas
        a1.setAceite(true);
        a2.setAceite(true);
        a3.setAceite(true);

        ucj.registaAluguer(a1);
        ucj.registaAluguer(a2);
        ucj.registaAluguer(a3);

        double val = ucj.totalFactBDates(v.getMatricula(),dInicio,dFim);
        assertEquals(val, 65,0.0);

    }

    @Test
    public void alugueresClassificarVeiculo() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoJaExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        Proprietario p = new Proprietario("a","123","a","","",new GregorianCalendar());
        ucj.registarUtilizador(p);

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Aluguer a1 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a2 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(2,4));
        Aluguer a3 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(3,4));
        Aluguer a4 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(3,4));
        a1.setEstadoClassificacao(3); //Ja foi avaliado
        a2.setEstadoClassificacao(1);
        a1.setRealizado(true);
        a2.setRealizado(true);
        a3.setRealizado(true);

        ucj.registaAluguer(a1);
        ucj.registaAluguer(a2);
        ucj.registaAluguer(a3);
        ucj.registaAluguer(a4); //este nao foi realizado

        List<Aluguer> alugs = new ArrayList<>();
        alugs.add(a2);
        alugs.add(a3);
        List<Aluguer> res = ucj.alugueresClassificarVeiculo();

        assertEquals(alugs,res);

    }

    @Test
    public void classificarVeiculo() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoJaExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        Proprietario p = new Proprietario("","123","a","","",new GregorianCalendar());
        ucj.registarUtilizador(p);

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Aluguer a1 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a2 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(2,4));
        a1.setEstadoClassificacao(1);

        ucj.registaAluguer(a1);
        ucj.registaAluguer(a2);

        ucj.classificarVeiculo(a1,80);
        ucj.classificarVeiculo(a2,90);

        assertEquals(3,a1.getEstadoClassificacao());
        assertEquals(2,a2.getEstadoClassificacao());

    }


    @Test
    public void maisProximo() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, NaoExistemVeiculosDisponiveisException, VeiculoJaExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        c.setPosicao(new Coordinate(0.4,0.4));
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        Veiculo v1 = new Veiculo("Ford","AZ-12-12","",90,3.45,1.3,100,new Coordinate(4.5,1),true);
        Veiculo v2 = new Veiculo("Citroen","DF-12-09","",92,2.70,1.3,120,new Coordinate(1,1),false);
        Veiculo v3 = new Veiculo("Mercedes","LO-34-01","",110,3.70,1.9,120,new Coordinate(3,2),true);


        ucj.registarVeiculo(v1);
        ucj.registarVeiculo(v2);
        ucj.registarVeiculo(v3);

        ParDatas pd = new ParDatas();

        List<Veiculo> res = ucj.maisProximo(new Coordinate(2,2),pd,2);
        assertEquals(v3,res.get(0));

    }

    @Test(expected = NaoExistemVeiculosDisponiveisException.class)
    public void maisBarato() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoJaExisteException, NaoExistemVeiculosDisponiveisException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        Veiculo v1 = new Veiculo("Ford","AZ-12-12","",90,3.45,1.3,100,new Coordinate(1,1),false);
        Veiculo v2 = new Veiculo("Ford","DF-12-09","",92,2.70,1.3,120,new Coordinate(1,1),false);

        ucj.registarVeiculo(v1);
        ucj.registarVeiculo(v2);

        ParDatas pd = new ParDatas();
        ucj.maisBarato(new Coordinate(2,2),pd,1);

    }

    @Test
    public void maisBaratoNoPerimetro() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoJaExisteException, NaoExistemVeiculosDisponiveisException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());


        Veiculo v1 = new Veiculo("Ford","AZ-12-12","",90,3.45,1.3,100,new Coordinate(1,1),true);
        Veiculo v2 = new Veiculo("Mercedes","LO-34-01","",110,3.70,1.9,120,new Coordinate(13.2,1),true);


        ucj.registarVeiculo(v1);
        ucj.registarVeiculo(v2);

        ParDatas pd = new ParDatas();

        List<Veiculo> res = ucj.maisBaratoNoPerimetro(new Coordinate(2,2),c.getPosicao(),pd,4,1);
        assertEquals(v1,res.get(0));
    }

    @Test(expected = VeiculoIndisponivelException.class)
    public void veiculoEspecifico() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoJaExisteException, VeiculoIndisponivelException, VeiculoNaoExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        Veiculo v1 = new Veiculo("Ford","AZ-12-12","",90,3.45,1.3,100,new Coordinate(1,1),false);
        ucj.registarVeiculo(v1);

        ucj.veiculoEspecifico(new Coordinate(1,1),new ParDatas(),v1.getMatricula());

    }

    @Test
    public void determinadaAutonomia() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoJaExisteException, NaoExistemVeiculosDisponiveisException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        Veiculo v1 = new Veiculo("Ford","AZ-12-12","",90,3.45,1.3,100,new Coordinate(1,1),true);
        Veiculo v2 = new Veiculo("Renault","XG-78-52","",80,3.25,1.22,100,new Coordinate(1,1),true);
        ucj.registarVeiculo(v1);
        ucj.registarVeiculo(v2);

        List<Veiculo> res = ucj.determinadaAutonomia(new Coordinate(1,1), new ParDatas(), 70,110,1);

        assertEquals(v1,res.get(0));


    }

    @Test
    public void getAlugueresCliente() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoJaExisteException, NaoExistemVeiculosDisponiveisException, NaoEfetuouNenhumAluguerException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        Proprietario p = new Proprietario("","123","a","","",new GregorianCalendar());
        ucj.registarUtilizador(p);

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Aluguer a1 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(1,1));
        Aluguer a2 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(2,4));
        Aluguer a3 = new Aluguer("3",v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(3,4));
        Aluguer a4 = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),new Coordinate(3,4));

        ucj.registaAluguer(a1);
        ucj.registaAluguer(a2);
        ucj.registaAluguer(a3);
        ucj.registaAluguer(a4);

        List<Aluguer> res = ucj.getAlugueresCliente(c.getEmail());

        assertFalse(res.contains(a3));
        assertEquals(3,res.size());

    }

    @Test(expected = NaoEfetuouNenhumAluguerException.class)
    public void getAlugueresCliente2() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoJaExisteException, NaoExistemVeiculosDisponiveisException, NaoEfetuouNenhumAluguerException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        ucj.getAlugueresCliente(c.getEmail());
    }

    @Test
    public void maisPertoJa() throws UtilizadorNaoExisteException, PasswordIncorretaException, UtilizadorJaExisteException, VeiculoJaExisteException, NaoExistemVeiculosDisponiveisException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        CarroGasolina v1 = new CarroGasolina("Ford","AZ-12-12","",90,3.45,1.3,100,new Coordinate(2,1),true);
        CarroGasolina v2 = new CarroGasolina("Ford","DF-12-09","",92,2.70,1.3,120,new Coordinate(1,1),true);
        CarroHibrido v3 = new CarroHibrido("Mercedes","LO-34-01","",110,3.70,1.9,120,new Coordinate(1,1),true);


        ucj.registarVeiculo(v1);
        ucj.registarVeiculo(v2);
        ucj.registarVeiculo(v3);

        Veiculo res = ucj.maisPertoJa(new Coordinate(4,4), new ParDatas(), "CarroGasolina");
        assertEquals(v2,res);
    }

    @Test
    public void maisBaratoJa() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoJaExisteException, NaoExistemVeiculosDisponiveisException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);
        ucj.iniciarSessao(c.getEmail(),c.getPassword());

        //Eletrico esta mais perto, mas quer um a gasolina
        CarroGasolina v1 = new CarroGasolina("Ford","AZ-12-12","",90,3.45,1.3,100,new Coordinate(2,1),true);
        CarroEletrico v2 = new CarroEletrico("Ford","DF-12-09","",92,2.70,1.3,120,new Coordinate(1,1),false);
        CarroEletrico v3 = new CarroEletrico("Mercedes","LO-34-01","",110,3.70,1.9,120,new Coordinate(1,1),true);


        ucj.registarVeiculo(v1);
        ucj.registarVeiculo(v2);
        ucj.registarVeiculo(v3);

        Veiculo res = ucj.maisBaratoJa(new Coordinate(4,4), new ParDatas(), "CarroEletrico");
        assertEquals(v3,res);
    }

    @Test
    public void alterarPosCliente() throws UtilizadorJaExisteException, UtilizadorNaoExisteException, PasswordIncorretaException, VeiculoJaExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        ucj.registarUtilizador(c);

        Proprietario p = new Proprietario("","123","a","","",new GregorianCalendar());
        ucj.registarUtilizador(p);

        Veiculo v = new Veiculo();
        v.setNIF(p.getNIF());
        ucj.registarVeiculo(v);

        Coordinate destino = new Coordinate(1,1);

        Aluguer a = new Aluguer(c.getEmail(),v.getMatricula(),new GregorianCalendar(),new GregorianCalendar(),destino);
        ucj.registaAluguer(a);

        ucj.alterarPosCliente(c.getEmail(),destino);
        Cliente res = (Cliente) ucj.getUtilizador(c.getEmail());

        assertEquals(destino,res.getPosicao());
        assertEquals(1,res.getNAlugueres());
    }

    @Test
    public void classificarClienteJa() throws UtilizadorJaExisteException, UtilizadorNaoExisteException {
        UmCarroJa ucj = new UmCarroJa();

        Cliente c = new Cliente();
        c.setClassificacao(95);
        c.setNAlugueres(3);
        ucj.registarUtilizador(c);
        ucj.classificarClienteJa(c.getEmail(),70);

        Cliente res = (Cliente) ucj.getUtilizador(c.getEmail());
        assertEquals(55,res.getClassificacao());

    }

}