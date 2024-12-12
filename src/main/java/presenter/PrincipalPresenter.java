/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Produto;
import observer.ProdutoObserver;
import produtoCollection.ProdutoCollection;
import view.PrincipalView;

/**
 *
 * @author maikr
 */
public class PrincipalPresenter implements ProdutoObserver{

    private Produto produto;
    private PrincipalView principalview;
    private ProdutoCollection produtos;
    private InclusaoProdutoPresenter inclusao;
    private ListagemProdutoPresenter listagem;
    
    public PrincipalPresenter(ProdutoCollection produtos) {
        this.produtos = produtos;
        this.principalview = new PrincipalView();
        produtos.addObserver(this);
        this.principalview.setVisible(false);
        configuraView();
        this.principalview.setVisible(true); 
    }
    
    private void configuraView(){
        atualizar();
        principalview.getBtnIncluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                incluir();
            }
        });
        principalview.getBtnListar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                listar();
            }
        });
        
    }
    
    private void incluir(){
        new InclusaoProdutoPresenter(produtos);
    }
    
    private void listar(){
        new ListagemProdutoPresenter(produtos);
    }
    
    @Override
    public void atualizar(){
        principalview.getLblTotalProdutosCadastrados().setText("Total de produtos cadastrados: " + produtos.getProdutos().size());
        principalview.getPrTotalProdutosCadastrados().setValue(produtos.getProdutos().size());
    }
    
}
