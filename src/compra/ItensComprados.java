package compra;

import java.io.Serializable;

import produto.Produto;

// CLASSE ITENS COMPRA
public class ItensComprados implements Serializable{
    // VARIAVEL NECESSARIA PARA O SERIALIZABLE
    private static final long serialVersionUID = 1L;
    
    private int quantidadeItens;
    private Produto produto;

    // CONSTRUTOR
    public ItensComprados(int quantidadeItens, Produto produto) {
        this.quantidadeItens = quantidadeItens;
        this.produto = produto;
    }
    
    //GET: QUANTIDADE_DE_ITENS
    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    //GET: VALOR TOTAL DOS ITENS
    public double getValorTotal() {
        return produto.getPreco() * quantidadeItens;
    }

}
