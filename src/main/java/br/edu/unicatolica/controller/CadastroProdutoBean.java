package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.jsf.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Danrley
 */
@ManagedBean
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private Produto produto;
    private ConsultaProdutoBean consultaProdutoBean;
    
    
    public CadastroProdutoBean() {
        limpar();
    }

    public void salvar() {
        if (produto.getId() == null) {
            ProdutoBO.getInstance().salvarOuAtualizar(produto);

            //FacesUtil.addInfoMessage("Produto cadastrado com sucesso!");
        } else {
            ProdutoBO.getInstance().salvarOuAtualizar(produto);

            // FacesUtil.addInfoMessage("Produto atualizado com sucesso!");
        }
        limpar();
        ArrayList<Produto> lista = (ArrayList<Produto>) ProdutoDAO.getInstance().getProdutosSemFiltro();
        
    }

    private void limpar() {
        produto = new Produto();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ConsultaProdutoBean getConsultaProdutoBean() {
        return consultaProdutoBean;
    }

    public void setConsultaProdutoBean(ConsultaProdutoBean consultaProdutoBean) {
        this.consultaProdutoBean = consultaProdutoBean;
    }
}





