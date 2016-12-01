/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unicatolica.controller;

import br.edu.unicatolica.bo.GraficoPedidoBO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.security.Seguranca;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Danrley
 */
@ManagedBean
@RequestScoped
public class GraficoPedidoBean implements Serializable {

    private LineChartModel model;
    private Integer numeroDeDias = 15;
    private Usuario usuario;

    public void preRender() {
        this.model = new LineChartModel();
        this.model.setAnimate(true);

        adicionarSerie("Todos os pedidos", null);
        adicionarSerie("Meus pedidos", new Seguranca().getUsuarioLogado().getUsuario());

        this.model.setTitle("Todos pedidos x Meus pedidos");
        this.model.setLegendPosition("ne");
        Axis yAxis = this.model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Valor");

        DateAxis xAxis = new DateAxis("Datas");
        xAxis.setTickAngle(-50);
        xAxis.setTickFormat("%b %#d");
        this.model.getAxes().put(AxisType.X, xAxis);
    }

    public void adicionarSerie(String rotulo, Usuario usuario) {
        Map<Date, BigDecimal> valoresPorData = GraficoPedidoBO.getInstance().valoresTotaisPorData(
                numeroDeDias, usuario);

        LineChartSeries series = new LineChartSeries();
        series.setLabel(rotulo);
        for (Date data : valoresPorData.keySet()) {
            series.set(data.getTime(), valoresPorData.get(data));
        }

        this.model.addSeries(series);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public LineChartModel getModel() {
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }

    public Integer getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(Integer numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
//</editor-fold>

}
