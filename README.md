# Implementação de Algoritmos de Cifras Simétricas em Java
Está página contém exemplos de uso dos algoritmos de cifras simétricas de bloco e de fluxo.

 Para as cifras de bloco (src/main/java/blockCiphers), existem implementações para o AES existem nos modos ECB, GCM e CBC, para o 3DES foram implementados os modos ECB E CBC. Nas cifras de fluxo (src/main/java/streamCiphers), foram realizadas implementações para o Chacha20 e para o RC4.
 
 # Comparação de desempenho dos algoritmos de cifra de bloco
 Foi realizada uma comparação entre os algoritmos de cifra de bloco, para tal foram realizados experimentos contendo operações de cifrar e decifrar para conjuntos de 1000, 10.000, 100.000 e 1.000.000 de elementos. 
 
![Alt text](images/blockCiphers.png?raw=true "Title")

