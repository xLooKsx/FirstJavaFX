/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.controller;

import br.pessoal.model.Pendencia;
import br.pessoal.model.Pessoa;
import br.pessoal.model.Usuario;
import br.pessoal.view.JanelaStatusController;
import br.pessoal.view.LoginController;
import br.pessoal.view.EditarDadosController;
import br.pessoal.view.PainelController;
import br.pessoal.view.PendenciasController;
import br.pessoal.view.RecuperacaoSenhaController;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author lucas.oliveira
 */
public class Main extends Application{
    
    private Stage primaryStage;
    private BorderPane painelBase;
    private Usuario usuario;
    
    private ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    private ObservableList<Pendencia> pendencias = FXCollections.observableArrayList();
    
    public Main() {
        /**
         * pendencias
         */
        pendencias.add(new Pendencia("Estoque", "aumenta a quantidade", LocalDate.now(), true, true));
        pendencias.add(new Pendencia("Reunião", "Sala 2 as 14h", LocalDate.now(), true, true));
        pendencias.add(new Pendencia("Ferias", "=D", LocalDate.now(), false, false));
        pendencias.add(new Pendencia("Eletropaulo", "Falta de energia o dia inteiro", LocalDate.now(), false, false));
        pendencias.add(new Pendencia("Manutenção", "conserto da maquina de café", LocalDate.now(), false, false));
        
        /**
         * Pessoas
         */
        pessoas.add(new Pessoa("alto", "João", "Antonio", "123456789", 37460347, "x@x.com", LocalDate.of(1884, Month.MARCH, 21), true));
        pessoas.add(new Pessoa("medio", "Antonio", "João", "654861328", 37460347, "z@x.com", LocalDate.of(1554, Month.JULY, 01), false));
        pessoas.add(new Pessoa("baixo", "Maria", "Claudia", "218654875", 37460347, "c@x.com", LocalDate.of(2013, Month.SEPTEMBER, 12), true));
        pessoas.add(new Pessoa("medio-baixo", "Claudia", "Maria", "214851322", 37460347, "v@x.com", LocalDate.of(2010, Month.MARCH, 8), false));
        pessoas.add(new Pessoa("medio-alto", "Antonieta", "Albuquerque", "321598765", 37460347, "a@x.com", LocalDate.of(1957, Month.JANUARY, 26), true));
        
        /**
         * Usuario
         */
        usuarios.add(new Usuario("LooKs", "123", "nenhumaPerguntaAqui","lol" ,"admin", "Lucas", "Oliveira", "12345678909", 37460347, "x@x.com", LocalDate.of(1998, Month.AUGUST, 22), pendencias, true, pessoas));        
        
    }
    
    
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sys-tema");
        
        showLogin();                
    }
    
    public void showLogin(){        
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/br/pessoal/view/Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();
            
            Scene scene = new Scene(login);
            this.primaryStage.setScene(scene);
            
            LoginController loginController = loader.getController();
            loginController.setMain(this);            
            
            this.primaryStage.show();
        }catch(IOException e){
            System.out.println("Erro ao carregar o Login: " + e.getMessage());
        }    
    }
    
    public void showJanelaStatus(){
        try {            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/br/pessoal/view/JanelaStatus.fxml"));
            AnchorPane janelaStatus = (AnchorPane) loader.load();
            
            painelBase.setCenter(janelaStatus);
            
            JanelaStatusController janelaStatusController = loader.getController();
            janelaStatusController.setMain(this);            
            janelaStatusController.mostraStatus();
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showPainelBase(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/br/pessoal/view/Painel.fxml"));
            painelBase = (BorderPane) loader.load();
            
            
            primaryStage.setTitle("Sys-tema");            
            
            Scene scene = new Scene(painelBase);
            primaryStage.setScene(scene);
            
            PainelController painelControler = new PainelController();
            painelControler.setMain(this);
            
            primaryStage.show();
        }catch(IOException e){}
    }
    
    public void mostrarJanelaEdicao() {        
        try{            
            /**
             * Carrega o arquivo FXML
             */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/br/pessoal/view/EditarDados.fxml"));
        AnchorPane pagina = (AnchorPane) loader.load();
        
        /**
         * Define uma janela de dialogo
         */
        Stage janelaDialogo = new Stage();
        janelaDialogo.setTitle("Editar Nome de Usuario");
        janelaDialogo.initModality(Modality.WINDOW_MODAL);
        janelaDialogo.initOwner(primaryStage);
        
        /**
         * Coloca a pagina dentro da cena e a cena dentro da janela de dialogo
         */
        Scene cena = new Scene(pagina);
        janelaDialogo.setScene(cena);
        
        EditarDadosController editarDadosController = loader.getController();
        editarDadosController.setDialogStage(janelaDialogo);
        editarDadosController.setUsuario(this.usuario);        
        editarDadosController.setMain(this);
        
        janelaDialogo.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void showJanelaPendencias(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/br/pessoal/view/Pendencias.fxml"));
            AnchorPane janelaPendencias = (AnchorPane) loader.load();
            
            painelBase.setCenter(janelaPendencias);
            
            PendenciasController pendenciasController = loader.getController();
            pendenciasController.setMain(this);
            pendenciasController.mostrarPendencias();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showJanelaRecuperacaoSenha(){
        try{
        //Carrega o arquivo FXML
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/br/pessoal/view/RecuperacaoSenha.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //Cria uma janela de dialogo
        Stage janelaDialogo = new Stage();
        janelaDialogo.setTitle("Recuperar Senha");
        janelaDialogo.initModality(Modality.WINDOW_MODAL);
        janelaDialogo.initOwner(primaryStage);
        
        Scene cena = new Scene(page);
        janelaDialogo.setScene(cena);
                
        RecuperacaoSenhaController recuperacaoSenhaController = loader.getController();
        recuperacaoSenhaController.setJanelaDialogo(janelaDialogo);
        recuperacaoSenhaController.setUsuario(this.usuario);
        recuperacaoSenhaController.setMain(this);
        
        janelaDialogo.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
        
    public ObservableList<Pessoa> getPessoas() {
        return pessoas;
    }

    public ObservableList<Usuario> getUsuarios() {
        return usuarios;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    

    
}
