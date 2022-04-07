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
public class Lancamento {
    
    private int id;
    private String data;
    private String descricao;
    private String tipo;
    private double valor;
    private String formaPagamento;
    private Venda venda;

    public Lancamento(int id, String data, String descricao, String tipo, double valor, String formaPagamento, Venda venda) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.venda = venda;
    }

    public Lancamento() {
        this.id = 0;
        this.data = "";
        this.descricao = "";
        this.tipo = "";
        this.valor = 0;
        this.formaPagamento = "";
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
