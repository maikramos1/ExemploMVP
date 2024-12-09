/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import model.Produto;
import produtoCollection.ProdutoCollection;
import view.InclusaoProdutoView;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author maikr
 */
public class InclusaoProdutoPresenter {
    private Produto produto;
    private InclusaoProdutoView view;
    private ProdutoCollection produtos;

    public InclusaoProdutoPresenter(ProdutoCollection produtos) {
        this.produtos = produtos;
        this.view = new InclusaoProdutoView();
        this.view.setVisible(false);
        configuraView();
        this.view.setVisible(true);
    }

    private void configuraView() {
        view.getBtnIncluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        
        view.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cancelar();
            }
        });
        
        view.getTxtPercentualLucro().getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            exibePrecoVenda();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            exibePrecoVenda();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            exibePrecoVenda();
        }
    });
        
        view.getTxtPrecoCusto().getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            exibePrecoVenda();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            exibePrecoVenda();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            exibePrecoVenda();
        }
       });

    }

    private void salvar() {
        String nome = view.getTxtNome().getText();
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        double precoCusto = Double.parseDouble(view.getTxtPrecoCusto().getText());
        if (precoCusto <= 0) {
            throw new IllegalArgumentException("Preço de custo deve ser maior que zero");
        }
        double percentualLucro = Double.parseDouble(view.getTxtPercentualLucro().getText());
        if (percentualLucro <= 0) {
            throw new IllegalArgumentException("Percentual de lucro deve ser maior que zero");
        }
        double precoVenda = Double.parseDouble(view.getTxtPrecoVenda().getText());
        if (precoVenda <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser maior que zero");
        }
        produto = new Produto(nome, precoCusto, percentualLucro);

        produtos.incluir(produto);

        JOptionPane.showMessageDialog(view, "Produto incluído com sucesso!");
        limpaCampos();
    }

    private void cancelar() {
        view.dispose();
    }
    
    private void exibePrecoVenda(){
        view.getTxtPrecoVenda().setText(String.valueOf(calculaPrecoVenda()));
    }
    
    private double calculaPrecoVenda(){
        try{
            if (view.getTxtPrecoCusto().getText().isEmpty()) return 0;
            if (view.getTxtPercentualLucro().getText().isEmpty()) return 0;
            double precoCusto = Double.parseDouble(view.getTxtPrecoCusto().getText());
            double percentualLucro = Double.parseDouble(view.getTxtPercentualLucro().getText());
            return precoCusto + (precoCusto * percentualLucro / 100);
        }
        catch(NumberFormatException nEx){
            JOptionPane.showMessageDialog(null, "Digite números em Preço Custo e/ou Percentual Lucro");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return 0;
    }
    
    private void limpaCampos(){
        view.getTxtNome().setText(null);
        view.getTxtPrecoCusto().setText(null);
        view.getTxtPercentualLucro().setText(null);
        view.getTxtPrecoVenda().setText(null);
    }
}
