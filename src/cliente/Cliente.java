package cliente;

import java.io.Serializable;
import java.time.LocalDate;

// CLASSE CLIENTE
public class Cliente implements Serializable{
    // VARIAVEL NECESSARIA PARA O SERIALIZABLE
    private static final long serialVersionUID = 1L;
    

    private String nome;
    private LocalDate dataCadastro;
    private Endereco endereco;

    public Cliente(String nome, LocalDate dataCadastro, String rua, int numero, String bairro, String cep, String cidade, String estado ) {
        
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);
    }

    //GET E SET: NOME
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //GET E SET: ENDERECO
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    //GET E SET: DATA_CADASTRO
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = LocalDate.parse(dataCadastro);
    }


    //FUNCAO DE DADOS
    public String paraString(){
        return "Não há dados";
    };

}
