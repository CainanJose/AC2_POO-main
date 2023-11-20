package telas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cliente.Cliente;
import cliente.ClienteFisico;
import cliente.ClienteJuridico;
import compra.Compra;
import compra.ItensComprados;
import produto.Produto;


public class TelasJoption extends DesenharTelas{
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    //FUNÇÃO EXIBIR MENU
    public int exirbirMenu(String[] opcoesMenu, String tituloMenu){
        String opcaoEscolida;
        String menu = "[-------"+tituloMenu+"-------]\n";

        for(int i=0; i<opcoesMenu.length;i++){
            menu += ("\n["+ (i+1) +"] - " + opcoesMenu[i]);
        }

        menu += "\n ";

        opcaoEscolida = lerDados(menu);

        return Integer.parseInt(opcaoEscolida); // CONVERÇÃO PARA INTEIRO
    }

    //FUNÇÃO CADASTRAR CLIENTE
    public Cliente cadastrarCliente(List<Cliente> listaClientes){
        //VARIAVEIS DE VERIFICAÇÃO DE CLIENTE
        final String[] tipoCliente = {"Física","Juridica","Cancelar"};
        int opcao;

        //VARIAVEIS DE DADOS DO CLIENTE
        String nome;
        String razaoSocial;
        String numDocumento;
        LocalDate dataCadastro;
        String rua;
        int    numero;
        String bairro;
        String cep;
        String cidade;
        String estado;
        int prazo;


        //PERGUNTA TIPO DE CLIENTE A SER CADASTRADO
        opcao = opcoesTela(tipoCliente,"Qual o tipo de cliente deseja cadastrar?");


        switch(opcao){
            //CADASTRO PESSOA FISICA
            case 0 ->{
                nome = lerDados("Digite o nome do cliente:");
                numDocumento = lerDados("Digite o CPF do cliente:");

                //VERIFICA SE O CLIENTE JA ESTA CADASTRADO
                for(Cliente cliente : listaClientes){
                    if(cliente instanceof ClienteFisico){
                        ClienteFisico cf = (ClienteFisico)cliente;
                        if(cf.getNome().equals(nome) && cf.getCpf().equals(numDocumento)){
                            exirbirMensagem("Cliente já cadastrado");
                            return null;
                        }
                    }
                }

                rua = lerDados("Informe o endereço:\nDigite a rua do cliente:");
                numero = lerDadosInt("Informe o endereço:\nDigite o número do imovel do cliente:");
                bairro = lerDados("Informe o endereço:\nDigite o Bairro do cliente:");
                cidade = lerDados("Informe o endereço:\nDigite o cidade do cliente:");
                cep = lerDados("Informe o endereço:\nDigite o CEP do cliente:");
                estado = lerDados("Informe o endereço:\nDigite o estado do cliente:");
                dataCadastro = LocalDate.now();
                prazo = lerDadosInt("Informe a quantidade maximas que o \ncliente pode parcelar:");
                
                // VERIFICAÇÃO DE VALORES
                if(nome.isEmpty() || numDocumento.isEmpty() || rua.isEmpty() || numero == -404 || bairro.isEmpty() ||
                    cidade.isEmpty() || cep.isEmpty() || estado.isEmpty() || prazo == -404){
                        exirbirMensagem("Erro ao passar valores. Acao Cancelada");
                        return null;
                }

                ClienteFisico cf = new ClienteFisico(nome, numDocumento, dataCadastro, rua, numero, bairro, cep, cidade, estado,prazo);
                exirbirMensagem("Cliente cadastrado com sucesso!!");
                return cf;
            }
            case 1 ->{
                //CADASTRO PESSOA JURIDICA
                nome = lerDados("Digite o nome fantasia do cliente:");
                razaoSocial = lerDados("Digite a razão social do cliente:");
                numDocumento = lerDados("Digite o CNPJ do cliente:");

                //VERIFICA SE O CLIENTE JA ESTA CADASTRADO
                for(Cliente cliente : listaClientes){
                    if(cliente instanceof ClienteJuridico){
                        ClienteJuridico cj = (ClienteJuridico)cliente;
                        if(cj.getNome().equals(nome) && cj.getCnpj().equals(numDocumento)){
                            exirbirMensagem("Cliente já cadastrado");
                            return null;
                        }
                    }
                }

                rua = lerDados("Informe o endereço:\nDigite a rua do cliente:");
                numero = lerDadosInt("Informe o endereço:\nDigite o númro do imovel do cliente:");
                bairro = lerDados("Informe o endereço:\nDigite o Bairro do cliente:");
                cidade = lerDados("Informe o endereço:\nDigite o cidade do cliente:");
                cep = lerDados("Informe o endereço:\nDigite o CEP do cliente:");
                estado = lerDados("Informe o endereço:\nDigite o estado do cliente:");
                dataCadastro = LocalDate.now();
                prazo = lerDadosInt("Informe a quantidade maximas de dias que o \ncliente tem para o pagamento:");

                // VERIFICAÇÃO DE VALORES
                if(nome.isEmpty() || razaoSocial.isEmpty() || numDocumento.isEmpty() || rua.isEmpty() || numero == -404 || bairro.isEmpty() ||
                    cidade.isEmpty() || cep.isEmpty() || estado.isEmpty() || prazo == -404){
                        exirbirMensagem("Erro ao passar valores. Acao Cancelada");
                        return null;
                }

                ClienteJuridico cj = new ClienteJuridico(nome, razaoSocial, numDocumento, dataCadastro, rua, numero, bairro, cep, cidade, estado, prazo);
                exirbirMensagem("Cliente cadastrado com sucesso!!");
                return cj;

            }
            //RETORNA AO MENU
            default -> {
                exirbirMensagem("Ação cancelada pelo usuario");
                return null;
            }
        }
    }

