import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;
import cliente.ClienteFisico;
import cliente.ClienteJuridico;
import compra.Compra;
import compra.ItensComprados;
import produto.Produto;
import telas.TelasJoption;


// CLASSE PRINCIPAL 
public class App extends TelasJoption {
    public static void main(String[] args) throws Exception {

        List<Cliente> clientes = lerDadosClientes();
        List<Produto> produtos = lerDadosProdutos();
        List<Compra> compras = lerDadosCompras(produtos);
        TelasJoption tela = new TelasJoption();

        // OPÇÕES MENU
        String[] menu = {"Cadastrar cliente",
                        "Deletar via CPF/CNPJ",
                        "Deletar via nome",
                        "Cadastrar Produto",
                        "Efetuar Compra",
                        "Efetuar Pagamento",
                        "Relatorios",
                        "Sair"
                        };

        int x;
        do{
            x = tela.exirbirMenu(menu,"Menu Principal");
            if(x == -404){x = 8;}

            // MENU PRINCIPAL
            switch (x) {

                case 1 -> {
                    Cliente c = tela.cadastrarCliente(clientes); // CADASTRO CLIENTE
                    if(c != null) {clientes.add(c);}
                }
                case 2 -> tela.deletarViaCpfCnpj(clientes); // DELETAR CPF OU CNPJ
                case 3 -> tela.deletarViaNome(clientes); // DELETAR VIA NOME
                case 4 -> {
                    Produto p = tela.cadastrarProduto(produtos); // CADASTRAR PRODUTO
                    if(p != null){produtos.add(p);}
                }
                case 5 -> {
                    Compra c = tela.efetuarCompra(produtos, clientes, compras.size()); // EFETUAR COMPRA
                    if(c != null){compras.add(c);}
                }
                case 6 -> tela.receberPagamento(compras); // RECEBER PAGAMENTO
                case 7 -> relatoriosMenu(tela, clientes, produtos, compras); // ABRIR MENU RELATORIO
                case 8 -> salvarAlteracoes(clientes, produtos, compras); // SALVAR ALTERAÇÕES
                default-> tela.exirbirMensagem("Valor invalido!");

            }
            
        }while(x != 8);

    }
    
    // FUNÇÃO INICIALIZAR CLIENTES
    private static List<Cliente> inicializarClientes(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        ClienteFisico cf;
        ClienteJuridico cj;

        //CRIANDO CLIENTE FISICO E JURIDICO
        cf = new ClienteFisico("caina", "123456789", LocalDate.of(2023, 10, 22), "Rua X", 25, "Centro", "123456", "ibiuna", "SP",12);
        cj = new ClienteJuridico("goblin legionnaire", "Goblin Legionnaire Studio", "1234567890", LocalDate.of(2010,8,04), "Rua Y", 42, "Centro", "12345678", "sorocaba", "SP",120);

        clientes.add(cf); // ADICIONAR EM CLIENTES
        clientes.add(cj); // ADICIONAR EM CLIENTES

        cf = new ClienteFisico("maurice", "123789654", LocalDate.of(2023,04,10), "Rua X", 27, "Centro", "123456", "ibiuna", "SP",6);
        cj = new ClienteJuridico("bazar bagda games", "Bazar Bagda Comercio", "74185296374", LocalDate.of(2012,12,25), "Rua D", 456, "Centro", "12345678", "Sorocaba", "SP",90);

        clientes.add(cf);
        clientes.add(cj);

        cf = new ClienteFisico("julian", "25285825", LocalDate.of(2022,01,28), "Rua rio", 15, "Campo verde", "18150", "Sao roque", "SP",10);
        cj = new ClienteJuridico("ragnarok", "Ragnarok Comercio LTDA", "586889525125", LocalDate.of(2023,05,25), "Rua lima", 111, "Veleiros", "1236548", "Sorocaba", "SP",120);

        clientes.add(cf);
        clientes.add(cj);

        return clientes;
    }

    // FUNÇÃO INICIALIZAR PRODUTOS
    private static List<Produto> inicializarProdutos(){
        List<Produto> produtos = new ArrayList<Produto>();
        Produto p;

        // CRIANDO PRODUTO
        p = new Produto(123, "copo descartavel", "Copos descartaveis de 100ml", 2.50, false, LocalDate.of(2023, 12, 22));
        produtos.add(p); // ADICIONAR EM PRODUTOS

        p = new Produto(124, "sacos platiscos", "Sacos platicos de 50L", 5.75, false, LocalDate.of(2057, 12, 31));
        produtos.add(p);

        p = new Produto(125, "lampada led 110v", "Lampada LED 110v - Phillips", 23.5, false, LocalDate.of(2024, 4, 2));
        produtos.add(p);
        
        p = new Produto(126, "carne", "Contra-Filet", 45.5, true, LocalDate.of(2023, 4, 2));
        produtos.add(p);

        p = new Produto(127, "queijo", "Queijo Mussarela", 35.5, true, LocalDate.of(2024, 4, 2));
        produtos.add(p);

        return produtos;
    }

