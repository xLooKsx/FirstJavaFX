/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pessoal.util;

import br.pessoal.model.Pessoa;
import br.pessoal.view.JanelaStatusController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author lucas.oliveira
 */
public class SysUtils {        
    
    private static final String DATE_PATTERN = "dd.MMMM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);    
    
    public int getQtdFuncionario(List<Pessoa> pessoas, boolean tipoDisponivel){
        
        int qtd=0;
        if (tipoDisponivel) {
            for (Pessoa pessoaDaVez : pessoas) {
                if (pessoaDaVez.getDisponivel()) {
                    qtd+= 1;
                }
            }            
        }else{
            for (Pessoa pessoaDaVez : pessoas) {
                if (!pessoaDaVez.getDisponivel()) {
                    qtd+= 1;
                }
            }
        }
        return qtd;
    }
    
    public static String formatarData(LocalDate data){
        if (data == null) {
            return null;
        }
        return DATE_FORMATTER.format(data);
    }
    
    public boolean validaCPF(String cpf) {        
        char dig10;
        char dig11;        
        int soma;
        int peso;
        int num;
        int resto;
        
        if (cpf.equals("00000000000")|| cpf.equals("11111111111") ||
            cpf.equals("22222222222")|| cpf.equals("33333333333") ||
            cpf.equals("44444444444")|| cpf.equals("55555555555") ||
            cpf.equals("66666666666")|| cpf.equals("77777777777") ||
            cpf.equals("88888888888")|| cpf.equals("99999999999") ||
            cpf.length() != 11) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo invalido");
            alert.setHeaderText("Campo invalido");
            alert.setContentText("CPF invalido");
            alert.showAndWait();
            
            return false;
        }
        
        try {
            //Calculo do 1ºDigito
            soma = 0;
            peso = 10;
            for (int i = 0; i < 9; i++) {
                num = (int)(cpf.charAt(i) - 48);
                soma +=(num * peso);
                peso--;
            }
            
            resto = 11 - (soma%11);
            if ((resto == 10)||(resto == 11)) {
                dig10 = '0';
            }else{
                dig10 = (char)(resto+48);
            }
            
            //Calculo do 2ºDigito
            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                soma += (num*peso);
                peso--;
            }
            
            resto = 11 - (soma%11);
            if ((resto == 10) || (resto == 11)){
                dig11 = '0';
            }else{
                dig11 = (char)(resto + 48);
            }
            
            //Verifica se os digitos calculados são iguais aos digitados
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return true;
            }else{
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Campo invalido");
                alert.setHeaderText("Campo invalido");
                alert.setContentText("CPF invalido");
                alert.showAndWait();                                
            }
        } catch (InputMismatchException e) {            
            e.printStackTrace();
        }
        return false;
    }
   
}