    //FUNÇÃO CADASTRAR PRODUTO
    public Produto cadastrarProduto(List<Produto> listaProdutos){
        //VARIAVEIS DE CADASTRO
        int codigoProduto;
        String nomeProduto;
        String descricao;
        Double preco;
        boolean isPerecivel;
        LocalDate dataValidade;

        //RECEBE DADOS DO PRODUTO
        codigoProduto = lerDadosInt("Digite o codigo do Produto:");
        nomeProduto = lerDados("Digite o nome do Produto:");

        //VERIFICA SE O PRODUTO JA ESTA CADASTRADO
        for(Produto produto : listaProdutos){
            if(produto.getCodigo() == codigoProduto){
                exirbirMensagem("Produto já cadastrado!");
                return null;
            }
        }

        descricao = lerDados("Digite o descrição do Produto:");
        preco = lerDadosDouble("Digite o valor do Produto:\n(formato: xxx.xx)");

        //VERIFICA SE PRODUTO É PERECIVEL OU NÃO
        isPerecivel = confirmarTela("O produto é perecivel?");

        //RECEBE DATA VALIDADE DO PRODUTO
        dataValidade = lerDadosData("Digite a data de validate do Produto:\n(formato: dd/MM/yyyy)");

        // VERIFICAÇÃO DE VALORES
        if(codigoProduto == -404 || nomeProduto.isEmpty() || descricao.isEmpty() || preco == -404.00 || dataValidade == null ){
            exirbirMensagem("Erro ao passar valores. Acao Cancelada");
                        return null;
        }

        Produto produto = new Produto(codigoProduto, nomeProduto, descricao, preco, isPerecivel, dataValidade);

        exirbirMensagem("Produto cadastrado com sucesso!");
        return produto;
    }

    //FUNCAO DELETAR CLIENTE VIA CPF OU CNPJ
    public void deletarViaCpfCnpj(List<Cliente> clientes){
        String valorDeBusca;
        boolean opcaoExclusao;
        int indiceCliente = -1;
        
        // VERIFICANDO SE TEM CLIENTES CADASTRADOS
        if(clientes.isEmpty()){
            exirbirMensagem("Não há Clientes cadastrados!");
            return;
        }

        valorDeBusca = lerDados("Digite o CPF/CNPJ:");

        //ACESSANDO E VERIFICANDO SE É FISICO OU JURIDICO
        for (Cliente cliente : clientes) {
            if(cliente instanceof ClienteFisico){
                ClienteFisico cf = (ClienteFisico)cliente;
                if(cf.getCpf().equals(valorDeBusca)){indiceCliente = clientes.indexOf(cliente);}
            }else{
                ClienteJuridico cj = (ClienteJuridico)cliente;
                if(cj.getCnpj().equals(valorDeBusca)){indiceCliente = clientes.indexOf(cliente);}
            }

        }

        if(indiceCliente < 0){
            exirbirMensagem("Cliente não encontrado!");
            return;
        }

        opcaoExclusao = confirmarTela("Deseja realmente deletar este cliente?\nNome: " + clientes.get(indiceCliente).getNome()); // CONFIRMAÇÃO DE EXCLUSAO

        if(opcaoExclusao){
            clientes.remove(indiceCliente); // REMOVE CLIENTE
            return;
        }

    }

