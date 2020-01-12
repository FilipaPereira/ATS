/**
 * Esta classe é responsável pela apresentação do menu ao utilizador e
 * pelo tratamento do input / output.
 * As variáveis de instância são a classe UmCarroJa que contém as
 * estruturas de dados utilizadas, bem como manuseamento das mesmas, para
 * além de três menus principais, gerados a partir da classe Menu.
 * 
 * 
 * 
 * 
 * @version 20190415
 */

package main.java;

import java.io.*;
import static java.lang.System.*;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

import java.util.TreeSet;
import java.util.stream.Collectors;

import java.io.IOException;
import java.util.InputMismatchException;

public class UmCarroJaApp{
    
    /** Variáveis de Classe */
    
    /* Instância da classe UmCarroJa */
    private static UmCarroJa ucj;
    
    /* Menu inicial da aplicação */
    private static Menu menuInicial;
    /* Menu dos utilizadores da aplicação */
    private static Menu menuUtilizador;
    /* Menu do administrador da aplicação */
    private static Menu menuAdmin;
    /* Menu do proprietário */
    private static Menu menuProprietario;
    /* Menu do cliente */
    private static Menu menuCliente;

    private static String ficheiroDados = "UCJ.dat";

    /*Strings of messages to the user to avoid duplications*/
    private static String latInvalid = "Latitude Inválida";
    private static String insertLat = "Digite a Latitude do Destino";
    private static String insertLatAgain = "Digite Novamente a Latitude";
    private static String longInvalid = "Longitude Inválida";
    private static String insertLong = "Digite a Longitude do Destino";
    private static String insertLongAgain = "Digite Novamente a Longitude";
    private static String screenSep = "------------------------------------------------\n";
    private static String errorMatricula = "Matrícula Inválida!";
    private static String errorEmail = "Email Inválido!";
    private static String insertMatricula = "Digite a matricula do Veículo: ";
    private static String insertMatriculaAgain = "Digite novamente a matricula do veículo: ";
    private static String noRentals = "Não existem alugueres entre essas datas.";
    private static String doesntExist = " não existe!\n";
    private static String doesntBelong = " não lhe pertence!\n";
    private static String alreadyRegistered = " já está registado!\n";
    private static String car = "O Veículo ";
    private static String endDateRental = "Digite a data de fim de aluguer (dd-mm-aaaa): ";
    private static String endDateRentalAgain ="Digite novamente a Data de Fim de Aluguer (dd-mm-aaaa): ";
    private static String endDateError = "Data de Fim de Aluguer Inválida!";
    private static String beginDateRental = "Digite a Data de Início (dd-mm-aaaa): ";
    private static String beginDateRentalAgain ="Digite novamente a Data de Início de Aluguer (dd-mm-aaaa): ";
    private static String beginDateError = "Data de Início de Aluguer Inválida!";
    private static String nrCarsScreen = "Qual o Número de Veículos que Deseja Ver: ";
    private static String nrError= "Valor Inválido";
    private static String nrCarsScreenAgain = "Digite Novamente o Número de Veículos que Deseja Ver: ";

    
    /** O construtor é privado, pois não queremos instâncias da mesma. */
    private UmCarroJaApp() {}

    /** Métodos de Classe */


    /**
     * @brief Inicia a aplicação lendo o ficheiro objeto que contém
     * a data de início da aplicacação e os dados.
     */
    private static void initApp() {
        GregorianCalendar date = new GregorianCalendar();
        try (FileInputStream fis = new FileInputStream(ficheiroDados);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
                date = (GregorianCalendar) ois.readObject();
                ucj = (UmCarroJa) ois.readObject();
        }
        catch (ClassNotFoundException e){
            ucj = new UmCarroJa();
            out.println("Erro ao ler os dados! Ficheiro com formato desconhecido!\n");
        }
        catch (FileNotFoundException e){
            ucj = new UmCarroJa();
            out.println("O ficheiro de dados não foi encontrado!\n");
        }
        catch (IOException e) {
            ucj = new UmCarroJa();
            out.println("Erro ao ler os dados!\nErro de leitura.\n");
        }
        ucj.setDataInicioApp(date);
    }

    /**
     * Inicia os diferentes Menus da aplicação, respetivamente o Menu Inicial,
     * o Menu do Proprietário e o Menu do Cliente.
     */
    private static void initMenus(){
        String[] inicial =      {"Menu Inicial",
                                 "Menu Utilizador",
                                 "Menu Administração",
                                 "Guardar Dados da Aplicação"};
                                 
        String[] utilizador =   {"Menu Utilizador",
                                 "Iniciar Sessão", 
                                 "Registar Utilizador"};
                                 
        String[] admin =        {"Menu Administração",
                                 "Extrato de Aluguer de uma Viatura num Determinado Período",
                                 "10 Clientes com Mais Alugueres",
                                 "10 Clientes com Mais Km Percorridos"};
        
        String[] proprietario = {"Menu Proprietário",
                                 "Registar Viatura",
                                 "Sinalizar Viatura Disponível", 
                                 "Abastecer Veículo", 
                                 "Alterar o Preço por Km", 
                                 "Aceitar/Rejeitar Alugueres", 
                                 "Registar Suplemento no Preço da Viagem",
                                 "Meus Alugueres",
                                 "Valor Faturado entre Datas"};
        
        String[] cliente =      {"Menu Cliente",
                                 "Alugar Veículo Mais Próximo", 
                                 "Alugar Veículo Mais Barato", 
                                 "Alugar Veículo Mais Barato num Perímetro", 
                                 "Alugar Viatura Específica", 
                                 "Alugar Viatura com Determinada Autonomia",
                                 "Meus Alugueres"};
        
        menuInicial = new Menu(inicial);
        menuUtilizador = new Menu(utilizador);
        menuAdmin = new Menu(admin);
        menuProprietario = new Menu(proprietario);
        menuCliente = new Menu(cliente);
    }

