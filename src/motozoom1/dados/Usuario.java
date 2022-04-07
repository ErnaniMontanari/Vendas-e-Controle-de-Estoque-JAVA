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
public class Usuario {
    
    private int id;
    private String dataCadastro;
    private String nome;
    private String telefone;
    private String email;
    private String sexo;
    private String login;
    private String senha;
    
    public Usuario(int id, String dataCadastro, String nome, String telefone, String email, String sexo, String login, String senha)
    {
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario()
    {
        this.id = 0;
        this.dataCadastro = "";
        this.nome = "";
        this.telefone = "";
        this.email = "";
        this.sexo = "";
        this.login = "";
        this.senha = "";
    }
    
    public int getId() {
        return this.id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