    //FUNCAO DELETAR CLIENTE VIA NOME
    public void deletarViaNome(List<Cliente> clientes){
        String valorDeBusca;
        boolean opcaoExclusao;
        int indiceCliente = -1;

        // VERIFICA SE EXISTE CLIENTES CADASTRADOS
        if(clientes.isEmpty()){
            exirbirMensagem("Não há Clientes cadastrados!");
            return;
        }

        valorDeBusca = lerDados("Digite o nome do Cliente:");
        
        
        for (Cliente cliente : clientes) {

            if(cliente.getNome().equals(valorDeBusca)){
                indiceCliente = clientes.indexOf(cliente);
            }

        }

        opcaoExclusao = confirmarTela("Deseja realmente deletar este cliente?\nNOME: " + clientes.get(indiceCliente).getNome()); //CONFIRMAÇÃO DE EXCLUSAO

        if(opcaoExclusao){
            clientes.remove(indiceCliente);
            return;
        }
                

        exirbirMensagem("Cliente não encontrado!");
    }

    //FUNCAO COMPRA
    public Compra efetuarCompra(List<Produto> produtosDiponiveis, List<Cliente> clientes, int codigoCompra){
        ArrayList<ItensComprados> carinhoCompras = new ArrayList<ItensComprados>();
        boolean continuarComprando = true;
        int quantidadeProduto;
        int indexProduto = 0;
        int indexCliente = 0;
        
        //RECEBE O CLIENTE QUE VAI COMPRAR
        String clienteCompra = lerDados("Digite o nome do Cliente:");

        //CHECA SE O CLIENTECOMPRA NÃO ESTA VAZIO
        if(clienteCompra != null){
            
            //PEGA O INDEX DO CLIENTE
            for (Cliente cliente : clientes) {
                if(cliente.getNome().equals(clienteCompra)){
                    indexCliente = clientes.indexOf(cliente);
                }
            }
        }
        else{
            //EXIBE MENSAGEM E RETORNA NULL SE O CLIENTE COMPRA ESTIVER VAZIO
            exirbirMensagem("Acao cancelada pelo usuario");
            return null;
        }
        
        //ADICIONA ITENS NO CARRINHO DE COMPRAS
        while (continuarComprando) {
            
            //CRIA UMA LISTA STRING COM O NOME DOS PRODUTOS E ADICIONA UMA STRING NO FINAL
            String[] estoqueProtudos = new String[produtosDiponiveis.size()+1];
            for(int i=0; i<produtosDiponiveis.size();i++){
                estoqueProtudos[i] = (produtosDiponiveis.get(i).getNome() + "| preco(un): "+ produtosDiponiveis.get(i).getPreco());
            }
            
            estoqueProtudos[produtosDiponiveis.size()]= "Finalizar Compras";
            
            //CRIA UMA TELA COM DROPDOWN PASSANDO A LISTA STRING COMO S OPCOES DO DORPDOWN
            String produtoEscolhido = dropDownTela(estoqueProtudos, "Selecione um Produto");
            produtoEscolhido = produtoEscolhido.substring(0, produtoEscolhido.indexOf("|"));

            if(produtoEscolhido == null){
                exirbirMensagem("Compra cancelada pelo usuario.");
                return null;
            }
            //VERIFICA SE O USUARIO NÃO FINALIZOU AS COMPRAS E SE ESCOLHEU UM PRODUTO VALIDO
            if(produtoEscolhido != "Finalizar Compras"){

                //PEGA O INDEX DO PRODUTO ESCOLHIDO
                for (int i=0;i< produtosDiponiveis.size();i++ ) {
                    if(produtoEscolhido.equals(produtosDiponiveis.get(i).getNome())){
                        indexProduto = i;
                        break;
                    }
                }
                //RECEBE A QUANTIDADE DE ITENS QUE O USUARIO QUER
                do{
                quantidadeProduto = lerDadosInt("Digite a quantidade do produto:");
                if(quantidadeProduto == -404){
                    exirbirMensagem("escolha a quantidade do produto");
                }
                }while(quantidadeProduto == -404 || quantidadeProduto !=0);

                if(quantidadeProduto ==0){
                    exirbirMensagem("Comprar cancelada pelo usuario.");
                    return null;
                }
                //ADICIONA NO CARRINHO A QUANTIDADE E O PRODUTO ESCOLHIDO
                carinhoCompras.add(new ItensComprados(quantidadeProduto, produtosDiponiveis.get(indexProduto)));

                //VERIFICA SE O USUARIO QUER CONTINUAR COMPRANDO
                continuarComprando = confirmarTela("Deseja continuar comprando?");
            }else{
                //ENCERRA A COMPRA
                continuarComprando = false;
            }

        }

        //VERIFICA SE O CARRINHO NÃO ESTA VAZIO
        if(carinhoCompras.isEmpty()){
            exirbirMensagem("Carrinho Vazio. Acao cancelada pelo usuario");
            return null;
        }

        //CRIA A COMPRA E RETORNA
        Compra compra;
        if(clientes.get(indexCliente) instanceof ClienteFisico){
            ClienteFisico cf = (ClienteFisico) clientes.get(indexCliente);
            compra = new Compra(codigoCompra, LocalDate.now(), cf.getCpf(), 0.0, carinhoCompras);
        }else{
            ClienteJuridico cj = (ClienteJuridico) clientes.get(indexCliente);
            compra = new Compra(codigoCompra, LocalDate.now(), cj.getCnpj(), 0.0, carinhoCompras);
        }
        
        exirbirMensagem("Compra realizada com sucesso!\nCodigo da compra:" + codigoCompra);
        return compra;
    }