    /**
     * @brief Método responsável por ler a data de hoje do Input e fazer a comparação com a data
     * guardada no ficheiro de dados.
     */
    private static void lerData(){
        GregorianCalendar dataAtual;
        out.print("Digite a Data do Dia de Hoje (dd-mm-aaaa): ");
        dataAtual  = Input.lerData("Data de Hoje Inválida! Digite Novamente a Data (dd-mm-aaaa): ", "Digite a Data do Dia de Hoje (dd-mm-aaaa): ");
        ucj.setDataInicioApp(dataAtual);
        UmCarroJaApp.guardarDados();
    }

    /**
     * Método responsável por limpar o terminal do IDE BlueJ.
     * Retirado da página de tips da blueJ.org.
     *
     * Este método não deverá funcionar noutros IDE's excepto BlueJ.
     */
    private static void clearScreen(){
        out.print('\u000C');
    }
    
    public static void main(String[] args){
        initApp();
        initMenus();
        lerDadosTXT("logsPOO_carregamentoInicial.bak");
        out.println("NÚMERO UTILIZADORES: " + ucj.getNUsers());
        out.println("NÚMERO VEÍCULOS: " + ucj.getNVeiculos());
        out.println("NÚMERO ALUGUERES: " + ucj.getNAlugs());
        lerData();
        
        ucj.alugueresEfetuados();

        do{
            UmCarroJaApp.clearScreen();
            menuInicial.executa();
            switch(menuInicial.getOpcao()){
                case 1: menuUtilizador();
                        break;
                case 2: menuAdmin();
                        break;
                case 3: guardarDados();
                        break;
                default: break;
            }
        }while(menuInicial.getOpcao() != 0);
        guardarDados();
    }
    
    /******************************************************************************
     *                            MENU DOS UTILIZADORES                           *
     ******************************************************************************/
     
    private static void menuUtilizador(){
        UmCarroJaApp.clearScreen();
        do{
            menuUtilizador.executa();
            if (menuUtilizador.getOpcao() == 1) {
                iniciarSessao();
            } else if (menuUtilizador.getOpcao() == 2) {
                registarUtilizador();
            }
        }while(menuUtilizador.getOpcao() != 0);
    }
    
    /**
     * @brief Método responsável pela exibição das informações que permitem a um utilizador autenticar-se na aplicação.
     * 
     * Primeiro pede-se e verifica-se o email e a password do utilizador. Caso o login esteja correto, verifica-se se
     * se trata de um cliente ou de um proprietário e chama-se o método respetivo para iniciar sessão. São ainda lançadas
     * as exceções UtilizadorNaoExisteException caso o utilizador não esteja registado e a exceção
     * PasswordIncorretaException, caso a password esteja incorreta.
     */
    private static void iniciarSessao(){
        String email;
        String password;
        
        UmCarroJaApp.clearScreen();
        
        out.print("Digite o seu Email: ");
        email = Input.lerString(errorEmail, "Digite novamente o seu Email: ");
        
        out.print("Digite a sua Password: ");
        password = Input.lerString(errorEmail, "Digite novamente a sua Password: ");

        iniciarSessaoAux(email, password);
    }

    private static void iniciarSessaoAux(String email, String password) {
        try{
            ucj.iniciarSessao(email, password);
            loginAux(email);
        }
        catch (UtilizadorNaoExisteException u){
            out.println("O utilizador com o email: " + u.getMessage() + doesntExist);
        }
        catch (PasswordIncorretaException p){
            out.println("A password introduzida: " + p.getMessage() + " está incorreta!");
        }
    }

    private static void loginAux(String email) {
        try{
            Utilizador user = ucj.getUtilizador(email);

            if (user instanceof Proprietario){
                UmCarroJaApp.sessaoProprietario();
            }
            else{
                UmCarroJaApp.sessaoCliente();
            }
        }catch (UtilizadorNaoExisteException e){
            out.println("O utilizador com o email: " + e.getMessage() + doesntExist);
        }
    }
    
