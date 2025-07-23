package com.gvp.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lavagem {
    private LocalDate data;
    private List<ILavavel> itensLavados;
    private String observacoes;

    public Lavagem(LocalDate data) {
        this.data = data;
        this.itensLavados = new ArrayList<>();
    }

    public void adicionarItem(ILavavel item) {
        itensLavados.add(item);
        item.registrarLavagem(data);
    }

    public LocalDate getData() {
        return data;
    }

    public List<ILavavel> getItensLavados() {
        return new ArrayList<>(itensLavados);
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getQuantidadeItens() {
        return itensLavados.size();
    }

    @Override
    public String toString() {
        return "Lavagem em " + data + " - " + itensLavados.size() + " itens";
    }
}