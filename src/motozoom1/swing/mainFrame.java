/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import motozoom1.MotoZoom1;
import motozoom1.Utils.Utils;
import motozoom1.dados.Cliente;
import motozoom1.dados.Lancamento;
import motozoom1.dados.OrdemDeServico;
import motozoom1.dados.Produto;
import motozoom1.dados.ProdutoValor;
import motozoom1.dados.SQLQuery;
import motozoom1.dados.Servico;
import motozoom1.dados.ServicoValor;
import motozoom1.dados.Venda;
import static motozoom1.swing.Test.setupAutoComplete;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author ErNaNi
 */
public class mainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
    private ArrayList<ProdutoValor> produtosOS;
    private ArrayList<ServicoValor> servicosOS;
    private Cliente ClienteOS;
    private Produto produtoOS;
    private Servico servicoOS;
    private OrdemDeServico ordemDS;
    private final int bTI = 1;
    private final int bC = 2;
    private final int bES = 3;
    private final int bNV = 4;
    private final int bOS = 5;
    private final int bP = 6;
    private final int bS = 7;
    private final int bV = 8;
    private final int bF = 9;
    private int bClicado = bTI;
    
    
    
    public mainFrame()  {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUndecorated(true);
        initComponents();
        
        produtosOS = new ArrayList<ProdutoValor>();
        servicosOS = new ArrayList<ServicoValor>();
        ordemDS = null;
                
        lbUser.setText("User: " + MotoZoom1.usuario.getNome());
        

        ncTelefone.setFocusLostBehavior(JFormattedTextField.COMMIT);
        
        
        autoCompleteNV();
        autoCompleteEV();
        autoCompleteNOS();
        autoCompleteEOS();
    }
    
    public void autoCompleteNV()
    {
        ArrayList<String> clients = new ArrayList<String>();
        for(Cliente cl : SQLQuery.getClientes(0))
        {
            clients.add(cl.getNome() + " : " + cl.getTelefone());
        }
        new Autocomplete().setupAutoComplete(nvCliente, clients, Autocomplete.CLIENTES, this);
                
        ArrayList<String> prods = new ArrayList<String>();
        for(Produto pr : SQLQuery.getProdutos(0))
        {
            prods.add(pr.getDescricao());
        }
        new Autocomplete().setupAutoComplete(nvProduto, prods, Autocomplete.PRODUTOS, this);
                
        ArrayList<String> servs = new ArrayList<String>();
        for(Servico sv : SQLQuery.getServicos(0))
        {
            servs.add(sv.getDescricao());
        }
        new Autocomplete().setupAutoComplete(nvServico, servs, Autocomplete.SERVICOS, this);
    }
    
    public void autoCompleteEV()
    {
        ArrayList<String> clients = new ArrayList<String>();
        for(Cliente cl : SQLQuery.getClientes(0))
        {
            clients.add(cl.getNome() + " : " + cl.getTelefone());
        }
        new Autocomplete().setupAutoComplete(evCliente, clients, Autocomplete.CLIENTES, this);
                
        ArrayList<String> prods = new ArrayList<String>();
        for(Produto pr : SQLQuery.getProdutos(0))
        {
            prods.add(pr.getDescricao());
        }
        new Autocomplete().setupAutoComplete(evProduto, prods, Autocomplete.PRODUTOS, this);
                
        ArrayList<String> servs = new ArrayList<String>();
        for(Servico sv : SQLQuery.getServicos(0))
        {
            servs.add(sv.getDescricao());
        }
        new Autocomplete().setupAutoComplete(evServico, servs, Autocomplete.SERVICOS, this);
        
        Utils.setTableOS_PS(evTableProdutoServico, produtosOS, servicosOS);
    }
    
    public void autoCompleteNOS()
    {
        ArrayList<String> clients = new ArrayList<String>();
        for(Cliente cl : SQLQuery.getClientes(0))
        {
            clients.add(cl.getNome() + " : " + cl.getTelefone());
        }
        new Autocomplete().setupAutoComplete(nosCliente, clients, Autocomplete.CLIENTES, this);
                
        ArrayList<String> prods = new ArrayList<String>();
        for(Produto pr : SQLQuery.getProdutos(0))
        {
            prods.add(pr.getDescricao());
        }
        new Autocomplete().setupAutoComplete(nosProduto, prods, Autocomplete.PRODUTOS, this);
                
        ArrayList<String> servs = new ArrayList<String>();
        for(Servico sv : SQLQuery.getServicos(0))
        {
            servs.add(sv.getDescricao());
        }
        new Autocomplete().setupAutoComplete(nosServico, servs, Autocomplete.SERVICOS, this);
    }
    
    public void autoCompleteEOS()
    {
        ArrayList<String> clients = new ArrayList<String>();
        for(Cliente cl : SQLQuery.getClientes(0))
        {
            clients.add(cl.getNome() + " : " + cl.getTelefone());
        }
        new Autocomplete().setupAutoComplete(eosCliente, clients, Autocomplete.CLIENTES, this);
                
        ArrayList<String> prods = new ArrayList<String>();
        for(Produto pr : SQLQuery.getProdutos(0))
        {
            prods.add(pr.getDescricao());
        }
        new Autocomplete().setupAutoComplete(eosProduto, prods, Autocomplete.PRODUTOS, this);
                
        ArrayList<String> servs = new ArrayList<String>();
        for(Servico sv : SQLQuery.getServicos(0))
        {
            servs.add(sv.getDescricao());
        }
        new Autocomplete().setupAutoComplete(eosServico, servs, Autocomplete.SERVICOS, this);
    }
    
    @Override
    protected void processWindowEvent(WindowEvent e)
    {
        if (e.getID() == WindowEvent.WINDOW_DEACTIVATED)
        {
            return;
        }        

        super.processWindowEvent(e);        
    }
    
    public void removeProduto(int idProduto)
    {
        SQLQuery.deleteProduto(idProduto);
        
        Utils.setTable(tableProdutos, Utils.TABLE_PRODUTOS, 0);
        selecionarCard(painelFuncional, "cardProdutos");
    }
    
    public void editaProduto()
    {
        if((npNomeDesc.getText().length() < 1) || 
                (npPrecoCompra.getText().length() < 1) ||
                (npPrecoVenda.getText().length() < 1) ||
                (npEstoque.getText().length() < 1))
        {
            return;
        }

        SQLQuery.updateProduto(new Produto(
                Integer.parseInt(epIdProduto.getText()),
                epNomeDesc.getText(), 
                Float.parseFloat(Utils.formataValorEUA(epPrecoCompra.getText())), 
                Float.parseFloat(Utils.formataValorEUA(epPrecoVenda.getText())), 
                Integer.parseInt(epEstoque.getText()))
        );
        
        Utils.setTable(tableProdutos, Utils.TABLE_PRODUTOS, 0);
        selecionarCard(painelFuncional, "cardProdutos");
    }
    
    public void showEditaProduto(int idProduto)
    {
        Produto pro = SQLQuery.getProduto(idProduto);
        
        epIdProduto.setText(pro.getId() + "");
        epNomeDesc.setText(pro.getDescricao());
        epPrecoCompra.setText(Utils.formataValorBRL(pro.getPrecoCompra() + ""));
        epPrecoVenda.setText(Utils.formataValorBRL(pro.getPrecoVenda() + ""));
        epEstoque.setText(pro.getEstoque()+ "");
                
        selecionarCard(painelFuncional, "cardEditarProduto");
    }
    
    public void removeServico(int idServico)
    {
        SQLQuery.deleteServico(idServico);
        
        selecionarCard(painelFuncional, "cardServicos");
        Utils.setTable(tableServicos, Utils.TABLE_SERVICOS, ((int) spinServicos.getValue()) - 1);
    }
    
    public void editaServico()
    {
        if((esNomeDesc.getText().length() < 1) || 
            (esPreco.getText().length() < 1))
        {
            return;
        }

        SQLQuery.updateServico(new Servico(
                Integer.parseInt(esIdServico.getText()),
                esNomeDesc.getText(), 
                Float.parseFloat(Utils.formataValorEUA(esPreco.getText())))
        );
        
        selecionarCard(painelFuncional, "cardServicos");
        Utils.setTable(tableServicos, Utils.TABLE_SERVICOS, ((int) spinServicos.getValue()) - 1);
    }
    
    public void showEditaServico(int idServico)
    {
        Servico serv = SQLQuery.getServico(idServico);
        
        esIdServico.setText(serv.getId() + "");
        esNomeDesc.setText(serv.getDescricao());
        esPreco.setText(Utils.formataValorBRL(serv.getPreco() + ""));
                
        selecionarCard(painelFuncional, "cardEditarServico");
    }
    
    public void removeCliente(int idCliente)
    {
        SQLQuery.deleteCliente(idCliente);
        
        selecionarCard(painelFuncional, "cardClientes");
        Utils.setTable(tableClientes, Utils.TABLE_CLIENTES, ((int) spinClientes.getValue()) - 1);
    }
    
    public void editaCliente()
    {
        
        if((ecNome.getText().length() < 1) ||
            (ecTelefone.getText().length() < 1))
        {
            return;
        }
        
        String sexo = "";
        if(ecSexo.getItemAt(ecSexo.getSelectedIndex()).equalsIgnoreCase("Masculino"))
        {
            sexo = "M";
        } else if(ecSexo.getItemAt(ecSexo.getSelectedIndex()).equalsIgnoreCase("Feminino")) {
            sexo = "F";
        }
                
        String estado = "";
        if(ecSexo.getSelectedIndex() > 0)
        {
            estado = ecEstado.getItemAt(ecEstado.getSelectedIndex());
        }
        
        Cliente cl = new Cliente(
        Integer.parseInt(ecIdCliente.getText()),
        ecData.getText(),
        ecNome.getText(),
        ecCPF.getText().matches(".*\\d.*") ? ecCPF.getText() : "",
        ecTelefone.getText().matches(".*\\d.*") ? ecTelefone.getText() : "",
        ecEmail.getText(),
        sexo,
        ecCEP.getText(),
        estado,
        ecCidade.getText(),
        ecBairro.getText(),
        ecRua.getText(),
        ecNumero.getText());
        
        SQLQuery.updateCliente(cl);
        
        selecionarCard(painelFuncional, "cardClientes");
        Utils.setTable(tableClientes, Utils.TABLE_CLIENTES, ((int) spinClientes.getValue()) - 1);
    }
    
    public void showEditaCliente(int idCliente)
    {
        Cliente client = SQLQuery.getCliente(idCliente);
        if(client == null)
        {
            return;
        }
        
        ecSexo.setSelectedIndex(0);
        if(client.getSexo().equalsIgnoreCase("M"))
        {
            ecSexo.setSelectedIndex(1);
        }
        else if(client.getSexo().equalsIgnoreCase("F"))
        {
            ecSexo.setSelectedIndex(2);
        }
        
        ecIdCliente.setText(client.getId() + "");
        ecData.setText(client.getDataCadastro());
        ecNome.setText(client.getNome());
        
        
        ecCPF.setText(client.getCpf());
        ecTelefone.setText(client.getTelefone());
        ecEmail.setText(client.getEmail());
        
        ecCEP.setText(client.getCep());
        ecEstado.setSelectedItem(client.getEstado());
        ecCidade.setText(client.getCidade());
        ecBairro.setText(client.getBairro());
        ecRua.setText(client.getRua());
        ecNumero.setText(client.getNumero());
        
        selecionarCard(painelFuncional, "cardEditarCliente");
    }
    
    public void removeVenda(int idVenda)
    {
        SQLQuery.deleteVenda(idVenda);
        
        Utils.setTable(tableVendas, Utils.TABLE_VENDAS, ((int) spinVendas.getValue()) - 1);
        selecionarCard(painelFuncional, "cardVendas");
    }
    
    public void editaVenda()
    {
        if(ClienteOS.getId() < 1)
        {
            return;
        }
        
        Venda venda = new Venda();
        venda.setId(Integer.parseInt(evId.getText()));
        venda.setData(evData.getText());
        venda.setCliente(ClienteOS);
        venda.setFormaPagamento(evFormaPagamento.getItemAt(evFormaPagamento.getSelectedIndex()));
        venda.setDesconto(Double.parseDouble(Utils.formataValorEUA(evDescontos.getText())));
        venda.setValorTotal(Double.parseDouble(Utils.formataValorEUA(evTotal.getText())));
        venda.setValorRecebido(Double.parseDouble(Utils.formataValorEUA(evValorRecebido.getText().length() < 1 ? "0" : evValorRecebido.getText())));
        venda.setTroco(Double.parseDouble(Utils.formataValorEUA(evTroco.getText())));
        venda.setObservacoes(evObservacoes.getText());
        venda.setProdutoValor(produtosOS);
        venda.setServicoValor(servicosOS);
        
        SQLQuery.updateVenda(venda);
        
        selecionarCard(painelFuncional, "cardVendas");
        Utils.setTable(tableVendas, Utils.TABLE_VENDAS, ((int) spinVendas.getValue()) - 1);
    }
    
    public void showEditaVenda(int idVenda)
    {
        selecionarCard(painelFuncional, "cardEditarVenda");
        produtoOS = null;
        servicoOS = null;
        Venda venda = SQLQuery.getVenda(idVenda);
        if(venda == null)
        {
            return;
        }
        
        ClienteOS = venda.getCliente();
        
        evUsuario.setText(venda.getUsuario().getNome());
        
        if(venda.getFormaPagamento().equalsIgnoreCase("Dinheiro"))
        {
            evFormaPagamento.setSelectedIndex(0);
        }
        else if(venda.getFormaPagamento().equalsIgnoreCase("Debito"))
        {
            evFormaPagamento.setSelectedIndex(1);
        }
        else if(venda.getFormaPagamento().equalsIgnoreCase("Crédito"))
        {
            evFormaPagamento.setSelectedIndex(2);
        }
        else if(venda.getFormaPagamento().equalsIgnoreCase("Pix"))
        {
            evFormaPagamento.setSelectedIndex(3);
        }
        else if(venda.getFormaPagamento().equalsIgnoreCase("Transferência"))
        {
            evFormaPagamento.setSelectedIndex(4);
        }
        
        evId.setText(venda.getId() + "");
        evData.setText(venda.getData().length() > 1 ? venda.getData() : new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        evCliente.setText(venda.getCliente().getNome());
        evTelefone.setText(venda.getCliente().getTelefone());
        
        evValorRecebido.setText(String.format("%,.2f", venda.getValorRecebido()));
        evTroco.setText(String.format("%,.2f", venda.getTroco()));
        evSubTotal.setText(String.format("%,.2f", (venda.getValorTotal() - venda.getDesconto())));
        evDescontos.setText(String.format("%,.2f", venda.getDesconto()));
        evTotal.setText(String.format("%,.2f", venda.getValorTotal()));
        evObservacoes.setText(venda.getObservacoes());
        
        evProduto.setText("");
        evQuantProduto.setText("");
        evValorUNProduto.setText("");
        evDescontoProduto.setText("");
        evValorTotalProduto.setText("");
        evServico.setText("");
        evQuantServico.setText("");
        evValorUNServico.setText("");
        evDescontoServico.setText("");
        evValorTotalServico.setText("");
        
        if(produtosOS != null)
        {
            produtosOS.clear();
        }        
        if(servicosOS != null)
        {
            servicosOS.clear();
        }
        
        produtosOS = venda.getProdutoValor() != null ? venda.getProdutoValor() : new ArrayList<>();
        servicosOS = venda.getServicoValor() != null ? venda.getServicoValor() : new ArrayList<>();
        
        Utils.setTableOS_PS(evTableProdutoServico, produtosOS, servicosOS);
    }
    
    public void removeFinanca(int idFinanca)
    {
        SQLQuery.deleteFinanca(idFinanca);
        
        List<Lancamento> lancamentos = Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        selecionarCard(painelFuncional, "cardFinancas");
        if(lancamentos == null) {
            fReceita.setText("0,00");
            fDespesa.setText("0,00");
            fTotal.setText("0,00");
            lancAnualUpdate();
            return;
        }
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento lanc : lancamentos) {
            if(lanc.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += lanc.getValor();
            } else {
                despesa += lanc.getValor();
            }
        }
        
        fReceita.setText(String.format("%,.2f", receita));
        fDespesa.setText(String.format("%,.2f", despesa));
        fTotal.setText(String.format("%,.2f", (receita - despesa)));
        
        lancAnualUpdate();
    }
    
    public void editaFinanca()
    {
        if((elData.getText().length() < 1) || 
                (elDesc.getText().length() < 1) ||
                (elValor.getText().length() < 1))
        {
            return;
        }
        
        Lancamento lanc = new Lancamento();
        lanc.setId(Integer.parseInt(elId.getText()));
        lanc.setData(elData.getText());
        lanc.setDescricao(elDesc.getText());
        lanc.setTipo(elTipo.getItemAt(elTipo.getSelectedIndex()));
        lanc.setFormaPagamento(elFormaPagamento.getItemAt(elFormaPagamento.getSelectedIndex()));
        lanc.setValor(Double.parseDouble(Utils.formataValorEUA(elValor.getText())));
        
        SQLQuery.updateFinanca(lanc);
        List<Lancamento> finas = Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        selecionarCard(painelFuncional, "cardFinancas");
        if(finas == null) {
            fReceita.setText("0,00");
            fDespesa.setText("0,00");
            fTotal.setText("0,00");
            lancAnualUpdate();
            return;
        }
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento fin : finas) {
            if(fin.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += fin.getValor();
            } else {
                despesa += fin.getValor();
            }
        }
        
        fReceita.setText(String.format("%,.2f", receita));
        fDespesa.setText(String.format("%,.2f", despesa));
        fTotal.setText(String.format("%,.2f", (receita - despesa)));
        
        lancAnualUpdate();
    }
    
    public void showEditaFinanca(int idFinanca)
    {
        Lancamento lanc = SQLQuery.getFinanca(idFinanca);
        if(lanc == null)
        {
            return;
        }
        
        if(lanc.getTipo().equalsIgnoreCase("Receita"))
        {
            elTipo.setSelectedIndex(1);
        }
        else
        {
            elTipo.setSelectedIndex(0);
        }
        
        if(lanc.getFormaPagamento().equalsIgnoreCase("Dinheiro"))
        {
            evFormaPagamento.setSelectedIndex(0);
        }
        else if(lanc.getFormaPagamento().equalsIgnoreCase("Debito"))
        {
            evFormaPagamento.setSelectedIndex(1);
        }
        else if(lanc.getFormaPagamento().equalsIgnoreCase("Crédito"))
        {
            evFormaPagamento.setSelectedIndex(2);
        }
        else if(lanc.getFormaPagamento().equalsIgnoreCase("Pix"))
        {
            evFormaPagamento.setSelectedIndex(3);
        }
        else if(lanc.getFormaPagamento().equalsIgnoreCase("Transferência"))
        {
            evFormaPagamento.setSelectedIndex(4);
        }
        
        elId.setText(lanc.getId() + "");
        elData.setText(lanc.getData().length() > 1 ? lanc.getData() : new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        elDesc.setText(lanc.getDescricao());
        elValor.setText(String.format("%,.2f", lanc.getValor()));
        
        selecionarCard(painelFuncional, "cardEditarLancamento");
    }
    
    public void removeOrdemDeServico(int idOrdemDeServico)
    {
        SQLQuery.deleteOrdemDeServico(idOrdemDeServico);
        
        Utils.setTable(tableOS, Utils.TABLE_OS, ((int) spinOS.getValue()) - 1);
        selecionarCard(painelFuncional, "cardOrdemdeServico");
    }
    
    public OrdemDeServico editaOrdemDeServico()
    {
        if((ClienteOS.getId() < 1) || (eosDescVeiculo.getText().length() < 1))
        {
            return null;
        }
        
        OrdemDeServico os = new OrdemDeServico();
        os.setId(Integer.parseInt(eosIdOrdem.getText()));
        os.setData(eosData.getText());
        os.setValorTotal(Double.parseDouble(Utils.formataValorEUA(eosValorTotal.getText())));
        os.setStatus(eosStatus.getItemAt(eosStatus.getSelectedIndex()));
        os.setDescricaoVeiculo(eosDescVeiculo.getText());
        os.setDescricaoDefeito(eosDescDefeito.getText());
        os.setPecasReposicao(eosPecaReposicao.getText());
        os.setObservacoes(eosObservacoes.getText());
        os.setCliente(ClienteOS);
        os.getVenda().setId(Integer.parseInt(eosIdVenda.getText()));
        os.setProdutoValor(produtosOS);
        os.setServicoValor(servicosOS);
        
        SQLQuery.updateOrdemDeServico(os);
        
        Utils.setTable(tableOS, Utils.TABLE_OS, ((int) spinOS.getValue()) - 1);
        selecionarCard(painelFuncional, "cardOrdemdeServico");
        
        return os;
    }

    public void showEditaOrdemDeServico(int idOrdemDeServico)
    {
        
        selecionarCard(painelFuncional, "cardEditarOrdemdeServico");
        produtoOS = null;
        servicoOS = null;
        OrdemDeServico os = SQLQuery.getOrdemDeServico(idOrdemDeServico);
        if(os == null)
        {
            return;
        }
        
        ClienteOS = os.getCliente();
        
        if(os.getStatus().equalsIgnoreCase("Aberta"))
        {
            eosStatus.setSelectedIndex(0);
        }
        else if(os.getStatus().equalsIgnoreCase("Aguardando Peças"))
        {
            eosStatus.setSelectedIndex(1);
        }
        else if(os.getStatus().equalsIgnoreCase("Finalizada"))
        {
            eosStatus.setSelectedIndex(2);
        }
        else if(os.getStatus().equalsIgnoreCase("Paga"))
        {
            eosStatus.setSelectedIndex(3);
        }
        
        if(os.getVenda().getId() < 1)
        {
            eosPagar.setEnabled(true);
        } else {
            eosPagar.setEnabled(false);
        }
        
        eosCliente.setText(os.getCliente().getNome());
        eosClienteFone.setText(os.getCliente().getTelefone());
        eosData.setText(os.getData().length() > 1 ? os.getData() : new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        
        eosDescDefeito.setText(os.getDescricaoDefeito());
        eosDescVeiculo.setText(os.getDescricaoVeiculo());
        eosObservacoes.setText(os.getObservacoes());
        eosPecaReposicao.setText(os.getPecasReposicao());
        eosValorTotal.setText(String.format("%,.2f", os.getValorTotal()));
        eosIdOrdem.setText(os.getId() + "");
        eosIdVenda.setText(os.getVenda().getId() + "");
        
        eosProduto.setText("");
        eosQuantProduto.setText("");
        eosValorUNProduto.setText("");
        eosDescontoProduto.setText("");
        eosValorTotalProduto.setText("");
        eosServico.setText("");
        eosQuantServico.setText("");
        eosValorUNServico.setText("");
        eosDescontoServico.setText("");
        eosValorTotalServico.setText("");
        
        if(produtosOS != null)
        {
            produtosOS.clear();
        }        
        if(servicosOS != null)
        {
            servicosOS.clear();
        }
        
        produtosOS = os.getProdutoValor() != null ? os.getProdutoValor() : new ArrayList<>();
        servicosOS = os.getServicoValor() != null ? os.getServicoValor() : new ArrayList<>();
        
        Utils.setTableOS_PS(eosTableProdutoServico, produtosOS, servicosOS);
    }
    
    public void calculaValorOS()
    {
        double val = 0;
        double descontos = 0;
        for(ProdutoValor pos : produtosOS)
        {
            val += pos.getValorTotal();
            descontos += pos.getDesconto() * ((double) pos.getQuantidade());
        }
        for(ServicoValor sos : servicosOS)
        {
            val += sos.getValorTotal();
            descontos += sos.getDesconto() * ((double) sos.getQuantidade());
        }
        nosValorTotal.setText(String.format("%,.2f", val));
        eosValorTotal.setText(String.format("%,.2f", val));
        
        nvTotal.setText(String.format("%,.2f", val));
        nvSubTotal.setText(String.format("%,.2f", (val + descontos)));
        nvDescontos.setText(String.format("%,.2f", descontos));
                
        evTotal.setText(String.format("%,.2f", val));
        evSubTotal.setText(String.format("%,.2f", (val + descontos)));
        evDescontos.setText(String.format("%,.2f", descontos));
    }
    
    public void removeOrdemDeServicoPS(int idItem, String name)
    {
        for(ProdutoValor pv : produtosOS)
        {
            if((pv.getProduto().getId() == idItem) && (pv.getProduto().getDescricao() == name))
            {
                produtosOS.remove(pv);
                Utils.setTableOS_PS(nosTableProdutoServico, produtosOS, servicosOS);
                Utils.setTableOS_PS(eosTableProdutoServico, produtosOS, servicosOS);
                Utils.setTableOS_PS(nvTableNovaVenda, produtosOS, servicosOS);
                Utils.setTableOS_PS(evTableProdutoServico, produtosOS, servicosOS);
                calculaValorOS();
                return;
            }
        }
        for(ServicoValor sv : servicosOS)
        {
            if((sv.getServico().getId() == idItem) && (sv.getServico().getDescricao() == name))
            {
                servicosOS.remove(sv);
                Utils.setTableOS_PS(nosTableProdutoServico, produtosOS, servicosOS);
                Utils.setTableOS_PS(eosTableProdutoServico, produtosOS, servicosOS);
                Utils.setTableOS_PS(nvTableNovaVenda, produtosOS, servicosOS);
                Utils.setTableOS_PS(evTableProdutoServico, produtosOS, servicosOS);
                calculaValorOS();
                return;
            }
        }
    }
    
    public void trocaCor(JComponent component, Color color)
    {
        switch(bClicado) {
            case bTI:
                if(component == (JComponent) bTelaInicial){return;}
                break;
            case bC:
                if(component == (JComponent) bConfiguracoes){return;}
                break;
            case bES:
                if(component == (JComponent) bEncerrarSessao){return;}
                break;
            case bNV:
                if(component == (JComponent) bNovaVenda){return;}
                break;
            case bOS:
                if(component == (JComponent) bOrdemdeServico){return;}
                break;
            case bP:
                if(component == (JComponent) bProdutos){return;}
                break;
            case bS:
                if(component == (JComponent) bServicos){return;}
                break;
            case bV:
                if(component == (JComponent) bVendas){return;}
                break;
            case bF:
                if(component == (JComponent) bFinancas){return;}
                break;
        }
        
        component.setBackground(color);
    }
    
    public void selecionarCard(JComponent component, String cardName)
    {
        CardLayout cl = (CardLayout) component.getLayout();
        cl.show(component, cardName);
    }
    
    public void botaoClicado(JLabel labe)
    {
        bTelaInicial.setBackground(new Color(0, 51, 51));
        bConfiguracoes.setBackground(new Color(0, 51, 51));
        bEncerrarSessao.setBackground(new Color(0, 51, 51));
        bNovaVenda.setBackground(new Color(0, 51, 51));
        bOrdemdeServico.setBackground(new Color(0, 51, 51));
        bProdutos.setBackground(new Color(0, 51, 51));
        bServicos.setBackground(new Color(0, 51, 51));
        bVendas.setBackground(new Color(0, 51, 51));
        bFinancas.setBackground(new Color(0, 51, 51));
        
        labe.setBackground(new Color(0, 66, 66));
    }
    
    public void adicionaProduto()
    {
        if((npNomeDesc.getText().length() < 1) || 
                (npPrecoCompra.getText().length() < 1) ||
                (npPrecoVenda.getText().length() < 1) ||
                (npEstoque.getText().length() < 1))
        {
            return;
        }

        SQLQuery.insertProduto(new Produto(
                0,
                npNomeDesc.getText(), 
                Float.parseFloat(Utils.formataValorEUA(npPrecoCompra.getText())), 
                Float.parseFloat(Utils.formataValorEUA(npPrecoVenda.getText())), 
                Integer.parseInt(npEstoque.getText()))
        );
        
        Utils.setTable(tableProdutos, Utils.TABLE_PRODUTOS, 0);
        selecionarCard(painelFuncional, "cardProdutos");
    }
    
    public void adicionaServico()
    {
        if((nsPreco.getText().length() < 1) || 
            (nsNomeDesc.getText().length() < 1))
        {
            return;
        }

        SQLQuery.insertServico(new Servico(
                0,
                nsNomeDesc.getText(),
                Float.parseFloat(Utils.formataValorEUA(nsPreco.getText()))
        ));
        
        selecionarCard(painelFuncional, "cardServicos");
        Utils.setTable(tableServicos, Utils.TABLE_SERVICOS, ((int) spinServicos.getValue()) - 1);
    }
    
    public void adicionaCliente()
    {
        
        if((ncNome.getText().length() < 1) ||
            (ncTelefone.getText().length() < 1))
        {
            return;
        }
        
        String sexo = "";
        if(ncSexo.getItemAt(ncSexo.getSelectedIndex()).equalsIgnoreCase("Masculino"))
        {
            sexo = "M";
        } else if(ncSexo.getItemAt(ncSexo.getSelectedIndex()).equalsIgnoreCase("Feminino")) {
            sexo = "F";
        }
                
        String estado = "";
        if(ncSexo.getSelectedIndex() > 0)
        {
            estado = ncEstado.getItemAt(ncEstado.getSelectedIndex());
        }
        
        Cliente cl = new Cliente(0,
        ncData.getText(),
        ncNome.getText(),
        ncCPF.getText().matches(".*\\d.*") ? ncCPF.getText() : "",
        ncTelefone.getText().matches(".*\\d.*") ? ncTelefone.getText() : "",
        ncEmail.getText(),
        sexo,
        ncCEP.getText(),
        estado,
        ncCidade.getText(),
        ncBairro.getText(),
        ncRua.getText(),
        ncNumero.getText());
        
        SQLQuery.insertCliente(cl);
        
        selecionarCard(painelFuncional, "cardClientes");
        Utils.setTable(tableClientes, Utils.TABLE_CLIENTES, ((int) spinClientes.getValue()) - 1);
    }
    
    public void adicionaOS()
    {
        if((ClienteOS.getId() < 1) || (nosDescVeiculo.getText().length() < 1))
        {
            return;
        }
        
        OrdemDeServico os = new OrdemDeServico();
        os.setData(nosData.getText());
        os.setValorTotal(Double.parseDouble(Utils.formataValorEUA(nosValorTotal.getText())));
        os.setStatus("Aberta");
        os.setDescricaoVeiculo(nosDescVeiculo.getText());
        os.setDescricaoDefeito(nosDescDefeito.getText());
        os.setPecasReposicao(nosPecaReposicao.getText());
        os.setObservacoes(nosObservacoes.getText());
        os.setUsuario(MotoZoom1.usuario);
        os.setCliente(ClienteOS);
        os.setProdutoValor(produtosOS);
        os.setServicoValor(servicosOS);
        
        SQLQuery.insertOrdemDeServico(os);
        
        Utils.setTable(tableOS, Utils.TABLE_OS, ((int) spinOS.getValue()) - 1);
        selecionarCard(painelFuncional, "cardOrdemdeServico");
    }
    
    public void adicionaVenda()
    {
        if((ClienteOS.getId() < 1) || (nvFormaPagamento.getSelectedIndex() < 1))
        {
            return;
        }
        
        Venda venda = new Venda();
        venda.setData(nvData.getText());
        venda.setUsuario(MotoZoom1.usuario);
        venda.setCliente(ClienteOS);
        venda.setFormaPagamento(nvFormaPagamento.getItemAt(nvFormaPagamento.getSelectedIndex()));
        venda.setDesconto(Double.parseDouble(Utils.formataValorEUA(nvDescontos.getText())));
        venda.setValorTotal(Double.parseDouble(Utils.formataValorEUA(nvTotal.getText())));
        venda.setValorRecebido(Double.parseDouble(Utils.formataValorEUA(nvValorRecebido.getText().length() < 1 ? "0" : nvValorRecebido.getText())));
        venda.setTroco(Double.parseDouble(Utils.formataValorEUA(nvTroco.getText())));
        venda.setObservacoes(nvObservacoes.getText());
        venda.setProdutoValor(produtosOS);
        venda.setServicoValor(servicosOS);
        
        int idVenda = SQLQuery.insertVenda(venda);
        
        if(ordemDS != null)
        {
            venda.setId(idVenda);
            ordemDS.setVenda(venda);
            SQLQuery.updateOrdemDeServico(ordemDS);
            ordemDS = null;
        }
        
        selecionarCard(painelFuncional, "cardVendas");
        botaoClicado(bVendas);
        Utils.setTable(tableVendas, Utils.TABLE_VENDAS, 0);
        spinVendas.setValue(1);
    }
    
    public void adicionaLancamento()
    {
        if((nlData.getText().length() < 1) || 
                (nlDesc.getText().length() < 1) ||
                (nlValor.getText().length() < 1)||
                (nlFormaPagamento.getSelectedIndex() < 1))
        {
            return;
        }
        
        Lancamento lanc = new Lancamento();
        lanc.setData(nlData.getText());
        lanc.setDescricao(nlDesc.getText());
        lanc.setTipo(nlTipo.getItemAt(nlTipo.getSelectedIndex()));
        lanc.setFormaPagamento(nlFormaPagamento.getItemAt(nlFormaPagamento.getSelectedIndex()));
        lanc.setValor(Double.parseDouble(Utils.formataValorEUA(nlValor.getText())));
        
        SQLQuery.insertFinanca(lanc);
        List<Lancamento> lancamentos = Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        selecionarCard(painelFuncional, "cardFinancas");
        if(lancamentos == null) {
            fReceita.setText("0,00");
            fDespesa.setText("0,00");
            fTotal.setText("0,00");
            lancAnualUpdate();
            return;
        }
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento lanca : lancamentos) {
            if(lanc.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += lanca.getValor();
            } else {
                despesa += lanca.getValor();
            }
        }
        
        fReceita.setText(String.format("%,.2f", receita));
        fDespesa.setText(String.format("%,.2f", despesa));
        fTotal.setText(String.format("%,.2f", (receita - despesa)));
        
        lancAnualUpdate();
    }
    
    private void lancAnualUpdate()
    {
        //Resultados anual
        List<Lancamento> lancamentosAnual = SQLQuery.getFinancasAnual(Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())));

        if(lancamentosAnual == null) {
            fReceitaAnual.setText("0,00");
            fDespesaAnual.setText("0,00");
            fTotalAnual.setText("0,00");
            return;
        }
        
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento lanc : lancamentosAnual) {
            if(lanc.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += lanc.getValor();
            } else {
                despesa += lanc.getValor();
            }
        }
        fReceitaAnual.setText(String.format("%,.2f", receita));
        fDespesaAnual.setText(String.format("%,.2f", despesa));
        fTotalAnual.setText(String.format("%,.2f", (receita - despesa)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        painelTitulo = new javax.swing.JPanel();
        bFexar = new javax.swing.JLabel();
        bMinimizar = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        painelPrograma = new javax.swing.JPanel();
        painelBotoes = new javax.swing.JPanel();
        bTelaInicial = new javax.swing.JLabel();
        bNovaVenda = new javax.swing.JLabel();
        bVendas = new javax.swing.JLabel();
        bProdutos = new javax.swing.JLabel();
        bServicos = new javax.swing.JLabel();
        bOrdemdeServico = new javax.swing.JLabel();
        bFinancas = new javax.swing.JLabel();
        bConfiguracoes = new javax.swing.JLabel();
        bEncerrarSessao = new javax.swing.JLabel();
        bClientes = new javax.swing.JLabel();
        painelFuncional = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        vendas = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        scrollVendas = new javax.swing.JScrollPane();
        tableVendas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        spinVendas = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        novaVenda = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        nvFinalizarVenda = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        nvFormaPagamento = new javax.swing.JComboBox<>();
        nvTelefone = new javax.swing.JFormattedTextField();
        nvData = new javax.swing.JFormattedTextField();
        nvUsuario = new javax.swing.JComboBox<>();
        nvCliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        nvSubTotal = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        nvDescontos = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        nvTotal = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        nvTroco = new javax.swing.JTextField();
        nvValorRecebido = new javax.swing.JFormattedTextField();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        nvObservacoes = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        nvTableNovaVenda = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        nvServico = new javax.swing.JTextField();
        nvAddServico = new javax.swing.JButton();
        nvQuantServico = new javax.swing.JFormattedTextField();
        nvValorTotalServico = new javax.swing.JFormattedTextField();
        jLabel31 = new javax.swing.JLabel();
        nvDescontoServico = new javax.swing.JFormattedTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        nvValorUNServico = new javax.swing.JFormattedTextField();
        jLabel148 = new javax.swing.JLabel();
        nvDescontoServicoTotal = new javax.swing.JFormattedTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        nvProduto = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        nvAddProduto = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        nvQuantProduto = new javax.swing.JFormattedTextField();
        nvValorTotalProduto = new javax.swing.JFormattedTextField();
        nvValorUNProduto = new javax.swing.JFormattedTextField();
        nvDescontoProduto = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        nvDescontoProdutoTotal = new javax.swing.JFormattedTextField();
        editarVenda = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        evId = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        evFormaPagamento = new javax.swing.JComboBox<>();
        evTelefone = new javax.swing.JFormattedTextField();
        evData = new javax.swing.JFormattedTextField();
        evCliente = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        evObservacoes = new javax.swing.JTextArea();
        jLabel69 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        evSubTotal = new javax.swing.JTextField();
        evDescontos = new javax.swing.JTextField();
        evTotal = new javax.swing.JTextField();
        evTroco = new javax.swing.JTextField();
        evValorRecebido = new javax.swing.JFormattedTextField();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        evUsuario = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        evTableProdutoServico = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        evServico = new javax.swing.JTextField();
        evAddServico = new javax.swing.JButton();
        evQuantServico = new javax.swing.JFormattedTextField();
        evValorTotalServico = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        evDescontoServico = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        evValorUNServico = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        evProduto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        evAddProduto = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        evQuantProduto = new javax.swing.JFormattedTextField();
        evValorTotalProduto = new javax.swing.JFormattedTextField();
        evValorUNProduto = new javax.swing.JFormattedTextField();
        evDescontoProduto = new javax.swing.JFormattedTextField();
        produtos = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        scrollVendas1 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        spinProdutos = new javax.swing.JSpinner();
        jLabel45 = new javax.swing.JLabel();
        bNovoProduto = new javax.swing.JButton();
        editarProduto = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        epIdProduto = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        epPrecoCompra = new javax.swing.JFormattedTextField();
        epPrecoVenda = new javax.swing.JFormattedTextField();
        epEstoque = new javax.swing.JFormattedTextField();
        epNomeDesc = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        novoProduto = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        bVoltarNovoProduto = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        npPrecoCompra = new javax.swing.JFormattedTextField();
        npPrecoVenda = new javax.swing.JFormattedTextField();
        npEstoque = new javax.swing.JFormattedTextField();
        npNomeDesc = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        servicos = new javax.swing.JPanel();
        header2 = new javax.swing.JPanel();
        jTextField16 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        scrollVendas2 = new javax.swing.JScrollPane();
        tableServicos = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        spinServicos = new javax.swing.JSpinner();
        jLabel54 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        editarServico = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        esIdServico = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        esPreco = new javax.swing.JFormattedTextField();
        esNomeDesc = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        novoServico = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        nsPreco = new javax.swing.JFormattedTextField();
        nsNomeDesc = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        ordemdeServico = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        spinOS = new javax.swing.JSpinner();
        jLabel61 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        scrollVendas3 = new javax.swing.JScrollPane();
        tableOS = new javax.swing.JTable();
        header3 = new javax.swing.JPanel();
        jTextField23 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        novaOrdemdeServico = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        nosCriar_e_Imprimir = new javax.swing.JButton();
        nosCriar = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        nosClienteFone = new javax.swing.JFormattedTextField();
        nosData = new javax.swing.JFormattedTextField();
        nosCliente = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        nosValorTotal = new javax.swing.JTextField();
        nosDescVeiculo = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        nosDescDefeito = new javax.swing.JTextArea();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        nosPecaReposicao = new javax.swing.JTextArea();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        nosObservacoes = new javax.swing.JTextArea();
        jLabel96 = new javax.swing.JLabel();
        nosVoltar = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        nosTableProdutoServico = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        nosServico = new javax.swing.JTextField();
        nosAddServico = new javax.swing.JButton();
        nosQuantServico = new javax.swing.JFormattedTextField();
        nosValorTotalServico = new javax.swing.JFormattedTextField();
        jLabel77 = new javax.swing.JLabel();
        nosDescontoServico = new javax.swing.JFormattedTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        nosValorUNServico = new javax.swing.JFormattedTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        nosProduto = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        nosAddProduto = new javax.swing.JButton();
        jLabel85 = new javax.swing.JLabel();
        nosQuantProduto = new javax.swing.JFormattedTextField();
        nosValorTotalProduto = new javax.swing.JFormattedTextField();
        nosValorUNProduto = new javax.swing.JFormattedTextField();
        nosDescontoProduto = new javax.swing.JFormattedTextField();
        editarOrdemdeServico = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        eosClienteFone = new javax.swing.JFormattedTextField();
        eosData = new javax.swing.JFormattedTextField();
        eosCliente = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        eosValorTotal = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        eosDescVeiculo = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        eosDescDefeito = new javax.swing.JTextArea();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        eosPecaReposicao = new javax.swing.JTextArea();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        eosObservacoes = new javax.swing.JTextArea();
        eosStatus = new javax.swing.JComboBox<>();
        jLabel128 = new javax.swing.JLabel();
        eosVoltar = new javax.swing.JButton();
        jLabel140 = new javax.swing.JLabel();
        eosIdOrdem = new javax.swing.JTextField();
        eosPagar = new javax.swing.JButton();
        jLabel147 = new javax.swing.JLabel();
        eosIdVenda = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        eosTableProdutoServico = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        eosServico = new javax.swing.JTextField();
        eosAddServico = new javax.swing.JButton();
        eosQuantServico = new javax.swing.JFormattedTextField();
        eosValorTotalServico = new javax.swing.JFormattedTextField();
        jLabel87 = new javax.swing.JLabel();
        eosDescontoServico = new javax.swing.JFormattedTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        eosValorUNServico = new javax.swing.JFormattedTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        eosProduto = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        eosAddProduto = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        eosQuantProduto = new javax.swing.JFormattedTextField();
        eosValorTotalProduto = new javax.swing.JFormattedTextField();
        eosValorUNProduto = new javax.swing.JFormattedTextField();
        eosDescontoProduto = new javax.swing.JFormattedTextField();
        financas = new javax.swing.JPanel();
        header4 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        scrollVendas4 = new javax.swing.JScrollPane();
        fTable = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        spinFinancas = new javax.swing.JSpinner();
        jLabel99 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        fAno = new javax.swing.JComboBox<>();
        jLabel100 = new javax.swing.JLabel();
        fMes = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jLabel150 = new javax.swing.JLabel();
        fTotalAnual = new javax.swing.JLabel();
        fDespesaAnual = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        fReceitaAnual = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        fTotal = new javax.swing.JLabel();
        fDespesa = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        fReceita = new javax.swing.JLabel();
        novoLancamento = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        nlData = new javax.swing.JFormattedTextField();
        jLabel102 = new javax.swing.JLabel();
        nlDesc = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        nlValor = new javax.swing.JFormattedTextField();
        jLabel104 = new javax.swing.JLabel();
        nlTipo = new javax.swing.JComboBox<>();
        nlAdd = new javax.swing.JButton();
        jLabel105 = new javax.swing.JLabel();
        nlVoltar = new javax.swing.JButton();
        nlFormaPagamento = new javax.swing.JComboBox<>();
        jLabel145 = new javax.swing.JLabel();
        editarLancamento = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        elData = new javax.swing.JFormattedTextField();
        jLabel107 = new javax.swing.JLabel();
        elDesc = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        elValor = new javax.swing.JFormattedTextField();
        jLabel109 = new javax.swing.JLabel();
        elTipo = new javax.swing.JComboBox<>();
        jButton31 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        elFormaPagamento = new javax.swing.JComboBox<>();
        nlVoltar1 = new javax.swing.JButton();
        elId = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        configuracoes = new javax.swing.JPanel();
        encerrarSessao = new javax.swing.JPanel();
        clientes = new javax.swing.JPanel();
        header5 = new javax.swing.JPanel();
        jTextField34 = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        scrollVendas5 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        spinClientes = new javax.swing.JSpinner();
        jLabel113 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        editarCliente = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        ecIdCliente = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        ecData = new javax.swing.JFormattedTextField();
        jTextField37 = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        ecNome = new javax.swing.JTextField();
        ecCPF = new javax.swing.JFormattedTextField();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        ecEmail = new javax.swing.JTextField();
        ecCEP = new javax.swing.JFormattedTextField();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        ecTelefone = new javax.swing.JFormattedTextField();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        ecBairro = new javax.swing.JTextField();
        ecCidade = new javax.swing.JTextField();
        ecSexo = new javax.swing.JComboBox<>();
        ecEstado = new javax.swing.JComboBox<>();
        ecRua = new javax.swing.JTextField();
        ecNumero = new javax.swing.JTextField();
        NovoCliente = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        ncData = new javax.swing.JFormattedTextField();
        jTextField44 = new javax.swing.JTextField();
        jLabel130 = new javax.swing.JLabel();
        ncNome = new javax.swing.JTextField();
        ncCPF = new javax.swing.JFormattedTextField();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        ncEmail = new javax.swing.JTextField();
        ncCEP = new javax.swing.JFormattedTextField();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        ncTelefone = new javax.swing.JFormattedTextField();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        ncBairro = new javax.swing.JTextField();
        ncCidade = new javax.swing.JTextField();
        ncSexo = new javax.swing.JComboBox<>();
        ncEstado = new javax.swing.JComboBox<>();
        ncRua = new javax.swing.JTextField();
        ncNumero = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Moto Zoom");
        setBackground(new java.awt.Color(0, 0, 51));
        setResizable(false);
        setSize(new java.awt.Dimension(1300, 700));

        painelPrincipal.setBackground(new java.awt.Color(153, 153, 153));

        painelTitulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        bFexar.setFont(new java.awt.Font("Segoe UI Black", 0, 11)); // NOI18N
        bFexar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bFexar.setText("Fexar");
        bFexar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bFexar.setFocusable(false);
        bFexar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        bFexar.setOpaque(true);
        bFexar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bFexarMouseDragged(evt);
            }
        });
        bFexar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bFexarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bFexarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bFexarMouseExited(evt);
            }
        });

        bMinimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bMinimizar.setText("Minimizar");
        bMinimizar.setOpaque(true);
        bMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bMinimizarMouseExited(evt);
            }
        });

        lbUser.setText("User: ");

        javax.swing.GroupLayout painelTituloLayout = new javax.swing.GroupLayout(painelTitulo);
        painelTitulo.setLayout(painelTituloLayout);
        painelTituloLayout.setHorizontalGroup(
            painelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bFexar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        painelTituloLayout.setVerticalGroup(
            painelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(bFexar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        painelPrograma.setBackground(new java.awt.Color(255, 153, 51));
        painelPrograma.setMaximumSize(new java.awt.Dimension(1231, 588));

        painelBotoes.setBackground(new java.awt.Color(0, 66, 66));

        bTelaInicial.setBackground(new java.awt.Color(0, 51, 51));
        bTelaInicial.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bTelaInicial.setForeground(new java.awt.Color(153, 153, 153));
        bTelaInicial.setText(" Tela Inicial");
        bTelaInicial.setToolTipText("");
        bTelaInicial.setOpaque(true);
        bTelaInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bTelaInicialMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bTelaInicialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bTelaInicialMouseExited(evt);
            }
        });

        bNovaVenda.setBackground(new java.awt.Color(0, 51, 51));
        bNovaVenda.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bNovaVenda.setForeground(new java.awt.Color(153, 153, 153));
        bNovaVenda.setText(" Nova Venda");
        bNovaVenda.setOpaque(true);
        bNovaVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bNovaVendaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bNovaVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bNovaVendaMouseExited(evt);
            }
        });

        bVendas.setBackground(new java.awt.Color(0, 51, 51));
        bVendas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bVendas.setForeground(new java.awt.Color(153, 153, 153));
        bVendas.setText(" Vendas");
        bVendas.setOpaque(true);
        bVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bVendasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bVendasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bVendasMouseExited(evt);
            }
        });

        bProdutos.setBackground(new java.awt.Color(0, 51, 51));
        bProdutos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bProdutos.setForeground(new java.awt.Color(153, 153, 153));
        bProdutos.setText(" Produtos");
        bProdutos.setOpaque(true);
        bProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bProdutosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bProdutosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bProdutosMouseExited(evt);
            }
        });

        bServicos.setBackground(new java.awt.Color(0, 51, 51));
        bServicos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bServicos.setForeground(new java.awt.Color(153, 153, 153));
        bServicos.setText(" Serviços");
        bServicos.setOpaque(true);
        bServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bServicosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bServicosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bServicosMouseExited(evt);
            }
        });

        bOrdemdeServico.setBackground(new java.awt.Color(0, 51, 51));
        bOrdemdeServico.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bOrdemdeServico.setForeground(new java.awt.Color(153, 153, 153));
        bOrdemdeServico.setText(" Ordem de Serviço");
        bOrdemdeServico.setOpaque(true);
        bOrdemdeServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bOrdemdeServicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bOrdemdeServicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bOrdemdeServicoMouseExited(evt);
            }
        });

        bFinancas.setBackground(new java.awt.Color(0, 51, 51));
        bFinancas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bFinancas.setForeground(new java.awt.Color(153, 153, 153));
        bFinancas.setText(" Finanças");
        bFinancas.setOpaque(true);
        bFinancas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bFinancasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bFinancasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bFinancasMouseExited(evt);
            }
        });

        bConfiguracoes.setBackground(new java.awt.Color(0, 51, 51));
        bConfiguracoes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bConfiguracoes.setForeground(new java.awt.Color(153, 153, 153));
        bConfiguracoes.setText(" Configurações");
        bConfiguracoes.setOpaque(true);
        bConfiguracoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bConfiguracoesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bConfiguracoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bConfiguracoesMouseExited(evt);
            }
        });

        bEncerrarSessao.setBackground(new java.awt.Color(0, 51, 51));
        bEncerrarSessao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bEncerrarSessao.setForeground(new java.awt.Color(153, 153, 153));
        bEncerrarSessao.setText(" Encerrar Sessão");
        bEncerrarSessao.setOpaque(true);
        bEncerrarSessao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bEncerrarSessaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bEncerrarSessaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bEncerrarSessaoMouseExited(evt);
            }
        });

        bClientes.setBackground(new java.awt.Color(0, 51, 51));
        bClientes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        bClientes.setForeground(new java.awt.Color(153, 153, 153));
        bClientes.setText(" Clientes");
        bClientes.setOpaque(true);
        bClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bClientesMouseExited(evt);
            }
        });

        javax.swing.GroupLayout painelBotoesLayout = new javax.swing.GroupLayout(painelBotoes);
        painelBotoes.setLayout(painelBotoesLayout);
        painelBotoesLayout.setHorizontalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bTelaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bOrdemdeServico, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
            .addComponent(bFinancas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bConfiguracoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bEncerrarSessao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bNovaVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        painelBotoesLayout.setVerticalGroup(
            painelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesLayout.createSequentialGroup()
                .addComponent(bTelaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bOrdemdeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFinancas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConfiguracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bEncerrarSessao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelFuncional.setLayout(new java.awt.CardLayout());

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/motozoom1/swing/logo.jpeg"))); // NOI18N
        jLabel14.setOpaque(true);
        painelFuncional.add(jLabel14, "card2");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Pesquisar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Vendas");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        tableVendas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Número", "Data", "Vendedor", "Cliente", "Valor Total", "Forma de Pagamento", "Editar", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVendas.setGridColor(new java.awt.Color(160, 160, 160));
        tableVendas.setRowHeight(40);
        tableVendas.setRowMargin(5);
        tableVendas.setRowSelectionAllowed(false);
        tableVendas.setShowGrid(false);
        tableVendas.setShowHorizontalLines(true);
        tableVendas.getTableHeader().setReorderingAllowed(false);
        scrollVendas.setViewportView(tableVendas);
        if (tableVendas.getColumnModel().getColumnCount() > 0) {
            tableVendas.getColumnModel().getColumn(0).setResizable(false);
            tableVendas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableVendas.getColumnModel().getColumn(1).setResizable(false);
            tableVendas.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableVendas.getColumnModel().getColumn(2).setResizable(false);
            tableVendas.getColumnModel().getColumn(3).setResizable(false);
            tableVendas.getColumnModel().getColumn(4).setResizable(false);
            tableVendas.getColumnModel().getColumn(5).setResizable(false);
            tableVendas.getColumnModel().getColumn(5).setHeaderValue("Forma de Pagamento");
            tableVendas.getColumnModel().getColumn(6).setResizable(false);
            tableVendas.getColumnModel().getColumn(6).setPreferredWidth(30);
            tableVendas.getColumnModel().getColumn(7).setResizable(false);
            tableVendas.getColumnModel().getColumn(7).setPreferredWidth(30);
        }

        spinVendas.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));
        spinVendas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinVendasStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Paginas");

        jButton19.setText("Imprimir Comprovante");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton19)
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout vendasLayout = new javax.swing.GroupLayout(vendas);
        vendas.setLayout(vendasLayout);
        vendasLayout.setHorizontalGroup(
            vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollVendas)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        vendasLayout.setVerticalGroup(
            vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendasLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollVendas, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelFuncional.add(vendas, "cardVendas");

        jPanel4.setToolTipText("");

        jLabel21.setText("Data");

        jLabel23.setText("Vendedor");

        jLabel24.setText("Cliente");

        jButton7.setText("Finalizar Venda e Imprimir Comprovante");

        nvFinalizarVenda.setText("Finalizar Venda");
        nvFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvFinalizarVendaActionPerformed(evt);
            }
        });

        jLabel27.setText("Forma de Pagamento");

        jLabel29.setText("Telefone");

        nvFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Escolha uma opção >", "Dinheiro", "Debito", "Crédito", "Pix", "Transferência" }));

        nvTelefone.setEditable(false);
        try {
            nvTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        nvTelefone.setText("54997033088");

        nvData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        nvData.setText("24/02/2021");

        nvUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ernani", "Jakson", "Remoaldo" }));

        nvCliente.setText("Ciclano");
        nvCliente.setToolTipText("");
        nvCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nvClienteKeyPressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Sub.Total");

        nvSubTotal.setEditable(false);
        nvSubTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nvSubTotal.setText("0,00");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Descontos");

        nvDescontos.setEditable(false);
        nvDescontos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nvDescontos.setText("0,00");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Total");

        nvTotal.setEditable(false);
        nvTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nvTotal.setText("0,00");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Valor Recebido");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Troco");

        nvTroco.setEditable(false);
        nvTroco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nvTroco.setText("0,00");

        nvValorRecebido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nvValorRecebido.setText("0,00");
        nvValorRecebido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nvValorRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nvValorRecebidoKeyReleased(evt);
            }
        });

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));
        jPanel29.setPreferredSize(new java.awt.Dimension(500, 200));

        nvObservacoes.setColumns(20);
        nvObservacoes.setRows(5);
        jScrollPane12.setViewportView(nvObservacoes);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nvFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nvData)
                                    .addComponent(nvUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nvCliente))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nvTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(253, 253, 253))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(nvFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nvTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nvDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nvSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nvTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nvValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nvData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nvUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nvTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nvCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nvFormaPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nvSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nvDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nvValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nvTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nvTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nvFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane2.addTab("Detalhes da Venda", jPanel4);

        nvTableNovaVenda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nvTableNovaVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lavagem", "1", "100,00", "0,00", null, "100,00", null},
                {"5", "Chaveiro", "1", "15,00", "0,00", null, "15,00", null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição Produto/Serviço", "Quantidade", "Valor Unitário", "Valor Desconto UN", "Valor Desconto Total", "Valor Total", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nvTableNovaVenda.setIntercellSpacing(new java.awt.Dimension(10, 10));
        nvTableNovaVenda.setRowHeight(40);
        nvTableNovaVenda.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(nvTableNovaVenda);
        if (nvTableNovaVenda.getColumnModel().getColumnCount() > 0) {
            nvTableNovaVenda.getColumnModel().getColumn(0).setResizable(false);
            nvTableNovaVenda.getColumnModel().getColumn(1).setResizable(false);
            nvTableNovaVenda.getColumnModel().getColumn(2).setResizable(false);
            nvTableNovaVenda.getColumnModel().getColumn(3).setResizable(false);
            nvTableNovaVenda.getColumnModel().getColumn(4).setResizable(false);
            nvTableNovaVenda.getColumnModel().getColumn(5).setResizable(false);
            nvTableNovaVenda.getColumnModel().getColumn(6).setResizable(false);
            nvTableNovaVenda.getColumnModel().getColumn(7).setResizable(false);
        }

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setText("Serviço");

        nvAddServico.setText("Adicionar");
        nvAddServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvAddServicoActionPerformed(evt);
            }
        });

        nvQuantServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        nvQuantServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nvQuantServicoKeyReleased(evt);
            }
        });

        nvValorTotalServico.setEditable(false);
        nvValorTotalServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel31.setText("Quantidade");

        nvDescontoServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nvDescontoServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nvDescontoServicoKeyReleased(evt);
            }
        });

        jLabel32.setText(" DescontoUN");

        jLabel33.setText("Valor UN");

        jLabel34.setText("Valor Total");

        nvValorUNServico.setEditable(false);
        nvValorUNServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel148.setText("Desconto Total");

        nvDescontoServicoTotal.setEditable(false);
        nvDescontoServicoTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nvDescontoServicoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nvDescontoServicoTotalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvServico, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nvDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel148)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nvDescontoServicoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvValorTotalServico, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvAddServico)
                .addGap(5, 5, 5))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(jLabel33)
                        .addComponent(nvQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nvValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32)
                        .addComponent(nvDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(nvServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nvAddServico)
                        .addComponent(nvValorTotalServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34)
                        .addComponent(nvDescontoServicoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel148)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel35.setText("Produto");

        nvProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvProdutoActionPerformed(evt);
            }
        });

        jLabel36.setText("Quantidade");

        jLabel37.setText("Valor UN");

        jLabel38.setText("Valor Total");

        nvAddProduto.setText("Adicionar");
        nvAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvAddProdutoActionPerformed(evt);
            }
        });

        jLabel39.setText("DescontoUN");

        nvQuantProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        nvQuantProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nvQuantProdutoKeyReleased(evt);
            }
        });

        nvValorTotalProduto.setEditable(false);
        nvValorTotalProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        nvValorUNProduto.setEditable(false);
        nvValorUNProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        nvDescontoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nvDescontoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nvDescontoProdutoKeyReleased(evt);
            }
        });

        jLabel1.setText("Desconto Total");

        nvDescontoProdutoTotal.setEditable(false);
        nvDescontoProdutoTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nvDescontoProdutoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nvDescontoProdutoTotalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvDescontoProdutoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvValorTotalProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nvAddProduto)
                .addGap(5, 5, 5))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(nvValorTotalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nvAddProduto))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(nvProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36)
                        .addComponent(jLabel37)
                        .addComponent(nvQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nvValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39)
                        .addComponent(nvDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(nvDescontoProdutoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Produtos / Serviços", jPanel7);

        javax.swing.GroupLayout novaVendaLayout = new javax.swing.GroupLayout(novaVenda);
        novaVenda.setLayout(novaVendaLayout);
        novaVendaLayout.setHorizontalGroup(
            novaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        novaVendaLayout.setVerticalGroup(
            novaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        painelFuncional.add(novaVenda, "cardNovaVenda");

        jPanel1.setToolTipText("");

        jLabel6.setText("Data");

        jLabel5.setText("Número");

        jLabel7.setText("Vendedor");

        jLabel8.setText("Cliente");

        evId.setEditable(false);
        evId.setText("15015");
        evId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evIdActionPerformed(evt);
            }
        });

        jButton5.setText("Salvar Alterações");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Voltar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel9.setText("Forma de Pagamento");

        jLabel15.setText("Telefone");

        evFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Debito", "Crédito", "Pix", "Transferência" }));

        evTelefone.setEditable(false);
        try {
            evTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        evTelefone.setText("54997033088");

        evData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));

        evCliente.setText("fwefwer");
        evCliente.setToolTipText("");
        evCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                evClienteKeyPressed(evt);
            }
        });

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));
        jPanel30.setPreferredSize(new java.awt.Dimension(500, 200));

        evObservacoes.setColumns(20);
        evObservacoes.setRows(5);
        jScrollPane13.setViewportView(evObservacoes);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("Total");

        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel141.setText("Descontos");

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel142.setText("Sub.Total");

        evSubTotal.setEditable(false);
        evSubTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        evSubTotal.setText("0,00");

        evDescontos.setEditable(false);
        evDescontos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        evDescontos.setText("0,00");

        evTotal.setEditable(false);
        evTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        evTotal.setText("0,00");

        evTroco.setEditable(false);
        evTroco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        evTroco.setText("0,00");

        evValorRecebido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        evValorRecebido.setText("0,00");
        evValorRecebido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        evValorRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                evValorRecebidoKeyReleased(evt);
            }
        });

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel143.setText("Troco");

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel144.setText("Valor Recebido");

        evUsuario.setEditable(false);
        evUsuario.setText("15015");
        evUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(evFormaPagamento, 0, 309, Short.MAX_VALUE)
                            .addComponent(evId)
                            .addComponent(evData)
                            .addComponent(evCliente)
                            .addComponent(evUsuario, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(evTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(253, 253, 253))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(evTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(evDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(evSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(evTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(evValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(evId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(evData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(evUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(evTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(evCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(evFormaPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(evSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(evDescontos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(evValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(evTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(evTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        jTabbedPane1.addTab("Detalhes da Venda", jPanel1);

        evTableProdutoServico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        evTableProdutoServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lavagem", "1", "100,00", "0,00", "100,00", null},
                {"5", "Chaveiro", "1", "15,00", "0,00", "15,00", null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição Produto/Serviço", "Quantidade", "Valor Unitário", "Valor Desconto", "Valor Total", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        evTableProdutoServico.setIntercellSpacing(new java.awt.Dimension(10, 10));
        evTableProdutoServico.setRowHeight(40);
        evTableProdutoServico.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(evTableProdutoServico);
        if (evTableProdutoServico.getColumnModel().getColumnCount() > 0) {
            evTableProdutoServico.getColumnModel().getColumn(0).setResizable(false);
            evTableProdutoServico.getColumnModel().getColumn(1).setResizable(false);
            evTableProdutoServico.getColumnModel().getColumn(2).setResizable(false);
            evTableProdutoServico.getColumnModel().getColumn(3).setResizable(false);
            evTableProdutoServico.getColumnModel().getColumn(4).setResizable(false);
            evTableProdutoServico.getColumnModel().getColumn(5).setResizable(false);
            evTableProdutoServico.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Serviço");

        evAddServico.setText("Adicionar");
        evAddServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evAddServicoActionPerformed(evt);
            }
        });

        evQuantServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        evQuantServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                evQuantServicoKeyReleased(evt);
            }
        });

        evValorTotalServico.setEditable(false);
        evValorTotalServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel18.setText("Quantidade");

        evDescontoServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        evDescontoServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                evDescontoServicoKeyReleased(evt);
            }
        });

        jLabel25.setText("Valor Desconto");

        jLabel19.setText("Valor Unitário");

        jLabel26.setText("Valor Total");

        evValorUNServico.setEditable(false);
        evValorUNServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evServico, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evValorTotalServico, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(evAddServico)
                .addGap(34, 34, 34))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26)
                        .addComponent(evDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(evValorTotalServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19)
                        .addComponent(evQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(evValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(evServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(evAddServico)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Produto");

        evProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evProdutoActionPerformed(evt);
            }
        });

        jLabel13.setText("Quantidade");

        jLabel16.setText("Valor Unitário");

        jLabel17.setText("Valor Total");

        evAddProduto.setText("Adicionar");
        evAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evAddProdutoActionPerformed(evt);
            }
        });

        jLabel20.setText("Valor Desconto");

        evQuantProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        evQuantProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                evQuantProdutoKeyReleased(evt);
            }
        });

        evValorTotalProduto.setEditable(false);
        evValorTotalProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        evValorUNProduto.setEditable(false);
        evValorUNProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        evDescontoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        evDescontoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                evDescontoProdutoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evValorTotalProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evAddProduto)
                .addGap(35, 35, 35))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel17)
                        .addComponent(evDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(evValorTotalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(evProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel16)
                        .addComponent(evAddProduto)
                        .addComponent(evQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(evValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Produtos / Serviços", jPanel3);

        javax.swing.GroupLayout editarVendaLayout = new javax.swing.GroupLayout(editarVenda);
        editarVenda.setLayout(editarVendaLayout);
        editarVendaLayout.setHorizontalGroup(
            editarVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        editarVendaLayout.setVerticalGroup(
            editarVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        painelFuncional.add(editarVenda, "cardEditarVenda");

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField11.setToolTipText("");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Pesquisar");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setText("Produtos");
        jLabel44.setToolTipText("");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(header1Layout.createSequentialGroup()
                        .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        tableProdutos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableProdutos.setGridColor(new java.awt.Color(160, 160, 160));
        tableProdutos.setIntercellSpacing(new java.awt.Dimension(1, 5));
        tableProdutos.setRowHeight(40);
        tableProdutos.setRowSelectionAllowed(false);
        tableProdutos.getTableHeader().setReorderingAllowed(false);
        scrollVendas1.setViewportView(tableProdutos);

        spinProdutos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinProdutos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinProdutosStateChanged(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel45.setText("Paginas");

        bNovoProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bNovoProduto.setText("Novo Produto");
        bNovoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bNovoProdutoMouseClicked(evt);
            }
        });
        bNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout produtosLayout = new javax.swing.GroupLayout(produtos);
        produtos.setLayout(produtosLayout);
        produtosLayout.setHorizontalGroup(
            produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollVendas1)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        produtosLayout.setVerticalGroup(
            produtosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produtosLayout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollVendas1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelFuncional.add(produtos, "cardProdutos");

        jLabel46.setText("Nome/Descrição");

        jLabel47.setText("Número");

        jLabel48.setText("Preço de Compra");

        jLabel49.setText("Preço de Venda");

        epIdProduto.setEditable(false);
        epIdProduto.setText("15015");
        epIdProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epIdProdutoActionPerformed(evt);
            }
        });

        jButton10.setText("Salvar Alterações");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Voltar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel50.setText("Estoque");

        epPrecoCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        epPrecoCompra.setText("0,00");

        epPrecoVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        epPrecoVenda.setText("0,00");

        epEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        epEstoque.setText("0");

        epNomeDesc.setText("Meu Produto");

        jTextField15.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jTextField15.setText("Editar Produto");
        jTextField15.setOpaque(false);

        javax.swing.GroupLayout editarProdutoLayout = new javax.swing.GroupLayout(editarProduto);
        editarProduto.setLayout(editarProdutoLayout);
        editarProdutoLayout.setHorizontalGroup(
            editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarProdutoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarProdutoLayout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarProdutoLayout.createSequentialGroup()
                        .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(epNomeDesc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(epEstoque, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(epPrecoVenda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(epPrecoCompra, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(epIdProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                        .addGap(148, 148, 148)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        editarProdutoLayout.setVerticalGroup(
            editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarProdutoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(epIdProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarProdutoLayout.createSequentialGroup()
                        .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(epNomeDesc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(epPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(epPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(epEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addGroup(editarProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        painelFuncional.add(editarProduto, "cardEditarProduto");

        jLabel53.setText("Nome/Descrição");

        jLabel57.setText("Preço de Compra");

        jLabel58.setText("Preço de Venda");

        jButton14.setText("Adicionar Produto");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        bVoltarNovoProduto.setText("Voltar");
        bVoltarNovoProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bVoltarNovoProdutoMouseClicked(evt);
            }
        });
        bVoltarNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVoltarNovoProdutoActionPerformed(evt);
            }
        });

        jLabel59.setText("Estoque");

        npPrecoCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        npPrecoCompra.setText("0,00");
        npPrecoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                npPrecoCompraActionPerformed(evt);
            }
        });

        npPrecoVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        npPrecoVenda.setText("0,00");

        npEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        npEstoque.setText("0");

        npNomeDesc.setText("Meu Produto");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jTextField14.setText("Novo Produto");

        javax.swing.GroupLayout novoProdutoLayout = new javax.swing.GroupLayout(novoProduto);
        novoProduto.setLayout(novoProdutoLayout);
        novoProdutoLayout.setHorizontalGroup(
            novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoProdutoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(novoProdutoLayout.createSequentialGroup()
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bVoltarNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, novoProdutoLayout.createSequentialGroup()
                        .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(npNomeDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                            .addComponent(npEstoque, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(npPrecoVenda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(npPrecoCompra, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(152, 152, 152)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        novoProdutoLayout.setVerticalGroup(
            novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoProdutoLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(novoProdutoLayout.createSequentialGroup()
                        .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(npNomeDesc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(npPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(npPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(npEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addGroup(novoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bVoltarNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        painelFuncional.add(novoProduto, "cardNovoProduto");

        jTextField16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField16.setToolTipText("");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Pesquisar");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel52.setText("Serviços");
        jLabel52.setToolTipText("");

        javax.swing.GroupLayout header2Layout = new javax.swing.GroupLayout(header2);
        header2.setLayout(header2Layout);
        header2Layout.setHorizontalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        header2Layout.setVerticalGroup(
            header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(header2Layout.createSequentialGroup()
                        .addGroup(header2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        tableServicos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Número", "Nome/Descrição", "Preço", "Editar", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableServicos.setGridColor(new java.awt.Color(160, 160, 160));
        tableServicos.setRowHeight(40);
        tableServicos.setRowMargin(5);
        tableServicos.setRowSelectionAllowed(false);
        tableServicos.getTableHeader().setReorderingAllowed(false);
        scrollVendas2.setViewportView(tableServicos);
        if (tableServicos.getColumnModel().getColumnCount() > 0) {
            tableServicos.getColumnModel().getColumn(0).setResizable(false);
            tableServicos.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableServicos.getColumnModel().getColumn(1).setResizable(false);
            tableServicos.getColumnModel().getColumn(2).setResizable(false);
            tableServicos.getColumnModel().getColumn(3).setResizable(false);
            tableServicos.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableServicos.getColumnModel().getColumn(4).setResizable(false);
            tableServicos.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        spinServicos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinServicos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinServicosStateChanged(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel54.setText("Paginas");

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton12.setText("Novo Serviço");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout servicosLayout = new javax.swing.GroupLayout(servicos);
        servicos.setLayout(servicosLayout);
        servicosLayout.setHorizontalGroup(
            servicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollVendas2)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        servicosLayout.setVerticalGroup(
            servicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(servicosLayout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollVendas2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelFuncional.add(servicos, "cardServicos");

        jLabel55.setText("Nome/Descrição");

        jLabel56.setText("Número");

        jLabel60.setText("Preço");

        esIdServico.setEditable(false);
        esIdServico.setText("15015");
        esIdServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esIdServicoActionPerformed(evt);
            }
        });

        jButton13.setText("Salvar Alterações");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton16.setText("Voltar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        esPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jTextField20.setText("Editar Serviço");
        jTextField20.setOpaque(false);

        javax.swing.GroupLayout editarServicoLayout = new javax.swing.GroupLayout(editarServico);
        editarServico.setLayout(editarServicoLayout);
        editarServicoLayout.setHorizontalGroup(
            editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarServicoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarServicoLayout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarServicoLayout.createSequentialGroup()
                        .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(esNomeDesc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(esPreco, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(esIdServico, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
                        .addGap(148, 148, 148)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        editarServicoLayout.setVerticalGroup(
            editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarServicoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(esIdServico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarServicoLayout.createSequentialGroup()
                        .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(esNomeDesc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(esPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                .addGroup(editarServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        painelFuncional.add(editarServico, "cardEditarServico");

        jLabel63.setText("Nome/Descrição");

        jLabel64.setText("Preço");

        jButton17.setText("Adicionar Serviço");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Voltar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        nsPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        nsNomeDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nsNomeDescActionPerformed(evt);
            }
        });

        jTextField22.setEditable(false);
        jTextField22.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jTextField22.setText("Novo Serviço");

        javax.swing.GroupLayout novoServicoLayout = new javax.swing.GroupLayout(novoServico);
        novoServico.setLayout(novoServicoLayout);
        novoServicoLayout.setHorizontalGroup(
            novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoServicoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(novoServicoLayout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, novoServicoLayout.createSequentialGroup()
                        .addGroup(novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nsNomeDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                            .addComponent(nsPreco, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(152, 152, 152)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        novoServicoLayout.setVerticalGroup(
            novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoServicoLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(novoServicoLayout.createSequentialGroup()
                        .addGroup(novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(nsNomeDesc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nsPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                .addGroup(novoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        painelFuncional.add(novoServico, "cardNovoServico");

        spinOS.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));
        spinOS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinOSStateChanged(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel61.setText("Paginas");

        jButton20.setText("Nova OS");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinOS, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinOS, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tableOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Número", "Data", "Cliente", "Status", "Valor", "Editar", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOS.setGridColor(new java.awt.Color(160, 160, 160));
        tableOS.setRowHeight(40);
        tableOS.setRowMargin(5);
        tableOS.setRowSelectionAllowed(false);
        tableOS.getTableHeader().setReorderingAllowed(false);
        scrollVendas3.setViewportView(tableOS);
        if (tableOS.getColumnModel().getColumnCount() > 0) {
            tableOS.getColumnModel().getColumn(0).setResizable(false);
            tableOS.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableOS.getColumnModel().getColumn(1).setResizable(false);
            tableOS.getColumnModel().getColumn(2).setResizable(false);
            tableOS.getColumnModel().getColumn(3).setResizable(false);
            tableOS.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableOS.getColumnModel().getColumn(4).setResizable(false);
            tableOS.getColumnModel().getColumn(5).setResizable(false);
            tableOS.getColumnModel().getColumn(5).setPreferredWidth(30);
            tableOS.getColumnModel().getColumn(6).setResizable(false);
            tableOS.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField23.setToolTipText("");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setText("Pesquisar");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel65.setText("Ordem de Serviço");
        jLabel65.setToolTipText("");

        javax.swing.GroupLayout header3Layout = new javax.swing.GroupLayout(header3);
        header3.setLayout(header3Layout);
        header3Layout.setHorizontalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header3Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 405, Short.MAX_VALUE)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        header3Layout.setVerticalGroup(
            header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(header3Layout.createSequentialGroup()
                        .addGroup(header3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout ordemdeServicoLayout = new javax.swing.GroupLayout(ordemdeServico);
        ordemdeServico.setLayout(ordemdeServicoLayout);
        ordemdeServicoLayout.setHorizontalGroup(
            ordemdeServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollVendas3)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ordemdeServicoLayout.setVerticalGroup(
            ordemdeServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordemdeServicoLayout.createSequentialGroup()
                .addComponent(header3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollVendas3, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelFuncional.add(ordemdeServico, "cardOrdemdeServico");

        jPanel13.setToolTipText("");

        jLabel66.setText("Data");

        jLabel68.setText("Cliente");

        nosCriar_e_Imprimir.setText("Criar OS e Imprimir");
        nosCriar_e_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nosCriar_e_ImprimirActionPerformed(evt);
            }
        });

        nosCriar.setText("Criar OS");
        nosCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nosCriarActionPerformed(evt);
            }
        });

        jLabel70.setText("Telefone");

        nosClienteFone.setEditable(false);
        try {
            nosClienteFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        nosClienteFone.setText("54997033088");

        nosData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        nosData.setText("24/02/2021");

        nosCliente.setText("Ciclano");
        nosCliente.setToolTipText("");
        nosCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nosClienteKeyPressed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Total");

        nosValorTotal.setEditable(false);
        nosValorTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nosValorTotal.setText("0,00");

        nosDescVeiculo.setText("Honda CG Titan, 2008, SEN2012");

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição do Defeito"));
        jPanel17.setPreferredSize(new java.awt.Dimension(500, 200));

        nosDescDefeito.setColumns(20);
        nosDescDefeito.setRows(5);
        jScrollPane2.setViewportView(nosDescDefeito);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Peças para Reposição"));
        jPanel18.setPreferredSize(new java.awt.Dimension(500, 200));

        nosPecaReposicao.setColumns(20);
        nosPecaReposicao.setRows(5);
        jScrollPane6.setViewportView(nosPecaReposicao);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));
        jPanel19.setPreferredSize(new java.awt.Dimension(500, 200));

        nosObservacoes.setColumns(20);
        nosObservacoes.setRows(5);
        jScrollPane7.setViewportView(nosObservacoes);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel96.setText("Descrição do Veiculo");

        nosVoltar.setText("Voltar");
        nosVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nosVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nosData, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(nosCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nosClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(253, 253, 253))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(nosCriar_e_Imprimir)
                                .addGap(18, 18, 18)
                                .addComponent(nosCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(nosVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nosValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nosDescVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nosData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nosClienteFone)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nosDescVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nosValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nosCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nosCriar_e_Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nosVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Detalhes da Venda", jPanel13);

        nosTableProdutoServico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nosTableProdutoServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lavagem", "1", "100,00", "0,00", "100,00", null},
                {"5", "Chaveiro", "1", "15,00", "0,00", "15,00", null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição Produto/Serviço", "Quantidade", "Valor Unitário", "Valor Desconto", "Valor Total", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nosTableProdutoServico.setIntercellSpacing(new java.awt.Dimension(10, 10));
        nosTableProdutoServico.setRowHeight(40);
        nosTableProdutoServico.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(nosTableProdutoServico);
        if (nosTableProdutoServico.getColumnModel().getColumnCount() > 0) {
            nosTableProdutoServico.getColumnModel().getColumn(0).setResizable(false);
            nosTableProdutoServico.getColumnModel().getColumn(1).setResizable(false);
            nosTableProdutoServico.getColumnModel().getColumn(2).setResizable(false);
            nosTableProdutoServico.getColumnModel().getColumn(3).setResizable(false);
            nosTableProdutoServico.getColumnModel().getColumn(4).setResizable(false);
            nosTableProdutoServico.getColumnModel().getColumn(5).setResizable(false);
            nosTableProdutoServico.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel76.setText("Serviço");

        nosAddServico.setText("Adicionar");
        nosAddServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nosAddServicoActionPerformed(evt);
            }
        });

        nosQuantServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        nosQuantServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nosQuantServicoKeyReleased(evt);
            }
        });

        nosValorTotalServico.setEditable(false);
        nosValorTotalServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel77.setText("Quantidade");

        nosDescontoServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nosDescontoServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nosDescontoServicoKeyReleased(evt);
            }
        });

        jLabel78.setText("Valor Desconto");

        jLabel79.setText("Valor Unitário");

        jLabel80.setText("Valor Total");

        nosValorUNServico.setEditable(false);
        nosValorUNServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosServico, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosValorTotalServico, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(nosAddServico)
                .addGap(34, 34, 34))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel78)
                        .addComponent(jLabel80)
                        .addComponent(nosDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nosValorTotalServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel77)
                        .addComponent(jLabel79)
                        .addComponent(nosQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nosValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel76)
                        .addComponent(nosServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nosAddServico)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel81.setText("Produto");

        nosProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nosProdutoActionPerformed(evt);
            }
        });

        jLabel82.setText("Quantidade");

        jLabel83.setText("Valor Unitário");

        jLabel84.setText("Valor Total");

        nosAddProduto.setText("Adicionar");
        nosAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nosAddProdutoActionPerformed(evt);
            }
        });

        jLabel85.setText("Valor Desconto");

        nosQuantProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        nosQuantProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nosQuantProdutoKeyReleased(evt);
            }
        });

        nosValorTotalProduto.setEditable(false);
        nosValorTotalProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        nosValorUNProduto.setEditable(false);
        nosValorUNProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        nosDescontoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nosDescontoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nosDescontoProdutoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosValorTotalProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nosAddProduto)
                .addGap(35, 35, 35))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel85)
                        .addComponent(jLabel84)
                        .addComponent(nosDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nosValorTotalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel81)
                        .addComponent(nosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel82)
                        .addComponent(jLabel83)
                        .addComponent(nosAddProduto)
                        .addComponent(nosQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nosValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Produtos / Serviços", jPanel14);

        javax.swing.GroupLayout novaOrdemdeServicoLayout = new javax.swing.GroupLayout(novaOrdemdeServico);
        novaOrdemdeServico.setLayout(novaOrdemdeServicoLayout);
        novaOrdemdeServicoLayout.setHorizontalGroup(
            novaOrdemdeServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        novaOrdemdeServicoLayout.setVerticalGroup(
            novaOrdemdeServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        painelFuncional.add(novaOrdemdeServico, "cardNovaOrdemdeServico");

        jPanel20.setToolTipText("");

        jLabel67.setText("Data");

        jLabel71.setText("Cliente*");

        jButton25.setText("Salvar Alterações e Imprimir");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Salvar Alterações");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel72.setText("Telefone");

        eosClienteFone.setEditable(false);
        try {
            eosClienteFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        eosClienteFone.setText("54997033088");

        eosData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        eosData.setText("24/02/2021");

        eosCliente.setText("Ciclano");
        eosCliente.setToolTipText("");
        eosCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                eosClienteKeyPressed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setText("Total");

        eosValorTotal.setEditable(false);
        eosValorTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eosValorTotal.setText("0,00");

        jLabel75.setText("Descrição do Veiculo*");

        eosDescVeiculo.setText("Honda CG Titan, 2008, SEN2012");

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição do Defeito*"));
        jPanel21.setPreferredSize(new java.awt.Dimension(500, 200));

        eosDescDefeito.setColumns(20);
        eosDescDefeito.setRows(5);
        jScrollPane8.setViewportView(eosDescDefeito);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Peças para Reposição"));
        jPanel22.setPreferredSize(new java.awt.Dimension(500, 200));

        eosPecaReposicao.setColumns(20);
        eosPecaReposicao.setRows(5);
        jScrollPane9.setViewportView(eosPecaReposicao);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Observações"));
        jPanel23.setPreferredSize(new java.awt.Dimension(500, 200));

        eosObservacoes.setColumns(20);
        eosObservacoes.setRows(5);
        jScrollPane10.setViewportView(eosObservacoes);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        eosStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aberta", "Aguardando Peças", "Finalizada", "Paga" }));

        jLabel128.setText("Status da OS");

        eosVoltar.setText("Voltar");
        eosVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eosVoltarActionPerformed(evt);
            }
        });

        jLabel140.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel140.setText("ID ORDEM");

        eosIdOrdem.setEditable(false);
        eosIdOrdem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eosIdOrdem.setText("0");

        eosPagar.setText("Pagar");
        eosPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eosPagarActionPerformed(evt);
            }
        });

        jLabel147.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel147.setText("ID VENDA");

        eosIdVenda.setEditable(false);
        eosIdVenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eosIdVenda.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eosData, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(eosCliente))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eosClienteFone, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(253, 253, 253))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel128)
                                .addGap(18, 18, 18)
                                .addComponent(eosStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eosDescVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jButton25)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eosPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(eosVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eosValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eosIdVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eosIdOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 47, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eosData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(eosStatus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel128, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eosClienteFone)
                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eosDescVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(eosIdOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(eosValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eosIdVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eosVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eosPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Detalhes da Venda", jPanel20);

        eosTableProdutoServico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        eosTableProdutoServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lavagem", "1", "100,00", "0,00", "100,00", null},
                {"5", "Chaveiro", "1", "15,00", "0,00", "15,00", null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição Produto/Serviço", "Quantidade", "Valor Unitário", "Valor Desconto", "Valor Total", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eosTableProdutoServico.setIntercellSpacing(new java.awt.Dimension(10, 10));
        eosTableProdutoServico.setRowHeight(40);
        eosTableProdutoServico.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(eosTableProdutoServico);
        if (eosTableProdutoServico.getColumnModel().getColumnCount() > 0) {
            eosTableProdutoServico.getColumnModel().getColumn(0).setResizable(false);
            eosTableProdutoServico.getColumnModel().getColumn(1).setResizable(false);
            eosTableProdutoServico.getColumnModel().getColumn(2).setResizable(false);
            eosTableProdutoServico.getColumnModel().getColumn(3).setResizable(false);
            eosTableProdutoServico.getColumnModel().getColumn(4).setResizable(false);
            eosTableProdutoServico.getColumnModel().getColumn(5).setResizable(false);
            eosTableProdutoServico.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel86.setText("Serviço");

        eosAddServico.setText("Adicionar");
        eosAddServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eosAddServicoActionPerformed(evt);
            }
        });

        eosQuantServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        eosQuantServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eosQuantServicoKeyReleased(evt);
            }
        });

        eosValorTotalServico.setEditable(false);
        eosValorTotalServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        jLabel87.setText("Quantidade");

        eosDescontoServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        eosDescontoServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eosDescontoServicoKeyReleased(evt);
            }
        });

        jLabel88.setText("Valor Desconto");

        jLabel89.setText("Valor Unitário");

        jLabel90.setText("Valor Total");

        eosValorUNServico.setEditable(false);
        eosValorUNServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosServico, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosValorTotalServico, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(eosAddServico)
                .addGap(34, 34, 34))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel88)
                        .addComponent(jLabel90)
                        .addComponent(eosDescontoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eosValorTotalServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel87)
                        .addComponent(jLabel89)
                        .addComponent(eosQuantServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eosValorUNServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel86)
                        .addComponent(eosServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eosAddServico)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel91.setText("Produto");

        eosProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eosProdutoActionPerformed(evt);
            }
        });

        jLabel92.setText("Quantidade");

        jLabel93.setText("Valor Unitário");

        jLabel94.setText("Valor Total");

        eosAddProduto.setText("Adicionar");
        eosAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eosAddProdutoActionPerformed(evt);
            }
        });

        jLabel95.setText("Valor Desconto");

        eosQuantProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        eosQuantProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eosQuantProdutoKeyReleased(evt);
            }
        });

        eosValorTotalProduto.setEditable(false);
        eosValorTotalProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        eosValorUNProduto.setEditable(false);
        eosValorUNProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        eosDescontoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        eosDescontoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eosDescontoProdutoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosValorTotalProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eosAddProduto)
                .addGap(35, 35, 35))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel95)
                        .addComponent(jLabel94)
                        .addComponent(eosDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eosValorTotalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel91)
                        .addComponent(eosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel92)
                        .addComponent(jLabel93)
                        .addComponent(eosAddProduto)
                        .addComponent(eosQuantProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eosValorUNProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Produtos / Serviços", jPanel24);

        javax.swing.GroupLayout editarOrdemdeServicoLayout = new javax.swing.GroupLayout(editarOrdemdeServico);
        editarOrdemdeServico.setLayout(editarOrdemdeServicoLayout);
        editarOrdemdeServicoLayout.setHorizontalGroup(
            editarOrdemdeServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        editarOrdemdeServicoLayout.setVerticalGroup(
            editarOrdemdeServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        painelFuncional.add(editarOrdemdeServico, "cardEditarOrdemdeServico");

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel98.setText("Finanças");
        jLabel98.setToolTipText("");

        javax.swing.GroupLayout header4Layout = new javax.swing.GroupLayout(header4);
        header4.setLayout(header4Layout);
        header4Layout.setHorizontalGroup(
            header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header4Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel98)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header4Layout.setVerticalGroup(
            header4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        fTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "compra de material", "Despesa", null, null, null},
                {null, "Venda", "Receita", null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Data", "Descrição", "Tipo", "Valor", "Editar", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fTable.setGridColor(new java.awt.Color(160, 160, 160));
        fTable.setRowHeight(40);
        fTable.setRowMargin(5);
        fTable.setRowSelectionAllowed(false);
        fTable.getTableHeader().setReorderingAllowed(false);
        scrollVendas4.setViewportView(fTable);
        if (fTable.getColumnModel().getColumnCount() > 0) {
            fTable.getColumnModel().getColumn(0).setResizable(false);
            fTable.getColumnModel().getColumn(1).setResizable(false);
            fTable.getColumnModel().getColumn(2).setResizable(false);
            fTable.getColumnModel().getColumn(2).setPreferredWidth(30);
            fTable.getColumnModel().getColumn(3).setResizable(false);
            fTable.getColumnModel().getColumn(4).setResizable(false);
            fTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            fTable.getColumnModel().getColumn(5).setResizable(false);
            fTable.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        spinFinancas.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));
        spinFinancas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinFinancasStateChanged(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel99.setText("Paginas");

        jButton29.setText("Novo Lançamento");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinFinancas, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinFinancas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel97.setText("Ano");

        fAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035" }));
        fAno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fAnoItemStateChanged(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setText("Mês");

        fMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fereveiro", "Março", "Abril", "Maio", "junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        fMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fMesItemStateChanged(evt);
            }
        });

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 204), null), "Anual"));

        jLabel150.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(0, 153, 51));
        jLabel150.setText("Receita");
        jLabel150.setPreferredSize(new java.awt.Dimension(46, 15));

        fTotalAnual.setText("0,00");
        fTotalAnual.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        fDespesaAnual.setText("0,00");
        fDespesaAnual.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel151.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(153, 51, 0));
        jLabel151.setText("Despesa");

        jLabel152.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel152.setText("TOTAL");

        fReceitaAnual.setText("0,00");
        fReceitaAnual.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fReceitaAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fDespesaAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel152)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fTotalAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fReceitaAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fDespesaAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fTotalAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null), "Mensal"));

        jLabel153.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(0, 153, 51));
        jLabel153.setText("Receita");
        jLabel153.setPreferredSize(new java.awt.Dimension(46, 15));

        fTotal.setText("0,00");
        fTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        fDespesa.setText("0,00");
        fDespesa.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel154.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(153, 51, 0));
        jLabel154.setText("Despesa");

        jLabel155.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel155.setText("TOTAL");

        fReceita.setText("0,00");
        fReceita.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel154)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel155)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout financasLayout = new javax.swing.GroupLayout(financas);
        financas.setLayout(financasLayout);
        financasLayout.setHorizontalGroup(
            financasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollVendas4, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(financasLayout.createSequentialGroup()
                .addGroup(financasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(financasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fAno, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fMes, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        financasLayout.setVerticalGroup(
            financasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financasLayout.createSequentialGroup()
                .addGroup(financasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(financasLayout.createSequentialGroup()
                        .addComponent(header4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(financasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fAno, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fMes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(scrollVendas4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelFuncional.add(financas, "cardFinancas");

        jLabel101.setText("Data");

        nlData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        nlData.setText("12/03/2021");

        jLabel102.setText("Descrição");

        nlDesc.setActionCommand("<Not Set>");

        jLabel103.setText("Valor");

        nlValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        nlValor.setText("0,00");

        jLabel104.setText("Tipo");

        nlTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Despesa", "Receita" }));

        nlAdd.setText("Criar Lançamento");
        nlAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nlAddActionPerformed(evt);
            }
        });

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel105.setText("Novo Laçamento");
        jLabel105.setToolTipText("");

        nlVoltar.setText("Voltar");
        nlVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nlVoltarActionPerformed(evt);
            }
        });

        nlFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Escolha uma opção >", "Dinheiro", "Debito", "Crédito", "Pix", "Transferência" }));

        jLabel145.setText("Forma de Pagamento");

        javax.swing.GroupLayout novoLancamentoLayout = new javax.swing.GroupLayout(novoLancamento);
        novoLancamento.setLayout(novoLancamentoLayout);
        novoLancamentoLayout.setHorizontalGroup(
            novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoLancamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(novoLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(novoLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nlTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(novoLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(novoLancamentoLayout.createSequentialGroup()
                                .addComponent(nlAdd)
                                .addGap(18, 18, 18)
                                .addComponent(nlVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(nlValor)))
                    .addGroup(novoLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nlData, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(novoLancamentoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel105))
                    .addGroup(novoLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel145)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nlFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(580, Short.MAX_VALUE))
        );
        novoLancamentoLayout.setVerticalGroup(
            novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(novoLancamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nlData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nlTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nlValor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nlFormaPagamento)
                    .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(novoLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nlAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nlVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        painelFuncional.add(novoLancamento, "cardNovoLancamento");

        jLabel106.setText("Data");

        elData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        elData.setText("12/03/2021");

        jLabel107.setText("Descrição");

        elDesc.setActionCommand("<Not Set>");

        jLabel108.setText("Valor");

        elValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        elValor.setText("0,00");

        jLabel109.setText("Tipo");

        elTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Despesa", "Receita" }));

        jButton31.setText("Salvar Alterações");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel110.setText("Editar Laçamento");
        jLabel110.setToolTipText("");

        jLabel146.setText("Forma de Pagamento");

        elFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Debito", "Crédito", "Pix", "Transferência" }));

        nlVoltar1.setText("Voltar");
        nlVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nlVoltar1ActionPerformed(evt);
            }
        });

        elId.setEditable(false);
        elId.setText("15015");
        elId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elIdActionPerformed(evt);
            }
        });

        jLabel10.setText("Número");

        javax.swing.GroupLayout editarLancamentoLayout = new javax.swing.GroupLayout(editarLancamento);
        editarLancamento.setLayout(editarLancamentoLayout);
        editarLancamentoLayout.setHorizontalGroup(
            editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarLancamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editarLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editarLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editarLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editarLancamentoLayout.createSequentialGroup()
                                .addComponent(jButton31)
                                .addGap(18, 18, 18)
                                .addComponent(nlVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(elValor)))
                    .addGroup(editarLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elData, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(elId))
                    .addGroup(editarLancamentoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel110))
                    .addGroup(editarLancamentoLayout.createSequentialGroup()
                        .addComponent(jLabel146)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(elFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(580, Short.MAX_VALUE))
        );
        editarLancamentoLayout.setVerticalGroup(
            editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarLancamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(elData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(elId, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(elTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elValor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(elFormaPagamento)
                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(editarLancamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nlVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        painelFuncional.add(editarLancamento, "cardEditarLancamento");

        javax.swing.GroupLayout configuracoesLayout = new javax.swing.GroupLayout(configuracoes);
        configuracoes.setLayout(configuracoesLayout);
        configuracoesLayout.setHorizontalGroup(
            configuracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1068, Short.MAX_VALUE)
        );
        configuracoesLayout.setVerticalGroup(
            configuracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        painelFuncional.add(configuracoes, "cardConfiguracoes");

        javax.swing.GroupLayout encerrarSessaoLayout = new javax.swing.GroupLayout(encerrarSessao);
        encerrarSessao.setLayout(encerrarSessaoLayout);
        encerrarSessaoLayout.setHorizontalGroup(
            encerrarSessaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1068, Short.MAX_VALUE)
        );
        encerrarSessaoLayout.setVerticalGroup(
            encerrarSessaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        painelFuncional.add(encerrarSessao, "cardEncerrarSessao");

        jTextField34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField34.setToolTipText("");

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel111.setText("Pesquisar");

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel112.setText("Clientes");
        jLabel112.setToolTipText("");

        javax.swing.GroupLayout header5Layout = new javax.swing.GroupLayout(header5);
        header5.setLayout(header5Layout);
        header5Layout.setHorizontalGroup(
            header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header5Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        header5Layout.setVerticalGroup(
            header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(header5Layout.createSequentialGroup()
                        .addGroup(header5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        tableClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Número", "Nome", "Data/Cadastro", "Telefone", "Cidade", "Editar", "Excluir"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClientes.setGridColor(new java.awt.Color(160, 160, 160));
        tableClientes.setRowHeight(40);
        tableClientes.setRowMargin(5);
        tableClientes.setRowSelectionAllowed(false);
        tableClientes.getTableHeader().setReorderingAllowed(false);
        scrollVendas5.setViewportView(tableClientes);
        if (tableClientes.getColumnModel().getColumnCount() > 0) {
            tableClientes.getColumnModel().getColumn(0).setResizable(false);
            tableClientes.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableClientes.getColumnModel().getColumn(1).setResizable(false);
            tableClientes.getColumnModel().getColumn(2).setResizable(false);
            tableClientes.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableClientes.getColumnModel().getColumn(3).setResizable(false);
            tableClientes.getColumnModel().getColumn(4).setResizable(false);
            tableClientes.getColumnModel().getColumn(5).setResizable(false);
            tableClientes.getColumnModel().getColumn(5).setPreferredWidth(30);
            tableClientes.getColumnModel().getColumn(6).setResizable(false);
            tableClientes.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        spinClientes.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));
        spinClientes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinClientesStateChanged(evt);
            }
        });

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel113.setText("Paginas");

        jButton32.setText("Novo Cliente");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout clientesLayout = new javax.swing.GroupLayout(clientes);
        clientes.setLayout(clientesLayout);
        clientesLayout.setHorizontalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollVendas5)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        clientesLayout.setVerticalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addComponent(header5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollVendas5, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        painelFuncional.add(clientes, "cardClientes");

        jLabel114.setText("Data/Cadastro");

        jLabel115.setText("Número");

        jLabel116.setText("Nome");

        ecIdCliente.setEditable(false);
        ecIdCliente.setText("15015");
        ecIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecIdClienteActionPerformed(evt);
            }
        });

        jButton33.setText("Salvar Alterações");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setText("Voltar");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        ecData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        ecData.setText("03/03/2021");

        jTextField37.setEditable(false);
        jTextField37.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jTextField37.setText("Editar Cliente");
        jTextField37.setOpaque(false);

        jLabel117.setText("Sexo");

        ecNome.setText("Ciclano");

        try {
            ecCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ecCPF.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel118.setText("CPF");

        jLabel119.setText("CEP");

        ecEmail.setText("Meu Serviço");

        try {
            ecCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ecCEP.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel120.setText("Estado");

        jLabel121.setText("Email");

        jLabel122.setText("Telefone");

        try {
            ecTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ecTelefone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel123.setText("Cidade");

        jLabel124.setText("Bairro");

        jLabel125.setText("Rua");

        jLabel126.setText("Número");

        ecBairro.setText("São Jose");

        ecCidade.setText("Passo Fundo");

        ecSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Escolha uma opção >", "Masculino", "Feminino" }));

        ecEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" }));

        ecRua.setText("Recife");

        ecNumero.setText("520");

        javax.swing.GroupLayout editarClienteLayout = new javax.swing.GroupLayout(editarCliente);
        editarCliente.setLayout(editarClienteLayout);
        editarClienteLayout.setHorizontalGroup(
            editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarClienteLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarClienteLayout.createSequentialGroup()
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel123)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarClienteLayout.createSequentialGroup()
                                .addComponent(jLabel125)
                                .addGap(43, 43, 43))
                            .addComponent(jLabel126))
                        .addGap(19, 19, 19)
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ecRua)
                            .addComponent(ecNumero, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ecBairro)
                            .addComponent(ecCidade, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(editarClienteLayout.createSequentialGroup()
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel122)
                            .addComponent(jLabel120)
                            .addComponent(jLabel119)
                            .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ecData, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ecIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addComponent(ecNome)
                            .addComponent(ecCPF)
                            .addComponent(ecTelefone)
                            .addComponent(ecEmail)
                            .addComponent(ecCEP)
                            .addComponent(ecSexo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ecEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(148, 148, 148)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editarClienteLayout.createSequentialGroup()
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(116, 116, 116))
        );
        editarClienteLayout.setVerticalGroup(
            editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarClienteLayout.createSequentialGroup()
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarClienteLayout.createSequentialGroup()
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ecIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ecData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ecCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ecTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(editarClienteLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ecNome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(ecSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(editarClienteLayout.createSequentialGroup()
                        .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editarClienteLayout.createSequentialGroup()
                        .addComponent(ecEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ecCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(ecEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ecCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ecBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ecRua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ecNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelFuncional.add(editarCliente, "cardEditarCliente");

        jLabel127.setText("Data/Cadastro");

        jLabel129.setText("Nome");

        jButton35.setText("Criar Cliente");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setText("Voltar");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        ncData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        ncData.setText("03/03/2021");

        jTextField44.setEditable(false);
        jTextField44.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jTextField44.setText("Novo Cliente");
        jTextField44.setOpaque(false);

        jLabel130.setText("Sexo");

        try {
            ncCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ncCPF.setText("");
        ncCPF.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel131.setText("CPF");

        jLabel132.setText("CEP");

        try {
            ncCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ncCEP.setText("");
        ncCEP.setCaretPosition(2);
        ncCEP.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel133.setText("Estado");

        jLabel134.setText("Email");

        jLabel135.setText("Telefone");

        try {
            ncTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ncTelefone.setText("");
        ncTelefone.setToolTipText("");
        ncTelefone.setCaretPosition(0);
        ncTelefone.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel136.setText("Cidade");

        jLabel137.setText("Bairro");

        jLabel138.setText("Rua");

        jLabel139.setText("Número");

        ncBairro.setText("São Jose");

        ncCidade.setText("Passo Fundo");

        ncSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Escolha uma opção >", "Masculino", "Feminino" }));

        ncEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< Escolha uma opção >", "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" }));

        ncRua.setText("Recife");

        ncNumero.setText("520");

        javax.swing.GroupLayout NovoClienteLayout = new javax.swing.GroupLayout(NovoCliente);
        NovoCliente.setLayout(NovoClienteLayout);
        NovoClienteLayout.setHorizontalGroup(
            NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NovoClienteLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NovoClienteLayout.createSequentialGroup()
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel136)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NovoClienteLayout.createSequentialGroup()
                                .addComponent(jLabel138)
                                .addGap(43, 43, 43))
                            .addComponent(jLabel139))
                        .addGap(19, 19, 19)
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ncRua)
                            .addComponent(ncNumero, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ncBairro)
                            .addComponent(ncCidade, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(NovoClienteLayout.createSequentialGroup()
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel135)
                            .addComponent(jLabel133)
                            .addComponent(jLabel132)
                            .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ncData, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ncNome)
                            .addComponent(ncCPF)
                            .addComponent(ncTelefone)
                            .addComponent(ncEmail)
                            .addComponent(ncCEP)
                            .addComponent(ncSexo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ncEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, 387, Short.MAX_VALUE))))
                .addGap(148, 148, 148)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NovoClienteLayout.createSequentialGroup()
                        .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(116, 116, 116))
        );
        NovoClienteLayout.setVerticalGroup(
            NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NovoClienteLayout.createSequentialGroup()
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NovoClienteLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ncData, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ncCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ncTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(NovoClienteLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ncNome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(ncSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(NovoClienteLayout.createSequentialGroup()
                        .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel132, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(NovoClienteLayout.createSequentialGroup()
                        .addComponent(ncEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ncCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel133, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(ncEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ncBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ncRua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NovoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ncNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelFuncional.add(NovoCliente, "cardNovoCliente");

        javax.swing.GroupLayout painelProgramaLayout = new javax.swing.GroupLayout(painelPrograma);
        painelPrograma.setLayout(painelProgramaLayout);
        painelProgramaLayout.setHorizontalGroup(
            painelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelProgramaLayout.createSequentialGroup()
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(painelFuncional, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        painelProgramaLayout.setVerticalGroup(
            painelProgramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(painelFuncional, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPrincipalLayout.createSequentialGroup()
                .addComponent(painelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(painelPrincipal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bFexarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFexarMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_bFexarMouseDragged

    private void bTelaInicialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bTelaInicialMouseEntered
        trocaCor(bTelaInicial, new Color(0, 66, 66));
    }//GEN-LAST:event_bTelaInicialMouseEntered

    private void bTelaInicialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bTelaInicialMouseExited
        trocaCor(bTelaInicial, new Color(0, 51, 51));
    }//GEN-LAST:event_bTelaInicialMouseExited

    private void bNovaVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bNovaVendaMouseEntered
        trocaCor(bNovaVenda, new Color(0, 66, 66));
    }//GEN-LAST:event_bNovaVendaMouseEntered

    private void bNovaVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bNovaVendaMouseExited
        trocaCor(bNovaVenda, new Color(0, 51, 51));
    }//GEN-LAST:event_bNovaVendaMouseExited

    private void bVendasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bVendasMouseEntered
        trocaCor(bVendas, new Color(0, 66, 66));
    }//GEN-LAST:event_bVendasMouseEntered

    private void bVendasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bVendasMouseExited
        trocaCor(bVendas, new Color(0, 51, 51));
    }//GEN-LAST:event_bVendasMouseExited

    private void bMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMinimizarMouseEntered
        trocaCor(bMinimizar, new Color(255,224,101));
    }//GEN-LAST:event_bMinimizarMouseEntered

    private void bFexarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFexarMouseEntered
        trocaCor(bFexar, new Color(255,101,101));
    }//GEN-LAST:event_bFexarMouseEntered

    private void bFexarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFexarMouseExited
        trocaCor(bFexar, new Color(240,240,240));
    }//GEN-LAST:event_bFexarMouseExited

    private void bFexarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFexarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_bFexarMouseClicked

    private void bMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMinimizarMouseExited
        trocaCor(bMinimizar, new Color(240,240,240));
    }//GEN-LAST:event_bMinimizarMouseExited

    private void bMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMinimizarMouseClicked
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_bMinimizarMouseClicked

    private void bProdutosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bProdutosMouseEntered
        trocaCor(bProdutos, new Color(0, 66, 66));
    }//GEN-LAST:event_bProdutosMouseEntered

    private void bProdutosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bProdutosMouseExited
        trocaCor(bProdutos, new Color(0, 51, 51));
    }//GEN-LAST:event_bProdutosMouseExited

    private void bServicosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bServicosMouseEntered
        trocaCor(bServicos, new Color(0, 66, 66));
    }//GEN-LAST:event_bServicosMouseEntered

    private void bServicosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bServicosMouseExited
        trocaCor(bServicos, new Color(0, 51, 51));
    }//GEN-LAST:event_bServicosMouseExited

    private void bOrdemdeServicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bOrdemdeServicoMouseEntered
        trocaCor(bOrdemdeServico, new Color(0, 66, 66));
    }//GEN-LAST:event_bOrdemdeServicoMouseEntered

    private void bOrdemdeServicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bOrdemdeServicoMouseExited
        trocaCor(bOrdemdeServico, new Color(0, 51, 51));
    }//GEN-LAST:event_bOrdemdeServicoMouseExited

    private void bFinancasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFinancasMouseEntered
        trocaCor(bFinancas, new Color(0, 66, 66));
    }//GEN-LAST:event_bFinancasMouseEntered

    private void bFinancasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFinancasMouseExited
        trocaCor(bFinancas, new Color(0, 51, 51));
    }//GEN-LAST:event_bFinancasMouseExited

    private void bConfiguracoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConfiguracoesMouseEntered
        trocaCor(bConfiguracoes, new Color(0, 66, 66));
    }//GEN-LAST:event_bConfiguracoesMouseEntered

    private void bConfiguracoesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConfiguracoesMouseExited
        trocaCor(bConfiguracoes, new Color(0, 51, 51));
    }//GEN-LAST:event_bConfiguracoesMouseExited

    private void bEncerrarSessaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bEncerrarSessaoMouseEntered
       trocaCor(bEncerrarSessao, new Color(0, 66, 66));
    }//GEN-LAST:event_bEncerrarSessaoMouseEntered

    private void bEncerrarSessaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bEncerrarSessaoMouseExited
        trocaCor(bEncerrarSessao, new Color(0, 51, 51));
    }//GEN-LAST:event_bEncerrarSessaoMouseExited

    private void bTelaInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bTelaInicialMouseClicked
        selecionarCard(painelFuncional, "card2");
        botaoClicado(bTelaInicial);
        bClicado = bTI;
    }//GEN-LAST:event_bTelaInicialMouseClicked

    private void bNovaVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bNovaVendaMouseClicked
        selecionarCard(painelFuncional, "cardNovaVenda");
        botaoClicado(bNovaVenda);
        bClicado = bNV;
        
        if(MotoZoom1.usuario.getNome().equalsIgnoreCase("Ernani"))
        {
            nvUsuario.setSelectedIndex(0);
        }
        else if(MotoZoom1.usuario.getNome().equalsIgnoreCase("Jakson"))
        {
            nvUsuario.setSelectedIndex(1);
        }
        else if(MotoZoom1.usuario.getNome().equalsIgnoreCase("Remoaldo"))
        {
            nvUsuario.setSelectedIndex(2);
        }
        
        nvData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        nvCliente.setText("");
        nvTelefone.setText("");
        nvFormaPagamento.setSelectedIndex(0);
        nvValorRecebido.setText("");
        nvTroco.setText("0,00");
        nvSubTotal.setText("0,00");
        nvDescontos.setText("0,00");
        nvTotal.setText("0,00");
        nvObservacoes.setText("");
        
        nvProduto.setText("");
        nvQuantProduto.setText("");
        nvValorUNProduto.setText("");
        nvDescontoProduto.setText("");
        nvValorTotalProduto.setText("");
        nvServico.setText("");
        nvQuantServico.setText("");
        nvValorUNServico.setText("");
        nvDescontoServico.setText("");
        nvValorTotalServico.setText("");
        
        ClienteOS = null;
        produtosOS.clear();
        servicosOS.clear();
        
        
        Utils.setTableOS_PS(nvTableNovaVenda, produtosOS, servicosOS);
    }//GEN-LAST:event_bNovaVendaMouseClicked

    private void bVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bVendasMouseClicked
        selecionarCard(painelFuncional, "cardVendas");
        botaoClicado(bVendas);bClicado = bV;
        Utils.setTable(tableVendas, Utils.TABLE_VENDAS, 0);
        spinVendas.setValue(1);
    }//GEN-LAST:event_bVendasMouseClicked

    private void bProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bProdutosMouseClicked
        selecionarCard(painelFuncional, "cardProdutos");
        botaoClicado(bProdutos);bClicado = bP;
        Utils.setTable(tableProdutos, Utils.TABLE_PRODUTOS, 0);
        spinProdutos.setValue(1);
    }//GEN-LAST:event_bProdutosMouseClicked

    private void bServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bServicosMouseClicked
        selecionarCard(painelFuncional, "cardServicos");
        botaoClicado(bServicos);bClicado = bS;
        Utils.setTable(tableServicos, Utils.TABLE_SERVICOS, 0);
        spinServicos.setValue(1);
    }//GEN-LAST:event_bServicosMouseClicked

    private void bOrdemdeServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bOrdemdeServicoMouseClicked
        selecionarCard(painelFuncional, "cardOrdemdeServico");
        botaoClicado(bOrdemdeServico);bClicado = bOS;
        spinOS.setValue(1);
        Utils.setTable(tableOS, Utils.TABLE_OS, ((int) spinOS.getValue()) - 1);
    }//GEN-LAST:event_bOrdemdeServicoMouseClicked

    private void bFinancasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFinancasMouseClicked
        selecionarCard(painelFuncional, "cardFinancas");
        botaoClicado(bFinancas);bClicado = bF;
        switch(new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis())))
        {
            case "2021":
                fAno.setSelectedIndex(0);
                break;
            case "2022":
                fAno.setSelectedIndex(1);
                break;
            case "2023":
                fAno.setSelectedIndex(2);
                break;
            case "2024":
                fAno.setSelectedIndex(3);
                break;
            case "2025":
                fAno.setSelectedIndex(4);
                break;
            case "2026":
                fAno.setSelectedIndex(5);
                break;
        }
        
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis()))) - 1;
        fMes.setSelectedIndex(mes);
        
        spinFinancas.setValue(1);
        Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        lancAnualUpdate();
    }//GEN-LAST:event_bFinancasMouseClicked

    private void bConfiguracoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConfiguracoesMouseClicked
        selecionarCard(painelFuncional, "cardConfiguracoes");
        botaoClicado(bConfiguracoes);bClicado = bC;
    }//GEN-LAST:event_bConfiguracoesMouseClicked

    private void bEncerrarSessaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bEncerrarSessaoMouseClicked
        selecionarCard(painelFuncional, "cardEncerrarSessao");
        botaoClicado(bEncerrarSessao);bClicado = bES;
    }//GEN-LAST:event_bEncerrarSessaoMouseClicked

    private void evIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_evIdActionPerformed

    private void evProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_evProdutoActionPerformed

    private void nvProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nvProdutoActionPerformed

    private void epIdProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epIdProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_epIdProdutoActionPerformed

    private void esIdServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esIdServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_esIdServicoActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        selecionarCard(painelFuncional, "cardNovaOrdemdeServico");
        
        nosCliente.setText("");
        nosClienteFone.setText("");
        nosData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        nosDescDefeito.setText("");
        nosDescVeiculo.setText("");
        nosObservacoes.setText("");
        nosPecaReposicao.setText("");
        nosValorTotal.setText("");
        
        nosProduto.setText("");
        nosQuantProduto.setText("");
        nosValorUNProduto.setText("");
        nosDescontoProduto.setText("");
        nosValorTotalProduto.setText("");
        nosServico.setText("");
        nosQuantServico.setText("");
        nosValorUNServico.setText("");
        nosDescontoServico.setText("");
        nosValorTotalServico.setText("");
        
        produtosOS.clear();
        servicosOS.clear();
        
        
        Utils.setTableOS_PS(nosTableProdutoServico, produtosOS, servicosOS);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void nosProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nosProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nosProdutoActionPerformed

    private void nosCriar_e_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nosCriar_e_ImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nosCriar_e_ImprimirActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void eosProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eosProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eosProdutoActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        selecionarCard(painelFuncional, "cardNovoLancamento");
        
        nlData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        nlDesc.setText("");
        nlTipo.setSelectedIndex(0);
        nlValor.setText("0,00");
        nlFormaPagamento.setSelectedIndex(0);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void nsNomeDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nsNomeDescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nsNomeDescActionPerformed

    private void bClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bClientesMouseClicked
        selecionarCard(painelFuncional, "cardClientes");
        spinClientes.setValue(1);
        Utils.setTable(tableClientes, Utils.TABLE_CLIENTES, ((int) spinClientes.getValue()) - 1);
    }//GEN-LAST:event_bClientesMouseClicked

    private void bClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bClientesMouseEntered
        trocaCor(bClientes, new Color(0, 66, 66));
    }//GEN-LAST:event_bClientesMouseEntered

    private void bClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bClientesMouseExited
        trocaCor(bClientes, new Color(0, 51, 51));
    }//GEN-LAST:event_bClientesMouseExited

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        selecionarCard(painelFuncional, "cardNovoCliente");
        
        ncData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        ncNome.setText("");
        ncSexo.setSelectedIndex(0);
        ncCPF.setText("");
        ncTelefone.setText("");
        ncEmail.setText("");
        
        ncCEP.setText("");
        ncEstado.setSelectedIndex(0);
        ncCidade.setText("");
        ncBairro.setText("");
        ncRua.setText("");
        ncNumero.setText("");
        
    }//GEN-LAST:event_jButton32ActionPerformed

    private void ecIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecIdClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ecIdClienteActionPerformed

    private void bNovoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bNovoProdutoMouseClicked
        selecionarCard(painelFuncional, "cardNovoProduto");
        npNomeDesc.setText("");
        npPrecoCompra.setText("");
        npPrecoVenda.setText("");
        npEstoque.setText("");
    }//GEN-LAST:event_bNovoProdutoMouseClicked

    private void bVoltarNovoProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bVoltarNovoProdutoMouseClicked
        selecionarCard(painelFuncional, "cardProdutos");
        Utils.setTable(tableProdutos, Utils.TABLE_PRODUTOS, ((int) spinServicos.getValue()) - 1);
    }//GEN-LAST:event_bVoltarNovoProdutoMouseClicked

    private void bVoltarNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVoltarNovoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bVoltarNovoProdutoActionPerformed

    private void bNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNovoProdutoActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        adicionaProduto();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void npPrecoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_npPrecoCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_npPrecoCompraActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        editaProduto();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        selecionarCard(painelFuncional, "cardProdutos");
        Utils.setTable(tableProdutos, Utils.TABLE_PRODUTOS, ((int) spinProdutos.getValue()) - 1);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void spinProdutosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinProdutosStateChanged
        Utils.setTable(tableProdutos, Utils.TABLE_PRODUTOS, ((int) spinProdutos.getValue()) - 1);
    }//GEN-LAST:event_spinProdutosStateChanged

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        selecionarCard(painelFuncional, "cardNovoServico");
        nsNomeDesc.setText("");
        nsPreco.setText("");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        selecionarCard(painelFuncional, "cardServicos");
        Utils.setTable(tableServicos, Utils.TABLE_SERVICOS, ((int) spinServicos.getValue()) - 1);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        adicionaServico();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        editaServico();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        selecionarCard(painelFuncional, "cardServicos");
        Utils.setTable(tableServicos, Utils.TABLE_SERVICOS, ((int) spinServicos.getValue()) - 1);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void spinServicosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinServicosStateChanged
        Utils.setTable(tableServicos, Utils.TABLE_SERVICOS, ((int) spinServicos.getValue()) - 1);
    }//GEN-LAST:event_spinServicosStateChanged

    private void spinClientesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinClientesStateChanged
        Utils.setTable(tableClientes, Utils.TABLE_CLIENTES, ((int) spinClientes.getValue()) - 1);
    }//GEN-LAST:event_spinClientesStateChanged

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        selecionarCard(painelFuncional, "cardClientes");
        Utils.setTable(tableClientes, Utils.TABLE_CLIENTES, ((int) spinClientes.getValue()) - 1);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        adicionaCliente();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        selecionarCard(painelFuncional, "cardClientes");
        Utils.setTable(tableClientes, Utils.TABLE_CLIENTES, ((int) spinClientes.getValue()) - 1);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        editaCliente();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void nosCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nosCriarActionPerformed
        adicionaOS();
    }//GEN-LAST:event_nosCriarActionPerformed

    private void nosAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nosAddProdutoActionPerformed
        if(Integer.parseInt(nosQuantProduto.getText()) < 1)
        {
            return;
        }
        
        produtosOS.add(new ProdutoValor(0,
                Integer.parseInt(nosQuantProduto.getText()),
                Double.parseDouble(Utils.formataValorEUA(nosDescontoProduto.getText())),
                Double.parseDouble(Utils.formataValorEUA(nosValorTotalProduto.getText())),
                produtoOS,
                produtoOS.getPrecoVenda()
        ));
        
        Utils.setTableOS_PS(nosTableProdutoServico, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_nosAddProdutoActionPerformed

    private void nosAddServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nosAddServicoActionPerformed
        if(Integer.parseInt(nosQuantServico.getText()) < 1)
        {
            return;
        }
        
        servicosOS.add(new ServicoValor(0,
                Integer.parseInt(nosQuantServico.getText()),
                Double.parseDouble(Utils.formataValorEUA(nosDescontoServico.getText())),
                Double.parseDouble(Utils.formataValorEUA(nosValorTotalServico.getText())),
                servicoOS,
                servicoOS.getPreco()
        ));
        
        Utils.setTableOS_PS(nosTableProdutoServico, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_nosAddServicoActionPerformed

    private void nosClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)
        {} else {
            nosClienteFone.setText("");
            ClienteOS = null;
        }
    }//GEN-LAST:event_nosClienteKeyPressed

    private void nosQuantProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosQuantProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(nosDescontoProduto.getText()));
        valtotal *= Double.parseDouble(nosQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(nosQuantProduto.getText()) : "0");
        nosValorTotalProduto.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_nosQuantProdutoKeyReleased

    private void nosDescontoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosDescontoProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(nosDescontoProduto.getText()));
        valtotal *= Double.parseDouble(nosQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(nosQuantProduto.getText()) : "0");
        nosValorTotalProduto.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_nosDescontoProdutoKeyReleased

    private void nosQuantServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosQuantServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(nosDescontoServico.getText()));
        valtotal *= Double.parseDouble(nosQuantServico.getText().length() != 0 ? Utils.formataValorEUA(nosQuantServico.getText()) : "0");
        nosValorTotalServico.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_nosQuantServicoKeyReleased

    private void nosDescontoServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosDescontoServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(nosDescontoServico.getText()));
        valtotal *= Double.parseDouble(nosQuantServico.getText().length() != 0 ? Utils.formataValorEUA(nosQuantServico.getText()) : "0");
        nosValorTotalServico.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_nosDescontoServicoKeyReleased

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        editaOrdemDeServico();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void eosAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eosAddProdutoActionPerformed
        if(Integer.parseInt(eosQuantProduto.getText()) < 1)
        {
            return;
        }
        
        produtosOS.add(new ProdutoValor(0,
                Integer.parseInt(eosQuantProduto.getText()),
                Double.parseDouble(Utils.formataValorEUA(eosDescontoProduto.getText())),
                Double.parseDouble(Utils.formataValorEUA(eosValorTotalProduto.getText())),
                produtoOS,
                produtoOS.getPrecoVenda()
        ));
        
        Utils.setTableOS_PS(eosTableProdutoServico, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_eosAddProdutoActionPerformed

    private void eosAddServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eosAddServicoActionPerformed
        if(Integer.parseInt(eosQuantServico.getText()) < 1)
        {
            return;
        }
        
        servicosOS.add(new ServicoValor(0,
                Integer.parseInt(eosQuantServico.getText()),
                Double.parseDouble(Utils.formataValorEUA(eosDescontoServico.getText())),
                Double.parseDouble(Utils.formataValorEUA(eosValorTotalServico.getText())),
                servicoOS,
                servicoOS.getPreco()
        ));
        
        Utils.setTableOS_PS(eosTableProdutoServico, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_eosAddServicoActionPerformed

    private void eosClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eosClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)
        {} else {
            eosClienteFone.setText("");
            ClienteOS = null;
        }
    }//GEN-LAST:event_eosClienteKeyPressed

    private void eosQuantProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eosQuantProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(eosDescontoProduto.getText()));
        valtotal *= Double.parseDouble(eosQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(eosQuantProduto.getText()) : "0");
        eosValorTotalProduto.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_eosQuantProdutoKeyReleased

    private void eosDescontoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eosDescontoProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(eosDescontoProduto.getText()));
        valtotal *= Double.parseDouble(eosQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(eosQuantProduto.getText()) : "0");
        eosValorTotalProduto.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_eosDescontoProdutoKeyReleased

    private void eosQuantServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eosQuantServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(eosDescontoServico.getText()));
        valtotal *= Double.parseDouble(eosQuantServico.getText().length() != 0 ? Utils.formataValorEUA(eosQuantServico.getText()) : "0");
        eosValorTotalServico.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_eosQuantServicoKeyReleased

    private void eosDescontoServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eosDescontoServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(eosDescontoServico.getText()));
        valtotal *= Double.parseDouble(eosQuantServico.getText().length() != 0 ? Utils.formataValorEUA(eosQuantServico.getText()) : "0");
        eosValorTotalServico.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_eosDescontoServicoKeyReleased

    private void eosVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eosVoltarActionPerformed
        selecionarCard(painelFuncional, "cardOrdemdeServico");
        Utils.setTable(tableOS, Utils.TABLE_OS, ((int) spinOS.getValue()) - 1);
    }//GEN-LAST:event_eosVoltarActionPerformed

    private void nosVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nosVoltarActionPerformed
        selecionarCard(painelFuncional, "cardOrdemdeServico");
        Utils.setTable(tableOS, Utils.TABLE_OS, ((int) spinOS.getValue()) - 1);
    }//GEN-LAST:event_nosVoltarActionPerformed

    private void nvFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvFinalizarVendaActionPerformed
        adicionaVenda();
    }//GEN-LAST:event_nvFinalizarVendaActionPerformed

    private void nvQuantProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvQuantProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(nvDescontoProduto.getText()));
        valtotal *= Double.parseDouble(nvQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(nvQuantProduto.getText()) : "0");
        nvValorTotalProduto.setText(String.format("%,.2f", valtotal));
        
        double valDesconto = Double.parseDouble(Utils.formataValorEUA(nvDescontoProduto.getText()));
        valDesconto *= Double.parseDouble(nvQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(nvQuantProduto.getText()) : "0");
        nvDescontoProdutoTotal.setText(String.format("%,.2f", valDesconto));
    }//GEN-LAST:event_nvQuantProdutoKeyReleased

    private void nvDescontoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvDescontoProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(nvDescontoProduto.getText()));
        valtotal *= Double.parseDouble(nvQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(nvQuantProduto.getText()) : "0");
        nvValorTotalProduto.setText(String.format("%,.2f", valtotal));
        
        double valDesconto = Double.parseDouble(Utils.formataValorEUA(nvDescontoProduto.getText()));
        valDesconto *= Double.parseDouble(nvQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(nvQuantProduto.getText()) : "0");
        nvDescontoProdutoTotal.setText(String.format("%,.2f", valDesconto));
    }//GEN-LAST:event_nvDescontoProdutoKeyReleased

    private void nvQuantServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvQuantServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(nvDescontoServico.getText()));
        valtotal *= Double.parseDouble(nvQuantServico.getText().length() != 0 ? Utils.formataValorEUA(nvQuantServico.getText()) : "0");
        nvValorTotalServico.setText(String.format("%,.2f", valtotal));
        
        double valDesconto = Double.parseDouble(Utils.formataValorEUA(nvDescontoServico.getText()));
        valDesconto *= Double.parseDouble(nvQuantServico.getText().length() != 0 ? Utils.formataValorEUA(nvQuantServico.getText()) : "0");
        nvDescontoServicoTotal.setText(String.format("%,.2f", valDesconto));
    }//GEN-LAST:event_nvQuantServicoKeyReleased

    private void nvDescontoServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvDescontoServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(nvDescontoServico.getText()));
        valtotal *= Double.parseDouble(nvQuantServico.getText().length() != 0 ? Utils.formataValorEUA(nvQuantServico.getText()) : "0");
        nvValorTotalServico.setText(String.format("%,.2f", valtotal));
        
        double valDesconto = Double.parseDouble(Utils.formataValorEUA(nvDescontoServico.getText()));
        valDesconto *= Double.parseDouble(nvQuantServico.getText().length() != 0 ? Utils.formataValorEUA(nvQuantServico.getText()) : "0");
        nvDescontoServicoTotal.setText(String.format("%,.2f", valDesconto));
    }//GEN-LAST:event_nvDescontoServicoKeyReleased

    private void nvAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvAddProdutoActionPerformed
        if(Integer.parseInt(nvQuantProduto.getText()) < 1)
        {
            return;
        }
        
        produtosOS.add(new ProdutoValor(0,
                Integer.parseInt(nvQuantProduto.getText()),
                Double.parseDouble(Utils.formataValorEUA(nvDescontoProduto.getText())),
                Double.parseDouble(Utils.formataValorEUA(nvValorTotalProduto.getText())),
                produtoOS,
                produtoOS.getPrecoVenda()
        ));
        
        Utils.setTableOS_PS(nvTableNovaVenda, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_nvAddProdutoActionPerformed

    private void nvAddServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvAddServicoActionPerformed
        if(Integer.parseInt(nvQuantServico.getText()) < 1)
        {
            return;
        }
        
        servicosOS.add(new ServicoValor(0,
                Integer.parseInt(nvQuantServico.getText()),
                Double.parseDouble(Utils.formataValorEUA(nvDescontoServico.getText())),
                Double.parseDouble(Utils.formataValorEUA(nvValorTotalServico.getText())),
                servicoOS,
                servicoOS.getPreco()
        ));
        
        Utils.setTableOS_PS(nvTableNovaVenda, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_nvAddServicoActionPerformed

    private void nvValorRecebidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvValorRecebidoKeyReleased
        double valRecebido = Double.parseDouble(Utils.formataValorEUA(nvValorRecebido.getText()));
        double valTotal = Double.parseDouble(Utils.formataValorEUA(nvTotal.getText()));
        nvTroco.setText(String.format("%,.2f", (valRecebido - valTotal)));
    }//GEN-LAST:event_nvValorRecebidoKeyReleased

    private void evValorRecebidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evValorRecebidoKeyReleased
        double valRecebido = Double.parseDouble(Utils.formataValorEUA(evValorRecebido.getText()));
        double valTotal = Double.parseDouble(Utils.formataValorEUA(evTotal.getText()));
        evTroco.setText(String.format("%,.2f", (valRecebido - valTotal)));
    }//GEN-LAST:event_evValorRecebidoKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        selecionarCard(painelFuncional, "cardVendas");
        Utils.setTable(tableVendas, Utils.TABLE_VENDAS, ((int) spinVendas.getValue()) - 1);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void evUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_evUsuarioActionPerformed

    private void evAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evAddProdutoActionPerformed
        if(Integer.parseInt(evQuantProduto.getText()) < 1)
        {
            return;
        }
        
        produtosOS.add(new ProdutoValor(0,
                Integer.parseInt(evQuantProduto.getText()),
                Double.parseDouble(Utils.formataValorEUA(evDescontoProduto.getText())),
                Double.parseDouble(Utils.formataValorEUA(evValorTotalProduto.getText())),
                produtoOS,
                produtoOS.getPrecoVenda()
        ));
        
        Utils.setTableOS_PS(evTableProdutoServico, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_evAddProdutoActionPerformed

    private void evAddServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evAddServicoActionPerformed
        if(Integer.parseInt(evQuantServico.getText()) < 1)
        {
            return;
        }
        
        servicosOS.add(new ServicoValor(0,
                Integer.parseInt(evQuantServico.getText()),
                Double.parseDouble(Utils.formataValorEUA(evDescontoServico.getText())),
                Double.parseDouble(Utils.formataValorEUA(evValorTotalServico.getText())),
                servicoOS,
                servicoOS.getPreco()
        ));
        
        Utils.setTableOS_PS(evTableProdutoServico, produtosOS, servicosOS);
        
        calculaValorOS();
    }//GEN-LAST:event_evAddServicoActionPerformed

    private void evQuantProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evQuantProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(evDescontoProduto.getText()));
        valtotal *= Double.parseDouble(evQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(evQuantProduto.getText()) : "0");
        evValorTotalProduto.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_evQuantProdutoKeyReleased

    private void evDescontoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evDescontoProdutoKeyReleased
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(evDescontoProduto.getText()));
        valtotal *= Double.parseDouble(evQuantProduto.getText().length() != 0 ? Utils.formataValorEUA(evQuantProduto.getText()) : "0");
        evValorTotalProduto.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_evDescontoProdutoKeyReleased

    private void evQuantServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evQuantServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(evDescontoServico.getText()));
        valtotal *= Double.parseDouble(evQuantServico.getText().length() != 0 ? Utils.formataValorEUA(evQuantServico.getText()) : "0");
        evValorTotalServico.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_evQuantServicoKeyReleased

    private void evDescontoServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evDescontoServicoKeyReleased
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(evDescontoServico.getText()));
        valtotal *= Double.parseDouble(evQuantServico.getText().length() != 0 ? Utils.formataValorEUA(evQuantServico.getText()) : "0");
        evValorTotalServico.setText(String.format("%,.2f", valtotal));
    }//GEN-LAST:event_evDescontoServicoKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        editaVenda();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void nlVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nlVoltarActionPerformed
        selecionarCard(painelFuncional, "cardFinancas");
        Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
    }//GEN-LAST:event_nlVoltarActionPerformed

    private void nlAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nlAddActionPerformed
        adicionaLancamento();
    }//GEN-LAST:event_nlAddActionPerformed

    private void nlVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nlVoltar1ActionPerformed
        selecionarCard(painelFuncional, "cardFinancas");
        List<Lancamento> lancamentos = Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        selecionarCard(painelFuncional, "cardFinancas");
        if(lancamentos == null) {
            fReceita.setText("0,00");
            fDespesa.setText("0,00");
            fTotal.setText("0,00");
            lancAnualUpdate();
            return;
        }
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento lanc : lancamentos) {
            if(lanc.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += lanc.getValor();
            } else {
                despesa += lanc.getValor();
            }
        }
        
        fReceita.setText(String.format("%,.2f", receita));
        fDespesa.setText(String.format("%,.2f", despesa));
        fTotal.setText(String.format("%,.2f", (receita - despesa)));
        
        lancAnualUpdate();
    }//GEN-LAST:event_nlVoltar1ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        editaFinanca();
    }//GEN-LAST:event_jButton31ActionPerformed

    private void elIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_elIdActionPerformed

    private void spinVendasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinVendasStateChanged
        Utils.setTable(tableVendas, Utils.TABLE_VENDAS, ((int) spinVendas.getValue()) - 1);
    }//GEN-LAST:event_spinVendasStateChanged

    private void spinOSStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinOSStateChanged
        Utils.setTable(tableOS, Utils.TABLE_OS, ((int) spinOS.getValue()) - 1);
    }//GEN-LAST:event_spinOSStateChanged

    private void spinFinancasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinFinancasStateChanged
        List<Lancamento> lancamentos = Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        selecionarCard(painelFuncional, "cardFinancas");
        if(lancamentos == null) {
            fReceita.setText("0,00");
            fDespesa.setText("0,00");
            fTotal.setText("0,00");
            return;
        }
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento lanc : lancamentos) {
            if(lanc.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += lanc.getValor();
            } else {
                despesa += lanc.getValor();
            }
        }
        
        fReceita.setText(String.format("%,.2f", receita));
        fDespesa.setText(String.format("%,.2f", despesa));
        fTotal.setText(String.format("%,.2f", (receita - despesa)));
    }//GEN-LAST:event_spinFinancasStateChanged

    private void fAnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fAnoItemStateChanged
        List<Lancamento> lancamentos = Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        
        selecionarCard(painelFuncional, "cardFinancas");
        
        //Resultados mensal
        if(lancamentos == null) {
            fReceita.setText("0,00");
            fDespesa.setText("0,00");
            fTotal.setText("0,00");
            lancAnualUpdate();
            return;
        }
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento lanc : lancamentos) {
            if(lanc.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += lanc.getValor();
            } else {
                despesa += lanc.getValor();
            }
        }
        
        fReceita.setText(String.format("%,.2f", receita));
        fDespesa.setText(String.format("%,.2f", despesa));
        fTotal.setText(String.format("%,.2f", (receita - despesa)));
        
        lancAnualUpdate();
    }//GEN-LAST:event_fAnoItemStateChanged

    private void fMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fMesItemStateChanged
        List<Lancamento> lancamentos = Utils.setTable(fTable, Utils.TABLE_FINANCAS, ((int) spinFinancas.getValue()) - 1, Integer.parseInt(fAno.getItemAt(fAno.getSelectedIndex())), fMes.getSelectedIndex() + 1);
        selecionarCard(painelFuncional, "cardFinancas");
        if(lancamentos == null) {
            fReceita.setText("0,00");
            fDespesa.setText("0,00");
            fTotal.setText("0,00");
            return;
        }
        double receita = 0;
        double despesa = 0;
        
        for(Lancamento lanc : lancamentos) {
            if(lanc.getTipo().equalsIgnoreCase("Receita"))
            {
                receita += lanc.getValor();
            } else {
                despesa += lanc.getValor();
            }
        }
        
        fReceita.setText(String.format("%,.2f", receita));
        fDespesa.setText(String.format("%,.2f", despesa));
        fTotal.setText(String.format("%,.2f", (receita - despesa)));
    }//GEN-LAST:event_fMesItemStateChanged

    private void nvClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)
        {} else {
            nvTelefone.setText("");
            ClienteOS = null;
        }
    }//GEN-LAST:event_nvClienteKeyPressed

    private void evClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_evClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)
        {} else {
            evTelefone.setText("");
            ClienteOS = null;
        }
    }//GEN-LAST:event_evClienteKeyPressed

    private void eosPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eosPagarActionPerformed
        eosStatus.setSelectedIndex(3);
        ordemDS = editaOrdemDeServico();
        if(ordemDS == null)
        {
            return;
        }
        
        selecionarCard(painelFuncional, "cardNovaVenda");
        botaoClicado(bNovaVenda);
        bClicado = bNV;
        
        if(MotoZoom1.usuario.getNome().equalsIgnoreCase("Ernani"))
        {
            nvUsuario.setSelectedIndex(0);
        }
        else if(MotoZoom1.usuario.getNome().equalsIgnoreCase("Jakson"))
        {
            nvUsuario.setSelectedIndex(1);
        }
        else if(MotoZoom1.usuario.getNome().equalsIgnoreCase("Remoaldo"))
        {
            nvUsuario.setSelectedIndex(2);
        }
        
        nvData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        nvCliente.setText(eosCliente.getText());
        nvTelefone.setText(eosClienteFone.getText());
        nvFormaPagamento.setSelectedIndex(0);
        nvValorRecebido.setText("");
        nvTroco.setText("0,00");
        nvSubTotal.setText("0,00");
        nvDescontos.setText("0,00");
        nvTotal.setText("0,00");
        calculaValorOS();
        nvObservacoes.setText(eosObservacoes.getText());
        
        nvProduto.setText("");
        nvQuantProduto.setText("");
        nvValorUNProduto.setText("");
        nvDescontoProduto.setText("");
        nvValorTotalProduto.setText("");
        nvServico.setText("");
        nvQuantServico.setText("");
        nvValorUNServico.setText("");
        nvDescontoServico.setText("");
        nvValorTotalServico.setText("");
        
        
        Utils.setTableOS_PS(nvTableNovaVenda, produtosOS, servicosOS);
    }//GEN-LAST:event_eosPagarActionPerformed

    private void nvDescontoServicoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvDescontoServicoTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nvDescontoServicoTotalKeyReleased

    private void nvDescontoProdutoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvDescontoProdutoTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nvDescontoProdutoTotalKeyReleased

    
    public void selecionaClienteOS(String name) {
        String n = name.replaceAll(" : ", ":");
        String[] nm = n.split(":");
        Cliente cl = SQLQuery.getClientes(nm[0], 0).get(0);
        ClienteOS = cl;
        
        nosCliente.setText(cl.getNome());
        nosClienteFone.setText(cl.getTelefone());
        
        eosCliente.setText(cl.getNome());
        eosClienteFone.setText(cl.getTelefone());
        nvCliente.setText(cl.getNome());
        nvTelefone.setText(cl.getTelefone());
        evCliente.setText(cl.getNome());
        evTelefone.setText(cl.getTelefone());
    }
    
    public void selecionaProdutoOS(String name) {
        produtoOS = SQLQuery.getProdutos(name, 0).get(0);
        
        nosProduto.setText(produtoOS.getDescricao());
        nosQuantProduto.setEditable(true);
        eosProduto.setText(produtoOS.getDescricao());
        eosQuantProduto.setEditable(true);
        
        nvProduto.setText(produtoOS.getDescricao());
        nvQuantProduto.setEditable(true);
        evProduto.setText(produtoOS.getDescricao());
        evQuantProduto.setEditable(true);
        if(produtoOS.getEstoque() < 1)
        {
            nosQuantProduto.setText("0");
            nosQuantProduto.setEditable(false);
            eosQuantProduto.setText("0");
            eosQuantProduto.setEditable(false);
            
            nvQuantProduto.setText("0");
            nvQuantProduto.setEditable(false);
            evQuantProduto.setText("0");
            evQuantProduto.setEditable(false);
        }
        nosQuantProduto.setText("1");
        nosValorUNProduto.setText(String.format("%,.2f", produtoOS.getPrecoVenda()));
        nosDescontoProduto.setText("0");
        eosQuantProduto.setText("1");
        eosValorUNProduto.setText(String.format("%,.2f", produtoOS.getPrecoVenda()));
        eosDescontoProduto.setText("0");
        
        nvQuantProduto.setText("1");
        nvValorUNProduto.setText(String.format("%,.2f", produtoOS.getPrecoVenda()));
        nvDescontoProduto.setText("0");
        evQuantProduto.setText("1");
        evValorUNProduto.setText(String.format("%,.2f", produtoOS.getPrecoVenda()));
        evDescontoProduto.setText("0");
        
        double valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(nosDescontoProduto.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(nosQuantProduto.getText()));
        nosValorTotalProduto.setText(String.format("%,.2f", valtotal));
        
        valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(eosDescontoProduto.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(eosQuantProduto.getText()));
        eosValorTotalProduto.setText(String.format("%,.2f", valtotal));
        
        
        valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(nvDescontoProduto.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(nvQuantProduto.getText()));
        nvValorTotalProduto.setText(String.format("%,.2f", valtotal));
        
        valtotal = produtoOS.getPrecoVenda() - Double.parseDouble(Utils.formataValorEUA(evDescontoProduto.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(evQuantProduto.getText()));
        evValorTotalProduto.setText(String.format("%,.2f", valtotal));
    }
    
    public void selecionaServicoOS(String name) {
        servicoOS = SQLQuery.getServicos(name, 0).get(0);
        
        nosServico.setText(servicoOS.getDescricao());
        nosQuantServico.setEditable(true);
        nosQuantServico.setText("1");
        nosValorUNServico.setText(String.format("%,.2f", servicoOS.getPreco()));
        nosDescontoServico.setText("0");
        
        double valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(nosDescontoServico.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(nosQuantServico.getText()));
        nosValorTotalServico.setText(String.format("%,.2f", valtotal));
        
        eosServico.setText(servicoOS.getDescricao());
        eosQuantServico.setEditable(true);
        eosQuantServico.setText("1");
        eosValorUNServico.setText(String.format("%,.2f", servicoOS.getPreco()));
        eosDescontoServico.setText("0");
        
        valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(eosDescontoServico.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(eosQuantServico.getText()));
        eosValorTotalServico.setText(String.format("%,.2f", valtotal));
        
        
        nvServico.setText(servicoOS.getDescricao());
        nvQuantServico.setEditable(true);
        nvQuantServico.setText("1");
        nvValorUNServico.setText(String.format("%,.2f", servicoOS.getPreco()));
        nvDescontoServico.setText("0");
        
        valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(nvDescontoServico.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(nvQuantServico.getText()));
        nvValorTotalServico.setText(String.format("%,.2f", valtotal));
                
        evServico.setText(servicoOS.getDescricao());
        evQuantServico.setEditable(true);
        evQuantServico.setText("1");
        evValorUNServico.setText(String.format("%,.2f", servicoOS.getPreco()));
        evDescontoServico.setText("0");
        
        valtotal = servicoOS.getPreco() - Double.parseDouble(Utils.formataValorEUA(evDescontoServico.getText()));
        valtotal *= Double.parseDouble(Utils.formataValorEUA(evQuantServico.getText()));
        evValorTotalServico.setText(String.format("%,.2f", valtotal));
    }
    
    
    
    /**
     * @param args the command line argumentsd
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new mainFrame().setUndecorated(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NovoCliente;
    private javax.swing.JLabel bClientes;
    private javax.swing.JLabel bConfiguracoes;
    private javax.swing.JLabel bEncerrarSessao;
    private javax.swing.JLabel bFexar;
    private javax.swing.JLabel bFinancas;
    private javax.swing.JLabel bMinimizar;
    private javax.swing.JLabel bNovaVenda;
    private javax.swing.JButton bNovoProduto;
    private javax.swing.JLabel bOrdemdeServico;
    private javax.swing.JLabel bProdutos;
    private javax.swing.JLabel bServicos;
    private javax.swing.JLabel bTelaInicial;
    private javax.swing.JLabel bVendas;
    private javax.swing.JButton bVoltarNovoProduto;
    private javax.swing.JPanel clientes;
    private javax.swing.JPanel configuracoes;
    private javax.swing.JTextField ecBairro;
    private javax.swing.JFormattedTextField ecCEP;
    private javax.swing.JFormattedTextField ecCPF;
    private javax.swing.JTextField ecCidade;
    private javax.swing.JFormattedTextField ecData;
    private javax.swing.JTextField ecEmail;
    private javax.swing.JComboBox<String> ecEstado;
    private javax.swing.JTextField ecIdCliente;
    private javax.swing.JTextField ecNome;
    private javax.swing.JTextField ecNumero;
    private javax.swing.JTextField ecRua;
    private javax.swing.JComboBox<String> ecSexo;
    private javax.swing.JFormattedTextField ecTelefone;
    private javax.swing.JPanel editarCliente;
    private javax.swing.JPanel editarLancamento;
    private javax.swing.JPanel editarOrdemdeServico;
    private javax.swing.JPanel editarProduto;
    private javax.swing.JPanel editarServico;
    private javax.swing.JPanel editarVenda;
    private javax.swing.JFormattedTextField elData;
    private javax.swing.JTextField elDesc;
    private javax.swing.JComboBox<String> elFormaPagamento;
    private javax.swing.JTextField elId;
    private javax.swing.JComboBox<String> elTipo;
    private javax.swing.JFormattedTextField elValor;
    private javax.swing.JPanel encerrarSessao;
    private javax.swing.JButton eosAddProduto;
    private javax.swing.JButton eosAddServico;
    private javax.swing.JTextField eosCliente;
    private javax.swing.JFormattedTextField eosClienteFone;
    private javax.swing.JFormattedTextField eosData;
    private javax.swing.JTextArea eosDescDefeito;
    private javax.swing.JTextField eosDescVeiculo;
    private javax.swing.JFormattedTextField eosDescontoProduto;
    private javax.swing.JFormattedTextField eosDescontoServico;
    private javax.swing.JTextField eosIdOrdem;
    private javax.swing.JTextField eosIdVenda;
    private javax.swing.JTextArea eosObservacoes;
    private javax.swing.JButton eosPagar;
    private javax.swing.JTextArea eosPecaReposicao;
    private javax.swing.JTextField eosProduto;
    private javax.swing.JFormattedTextField eosQuantProduto;
    private javax.swing.JFormattedTextField eosQuantServico;
    private javax.swing.JTextField eosServico;
    private javax.swing.JComboBox<String> eosStatus;
    private javax.swing.JTable eosTableProdutoServico;
    private javax.swing.JTextField eosValorTotal;
    private javax.swing.JFormattedTextField eosValorTotalProduto;
    private javax.swing.JFormattedTextField eosValorTotalServico;
    private javax.swing.JFormattedTextField eosValorUNProduto;
    private javax.swing.JFormattedTextField eosValorUNServico;
    private javax.swing.JButton eosVoltar;
    private javax.swing.JFormattedTextField epEstoque;
    private javax.swing.JTextField epIdProduto;
    private javax.swing.JTextField epNomeDesc;
    private javax.swing.JFormattedTextField epPrecoCompra;
    private javax.swing.JFormattedTextField epPrecoVenda;
    private javax.swing.JTextField esIdServico;
    private javax.swing.JTextField esNomeDesc;
    private javax.swing.JFormattedTextField esPreco;
    private javax.swing.JButton evAddProduto;
    private javax.swing.JButton evAddServico;
    private javax.swing.JTextField evCliente;
    private javax.swing.JFormattedTextField evData;
    private javax.swing.JFormattedTextField evDescontoProduto;
    private javax.swing.JFormattedTextField evDescontoServico;
    private javax.swing.JTextField evDescontos;
    private javax.swing.JComboBox<String> evFormaPagamento;
    private javax.swing.JTextField evId;
    private javax.swing.JTextArea evObservacoes;
    private javax.swing.JTextField evProduto;
    private javax.swing.JFormattedTextField evQuantProduto;
    private javax.swing.JFormattedTextField evQuantServico;
    private javax.swing.JTextField evServico;
    private javax.swing.JTextField evSubTotal;
    private javax.swing.JTable evTableProdutoServico;
    private javax.swing.JFormattedTextField evTelefone;
    private javax.swing.JTextField evTotal;
    private javax.swing.JTextField evTroco;
    private javax.swing.JTextField evUsuario;
    private javax.swing.JFormattedTextField evValorRecebido;
    private javax.swing.JFormattedTextField evValorTotalProduto;
    private javax.swing.JFormattedTextField evValorTotalServico;
    private javax.swing.JFormattedTextField evValorUNProduto;
    private javax.swing.JFormattedTextField evValorUNServico;
    private javax.swing.JComboBox<String> fAno;
    private javax.swing.JLabel fDespesa;
    private javax.swing.JLabel fDespesaAnual;
    private javax.swing.JComboBox<String> fMes;
    private javax.swing.JLabel fReceita;
    private javax.swing.JLabel fReceitaAnual;
    private javax.swing.JTable fTable;
    private javax.swing.JLabel fTotal;
    private javax.swing.JLabel fTotalAnual;
    private javax.swing.JPanel financas;
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JPanel header2;
    private javax.swing.JPanel header3;
    private javax.swing.JPanel header4;
    private javax.swing.JPanel header5;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JLabel lbUser;
    private javax.swing.JTextField ncBairro;
    private javax.swing.JFormattedTextField ncCEP;
    private javax.swing.JFormattedTextField ncCPF;
    private javax.swing.JTextField ncCidade;
    private javax.swing.JFormattedTextField ncData;
    private javax.swing.JTextField ncEmail;
    private javax.swing.JComboBox<String> ncEstado;
    private javax.swing.JTextField ncNome;
    private javax.swing.JTextField ncNumero;
    private javax.swing.JTextField ncRua;
    private javax.swing.JComboBox<String> ncSexo;
    private javax.swing.JFormattedTextField ncTelefone;
    private javax.swing.JButton nlAdd;
    private javax.swing.JFormattedTextField nlData;
    private javax.swing.JTextField nlDesc;
    private javax.swing.JComboBox<String> nlFormaPagamento;
    private javax.swing.JComboBox<String> nlTipo;
    private javax.swing.JFormattedTextField nlValor;
    private javax.swing.JButton nlVoltar;
    private javax.swing.JButton nlVoltar1;
    private javax.swing.JButton nosAddProduto;
    private javax.swing.JButton nosAddServico;
    private javax.swing.JTextField nosCliente;
    private javax.swing.JFormattedTextField nosClienteFone;
    private javax.swing.JButton nosCriar;
    private javax.swing.JButton nosCriar_e_Imprimir;
    private javax.swing.JFormattedTextField nosData;
    private javax.swing.JTextArea nosDescDefeito;
    private javax.swing.JTextField nosDescVeiculo;
    private javax.swing.JFormattedTextField nosDescontoProduto;
    private javax.swing.JFormattedTextField nosDescontoServico;
    private javax.swing.JTextArea nosObservacoes;
    private javax.swing.JTextArea nosPecaReposicao;
    private javax.swing.JTextField nosProduto;
    private javax.swing.JFormattedTextField nosQuantProduto;
    private javax.swing.JFormattedTextField nosQuantServico;
    private javax.swing.JTextField nosServico;
    private javax.swing.JTable nosTableProdutoServico;
    private javax.swing.JTextField nosValorTotal;
    private javax.swing.JFormattedTextField nosValorTotalProduto;
    private javax.swing.JFormattedTextField nosValorTotalServico;
    private javax.swing.JFormattedTextField nosValorUNProduto;
    private javax.swing.JFormattedTextField nosValorUNServico;
    private javax.swing.JButton nosVoltar;
    private javax.swing.JPanel novaOrdemdeServico;
    private javax.swing.JPanel novaVenda;
    private javax.swing.JPanel novoLancamento;
    private javax.swing.JPanel novoProduto;
    private javax.swing.JPanel novoServico;
    private javax.swing.JFormattedTextField npEstoque;
    private javax.swing.JTextField npNomeDesc;
    private javax.swing.JFormattedTextField npPrecoCompra;
    private javax.swing.JFormattedTextField npPrecoVenda;
    private javax.swing.JTextField nsNomeDesc;
    private javax.swing.JFormattedTextField nsPreco;
    private javax.swing.JButton nvAddProduto;
    private javax.swing.JButton nvAddServico;
    private javax.swing.JTextField nvCliente;
    private javax.swing.JFormattedTextField nvData;
    private javax.swing.JFormattedTextField nvDescontoProduto;
    private javax.swing.JFormattedTextField nvDescontoProdutoTotal;
    private javax.swing.JFormattedTextField nvDescontoServico;
    private javax.swing.JFormattedTextField nvDescontoServicoTotal;
    private javax.swing.JTextField nvDescontos;
    private javax.swing.JButton nvFinalizarVenda;
    private javax.swing.JComboBox<String> nvFormaPagamento;
    private javax.swing.JTextArea nvObservacoes;
    private javax.swing.JTextField nvProduto;
    private javax.swing.JFormattedTextField nvQuantProduto;
    private javax.swing.JFormattedTextField nvQuantServico;
    private javax.swing.JTextField nvServico;
    private javax.swing.JTextField nvSubTotal;
    private javax.swing.JTable nvTableNovaVenda;
    private javax.swing.JFormattedTextField nvTelefone;
    private javax.swing.JTextField nvTotal;
    private javax.swing.JTextField nvTroco;
    private javax.swing.JComboBox<String> nvUsuario;
    private javax.swing.JFormattedTextField nvValorRecebido;
    private javax.swing.JFormattedTextField nvValorTotalProduto;
    private javax.swing.JFormattedTextField nvValorTotalServico;
    private javax.swing.JFormattedTextField nvValorUNProduto;
    private javax.swing.JFormattedTextField nvValorUNServico;
    private javax.swing.JPanel ordemdeServico;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelFuncional;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelPrograma;
    private javax.swing.JPanel painelTitulo;
    private javax.swing.JPanel produtos;
    private javax.swing.JScrollPane scrollVendas;
    private javax.swing.JScrollPane scrollVendas1;
    private javax.swing.JScrollPane scrollVendas2;
    private javax.swing.JScrollPane scrollVendas3;
    private javax.swing.JScrollPane scrollVendas4;
    private javax.swing.JScrollPane scrollVendas5;
    private javax.swing.JPanel servicos;
    private javax.swing.JSpinner spinClientes;
    private javax.swing.JSpinner spinFinancas;
    private javax.swing.JSpinner spinOS;
    private javax.swing.JSpinner spinProdutos;
    private javax.swing.JSpinner spinServicos;
    private javax.swing.JSpinner spinVendas;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTable tableOS;
    private javax.swing.JTable tableProdutos;
    private javax.swing.JTable tableServicos;
    private javax.swing.JTable tableVendas;
    private javax.swing.JPanel vendas;
    // End of variables declaration//GEN-END:variables

}

