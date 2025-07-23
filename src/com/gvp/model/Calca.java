package com.gvp.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Calca extends Item implements IEmprestavel, ILavavel {
    private boolean emprestado;
    private String nomeEmprestado;
    private LocalDate dataEmprestimo;
    private List<LocalDate> datasLavagem;

    public Calca(String nome, String cor, String tamanho, String lojaOrigem, String conservacao, ImageIcon foto) {
        super(nome, cor, tamanho, lojaOrigem, conservacao, foto);
        this.emprestado = false;
        this.datasLavagem = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Calça";
    }

    @Override
    public String getParteCorpo() {
        return "Pernas";
    }

    @Override
    public void registrarEmprestimo(String nomeEmprestado, LocalDate dataEmprestimo) {
        this.emprestado = true;
        this.nomeEmprestado = nomeEmprestado;
        this.dataEmprestimo = dataEmprestimo;
    }

    @Override
    public int quantidadeDeDiasDesdeOEmprestimo() {
        if (!emprestado || dataEmprestimo == null) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(dataEmprestimo, LocalDate.now());
    }

    @Override
    public void registrarDevolucao() {
        this.emprestado = false;
        this.nomeEmprestado = null;
        this.dataEmprestimo = null;
    }

    @Override
    public boolean estaEmprestado() {
        return emprestado;
    }

    @Override
    public String getNomeEmprestado() {
        return nomeEmprestado;
    }

    @Override
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
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