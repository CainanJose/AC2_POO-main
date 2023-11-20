package telas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

// REDUTOR DE TELAS EM JOPTIONPANE
public class DesenharTelas {

    //LEITURA DE DADOS
    public String lerDados(String mensagem){
        String dados = JOptionPane.showInputDialog(mensagem);
        if(dados == null){
            return "-404";
        }
        
        return dados.toLowerCase(); // TRANSFORMA EM MINUSCULO 
    }
    public int lerDadosInt(String mensagem){
        String dados = JOptionPane.showInputDialog(mensagem);
        if(dados.isEmpty()){
            return -404;
        }
        return Integer.parseInt(dados);
    }
    public double lerDadosDouble(String mensagem){
        String dados = JOptionPane.showInputDialog(mensagem);
        if(dados.contains(",")){
            dados = dados.replace(",", ".");
        }
        if(dados.isEmpty()){
            return -404.00;
        }
        return Double.parseDouble(dados);
    }
    public LocalDate lerDadosData(String mensagem){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = lerDados(mensagem);
        if(data.contains("-") || data.contains(".")){
            data = data.replace("-", "/");
            data = data.replace(".", "/");
        }
        if(data.isEmpty()){
            return null;
        }

        return LocalDate.parse(data, formatter);
    }

    //EXIBIR MENSAGENS
    public void exirbirMensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }

    //TELA DE CONFIRMACAO
    public boolean confirmarTela(String mensagem){
        int checaConfimacao = JOptionPane.showConfirmDialog(null,mensagem,null,JOptionPane.YES_NO_OPTION);
        return (checaConfimacao == 0)? true:false;
    }
    public boolean confirmarTela(String mensagem, String titulo){
        int checaConfimacao = JOptionPane.showConfirmDialog(null,mensagem,titulo,JOptionPane.YES_NO_OPTION);
        return (checaConfimacao == 0)? true:false;
    }
    
    //TELA COM OPCOES
    public int opcoesTela(String[] listaOpcoes, String mensagem){
        return JOptionPane.showOptionDialog(null,
        mensagem,
        null,
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        listaOpcoes,
        listaOpcoes[0]);
    }
    public int opcoesTela(String[] listaOpcoes, String mensagem, String titulo){
        return JOptionPane.showOptionDialog(null,
        mensagem,
        titulo,
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        listaOpcoes,
        listaOpcoes[0]);
    }

    //TELA COM DROPDOWN
    public String dropDownTela(String[] listaString, String mensagem){
        return (String) JOptionPane.showInputDialog(
                    null,
                    mensagem,
                    null,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    listaString,
                    listaString[0]
            );
    }
    public String dropDownTela(String[] listaString, String mensagem, String titulo){
        return (String) JOptionPane.showInputDialog(
                    null,
                    mensagem,
                    titulo,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    listaString,
                    listaString[0]
            );
    }

}
