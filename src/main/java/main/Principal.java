/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import presenter.*;
import produtoCollection.ProdutoCollection;

/**
 *
 * @author maikr
 */
public class Principal {

    public static void main(String[] args) {
        ProdutoCollection produtos = new ProdutoCollection();
        new PrincipalPresenter(produtos);
    }
}
