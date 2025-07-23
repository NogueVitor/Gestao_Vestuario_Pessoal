import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorVestuarioGUI extends JFrame {

    private final GerenciadorVestuario gerenciador;
    private final DefaultTableModel modeloTabelaItens;
    private final DefaultTableModel modeloTabelaLooks;
    private final JTable tabelaItens;
    private final JTable tabelaLooks;
    private final JLabel lblFoto;
    private final JTextArea areaEmprestimos;
    private final JTextArea areaLavagens;
    private final JTextArea areaEstatisticas;


    // Paleta de cores definida pelo usuário
    private static final Color COR_PRINCIPAL = new Color(0xAD8B73);
    private static final Color COR_SECUNDARIA = new Color(0xCEAB93);
    private static final Color COR_DESTAQUE = new Color(0xE3CAA5);
    private static final Color COR_FUNDO = new Color(0xFFFBE9);

    public GestorVestuarioGUI() {
        gerenciador = new GerenciadorVestuario();

        setTitle("Gestor de Vestuário Pessoal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(COR_PRINCIPAL);
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Inicializando componentes de texto
        areaEmprestimos = new JTextArea();
        areaLavagens = new JTextArea();
        areaEstatisticas = new JTextArea();

        // Tabela de Itens
        modeloTabelaItens = new DefaultTableModel(new String[]{"Nome", "Tipo", "Cor", "Tamanho", "Conservação", "Usos"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaItens = new JTable(modeloTabelaItens);
        tabelaItens.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaItens.setRowHeight(24);


        // Label para a foto
        lblFoto = new JLabel();
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);

        // Tabela de Looks
        modeloTabelaLooks = new DefaultTableModel(new String[]{"Nome", "Itens", "Utilizações"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaLooks = new JTable(modeloTabelaLooks);
        tabelaLooks.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaLooks.setRowHeight(24);


        // Adicionando as abas
        tabbedPane.addTab("Itens", criarPainelItens());
        tabbedPane.addTab("Looks", criarPainelLooks());
        tabbedPane.addTab("Empréstimos", criarPainelEmprestimos());
        tabbedPane.addTab("Lavagens", criarPainelLavagens());
        tabbedPane.addTab("Estatísticas", criarPainelEstatisticas());

        add(tabbedPane);

        // Carregamento inicial dos dados
        atualizarTodasAsAbas();
    }

    private JPanel criarPainelItens() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPaneTabela = new JScrollPane(tabelaItens);
        tabelaItens.getSelectionModel().addListSelectionListener(e -> mostrarFotoItemSelecionado());

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(COR_FUNDO);

        JButton btnAdicionar = criarBotao("Adicionar Item");
        JButton btnEditar = criarBotao("Editar Item");
        JButton btnRemover = criarBotao("Remover Item");
        JButton btnRegistrarUso = criarBotao("Registrar Uso");

        btnAdicionar.addActionListener(e -> mostrarDialogoItem(null));
        btnEditar.addActionListener(e -> editarItemSelecionado());
        btnRemover.addActionListener(e -> removerItemSelecionado());
        btnRegistrarUso.addActionListener(e -> registrarUsoItem());

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnRegistrarUso);

        // ALTERAÇÃO: Painel lateral para controlar a posição e tamanho da foto
        JPanel painelLateral = new JPanel(new BorderLayout());
        painelLateral.setBackground(COR_FUNDO);

        JPanel painelFotoContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelFotoContainer.setBackground(COR_FUNDO);
        lblFoto.setPreferredSize(new Dimension(300, 300)); // Tamanho fixo para a foto
        lblFoto.setBorder(BorderFactory.createLineBorder(COR_SECUNDARIA, 2));
        painelFotoContainer.add(lblFoto);

        // Adiciona o container da foto na parte superior (NORTH) do painel lateral
        painelLateral.add(painelFotoContainer, BorderLayout.NORTH);

        panel.add(scrollPaneTabela, BorderLayout.CENTER);
        panel.add(painelBotoes, BorderLayout.SOUTH);
        panel.add(painelLateral, BorderLayout.EAST); // Adiciona o painel lateral à direita

        return panel;
    }

    private JPanel criarPainelLooks() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(tabelaLooks);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(COR_FUNDO);

        JButton btnCriar = criarBotao("Criar Look");
        JButton btnEditar = criarBotao("Editar Look");
        JButton btnRemover = criarBotao("Remover Look");
        JButton btnUtilizar = criarBotao("Registrar Utilização");

        btnCriar.addActionListener(e -> mostrarDialogoCriarLook());
        btnEditar.addActionListener(e -> editarLookSelecionado());
        btnRemover.addActionListener(e -> removerLookSelecionado());
        btnUtilizar.addActionListener(e -> registrarUtilizacaoLook());

        painelBotoes.add(btnCriar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnUtilizar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(painelBotoes, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelEmprestimos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        areaEmprestimos.setEditable(false);
        areaEmprestimos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaEmprestimos.setBackground(COR_DESTAQUE);
        areaEmprestimos.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(areaEmprestimos);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(COR_FUNDO);
        JButton btnEmprestar = criarBotao("Emprestar Item");
        JButton btnDevolver = criarBotao("Devolver Item");

        btnEmprestar.addActionListener(e -> emprestarItem());
        btnDevolver.addActionListener(e -> devolverItem());

        painelBotoes.add(btnEmprestar);
        painelBotoes.add(btnDevolver);

        panel.add(new JLabel("Itens Atualmente Emprestados:", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(painelBotoes, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelLavagens() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        areaLavagens.setEditable(false);
        areaLavagens.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaLavagens.setBackground(COR_DESTAQUE);
        areaLavagens.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(areaLavagens);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(COR_FUNDO);
        JButton btnRegistrarLavagem = criarBotao("Registrar Nova Lavagem");

        btnRegistrarLavagem.addActionListener(e -> registrarLavagem());

        painelBotoes.add(btnRegistrarLavagem);

        panel.add(new JLabel("Histórico de Lavagens:", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(painelBotoes, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelEstatisticas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        areaEstatisticas.setEditable(false);
        areaEstatisticas.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaEstatisticas.setBackground(COR_DESTAQUE);
        areaEstatisticas.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(areaEstatisticas);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(COR_FUNDO);
        JButton btnAtualizar = criarBotao("Atualizar Estatísticas");
        btnAtualizar.addActionListener(e -> atualizarEstatisticas());
        painelBotoes.add(btnAtualizar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(painelBotoes, BorderLayout.SOUTH);

        return panel;
    }

    // --- MÉTODOS DE LÓGICA ---

    private void editarItemSelecionado() {
        int selectedRow = tabelaItens.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para editar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Item itemSelecionado = gerenciador.getItens().get(selectedRow);
        mostrarDialogoItem(itemSelecionado);
    }

    private void mostrarDialogoItem(Item item) {
        boolean isEdit = item != null;
        JDialog dialog = new JDialog(this, isEdit ? "Editar Item" : "Adicionar Item", true);
        dialog.setSize(450, 400);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(COR_FUNDO);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_FUNDO);
        panel.setBorder(new EmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNome = new JTextField(20);
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Camisa", "Calça", "Saia", "Relógio", "Roupa Íntima"});
        JTextField txtCor = new JTextField(20);
        JTextField txtTamanho = new JTextField(20);
        JTextField txtLoja = new JTextField(20);
        JComboBox<String> cbConservacao = new JComboBox<>(new String[]{"Excelente", "Boa", "Regular", "Ruim"});
        JLabel lblFotoPath = new JLabel("Nenhuma imagem selecionada.");
        JButton btnEscolherFoto = criarBotao("Escolher Foto");
        final ImageIcon[] fotoHolder = {isEdit ? item.getFoto() : null};

        if (isEdit) {
            txtNome.setText(item.getNome());
            cbTipo.setSelectedItem(item.getTipo());
            cbTipo.setEnabled(false);
            txtCor.setText(item.getCor());
            txtTamanho.setText(item.getTamanho());
            txtLoja.setText(item.getLojaOrigem());
            cbConservacao.setSelectedItem(item.getConservacao());
            if (item.getFoto() != null) {
                lblFotoPath.setText("Imagem carregada.");
            }
        }

        btnEscolherFoto.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(dialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fotoHolder[0] = new ImageIcon(selectedFile.getAbsolutePath());
                lblFotoPath.setText(selectedFile.getName());
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST; panel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; panel.add(txtNome, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(cbTipo, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Cor:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(txtCor, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Tamanho:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(txtTamanho, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Loja:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(txtLoja, gbc);
        gbc.gridx = 0; gbc.gridy = 5; panel.add(new JLabel("Conservação:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(cbConservacao, gbc);
        gbc.gridx = 0; gbc.gridy = 6; panel.add(new JLabel("Foto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 6; panel.add(btnEscolherFoto, gbc);
        gbc.gridx = 1; gbc.gridy = 7; panel.add(lblFotoPath, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBackground(COR_FUNDO);
        JButton btnSalvar = criarBotao("Salvar");
        JButton btnCancelar = criarBotao("Cancelar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "O nome é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tipo = (String) cbTipo.getSelectedItem();
            String cor = txtCor.getText().trim();
            String tamanho = txtTamanho.getText().trim();
            String loja = txtLoja.getText().trim();
            String conservacao = (String) cbConservacao.getSelectedItem();
            ImageIcon foto = fotoHolder[0];

            if (isEdit) {
                item.setNome(nome);
                item.setCor(cor);
                item.setTamanho(tamanho);
                item.setLojaOrigem(loja);
                item.setConservacao(conservacao);
                item.setFoto(foto);
                gerenciador.modificarItem(item, item);
            } else {
                Item novoItem = criarItem(tipo, nome, cor, tamanho, loja, conservacao, foto);
                gerenciador.adicionarItem(novoItem);
            }
            atualizarTodasAsAbas();
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(painelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private Item criarItem(String tipo, String nome, String cor, String tamanho, String loja, String conservacao, ImageIcon foto) {
        switch (tipo) {
            case "Camisa": return new Camisa(nome, cor, tamanho, loja, conservacao, foto);
            case "Calça": return new Calca(nome, cor, tamanho, loja, conservacao, foto);
            case "Saia": return new Saia(nome, cor, tamanho, loja, conservacao, foto);
            case "Relógio": return new Relogio(nome, cor, tamanho, loja, conservacao, foto);
            case "Roupa Íntima": return new RoupaIntima(nome, cor, tamanho, loja, conservacao, foto);
            default:
                throw new IllegalArgumentException("Tipo de item desconhecido: " + tipo);
        }
    }

    private void editarLookSelecionado() {
        int selectedRow = tabelaLooks.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um look para editar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Look lookSelecionado = gerenciador.getLooks().get(selectedRow);

        JDialog dialog = new JDialog(this, "Editar Look", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(COR_FUNDO);
        dialog.setLayout(new BorderLayout(10,10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(COR_FUNDO);
        formPanel.setBorder(new EmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx=0; gbc.gridy=0;
        formPanel.add(new JLabel("Nome do Look:"), gbc);
        JTextField txtNomeLook = new JTextField(lookSelecionado.getNome(), 20);
        gbc.gridx=1;
        formPanel.add(txtNomeLook, gbc);

        Map<String, JComboBox<Item>> combosPorParte = new HashMap<>();
        List<Item> todosItens = gerenciador.getItens();
        String[] partesCorpo = {"Torso Superior", "Pernas", "Pulso", "Íntimo"};

        for (int i = 0; i < partesCorpo.length; i++) {
            String parte = partesCorpo[i];
            gbc.gridy = i + 1;
            gbc.gridx = 0;
            formPanel.add(new JLabel(parte + ":"), gbc);

            JComboBox<Item> combo = new JComboBox<>();
            combo.addItem(null);
            for(Item item : todosItens){
                if(item.getParteCorpo().equals(parte)){
                    combo.addItem(item);
                }
            }

            combosPorParte.put(parte, combo);
            Item itemAtual = lookSelecionado.getItens().get(parte);
            if (itemAtual != null) {
                combo.setSelectedItem(itemAtual);
            }
            gbc.gridx = 1;
            formPanel.add(combo, gbc);
        }

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(COR_FUNDO);
        JButton btnSalvar = criarBotao("Salvar");
        JButton btnCancelar = criarBotao("Cancelar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String novoNome = txtNomeLook.getText().trim();
            if (novoNome.isEmpty()){
                JOptionPane.showMessageDialog(dialog, "O nome do look não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Map<String, Item> novosItens = new HashMap<>();
            for (Map.Entry<String, JComboBox<Item>> entry : combosPorParte.entrySet()) {
                if(entry.getValue().getSelectedItem() != null){
                    novosItens.put(entry.getKey(), (Item) entry.getValue().getSelectedItem());
                }
            }
            gerenciador.modificarLook(lookSelecionado, novoNome, novosItens);
            atualizarTodasAsAbas();
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(painelBotoes, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void removerItemSelecionado() {
        int selectedRow = tabelaItens.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este item?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Item item = gerenciador.getItens().get(selectedRow);
            gerenciador.removerItem(item);
            atualizarTodasAsAbas();
        }
    }

    private void registrarUsoItem() {
        int selectedRow = tabelaItens.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para registrar o uso!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Item item = gerenciador.getItens().get(selectedRow);
        item.registrarUso(LocalDate.now());
        atualizarTodasAsAbas();
        JOptionPane.showMessageDialog(this, "Uso registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarDialogoCriarLook() {
        String nome = JOptionPane.showInputDialog(this, "Nome do Look:");
        if (nome != null && !nome.trim().isEmpty()) {
            Look look = new Look(nome.trim());
            gerenciador.adicionarLook(look);
            atualizarTodasAsAbas();
        }
    }

    private void removerLookSelecionado() {
        int selectedRow = tabelaLooks.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um look para remover!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este look?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Look look = gerenciador.getLooks().get(selectedRow);
            gerenciador.removerLook(look);
            atualizarTodasAsAbas();
        }
    }

    private void registrarUtilizacaoLook() {
        int selectedRow = tabelaLooks.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um look!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String evento = JOptionPane.showInputDialog(this, "Evento/Ocasião:");
        String periodo = JOptionPane.showInputDialog(this, "Período (manhã/tarde/noite):");
        if (evento != null && periodo != null) {
            Look look = gerenciador.getLooks().get(selectedRow);
            look.registrarUtilizacao(LocalDate.now(), periodo, evento);
            atualizarTodasAsAbas();
            JOptionPane.showMessageDialog(this, "Utilização registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void emprestarItem() {
        List<Item> itensDisponiveis = gerenciador.getItens().stream()
                .filter(item -> item instanceof IEmprestavel && !((IEmprestavel) item).estaEmprestado())
                .collect(Collectors.toList());

        if (itensDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há itens disponíveis para empréstimo.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Item itemSelecionado = (Item) JOptionPane.showInputDialog(
                this, "Selecione o item para emprestar:", "Emprestar Item",
                JOptionPane.PLAIN_MESSAGE, null, itensDisponiveis.toArray(), null);

        if (itemSelecionado != null) {
            String nomePessoa = JOptionPane.showInputDialog(this, "Emprestar para quem?");
            if (nomePessoa != null && !nomePessoa.trim().isEmpty()) {
                ((IEmprestavel) itemSelecionado).registrarEmprestimo(nomePessoa.trim(), LocalDate.now());
                JOptionPane.showMessageDialog(this, "Empréstimo registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                atualizarTodasAsAbas();
            }
        }
    }

    private void devolverItem() {
        List<Item> itensEmprestados = gerenciador.getItensEmprestados();

        if (itensEmprestados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há itens emprestados para devolver.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Item itemSelecionado = (Item) JOptionPane.showInputDialog(
                this, "Selecione o item a ser devolvido:", "Devolver Item",
                JOptionPane.PLAIN_MESSAGE, null, itensEmprestados.toArray(), null);

        if (itemSelecionado != null) {
            ((IEmprestavel) itemSelecionado).registrarDevolucao();
            JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarTodasAsAbas();
        }
    }

    private void registrarLavagem() {
        List<Item> itensLavaveis = gerenciador.getItens().stream()
                .filter(item -> item instanceof ILavavel)
                .collect(Collectors.toList());

        if (itensLavaveis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há itens laváveis no seu vestuário.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JList<Item> listaItens = new JList<>(itensLavaveis.toArray(new Item[0]));
        listaItens.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        int result = JOptionPane.showConfirmDialog(this, new JScrollPane(listaItens), "Registrar Lavagem - Selecione os Itens", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            List<Item> itensSelecionados = listaItens.getSelectedValuesList();
            if (itensSelecionados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum item foi selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Lavagem novaLavagem = new Lavagem(LocalDate.now());
            for(Item item : itensSelecionados) {
                novaLavagem.adicionarItem((ILavavel) item);
            }
            gerenciador.adicionarLavagem(novaLavagem);
            JOptionPane.showMessageDialog(this, "Lavagem registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarTodasAsAbas();
        }
    }

    // --- MÉTODOS DE ATUALIZAÇÃO DA GUI ---

    private void atualizarTodasAsAbas() {
        atualizarTabelaItens();
        atualizarTabelaLooks();
        atualizarListaEmprestimos();
        atualizarListaLavagens();
        atualizarEstatisticas();
    }

    private void atualizarTabelaItens() {
        modeloTabelaItens.setRowCount(0);
        for (Item item : gerenciador.getItens()) {
            modeloTabelaItens.addRow(new Object[]{item.getNome(), item.getTipo(), item.getCor(), item.getTamanho(), item.getConservacao(), item.getQuantidadeUsos()});
        }
    }

    private void atualizarTabelaLooks() {
        modeloTabelaLooks.setRowCount(0);
        for (Look look : gerenciador.getLooks()) {
            modeloTabelaLooks.addRow(new Object[]{look.getNome(), look.getItens().size() + " itens", look.getQuantidadeUtilizacoes()});
        }
    }

    private void atualizarListaEmprestimos() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ITENS EMPRESTADOS ATUALMENTE ===\n\n");
        List<Item> emprestados = gerenciador.getItensEmprestados();

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (emprestados.isEmpty()) {
            sb.append("Nenhum item emprestado no momento.");
        } else {
            for (Item item : emprestados) {
                IEmprestavel emprestavel = (IEmprestavel) item;

                String dataFormatada = emprestavel.getDataEmprestimo().format(formatador);

                sb.append(String.format("Item: %s\n", item.getNome()));
                sb.append(String.format("Para: %s\n", emprestavel.getNomeEmprestado()));
                sb.append(String.format("Desde: %s (%d dias)\n", dataFormatada, emprestavel.quantidadeDeDiasDesdeOEmprestimo()));
                sb.append("----------------------------------------\n");
            }
        }
        areaEmprestimos.setText(sb.toString());
    }

    private void atualizarListaLavagens() {
        StringBuilder sb = new StringBuilder();
        List<Lavagem> lavagens = gerenciador.getLavagens();
        sb.append("=== HISTÓRICO DE LAVAGENS ===\n\n");
        if(lavagens.isEmpty()) {
            sb.append("Nenhuma lavagem registrada.");
        } else {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for(Lavagem l : lavagens) {
                String dataFormatada = l.getData().format(formatador);
                sb.append(String.format("Data: %s - %d itens lavados\n", dataFormatada, l.getQuantidadeItens()));
            }
        }
        areaLavagens.setText(sb.toString());
    }

    private void atualizarEstatisticas() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTATÍSTICAS DO VESTUÁRIO ===\n\n");

        sb.append("--- GERAL ---\n");
        sb.append(String.format("Total de Itens: %d\n", gerenciador.getItens().size()));
        sb.append(String.format("Total de Looks: %d\n", gerenciador.getLooks().size()));
        sb.append(String.format("Itens Emprestados: %d\n\n", gerenciador.getItensEmprestados().size()));

        sb.append("--- ITENS MAIS USADOS ---\n");
        List<Item> maisUsados = gerenciador.getItensMaisUsados(5);
        if(maisUsados.isEmpty()) sb.append("Nenhum uso registrado.\n");
        for(Item item : maisUsados) sb.append(String.format("- %s (%d usos)\n", item.getNome(), item.getQuantidadeUsos()));

        sb.append("\n--- ITENS MENOS USADOS ---\n");
        List<Item> menosUsados = gerenciador.getItensMenosUsados(5);
        if(menosUsados.isEmpty()) sb.append("Nenhum item registrado.\n");
        for(Item item : menosUsados) sb.append(String.format("- %s (%d usos)\n", item.getNome(), item.getQuantidadeUsos()));

        sb.append("\n--- LOOKS MAIS USADOS ---\n");
        List<Look> looksMaisUsados = gerenciador.getLooksMaisUsados(5);
        if(looksMaisUsados.isEmpty()) sb.append("Nenhum uso de look registrado.\n");
        for(Look look : looksMaisUsados) sb.append(String.format("- %s (%d usos)\n", look.getNome(), look.getQuantidadeUtilizacoes()));

        sb.append("\n--- ITENS MAIS LAVADOS ---\n");
        List<Item> maisLavados = gerenciador.getItensMaisLavados(5);
        if(maisLavados.isEmpty()) sb.append("Nenhuma lavagem registrada.\n");
        for(Item item : maisLavados) sb.append(String.format("- %s (%d lavagens)\n", item.getNome(), ((ILavavel)item).getQuantidadeLavagens()));

        areaEstatisticas.setText(sb.toString());
        areaEstatisticas.setCaretPosition(0);
    }

    private void mostrarFotoItemSelecionado() {
        int selectedRow = tabelaItens.getSelectedRow();
        if (selectedRow != -1 && selectedRow < gerenciador.getItens().size()) {
            Item item = gerenciador.getItens().get(selectedRow);
            ImageIcon foto = item.getFoto();
            if (foto != null && foto.getImage() != null) {
                // Redimensiona a imagem para caber na label, mantendo a proporção
                int lblWidth = lblFoto.getWidth();
                int lblHeight = lblFoto.getHeight();
                Image img = foto.getImage();

                // Calcula a nova dimensão mantendo a proporção
                int imgWidth = img.getWidth(null);
                int imgHeight = img.getHeight(null);
                double ratio = (double) imgWidth / imgHeight;

                int newWidth = lblWidth;
                int newHeight = (int) (newWidth / ratio);

                if (newHeight > lblHeight) {
                    newHeight = lblHeight;
                    newWidth = (int) (newHeight * ratio);
                }

                Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(scaledImg));
                lblFoto.setText("");
            } else {
                lblFoto.setIcon(null);
                lblFoto.setText("Sem foto");
            }
        } else {
            lblFoto.setIcon(null);
            lblFoto.setText("Sem foto");
        }
    }

    private JButton criarBotao(String texto) {
        JButton button = new JButton(texto);
        button.setBackground(COR_DESTAQUE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new GestorVestuarioGUI().setVisible(true);
        });
    }
}