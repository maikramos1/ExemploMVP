/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package produtoCollection;

import model.Produto;
import observer.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author maikr
 */
public class ProdutoCollection {

    private List<Produto> produtos;
    private List<ProdutoObserver> observadores;

    public ProdutoCollection() {
        produtos = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    public void incluir(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Informe um produto válido");
        }
        produtos.add(produto);
        notificar();
    }
    
    public void remover(int indice) {
        if (indice >= 0 && indice < produtos.size()) {
            produtos.remove(indice);
            notificar();
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Optional<Produto> findProdutoByNome(String nome) {
        Optional<Produto> optProduto = null;
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return optProduto.of(produto);
            }
        }
        return optProduto.empty();
    }
    
    public void addObserver(ProdutoObserver observer) {
        observadores.add(observer);
    }

    private void notificar() {
        for (ProdutoObserver observador : observadores) {
            observador.atualizar();
        }
    }
}