    //FUNCAO RECEBER PAGAMENTO
    public void receberPagamento(List<Compra> listaComprasFeitas){
        int codigoCompra;
        double valorPagamento;
        boolean confirmarPagamento;

        // VERIFICA SE HA COMPRAS REGISTRADAS
        if(listaComprasFeitas.isEmpty()){
            exirbirMensagem("Não há compras registradas no sistema");
            return;
        }

        codigoCompra = lerDadosInt("Digite o codigo da compra");
        

        if(listaComprasFeitas.get(codigoCompra) == null){
            exirbirMensagem("Compra não localizada, acao cancelada");
            return;
        }else{

            if(codigoCompra > listaComprasFeitas.size() || codigoCompra < 0){
                exirbirMensagem("Não existe compra com este codigo, acao cancelada");
                return;
            }

            valorPagamento = lerDadosDouble("Valor a pagar:\n(formato: xx.x)");

            confirmarPagamento = confirmarTela("Desejar efetuar este pagamento?\nValor a ser pago: "+ valorPagamento);

            //  VERIFICAÇÃO DE CONTAS PENDENTES E OPÇÃO DE PAGAMENTO
            if(confirmarPagamento && valorPagamento <= listaComprasFeitas.get(codigoCompra).getSaldoDevedor()){
                listaComprasFeitas.get(codigoCompra).setPagamento(valorPagamento);
                if(listaComprasFeitas.get(codigoCompra).getSaldoDevedor() == 0.0){
                    listaComprasFeitas.get(codigoCompra).setDataUltimoPagamento(LocalDate.now());
                }
                exirbirMensagem("Pagamento no valor de R$ " + valorPagamento + " realizado com sucesso!!\n\nSaldo devedor: " + listaComprasFeitas.get(codigoCompra).getSaldoDevedor());
            }else if(valorPagamento > listaComprasFeitas.get(codigoCompra).getSaldoDevedor()){
                exirbirMensagem("Valor informado maior que o deevido. Acao cancelada.");
                return;
            }else{
                exirbirMensagem( "Acao cancelada pelo usuario.");
                return;
            }
            
        }

    }


    //RELATORIOS

