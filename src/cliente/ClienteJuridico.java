package cliente;

import java.time.LocalDate;

// CLASSE CLIENTE JURIDICO FILHO DE CLIENTE
public class ClienteJuridico extends Cliente {
    // VARIAVEL NECESSARIA PARA O SERIALIZABLE
    private static final long serialVersionUID = 1L;
    
    private String cnpj;
    private String razaoSocial;
    private int    prazoDiasParaPagar;

    //CONSTRUTOR
    public ClienteJuridico(String nome, String razaoSocial, String cnpj , LocalDate dataCadastro , String rua, int numero, String bairro, String cep, String cidade, String estado, int prazoDiasParaPagar) {
        super(nome, dataCadastro, rua, numero, bairro, cep, cidade, estado);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoDiasParaPagar = prazoDiasParaPagar;
    }

    //GET E SET: CNPJ
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    //GET E SET: RAZAO_SOCIAL
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    //GET E SET: PRAZO_DIAS_PARA_PAGAR
    public int getPrazoDiasParaPagar() {
        return prazoDiasParaPagar;
    }
    public void setPrazoDiasParaPagar(int prazoDiasParaPagar) {
        this.prazoDiasParaPagar = prazoDiasParaPagar;
    }

    //FUNCAO QUE RETORNA OS DADOS
    @Override
    public String paraString() {
        return  "DADOS CLIENTES \nNome Fantasia: " + getNome() + "\n" +
                "Razão Social: " + this.razaoSocial + "\n" +
                "CNPJ: " + this.cnpj + "\n" +
                "Endereço: " + getEndereco().paraString() +
                "Data de Cadastro: " + getDataCadastro() + "\n" +
                "Prazo de dias para pagamento: " + this.prazoDiasParaPagar;
    }

}
