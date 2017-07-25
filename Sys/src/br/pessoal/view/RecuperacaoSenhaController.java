/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.view;

import br.pessoal.controller.Main;
import br.pessoal.model.Usuario;
import br.pessoal.util.SysUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author lucas.oliveira
 */
public class RecuperacaoSenhaController {
    
    @FXML
    private TextField txtCpf;
    @FXML
    private PasswordField pwNovaSenha;
    @FXML
    private PasswordField pwConfirmaSenha;        
    
    @FXML
    private Node panelPasso2;
    @FXML
    private Node panelPasso1;
    
    private Stage janelaDialogo;
    private Usuario usuario;
    private Main main;
    
    @FXML
    private void initialize(){                
    }

    @FXML
    private void handleSalvar(){
        if (validaSenha()) {
            if (pwNovaSenha.getText().equals(pwConfirmaSenha.getText())) {                
                
                
                this.usuario.setSenha(pwNovaSenha.getText());
                main.setUsuario(this.usuario);
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Operação concluida");
                alert.setHeaderText("Senha salva");
                alert.setContentText("Senha alterada com sucesso");
                alert.showAndWait();
                
                janelaDialogo.close();
            }else{                                
            
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Falha na operação");
                alert.setHeaderText("Erro");
                alert.setContentText("As senhas não são identicas");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    private void handleValidar(){        
        SysUtils sysUtils = new SysUtils();
        
        if (validaCampoCpf()) {                    
            if (sysUtils.validaCPF(txtCpf.getText())){
                for (Usuario usuarioDaVez : main.getUsuarios()) {
                    if (usuarioDaVez.getCpf().equals(txtCpf.getText())){
                        panelPasso2.setDisable(false);
                        panelPasso1.setDisable(true);                                        
                        this.usuario = usuarioDaVez;
                    }else{

                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Dado não encontrado");
                        alert.setHeaderText("CPF inexistente");
                        alert.setContentText("O cpf informado não esta cadastrado");
                        alert.showAndWait();
                    }               
                }            
            }
        }
    }
    
    private boolean validaSenha() {
        String menssagemError="";
        
        if (pwNovaSenha.getText().trim().length() == 0 || pwNovaSenha.getText() == null) {
            menssagemError += "Campo 'Nova Senha' deve ser preenchido";
        }
        if (pwConfirmaSenha.getText().trim().length() == 0 || pwConfirmaSenha.getText() == null) {
            menssagemError += "Campo 'Confirmação Nova Senha' deve ser preenchido";
        }
        if (menssagemError.length() == 0) {            
            return true;                        
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Campo invalido");
            alert.setHeaderText("Campo não preenchido");
            alert.setContentText(menssagemError);
            alert.showAndWait();
        }
        return false;
    }    
    
    private boolean validaCampoCpf() {
        String menssagemError="";
        
        if (txtCpf.getText().trim().length() == 0 || txtCpf.getText() == null) {
            menssagemError += "O campo 'CPF' deve ser preenchido";
        }        
        if (menssagemError.length() == 0) {            
            return true;                        
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Campo invalido");
            alert.setHeaderText("Campo não preenchido");
            alert.setContentText(menssagemError);
            alert.showAndWait();
        }
        return false;
    }  
       
    
    public void setJanelaDialogo(Stage janelaDialogo) {
        this.janelaDialogo = janelaDialogo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setMain(Main main) {
        this.main = main;
    }


    
    
}
