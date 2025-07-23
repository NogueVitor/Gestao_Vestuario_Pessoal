package com.gvp.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class RoupaIntima extends Item implements ILavavel {
    private List<LocalDate> datasLavagem;

    public RoupaIntima(String nome, String cor, String tamanho, String lojaOrigem, String conservacao, ImageIcon foto) {
        super(nome, cor, tamanho, lojaOrigem, conservacao, foto);
        this.datasLavagem = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Roupa Íntima";
    }

    @Override
    public String getParteCorpo() {
        return "Íntimo";
    }

    @Override
    public void registrarLavagem(LocalDate dataLavagem) {
        this.datasLavagem.add(dataLavagem);
    }

    @Override
    public int getQuantidadeLavagens() {
        return datasLavagem.size();
    }

    @Override
    public LocalDate getUltimaLavagem() {
        if (datasLavagem.isEmpty()) {
            return null;
        }
        return datasLavagem.get(datasLavagem.size() - 1);
    }
}