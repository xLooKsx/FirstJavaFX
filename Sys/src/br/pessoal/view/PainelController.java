/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.view;

import br.pessoal.controller.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author lucas.oliveira
 */
public class PainelController {
    
    private static Main main;
    EditarDadosController editarDadosController = new EditarDadosController();

    public void setMain(Main main) {
        this.main = main;
    }
    
    @FXML
    private void handleTrocarNomeUsuario(){        
        editarDadosController.setTrocaNomeUsuario(true);
        main.mostrarJanelaEdicao();
    }
    
    @FXML
    private void handleTrocarSenhaUsuario(){
        editarDadosController.setTrocaNomeUsuario(false);
        main.mostrarJanelaEdicao();
    }
    
    @FXML
    private void handleSobre(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sys-tema");
        alert.setHeaderText("Sys-tema");
        alert.setContentText("Autor: Lucas Oliveira");
        alert.showAndWait();
    }
    
    @FXML
    private void handleLogout(){
        main.showLogin();
    }
    
    @FXML
    private void handleSair(){
        System.exit(0);
    }
}
