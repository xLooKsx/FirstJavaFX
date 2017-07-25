/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.view;

import br.pessoal.controller.Main;
import br.pessoal.model.Usuario;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author lucas.oliveira
 */
public class LoginController {
    
    private Main main;
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private PasswordField pwSenha;
    
    @FXML
    private Label lblInfo;
    
    @FXML
    public void initialize(){
    }

    public void setMain(Main main) {
        this.main = main;
    }
    
    
    @FXML
    public void handleOk(){
        
        if (camposValidos() && loginValido(main.getUsuarios())) {
            main.showPainelBase();
            main.showJanelaStatus();
        }else{
            lblInfo.setText("Entrada Negada");
        }
    }
    
    public boolean loginValido(List<Usuario>usuarios){
    
        if (camposValidos()) {
            for (Usuario usuarioDaVez : usuarios) {
                if (usuarioDaVez.getUsuario().equals(txtUsuario.getText()) && usuarioDaVez.getSenha().equals(pwSenha.getText())) {
                    main.setUsuario(usuarioDaVez);
                    return true;
                }
            }
        }        
        return false;
    }
    
    @FXML
    private void handleRecuperarSenha(){
        main.showJanelaRecuperacaoSenha();
    }
    
    private boolean camposValidos(){
        String mensagemErro="";
        
        if (txtUsuario.getText() == null || txtUsuario.getText().trim().length() == 0) {
            mensagemErro += "Usuario Invalido\n";
        }
        if (pwSenha.getText() == null || pwSenha.getText().trim().length() == 0) {
            mensagemErro +="Senha invalida\n";
        }
        
        if (mensagemErro.length() == 0) {
            return true;
        }else {
            //Mostra mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor, corrija os campos invalidos");
            alert.setContentText(mensagemErro);
            alert.showAndWait();
        }
        
        return false;
    }
}
