import Data.List
import Test.QuickCheck
import Info


-- GERAR CARRO
data Carro = Carro Tipo Marca Matricula NIF CPKm Autonomia
            deriving Show

data Tipo = Combustao
          | Eletrico
          | Hibrido
          deriving Show

type Marca      = String
type Matricula  = String
type NIF        = String
type CPKm       = Float
type Autonomia  = Int

genTipo :: Gen Tipo
genTipo = frequency [(70,return Combustao),(5,return Eletrico),(25,return Hibrido)]

genCPKm :: Gen CPKm
genCPKm = choose (0.1,2)


genAutonomia :: Gen Autonomia
genAutonomia =  frequency [(70, elements autCombustao),(5, elements autEletrico),(25, elements autHibrido)]

{-
genMarca :: Gen Marca
genMarca = frequency [(120,return "Renault"),(35,return "Mercedes"),(50,return "Ford"),(15,return "Seat"),(12,return "Porsche"),(4,return "Ferrari")]
--}

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

genCarro :: [NIF] -> Gen Carro
genCarro nifs = do tipo <- genTipo
                   marca <- genMarca
                   mat <- genMatricula
                   nif <- elements nifs
                   cp <- genCPKm
                   aut <- genAutonomia
                   return (Carro tipo marca mat nif cp aut)

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

{-
genProp :: Gen Prop
genProp = do 
          nome <- genNome
          nif <- elements nifs
          email <- genEmail nif
          morada <- genMorada
          return (Prop nome nif email morada)
-}

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
