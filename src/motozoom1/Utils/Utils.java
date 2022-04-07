/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1.Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import motozoom1.dados.Cliente;
import motozoom1.dados.Lancamento;
import motozoom1.dados.OrdemDeServico;
import motozoom1.dados.Produto;
import motozoom1.dados.ProdutoValor;
import motozoom1.dados.Servico;
import motozoom1.dados.SQLQuery;
import motozoom1.dados.ServicoValor;
import motozoom1.dados.Venda;

/**
 *
 * @author ErNaNi
 */
public class Utils {
    
    public static final int TABLE_VENDAS = 1;
    public static final int TABLE_PRODUTOS = 2;
    public static final int TABLE_SERVICOS = 3;
    public static final int TABLE_CLIENTES = 4;
    public static final int TABLE_OS = 5;
    public static final int TABLE_OS_PS = 6;
    public static final int TABLE_FINANCAS = 7;
    
    public static final String[] TABLE_PRODUTOS_COLUMN_NAMES = { "Número", "Nome/Descrição", "Preço de Compra", "Preço de Venda", "Estoque", "Forma de Pagamento", "Editar", "Remover" };

    public static List<Lancamento> setTable(JTable table, int table_tipo, int pagina, int ano, int mes)
    {
        if(table_tipo != TABLE_FINANCAS)
        {
            return null;
        }
        
        return setTableFinancas(table, pagina, ano, mes);
    }
    
    public static void setTable(JTable table, int table_tipo, int pagina)
    {
        switch(table_tipo)
        {
            case TABLE_VENDAS:
                setTableVendas(table, pagina);
                break;
            case TABLE_PRODUTOS:
                setTableProdutos(table, pagina);
                break;
            case TABLE_SERVICOS:
                setTableServicos(table, pagina);
                break;
            case TABLE_CLIENTES:
                setTableClientes(table, pagina);
                break;
            case TABLE_OS:
                setTableOS(table, pagina);
                break;
            case TABLE_FINANCAS:
                //
                break;
        }
    }
    
    public static String formataValorEUA(String valor) {
        return valor.replaceAll(",", "#").replaceAll("\\.", "").replaceAll("#", "\\.");
    }

    public static String formataValorBRL(String valor) {
        return valor.replaceAll("\\.", "#").replaceAll(",", "\\.").replaceAll("#", ",");
    }

    public static String formataValorMilharrr(String valor) {
        String temp = "";
        int count = 0;
        for(char c : valor.toCharArray()){
            if(c == ',')
            {
                String[] ar = valor.split(temp);
                if(ar.length == 2)
                {
                    temp = temp + ar[1];
                }
                break;
            }
            
            temp = temp + c;
            count++;
            
            if(count == 3)
            {
                temp = temp + ".";
                count = 0;
            }
        }
        
        return temp;
    }
    
    public static void setTableOS_PS(JTable table, ArrayList<ProdutoValor> produtosOS, ArrayList<ServicoValor> servicosOS)
    {
        String[] columnNames = { "Número", "Descrição Produto/Servico", "Quantidade", "Valor Unitário", "Valor Desconto UN", "Valor Desconto Total", "Valor Total", "Remover" };
        
        
        DefaultTableModel model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column == 7)
                {
                    return true;
                }
                
