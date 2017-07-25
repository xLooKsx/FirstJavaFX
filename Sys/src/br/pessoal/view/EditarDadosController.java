/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.view;

import br.pessoal.controller.Main;
import br.pessoal.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author lucas.oliveira
 */
public class EditarDadosController {
    
    @FXML
    private TextField txtNomeUsuario;
    @FXML
    private PasswordField pwSenhaAntiga;
    @FXML
    private PasswordField pwNovaSenha;
    @FXML
    private PasswordField pwConfirmaNovaSenha;
    
    @FXML
    private Node panelUsuario;
    @FXML
    private Node panelSenha;
    
    private static boolean trocaNomeUsuario;
    private Stage janelaDialogo;
    private Usuario usuario;
    private Main main;

    @FXML
    private void initialize(){
        mostraCamposDisponiveis();
    }
    
    public void setDialogStage(Stage janelaDialogo) {
        this.janelaDialogo = janelaDialogo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void mostraCamposDisponiveis(){
        if (this.trocaNomeUsuario) {
            panelSenha.setDisable(true);
        }else{
            panelUsuario.setDisable(true);
        }
    }
    
    @FXML
    private void handleSalvar(){
        if (validaCampos()) {
            if (trocaNomeUsuario) {
                this.usuario.setUsuario(txtNomeUsuario.getText());

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmação de alteração");
                alert.setHeaderText("Usuario Alterado com sucesso!");
                alert.setContentText("Nome de usuario alerado com sucesso");
                alert.showAndWait();

                System.out.println("Novo nome de usuario: "+ main.getUsuario().getUsuario());  
                janelaDialogo.close();
            }
            if (validaCampos() && !trocaNomeUsuario) {
                this.usuario.setSenha(pwNovaSenha.getText());

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmação de alteração");
                alert.setHeaderText("Usuario Alterado com sucesso!");
                alert.setContentText("Senha alerada com sucesso");
                alert.showAndWait();

                System.out.println("Nova senha: " + main.getUsuario().getSenha());
                janelaDialogo.close();
            }
        }        
    }
    
    private boolean validaCampos(){
        String mensagemErro="";
        
        if (trocaNomeUsuario) {
            if (txtNomeUsuario.getText().trim().length() == 0 || txtNomeUsuario.getText() == null) {
                mensagemErro += "Campo usuario invalido\n";
            }
        }else{            
            if (pwSenhaAntiga.getText().trim().length() != 0 || pwSenhaAntiga.getText() != null) {
                if(!pwSenhaAntiga.getText().equals(usuario.getSenha())){
                    mensagemErro += "Senha invalida\n";
                }
            }else{
                mensagemErro += "O campo de senha antiga deve ser preenchida\n";
            }
            
            if (pwNovaSenha.getText().trim().length() != 0 || pwNovaSenha.getText() != null || pwConfirmaNovaSenha.getText().trim().length() != 0 || pwConfirmaNovaSenha.getText() != null) {
                if (!pwNovaSenha.getText().equals(pwConfirmaNovaSenha.getText())) {
                    mensagemErro += "As senhas não conferem\n";
                }
            }else{
                mensagemErro += "O campo 'Nova senha' e 'Confirmação da nova senha devem ser preenchidas'\n";
            }
        }
        
        if (mensagemErro.length() == 0) {
            return true;
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor, corrija os campos invalidos");
            alert.setContentText(mensagemErro);
            alert.showAndWait();
        }
        return false;
    }
    
    @FXML
    private void handleCancelar(){
        janelaDialogo.close();
    }
    
    public void setTrocaNomeUsuario(boolean trocaNomeUsuario) {
        this.trocaNomeUsuario = trocaNomeUsuario;
    }

    public void setMain(Main main) {
        this.main = main;
    } 
}
