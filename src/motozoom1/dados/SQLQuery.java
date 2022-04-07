/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1.dados;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import motozoom1.ModuloConexao;
import motozoom1.MotoZoom1;
import motozoom1.dados.Servico;
/**
 *
 * @author ErNaNi
 */
public class SQLQuery {
    
    private static Connection conexao;
    //private static PreparedStatement pstmt;
    private static ResultSet rs;
    
    private static final String SQL_LOGIN = "SELECT * FROM usuarios WHERE login = ? AND senha = ? LIMIT 1";
    private static final String SQL_GET_USUARIO = "SELECT * FROM usuarios WHERE idUsuario = ? LIMIT 1";
    
    //SQL PRODUTOS
    private static final String SQL_COUNT_PRODUTOS = "SELECT COUNT(*) FROM produtos";
    private static final String SQL_GET_PRODUTOS = "SELECT * FROM produtos LIMIT ? , 30";
    private static final String SQL_GET_PRODUTOS_BY_NAME = "SELECT * FROM produtos WHERE descricao LIKE ? LIMIT ? , 30";
    private static final String SQL_GET_PRODUTO = "SELECT * FROM produtos WHERE idProduto = ? LIMIT 1";
    private static final String SQL_DELETE_PRODUTO = "DELETE FROM produtos WHERE idProduto = ? LIMIT 1";
    private static final String SQL_UPDATE_PRODUTO = "UPDATE produtos SET descricao = ?, precoCompra = ?, precoVenda = ?, estoque = ? WHERE idProduto = ? LIMIT 1";
    private static final String SQL_UPDATE_PRODUTO_SOMA = "UPDATE produtos SET estoque = estoque + ? WHERE idProduto = ? LIMIT 1";
    private static final String SQL_UPDATE_PRODUTO_SUBTRAI = "UPDATE produtos SET estoque = estoque - ? WHERE idProduto = ? LIMIT 1";
    private static final String SQL_INSERT_PRODUTOS = "INSERT INTO produtos (descricao, precoCompra, precoVenda, estoque) VALUES(? ,? , ?, ?)";
    
    //SQL SERVICOS
    private static final String SQL_COUNT_SERVICOS = "SELECT COUNT(*) FROM servicos";
    private static final String SQL_GET_SERVICOS = "SELECT * FROM servicos LIMIT ? , 30";
    private static final String SQL_GET_SERVICOS_BY_NAME = "SELECT * FROM servicos WHERE descricao LIKE ? LIMIT ? , 30";
    private static final String SQL_GET_SERVICO = "SELECT * FROM servicos WHERE idServico = ? LIMIT 1";
    private static final String SQL_DELETE_SERVICO = "DELETE FROM servicos WHERE idServico = ? LIMIT 1";
    private static final String SQL_UPDATE_SERVICO = "UPDATE servicos SET descricao = ?, preco = ? WHERE idServico = ? LIMIT 1";
    private static final String SQL_INSERT_SERVICOS = "INSERT INTO servicos (descricao, preco) VALUES(? ,?)";
        
    //SQL CLIENTES
    private static final String SQL_COUNT_CLIENTE = "SELECT COUNT(*) FROM clientes";
    private static final String SQL_GET_CLIENTES = "SELECT * FROM clientes LIMIT ? , 30";
    private static final String SQL_GET_CLIENTES_BY_NAME = "SELECT * FROM clientes WHERE nome LIKE ? LIMIT ? , 30";
    private static final String SQL_GET_CLIENTE = "SELECT * FROM clientes WHERE idCliente = ? LIMIT 1";
    private static final String SQL_DELETE_CLIENTE = "DELETE FROM clientes WHERE idCliente = ? LIMIT 1";
    private static final String SQL_UPDATE_CLIENTE = "UPDATE clientes SET dataCadastro = ?, nome = ?, cpf = ?, telefone = ?, email = ?, sexo = ?, cep = ?, estado = ?, cidade = ?, bairro = ?, rua = ?, numero = ? WHERE idCliente = ? LIMIT 1";
    private static final String SQL_INSERT_CLIENTES = "INSERT INTO clientes (dataCadastro, nome, cpf, telefone, email, sexo, cep, estado, cidade, bairro, rua, numero) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
        
    //SQL VENDAS
    private static final String SQL_COUNT_VENDAS = "SELECT COUNT(*) FROM vendas";
    private static final String SQL_GET_VENDAS = "SELECT * FROM vendas LIMIT ? , 30";
    private static final String SQL_GET_VENDA = "SELECT * FROM vendas WHERE idVenda = ? LIMIT 1";
    private static final String SQL_DELETE_VENDA = "DELETE FROM vendas WHERE idVenda = ? LIMIT 1";
    private static final String SQL_UPDATE_VENDA = "UPDATE vendas SET data = ?, valorTotal = ? , desconto = ? , valorRecebido = ? , troco = ? , formaPagamento = ? , observacoes = ? , idCliente = ? WHERE idVenda = ? LIMIT 1";
    private static final String SQL_INSERT_VENDAS = "INSERT INTO vendas (data, valorTotal, desconto, valorRecebido, troco, formaPagamento, observacoes, idUsuario, idCliente) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,?)";
        
    //SQL FINANCAS
    private static final String SQL_COUNT_FINANCAS = "SELECT COUNT(*) FROM lancamentos";
    private static final String SQL_GET_FINANCAS = "SELECT * FROM lancamentos WHERE YEAR(data) = ? AND MONTH(data) = ? ORDER BY idLancamento DESC LIMIT ? , 30";
    private static final String SQL_GET_FINANCAS_ANUAL = "SELECT tipo, valor FROM lancamentos WHERE YEAR(data) = ?";
    private static final String SQL_GET_FINANCA = "SELECT * FROM lancamentos WHERE idLancamento  = ? LIMIT 1";
    private static final String SQL_DELETE_FINANCA = "DELETE FROM lancamentos WHERE idLancamento  = ? LIMIT 1";
    private static final String SQL_DELETE_FINANCA_VENDA = "DELETE FROM lancamentos WHERE idVenda  = ? LIMIT 1";
    private static final String SQL_UPDATE_FINANCA = "UPDATE lancamentos SET data = ?, descricao = ?, tipo = ?, valor = ?, formaPagamento = ? WHERE idLancamento  = ? LIMIT 1";
    private static final String SQL_UPDATE_FINANCA_VENDA = "UPDATE lancamentos SET data = ?, descricao = ?, tipo = ?, valor = ?, formaPagamento = ? WHERE idVenda = ? LIMIT 1";
    private static final String SQL_INSERT_FINANCAS = "INSERT INTO lancamentos (data, descricao, tipo, valor, formaPagamento, idVenda) VALUES(? ,? ,? ,? ,? ,?)";
        
