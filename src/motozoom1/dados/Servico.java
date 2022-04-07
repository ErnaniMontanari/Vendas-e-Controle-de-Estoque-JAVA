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
public class Servico {
    
    private int id;
    private String descricao;
    private float preco;
    
    public Servico(int id, String descricao, float preco)
    {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    public Servico()
    {
        this.id = 0;
        this.descricao = "";
        this.preco = 0;
    }
    
    public int getId() {
        return this.id;
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
