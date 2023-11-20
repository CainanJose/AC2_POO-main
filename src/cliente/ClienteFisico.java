package cliente;

import java.time.LocalDate;

// CLASSE CLIENTE FISICO FILHO DE CLIENTE
public class ClienteFisico extends Cliente{
    // VARIAVEL NECESSARIA PARA O SERIALIZABLE
    private static final long serialVersionUID = 1L;
    
    private String cpf;
    private int parcelasMaximas;

    //CONSTRUTOR
    public ClienteFisico(String nome,String cpf , LocalDate dataCadastro , String rua, int numero, String bairro, String cep, String cidade, String estado, int parcelasMaximas) {
        super(nome, dataCadastro, rua, numero, bairro, cep, cidade, estado);
        this.cpf = cpf;
        this.parcelasMaximas = parcelasMaximas;

    }

    //GET E SET: CPF
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //GET E SET: PARCELAS_MAXIMAS
    public int getParcelasMaximas() {
        return parcelasMaximas;
    }

    public void setParcelasMaximas(int parcelasMaximas) {
        this.parcelasMaximas = parcelasMaximas;
    }

    //FUNCAO QUE RETORNA OS DADOS
    @Override
    public String paraString() {
        return  "DADOS CLIENTES \nNome: " + getNome() + "\n" +
                "CPF: " + this.cpf + "\n" +
                "Endereço: \n" + getEndereco().paraString() +
                "Data de Cadastro: " + getDataCadastro() + "\n" +
                "Quant. maxima de parcelas: " + this.parcelasMaximas;
    }

}
