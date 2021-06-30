import java.util.HashMap;
import java.util.Map;

public class Leitor {
    
    public String[] codigos;
    public Map<String, String> regioes = new HashMap<String, String>();
    public Map<String, String> produtos = new HashMap<String, String>();

    public Leitor(String[] codigos){
        this.codigos = codigos;
        regioes.put("111", "Centro-Oeste");
        regioes.put("333", "Nordeste");
        regioes.put("555", "Norte");
        regioes.put("888", "Sudeste");
        regioes.put("000", "Sul");
        produtos.put("111", "Livros");
        produtos.put("333", "Eletronicos");
        produtos.put("555", "Bebidas");
        produtos.put("888", "Brinquedos");
        produtos.put("000", "Jóias");
    }

    public String getXstPart(String codigo, int x){
        String nova = codigo.substring((x*3)-3, x*3);
        return nova;
    }

    public boolean ehValido(String codigo){
        boolean saida = true;
 
        String localOrigem = getXstPart(codigo, 1);
        String vendedor = getXstPart(codigo, 4);
        String produto = getXstPart(codigo, 5);

        saida = produtos.containsKey(produto);
        if (localOrigem.equals("111") && produto.equals("000")){
            return false;
        }
        if (vendedor.equals("584")){
            return false;
        }

        return saida;
    }

    public void destinos(){
        String destino;
        System.out.println("Destinos:");

        for(int i= 0; i < codigos.length; i++){
            destino = getXstPart(codigos[i], 2);
            System.out.print("codigo " + (i+1) + " : ");
            System.out.println(regioes.get(destino));
        }
    }

    public void validacao(){
        System.out.println("Codigos Validos:");
        for(int i= 0; i < codigos.length; i++){
            if(ehValido(codigos[i])){
                System.out.println((i+1));
            }
        }
    }

    public void filtrar(String regiao, String produto){
        System.out.println("Filtro por " + regiao + " e " + produto);
        String resultado = "Não há uma peca com essas especificacoes";
        for(int i= 0; i < codigos.length; i++){
            String localOrigem = getXstPart(codigos[i], 1);
            String peca = getXstPart(codigos[i], 5);
            if(regioes.get(localOrigem).equals(regiao) && produtos.get(peca).equals(produto)){
                System.out.println("codigo " + (i+1) + " tem essas especificacoes"); 
                resultado = "fim do filtro";
            }
        }
        System.out.println(resultado);
    }
}
