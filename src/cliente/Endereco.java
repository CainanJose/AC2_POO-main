package cliente;

import java.io.Serializable;

// CLASSE ENDEREÇO 
public class Endereco implements Serializable{
    // VARIAVEL NECESSARIA PARA O SERIALIZABLE
    private static final long serialVersionUID = 1L;
    
    private String rua;
    private int    numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    //CONSTRUTOR
    public Endereco(String rua, int numero, String bairro, String cep, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    //GET E SET: RUA
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    //GET E SET: NUMERO
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    //GET E SET: BAIRRO
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    //GET E SET: CEP
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    //GET E SET: CIDADE
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    //GET E SET: ESTADO
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    //FUNCAO QUE RETORNA O ENDERECO COMPLETO
    public String paraString() {
        return  "-Rua: " + this.rua + "\n" +
                "-Numero: " + this.numero + "\n" +
                "-Bairro: " + this.bairro + "\n" +
                "-CEP : " + this.cep + "\n" +
                "-Cidade: " + this.cidade + "\n" +
                "-Estado: " + this.estado + "\n";

    }
}
