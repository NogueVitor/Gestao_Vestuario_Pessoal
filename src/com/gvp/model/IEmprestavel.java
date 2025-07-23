package com.gvp.model;
import java.time.LocalDate;

public interface IEmprestavel {
    void registrarEmprestimo(String nomeEmprestado, LocalDate dataEmprestimo);
    int quantidadeDeDiasDesdeOEmprestimo();
    void registrarDevolucao();
    boolean estaEmprestado();
    String getNomeEmprestado();
    LocalDate getDataEmprestimo();
}