    //FUNCAO BUSCAR CLIENTE POR PARTE DO NOME
    public void buscaCliente(List<Cliente> listaClientes){
        String relatorio = "[Clientes Encontrados]\n\n";
        String[] opcoes = {"Retornar"};
        String valorBusca;
        boolean adicionarLinha = false;

        valorBusca = lerDados("Digite o nome ou o inicio do nome do cliente:");

        for(Cliente cliente : listaClientes){
            if(cliente.getNome().startsWith(valorBusca.toLowerCase())){
                if(adicionarLinha){
                    relatorio += "\n-----------------------------------------------\n\n";
                }
                if(cliente instanceof ClienteFisico){
                    ClienteFisico cf = (ClienteFisico)cliente;
                    relatorio += "Nome: " + cf.getNome() + "\n";
                    relatorio += "CPF: " + cf.getCpf() + "\n";
                    relatorio += "Data Cadastro: " + cf.getDataCadastro() + "\n";
                    relatorio += "Endereco: " + cf.getEndereco().getRua() + ", Nº: "+ cf.getEndereco().getNumero() + "\n";
                    relatorio += "         Bairro: " + cf.getEndereco().getBairro() + ", Cidade: "+ cf.getEndereco().getCidade() + "\n";
                    relatorio += "         Cidade: " + cf.getEndereco().getCidade() + ", CEP: "+ cf.getEndereco().getCep() +" / " + cf.getEndereco().getEstado() + "\n";
                    relatorio += "Parcelas Maximas Permitidas: " + cf.getParcelasMaximas() + "\n";
                }else{
                    ClienteJuridico cj = (ClienteJuridico)cliente;
                    relatorio += "Nome Fantasia: " + cj.getNome() + "\n";
                    relatorio += "Razao Social: " + cj.getRazaoSocial() + "\n";
                    relatorio += "CNPJ: " + cj.getCnpj() + "\n";
                    relatorio += "Data Cadastro: " + cj.getDataCadastro() + "\n";
                    relatorio += "Endereco: " + cj.getEndereco().getRua() + ", Nº: "+ cj.getEndereco().getNumero() + "\n";
                    relatorio += "         Bairro: " + cj.getEndereco().getBairro() + ", Cidade: "+ cj.getEndereco().getCidade() + "\n";
                    relatorio += "         Cidade: " + cj.getEndereco().getCidade() + ", CEP: "+ cj.getEndereco().getCep() +" / " + cj.getEndereco().getEstado() + "\n";
                    relatorio += "Pazos para Pagamentos (em dias): " + cj.getPrazoDiasParaPagar() + "\n";
                }
                adicionarLinha = true;
            }
        }

        // VERIFICAÇÃO DE CLIENTE
        if(adicionarLinha == false){
            exirbirMensagem("Cliente inexistente!!");
            return;
        }

        opcoesTela(opcoes, relatorio);

    }

    //FUNCAO RELATORIO PRODUTOS
    public void relatorioProdutos(List<Produto> listaProdutos){
        String relatorio = "[Relatorio Produto]\n\n";
        String[] opcoes = {"Retornar"};
        
        // VERIFICAÇÃO DE PRODUTOS
        if(listaProdutos.size() <= 0){
            exirbirMensagem("Não há produtos cadastrados!");
            return;
        }

        //EXIBIR RELATORIO
        for(int i=0; i<listaProdutos.size() ;i++){
            relatorio += "Nome: " + listaProdutos.get(i).getNome() + "\n";
            relatorio += "Descricao: " + listaProdutos.get(i).getDescricao() + "\n";
            relatorio += "É prerecivel?: " + listaProdutos.get(i).getIsPerecivel() + "\n";
            relatorio += "Validade: " + listaProdutos.get(i).getDataValidade() + "\n";
            relatorio += "Valor unitario: " + listaProdutos.get(i).getPreco() + "\n";
            if(i+1 < listaProdutos.size()){
                relatorio += "\n--------------------------------------------------------\n\n";
            }else{
                relatorio += "\n ";
            }
            

        }

        opcoesTela(opcoes, relatorio);
    }

