import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe principal para gerenciar o vestuário
 */
public class GerenciadorVestuario {
    private List<Item> itens;
    private List<Look> looks;
    private List<Lavagem> lavagens;
    private static final String ARQUIVO_ITENS = "itens.dat";
    private static final String ARQUIVO_LOOKS = "looks.dat";

    public GerenciadorVestuario() {
        this.itens = new ArrayList<>();
        this.looks = new ArrayList<>();
        this.lavagens = new ArrayList<>();
        carregarDados();
    }

    // Métodos para gerenciar itens
    public void adicionarItem(Item item) {
        itens.add(item);
        salvarDados();
    }

    public void removerItem(Item item) {
        itens.remove(item);
        salvarDados();
    }

    public void modificarItem(Item itemAntigo, Item itemNovo) {
        int index = itens.indexOf(itemAntigo);
        if (index != -1) {
            itens.set(index, itemNovo);
            salvarDados();
        }
    }

    public List<Item> getItens() {
        return new ArrayList<>(itens);
    }

    // Métodos para gerenciar looks
    public void adicionarLook(Look look) {
        looks.add(look);
        salvarDados();
    }

    public void removerLook(Look look) {
        looks.remove(look);
        salvarDados();
    }

    public void modificarLook(Look look, String novoNome, Map<String, Item> novosItens) {
        look.setNome(novoNome);
        look.setItens(novosItens);
        salvarDados();
    }

    public List<Look> getLooks() {
        return new ArrayList<>(looks);
    }

    // Métodos para gerenciar lavagens
    public void adicionarLavagem(Lavagem lavagem) {
        lavagens.add(lavagem);
    }

    public List<Lavagem> getLavagens() {
        return new ArrayList<>(lavagens);
    }

    // Métodos de estatísticas
    public List<Item> getItensMaisUsados(int quantidade) {
        return itens.stream()
                .sorted((a, b) -> Integer.compare(b.getQuantidadeUsos(), a.getQuantidadeUsos()))
                .limit(quantidade)
                .collect(Collectors.toList());
    }

    public List<Item> getItensMenosUsados(int quantidade) {
        return itens.stream()
                .sorted((a, b) -> Integer.compare(a.getQuantidadeUsos(), b.getQuantidadeUsos()))
                .limit(quantidade)
                .collect(Collectors.toList());
    }

    public List<Item> getItensEmprestados() {
        return itens.stream()
                .filter(item -> item instanceof IEmprestavel)
                .filter(item -> ((IEmprestavel) item).estaEmprestado())
                .collect(Collectors.toList());
    }

    public List<Look> getLooksMaisUsados(int quantidade) {
        return looks.stream()
                .sorted((a, b) -> Integer.compare(b.getQuantidadeUtilizacoes(), a.getQuantidadeUtilizacoes()))
                .limit(quantidade)
                .collect(Collectors.toList());
    }

    public List<Item> getItensMaisLavados(int quantidade) {
        return itens.stream()
                .filter(item -> item instanceof ILavavel)
                .sorted((a, b) -> Integer.compare(((ILavavel) b).getQuantidadeLavagens(),
                        ((ILavavel) a).getQuantidadeLavagens()))
                .limit(quantidade)
                .collect(Collectors.toList());
    }

    // Métodos de persistência
    private void salvarDados() {
        try {
            // Salvar itens
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ITENS));
            oos.writeObject(itens);
            oos.close();

            // Salvar looks
            oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_LOOKS));
            oos.writeObject(looks);
            oos.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        try {
            // Carregar itens
            File arquivoItens = new File(ARQUIVO_ITENS);
            if (arquivoItens.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_ITENS));
                itens = (List<Item>) ois.readObject();
                ois.close();
            }

            // Carregar looks
            File arquivoLooks = new File(ARQUIVO_LOOKS);
            if (arquivoLooks.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_LOOKS));
                looks = (List<Look>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            // Se houver erro, inicializar com listas vazias
            itens = new ArrayList<>();
            looks = new ArrayList<>();
        }
    }
}