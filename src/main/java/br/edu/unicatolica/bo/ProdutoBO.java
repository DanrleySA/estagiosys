package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.filter.ProdutoFilter;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Danrley
 */
public class ProdutoBO implements Serializable {

    private static ProdutoBO instance;

    private ProdutoBO() {

    }

    public static ProdutoBO getInstance() {
        if (instance == null) {
            instance = new ProdutoBO();
        }
        return instance;
    }

    public void salvarOuAtualizar(Produto produto) {
        ProdutoDAO.getInstance().salvarOuAtualizar(produto);
    }

    public void remover(Produto produto) {
        ProdutoDAO.getInstance().remover(produto);
    }

    public List<Produto> getProdutos(ProdutoFilter produtoFilter) {
        return ProdutoDAO.getInstance().getProdutos(produtoFilter);
    }

    public List<Produto> getProdutosComEstoque() {
        return ProdutoDAO.getInstance().getProdutosComEstoque();
    }
}
