package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.filter.ProdutoFilter;
import br.edu.unicatolica.jsf.util.FacesUtil;
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

    public boolean isValido(Produto produto) {
        if (produto.getDescricao().replace(" ", "").equals("")) {
            FacesUtil.addErrorMessage("O campo descrição não pode estar em branco");
            return false;
        }
        if (produto.getValorUnitario().intValue() == 0) {
            FacesUtil.addErrorMessage("O campo 'Preço' deve ser maior que R$ 0,00");
            return false;
        }
        return true;
    }

    public boolean salvarOuAtualizar(Produto produto) {
        if (isValido(produto)) {
            ProdutoDAO.getInstance().salvarOuAtualizar(produto);
            
            return true;
        } else {
            return false;
        }
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