    //FUNCAO BUSCAR PRODUTO
    public void buscarProduto(List<Produto> listaProdutos){
        String relatorio = "[Produto Encontrado]\n\n";
        String[] opcoes = {"Retornar"};
        String valorBusca;
        boolean adicionarLinha = false;

        valorBusca = lerDados("Digite o nome ou parte do nome do Produto:");

        //EXIBIR RELATORIO
        for(Produto produto : listaProdutos){
            if(produto.getNome().contains(valorBusca)){
                if(adicionarLinha){
                    relatorio += "\n-----------------------------------------------\n\n";
                }
                relatorio += "Nome: " + produto.getNome() + "\n";
                relatorio += "Descricao: " + produto.getDescricao() + "\n";
                relatorio += "É prerecivel?: " + produto.getIsPerecivel() + "\n";
                relatorio += "Validade: " + produto.getDataValidade() + "\n";
                relatorio += "Valor unitario: " + produto.getPreco() + "\n";

                adicionarLinha = true;
            }
        }

        // NAO HA PRODUTOS
        if(adicionarLinha == false){
            exirbirMensagem("Produto inexistente!!");
            return;
        }

        opcoesTela(opcoes, relatorio);
    }

    //FUNCAO BUSCAR PRODUTO Vencido
    public void buscarProdutoVencido(List<Produto> listaProdutos){
        String relatorio = "[Produto Encontrado]\n\n";
        String[] opcoes = {"Retornar"};
        boolean adicionarLinha = false;

        //EXIBIR RELATORIO
        for(Produto produto : listaProdutos){
            if(produto.getIsPerecivel() && produto.getDataValidade().isBefore(LocalDate.now())){
                if(adicionarLinha){
                    relatorio += "\n-----------------------------------------------\n\n";
                }
                relatorio += "Nome: " + produto.getNome() + "\n";
                relatorio += "Descricao: " + produto.getDescricao() + "\n";
                relatorio += "É prerecivel?: " + produto.getIsPerecivel() + "\n";
                relatorio += "Validade: " + produto.getDataValidade() + "\n";
                relatorio += "Valor unitario: " + produto.getPreco() + "\n";

                adicionarLinha = true;
            }
        }

        // NAO HA PRODUTOS VENCIDOS
        if(adicionarLinha == false){
            relatorio += "Não há nenhum produto vencido\n ";
        }

        opcoesTela(opcoes, relatorio);
    }

    //FUNCAO RELATORIO COMPRAS
    public void relatorioCompras(List<Compra> listaCompras){
        String relatorio = "[Relatorio Compras]\n\n";
        String[] opcoes = {"Retornar"};

        // VERIFICAÇÃO DE COMPRAS
        if(listaCompras.size() <= 0){
            exirbirMensagem("Não há compras!");
            return;
        }

        //EXIBIR RELATORIO
        for(int i=0; i<listaCompras.size() ;i++){
            relatorio += "Codigo Compra: " + listaCompras.get(i).getCodigoCompra() + "\n";
            relatorio += "Nome Comprador: " + listaCompras.get(i).getindentificadorCliente() + "\n";
            relatorio += "Data da Compra: " + listaCompras.get(i).getDataCompra() + "\n";
            relatorio += "Valor Total da Compra: " + listaCompras.get(i).getValorTotalCompra() + "\n";
            relatorio += "Valor Pago: " + listaCompras.get(i).getValorPago() + "\n";
            relatorio += "Valor a Pagar: " + listaCompras.get(i).getSaldoDevedor() + "\n";
            if(i+1 < listaCompras.size()){
                relatorio += "\n--------------------------------------------------------\n\n";
            }else{
                relatorio += "\n ";
            }
            

        }

        opcoesTela(opcoes, relatorio);
    }

     //FUNCAO RELATORIO COMPRAS
    public void buscarCompras(List<Compra> listaCompras){
        String relatorio = "[Compra Encontrada]\n\n";
        String[] opcoes = {"Retornar"};
        int valorBusca;
        boolean valorEncontrado = false;

        valorBusca = lerDadosInt("Digite o codigo da compra");

        //EXIBIR RELATORIO
        for (Compra compra : listaCompras) {
            if(compra.getCodigoCompra() == valorBusca){
                relatorio += "Codigo Compra: " + compra.getCodigoCompra() + "\n";
                relatorio += "Nome Comprador: " + compra.getindentificadorCliente() + "\n";
                relatorio += "Data da Compra: " + compra.getDataCompra() + "\n";
                relatorio += "Valor Total da Compra: " + compra.getValorTotalCompra() + "\n";
                relatorio += "Valor Pago: " + compra.getValorPago() + "\n";
                relatorio += "Valor a Pagar: " + compra.getSaldoDevedor() + "\n";
                valorEncontrado = true;
                break;
            }
        }

        // NAO HA C0MPRAS POR CODIGO
        if(valorEncontrado == false){
            relatorio += "Não há nenhuma compra com este codigo\n ";
        }

        opcoesTela(opcoes, relatorio);
    }