    /**
     * @brief Método responsável por proceder ao registo de um utilizador na aplicação.
     *
     * São pedidas todas as informações necessárias para o registo na aplicação do utilizador
     * e no final é perguntado qual o tipo de conta que pretende. Posteriormente este é
     * inserido na base de dados da aplicação.
     */
    private static void registarUtilizador(){
        String email = "";
        String password;
        String nome;
        String nif;
        String morada;
        GregorianCalendar dataNascimento;
        
        boolean existe = true;
        int tipoUser;
        
        UmCarroJaApp.clearScreen();
        
        while(existe){
            out.print("Digite o seu Email: ");
            email = Input.lerString(errorEmail, "Digite novamente o seu Email: ");
            existe = ucj.existeUtilizador(email);
            if (existe){
                out.println("Já existe um utilizador com o email introduzido!");
            }
        }
        
        out.print("Digite a sua Password: ");
        password = Input.lerString("Password Inválida!", "Digite novamente a sua Password: ");
        
        out.print("Digite o seu Nome Completo: ");
        nome = Input.lerString("Nome Inválido!", "Digite novamente o seu Nome Completo: ");
        
        out.print("Digite o seu NIF: ");
        nif = Input.lerString("NIF Inválido!", "Digite novamente o seu NIF: ");
        
        out.print("Digite a sua Morada: ");
        morada = Input.lerString("Morada Inválida!", "Digite novamente a sua Morada: ");
        
        out.print("Digite a sua Data de Nascimento (dd-mm-aaaa): ");
        dataNascimento = Input.lerData("Data de Nascimento Inválida!", "Digite novamente a sua Data de Nascimento (dd-mm-aaaa): ");
            
        tipoUser = Menu.menuLerInt(1, 2, "\n******************\n1 - Proprietário\n2 - Cliente.\nEscolha o Tipo de Utilizador: ",
                                 "Tipo de Utilizador Inválido!, Tente novamente!");
                                 
        if (tipoUser == 1){
            UmCarroJaApp.registarProprietario(nome, nif, email, password, morada, dataNascimento);
        }else{
            UmCarroJaApp.registarCliente(nome, nif, email, password, morada, dataNascimento);
        }
    }
    
    private static void registarProprietario(String nome, String nif, String email, String password, 
                                            String morada, GregorianCalendar data){
                                                
        Proprietario prop = new Proprietario(nome, nif, email, password, morada, data);
                                            
        try {
            ucj.registarUtilizador(prop);
            guardarDados();
        }
        catch (UtilizadorJaExisteException e) {
            out.println("O proprietário " + e.getMessage() +  alreadyRegistered);
        }
    }
    
    private static void registarCliente(String nome, String nif, String email, String password, 
                                       String morada, GregorianCalendar data){
        double latitude;
        double longitude;

        out.print("Digite a sua Latitude: ");
        latitude = Input.lerDouble(latInvalid, "Digite novamente a sua Latitude: ");

        out.print("Digite a sua Longitude: ");
        longitude = Input.lerDouble(longInvalid, "Digite novamente a sua Longitude: ");

        Coordinate cords = new Coordinate(latitude, longitude);

        Cliente cli = new Cliente(nome, nif, email, password, morada, data, cords);

        try {
            ucj.registarUtilizador(cli);
            guardarDados();
        }
        catch (UtilizadorJaExisteException e) {
            out.println("O cliente " + e.getMessage() +  alreadyRegistered);
        }
    }
    
    /******************************************************************************
     *                         FIM DO MENU DOS UTILIZADORES                       *
     ******************************************************************************/


    /******************************************************************************
     *                            MENU DA ADMINISTRAÇÃO                           *
     ******************************************************************************/
    
    private static void menuAdmin(){
        UmCarroJaApp.clearScreen();
        do{
            menuAdmin.executa();
            if (menuAdmin.getOpcao() == 1) {
                extratoAluguerViatura();
            } else if (menuAdmin.getOpcao() == 2) {
                clientesMaisAlugueres();
            } else if (menuAdmin.getOpcao() == 3) {
                clientesComMaisKm();
            }
        }while(menuAdmin.getOpcao() != 0);
        menuInicial.executa();
    }

    private static void extratoAluguerViatura(){
        String matricula;
        GregorianCalendar dataInicio;
        GregorianCalendar dataFim;
        UmCarroJaApp.clearScreen();

        out.print(insertMatricula);
        matricula = Input.lerString(errorMatricula, insertMatriculaAgain);

        do {
            out.print(beginDateRental);
            dataInicio = Input.lerData(beginDateError, beginDateRentalAgain);

            out.print(endDateRental);
            dataFim = Input.lerData(endDateError, endDateRentalAgain);
        }while (dataFim.before(dataInicio));

        List<Aluguer> aux;
        List<Aluguer> alugs = new ArrayList<>();
        try{
            aux = ucj.getAlugueresVeiculo(matricula);
            alugs = filterAlugueresBD(aux, dataInicio, dataFim);
        } catch(VeiculoNaoExisteException e) {
            out.println(car + e.getMessage() + doesntExist);
        }
        if(alugs.isEmpty()) {
            out.print(noRentals);
        } else {
            for(Aluguer a : alugs){
                out.println(screenSep);
                out.println(a.toString());
                out.println(screenSep);
            }
        }
    }

    private static void clientesComMaisKm(){
        List <Cliente> lista = new ArrayList<>();

        try{
            lista = ucj.get10ClientesKm();
        }
        catch(NaoExistemClientesException e){
            out.println(e.getMessage());
        }

        for(Cliente c : lista){
            out.println(screenSep);
            out.println(c.toString());
            out.println(screenSep);
        }
    }

    private static void clientesMaisAlugueres(){
        List <Cliente> lista = new ArrayList<>();

        try{
            lista = ucj.get10ClientesAlugueres();
        }
        catch(NaoExistemClientesException e){
            out.println(e.getMessage());
        }

        for(Cliente c : lista){
            out.println(screenSep);
            out.println(c.toString());
            out.println(screenSep);
        }
    }
     
    /******************************************************************************
     *                         FIM DO MENU DA ADMINISTRAÇÃO                       *
     ******************************************************************************/

    
    /******************************************************************************
     *                              MENU PROPRIETÁRIOS                            *
     *****************************************************************************/
     
