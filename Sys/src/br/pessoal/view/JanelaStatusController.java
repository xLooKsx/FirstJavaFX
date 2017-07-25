/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.view;

import br.pessoal.controller.Main;
import br.pessoal.model.Pendencia;
import br.pessoal.model.Pessoa;
import br.pessoal.util.SysUtils;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 *
 * @author lucas.oliveira
 */
public class JanelaStatusController {
    
    @FXML
    private TableView<Pendencia> tabelaPendencia; 
    @FXML
    private TableColumn<Pendencia, String> colunaPendencia;
    
    @FXML
    private Label lblqtdFuncionarioDisponivel;
    @FXML
    private Label lblqtdFuncionarioIndisponivel;    
    @FXML
    private Label lblqtdPendencia;
    @FXML
    private Label lblVlrTitulo;
    @FXML
    private Label lblVlrConteudo;
    @FXML
    private Label lblVlrDataAbertura;
    
    
    private Main main;
    private SysUtils sysutils = new SysUtils();
    private ObservableList<Pendencia> pendenciasNaoFinalizadas = FXCollections.observableArrayList();
    
    public JanelaStatusController() {
    }    
    
    @FXML
    private void initialize(){
        colunaPendencia.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());        
        
        mostraDetalhePendencia(null);        
        tabelaPendencia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostraDetalhePendencia(newValue));
    }    
    
    public void mostraStatus(){                
        lblqtdFuncionarioDisponivel.setText(Integer.toString(sysutils.getQtdFuncionario(main.getPessoas(), true)));
        lblqtdFuncionarioIndisponivel.setText(Integer.toString(sysutils.getQtdFuncionario(main.getPessoas(), false)));
        lblqtdPendencia.setText(Integer.toString(main.getUsuario().getPendencias().size()));
       tabelaPendencia.setItems(main.getUsuario().getPendenciasNaoFinalizadas());
    }
    
    private void mostraDetalhePendencia(Pendencia pendencia){
    
        if (pendencia != null) {
            lblVlrTitulo.setText(pendencia.getTitulo());
            lblVlrConteudo.setText(pendencia.getConteudo());
            lblVlrDataAbertura.setText(SysUtils.formatarData(pendencia.getDataAbertura()));
        }else{
            lblVlrTitulo.setText("");
            lblVlrConteudo.setText("");
            lblVlrDataAbertura.setText("");
        }
    }
    
    @FXML
    private void handleFinalizaPendencia(){
        Pendencia pendenciaSelecionada = tabelaPendencia.getSelectionModel().getSelectedItem();
        if (pendenciaSelecionada != null) {
            pendenciaSelecionada.setFinalizada(true);
            main.getUsuario().getPendenciasNaoFinalizadas().remove(pendenciaSelecionada);            
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Operação invalida");
            alert.setHeaderText("Operação invalida");
            alert.setContentText("Selecione alguma pendencia para finalizar");
            alert.showAndWait();
        }        
    }    

    @FXML
    private void handleShowJanelaPendencia(){
        main.showJanelaPendencias();
    }
    
    public void setMain(Main main) {
        this.main = main;
        
    }    
}
