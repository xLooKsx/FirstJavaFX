/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.model;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author lucas.oliveira
 */
public class Pendencia {
    
    private StringProperty titulo;
    private StringProperty conteudo;
    private ObjectProperty<LocalDate> dataAbertura;    
    private BooleanProperty importante;
    private BooleanProperty finalizada;

    public Pendencia() {
        this(null, null, null, false, false);
    }

    public Pendencia(String titulo, String conteudo, LocalDate dataAbertura, boolean importante, boolean finalizada) {
        this.titulo = new SimpleStringProperty(titulo);
        this.conteudo = new SimpleStringProperty(conteudo);
        this.dataAbertura = new SimpleObjectProperty<LocalDate>(dataAbertura);        
        this.importante = new SimpleBooleanProperty(importante);
        this.finalizada = new SimpleBooleanProperty(finalizada);
    }

    /**
     * Titulo
     * 
     * @return 
     */
    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }
    
    public StringProperty tituloProperty(){
        return titulo;
    }

    /**
     * Conteudo
     * 
     * @return 
     */
    public String getConteudo() {
        return conteudo.get();
    }

    public void setConteudo(String conteudo) {
        this.conteudo.set(conteudo);
    }

    public StringProperty conteudoProperty(){
        return conteudo;
    }
    
    /**
     * DataAbertura
     * @return 
     */
    public LocalDate getDataAbertura() {
        return dataAbertura.get();
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura.set(dataAbertura);
    }

    public ObjectProperty<LocalDate> dataAberturaProperty(){
        return dataAbertura;
    }
    
    /**
     * Importante
     * @return 
     */
    public boolean getImportante() {
        return importante.get();
    }

    public void setImportante(boolean importante) {
        this.importante.set(importante);
    }        

    public BooleanProperty importanteProperty(){
        return importante;
    }

    /**
     * Finalizada
     * 
     * @return 
     */
    public boolean getFinalizada() {
        return finalizada.get();
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada.set(finalizada);
    }
    
    public BooleanProperty finalizadaProperty(){
        return finalizada;
    }
}
