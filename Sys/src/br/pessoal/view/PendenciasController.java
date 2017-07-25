/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.view;

import br.pessoal.controller.Main;
import br.pessoal.model.Pendencia;
import br.pessoal.util.SysUtils;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author lucas.oliveira
 */
public class PendenciasController {
    
    @FXML
    private TableView<Pendencia> tabelaPendencia; 
    @FXML
    private TableColumn<Pendencia, String> colunaPendencia;
    
    @FXML
    private Node panelCriarPendencias;
    @FXML
    private Node panelAlterarPendencia;
    
    @FXML
    private TextField txtCriarTituloPendencia;
    @FXML
    private TextField txtCriarConteudoPendencia;
    @FXML
    private TextField txtAlterarTituloPendencia;
    @FXML
    private TextField txtAlterarConteudoPendencia;
    
    @FXML
    private CheckBox checkBoxImportanteCriarPendencia;
    @FXML
    private CheckBox checkBoxIimportanteAlterarPendencia;
    
    @FXML
    private Label lblIsFinalizado;
    
    private Main main;

    public PendenciasController() {
    }    
    
    @FXML
    private void initialize(){
        colunaPendencia.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        mostraDetalhePendencia(null);
        tabelaPendencia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostraDetalhePendencia(newValue));
    }
    
    private void mostraDetalhePendencia(Pendencia pendencia){
    
        if (pendencia != null) {
            txtAlterarTituloPendencia.setText(pendencia.getTitulo());
            txtAlterarConteudoPendencia.setText(pendencia.getConteudo());
            checkBoxIimportanteAlterarPendencia.setSelected(pendencia.getImportante());
            lblIsFinalizado.setText(pendencia.getFinalizada()==true?"Sim":"Não");
        }else{
            txtAlterarTituloPendencia.setText("");
            txtAlterarConteudoPendencia.setText("");
            checkBoxIimportanteAlterarPendencia.setSelected(false);
            lblIsFinalizado.setText("");
        }
    }
    
    public void mostrarPendencias(){
        tabelaPendencia.setItems(main.getUsuario().getPendencias());
    }
    
   @FXML
   private void handleSalvarNovaPendencia(){
       if (isCriaInputValid()){
           Pendencia pendencia = new Pendencia();
           pendencia.setTitulo(txtCriarTituloPendencia.getText());
           pendencia.setConteudo(txtCriarConteudoPendencia.getText());
           pendencia.setDataAbertura(LocalDate.now());
           pendencia.setFinalizada(false);
           pendencia.setImportante(checkBoxImportanteCriarPendencia.isSelected()); 
           
           main.getUsuario().getPendencias().add(pendencia);
           panelCriarPendencias.setDisable(true);
           
           limparPanelCriarPendencia();
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adição");
            alert.setHeaderText("Adição Valida");
            alert.setContentText("A pendencia selecionada foi Adicionada o com sucesso");
            alert.showAndWait();
       }else{
           panelCriarPendencias.setDisable(true);
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adição");
            alert.setHeaderText("Adição Invalida");
            alert.setContentText("Falha ao adicionar uma pendencia");
            alert.showAndWait();
       }
   }
    
   @FXML
   private void handleAlterarPendencia(){
       Pendencia pendenciaSelecionada = tabelaPendencia.getSelectionModel().getSelectedItem();
       if (isAlteraInputValid() && pendenciaSelecionada != null){
           
           pendenciaSelecionada.setTitulo(txtAlterarTituloPendencia.getText());
           pendenciaSelecionada.setConteudo(txtAlterarConteudoPendencia.getText());           
           pendenciaSelecionada.setFinalizada(false);
           pendenciaSelecionada.setImportante(checkBoxIimportanteAlterarPendencia.isSelected());           
           
           panelAlterarPendencia.setDisable(true);
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alteração");
            alert.setHeaderText("Alteração Valida");
            alert.setContentText("A pendencia selecionada foi Alterada com sucesso");
            alert.showAndWait();
            
       }else{
           panelAlterarPendencia.setDisable(true);
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adição");
            alert.setHeaderText("Alteração Invalida");
            alert.setContentText("Falha ao alterar uma pendencia");
            alert.showAndWait();           
       }
   }
   
   @FXML
    private void handleExcluirPendencia(){
        Pendencia pendenciaSelecionada = tabelaPendencia.getSelectionModel().getSelectedItem();
        if (pendenciaSelecionada != null) {            
            main.getUsuario().getPendencias().remove(pendenciaSelecionada);            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exclusão");
            alert.setHeaderText("Exclusão Valida");
            alert.setContentText("A pendencia selecionada foi excluida com sucesso");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exclusão");
            alert.setHeaderText("Exclusão Invalida");
            alert.setContentText("Falha ao excluir uma pendencia");
            alert.showAndWait();        
        }       
    }
   
    @FXML
    private void btnCriarPendencia(){
        panelCriarPendencias.setDisable(false);
        panelAlterarPendencia.setDisable(true);
    }
    
    @FXML
    private void btnAlterarPendencia(){
        panelAlterarPendencia.setDisable(false);
        panelCriarPendencias.setDisable(true);
    }

    @FXML
    private void handleVoltar(){
        main.showJanelaStatus();
    }
    
    private boolean isCriaInputValid() {
        String errorMessage = "";
        
        if (txtCriarTituloPendencia.getText() == null || txtCriarTituloPendencia.getText().trim().length() == 0) {
            errorMessage += "Titulo de pendencia invalido\n";
        }
        if (txtCriarConteudoPendencia.getText() == null || txtCriarConteudoPendencia.getText().trim().length() == 0) {
            errorMessage += "conteudo de pendencia invalido\n";            
        }                
        if (errorMessage.trim().length() == 0) {
            return true;
        }else{
        
            //Mostra mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor, corrija os campos invalidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        return false;
    }
    
    private void limparPanelCriarPendencia() {
        
        txtCriarConteudoPendencia.setText("");
        txtCriarTituloPendencia.setText("");
        checkBoxImportanteCriarPendencia.setSelected(false);
    }
    
    private boolean isAlteraInputValid() {
        String errorMessage = "";
         Pendencia pendenciaSelecionada = tabelaPendencia.getSelectionModel().getSelectedItem();
        if (pendenciaSelecionada != null) {
        
        if (txtAlterarTituloPendencia.getText() == null || txtAlterarTituloPendencia.getText().trim().length() == 0) {
            errorMessage += "Titulo de pendencia invalido\n";
        }
        if (txtAlterarConteudoPendencia.getText() == null || txtAlterarConteudoPendencia.getText().trim().length() == 0) {
            errorMessage += "conteudo de pendencia invalido\n";            
        }     
        }else{
            errorMessage += "Favor selecionar uma pendencia para alterar";
        }
        if (errorMessage.trim().length() == 0) {
            return true;
        }else{
        
            //Mostra mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos invalidos");
            alert.setHeaderText("Por favor, corrija os campos invalidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        return false;
    }
    
    public void setMain(Main main) {
        this.main = main;
    }    
}
