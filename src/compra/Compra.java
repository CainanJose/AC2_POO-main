package compra;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

// CLASSE COMPRA
public class Compra implements Serializable{
    // VARIAVEL NECESSARIA PARA O SERIALIZABLE
    private static final long serialVersionUID = 1L;
    
    private int codigoCompra;
    private LocalDate dataCompra;
    private String indentificadorCliente;
    private Double valorPagoAteOMomento;
    private List<ItensComprados> itensComprados;
    private LocalDate dataUltimoPagamento;


    //CONSTRUTOR
    public Compra(int codigoCompra, LocalDate dataCompra, String indentificadorCliente, Double valorPagoAteOMomento, List<ItensComprados> itensComprados) {
        this.codigoCompra = codigoCompra;
        this.dataCompra = dataCompra;
        this.indentificadorCliente = indentificadorCliente;
        this.valorPagoAteOMomento = valorPagoAteOMomento;
        this.itensComprados = itensComprados;
        this.dataUltimoPagamento = null;
    }

    //GET: CODIGO_DA_COMPRA
    public int getCodigoCompra() {
        return codigoCompra;
    }

    //GET: DATA_DA_COMPRA
    public LocalDate getDataCompra() {
        return dataCompra;
    }

    //GET: VALOR_TOTAL_DA_COMPRA
    public Double getValorTotalCompra() {
        double valorCompra = 0;
        for (ItensComprados item: itensComprados) {
            valorCompra += item.getValorTotal();
        }
        return valorCompra;
    }

    //GET: INDENTIFICADOR DO CLIENTE
    public String getindentificadorCliente() {
        return indentificadorCliente;
    }

    //GET: VALOR PAGO ATE O MOMENTO
    public Double getValorPago() {
        return valorPagoAteOMomento;
    }

    //SET PAGAMENTO DE PARCELA
    public void setPagamento(Double valorPagamento) {
        this.valorPagoAteOMomento += valorPagamento;
    }

    //GET: LISTA_DOS_ITENS_COMPRADOS
    public List<ItensComprados> getItensComprados() {
        return itensComprados;
    }

    //GET: DATA QUITAÇÃO DA COMPRA
    public LocalDate getDataUltimoPagamento() {
        return this.dataUltimoPagamento;
    }
    //SET: DATA QUITAÇÃO DA COMPRA
    public void setDataUltimoPagamento(LocalDate value) {
        this.dataUltimoPagamento = value;
    }

    //FUNCAO: SALDO_DEVEDOR
    public Double getSaldoDevedor () {
        return getValorTotalCompra() - this.valorPagoAteOMomento;
    }

}
