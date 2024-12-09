/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import presenter.InclusaoProdutoPresenter;
import produtoCollection.ProdutoCollection;

/**
 *
 * @author maikr
 */
public class Principal {

    public static void main(String[] args) {
        ProdutoCollection produtos = new ProdutoCollection();
        new InclusaoProdutoPresenter(produtos);
    }
}
