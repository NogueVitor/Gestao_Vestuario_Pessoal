package com.gvp.persistence;
import com.gvp.model.Item;
import com.gvp.model.Look;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String ARQUIVO_ITENS = "itens.dat";
    private static final String ARQUIVO_LOOKS = "looks.dat";

    public void salvarDados(List<Item> itens, List<Look> looks) {
        try (ObjectOutputStream oosItens = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ITENS));
             ObjectOutputStream oosLooks = new ObjectOutputStream(new FileOutputStream(ARQUIVO_LOOKS))) {

            oosItens.writeObject(new ArrayList<>(itens));
            oosLooks.writeObject(new ArrayList<>(looks));

        } catch (IOException e) {

            System.err.println("Erro ao salvar os dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Não verificado")
    public List<Item> carregarItens() {
        File arquivoItens = new File(ARQUIVO_ITENS);

        if (arquivoItens.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_ITENS))) {
                return (List<Item>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar o arquivo de itens: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }

    @SuppressWarnings("Não verificado")
    public List<Look> carregarLooks() {
        File arquivoLooks = new File(ARQUIVO_LOOKS);

        if (arquivoLooks.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_LOOKS))) {
                return (List<Look>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar o arquivo de looks: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }
}