/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import produtoCollection.ProdutoCollection;
import view.ListagemProdutoView;
import observer.ProdutoObserver;

/**
 *
 * @author maikr
 */
public class ListagemProdutoPresenter implements ProdutoObserver{
    private ProdutoCollection produtos;
    private ListagemProdutoView view;
    
    public ListagemProdutoPresenter(ProdutoCollection produtos) {
        this.produtos = produtos;
        this.view = new ListagemProdutoView();
        produtos.addObserver(this);
        this.view.setVisible(false);
        configuraView();
        this.view.setVisible(true);
    }
    
    private void configuraView(){
        atualizar();
        
        view.getTblListaProduto().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                atualizabotao();
            }
        });
        
        view.getBtnRemover().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                remover();
            }
        });
    }
    
    private void remover(){
        int linhaselecionada = view.getTblListaProduto().getSelectedRow();
        if (linhaselecionada >= 0) {
            int confirm = JOptionPane.showConfirmDialog(view, "Deseja remover o produto selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                produtos.remover(linhaselecionada);
            }
        }
    }
    
    private void atualizabotao(){
        view.getBtnRemover().setEnabled(view.getTblListaProduto().getSelectedRow() >= 0);
    }
    
    @Override
    public void atualizar(){
        //aprimorar o uso da JTable
        DefaultTableModel tabela = (DefaultTableModel) view.getTblListaProduto().getModel();
        tabela.setRowCount(0);
        for (Produto produto : produtos.getProdutos()) {
            tabela.addRow(new Object[]{
                produto.getNome(),
                produto.getPrecoCusto(),
                produto.getPercentualLucro(),
                produto.getPrecoVenda()
            });
        }
    }
}
