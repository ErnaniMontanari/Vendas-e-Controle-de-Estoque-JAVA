package motozoom1.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import motozoom1.dados.Cliente;
import motozoom1.dados.Produto;
import motozoom1.dados.SQLQuery;
import motozoom1.dados.Servico;


public class Autocomplete {
    
    private ArrayList<String> items;
    private static boolean selected = false;
    public static final int CLIENTES = 1;
    public static final int PRODUTOS = 2;
    public static final int SERVICOS = 3;

    public Autocomplete() {
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        frame.setTitle("Auto Completion Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 500, 400);

        ArrayList<String> ites = new ArrayList<String>();
        Locale[] locales = Locale.getAvailableLocales();
        for (int i = 0; i < locales.length; i++) {
            String item = locales[i].getDisplayName();
            ites.add(item);
        }
        JTextField txtInput = new JTextField();
        //setupAutoComplete(txtInput, ites,0, null);
        txtInput.setColumns(30);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(txtInput, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }

    public void setupAutoComplete(final JTextField txtInput, final ArrayList<String> ite, int tipe, mainFrame mf) {
        items = ite;
        selected = false;
        txtInput.removeAll();
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        final JComboBox cbInput = new JComboBox(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(cbInput, false);
        for (String item : items) {
            model.addElement(item);
        }
        cbInput.setSelectedItem(null);
        cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                        selected = true;
                        txtInput.setText(cbInput.getSelectedItem().toString());
                        
                        if(CLIENTES == tipe)
                        {
                            mf.selecionaClienteOS(txtInput.getText());
                        }
                        if(PRODUTOS == tipe)
                        {
                            mf.selecionaProdutoOS(txtInput.getText());
                        }
                        if(SERVICOS == tipe)
                        {
                            mf.selecionaServicoOS(txtInput.getText());
                        }
                    }
                }
            }
        });

        txtInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);/**
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }**/
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        selected = true;
                        txtInput.setText(cbInput.getSelectedItem().toString());
                        if(CLIENTES == tipe)
                        {
                            mf.selecionaClienteOS(txtInput.getText());
                        }
                        if(PRODUTOS == tipe)
                        {
                            mf.selecionaProdutoOS(txtInput.getText());
                        }
                        if(SERVICOS == tipe)
                        {
                            mf.selecionaServicoOS(txtInput.getText());
                        }
                        cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    //cbInput.setPopupVisible(false);
                }
                
                if(txtInput.getText().length() == 3)
                {selected = false;
                    items.clear();
                    if(CLIENTES == tipe)
                    {
                        List<Cliente> cls = SQLQuery.getClientes(txtInput.getText(), 0);
                        if(cls != null)
                        {
                            for(Cliente cl : cls)
                            {
                                items.add(cl.getNome() + " : " + cl.getTelefone());
                            }
                        }

                    }
                    if(PRODUTOS == tipe)
                    {
                        List<Produto> prs = SQLQuery.getProdutos(txtInput.getText(), 0);
                        
                        if(prs != null)
                        {
                            for(Produto pr : prs)
                            {
                                items.add(pr.getDescricao());
                            }
                        }

                    }
                    if(SERVICOS == tipe)
                    {
                        List<Servico> svs = SQLQuery.getServicos(txtInput.getText(), 0);
                        if(svs != null)
                        {
                        for(Servico sv : svs)
                        {
                            items.add(sv.getDescricao());
                        }
                        }

                    }
                }
                
                setAdjusting(cbInput, false);
            }
        });
        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(cbInput, true);
                model.removeAllElements();
                String input = txtInput.getText();
                if (!input.isEmpty()) {
                    for (String item : items) {
                        if (item.toLowerCase().startsWith(input.toLowerCase())) {
                            model.addElement(item);
                        }
                    }
                }
                if(model.getSize() > 0 && !cbInput.isPopupVisible() && !selected && txtInput.isVisible() && cbInput.isVisible())
                {
                    cbInput.setPopupVisible(true);
                }
                
                setAdjusting(cbInput, false);
            }
        });
        txtInput.setLayout(new BorderLayout());
        txtInput.add(cbInput, BorderLayout.SOUTH);
    }
}