    private static void sessaoProprietario(){
        do{
            classificarClientes();
            clearScreen();
            menuProprietario.executa();
            switch(menuProprietario.getOpcao()){
                case 1: registarVeiculoProp();
                    break;
                case 2: setVeiculoDisponivel();
                    break;
                case 3: abastecerVeiculoProp();
                    break;
                case 4: altPKMVeiculo();
                    break;
                case 5: analisarAlugueres();
                    break;
                case 6: suplementoPrecoAlug();
                    break;
                case 7: getListaDeAlugueres();
                    break;
                case 8: calcFactBDates();
                    break;
                default: break;
            }
        }while(menuProprietario.getOpcao() != 0);
        UmCarroJaApp.guardarDados();
        ucj.logoutUtilizador();
        menuUtilizador.executa();
    }

    private static void classificarClientes(){
        List<Aluguer> classif = new ArrayList<>();
        try{
            classif = ucj.alugueresClassificarCliente();
        }catch (NaoExistemAlugueresException e){
            out.println(e.getMessage());
        }

        boolean correto = false;
        int classificacao = 0;

        for (Aluguer alug : classif){
            out.println("--------------------- Aluguer ---------------------");
            out.println("Matrícula: " + alug.getMatricula());
            out.println("Email do Cliente: " + alug.getEmail());
            out.println("Data de Início do Aluguer: " + alug.getDataFim());
            out.println("Data de Fim do Aluguer: " + alug.getDataFim());
            out.println(screenSep);
            do {
                out.print("Digite uma classificação para o cliente com o email: " + alug.getEmail() + " (0-100): ");
                classificacao = Input.lerInt("Classificação Inválida!", "Digite novamente a classificação (0-100): ");
                if (classificacao >= 0 && classificacao <= 100){
                    correto = true;
                }
            }while(!correto);
            ucj.classificarCliente(alug, classificacao);
        }
    }
     
    private static void registarVeiculoProp(){
        String marca;
        String matricula = "";
        String nif = ucj.getUserNIF();
        int velocidade;
        double preco;
        double consumo;
        int autonomia;
        Coordinate posicao;
        double latitude;
        double longitude;
        boolean disponivel;

        boolean existe = true;
        int tipoVeiculo;

        UmCarroJaApp.clearScreen();


        while(existe){
            out.print(insertMatricula);
            matricula = Input.lerString(errorMatricula, insertMatriculaAgain);
            existe = ucj.existeVeiculo(matricula);
            if (existe){
                out.println("Já existe um veículo com a matricula introduzida!");
            }
        }

        out.print("Digite a marca do Veículo: ");
        marca = Input.lerString("Marca Inválida!", "Digite novamente a marca do veículo: ");

        out.print("Digite o velocidade média do veículo: ");
        velocidade = Input.lerInt("Velocidade média do veículo Inválida!", "Digite novamente a velocidade média do veículo: ");

        out.print("Digite o preço base por Km do veículo: ");
        preco = Input.lerDouble("Preço base por Km do veículo Inválida!", "Digite novamente o preço base por Km do veículo: ");

        out.print("Digite o consumo por Km percorrido do veículo: ");
        consumo = Input.lerDouble("Consumo por Km percorrido do veículo Inválida!", "Digite novamente o consumo por Km percorrido do veículo: ");

        out.print("Digite a autonomia do veículo: ");
        autonomia = Input.lerInt("Autonomia do veículo Inválida!", "Digite novamente a autonomia do veículo: ");

        out.print("Digite a disponibilidade do veículo [true - disponível, false - indisponível]: ");
        disponivel = Input.lerBoolean("Disponobilidade do veículo Inválida!", "Digite novamente a disponibilidade do veículo: ");

        out.print("Digite o latitude de localização do veículo: ");
        latitude = Input.lerDouble("Latitude de localização do veículo Inválida!", "Digite novamente a latitude de localização do veículo: ");

        out.print("Digite o longitude de localização do veículo: ");
        longitude = Input.lerDouble("Longitude de localização do veículo Inválida!", "Digite novamente a longitude de localização do veículo: ");

        posicao = new Coordinate(latitude, longitude);

        tipoVeiculo = Menu.menuLerInt(1, 3, "\n******************\n1 - Combustível.\n2 - Elétrico.\n3 - Hibrido.\nEscolha o Tipo de Veículo: ",
                "Tipo de Veículo Inválido!, Tente novamente!");
        Veiculo vr;
        if (tipoVeiculo == 1){
            vr = new CarroGasolina(marca,matricula,nif,velocidade,preco,consumo, autonomia, posicao,disponivel);
        }else{
            if (tipoVeiculo == 2){
                vr =  new CarroEletrico(marca,matricula,nif,velocidade,preco,consumo, autonomia, posicao,disponivel);
            }else{
                vr = new CarroHibrido(marca,matricula,nif,velocidade,preco,consumo, autonomia, posicao,disponivel);
            }
        }

        try {
            ucj.registarVeiculo(vr);
            UmCarroJaApp.guardarDados();
        }
        catch (VeiculoJaExisteException e) {
            out.println(car + e.getMessage() + alreadyRegistered);
        }
    }   
     
