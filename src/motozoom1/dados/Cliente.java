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
public class Cliente {
    
    private int id;
    private String dataCadastro;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String sexo;
    
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

    public Cliente(int id, String dataCadastro, String nome, String cpf, String telefone, String email, String sexo, String cep, String estado, String cidade, String bairro, String rua, String numero) {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public Cliente() {
        this.id = 0;
        this.dataCadastro = "";
        this.nome = "";
        this.cpf = "";
        this.telefone = "";
        this.email = "";
        this.sexo = "";
        this.cep = "";
        this.estado = "";
        this.cidade = "";
        this.bairro = "";
        this.rua = "";
        this.numero = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
}
