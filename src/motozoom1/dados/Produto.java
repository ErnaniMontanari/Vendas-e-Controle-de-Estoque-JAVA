/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motozoom1.dados;

/**
 *
 * @author ErNaNi
 */
public class Produto {
    
    private int id;
    private String descricao;
    private float precoCompra;
    private float precoVenda;
    private int estoque;
    
    public Produto(int id, String descricao, float precoCompra, float precoVenda, int estoque)
    {
        this.id = id;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.estoque = estoque;
    }
    
    public Produto()
    {
        this.id = 0;
        this.descricao = "";
        this.precoCompra = 0;
        this.precoVenda = 0;
        this.estoque = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
