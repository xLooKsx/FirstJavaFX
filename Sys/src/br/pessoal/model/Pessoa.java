/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.model;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author lucas.oliveira
 */
public class Pessoa {
    
    private StringProperty cargo;
    private StringProperty nome;
    private StringProperty sobrenome;
    private StringProperty cpf;
    private IntegerProperty telefone;
    private StringProperty email;
    private ObjectProperty<LocalDate> dataNascimento;
    private BooleanProperty disponivel;

    public Pessoa() {
        this(null, null, null, null, 0, null, null, false);
    }

    public Pessoa(String cargo, String nome, String sobrenome, String cpf, int telefone, String email, LocalDate dataNascimento, boolean disponivel) {
        this.cargo = new SimpleStringProperty(cargo);
        this.nome = new SimpleStringProperty(nome);
        this.sobrenome = new SimpleStringProperty(sobrenome);
        this.cpf = new SimpleStringProperty(cpf);
        this.telefone = new SimpleIntegerProperty(telefone);
        this.email = new SimpleStringProperty(email);
        this.dataNascimento = new SimpleObjectProperty<LocalDate>(dataNascimento);
        this.disponivel = new SimpleBooleanProperty(disponivel);
    }
        
    /**
     * Telefone
     * @return 
     */
    public Integer getTelefone() {
        return telefone.get();
    }

    public void setTelefone(int telefone) {
        this.telefone.set(telefone);
    }

    public IntegerProperty telefoneProperty(){
        return telefone;
    }
    
    /**
     * Data de Nascimento
     * @return 
     */
    public LocalDate getDataNascimento() {
        return dataNascimento.get();
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento.set(dataNascimento);
    }

    public ObjectProperty<LocalDate> dataNascimentoProperty(){
        return dataNascimento;
    }
    
    /**
     * Nome
     * @return 
     */
    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }
    
    public StringProperty nomeProperty(){
        return nome;
    }

    /**
     * Sobrenome
     * @return 
     */
    public String getSobrenome() {
        return sobrenome.get();
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome.set(sobrenome);
    }

    public StringProperty sobrenomeProperty(){
        return nome;
    }
    
    /**
     * CPF
     * @return 
     */
    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }
    
    public StringProperty cpfProperty(){
        return cpf;
    }

    /**
     * Cargo
     * @return 
     */
    public String getCargo() {
        return cargo.get();
    }

    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }

    public StringProperty cargoProperty(){
        return cargo;
    }
    /**
     * Email
     * @return 
     */
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public StringProperty emailProperty(){
        return email;
    }

    /**
     * Disponivel
     * @return 
     */
    public boolean getDisponivel() {
        return disponivel.get();
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel.set(disponivel);
    }
    
    public BooleanProperty disponivelProperty(){
        return disponivel;
    }
}
