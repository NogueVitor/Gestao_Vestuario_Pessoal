import java.time.LocalDate;

/**
 * Interface para itens que podem ser emprestados
 */
public interface IEmprestavel {
    void registrarEmprestimo(String nomeEmprestado, LocalDate dataEmprestimo);
    int quantidadeDeDiasDesdeOEmprestimo();
    void registrarDevolucao();
    boolean estaEmprestado();
    String getNomeEmprestado();
    LocalDate getDataEmprestimo();
}