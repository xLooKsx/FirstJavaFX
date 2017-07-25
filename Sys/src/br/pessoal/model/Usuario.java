/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.model;

import com.sun.javafx.collections.ObservableListWrapper;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author lucas.oliveira
 */
public class Usuario extends Pessoa{
    
    private StringProperty usuario;
    private StringProperty senha;
    private StringProperty perguntaSeguranca;
    private StringProperty respostaSeguranca;
    private ObservableList<Pendencia> pendencias;

    private ObservableList<Pendencia> pendenciasNaoFinalizadas = FXCollections.observableArrayList();
    private ObservableList<Pessoa> funcionarios;
    
    public Usuario() {        
        
    }

    public Usuario(String usuario, String senha, String perguntaSeguranca, String respostaSeguranca, String cargo, String nome, String sobrenome, String cpf, int telefone, String email, LocalDate dataNascimento, ObservableList<Pendencia> pendencias, boolean disponivel, ObservableList<Pessoa> funcionarios) {
        super(cargo, nome, sobrenome, cpf, telefone, email, dataNascimento, disponivel);
        this.usuario = new SimpleStringProperty(usuario);
        this.senha = new SimpleStringProperty(senha);
        this.perguntaSeguranca = new SimpleStringProperty(perguntaSeguranca);
        this.respostaSeguranca = new SimpleStringProperty(respostaSeguranca);
        this.pendencias = new ObservableListWrapper<Pendencia>(pendencias);
        this.funcionarios = new ObservableListWrapper<Pessoa>(funcionarios);
    }

    /**
     * Usuario
     * @return 
     */
    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public StringProperty usuarioProperty(){
        return usuario;
    }
    
    /**
     * Senha
     * @return 
     */
    public String getSenha() {
        return senha.get();
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }
    
    public StringProperty senhaProperty(){
        return senha;
    }

    /**
     * Pergunta de Segurança
     * @return 
     */
    public String getPerguntaSeguranca() {
        return perguntaSeguranca.get();
    }

    public void setPerguntaSeguranca(String perguntaSeguranca) {
        this.perguntaSeguranca.set(perguntaSeguranca);
    }
    
    public StringProperty perguntaSegurancaProperty(){
        return perguntaSeguranca;
    }    

    /**
     * Resposta de Segurança
     * @return 
     */
    public String getRespostaSeguranca() {
        return respostaSeguranca.get();
    }

    public void setRespostaSeguranca(String respostaSeguranca) {
        this.respostaSeguranca.set(respostaSeguranca);
    }    
    
    public StringProperty respostaSegurancaProperty(){
        return respostaSeguranca;
    }
    
    /**
     * Pendencias
     * @return 
     */
    public ObservableList<Pendencia> getPendencias() {
        return pendencias;
    }
    
    /**
     * Funcionarios
     * @return 
     */
    public ObservableList<Pessoa> getFuncionarios(){
        return funcionarios;
    }
    
    public ObservableList<Pendencia> getPendenciasNaoFinalizadas(){        
        for (Pendencia pendenciaDaVez : pendencias) {            
            if (!pendenciaDaVez.getFinalizada() && !pendenciasNaoFinalizadas.contains(pendenciaDaVez)) {
                pendenciasNaoFinalizadas.add(pendenciaDaVez);
            }
        }
        return pendenciasNaoFinalizadas;
    }
    
}