    // FUNÇÃO INICIALIZAR COMPRAS
    private static List<Compra> inicializarCompras(List<Produto> listaProdutos){
        List<Compra> compras = new ArrayList<Compra>();
        List<ItensComprados> carrinho = new ArrayList<>();
        Compra c;

        if(listaProdutos.isEmpty()){
            System.out.println("Erro, produtos não cadastrados");
            return null;
        }

        // CRIANDO COMPRAS E ADICIONANDO NO CARRINHO
        carrinho.add(new ItensComprados(10, listaProdutos.get(0)));
        c = new Compra(0, LocalDate.of(2023, 5, 15),"123789654", 0.0, carrinho);
        compras.add(c); // ADICIONANDO EM COMPRAS
        carrinho = new ArrayList<>(); //LIMPANDO CARRINHO

        carrinho.add(new ItensComprados(5, listaProdutos.get(3)));
        carrinho.add(new ItensComprados(4, listaProdutos.get(2)));
        c = new Compra(1, LocalDate.of(2023, 10, 15),"74185296374", 0.0, carrinho);
        compras.add(c);
        carrinho = new ArrayList<>();

        carrinho.add(new ItensComprados(9, listaProdutos.get(2)));
        carrinho.add(new ItensComprados(1, listaProdutos.get(3)));
        carrinho.add(new ItensComprados(10, listaProdutos.get(0)));
        c = new Compra(2, LocalDate.of(2022, 02, 15),"25285825", 0.0, carrinho);
        compras.add(c);
        carrinho = new ArrayList<>();

        carrinho.add(new ItensComprados(6, listaProdutos.get(0)));
        c = new Compra(3, LocalDate.of(2021, 5, 31),"86889525125", 0.0, carrinho);
        compras.add(c);
        carrinho = new ArrayList<>();

        carrinho.add(new ItensComprados(8, listaProdutos.get(1)));
        c = new Compra(4, LocalDate.of(2022, 9, 20),"123789654", 0.0, carrinho);
        compras.add(c);
        carrinho = new ArrayList<>();



        return compras;
    }

    // FUNÇÃO MENU RELATORIO
    private static void relatoriosMenu(TelasJoption tela, List<Cliente> clientes, List<Produto> produtos, List<Compra> compras){
        int opcao;

        // OPÇÕES MENU
        String[] menu = {"Buscar Cliente", //feito
                        "Relatorio de Produtos",//feito
                        "Buscar Produto", //feito
                        "Relatorio Produtos vencidos", //feito
                        "Relatorio Compras",//feito
                        "Buscar Compra", //feito
                        "Relatorio Compras com Pendencias", //feito
                        "Relatorio Ultimos pagamentos",
                        "Relatorio Compras(maior valor)", //feito
                        "Relatorio Compras(menor valor)", //feito
                        "Relatorio Compras(ultimo ano)", //feito
                        "Retornar"
                        };
        do{
            opcao = tela.exirbirMenu(menu,"Relatorios");
            if(opcao == -404){opcao = 12;}

            // MENU RELATORIO
            switch (opcao) {
                case 1 -> tela.buscaCliente(clientes); // RELATORIO BUSCARE CLIENTE
                case 2 -> tela.relatorioProdutos(produtos); // RELATORIO PRODUTOS
                case 3 -> tela.buscarProduto(produtos); // RELATORIO BUSCAR PRODUTOS
                case 4 -> tela.buscarProdutoVencido(produtos); // RELATORIO PRODUTO VENCIDO
                case 5 -> tela.relatorioCompras(compras); // RELATORIO COMPRAS
                case 6 -> tela.buscarCompras(compras); // RELATORIO BUSCAR COMPRA
                case 7 -> tela.relatorioComprasPendencia(compras); // RELATORIO COMPRAS PENDENTES
                case 8 -> tela.relatorioUltimosPagamentos(compras); // RELATORIO ULTIMOS PAGAMENTOS
                case 9 -> tela.relatorioComprasMaior(compras); // RELATORIO COMPRAS VALOR MAIOR
                case 10 -> tela.relatorioComprasMenor(compras); // RELATORIO COMPRAS VALOR MENOS
                case 11 -> tela.relatorioUltimoAno(compras); // RELATORIO ULTIMO ANO 
                case 12 -> {return;}
                default-> tela.exirbirMensagem("Valor invalido!");

            }
            
        }while(opcao != 12);
    }

