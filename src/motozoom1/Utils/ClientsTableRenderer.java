package motozoom1.Utils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import motozoom1.MotoZoom1;
import motozoom1.swing.mainFrame;

/**
 *
 * @author ErNaNi
 */

  public class ClientsTableRenderer extends DefaultCellEditor
  {
    public static final int BUTTON_EDITAR = 1;
    public static final int BUTTON_REMOVER = 2;
    
    public static final int TABLE_PRODUTOS = 3;
    public static final int TABLE_SERVICOS = 4;
    public static final int TABLE_VENDAS = 5;
    public static final int TABLE_CLIENTES = 6;
    public static final int TABLE_ORDEM_DE_SERVICO = 7;
    public static final int TABLE_FINANCAS = 8;
    public static final int TABLE_ORDEM_DE_SERVICO_PS = 9;
    
    private JButton button;
    private String label;
    private boolean clicked;
    private int row, col;
    private JTable table;
    private int buttonTipo;
    private int tableTipo;

    public ClientsTableRenderer(JCheckBox checkBox, int btipo, int ttipo)
    {
      super(checkBox);
      
      this.buttonTipo = btipo;
      this.tableTipo = ttipo;
      
      button = new JButton();
      button.setOpaque(true);
      button.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          fireEditingStopped();
        }
      });
    }
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
      this.table = table;
      this.row = row;
      this.col = column;

      button.setForeground(Color.black);
      button.setBackground(UIManager.getColor("Button.background"));
      label = (value == null) ? "" : value.toString();
      button.setText(label);
      clicked = true;
      return button;
    }
    public Object getCellEditorValue()
    {
      if (clicked)
      {
          if(buttonTipo == BUTTON_EDITAR)
          {
              eventEditar();
          } 
          else 
          {
              eventRemover();
          }
        //JOptionPane.showMessageDialog(MotoZoom1.mainframe, "Column with Value: "+table.getValueAt(row, 1) + " -  Clicked!");
      }
      clicked = false;
      return new String(label);
    }
    
    private void eventEditar()
    {
        switch(this.tableTipo) {
            case TABLE_PRODUTOS:
                eventProdutosEditar();
                break;
            case TABLE_SERVICOS:
                eventServicosEditar();
                break;
            case TABLE_CLIENTES:
                eventClientesEditar();
                break;
            case TABLE_VENDAS:
                eventVendasEditar();
                break;
            case TABLE_FINANCAS:
                eventFinancasEditar();
                break;
            case TABLE_ORDEM_DE_SERVICO:
                eventOrdemDeServicoEditar();
                break;
        }
    }
    
    private void eventRemover()
    {
        switch(this.tableTipo) {
            case TABLE_PRODUTOS:
                eventProdutosRemover();
                break;
            case TABLE_SERVICOS:
                eventServicosRemover();
                break;
            case TABLE_CLIENTES:
                eventClientesRemover();
                break;
            case TABLE_VENDAS:
                eventVendasRemover();
                break;
            case TABLE_FINANCAS:
                eventFinancasRemover();
                break;
            case TABLE_ORDEM_DE_SERVICO:
                eventOrdemDeServicoRemover();
                break;
            case TABLE_ORDEM_DE_SERVICO_PS:
                eventOrdemDeServicoPSRemover();
                break;
        }
    }

    public boolean stopCellEditing()
    {
      clicked = false;
      return super.stopCellEditing();
    }

    protected void fireEditingStopped()
    {
      super.fireEditingStopped();
    }
    
    private void eventProdutosEditar()
    {
        int id = Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", ""));
        ((mainFrame) MotoZoom1.mainframe).showEditaProduto(id);
    }
    
    private void eventProdutosRemover()
    {
        int result = JOptionPane.showConfirmDialog(MotoZoom1.mainframe,"Tem certeja que dezeja remover o Produto: " + table.getValueAt(row, 1), "Remover Produto?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            ((mainFrame) MotoZoom1.mainframe).removeProduto(Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", "")));
        }
    }
    
    private void eventServicosEditar()
    {
        int id = Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", ""));
        ((mainFrame) MotoZoom1.mainframe).showEditaServico(id);
    }
    
    private void eventServicosRemover()
    {
        int result = JOptionPane.showConfirmDialog(MotoZoom1.mainframe,"Tem certeja que dezeja remover o Servico: " + table.getValueAt(row, 1), "Remover Servico?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            ((mainFrame) MotoZoom1.mainframe).removeServico(Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", "")));
        }
    }
    
    private void eventClientesEditar()
    {
        int id = Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", ""));
        ((mainFrame) MotoZoom1.mainframe).showEditaCliente(id);
    }
    
    private void eventClientesRemover()
    {
        int result = JOptionPane.showConfirmDialog(MotoZoom1.mainframe,"Tem certeja que dezeja remover o Cliente: " + table.getValueAt(row, 1), "Remover Cliente?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            ((mainFrame) MotoZoom1.mainframe).removeCliente(Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", "")));
        }
    }
    
    private void eventVendasEditar()
    {
        int id = Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", ""));
        ((mainFrame) MotoZoom1.mainframe).showEditaVenda(id);
    }
    
    private void eventVendasRemover()
    {
        int result = JOptionPane.showConfirmDialog(MotoZoom1.mainframe,"Tem certeja que dezeja remover a Venda: " + table.getValueAt(row, 0), "Remover Venda?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            ((mainFrame) MotoZoom1.mainframe).removeVenda(Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", "")));
        }
    }
    
    private void eventOrdemDeServicoEditar()
    {
        int id = Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", ""));
        ((mainFrame) MotoZoom1.mainframe).showEditaOrdemDeServico(id);
    }
    
    private void eventOrdemDeServicoRemover()
    {
        int result = JOptionPane.showConfirmDialog(MotoZoom1.mainframe,"Tem certeja que dezeja remover a Ordem de Servico: " + table.getValueAt(row, 0), "Remover Ordem de Servico?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            ((mainFrame) MotoZoom1.mainframe).removeOrdemDeServico(Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", "")));
        }
    }
    
    private void eventOrdemDeServicoPSRemover()
    {
        int result = JOptionPane.showConfirmDialog(MotoZoom1.mainframe,"Tem certeja que dezeja remover o Item: " + table.getValueAt(row, 1), "Remover Item?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            ((mainFrame) MotoZoom1.mainframe).removeOrdemDeServicoPS(Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", "")), (String)table.getValueAt(row, 1));
        }
    }
    
    private void eventFinancasEditar()
    {
        int id = Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", ""));
        ((mainFrame) MotoZoom1.mainframe).showEditaFinanca(id);
    }
    
    private void eventFinancasRemover()
    {
        int result = JOptionPane.showConfirmDialog(MotoZoom1.mainframe,"Tem certeja que dezeja remover o Lançamento: " + table.getValueAt(row, 2), "Remover Lançamento?",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            ((mainFrame) MotoZoom1.mainframe).removeFinanca(Integer.parseInt(((String) table.getValueAt(row, 0)).replaceAll(" ", "")));
        }
    }
  }