    private static void setVeiculoDisponivel(){
        String matricula;
        boolean disp;

        UmCarroJaApp.clearScreen();

        out.print(insertMatricula);
        matricula = Input.lerString(errorMatricula, insertMatriculaAgain);


        out.print("Digite a disponibilidade do veículo: ");
        disp = Input.lerBoolean("Disponibilidade do veículo Inválida!", "Digite novamente a disponibilidade do veículo: ");

        try {
            ucj.sinalizarDisponibilidade(matricula, disp);
            UmCarroJaApp.guardarDados();
        }
        catch (VeiculoNaoExisteException e) {
            out.println(car + e.getMessage() +  doesntExist);
        }
        catch (VeiculoNaoESeuException e2) {
            out.println(car + e2.getMessage() +  doesntBelong);
        }
    }
    
    private static void abastecerVeiculoProp(){
        String matricula;
        double abast;

        UmCarroJaApp.clearScreen();

        out.print(insertMatricula);
        matricula = Input.lerString(errorMatricula, insertMatriculaAgain);


        out.print("Digite a quantidade desejada a abastecer: ");
        abast = Input.lerDouble("Quantidade Inválida!", "Digite novamente a quantidade a abastecer: ");

        try {
            ucj.abastecerVeiculo(matricula, abast);
            UmCarroJaApp.guardarDados();
        }
        catch (VeiculoNaoExisteException e) {
            out.println(car + e.getMessage() +  doesntExist);
        }
        catch (VeiculoNaoESeuException e2) {
            out.println(car + e2.getMessage() +  doesntBelong);
        }
    }
    
    private static void altPKMVeiculo(){
        String matricula;
        double pkm;

        UmCarroJaApp.clearScreen();

        out.print(insertMatricula);
        matricula = Input.lerString(errorMatricula, insertMatriculaAgain);


        out.print("Digite novo preço por km: ");
        pkm = Input.lerDouble("Preço por km Inválida!", "Digite novamente um novo preço por km: ");

        try {
            ucj.altPrecoKm(matricula, pkm);
        }
        catch (VeiculoNaoExisteException e) {
            out.println(car + e.getMessage() + doesntExist);
        }
        catch (VeiculoNaoESeuException e2) {
            out.println(car + e2.getMessage() + doesntBelong);
        }
    }

    private static void getListaDeAlugueres(){
        List<Aluguer> aux = ucj.getAlugueresProp(ucj.getUserNIF());

        GregorianCalendar dataInicio;
        GregorianCalendar dataFim;

        do {
            out.print(beginDateRental);
            dataInicio = Input.lerData(beginDateError, beginDateRentalAgain);

            out.print(endDateRental);
            dataFim = Input.lerData(endDateError, endDateRentalAgain);
        }while (dataFim.before(dataInicio));

        List<Aluguer> alugs;

        alugs = filterAlugueresBD(aux, dataInicio, dataFim);

        UmCarroJaApp.clearScreen();

        if(alugs.isEmpty()) {
            out.print(noRentals);
        } else {
            for(Aluguer a : alugs){
                out.println(screenSep);
                out.println(a.toString());
                out.println(screenSep);
            }
        }
    }
    
    private static void analisarAlugueres(){
        List<Aluguer> alugs = ucj.determinarListaEspera(ucj.getUserNIF());
        boolean rep;
        UmCarroJaApp.clearScreen();

        for(Aluguer a : alugs) {
            a.toString();
            out.print("Digite se pretende aceitar aluguer (Sim - true, Não - false): ");
            rep = Input.lerBoolean("Resposta Inválida!", "Digite novamente uma nova resposta: ");

            ucj.respostaProp(rep,a);
        }
    }
    
    public static void suplementoPrecoAlug(){
        String matricula;
        String nifCliente;
        List<Aluguer> alugs = new ArrayList<>();
        UmCarroJaApp.clearScreen();

        out.print(insertMatricula);
        matricula = Input.lerString(errorMatricula, insertMatriculaAgain);


        out.print("Digite o Email do cliente: ");
        nifCliente = Input.lerString(errorEmail, "Digite novamente o Email do cliente: ");


        try {
            alugs = ucj.determinarListaAlugCli(matricula, nifCliente);
        }
        catch (VeiculoNaoESeuException e) {
            out.println(car + e.getMessage() + doesntBelong);
        }
        catch (UtilizadorNaoExisteException e2) {
            out.println("O cliente " + e2.getMessage() + doesntExist);
        }


        if(alugs.isEmpty()){
            out.println("Não existem alugueres por parte do cliente " + nifCliente + " no veículo com matrícula " + matricula + ".\n");
        } else {
            for(Aluguer a : alugs) {
                newPrice(a);
            }
        }
        UmCarroJaApp.guardarDados();
    }

    private static void newPrice(Aluguer a) {
        boolean rep;
        double newPrice;
        boolean flag;
        out.print("Digite se pretende alterar preço de aluguer (Sim - true, Não - false): ");
        rep = Input.lerBoolean("Resposta Inválida!", "Digite novamente uma nova resposta: ");
        if(rep) {
            do{
                out.print("Digite novo preço para aluguer: ");
                newPrice = Input.lerDouble("Preço Inválido!", "Digite novamente um novo preço: ");
                flag = newPrice < a.getCustoViagem();
                if (flag){
                    out.println("Novo preço menor do que o anterior digite novo preço novamente!");
                }
            } while(flag);
            ucj.altPrecoAluguer(newPrice, a);
        }
    }
    
