package com.gvp.model;
import java.time.LocalDate;

public interface ILavavel {
    void registrarLavagem(LocalDate dataLavagem);
    int getQuantidadeLavagens();
    LocalDate getUltimaLavagem();
}