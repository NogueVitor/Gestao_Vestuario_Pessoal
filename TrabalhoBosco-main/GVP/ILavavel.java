import java.time.LocalDate;

/**
 * Interface para itens que podem ser lavados
 */
public interface ILavavel {
    void registrarLavagem(LocalDate dataLavagem);
    int getQuantidadeLavagens();
    LocalDate getUltimaLavagem();
}