module Info where

listaLocais :: [String]
listaLocais = ["Lisboa","Vila Nova de Gaia","Porto","Amadora","Braga","Funchal",
               "Coimbra","Almada","Setubal","Aveiro","Odivelas","Leiria","Portimao",
               "Guimaraes","Viseu","Matosinhos","Faro","Evora","Maia","Viana do Castelo",
               "Agualva-Cacem","Castelo Branco","Vila Nova de Famalicao","Marinha Grande",
               "Santarem","Vila do Conde","Figueira da Foz","Guarda","Queluz"]

listaApelidos :: [String]
listaApelidos = ["Silva","Santos","Ferreira","Pereira","Oliveira","Costa","Rodrigues","Martins","Jesus","Sousa",
                 "Fernandes","Goncalves","Gomes","Lopes","Marques","Alves","Almeida","Ribeiro","Pinto","Carvalho",
                 "Teixeira","Moreira","Correia","Mendes","Nunes","Soares","Vieira","Monteiro","Cardoso","Rocha",
                 "Raposo","Neves","Coelho","Cruz","Cunha","Pires","Ramos","Reis","Simoes","Antunes",
                 "Matos","Fonseca","Machado","Araujo","Barbosa","Tavares","Lourenco","Castro","Figueiredo","Azevedo",
                 "Freitas","Magalhaes","Henriques","Lima","Guerreiro","Batista","Pinheiro","Faria","Miranda","Barros",
                 "Morais","Nogueira","Esteves","Anjos","Baptista","Campos","Mota","Andrade","Brito","Sa",
                 "Nascimento","Leite","Abreu","Borges","Melo","Vaz","Pinho","Vicente","Gaspar","Assuncao",
                 "Maia","Moura","Valente","Domingues","Garcia","Carneiro","Loureiro","Neto","Amaral","Branco",
                 "Leal","Pacheco","Macedo","Paiva","Matias","Amorim","Torres"]

listaNomesProprios :: [String]
listaNomesProprios = ["Joao","Francisco","Santiago","Afonso","Duarte","Tomas","Martim","Rodrigo","Lourenco","Gabriel",
                      "Miguel","Lucas","Pedro","Dinis","Vicente","Guilherme","Salvador","Goncalo","Rafael","Mateus",
                      "Gustavo","Tiago","Diogo","Diego","Jose","Manuel","Henrique","Simao","Bernardo","David",
                      "Daniel","Vasco","Antonio","Enzo","Leonardo","Andre","Luis","Isaac","Eduardo","Artur",
                      "Kevin","Matias","Alexandre","Xavier","Benjamim","Filipe","Valentim","Bryan","Lorenzo","Samuel",
                      "Frederico","Leandro","Carlos","Nuno","Ricardo","Ruben","Joaquim","Sebastiao","Bruno","Davi",
                      "Hugo","William","Noah","Tome","Rui","Jaime","Vitor","Manel","Paulo","Jorge",
                      "Luca","Eric","Mateo","Angelo","Mario","Ivan","Renato","Christian","Dylan","Fabio",
                      "Marco","Caio","Cristiano","Liam","Gil","Muhammad","Micael","Raul","Cesar","Fernando",
                      "Gaspar","Edgar","Emanuel","Luan","Joel","Adrien","Theo","Sergio","Jonathan","Ivo",
                      "Maria","Leonor","Matilde","Beatriz","Carolina","Sofia","Alice","Mariana","Ana","Benedita",
                      "Francisca","Margarida","Ines","Clara","Lara","Laura","Madalena","Vitoria","Diana","Joana",
                      "Eva","Camila","Iris","Mafalda","Constanca","Gabriela","Leticia","Bianca","Sara","Luana",
                      "Ema","Ariana","Yara","Luisa","Carlota","Rita","Carminho","Yasmin","Helena","Mara",
                      "Valentina","Alicia","Julia","Catarina","Kyara","Olivia","Melissa","Rafaela","Isabella","Noa",
                      "Nuria","Marta","Miriam","Nicole","Teresa","Isabel","Lia","Alana","Luna","Daniela",
                      "Filipa","Aurora","Melanie","Mia","Barbara","Bruna","Raquel","Kelly","Julieta","Nayara",
                      "Chloe","Luena","Amelia","Pilar","Juliana","Lorena","Erica","Aurea","Caetana","Isis",
                      "Jessica","Frederica","Mayara","Eduarda","Luz","Emilia","Adriana","Giovanna","Emily","Flor",
                      "Debora","Soraia","Fabiana","Larissa","Alexandra","Elisa","Vera","Bia","Ester","Anita"]

listaCarrosFreq :: [(String,Int)]
listaCarrosFreq = [("Mercedes",12),("Ford",23)] -- so para testar
                  [("Nissan",31),("Peugeot",29),("Renault",27),("Mercedes",38),("Citroen",24),("Volkswagen",25),("Opel",31),("Ford",31),
                   ("Seat",33),("Fiat",27),("BMW",30),("Dacia",27),("Toyota",35),("Ferrari",20),("Lamborghini",22),("Tesla",24),
                   ("Volvo",25),("Jaguar",25),("Jeep"24),("Smart",18),("Mazda",27),("Honda",32)]

autEletrico :: [Int]
autEletrico = [450,300,340,400]

autHibrido :: [Int]
autHibrido = [300,290,365]

autGasolina :: [Int]
autGasolina = [390,297,420]

nifs :: [String]
nifs = ["100000000","100000001","100000002","100000003","100000004","100000005","100000006","100000007","100000008","100000009",
        "100000010","100000011","100000012","100000013","100000014","100000015","100000016","100000017","100000018","100000019",
        "100000020","100000021","100000022","100000023","100000024","100000025","100000026","100000027","100000028","100000029",
        "100000030","100000031","100000032","100000033","100000034","100000035","100000036","100000037","100000038","100000039",
        "100000040","100000041","100000042","100000043","100000044","100000045","100000046","100000047","100000048","100000049",
        "100000050","100000051","100000052","100000053",,"100000054","100000055","100000056","100000057","100000058","100000059",
        "100000060","100000061","100000062","100000063","100000064","100000065","100000066","100000067","100000068","100000069",
        "100000070","100000071","100000072","100000073","100000074","100000075","100000076","100000077","100000078","100000079",
        "100000080","100000081","100000082","100000083","100000084","100000085","100000086","100000087","100000088","100000089",
        "100000090","100000091","100000092","100000093","100000094","100000095","100000096","100000097","100000098","100000099",
        "100000100","100000101","100000102","100000103","100000104","100000105","100000106","100000107","100000108","100000109",
        "100000110","100000111","100000112","100000113","100000114","100000115","100000116","100000117","100000118","100000119",
        "100000120","100000121","100000122","100000123","100000124","100000125","100000126","100000127","100000128","100000129",
        "100000130","100000131","100000132","100000133","100000134","100000135","100000136","100000137","100000138","100000139",
        "100000140","100000141","100000142","100000143","100000144","100000145","100000146","100000147","100000148","100000149"]
