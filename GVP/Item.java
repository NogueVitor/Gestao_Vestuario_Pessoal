import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Classe abstrata que representa um item de vestuário
 */
public abstract class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String nome;
    protected String cor;
    protected String tamanho;
    protected String lojaOrigem;
    protected String conservacao; // excelente, boa, regular, ruim
    protected ImageIcon foto;
    protected int quantidadeUsos;
    protected List<LocalDate> datasUso;

    public Item(String nome, String cor, String tamanho, String lojaOrigem, String conservacao, ImageIcon foto) {
        this.nome = nome;
        this.cor = cor;
        this.tamanho = tamanho;
        this.lojaOrigem = lojaOrigem;
        this.conservacao = conservacao;
        this.foto = foto;
        this.quantidadeUsos = 0;
        this.datasUso = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getLojaOrigem() { return lojaOrigem; }
    public void setLojaOrigem(String lojaOrigem) { this.lojaOrigem = lojaOrigem; }

    public String getConservacao() { return conservacao; }
    public void setConservacao(String conservacao) { this.conservacao = conservacao; }

    public ImageIcon getFoto() { return foto; }
    public void setFoto(ImageIcon foto) { this.foto = foto; }

    public int getQuantidadeUsos() { return quantidadeUsos; }

    public List<LocalDate> getDatasUso() { return new ArrayList<>(datasUso); }

    public void registrarUso(LocalDate data) {
        this.quantidadeUsos++;
        this.datasUso.add(data);
    }

    public abstract String getTipo();
    public abstract String getParteCorpo();

    @Override
    public String toString() {
        return nome + " (" + cor + ", " + tamanho + ")";
    }
}