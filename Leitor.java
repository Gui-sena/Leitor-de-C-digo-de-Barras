import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Leitor {
    
    // Atributos da classe

    public String[] codigos;
    public Map<String, String> regioes = new HashMap<String, String>();
    public Map<String, String> produtos = new HashMap<String, String>();

    // Construtor da classe

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

// -------------------------métodos da classe--------------------------------

    // método que obtém a parte de 3 caracteres desejados da String

    public String getXstPart(String codigo, int x){
        String nova = codigo.substring((x*3)-3, x*3);
        return nova;
    }

    // Avalia se a String representando o código é válida

    public boolean ehValido(String codigo){
        boolean saida = true;
 
        String localOrigem = getXstPart(codigo, 1);
        String vendedor = getXstPart(codigo, 4);
        String produto = getXstPart(codigo, 5);

        saida = produtos.containsKey(localOrigem);
        if(saida)
            saida = produtos.containsKey(produto);
        if (localOrigem.equals("111") && produto.equals("000")){
            return false;
        }
        if (vendedor.equals("584")){
            return false;
        }

        return saida;
    }

    // printa os destinos de todos os código de entrada

    public void destinos(){
        String destino;
        System.out.println();
        System.out.println("Destinos:");

        for(int i= 0; i < codigos.length; i++){
            destino = getXstPart(codigos[i], 2);
            System.out.print("Pacote " + (i+1) + " : ");
            System.out.println(regioes.get(destino));
        }
    }

    // printa todas as strings válidas

    public void validacao(){
        System.out.println();
        System.out.println("Pacotes Validos:");
        for(int i= 0; i < codigos.length; i++){
            if(ehValido(codigos[i])){
                System.out.print((i+1) + " ");
            }
        }
        System.out.println();
    }

    // filtra todos os códigos pela região e produto desejados e printa o resultado do filtro

    public void filtrar(String regiao, String produto){
        System.out.println();
        System.out.println("Filtro por " + regiao + " e " + produto + ":");
        String resultado = "Não há uma peca com essas especificacoes";
        for(int i= 0; i < codigos.length; i++){
            if(ehValido(codigos[i])){
                String localOrigem = getXstPart(codigos[i], 1);
                String peca = getXstPart(codigos[i], 5);
                if(regioes.get(localOrigem).equals(regiao) && produtos.get(peca).equals(produto)){
                    System.out.println("Pacote " + (i+1) + " tem essas especificacoes"); 
                    resultado = "fim do filtro";
                }
            }
        }
        System.out.println(resultado);
    }

    // método auxiliar que Retorna uma matriz com a primeira coluna sendo o label referenciado pelo 
    // inteiro x e as outras colunas, os dados

    public List<List<String>> listar(int x, Map<String, String> map){
        List<List<String>> matrix = new ArrayList<>();
        for(int i = 0; i<codigos.length; i++){
            String label = map.get(getXstPart(codigos[i], x));    
            boolean colocou = false;
            for(int j = 0; j<=matrix.size(); j++){
                String codigo = Integer.toString(i+1);
                if(j!=matrix.size()){
                    if(matrix.get(j).contains(label)){
                        matrix.get(j).add(codigo);
                        colocou = true;
                    }
                }
                if(!colocou && j==matrix.size()){
                    List<String> nova = new ArrayList<>();
                    nova.add(label);
                    nova.add(codigo);
                    matrix.add(nova);
                    j = matrix.size()+1;
                }
            }
        }
        return matrix;
    }

    // usa o método listar() para listar todos os destinos válidos e printar

    public void listarPorDestinos(){
        System.out.println();
        System.out.println("Listando por Destinos:");
        List<List<String>> matrix = new ArrayList<>();
        matrix = listar(2, regioes);

        for(List<String> lista : matrix){
            for(String up : lista){
                if(!up.equals(lista.get(0))){
                    if(ehValido(codigos[Integer.parseInt(up)-1])){
                        System.out.print(up + " ");
                    }
                }
                else{
                    System.out.print(up + " ");
                }
            }
            System.out.println();
        }
    }

    // não usa o método listar() para listar todos os Vendedores válidos pois não
    // tem um atributo na classe com todos os seus tipos, como os destinos

    public void listarPorVendedores(){
        System.out.println();
        System.out.println("Listando por Vendedores:");
        List<List<String>> matrix = new ArrayList<>();
        for(int i = 0; i<codigos.length; i++){
            String vendendor = getXstPart(codigos[i], 4);    
            boolean colocou = false;
            for(int j = 0; j<=matrix.size(); j++){
                String codigo = Integer.toString(i+1);
                if(j!=matrix.size()){
                    if(matrix.get(j).contains(vendendor)){
                        matrix.get(j).add(codigo);
                        colocou = true;
                    }
                }
                if(!colocou && j==matrix.size()){
                    List<String> nova = new ArrayList<>();
                    nova.add(vendendor);
                    nova.add(codigo);
                    matrix.add(nova);
                    j = matrix.size()+1;
                }
            }
        }

        for(List<String> lista : matrix){
            for(String up : lista){
                if(!up.equals(lista.get(0))){
                    if(ehValido(codigos[Integer.parseInt(up)-1])){
                        System.out.print(up + " ");
                    }
                }
                else{
                    System.out.print(up + " ");
                }
            }
            System.out.println();
        }
    }

    // usa o método listar() para listar todos os destinos e produtos válidos 
    // com essas duas martizes em mãos, percorre as duas e as compara 
    // em busca de duplicatas. Se as encontra, printa.

    public void gerarRelatorio(){
        System.out.println();
        System.out.println("Listando por Destinos e Produtos:");
        List<List<String>> matrixDestino = new ArrayList<>();
        matrixDestino = listar(2, regioes);
        List<List<String>> matrixProduto = new ArrayList<>();
        matrixProduto = listar(5, produtos);

        for(List<String> lista1 : matrixDestino){
            for(List<String> lista2 : matrixProduto){
                if (lista1.get(0) != null && lista2.get(0) != null){
                    System.out.print(lista1.get(0) + " e " + lista2.get(0) + ": ");
                    for(String up : lista1){
                        if(lista2.contains(up)){
                            if(!up.equals(lista1.get(0))){
                                if(ehValido(codigos[Integer.parseInt(up)-1])){
                                    System.out.print(up + " ");
                                }
                            }
                            else{
                                System.out.print(up + " ");
                            }
                        }
                    }
                    System.out.println();
                }
            }
        }
    }
}
