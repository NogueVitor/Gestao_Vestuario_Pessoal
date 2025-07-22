import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa um look (conjunto de itens de vestuário)
 */
public class Look implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private Map<String, Item> itens; // Mapa por parte do corpo
    private List<UtilizacaoLook> utilizacoes;

    public Look(String nome) {
        this.nome = nome;
        this.itens = new HashMap<>();
        this.utilizacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarItem(Item item) {
        itens.put(item.getParteCorpo(), item);
    }

    public void removerItem(String parteCorpo) {
        itens.remove(parteCorpo);
    }

    // Sobrecarga para modificar apenas uma parte
    public void modificarLook(Item novoItem) {
        itens.put(novoItem.getParteCorpo(), novoItem);
    }

    // Sobrecarga para modificar múltiplas partes
    public void modificarLook(Item... novosItens) {
        for (Item item : novosItens) {
            itens.put(item.getParteCorpo(), item);
        }
    }

    public Map<String, Item> getItens() {
        return new HashMap<>(itens);
    }

    /**
     * Define os itens do look.
     * Este método foi adicionado para corrigir o erro de compilação.
     * @param novosItens O novo mapa de itens para o look.
     */
    public void setItens(Map<String, Item> novosItens) {
        this.itens = novosItens;
    }


    public void registrarUtilizacao(LocalDate data, String periodo, String evento) {
        UtilizacaoLook utilizacao = new UtilizacaoLook(data, periodo, evento);
        utilizacoes.add(utilizacao);

        // Registrar uso em todos os itens do look
        for (Item item : itens.values()) {
            item.registrarUso(data);
        }
    }

    public List<UtilizacaoLook> getUtilizacoes() {
        return new ArrayList<>(utilizacoes);
    }

    public int getQuantidadeUtilizacoes() {
        return utilizacoes.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Look: ").append(nome).append("\n");
        for (Map.Entry<String, Item> entry : itens.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Classe interna para representar uma utilização do look
     */
    public static class UtilizacaoLook implements Serializable {
        private static final long serialVersionUID = 1L;
        private LocalDate data;
        private String periodo; // manhã, tarde, noite
        private String evento;

        public UtilizacaoLook(LocalDate data, String periodo, String evento) {
            this.data = data;
            this.periodo = periodo;
            this.evento = evento;
        }

        public LocalDate getData() { return data; }
        public String getPeriodo() { return periodo; }
        public String getEvento() { return evento; }

        @Override
        public String toString() {
            return data + " - " + periodo + " - " + evento;
        }
    }
}