     //FUNCAO RELATORIO COMPRAS COM PENDENCIAS
    public void relatorioComprasPendencia(List<Compra> listaCompras){
        String relatorio = "[Compras com Pendencias Encontradas]\n\n";
        String[] opcoes = {"Retornar"};
        boolean adicionarLinha = false;

        //EXIBIR RELATORIO
        for (Compra compra : listaCompras) {
            if(compra.getSaldoDevedor() > 0.0){
                if(adicionarLinha){
                    relatorio += "\n-----------------------------------------------\n\n";
                }
                relatorio += "Codigo Compra: " + compra.getCodigoCompra() + "\n";
                relatorio += "Nome Comprador: " + compra.getindentificadorCliente() + "\n";
                relatorio += "Data da Compra: " + compra.getDataCompra() + "\n";
                relatorio += "Valor Total da Compra: " + compra.getValorTotalCompra() + "\n";
                relatorio += "Valor Pago: " + compra.getValorPago() + "\n";
                relatorio += "Valor a Pagar: " + compra.getSaldoDevedor() + "\n";
                
                adicionarLinha = true;
            }
        }

        // NAO HA COMPRAS PENDENTES
        if(adicionarLinha == false){
            relatorio += "Não há nenhuma compra com pendencia\n ";
        }

        opcoesTela(opcoes, relatorio);
    }

    //FUNCAO RELATORIO ULTIMOS PAGAMENTOS
    public void relatorioUltimosPagamentos(List<Compra> listaCompras){
        List<Compra> comprasQuitadas = new ArrayList<Compra>();
        int quantidadeCompra;
        String relatorio = "[Ultimas Compras Quitadas]\n\n";
        String[] opcoes = {"Retornar"};
        boolean adicionarLinha = false;

        // PEGANDO AS COMPRAS QUITADAS
        for(Compra compra : listaCompras){
                if(compra.getDataUltimoPagamento() != null){
                    comprasQuitadas.add(compra);
                }
        }

        // ORGANIZANDO DE TRAZ PARA FRENTE AS COMPRAS QUITADAS
        Collections.sort(comprasQuitadas, Collections.reverseOrder((c1, c2) -> c2.getDataUltimoPagamento().compareTo(c1.getDataUltimoPagamento())));

        //VERIFICANDO SE A QUANTIDADE DE COMPRAS NO MAX(10)
        if (comprasQuitadas.size() < 10) {
            quantidadeCompra = comprasQuitadas.size();
        }else{
            quantidadeCompra = 10;
        }

        //EXIBIR RELATORIO
        for(int i=0;i<quantidadeCompra; i++){
            if(adicionarLinha){
                    relatorio += "\n-----------------------------------------------\n\n";
            }
            relatorio += "Codigo Compra: " + comprasQuitadas.get(i).getCodigoCompra() + "\n";
            relatorio += "Nome Comprador: " + comprasQuitadas.get(i).getindentificadorCliente() + "\n";
            relatorio += "Data da Compra: " + comprasQuitadas.get(i).getDataCompra() + "\n";
            relatorio += "Valor Total da Compra: " + comprasQuitadas.get(i).getValorTotalCompra() + "\n";
            relatorio += "Data da Quitação: " + comprasQuitadas.get(i).getDataUltimoPagamento() + "\n";

            adicionarLinha = true;
        }
        
        // NAO HA COMPRA QUITADA
        if(adicionarLinha == false){
            relatorio += "Não há nenhuma compra quitada.\n ";
        }

        opcoesTela(opcoes, relatorio);

    }

