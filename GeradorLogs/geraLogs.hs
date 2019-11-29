import Data.List
import Test.QuickCheck
import Control.Monad
import Control.Monad.State.Strict
import Info

-- MAIN
main :: IO ()
main = do 
       users <- generate $ execGerador (genUsers 20 nifs)
       let nifsProp = getNifsProp users
       let nifsClientes = getNifsClientes users
       carros <- generate $ execGerador (genCarros 5 nifsProp)
       writeFile "logs.txt" $ unlines (users++carros)

getNifsProp :: [String] -> [String]
getNifsProp [] = []
getNifsProp (x:xs) = if (isPrefixOf "NovoProp" x) 
                     then ((splitSep ',' x) !! 1): getNifsProp xs 
                     else getNifsProp xs

getNifsClientes :: [String] -> [String]
getNifsClientes [] = []
getNifsClientes (x:xs) = if (isPrefixOf "NovoCliente" x) 
                         then ((splitSep ',' x) !! 1): getNifsClientes xs 
                         else getNifsClientes xs

splitSep :: Eq a => a -> [a] -> [[a]]
splitSep _ [] = []
splitSep s l = let (l0, l1) = break (==s) l
              in l0 : splitSep s (drop 1 l1)

genUsers :: Int -> [String] -> Gerador [String]
genUsers 0 _ = return []
genUsers n nifs = do
                  nif <- lift $ elements nifs
                  let nifs' = delete nif nifs
                  tipoU <- lift $ frequency [(40,return 1),(60,return 2)]
                  let u = if (tipoU == 1) then (genProp nif) else (genCliente nif)
                  ul <- u
                  us <- genUsers (n-1) nifs'
                  return (ul:us)

genCarros :: Int -> [String] -> Gerador [String]
genCarros 0 _ = return []
genCarros n nifs = do 
                   nifProp <- lift $ elements nifs
                   car <- genCarro nifProp
                   crs <- genCarros (n-1) nifs
                   return (car:crs)

-- ESTADOS
data GeradorState = GeradorState StateProps StateClientes StateCarros
            deriving Show

type StateProps = [Prop]
type StateClientes = [Cliente]
type StateCarros = [Carro]

type Gerador a = StateT GeradorState Gen a 

defaultState = GeradorState [] [] []

execGerador :: Gerador a -> Gen a 
execGerador g = evalStateT g defaultState


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

genProp :: String -> Gerador String
genProp nif = do 
              (GeradorState props cls cars) <- get
              nome <- lift $ genNome
              email <- lift $ genEmail nif
              morada <- lift $ genMorada
              let pr = (Prop nome nif email morada)
              put (GeradorState (pr:props) cls cars)
              return ("NovoProp:" ++ nome ++ "," ++ nif ++ "," ++ email ++ "," ++ morada ++ "\n")

---GERAR CLIENTE
data Cliente = Cliente Nome NIF Email Morada CoordX CoordY
        deriving Show

type CoordX = Float
type CoordY = Float

genCoord :: Gen Float
genCoord = choose (-100.0,100.0)

genCliente :: String -> Gerador String
genCliente nif = do
                 (GeradorState props cls cars) <- get
                 nome <- lift $ genNome
                 email <- lift $ genEmail nif
                 morada <- lift $ genMorada
                 cordX <- lift $  genCoord
                 cordY <- lift $  genCoord
                 let c = (Cliente nome nif email morada cordX cordY)
                 put (GeradorState props (c:cls) cars)
                 return ("NovoCliente:" ++ nome ++ "," ++ nif ++ "," ++ email ++ "," ++ morada ++ "," ++ (show cordX) ++ "," ++ (show cordY) ++ "\n")

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


genCarro :: String -> Gerador String
genCarro nifProp = do 
                   (GeradorState props cls cars) <- get
                   tipo <- lift $ genTipo
                   marca <- lift $ genMarca
                   mat <- lift $ genMatricula
                   nif <- lift $ elements nifs
                   v <- lift $ genVelocidadeMed
                   pkm <- lift $ genPrecoKm
                   cp <- lift $ genCPKm
                   aut <- lift $ genAutonomia
                   x <- lift $ genCoord
                   y <- lift $ genCoord
                   let car = (Carro tipo marca mat nifProp v pkm cp aut x y)
                   put (GeradorState props cls (car:cars))
                   return ("NovoCarro:" ++ (show tipo) ++ "," ++ marca ++ "," ++ mat ++ "," ++ nifProp ++ ","++ (show v) ++ "," ++ (show pkm) ++ "," ++ (show cp) ++ "," ++ (show aut) ++ "," ++ (show x) ++ "," ++ (show y) ++ "\n")


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