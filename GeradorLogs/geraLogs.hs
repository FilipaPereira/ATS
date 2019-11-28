import Data.List
import Test.QuickCheck
import Control.Monad.State.Strict
import Info


-- ESTADOS
type Gerador st a = StateT st Gen a 

execGerador :: st -> Gerador st a -> Gen a 
execGerador st g = evalStateT g st


--GERAR PROPRIETARIO

data Prop = Prop Nome NIF Email Morada
   deriving Show
    
type Nome = String
type Email = String
type Morada = String

genNome :: Gen String
genNome = do
          nomeP <- elements listaNomesProprios
          apelido <- elements listaApelidos
          return (nomeP ++ " " ++ apelido)


genEmail :: String -> Gen String
genEmail nif = do
               a <- elements["@gmail.com","@hotmail.com","@live.pt"]
               return (nif++a)

genMorada :: Gen String
genMorada = elements listaLocais


genProps :: Int -> [String] -> Gen [Prop]
genProps 0 _ = return []
genProps n nifs = do
                  nif <- elements nifs
                  let nifs' = delete nif nifs
                  ps <- genProps (n-1) nifs'
                  nome <- genNome
                  email <- genEmail nif
                  morada <- genMorada
                  let pr = (Prop nome nif email morada)
                  return (pr:ps)

---GERAR CLIENTE
data Cliente = Cliente Nome NIF Email Morada CoordX CoordY
        deriving Show

type CoordX = Float
type CoordY = Float

genCoord :: Gen Float
genCoord = choose (-100.0,100.0)

genClientes :: Int -> [String] -> Gen [Cliente]
genClientes 0 _ = return []
genClientes n nifs = do
                    nif <- elements nifs
                    let nifs' = delete nif nifs
                    cls <- genClientes (n-1) nifs'
                    nome <- genNome
                    email <- genEmail nif
                    morada <- genMorada
                    cordX <- genCoord
                    cordY <- genCoord
                    let c = (Cliente nome nif email morada cordX cordY)
                    return (c:cls)


-- GERAR CARRO
data Carro = Carro Tipo Marca Matricula NIF VelocidadeMed PpKm CPKm Autonomia CoordX CoordY
            deriving Show

data Tipo = Gasolina
          | Eletrico
          | Hibrido
          deriving Show

type Marca         = String
type Matricula     = String
type NIF           = String
type VelocidadeMed = Int
type PpKm          = Float
type CPKm          = Float
type Autonomia     = Int

genTipo :: Gen Tipo
genTipo = frequency [(70,return Gasolina),(5,return Eletrico),(25,return Hibrido)]

genCPKm :: Gen CPKm
genCPKm = choose (0.1,2)


genAutonomia :: Gen Autonomia
genAutonomia =  frequency [(70, elements autGasolina),(5, elements autEletrico),(25, elements autHibrido)]


genMarca :: Gen Marca
genMarca = frequency (f l)
        where l = listaCarrosFreq
              f [] = []
              f ((x,y):xs) = (y,return x) : f xs

genMatricula :: Gen Matricula
genMatricula = do a <- vectorOf 2 $ elements ['A'..'Z']
                  b <- vectorOf 2 $ elements ['0'..'9']
                  c <- vectorOf 2 $ elements ['0'..'9']
                  return (a ++ "-" ++ b ++ "-" ++ c ++ "")

genVelocidadeMed :: Gen VelocidadeMed
genVelocidadeMed = choose (40,120)

genPrecoKm :: Gen PpKm
genPrecoKm = choose (1.1,2.5)

genCarro :: [NIF] -> Gen Carro
genCarro nifs = do tipo <- genTipo
                   marca <- genMarca
                   mat <- genMatricula
                   nif <- elements nifs
                   v <- genVelocidadeMed
                   pkm <- genPrecoKm
                   cp <- genCPKm
                   aut <- genAutonomia
                   x <- genCoord
                   y <- genCoord
                   return (Carro tipo marca mat nif v pkm cp aut x y)

-- GERAR ALUGUERES

data Aluguer = Aluguer NIFCliente CoordX CoordY Tipo Preferencia
             deriving Show

type NIFCliente = String
type Preferencia = String

genPreferencia :: Gen Preferencia
genPreferencia = elements ["MaisBarato","MaisPerto"]

genAluguer :: [String] -> Gen Aluguer
genAluguer nifs = do
                  nif <- elements nifs 
                  x <- genCoord
                  y <- genCoord
                  tipo <- genTipo
                  pref <- genPreferencia
                  return (Aluguer nif x y tipo pref) 

-- GERAR CLASSIFICAÇÕES

data Classificacao = Classificacao Classificado Nota
                 deriving Show

type Classificado = String
type Nota = Int

genClassificado :: [String] -> [String] -> Gen Classificado
genClassificado clientesNifs matriculas = elements(clientesNifs++matriculas)

genNota :: Gen Nota
genNota = choose (1,100)

genClassificacao :: [String] -> [String] -> Gen Classificacao
genClassificacao clientes mats = do 
                                 cl <- genClassificado clientes mats
                                 n <- genNota
                                 return (Classificacao cl n)