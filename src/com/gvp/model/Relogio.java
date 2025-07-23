package com.gvp.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.ImageIcon;


public class Relogio extends Item implements IEmprestavel {
    private boolean emprestado;
    private String nomeEmprestado;
    private LocalDate dataEmprestimo;

    public Relogio(String nome, String cor, String tamanho, String lojaOrigem, String conservacao, ImageIcon foto) {
        super(nome, cor, tamanho, lojaOrigem, conservacao, foto);
        this.emprestado = false;
    }

    @Override
    public String getTipo() {
        return "Relógio";
    }

    @Override
    public String getParteCorpo() {
        return "Pulso";
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
}