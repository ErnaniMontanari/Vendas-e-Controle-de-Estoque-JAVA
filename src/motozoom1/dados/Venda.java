/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1.dados;

import java.util.ArrayList;

/**
 *
 * @author ErNaNi
 */
public class Venda {
    
    private int id;
    private String data;
    private double valorTotal;
    private double desconto;
    private double valorRecebido;
    private double troco;
    private String formaPagamento;
    private String observacoes;
    private ArrayList<ProdutoValor> produtoValor;
    private ArrayList<ServicoValor> servicoValor;
    private Usuario usuario;
    private Cliente cliente;

    public Venda(int id, String data, double valorTotal, double desconto, double valorRecebido, double troco, String formaPagamento, String observacoes, ArrayList<ProdutoValor> produtoValor, ArrayList<ServicoValor> servicoValor, Usuario usuario, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.valorRecebido = valorRecebido;
        this.troco = troco;
        this.formaPagamento = formaPagamento;
        this.observacoes = observacoes;
        this.produtoValor = produtoValor;
        this.servicoValor = servicoValor;
        this.usuario = usuario;
        this.cliente = cliente;
    }
    
    public Venda() {
        this.id = 0;
        this.data = "";
        this.valorTotal = 0;
        this.desconto = 0;
        this.valorRecebido = 0;
        this.troco = 0;
        this.formaPagamento = "";
        this.observacoes = "";
        this.usuario = new Usuario();
        this.cliente = new Cliente();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public ArrayList<ProdutoValor> getProdutoValor() {
        return produtoValor;
    }

    public void setProdutoValor(ArrayList<ProdutoValor> produtoValor) {
        this.produtoValor = produtoValor;
    }

    public ArrayList<ServicoValor> getServicoValor() {
        return servicoValor;
    }

    public void setServicoValor(ArrayList<ServicoValor> servicoValor) {
        this.servicoValor = servicoValor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
