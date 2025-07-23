package com.gvp.controller;
import com.gvp.model.*;
import com.gvp.persistence.DataManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VestuarioController {
    private List<Item> itens;
    private List<Look> looks;
    private List<Lavagem> lavagens;
    private final DataManager dataManager;

    public VestuarioController() {
        this.dataManager = new DataManager();
        this.lavagens = new ArrayList<>();
        carregarDados();
    }
    private void carregarDados() {
        this.itens = dataManager.carregarItens();
        this.looks = dataManager.carregarLooks();
    }
    private void salvarDados() {
        dataManager.salvarDados(itens, looks);
    }

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

    public void adicionarLavagem(Lavagem lavagem) {
        lavagens.add(lavagem);
    }

    public List<Lavagem> getLavagens() {
        return new ArrayList<>(lavagens);
    }

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
}