    public static void calcFactBDates(){
        String matricula;
        GregorianCalendar inicio;
        GregorianCalendar fim;
        double total = 0;
        UmCarroJaApp.clearScreen();

        out.print(insertMatricula);
        matricula = Input.lerString(errorMatricula, insertMatriculaAgain);

        do {
            out.print("Digite a Data limite inferior (dd-mm-aaaa): ");
            inicio = Input.lerData("Data limite inferior Inválida!", "Digite novamente a Data limite inferior (dd-mm-aaaa): ");

            out.print("Digite a Data limite superior (dd-mm-aaaa): ");
            fim = Input.lerData("Data limite superior Inválida!", "Digite novamente a Data limite superior (dd-mm-aaaa): ");
        }while (fim.before(inicio));

        try {
            total = ucj.totalFactBDates(matricula, inicio, fim);
        }
        catch (VeiculoNaoESeuException e) {
            out.println(car + e.getMessage() +  " não está registado no seu historial de alugueres!\n");
        }

        StringBuilder str = new StringBuilder();
        str.append("O total facturado pelo veículo de matrícula "); str.append(matricula);
        str.append(" faturou no total: "); str.append(total);
        str.append(" euros, entre "); str.append(inicio.toString());
        str.append(" e "); str.append(fim.toString());
        str.append(".\n");
        out.println(str);
    }

    private static List<Aluguer> filterAlugueresBD (List<Aluguer> alugs, GregorianCalendar dataInicio, GregorianCalendar dataFim) {
        List<Aluguer> res = alugs.stream().filter(a -> a.getDataInicio().after(dataInicio) && a.getDataFim().before(dataFim) && a.getRealizado()).collect(Collectors.toList());

        TreeSet<Aluguer> resOrd = new TreeSet<>((a1, a2) -> {
            int i = a1.getDataInicio().compareTo(a2.getDataInicio());
            if(i == 0) {return -1; }
            return i;
        });


        for(Aluguer a : res) {
            resOrd.add(a.clone());
        }

        return resOrd.stream().collect(Collectors.toList());
    }

    
    /******************************************************************************
     *                         FIM DO MENU DOS PROPRIETÁRIOS                      *
     ******************************************************************************/
     
    /******************************************************************************
     *                               MENU DOS CLIENTES                            *
     ******************************************************************************/
    
    private static void sessaoCliente(){
        do{
            classificarVeiculos();
            UmCarroJaApp.clearScreen();
            menuCliente.executa();
            switch(menuCliente.getOpcao()){
                case 1: alugarCarro(1);
                    break;
                case 2: alugarCarro(2);
                    break;
                case 3: alugarCarro(3);
                    break;
                case 4: alugarCarro(4);
                    break;
                case 5: aluguerDeterminadaAutonomia();
                    break;
                case 6: meusAlugueresEntreDatasCli();
                    break;
                default: break;
            }
        }while(menuCliente.getOpcao() != 0);
        ucj.logoutUtilizador();
        menuUtilizador.executa();
    }

    private static boolean verificaData (GregorianCalendar inicio, GregorianCalendar fim){
        return ((inicio.equals(ucj.getDataInicioApp()) || inicio.after(ucj.getDataInicioApp())) &&
                (fim.equals(inicio) || fim.after(inicio)));
    }

    private static void classificarVeiculos(){
        List<Aluguer> classif = ucj.alugueresClassificarVeiculo();

        boolean correto = false;
        int classificacao = 0;

        for (Aluguer alug : classif){
            out.println("--------------------- Aluguer ---------------------");
            out.println("Matrícula: " + alug.getMatricula());
            out.println("Data de Início do Aluguer: " + alug.getDataFim());
            out.println("Data de Fim do Aluguer: " + alug.getDataFim());
            out.println(screenSep);
            do {
                out.print("Digite uma classificação para o veículo com a matrícula: " + alug.getMatricula() + " (0-100): ");
                classificacao = Input.lerInt("Classificação Inválida!", "Digite novamente a classificação (0-100): ");
                if (classificacao >= 0 && classificacao <= 100){
                    correto = true;
                }
            }while(!correto);
            ucj.classificarVeiculo(alug, classificacao);
        }
    }

