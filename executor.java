import java.util.Scanner;

public class executor{
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        String[] entradas = new String[2];
        for(int i = 0; i<2; i++){
            entradas[i] = scr.nextLine();
        }

        Leitor leitor = new Leitor(entradas);

        leitor.destinos();
        leitor.validacao();
        leitor.filtrar("Sul","Brinquedos");
    }
    
}