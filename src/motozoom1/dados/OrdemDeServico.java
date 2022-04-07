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
public class OrdemDeServico {
    
    private int id;
    private String data;
    private double valorTotal;
    private String status;
    private String descricaoVeiculo;
    private String descricaoDefeito;
    private String pecasReposicao;
    private String observacoes;
    private ArrayList<ProdutoValor> produtoValor;
    private ArrayList<ServicoValor> servicoValor;
    private Usuario usuario;
    private Cliente cliente;
    private Venda venda;

    public OrdemDeServico(int id, String data, double valorTotal, String status, String descricaoVeiculo, String descricaoDefeito, String pecasReposicao, String observacoes, ArrayList<ProdutoValor> produtoValor, ArrayList<ServicoValor> servicoValor, Usuario usuario, Cliente cliente, Venda venda) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.status = status;
        this.descricaoVeiculo = descricaoVeiculo;
        this.descricaoDefeito = descricaoDefeito;
        this.pecasReposicao = pecasReposicao;
        this.observacoes = observacoes;
        this.produtoValor = produtoValor;
        this.servicoValor = servicoValor;
        this.usuario = usuario;
        this.cliente = cliente;
        this.venda = venda;
    }

    public OrdemDeServico() {
        this.id = 0;
        this.data = "";
        this.valorTotal = 0;
        this.status = "";
        this.descricaoVeiculo = "";
        this.descricaoDefeito = "";
        this.pecasReposicao = "";
        this.observacoes = "";
        this.usuario = new Usuario();
        this.cliente = new Cliente();
        this.venda = new Venda();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricaoVeiculo() {
        return descricaoVeiculo;
    }

    public void setDescricaoVeiculo(String descricaoVeiculo) {
        this.descricaoVeiculo = descricaoVeiculo;
    }

    public String getDescricaoDefeito() {
        return descricaoDefeito;
    }

    public void setDescricaoDefeito(String descricaoDefeito) {
        this.descricaoDefeito = descricaoDefeito;
    }

    public String getPecasReposicao() {
        return pecasReposicao;
    }

    public void setPecasReposicao(String pecasReposicao) {
        this.pecasReposicao = pecasReposicao;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