    private static void alugarCarro(int tipoAluguer){
        GregorianCalendar dataInicio;
        GregorianCalendar dataFim;
        int quantos = 0;
        String matricula = "";
        String mat = "";
        double latitude;
        double longitude;
        double perimetro = 0.0;
        Veiculo ve;
        Aluguer alug;

        UmCarroJaApp.clearScreen();

        do{
            out.print(beginDateRental);
            dataInicio = Input.lerData(beginDateError, beginDateRentalAgain);

            out.print(endDateRental);
            dataFim = Input.lerData(endDateError, endDateRentalAgain);
        }while (!verificaData(dataInicio, dataFim));

        if(tipoAluguer == 4){
            out.print("Introduza a Matrícula do Veículo pretendido");
            mat = Input.lerString(errorMatricula, insertMatriculaAgain);
        }

        out.print(insertLat);
        latitude = Input.lerDouble(latInvalid, insertLatAgain);

        out.print(insertLong);
        longitude = Input.lerDouble(longInvalid, insertLongAgain);

        if(tipoAluguer == 3){
            out.print("Indique o perimetro de procura: ");
            perimetro = Input.lerDouble("Perimetro Inválido!", "Digite novamente o perimetro: ");
        }

        if(tipoAluguer != 4){
            out.print(nrCarsScreen);
            quantos = Input.lerInt(nrError, nrCarsScreenAgain);
        }

        ParDatas newPair = new ParDatas(dataInicio, dataFim);
        Coordinate posDestino = new Coordinate(latitude,longitude);

        List<Veiculo> veiculos = new ArrayList<>();

        try {
            if (tipoAluguer == 1)
                veiculos = ucj.maisProximo(posDestino, newPair, quantos);
            else if (tipoAluguer == 2)
                veiculos = ucj.maisBarato(posDestino, newPair, quantos);
            else if (tipoAluguer == 3)
                veiculos = ucj.maisBaratoNoPerimetro(posDestino, ucj.getPosicaoCliente(), newPair, perimetro, quantos);
            else if (tipoAluguer == 4) {
                ve = ucj.veiculoEspecifico(posDestino, newPair, mat);
                matricula = ve.getMatricula();
            }
        }
        catch(NaoExistemVeiculosDisponiveisException e){
            out.println("Não Existem Veículos Disponíveis para Alugar, Com os Critérios Inseridos!");
        }
        catch(VeiculoNaoExisteException e){
            out.println(car + e.getMessage() +  " com a matrícula introduzida não existe. \n");
        }
        catch(VeiculoIndisponivelException e){
            out.println(car + e.getMessage() +  " não tem disponibilidade para ser alugado. \n");
        }
        if(tipoAluguer != 4) {
            matricula = alugarAux(veiculos);
        }

        alug = new Aluguer(ucj.getEmailUser(), matricula, dataInicio, dataFim, posDestino);

        ucj.registaAluguer(alug);

    }



    private static void aluguerDeterminadaAutonomia(){
        GregorianCalendar dataInicio;
        GregorianCalendar dataFim;
        int quantos;
        String matricula;
        double latitude;
        double longitude;
        int auto1;
        int auto2;
        boolean correto = false;

        do {
            out.print(beginDateRental);
            dataInicio = Input.lerData(beginDateError, beginDateRentalAgain);

            out.print(endDateRental);
            dataFim = Input.lerData(endDateError, endDateRentalAgain);
        }while(!verificaData(dataInicio, dataFim));

        out.print(insertLat);
        latitude = Input.lerDouble(latInvalid, insertLatAgain);

        out.print(insertLong);
        longitude = Input.lerDouble(longInvalid, insertLongAgain);

        do {
            out.print("Indique o limite inferior de autonomia: ");
            auto1 = Input.lerInt("Limite Inválido!", "Digite novamente o limite inferior: ");

            out.print("Indique o limite superior de autonomia: ");
            auto2 = Input.lerInt("Limite Inválido!", "Digite novamente o limite superior: ");
            if (auto1 < auto2 && auto1 >= 0 && auto2 > 0){
                correto = true;
            }
        }while (!correto);

        out.print(nrCarsScreen);
        quantos = Input.lerInt(nrError, nrCarsScreenAgain);

        ParDatas newPair = new ParDatas(dataInicio,dataFim);
        Coordinate posDestino = new Coordinate(latitude,longitude);

        List<Veiculo> veiculosDetAutonomia = new ArrayList<>();

        try{
            veiculosDetAutonomia = ucj.determinadaAutonomia(posDestino, newPair, auto1, auto2, quantos);
        }
        catch(NaoExistemVeiculosDisponiveisException e){
            out.println(e.getMessage());
        }

        matricula = alugarAux(veiculosDetAutonomia);

        Aluguer alug = new Aluguer(ucj.getEmailUser(), matricula, dataInicio, dataFim, posDestino);

        ucj.registaAluguer(alug);
    }

    private static String alugarAux(List<Veiculo> veiculos) {
        boolean correto = false;
        String matricula;
        for (Veiculo v : veiculos) {
            out.println(screenSep);
            out.println(v.toString());
            out.println(screenSep);
        }

        do {
            out.print("Introduza a Matrícula do Veículo que Pretende Alugar: ");
            matricula = Input.lerString(errorMatricula, insertMatriculaAgain);
            final String matr = matricula;
            if (veiculos.stream().filter(v -> v.getMatricula().equals(matr)).count() > 0) {
                correto = true;
            }
        } while (!correto);
        return matricula;
    }

    private static void meusAlugueresEntreDatasCli() {
        List<Aluguer> alugs = new ArrayList<>();

        try {
            alugs = ucj.getAlugueresCliente(ucj.getEmailUser());
        } catch (NaoEfetuouNenhumAluguerException e) {
            out.println(e.getMessage());
        }

        GregorianCalendar dataInicio;
        GregorianCalendar dataFim;

        do {
            out.print(beginDateRental);
            dataInicio = Input.lerData(beginDateError, beginDateRentalAgain);

            out.print(endDateRental);
            dataFim = Input.lerData(endDateError, endDateRentalAgain);
        } while (dataFim.before(dataInicio));

        List<Aluguer> res = filterAlugueresBD(alugs, dataInicio, dataFim);

        UmCarroJaApp.clearScreen();
        if (res.isEmpty()) {
            out.print(noRentals);
        } else {
            for (Aluguer a : res) {
                out.println(screenSep);
                out.println(a.toString());
                out.println(screenSep);
            }
        }
    }

                                 
    /******************************************************************************
     *                            FIM DO MENU DOS CLIENTES                        *
     *****************************************************************************


    /**
     * Guarda o estado da aplicação no ficheiro de objetos e guarda a data do dia
     * de hoje.
     */
    private static void guardarDados(){
        try {
            ucj.guardarEstado(ficheiroDados, ucj.getDataInicioApp());
        } catch (IOException e) {
            out.println("Erro ao gravar os dados no ficheiro " + ficheiroDados + "!");
        }
    }


