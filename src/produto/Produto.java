package produto;

import java.io.Serializable;
import java.time.LocalDate;

// CLASSE PRODUTO
public class Produto implements Serializable{
    // VARIAVEL NECESSARIA PARA O SERIALIZABLE
    private static final long serialVersionUID = 1L;
    
    private int codigo;
    private String nomeProduto;
    private String descricao;
    private Double preco;
    private boolean isPerecivel;
    private LocalDate dataValidade;

    //CONSTRUTOR
    public Produto(int codigo, String nomeProduto, String descricao, Double preco, boolean isPerecivel,
            LocalDate dataValidade) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.isPerecivel = isPerecivel;
        this.dataValidade = dataValidade;
    }

    //GET: CODIGO
    public int getCodigo() {
        return codigo;
    }


    //GET E SET: NOME
    public String getNome() {
        return nomeProduto;
    }
    public void setNome(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    //GET E SET: DESCRICAO
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //GET E SET: PRECO
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    //GET E SET: PERECIVEL
    public boolean getIsPerecivel() {
        return isPerecivel;
    }
    public void setPerecivel(boolean isPerecivel) {
        this.isPerecivel = isPerecivel;
    }

    //GET E SET: DATA_VALIDADE
    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    //FUNCAO QUE VERIFICA SE ESTA VENCIDO
    public boolean estaVencido() {
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(dataValidade);
    }

    // RETORNA DADOS
    public String infoProduto(){
        return  "\nINFO PRODUTO\n\n>Codigo: " + this.codigo + "\n" +
                ">Nome: " + this.nomeProduto + "\n" +
                ">Descricao: " + this.descricao + "\n" +
                ">Preco: " + this.preco + "\n" +
                ">Produto perecivel?: " + this.isPerecivel + "\n" +
                ">Validade: " + this.dataValidade;
    }
}