    //SQL ORDEM DE SERVICO
    private static final String SQL_COUNT_ORDEM_DE_SERVICOS = "SELECT COUNT(*) FROM ordem_de_servico";
    private static final String SQL_GET_ORDEM_DE_SERVICOS = "SELECT * FROM ordem_de_servico LIMIT ? , 30";
    private static final String SQL_GET_ORDEM_DE_SERVICO = "SELECT * FROM ordem_de_servico WHERE idOrdemDeServico = ? LIMIT 1";
    private static final String SQL_DELETE_ORDEM_DE_SERVICO = "DELETE FROM ordem_de_servico WHERE idOrdemDeServico = ? LIMIT 1";
    private static final String SQL_UPDATE_ORDEM_DE_SERVICO = "UPDATE ordem_de_servico SET data = ?, valorTotal = ?, status = ?, descricaoVeiculo = ?, descricaoDefeito = ?, pecasReposicao = ?, observacoes = ?, idCliente = ?, idVenda = ? WHERE idOrdemDeServico  = ? LIMIT 1";
    private static final String SQL_INSERT_ORDEM_DE_SERVICOS = "INSERT INTO ordem_de_servico (data, valorTotal, status, descricaoVeiculo, descricaoDefeito, pecasReposicao, observacoes, idUsuario, idCliente, idVenda) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";

    //SQL PRODUTO OS
    private static final String SQL_INSERT_PRODUTO_OS = "INSERT INTO produto_ordem_de_servico (quantidade, descontoUN, valorTotal, idOrdemDeServico, idProduto) VALUES(? ,? ,? ,? ,?)";
    private static final String SQL_GET_PRODUTOS_OS = "SELECT * FROM produto_ordem_de_servico WHERE idOrdemDeServico = ?";
    private static final String SQL_DELETE_PRODUTO_OS = "DELETE FROM produto_ordem_de_servico WHERE idOrdemDeServico = ?";
    private static final String SQL_UPDATE_PRODUTO_OS = "UPDATE produto_ordem_de_servico SET quantidade = ?, descontoUN = ?, valorTotal = ?, idOrdemDeServico = ?, idProduto = ? WHERE idProdutoOS = ? LIMIT 1";

    //SQL SERVICO OS
    private static final String SQL_INSERT_SERVICO_OS = "INSERT INTO servico_ordem_de_servico (quantidade, descontoUN, valorTotal, idOrdemDeServico, idServico) VALUES(? ,? ,? ,? ,?)";
    private static final String SQL_GET_SERVICOS_OS = "SELECT * FROM servico_ordem_de_servico WHERE idOrdemDeServico = ?";
    private static final String SQL_DELETE_SERVICO_OS = "DELETE FROM servico_ordem_de_servico WHERE idOrdemDeServico = ?";
    private static final String SQL_UPDATE_SERVICO_OS = "UPDATE servico_ordem_de_servico SET quantidade = ?, descontoUN = ?, valorTotal = ?, idOrdemDeServico = ?, idServico = ? WHERE idServicoOS = ? LIMIT 1";
    
    //SQL PRODUTO VENDA
    private static final String SQL_INSERT_PRODUTO_VENDA = "INSERT INTO produtos_vendidos (quantidade, valor, descontoUN, valorTotal, idVenda, idProduto) VALUES(? ,? ,? ,? ,? ,?)";
    private static final String SQL_GET_PRODUTOS_VENDA = "SELECT * FROM produtos_vendidos WHERE idVenda = ?";
    private static final String SQL_DELETE_PRODUTO_VENDA = "DELETE FROM produtos_vendidos WHERE idVenda = ?";
    private static final String SQL_UPDATE_PRODUTO_VENDA = "UPDATE produtos_vendidos SET quantidade = ?, valor = ?, descontoUN = ?, valorTotal = ?, idVenda = ?, idProduto = ? WHERE idProdutosVendidos = ? LIMIT 1";

    //SQL SERVICO VENDA
    private static final String SQL_INSERT_SERVICO_VENDA = "INSERT INTO servicos_vendidos (quantidade, valor, descontoUN, valorTotal, idVenda, idServico) VALUES(? ,? ,? ,? ,? ,?)";
    private static final String SQL_GET_SERVICOS_VENDA = "SELECT * FROM servicos_vendidos WHERE idVenda = ?";
    private static final String SQL_DELETE_SERVICO_VENDA = "DELETE FROM servicos_vendidos WHERE idVenda = ?";
    private static final String SQL_UPDATE_SERVICO_VENDA = "UPDATE servicos_vendidos SET quantidade = ?, valor = ?, descontoUN = ?, valorTotal = ?, idVenda = ?, idServico = ? WHERE idServicosVendidos  = ? LIMIT 1";
    
    
    private  SQLQuery() {}
    
    private static void closeCPR()
    {
        // conexao.close();
        // pstmt.close();
        // rs.close();

    }
    