                return false;
            }
        };
        
        for(String cm : columnNames)
        {
            model.addColumn(cm);
        }
        
        if(produtosOS != null)
        {
            for(ProdutoValor pos : produtosOS)
            {
                model.addRow(new Object[] {
                    "   " + pos.getProduto().getId(),
                    pos.getProduto().getDescricao(),
                    pos.getQuantidade(),
                    String.format("%,.2f", pos.getProduto().getPrecoVenda()),
                    String.format("%,.2f", pos.getDesconto()),
                    String.format("%,.2f", (pos.getDesconto() * ((double) pos.getQuantidade()))),
                    String.format("%,.2f", pos.getValorTotal()),
                    "REMOVER"
            });
            }
        }
        
        
        if(servicosOS != null)
        {
            for(ServicoValor sos : servicosOS)
            {
                model.addRow(new Object[] {
                    "   " + sos.getServico().getId(),
                    sos.getServico().getDescricao(),
                    sos.getQuantidade(),
                    String.format("%,.2f", sos.getServico().getPreco()),
                    String.format("%,.2f", sos.getDesconto()),
                    String.format("%,.2f", (sos.getDesconto() * ((double) sos.getQuantidade()))),
                    String.format("%,.2f", sos.getValorTotal()),
                    "REMOVER"
            });
            }
        }
        
        table.setModel(model);
        defineTableButtonsRemove(table, 7, ClientsTableRenderer.TABLE_ORDEM_DE_SERVICO_PS);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(0).setMinWidth(70);
        table.getColumnModel().getColumn(0).setMaxWidth(70);
        
        
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setMinWidth(70);
        table.getColumnModel().getColumn(2).setMaxWidth(70);
        
        
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        
        
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMaxWidth(100);
        
        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        table.getColumnModel().getColumn(5).setMinWidth(110);
        table.getColumnModel().getColumn(5).setMaxWidth(110);
        
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setMinWidth(100);
        table.getColumnModel().getColumn(6).setMaxWidth(100);
     
    }
    
    private static void setTableVendas(JTable table, int pagina)
    {
        String[] columnNames = { "Número", "Data", "Vendedor", "Cliente", "Valor Total", "Forma de Pagamento", "Editar", "Remover" };
        List<Venda> vendas = SQLQuery.getVendas(pagina);
        
        DefaultTableModel model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if((column == 7) || (column == 6))
                {
                    return true;
                }
                
                return false;
            }
        };
        
        for(String cm : columnNames)
        {
            model.addColumn(cm);
        }
        
        if(vendas != null)
        {
            for(Venda venda : vendas)
            {
                model.addRow(new Object[] {
                    "   " + venda.getId(),
                    venda.getData(),
                    venda.getUsuario().getNome(),
                    venda.getCliente().getNome(),
                    String.format("%,.2f", venda.getValorTotal()),
                    venda.getFormaPagamento(),
                    "EDITAR",
                    "REMOVER"
            });
            }
        }
        
        table.setModel(model);
        defineTableButtons(table, 6, 7, ClientsTableRenderer.TABLE_VENDAS);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        
        
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setMinWidth(120);
        table.getColumnModel().getColumn(1).setMaxWidth(120);
        
        table.getColumnModel().getColumn(2).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setMinWidth(130);
        table.getColumnModel().getColumn(2).setMaxWidth(130);
        
        table.getColumnModel().getColumn(4).setPreferredWidth(110);
        table.getColumnModel().getColumn(4).setMinWidth(110);
        table.getColumnModel().getColumn(4).setMaxWidth(110);
                
        
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setMinWidth(150);
        table.getColumnModel().getColumn(5).setMaxWidth(150);
    }
    
    private static void setTableOS(JTable table, int pagina)
    {
        String[] columnNames = { "Número", "Data", "Cliente", "Status", "Valor", "Editar", "Remover" };
        List<OrdemDeServico> oss = SQLQuery.getOrdemDeServicos(pagina);
        
        DefaultTableModel model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if((column == 5) || (column == 6))
                {
                    return true;
                }
                
                return false;
            }
        };
        
        for(String cm : columnNames)
        {
            model.addColumn(cm);
        }
        
        if(oss != null)
        {
            for(OrdemDeServico os : oss)
            {
                model.addRow(new Object[] {
                    "   " + os.getId(),
                    os.getData(),
                    os.getCliente().getNome(),
                    os.getStatus(),
                    formataValorBRL(os.getValorTotal() + ""),
                    "EDITAR",
                    "REMOVER"
            });
            }
        }
        
        table.setModel(model);
        defineTableButtons(table, 5, 6, ClientsTableRenderer.TABLE_ORDEM_DE_SERVICO);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        
        
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setMinWidth(120);
        table.getColumnModel().getColumn(1).setMaxWidth(120);
        
        
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        table.getColumnModel().getColumn(3).setMaxWidth(150);
        
        
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMaxWidth(100);
    
    }
    
    private static List<Lancamento> setTableFinancas(JTable table, int pagina, int ano, int mes)
    {
        String[] columnNames = { "Número", "Data", "Descrição", "Tipo", "Valor", "Forma de Pagamento", "Editar", "Remover" };
        List<Lancamento> lancamentos = SQLQuery.getFinancas(pagina, ano, mes);
        
        DefaultTableModel model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
            private final ArrayList<Integer> listVendas = new ArrayList<>();
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if((column == 7) || (column == 6))
                {
                    for(int linha : listVendas)
                    {
                        if(linha == row){
                            return false;
                        }
                    }
                    return true;
                }
                
                return false;
            }
            
            @Override
            public void addRow(Object[] rowData) {
                addRow(convertToVector(rowData));
                
                if(((String) rowData[2]).equalsIgnoreCase("Venda")){
                    listVendas.add(getRowCount()-1);
                }
            }
        };
        
        for(String cm : columnNames)
        {
            model.addColumn(cm);
        }
        
        if(lancamentos != null)
        {
            for(Lancamento lancamento : lancamentos)
            {
                model.addRow(new Object[] {
                    "   " + lancamento.getId(),
                    lancamento.getData(),
                    lancamento.getDescricao(),
                    lancamento.getTipo(),
                    formataValorBRL(lancamento.getValor()+""),
                    lancamento.getFormaPagamento(),
                    "EDITAR",
                    "REMOVER"
            });
            }
        }
        
        table.setModel(model);
        defineTableButtons(table, 6, 7, ClientsTableRenderer.TABLE_FINANCAS);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(0).setMinWidth(120);
        table.getColumnModel().getColumn(0).setMaxWidth(120);
        
        
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setMinWidth(120);
        table.getColumnModel().getColumn(1).setMaxWidth(120);
        
        
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setMinWidth(120);
        table.getColumnModel().getColumn(3).setMaxWidth(120);
        
        
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setMinWidth(120);
        table.getColumnModel().getColumn(4).setMaxWidth(120);
        
        
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setMinWidth(150);
        table.getColumnModel().getColumn(5).setMaxWidth(150);
        
        return lancamentos;
    }
    
    
    private static void setTableProdutos(JTable table, int pagina)
    {
        String[] columnNames = { "Número", "Nome/Descrição", "Preço de Compra", "Preço de Venda", "Estoque", "Editar", "Remover" };
        List<Produto> produtos = SQLQuery.getProdutos(pagina);
        
        DefaultTableModel model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if((column == 5) || (column == 6))
                {
                    return true;
                }
                
                return false;
            }
        };
        
        for(String cm : columnNames)
        {
            model.addColumn(cm);
        }
        
        if(produtos != null)
        {
            for(Produto produto : produtos)
            {
                model.addRow(new Object[] {
                    "   " + produto.getId(),
                    produto.getDescricao(),
                    formataValorBRL(produto.getPrecoCompra()+""),
                    formataValorBRL(produto.getPrecoVenda()+""),
                    produto.getEstoque(),
                    "EDITAR",
                    "REMOVER"
            });
            }
        }
        
        table.setModel(model);
        defineTableButtons(table, 5, 6, ClientsTableRenderer.TABLE_PRODUTOS);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        
        
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setMinWidth(120);
        table.getColumnModel().getColumn(2).setMaxWidth(120);
        
        
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setMinWidth(120);
        table.getColumnModel().getColumn(3).setMaxWidth(120);
        
        
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMaxWidth(100);
    }
    
    private static void setTableServicos(JTable table, int pagina)
    {
        String[] columnNames = { "Número", "Nome/Descrição", "Preço", "Editar", "Remover" };
        
        List<Servico> servicos = SQLQuery.getServicos(pagina);
        
        DefaultTableModel model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if((column == 3) || (column == 4))
                {
                    return true;
                }
                
                return false;
            }
        };
        
        for(String cm : columnNames)
        {
            model.addColumn(cm);
        }
        
        if(servicos != null)
        {
            for(Servico servico : servicos)
            {
                model.addRow(new Object[] {
                    "   " + servico.getId(),
                    servico.getDescricao(),
                    formataValorBRL(servico.getPreco()+""),
                    "EDITAR",
                    "REMOVER"
            });
            }
        }
        
        table.setModel(model);
        defineTableButtons(table, 3, 4, ClientsTableRenderer.TABLE_SERVICOS);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        
        
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setMinWidth(120);
        table.getColumnModel().getColumn(2).setMaxWidth(120);
    }
    
    private static void setTableClientes(JTable table, int pagina)
    {
        
        String[] columnNames = { "Número", "Nome", "Data/Cadastro", "Telefone", "Cidade", "Editar", "Remover" };
        
        List<Cliente> clientes = SQLQuery.getClientes(pagina);
        
        DefaultTableModel model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if((column == 5) || (column == 6))
                {
                    return true;
                }
                
                return false;
            }
        };
        
        for(String cm : columnNames)
        {
            model.addColumn(cm);
        }
        
        if(clientes != null)
        {
            for(Cliente cliente : clientes)
            {
                model.addRow(new Object[] {
                    "   " + cliente.getId(),
                    cliente.getNome(),
                    cliente.getDataCadastro(),
                    cliente.getTelefone(),
                    cliente.getCidade(),
                    "EDITAR",
                    "REMOVER"
            });
            }
        }
        
        table.setModel(model);
        defineTableButtons(table, 5, 6, ClientsTableRenderer.TABLE_CLIENTES);
        
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        
        
        table.getColumnModel().getColumn(2).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setMinWidth(110);
        table.getColumnModel().getColumn(2).setMaxWidth(110);
        
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        table.getColumnModel().getColumn(3).setMaxWidth(150);
    }
    
    private static void defineTableButtons(JTable table,int coluna1, int coluna2, int ttipo)
    {
        table.getColumnModel().getColumn(coluna1).setCellRenderer(new ClientsTableButtonRenderer());
        table.getColumnModel().getColumn(coluna1).setCellEditor(new ClientsTableRenderer(new JCheckBox(), ClientsTableRenderer.BUTTON_EDITAR, ttipo));
        
        table.getColumnModel().getColumn(coluna2).setCellRenderer(new ClientsTableButtonRenderer());
        table.getColumnModel().getColumn(coluna2).setCellEditor(new ClientsTableRenderer(new JCheckBox(), ClientsTableRenderer.BUTTON_REMOVER,ttipo));
        
        table.getColumnModel().getColumn(coluna1).setPreferredWidth(80);
        table.getColumnModel().getColumn(coluna1).setMinWidth(80);
        table.getColumnModel().getColumn(coluna1).setMaxWidth(80);
        table.getColumnModel().getColumn(coluna1).setResizable(false);
        
        table.getColumnModel().getColumn(coluna2).setResizable(false);
        table.getColumnModel().getColumn(coluna2).setPreferredWidth(90);
        table.getColumnModel().getColumn(coluna2).setMinWidth(90);
        table.getColumnModel().getColumn(coluna2).setMaxWidth(90);
        
        table.setRowHeight(40);
        table.setRowMargin(5);
        
        table.setFont(new java.awt.Font("Tahoma", 0, 14));
        
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
    }
    
    private static void defineTableButtonsRemove(JTable table, int coluna2, int ttipo)
    {  
        table.getColumnModel().getColumn(coluna2).setCellRenderer(new ClientsTableButtonRenderer());
        table.getColumnModel().getColumn(coluna2).setCellEditor(new ClientsTableRenderer(new JCheckBox(), ClientsTableRenderer.BUTTON_REMOVER,ttipo));

        table.getColumnModel().getColumn(coluna2).setResizable(false);
        table.getColumnModel().getColumn(coluna2).setPreferredWidth(90);
        table.getColumnModel().getColumn(coluna2).setMinWidth(90);
        table.getColumnModel().getColumn(coluna2).setMaxWidth(90);
        
        table.setRowHeight(40);
        table.setRowMargin(5);
        
        table.setFont(new java.awt.Font("Tahoma", 0, 14));
        
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
    }
    
    
    
    
    
    
    
 
}