    /********** PARSING *************/
    
    private static void lerDadosTXT(String fichtxt){
        String linha = null;
        String [] linhas = null;
        UmCarroJaApp.clearScreen();
        try (BufferedReader inFile = new BufferedReader(new FileReader(fichtxt))) {
            while (!inFile.readLine().equals("Logs")) {
                out.println();
            }
            while ((linha = inFile.readLine()) != null) {
                linhas = linha.split(":", 2);
                if (linhas[0].equals("NovoProp")) {
                    parseProp(linhas[1]);
                }
                if (linhas[0].equals("NovoCliente")) {
                    parseCliente(linhas[1]);
                }
                if (linhas[0].equals("NovoCarro")) {
                    parseCarro(linhas[1]);
                }
                if (linhas[0].equals("Aluguer")) {
                    parseAluguer(linhas[1]);
                }
                if (linhas[0].equals("Classificar")) {
                    parseClassificar(linhas[1]);
                }
            }
            UmCarroJaApp.guardarDados();
        } catch (IOException exc) {
            out.println("Erro ao ler ficheiro de texto!");
        }
    }

    private static void parseCarro(String linha) {
        try {
            Veiculo v = ParseDados.parseVeiculo(linha);
            ucj.registarVeiculo(v);
        } catch (VeiculoJaExisteException e) {
            out.println("O veículo com a matrícula: " + e.getMessage() + " já foi inserido!");
        }
    }

    private static void parseCliente(String linha) {
        try{
            Cliente cli = ParseDados.parseCliente(linha);
            ucj.registarUtilizador(cli);
        }catch(UtilizadorJaExisteException e){
            out.println("O cliente com o email: " + e.getMessage() + alreadyRegistered);
        }
    }

    private static void parseProp(String linha) {
        try{
            Proprietario prop = ParseDados.parseProprietario(linha);
            ucj.registarUtilizador(prop);
        }catch(UtilizadorJaExisteException e){
            out.println("O proprietário com o email: " + e.getMessage() + alreadyRegistered);
        }
    }
    
   private static void parseAluguer(String linha){
       String mail = "";
       double x;
       double y;
       GregorianCalendar dataInicio = new GregorianCalendar(2019,4,20);
       GregorianCalendar dataFim = new GregorianCalendar(2019,4,21);
       ParDatas datas = new ParDatas(dataInicio, dataFim);
       String [] dados = linha.split(",");

       mail = dados[0] + "@gmail.com";

       try{
           x = Double.parseDouble(dados[1]);
           y = Double.parseDouble(dados[2]);
       }
       catch(InputMismatchException exc){return;}

       Coordinate cords = new Coordinate(x, y);

       Cliente cli = new Cliente();
       try {
           cli = (Cliente) ucj.getUtilizador(mail);
       }catch (UtilizadorNaoExisteException e) {
           out.println("Cliente não encontrado!\n");
       }

       Veiculo v = new Veiculo();

       try {
           if (dados[4].equals("MaisBarato")){
               if (dados[3].equals("Electrico"))
                   v = ucj.maisBaratoJa(cords, datas, "CarroEletrico");
               else if (dados[3].equals("Hibrido"))
                   v = ucj.maisBaratoJa(cords, datas, "CarroHibrido");
               else if (dados[3].equals("Gasolina"))
                   v = ucj.maisBaratoJa(cords, datas, "CarroGasolina");
           }
           else{
               if (dados[3].equals("Electrico"))
                   v = ucj.maisPertoJa(cords, datas, "CarroEletrico");
               else if (dados[3].equals("Hibrido"))
                   v = ucj.maisPertoJa(cords, datas, "CarroHibrido");
               else if (dados[3].equals("Gasolina"))
                   v = ucj.maisPertoJa(cords, datas, "CarroGasolina");
           }
       }catch (NaoExistemVeiculosDisponiveisException e){
           out.println("Não existem veículos disponíveis para alugar!\n");
       }

       double dist = v.getPosicao().getDistancia(cords);
       Coordinate posCli = cli.getPosicao().clone();
       Aluguer alug = new Aluguer(mail, v.getMatricula(), dataInicio, dataFim,cords);
       alug.setCustoViagem(v.custoViagem(dist));
       alug.setTempoAteVeiculo(v.tempoAteVeiculoPeJa(posCli));
       alug.setTempoViagem(v.tempoViagemCarroJa(cords));
       alug.setDistancia(dist);
       alug.setAceite(true);
       alug.setListaEspera(false);
       alug.setRealizado(true);
       alug.setAlteraPreco(false);
       alug.setEstadoClassificacao(3);
       ucj.registaAluguer(alug);
       ucj.alterarPosAutonomiaVeiculo(v.getMatricula(), cords.clone());
       ucj.alterarPosCliente(mail, cords.clone());
    }
    
    public static void parseClassificar(String linha){
        String [] dados = linha.split(",");
        int classificacao = 0;
        
        try {
            classificacao = Integer.parseInt(dados[1]);
        }
        catch(InputMismatchException exc){return;}
        
        if (dados[0].contains("-")){
            ucj.classificarVeiculoJa(dados[0], classificacao);
        }else{
            ucj.classificarClienteJa(dados[0] + "@gmail.com", classificacao);
        }
    }
}