    public static boolean checkLogin(String login, String senha)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_LOGIN);
            pstmt.setString(1, login);
            pstmt.setString(2, senha);
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return false;
            }
            
            MotoZoom1.usuario = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("dataCadastro"), 
                    rs.getString("nome"), 
                    rs.getString("telefone"), 
                    rs.getString("email"), 
                    rs.getString("sexo"), 
                    rs.getString("login"), 
                    rs.getString("senha")
            );
            
            conexao.close();
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO no checkLogin() -> " + ex);
        }
        
        return true;
    }
    
    public static Usuario getUsuario(int idUsuario)
    {
        conexao = ModuloConexao.getConnection();
        Usuario usuario = new Usuario();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_USUARIO);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            usuario = new Usuario(
                    rs.getInt("idUsuario"),
                    rs.getString("dataCadastro"), 
                    rs.getString("nome"), 
                    rs.getString("telefone"), 
                    rs.getString("email"), 
                    rs.getString("sexo"), 
                    rs.getString("login"), 
                    rs.getString("senha")
            );
            
            conexao.close();
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO no getUsuario() -> " + ex);
        }
        
        return usuario;
    }
    
    public static int getCountProdutos()
    {
        int count = 0;
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_COUNT_PRODUTOS);
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                return count;
            }
            
            count = rs.getInt("COUNT(*)");
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getCountProdutos() -> " + ex);
        }
        return count;
    }
    
    public static List<Produto> getProdutos(int pagina)
    {   
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_PRODUTOS);
            pstmt.setInt(1, (pagina*30));
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            do
            {
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("idProduto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getFloat("precoCompra"));
                produto.setPrecoVenda(rs.getFloat("precoVenda"));
                produto.setEstoque(rs.getInt("estoque"));
                
                produtos.add(produto);
                
            } while(rs.next());
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getProdutos() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return produtos;
    }
    
    public static List<Produto> getProdutos(String partName, int pagina)
    {   
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_PRODUTOS_BY_NAME);
            pstmt.setString(1, partName + "%");
            pstmt.setInt(2, (pagina*30));
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            do
            {
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("idProduto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getFloat("precoCompra"));
                produto.setPrecoVenda(rs.getFloat("precoVenda"));
                produto.setEstoque(rs.getInt("estoque"));
                
                produtos.add(produto);
                
            } while(rs.next());
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getProdutos(2) -> " + ex);
        } finally {
            closeCPR();
        }
        
        return produtos;
    }
    
    public static Produto getProduto(int idProduto)
    {   
        conexao = ModuloConexao.getConnection();
        Produto produto = new Produto();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_PRODUTO);
            pstmt.setInt(1, idProduto);
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }

            produto.setId(rs.getInt("idProduto"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPrecoCompra(rs.getFloat("precoCompra"));
            produto.setPrecoVenda(rs.getFloat("precoVenda"));
            produto.setEstoque(rs.getInt("estoque"));
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getProdutos() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return produto;
    }
    
    public static void insertProduto(Produto produto)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_PRODUTOS);
            
            pstmt.setString(1, produto.getDescricao());
            pstmt.setFloat(2, produto.getPrecoCompra());
            pstmt.setFloat(3, produto.getPrecoVenda());
            pstmt.setInt(4, produto.getEstoque());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no insertProdutos() -> " + ex);
        }
    }
    
    public static void updateProduto(Produto produto)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_UPDATE_PRODUTO);
            
            pstmt.setString(1, produto.getDescricao());
            pstmt.setFloat(2, produto.getPrecoCompra());
            pstmt.setFloat(3, produto.getPrecoVenda());
            pstmt.setInt(4, produto.getEstoque());
            pstmt.setInt(5, produto.getId());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no updateProdutos() -> " + ex);
        }
    }
    
    public static void somaProduto(int idProduto, int quantia)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_UPDATE_PRODUTO_SOMA);
            
            pstmt.setInt(1, quantia);
            pstmt.setInt(2, idProduto);
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no somaProduto() -> " + ex);
        }
    }
    
    public static void subtraiProduto(int idProduto, int quantia)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_UPDATE_PRODUTO_SUBTRAI);
            
            pstmt.setInt(1, quantia);
            pstmt.setInt(2, idProduto);
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no subtraiProduto() -> " + ex);
        }
    }
    
    public static void deleteProduto(Produto produto)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_PRODUTO);
            
            pstmt.setInt(1, produto.getId());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteProdutos() -> " + ex);
        }
    }
    
    public static void deleteProduto(int idProduto)
    {
        deleteProduto(new Produto(idProduto, "", 0, 0, 0));
    }
    
    public static List<Servico> getServicos(int pagina)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Servico> servicos = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_SERVICOS);
            pstmt.setInt(1, (pagina*30));
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            do
            {
                Servico servico = new Servico();
                
                servico.setId(rs.getInt("idServico"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                
                servicos.add(servico);
                
            } while(rs.next());
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getServicos() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return servicos;
    }
    
    public static List<Servico> getServicos(String partName, int pagina)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Servico> servicos = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_SERVICOS_BY_NAME);
            pstmt.setString(1, partName + "%");
            pstmt.setInt(2, (pagina*30));
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            do
            {
                Servico servico = new Servico();
                
                servico.setId(rs.getInt("idServico"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                
                servicos.add(servico);
                
            } while(rs.next());
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getServicos() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return servicos;
    }
    
    public static Servico getServico(int idServico)
    {
        conexao = ModuloConexao.getConnection();
        Servico servico = new Servico();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_SERVICO);
            pstmt.setInt(1, idServico);
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }

            servico.setId(rs.getInt("idServico"));
            servico.setDescricao(rs.getString("descricao"));
            servico.setPreco(rs.getFloat("preco"));
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getServico() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return servico;
    }
    
    public static void insertServico(Servico servico)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_SERVICOS);
            
            pstmt.setString(1, servico.getDescricao());
            pstmt.setFloat(2, servico.getPreco());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no insertServico() -> " + ex);
        }
    }
    
    public static void updateServico(Servico servico)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_UPDATE_SERVICO);
            
            pstmt.setString(1, servico.getDescricao());
            pstmt.setFloat(2, servico.getPreco());
            pstmt.setInt(3, servico.getId());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no updateServicos() -> " + ex);
        }
    }
    
    public static void deleteServico(Servico servico)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_SERVICO);
            
            pstmt.setInt(1, servico.getId());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteServicos() -> " + ex);
        }
    }
    
    public static void deleteServico(int idServico)
    {
        deleteServico(new Servico(idServico, "", 0));
    }
    
    public static List<Cliente> getClientes(int pagina)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_CLIENTES);
            pstmt.setInt(1, (pagina*30));
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            
            do
            {
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("idCliente"));
                if(rs.getDate("dataCadastro") != null)
                {
                    cliente.setDataCadastro(dateformat.format(rs.getDate("dataCadastro")));
                } else {
                    cliente.setDataCadastro("");
                }
                cliente.setNome(rs.getString("nome") != null ? rs.getString("nome") : "");
                cliente.setCpf(rs.getString("cpf") != null ? rs.getString("cpf") : "");
                cliente.setTelefone(rs.getString("telefone") != null ? rs.getString("telefone") : "");
                cliente.setEmail(rs.getString("email") != null ? rs.getString("email") : "");
                cliente.setSexo(rs.getString("sexo") != null ? rs.getString("sexo") : "");
                cliente.setCep(rs.getString("cep") != null ? rs.getString("cep") : "");
                cliente.setEstado(rs.getString("estado") != null ? rs.getString("estado") : "");
                cliente.setCidade(rs.getString("cidade") != null ? rs.getString("cidade") : "");
                cliente.setBairro(rs.getString("bairro") != null ? rs.getString("bairro") : "");
                cliente.setRua(rs.getString("rua") != null ? rs.getString("rua") : "");
                cliente.setNumero(rs.getString("numero") != null ? rs.getString("numero") : "");
                
                clientes.add(cliente);
                
            } while(rs.next());
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getClientes() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return clientes;
    }
    
    public static List<Cliente> getClientes(String partNome, int pagina)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_CLIENTES_BY_NAME);
            pstmt.setString(1, partNome + "%");
            pstmt.setInt(2, (pagina*30));
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            
            do
            {
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("idCliente"));
                if(rs.getDate("dataCadastro") != null)
                {
                    cliente.setDataCadastro(dateformat.format(rs.getDate("dataCadastro")));
                } else {
                    cliente.setDataCadastro("");
                }
                cliente.setNome(rs.getString("nome") != null ? rs.getString("nome") : "");
                cliente.setCpf(rs.getString("cpf") != null ? rs.getString("cpf") : "");
                cliente.setTelefone(rs.getString("telefone") != null ? rs.getString("telefone") : "");
                cliente.setEmail(rs.getString("email") != null ? rs.getString("email") : "");
                cliente.setSexo(rs.getString("sexo") != null ? rs.getString("sexo") : "");
                cliente.setCep(rs.getString("cep") != null ? rs.getString("cep") : "");
                cliente.setEstado(rs.getString("estado") != null ? rs.getString("estado") : "");
                cliente.setCidade(rs.getString("cidade") != null ? rs.getString("cidade") : "");
                cliente.setBairro(rs.getString("bairro") != null ? rs.getString("bairro") : "");
                cliente.setRua(rs.getString("rua") != null ? rs.getString("rua") : "");
                cliente.setNumero(rs.getString("numero") != null ? rs.getString("numero") : "");
                
                clientes.add(cliente);
                
            } while(rs.next());
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getClientes() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return clientes;
    }
    
    public static Cliente getCliente(int idCliente)
    {
        conexao = ModuloConexao.getConnection();
        Cliente cliente = new Cliente();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_CLIENTE);
            pstmt.setInt(1, idCliente);
            
            rs = pstmt.executeQuery();
            if(!rs.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");

            cliente.setId(rs.getInt("idCliente"));
            if(rs.getDate("dataCadastro") != null)
            {
                cliente.setDataCadastro(dateformat.format(rs.getDate("dataCadastro")));
            } else {
                cliente.setDataCadastro("");
            }
            cliente.setNome(rs.getString("nome") != null ? rs.getString("nome") : "");
            cliente.setCpf(rs.getString("cpf") != null ? rs.getString("cpf") : "");
            cliente.setTelefone(rs.getString("telefone") != null ? rs.getString("telefone") : "");
            cliente.setEmail(rs.getString("email") != null ? rs.getString("email") : "");
            cliente.setSexo(rs.getString("sexo") != null ? rs.getString("sexo") : "");
            cliente.setCep(rs.getString("cep") != null ? rs.getString("cep") : "");
            cliente.setEstado(rs.getString("estado") != null ? rs.getString("estado") : "");
            cliente.setCidade(rs.getString("cidade") != null ? rs.getString("cidade") : "");
            cliente.setBairro(rs.getString("bairro") != null ? rs.getString("bairro") : "");
            cliente.setRua(rs.getString("rua") != null ? rs.getString("rua") : "");
            cliente.setNumero(rs.getString("numero") != null ? rs.getString("numero") : "");
            
        } catch (SQLException ex) {
            System.out.println("ERRO no getCliente() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return cliente;
    }
    
    public static void insertCliente(Cliente cliente)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_CLIENTES);
            
            if(cliente.getDataCadastro().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(cliente.getDataCadastro()).getTime()));
            } else {
                pstmt.setString(1, null);
            }
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getCpf().length() > 0 ? cliente.getCpf() : null);
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getEmail());
            pstmt.setString(6, cliente.getSexo().length() > 0 ? cliente.getSexo() : null);
            pstmt.setString(7, cliente.getCep());
            pstmt.setString(8, cliente.getEstado().length() > 1 ? cliente.getEstado() : null);
            pstmt.setString(9, cliente.getCidade());
            pstmt.setString(10, cliente.getBairro());
            pstmt.setString(11, cliente.getRua());
            pstmt.setString(12, cliente.getNumero());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException | ParseException ex) {
            System.out.println("ERRO no insertCliente() -> " + ex);
        }
        
    }
    
    public static void updateCliente(Cliente cliente)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_UPDATE_CLIENTE);
            
            if(cliente.getDataCadastro().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(cliente.getDataCadastro()).getTime()));
            } else {
                pstmt.setString(1, null);
            }
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getCpf().length() > 0 ? cliente.getCpf() : null);
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getEmail());
            pstmt.setString(6, cliente.getSexo().length() > 0 ? cliente.getSexo() : null);
            pstmt.setString(7, cliente.getCep());
            pstmt.setString(8, cliente.getEstado().length() > 1 ? cliente.getEstado() : null);
            pstmt.setString(9, cliente.getCidade());
            pstmt.setString(10, cliente.getBairro());
            pstmt.setString(11, cliente.getRua());
            pstmt.setString(12, cliente.getNumero());
            pstmt.setInt(13, cliente.getId());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no updateCliente() -> " + ex);
        } catch (ParseException ex) {
            System.out.println("ERRO no updateCliente() -> " + ex);
        }
    }
    
    public static void deleteCliente(Cliente cliente)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_CLIENTE);
            
            pstmt.setInt(1, cliente.getId());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteCliente() -> " + ex);
        }
    }
    
    public static void deleteCliente(int idCliente)
    {
        deleteCliente(new Cliente(idCliente, "", "", "", "", "", "", "", "", "", "", "", ""));
    }
    
    public static List<Venda> getVendas(int pagina)
    { 
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Venda> vendas = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_VENDAS);
            pstmt.setInt(1, (pagina*30));
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                rss.close();
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            
            do
            {
                Venda venda = new Venda();
                Usuario usuario = SQLQuery.getUsuario(rss.getInt("idUsuario"));
                Cliente cliente = SQLQuery.getCliente(rss.getInt("idCliente"));
                
                venda.setId(rss.getInt("idVenda"));
                if(rss.getDate("data") != null)
                {
                    venda.setData(dateformat.format(rss.getDate("data")));
                } else {
                    venda.setData("");
                }
                venda.setValorTotal(rss.getDouble("valorTotal") > 0 ? rss.getDouble("valorTotal") : 0);
                venda.setDesconto(rss.getDouble("desconto") > 0 ? rss.getDouble("desconto") : 0);
                venda.setValorRecebido(rss.getDouble("valorRecebido") > 0 ? rss.getDouble("valorRecebido") : 0);
                venda.setTroco(rss.getDouble("troco") > 0 ? rss.getDouble("troco") : 0);
                venda.setFormaPagamento(rss.getString("formaPagamento") != null ? rss.getString("formaPagamento") : "");
                venda.setObservacoes(rss.getString("observacoes") != null ? rss.getString("observacoes") : "");
                venda.setUsuario(usuario != null ? usuario : new Usuario());
                venda.setCliente(cliente != null ? cliente : new Cliente());
                
                vendas.add(venda);
                
            } while(rss.next());
            rss.close();
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getVendas() -> " + ex);
        }
        
        return vendas;
    }
    
    public static Venda getVenda(int idVenda)
    { 
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        Venda venda = new Venda();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_VENDA);
            pstmt.setInt(1, idVenda);
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                rss.close();
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            Usuario usuario = SQLQuery.getUsuario(rss.getInt("idUsuario"));
            Cliente cliente = SQLQuery.getCliente(rss.getInt("idCliente"));

            venda.setId(rss.getInt("idVenda"));
            if(rss.getDate("data") != null)
            {
                venda.setData(dateformat.format(rss.getDate("data")));
            } else {
                venda.setData("");
            }
            venda.setValorTotal(rss.getDouble("valorTotal") > 0 ? rss.getDouble("valorTotal") : 0);
            venda.setDesconto(rss.getDouble("desconto") > 0 ? rss.getDouble("desconto") : 0);
            venda.setValorRecebido(rss.getDouble("valorRecebido") > 0 ? rss.getDouble("valorRecebido") : 0);
            venda.setTroco(rss.getDouble("troco") > 0 ? rss.getDouble("troco") : 0);
            venda.setFormaPagamento(rss.getString("formaPagamento") != null ? rss.getString("formaPagamento") : "");
            venda.setObservacoes(rss.getString("observacoes") != null ? rss.getString("observacoes") : "");
            venda.setUsuario(usuario != null ? usuario : new Usuario());
            venda.setCliente(cliente != null ? cliente : new Cliente());
            venda.setProdutoValor((ArrayList<ProdutoValor>)getProdutoVenda(idVenda));
            venda.setServicoValor((ArrayList<ServicoValor>)getServicoVenda(idVenda));
            
            rss.close();
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getVenda() -> " + ex);
        }
        
        return venda;
    }
    
    public static int insertVenda(Venda venda)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        int id = 0;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_VENDAS, Statement.RETURN_GENERATED_KEYS);
            
            if(venda.getData().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(venda.getData()).getTime()));
            } else {
                pstmt.setDate(1, new Date(java.util.Calendar.getInstance().getTime().getTime()));
            }
            pstmt.setDouble(2, venda.getValorTotal());
            pstmt.setDouble(3, venda.getDesconto());
            pstmt.setDouble(4, venda.getValorRecebido());
            pstmt.setDouble(5, venda.getTroco());
            pstmt.setString(6, venda.getFormaPagamento());
            pstmt.setString(7, venda.getObservacoes());
            pstmt.setInt(8, venda.getUsuario().getId());
            pstmt.setInt(9, venda.getCliente().getId());
            
            pstmt.execute();
            
            int key = 0;
            rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                key = rs.getInt(1);
                id = key;
            }
            
            venda.setId(key);
            closeCPR();
            insertProdutosVenda(venda.getProdutoValor(), key);
            insertServicosVenda(venda.getServicoValor(), key);
            
            insertFinanca(new Lancamento(0, venda.getData(), "Venda", "Receita", venda.getValorTotal(), venda.getFormaPagamento(), venda));
        } catch (SQLException | ParseException ex) {
            System.out.println("ERRO no insertVenda() -> " + ex);
        }
        return id;
    }
    
    public static void updateVenda(Venda venda)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_UPDATE_VENDA);
            
            if(venda.getData().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(venda.getData()).getTime()));
            } else {
                pstmt.setDate(1, new Date(java.util.Calendar.getInstance().getTime().getTime()));
            }
            pstmt.setDouble(2, venda.getValorTotal());
            pstmt.setDouble(3, venda.getDesconto());
            pstmt.setDouble(4, venda.getValorRecebido());
            pstmt.setDouble(5, venda.getTroco());
            pstmt.setString(6, venda.getFormaPagamento());
            pstmt.setString(7, venda.getObservacoes());
            pstmt.setInt(8, venda.getCliente().getId());
            pstmt.setInt(9, venda.getId());
            
            pstmt.execute();
            
            closeCPR();
            deleteProdutosVenda(venda.getId());
            deleteServicosVenda(venda.getId());
            insertProdutosVenda(venda.getProdutoValor(), venda.getId());
            insertServicosVenda(venda.getServicoValor(), venda.getId());
            
            updateFinanca(new Lancamento(0, venda.getData(), "Venda", "Receita", venda.getValorTotal(), venda.getFormaPagamento(), venda));
        } catch (SQLException | ParseException ex) {
            System.out.println("ERRO no updateVenda() -> " + ex);
        }
    }
    
    public static void deleteVenda(Venda venda)
    {
        deleteProdutosVenda(venda.getId());
        deleteServicosVenda(venda.getId());
        deleteFinanca(new Lancamento(0, venda.getData(), "Venda", "Receita", venda.getValorTotal(), venda.getFormaPagamento(), venda));
        
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_VENDA);
            
            pstmt.setInt(1, venda.getId());
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteVenda() -> " + ex);
        }
    }
    
    public static void deleteVenda(int idVenda)
    {
        deleteVenda(new Venda(idVenda, "", 0, 0, 0, 0, "", "", null, null, null, null));
    }
    
    public static List<Lancamento> getFinancas(int pagina, int ano, int mes)
    { 
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Lancamento> lancamentos = new ArrayList<Lancamento>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_FINANCAS);
            pstmt.setInt(1, ano);
            pstmt.setInt(2, mes);
            pstmt.setInt(3, (pagina*30));
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            
            do
            {
                Lancamento lancamento = new Lancamento();
                //Venda venda = SQLQuery.getVenda(rss.getInt("idVenda"));
                
                lancamento.setId(rss.getInt("idLancamento"));
                if(rss.getDate("data") != null)
                {
                    lancamento.setData(dateformat.format(rss.getDate("data")));
                } else {
                    lancamento.setData("");
                }
                lancamento.setDescricao(rss.getString("descricao") != null ? rss.getString("descricao") : "");
                lancamento.setTipo(rss.getString("tipo") != null ? rss.getString("tipo") : "");
                lancamento.setValor(rss.getDouble("valor") > 0 ? rss.getDouble("valor") : 0);
                lancamento.setFormaPagamento(rss.getString("formaPagamento") != null ? rss.getString("formaPagamento") : "");
                //lancamento.setVenda(venda != null ? venda : new Venda());
                lancamento.setVenda(new Venda());
                
                lancamentos.add(lancamento);
                
            } while(rss.next());
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getFinancas() -> " + ex);
        }
        
        return lancamentos;
    }
        
    public static List<Lancamento> getFinancasAnual(int ano)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<Lancamento> lancamentos = new ArrayList<Lancamento>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_FINANCAS_ANUAL);
            pstmt.setInt(1, ano);
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            
            do
            {
                Lancamento lancamento = new Lancamento();
                
                lancamento.setTipo(rss.getString("tipo") != null ? rss.getString("tipo") : "");
                lancamento.setValor(rss.getDouble("valor") > 0 ? rss.getDouble("valor") : 0);
                
                lancamentos.add(lancamento);
                
            } while(rss.next());
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getFinancasAnual() -> " + ex);
        }
        
        return lancamentos;
    }
    
    public static Lancamento getFinanca(int idFinanca)
    { 
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        Lancamento lancamento = new Lancamento();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_FINANCA);
            pstmt.setInt(1, idFinanca);
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            
            Venda venda = SQLQuery.getVenda(rss.getInt("idVenda"));

            lancamento.setId(rss.getInt("idLancamento"));
            if(rss.getDate("data") != null)
            {
                lancamento.setData(dateformat.format(rss.getDate("data")));
            } else {
                lancamento.setData("");
            }
            lancamento.setDescricao(rss.getString("descricao") != null ? rss.getString("descricao") : "");
            lancamento.setTipo(rss.getString("tipo") != null ? rss.getString("tipo") : "");
            lancamento.setValor(rss.getDouble("valor") > 0 ? rss.getDouble("valor") : 0);
            lancamento.setFormaPagamento(rss.getString("formaPagamento") != null ? rss.getString("formaPagamento") : "");
            lancamento.setVenda(venda != null ? venda : new Venda());
                
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getFinanca() -> " + ex);
        }
        
        return lancamento;
    }
    
    public static void insertFinanca(Lancamento lancamento)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_FINANCAS);
            
            if(lancamento.getData().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(lancamento.getData()).getTime()));
            } else {
                pstmt.setDate(1, new Date(java.util.Calendar.getInstance().getTime().getTime()));
            }
            pstmt.setString(2, lancamento.getDescricao());
            pstmt.setString(3, lancamento.getTipo());
            pstmt.setDouble(4, lancamento.getValor());
            pstmt.setString(5, lancamento.getFormaPagamento());
            if(lancamento.getVenda() != null && lancamento.getVenda().getId() > 0)
            {
                pstmt.setInt(6, lancamento.getVenda().getId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            
            pstmt.execute();
            closeCPR();
        } catch (SQLException | ParseException ex) {
            System.out.println("ERRO no insertFinanca() -> " + ex);
        }
    }
    
    public static void updateFinanca(Lancamento lancamento)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            if(lancamento.getId() < 1)
            {
                pstmt = conexao.prepareStatement(SQL_UPDATE_FINANCA_VENDA);
                pstmt.setInt(6, lancamento.getVenda().getId());
            }
            else
            {
                pstmt = conexao.prepareStatement(SQL_UPDATE_FINANCA);
                pstmt.setInt(6, lancamento.getId());
            }
            
            if(lancamento.getData().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(lancamento.getData()).getTime()));
            } else {
                pstmt.setDate(1, new Date(java.util.Calendar.getInstance().getTime().getTime()));
            }
            pstmt.setString(2, lancamento.getDescricao());
            pstmt.setString(3, lancamento.getTipo());
            pstmt.setDouble(4, lancamento.getValor());
            pstmt.setString(5, lancamento.getFormaPagamento());
            
            pstmt.execute();
            closeCPR();
        } catch (SQLException | ParseException ex) {
            System.out.println("ERRO no updateFinanca() -> " + ex);
        }
    }
    
    public static void deleteFinanca(Lancamento lancamento)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            if(lancamento.getId() < 1)
            {
                pstmt = conexao.prepareStatement(SQL_DELETE_FINANCA_VENDA);
                pstmt.setInt(1, lancamento.getVenda().getId());
            }
            else
            {
                pstmt = conexao.prepareStatement(SQL_DELETE_FINANCA);
                pstmt.setInt(1, lancamento.getId());
            }
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteFinanca() -> " + ex);
        }
    }
    
    public static void deleteFinanca(int idFinanca)
    {
        deleteFinanca(new Lancamento(idFinanca, "", "", "", 0, "", null));
    }
    
    public static List<OrdemDeServico> getOrdemDeServicos(int pagina)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<OrdemDeServico> oss = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_ORDEM_DE_SERVICOS);
            pstmt.setInt(1, (pagina*30));
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
            
            do
            {
                OrdemDeServico os = new OrdemDeServico();
                Usuario usuario = SQLQuery.getUsuario(rss.getInt("idUsuario"));
                Cliente cliente = SQLQuery.getCliente(rss.getInt("idCliente"));
                Venda venda = SQLQuery.getVenda(rss.getInt("idVenda"));
                
                os.setId(rss.getInt("idOrdemDeServico"));
                if(rss.getDate("data") != null)
                {
                    os.setData(dateformat.format(rss.getDate("data")));
                } else {
                    os.setData("");
                }
                os.setValorTotal(rss.getDouble("valorTotal") > 0 ? rss.getDouble("valorTotal") : 0);
                os.setStatus(rss.getString("status") != null ? rss.getString("status") : "");
                os.setDescricaoVeiculo(rss.getString("descricaoVeiculo") != null ? rss.getString("descricaoVeiculo") : "");
                os.setDescricaoDefeito(rss.getString("descricaoDefeito") != null ? rss.getString("descricaoDefeito") : "");
                os.setPecasReposicao(rss.getString("pecasReposicao") != null ? rss.getString("pecasReposicao") : "");
                os.setObservacoes(rss.getString("observacoes") != null ? rss.getString("observacoes") : "");
                os.setUsuario(usuario != null ? usuario : new Usuario());
                os.setCliente(cliente != null ? cliente : new Cliente());
                os.setVenda(venda != null ? venda : new Venda());
                
                oss.add(os);
                
            } while(rss.next());
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getOrdemDeServicos() -> " + ex);
        }
        
        return oss;
    }
    
    public static OrdemDeServico getOrdemDeServico(int idOrdemDeServico)
    { 
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        OrdemDeServico os = new OrdemDeServico();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_ORDEM_DE_SERVICO);
            pstmt.setInt(1, idOrdemDeServico);
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                return null;
            }
            
            SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");

            Usuario usuario = SQLQuery.getUsuario(rss.getInt("idUsuario"));
            Cliente cliente = SQLQuery.getCliente(rss.getInt("idCliente"));
            Venda venda = SQLQuery.getVenda(rss.getInt("idVenda"));

            os.setId(rss.getInt("idOrdemDeServico"));
            if(rss.getDate("data") != null)
            {
                os.setData(dateformat.format(rss.getDate("data")));
            } else {
                os.setData("");
            }
            os.setValorTotal(rss.getDouble("valorTotal") > 0 ? rss.getDouble("valorTotal") : 0);
            os.setStatus(rss.getString("status") != null ? rss.getString("status") : "");
            os.setDescricaoVeiculo(rss.getString("descricaoVeiculo") != null ? rss.getString("descricaoVeiculo") : "");
            os.setDescricaoDefeito(rss.getString("descricaoDefeito") != null ? rss.getString("descricaoDefeito") : "");
            os.setPecasReposicao(rss.getString("pecasReposicao") != null ? rss.getString("pecasReposicao") : "");
            os.setObservacoes(rss.getString("observacoes") != null ? rss.getString("observacoes") : "");
            os.setUsuario(usuario != null ? usuario : new Usuario());
            os.setCliente(cliente != null ? cliente : new Cliente());
            os.setVenda(venda != null ? venda : new Venda());
            os.setProdutoValor((ArrayList<ProdutoValor>)getProdutoOS(idOrdemDeServico));
            os.setServicoValor((ArrayList<ServicoValor>)getServicoOS(idOrdemDeServico));
                
            rss.close();
        } catch (SQLException ex) {
            System.out.println("ERRO no getOrdemDeServico() -> " + ex);
        } finally {
            closeCPR();
        }
        
        return os;
    }
    
    public static void insertOrdemDeServico(OrdemDeServico ordemDeServico)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_ORDEM_DE_SERVICOS, Statement.RETURN_GENERATED_KEYS);
            
            if(ordemDeServico.getData().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(ordemDeServico.getData()).getTime()));
            } else {
                pstmt.setDate(1, new Date(java.util.Calendar.getInstance().getTime().getTime()));
            }
            pstmt.setDouble(2, ordemDeServico.getValorTotal());
            pstmt.setString(3, ordemDeServico.getStatus());
            pstmt.setString(4, ordemDeServico.getDescricaoVeiculo());
            pstmt.setString(5, ordemDeServico.getDescricaoDefeito());
            pstmt.setString(6, ordemDeServico.getPecasReposicao());
            pstmt.setString(7, ordemDeServico.getObservacoes());
            if(ordemDeServico.getUsuario().getId() > 0)
            {
                pstmt.setInt(8,ordemDeServico.getUsuario().getId());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }
            pstmt.setInt(9, ordemDeServico.getCliente().getId());
            if(ordemDeServico.getVenda().getId() > 0)
            {
                pstmt.setInt(10, ordemDeServico.getVenda().getId());
            } else {
                pstmt.setNull(10, Types.INTEGER);
            }
            
            
            pstmt.execute();
            
            int key = 0;
            rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                key = rs.getInt(1);
            }
            
            closeCPR();
            insertProdutosOS(ordemDeServico.getProdutoValor(), key);
            insertServicosOS(ordemDeServico.getServicoValor(), key);
        } catch (SQLException | ParseException ex) {
            System.out.println("ERRO no insertOrdemDeServico() -> " + ex);
        }
    }
    
    public static void updateOrdemDeServico(OrdemDeServico ordemDeServico)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_UPDATE_ORDEM_DE_SERVICO);
            
            if(ordemDeServico.getData().length() > 1)
            {
                SimpleDateFormat dateformat =  new SimpleDateFormat("dd/MM/yyyy");
                pstmt.setDate(1, new Date(dateformat.parse(ordemDeServico.getData()).getTime()));
            } else {
                pstmt.setDate(1, new Date(java.util.Calendar.getInstance().getTime().getTime()));
            }
            pstmt.setDouble(2, ordemDeServico.getValorTotal());
            pstmt.setString(3, ordemDeServico.getStatus());
            pstmt.setString(4, ordemDeServico.getDescricaoVeiculo());
            pstmt.setString(5, ordemDeServico.getDescricaoDefeito());
            pstmt.setString(6, ordemDeServico.getPecasReposicao());
            pstmt.setString(7, ordemDeServico.getObservacoes());
     /*     if(ordemDeServico.getUsuario().getId() > 0)
            {
                pstmt.setInt(8,ordemDeServico.getUsuario().getId());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }*/
            pstmt.setInt(8, ordemDeServico.getCliente().getId());
            if(ordemDeServico.getVenda().getId() > 0)
            {
                pstmt.setInt(9, ordemDeServico.getVenda().getId());
            } else {
                pstmt.setNull(9, Types.INTEGER);
            }
            pstmt.setInt(10, ordemDeServico.getId());
            
            pstmt.execute();
            
            closeCPR();
            deleteProdutosOS(ordemDeServico.getId());
            deleteServicosOS(ordemDeServico.getId());
            insertProdutosOS(ordemDeServico.getProdutoValor(), ordemDeServico.getId());
            insertServicosOS(ordemDeServico.getServicoValor(), ordemDeServico.getId());
        } catch (SQLException | ParseException ex) {
            System.out.println("ERRO no updateOrdemDeServico() -> " + ex);
        }
        
        
    }
    
    public static void deleteOrdemDeServico(OrdemDeServico ordemDeServico)
    {
        deleteProdutosOS(ordemDeServico.getId());
        deleteServicosOS(ordemDeServico.getId());
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_ORDEM_DE_SERVICO);
            
            pstmt.setInt(1, ordemDeServico.getId());
            
            pstmt.execute();
            
            closeCPR();

        } catch (SQLException ex) {
            System.out.println("ERRO no deleteOrdemDeServico() -> " + ex);
        }
    }
    
    public static void deleteOrdemDeServico(int idOrdemDeServico)
    {
        deleteOrdemDeServico(new OrdemDeServico(idOrdemDeServico, "", 0, "", "", "", "", "", null, null, null, null, null));
    }
    
    public static List<ProdutoValor> getProdutoOS(int idOrdemDeServico)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<ProdutoValor> pvs = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_PRODUTOS_OS);
            pstmt.setInt(1, idOrdemDeServico);
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                rss.close();
                return null;
            }
            
            do
            {
                ProdutoValor pv = new ProdutoValor();
                Produto produto = SQLQuery.getProduto(rss.getInt("idProduto"));
                
                pv.setId(rss.getInt("idProdutoOS"));
                pv.setQuantidade(rss.getInt("quantidade"));
                pv.setDesconto(rss.getDouble("descontoUN"));
                pv.setValorTotal(rss.getDouble("valorTotal"));
                pv.setProduto(produto);
                
                pvs.add(pv);
                
            } while(rss.next());
            rss.close();
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getProdutoOS() -> " + ex);
        }
        
        return pvs;
    }
    
    private static void insertProdutosOS(ArrayList<ProdutoValor> produtosOS, int idOS)
    {
        if(produtosOS == null)
        {
            return;
        }
        if(produtosOS.size() < 1)
        {
            return;
        }
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_PRODUTO_OS);
            
            int i = 0;
            for (ProdutoValor pos : produtosOS)
            {
                pstmt.setInt(1, pos.getQuantidade());
                pstmt.setDouble(2, pos.getDesconto());
                pstmt.setDouble(3, pos.getValorTotal());
                pstmt.setInt(4, idOS);
                pstmt.setInt(5, pos.getProduto().getId());

                pstmt.addBatch();
                i++;

                if (i % 1000 == 0 || i == produtosOS.size()) {
                    pstmt.executeBatch(); // Execute every 1000 items.
                }
            }
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no insertProdutosOS() -> " + ex);
        }
    }
    
    private static void deleteProdutosOS(int idOS)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_PRODUTO_OS);
            
            pstmt.setInt(1, idOS);
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteProdutosOS() -> " + ex);
        }
    }
    
    public static List<ServicoValor> getServicoOS(int idOrdemDeServico)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        List<ServicoValor> svs = new ArrayList<>();
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_SERVICOS_OS);
            pstmt.setInt(1, idOrdemDeServico);
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                rss.close();
                return null;
            }
            
            do
            {
                ServicoValor sv = new ServicoValor();
                Servico servico = SQLQuery.getServico(rss.getInt("idServico"));
                
                sv.setId(rss.getInt("idServicoOS"));
                sv.setQuantidade(rss.getInt("quantidade"));
                sv.setDesconto(rss.getDouble("descontoUN"));
                sv.setValorTotal(rss.getDouble("valorTotal"));
                sv.setServico(servico);
                
                svs.add(sv);
                
            } while(rss.next());
            rss.close();
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getServicoOS() -> " + ex);
        }
        
        return svs;
    }

    private static void insertServicosOS(ArrayList<ServicoValor> servicosOS, int idOS)
    {
        if(servicosOS == null)
        {
            return;
        }
        if(servicosOS.size() < 1)
        {
            return;
        }
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_SERVICO_OS);
            
            int i = 0;
            for (ServicoValor pos : servicosOS)
            {
                pstmt.setInt(1, pos.getQuantidade());
                pstmt.setDouble(2, pos.getDesconto());
                pstmt.setDouble(3, pos.getValorTotal());
                pstmt.setInt(4, idOS);
                pstmt.setInt(5, pos.getServico().getId());

                pstmt.addBatch();
                i++;

                if (i % 1000 == 0 || i == servicosOS.size()) {
                    pstmt.executeBatch(); // Execute every 1000 items.
                }
            }
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no insertServicosOS() -> " + ex);
        }
    }
    
    private static void deleteServicosOS(int idOS)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_SERVICO_OS);
            
            pstmt.setInt(1, idOS);
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteServicosOS() -> " + ex);
        }
    }
    
    public static List<ProdutoValor> getProdutoVenda(int idVenda)
    {
        conexao = ModuloConexao.getConnection();
        
        List<ProdutoValor> pvs = new ArrayList<>();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_GET_PRODUTOS_VENDA);
            pstmt.setInt(1, idVenda);
            
            ResultSet rss = pstmt.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                rss.close();
                return null;
            }
            
            do
            {
                ProdutoValor pv = new ProdutoValor();
                Produto produto = SQLQuery.getProduto(rss.getInt("idProduto"));
                
                pv.setId(rss.getInt("idProdutosVendidos"));
                pv.setQuantidade(rss.getInt("quantidade"));
                pv.setDesconto(rss.getDouble("descontoUN"));
                pv.setValorTotal(rss.getDouble("valorTotal"));
                pv.setProduto(produto);
                
                pvs.add(pv);
                
            } while(rss.next());
            rss.close();
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getProdutoVenda() -> " + ex);
        }
        
        return pvs;
    }
    
    private static void insertProdutosVenda(ArrayList<ProdutoValor> produtosOS, int idVenda)
    {
        if(produtosOS == null)
        {
            return;
        }
        if(produtosOS.size() < 1)
        {
            return;
        }
        conexao = ModuloConexao.getConnection();
        PreparedStatement ps;
        
        try {
            ps = conexao.prepareStatement(SQL_INSERT_PRODUTO_VENDA);
            
            int i = 0;
            for (ProdutoValor pos : produtosOS)
            {
                subtraiProduto(pos.getProduto().getId(), pos.getQuantidade());
                
                ps.setInt(1, pos.getQuantidade());
                ps.setDouble(2, pos.getValor());
                ps.setDouble(3, pos.getDesconto());
                ps.setDouble(4, pos.getValorTotal());
                ps.setInt(5, idVenda);
                ps.setInt(6, pos.getProduto().getId());

                ps.addBatch();
                i++;

                if (i % 1000 == 0 || i == produtosOS.size()) {
                    ps.executeBatch(); // Execute every 1000 items.
                }
            }
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no insertProdutosVenda() -> " + ex);
        }
        /**
        List<ProdutoValor> pvs = getProdutoVenda(idVenda);
        
        if(pvs != null)
        {
            for(ProdutoValor pv : pvs)
            {
                subtraiProduto(pv.getProduto().getId(), pv.getQuantidade());
            }
        }**/
    }
    
    private static void deleteProdutosVenda(int idVenda)
    {
        List<ProdutoValor> pvs = getProdutoVenda(idVenda);
        
        if(pvs == null)
        {
            return;
        }
        
        for(ProdutoValor pv : pvs)
        {
            somaProduto(pv.getProduto().getId(), pv.getQuantidade());
        }
        
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_PRODUTO_VENDA);
            
            pstmt.setInt(1, idVenda);
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteProdutosVenda() -> " + ex);
        }
    }
    
    public static List<ServicoValor> getServicoVenda(int idVenda)
    {
        Connection con = ModuloConexao.getConnection();
        
        List<ServicoValor> svs = new ArrayList<>();
        
        try {
            PreparedStatement pst = con.prepareStatement(SQL_GET_SERVICOS_VENDA);
            pst.setInt(1, idVenda);
            
            ResultSet rss = pst.executeQuery();
            if(!rss.next())
            {
                closeCPR();
                rss.close();
                return null;
            }
            
            do
            {
                ServicoValor sv = new ServicoValor();
                Servico servico = SQLQuery.getServico(rss.getInt("idServico"));
                
                sv.setId(rss.getInt("idServicosVendidos"));
                sv.setQuantidade(rss.getInt("quantidade"));
                sv.setDesconto(rss.getDouble("descontoUN"));
                sv.setValorTotal(rss.getDouble("valorTotal"));
                sv.setServico(servico);
                
                svs.add(sv);
               
            } while(rss.next());
            rss.close();
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no getServicoVenda() -> " + ex);
        }
        
        return svs;
    }

    private static void insertServicosVenda(ArrayList<ServicoValor> servicosOS, int idVenda)
    {
        if(servicosOS == null)
        {
            return;
        }
        if(servicosOS.size() < 1)
        {
            return;
        }
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_INSERT_SERVICO_VENDA);
            
            int i = 0;
            for (ServicoValor pos : servicosOS)
            {
                pstmt.setInt(1, pos.getQuantidade());
                pstmt.setDouble(2, pos.getValor());
                pstmt.setDouble(3, pos.getDesconto());
                pstmt.setDouble(4, pos.getValorTotal());
                pstmt.setInt(5, idVenda);
                pstmt.setInt(6, pos.getServico().getId());

                pstmt.addBatch();
                i++;

                if (i % 1000 == 0 || i == servicosOS.size()) {
                    pstmt.executeBatch(); // Execute every 1000 items.
                }
            }
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no insertServicosVenda() -> " + ex);
        }
    }
    
    private static void deleteServicosVenda(int idOS)
    {
        conexao = ModuloConexao.getConnection();
        PreparedStatement pstmt;
        
        try {
            pstmt = conexao.prepareStatement(SQL_DELETE_SERVICO_VENDA);
            
            pstmt.setInt(1, idOS);
            
            pstmt.execute();
            
            closeCPR();
        } catch (SQLException ex) {
            System.out.println("ERRO no deleteServicosVenda() -> " + ex);
        }
    }
    
}
