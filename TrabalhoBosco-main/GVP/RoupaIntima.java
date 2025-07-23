import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Classe que representa roupa íntima (não emprestável)
 */
public class RoupaIntima extends Item implements ILavavel {
    private List<LocalDate> datasLavagem;

    public RoupaIntima(String nome, String cor, String tamanho, String lojaOrigem, String conservacao, ImageIcon foto) {
        // Corrigido: Adicionado 'foto' ao super()
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

    // ... (o resto do código da classe permanece o mesmo)
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