    // FUNÇÃO LER DADOS CLIENTES.TXT
    private static List<Cliente> lerDadosClientes(){

        String caminho = "./src/baseDados/clientes.txt"; // FORNECENDO CAMINHO

        File arquivo = new File(caminho); // CRIANDO UM NOVO ARQUIVO

        if (arquivo.exists()) // VERIFICANDO SE EXISTE O ARQUIVO
        {
            if(arquivo.length()<1){
                System.out.println("Inicializando arquivo");
                return inicializarClientes();
            }
            System.out.println("arquivo existe."); 
        }else {
            try {
                if (arquivo.createNewFile()) // VERIFICA SE FOI CRIADO UM NOVO ARQUIVO
                {
                    System.out.println("O arquivo foi criado com sucesso.");
                    return inicializarClientes();
                } else {
                    System.out.println("Falha ao criar o arquivo."); // ERRO NA CRIAÇÃO
                }
            } catch (IOException e) // FALHA NA CRIAÇÃO
            {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
            }
        }

        List<Cliente> clientes = new ArrayList<Cliente>(); // CRIANDO UMA NOVA LISTA
        
        // TENTANDO LER ARQUIVO
        try(FileInputStream fileIn = new FileInputStream(caminho); 
            ObjectInputStream in = new ObjectInputStream(fileIn)) {

            clientes = (List<Cliente>) in.readObject(); // CONVERÇÃO E ATRIBUIÇÃO DO OBJETO LIDO
            
        } catch (Exception e) {
            e.printStackTrace(); // ERRO NA LEITURA
        }

        return clientes;
    }

    // FUNÇÃO LER DADOS PRODUTOS.TXT
    private static List<Produto> lerDadosProdutos(){
        String caminho = "./src/baseDados/produtos.txt"; // FORNECENDO CAMINHO

        File arquivo = new File(caminho); // CRIANDO UM NOVO ARQUIVO

        if (arquivo.exists()) // VERIFICANDO SE EXISTE O ARQUIVO
        {
            if(arquivo.length()<1){
                System.out.println("Inicializando arquivo");
                return inicializarProdutos();
            }
            System.out.println("arquivo existe.");
        }else {
            try {
                if (arquivo.createNewFile()) // VERIFICA SE FOI CRIADO UM NOVO ARQUIVO
                {
                    System.out.println("O arquivo foi criado com sucesso.");
                    return inicializarProdutos();
                } else {
                    System.out.println("Falha ao criar o arquivo."); // ERRO NA CRIAÇÃO
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage()); // FALHA NA CRIAÇÃO
            }
        }

        List<Produto> produtos = new ArrayList<Produto>(); // CRIANDO UMA NOVA LISTA

        try(FileInputStream fileIn = new FileInputStream(caminho);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {

            produtos = (List<Produto>) in.readObject(); // CONVERÇÃO E ATRIBUIÇÃO DO OBJETO LIDO
            
        } catch (Exception e) {
            e.printStackTrace(); // ERRO NA LEITURA
        }

        return produtos;
    }

    // FUNÇÃO LER DADOS COMPRAS.TXT
    private static List<Compra> lerDadosCompras(List<Produto> listaProdutos){
        String caminho = "./src/baseDados/compras.txt"; // FORNECENDO CAMINHO

        File arquivo = new File(caminho); // CRIANDO UM NOVO ARQUIVO

        if (arquivo.exists()) // VERIFICANDO SE EXISTE O ARQUIVO
        {
            if(arquivo.length()<1){
                System.out.println("Inicializando arquivo");
                return inicializarCompras(listaProdutos);
            }
            System.out.println("arquivo existe.");
        }else {
            try {
                if (arquivo.createNewFile()) // VERIFICA SE FOI CRIADO UM NOVO ARQUIVO
                {
                    System.out.println("O arquivo foi criado com sucesso.");
                    return inicializarCompras(listaProdutos);
                } else {
                    System.out.println("Falha ao criar o arquivo."); // ERRO NA CRIAÇÃO
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage()); //FALHA NA CRIAÇÃO
            }
        }
        List<Compra> compras = new ArrayList<Compra>(); // CRIANDO UMA NOVA LISTA

        try(FileInputStream fileIn = new FileInputStream(caminho);
            ObjectInputStream in = new ObjectInputStream(fileIn))
            {
            compras = (List<Compra>) in.readObject(); // CONVERÇÃO E ATRIBUIÇÃO DO OBJETO LIDO
            
        } catch (Exception e) {
            e.printStackTrace(); // ERRO NA LEITURA
        }

        return compras;
    }


    //FUNÇÃO SALVAR ALTERAÇÕES
    private static void salvarAlteracoes(List<Cliente> listaClientes,List<Produto> listaProdutos, List<Compra> listaCompras){
        // TENTANDO BUSCAR ARQUIVO
        try (FileOutputStream fileOut = new FileOutputStream("./src/baseDados/clientes.txt"); 
            ObjectOutputStream saida = new ObjectOutputStream(fileOut)) {
                saida.writeObject(listaClientes); //ESCREVENDO NO ARQUIVO
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TENTANDO BUSCAR ARQUIVO
        try (FileOutputStream fileOut = new FileOutputStream("./src/baseDados/produtos.txt");
            ObjectOutputStream saida = new ObjectOutputStream(fileOut)) {
                saida.writeObject(listaProdutos); //ESCREVENDO NO ARQUIVO
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TENTANDO BUSCAR ARQUIVO
        try (FileOutputStream fileOut = new FileOutputStream("./src/baseDados/compras.txt");
            ObjectOutputStream saida = new ObjectOutputStream(fileOut)) {
                saida.writeObject(listaCompras); //ESCREVENDO NO ARQUIVO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