    //FUNCAO RELATORIO COMPRAS (MAIOR VALOR)
    public void relatorioComprasMaior(List<Compra> listaCompras){
        String relatorio = "[Maior Compra Encontrada]\n\n";
        String[] opcoes = {"Retornar"};

        //VERIFICANDO SE TEM COMPRAS
        if(listaCompras.size() <= 0){
            exirbirMensagem("Não há compras!");
            return;
        }

        //CRIANDO UM COMPARADOR DE VALOR ORDENADO 
        Comparator<Compra> comparadorPorValor = Comparator.comparing(Compra::getValorTotalCompra, Comparator.reverseOrder());

        // ORGANIZANDO DO MAIOR PARA O MENOR
        Collections.sort(listaCompras, comparadorPorValor);

        //EXIBIR RELATORIO
        relatorio += "Codigo Compra: " + listaCompras.get(0).getCodigoCompra() + "\n";
        relatorio += "Nome Comprador: " + listaCompras.get(0).getindentificadorCliente() + "\n";
        relatorio += "Data da Compra: " + listaCompras.get(0).getDataCompra() + "\n";
        relatorio += "Valor Total da Compra: " + listaCompras.get(0).getValorTotalCompra() + "\n";
        relatorio += "Valor Pago: " + listaCompras.get(0).getValorPago() + "\n";
        relatorio += "Valor a Pagar: " + listaCompras.get(0).getSaldoDevedor() + "\n ";


        opcoesTela(opcoes, relatorio);
    }

    //FUNCAO RELATORIO COMPRAS (MENOR VALOR)
    public void relatorioComprasMenor(List<Compra> listaCompras){
        String relatorio = "[Relatorio Compras]\n\n";
        String[] opcoes = {"Retornar"};

        // VERIFICANDO SE TEM COMPRAS
        if(listaCompras.size() <= 0){
            exirbirMensagem("Não há compras!");
            return;
        }

        //CRIANDO UM COMPARADOR DE VALOR ORDENADO 
        Comparator<Compra> comparadorPorValor = Comparator.comparing(Compra::getValorTotalCompra);

        // ORGANIZANDO DO MENOR PARA O MAIOR
        Collections.sort(listaCompras, comparadorPorValor);

        //EXIBIR RELATORIO 
        relatorio += "Codigo Compra: " + listaCompras.get(0).getCodigoCompra() + "\n";
        relatorio += "Nome Comprador: " + listaCompras.get(0).getindentificadorCliente() + "\n";
        relatorio += "Data da Compra: " + listaCompras.get(0).getDataCompra() + "\n";
        relatorio += "Valor Total da Compra: " + listaCompras.get(0).getValorTotalCompra() + "\n";
        relatorio += "Valor Pago: " + listaCompras.get(0).getValorPago() + "\n";
        relatorio += "Valor a Pagar: " + listaCompras.get(0).getSaldoDevedor() + "\n ";


        opcoesTela(opcoes, relatorio);
    }

    //FUNCAO RELATORIO COMPRAS (ULTIMO ANO)
    public void relatorioUltimoAno(List<Compra> listaCompras){
        String relatorio = "[Relatorio Vendas - Ultimo Ano]\n\n";
        String[] opcoes = {"Retornar"};
        
        // LISTA DE VALORES POR MES
        Map<LocalDate, Double> valorPorMes = new TreeMap<>(); 
        

        // VERIFICANDO ENTRE 12 MESES, OS VALORES DE COMPRAS E SOMANDO 
        for(int i=0; i<12; i++){
            LocalDate dataInicio = LocalDate.now().minusMonths(i).withDayOfMonth(1);
            LocalDate dataFinal  = dataInicio.plusMonths(1);
            double valorMes = 0.0;
            for (Compra compra : listaCompras) {
                LocalDate dataCompra = compra.getDataCompra();

                if (dataCompra.isAfter(dataInicio.minusDays(1)) && dataCompra.isBefore(dataFinal)) {
                    valorMes += compra.getValorTotalCompra();
                }
            }

            valorPorMes.put(dataInicio, valorMes);

        }

        // EXIBIR RELATORIO 
        for(Map.Entry<LocalDate, Double> vendas : valorPorMes.entrySet()){
            relatorio += vendas.getKey().getMonth() + " / " + vendas.getKey().getYear()+ " : R$ " + vendas.getValue() + "\n";
        }

        relatorio += "\n ";

        opcoesTela(opcoes, relatorio);

    }

}
