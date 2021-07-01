import java.util.Scanner;

public class executor{
    public static void main(String[] args) {

        // Leitura de todos os códigos

        Scanner scr = new Scanner(System.in);
        String[] entradas = new String[15];
        for(int i = 0; i<15; i++){
            entradas[i] = scr.nextLine();
        }

        // Intanciamento do objeto de classe leitor e efetuação de seus métodos 

        Leitor leitor = new Leitor(entradas);
        leitor.destinos();
        leitor.validacao();
        leitor.filtrar("Sul","Brinquedos");
        leitor.listarPorDestinos();
        leitor.listarPorVendedores();
        leitor.gerarRelatorio();
    }
    
}
/*
888555555123888
333333555584333
222333555124000
000111555874555
111888555654777
111333555123333
555555555123888
888333555584333
111333555124000
333888555584333
555888555123000
111888555123555
888000555845333
000111555874000
111333555123555 
*/