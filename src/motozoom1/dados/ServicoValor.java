/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1.dados;

/**
 *
 * @author ernan
 */
public class ServicoValor {
       
    private int id;
    private int quantidade;
    private double valor;
    private double desconto;
    private double ValorTotal;
    private Servico servico;

    public ServicoValor(int id, int quantidade, double desconto, double ValorTotal, Servico servico, double valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.ValorTotal = ValorTotal;
        this.servico = servico;
        this.valor = valor;
    }
    
    public ServicoValor() {
        this.id = 0;
        this.quantidade = 0;
        this.desconto = 0;
        this.ValorTotal = 0;
        this.servico = new Servico();
        this.valor = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }
    
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
