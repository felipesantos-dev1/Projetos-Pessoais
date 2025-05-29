import java.awt.*;
import javax.swing.*;

// janela principal do funcionario
public class NovaJanela extends JFrame {

    private DefaultListModel<Item> listModel;  //modelo que armazena os itens para a JList
    private JList<Item> list;                   //lista visual para mostrar os itens
    private JButton fireButton;                 //botão para remover itens selecionados
    private JTextField inseriritem;             //campo para digitar nome do novo item

    public NovaJanela() {
        super("Área do Funcionário");
        setLayout(null);

         setContentPane(new PainelComImagem3("fundocompfun.png")); // <- Altere o caminho aqui
        getContentPane().setLayout(null);
        
        
        //título da tela
        JLabel mensagem = new JLabel("Atualize a lista de produtos:");
        mensagem.setFont(new Font("Arial", Font.BOLD, 24));
        mensagem.setBounds(50, 30, 500, 40);
        add(mensagem);

        //campo de texto para inserir nome do item
        inseriritem = new JTextField();
        inseriritem.setBounds(50, 100, 250, 30);
        add(inseriritem);

        //botão para adicionar novo item
        JButton addButton = new JButton("Adicionar");
        addButton.setBounds(320, 100, 120, 30);
        add(addButton);

        //botão para voltar
        JButton sair = new JButton("Voltar");
        sair.setBounds(50, 300, 100, 30);
        add(sair);

        //ação do botão voltar
        sair.addActionListener(e -> {
            new javaintroducao(); // Sua janela de login
            dispose();           // Fecha essa janela atual
        });

        //inicializa o modelo da lista com os itens do banco de dados
        listModel = new DefaultListModel<>();
        for (Item item : BancoDeDados.getItens()) {
            listModel.addElement(item);
        }

        //cria a JList visual com rolagem
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(50, 150, 250, 150);
        add(scrollPane);

        //botão para remover item selecionado
        fireButton = new JButton("Remover Selecionado");
        fireButton.setBounds(320, 150, 180, 30);
        fireButton.setEnabled(false); // Começa desabilitado, só ativa se selecionar um item
        add(fireButton);

        //quando um item da lista for selecionado, botão remover fica apertavel
        list.addListSelectionListener(e -> {
            fireButton.setEnabled(!list.isSelectionEmpty());
        });

        //botão remover: remove do banco e atualiza a lista visual
        fireButton.addActionListener(e -> {
            Item selected = list.getSelectedValue();
            if (selected != null) {
                BancoDeDados.removerItem(selected.getNome());  //remove do "banco"
                atualizarLista();                               //atualiza a lista na tela
            }
        });

        //botão adicionar: adiciona item no banco e atualiza a lista
        addButton.addActionListener(e -> {
            String nome = inseriritem.getText().trim();  //pega o nome digitado
            if (nome.isEmpty()) {
                Toolkit.getDefaultToolkit().beep();      //som de erro se vazio
                return;
            }

            BancoDeDados.adicionarItem(nome);  //adiciona (ou incrementa) no banco
            atualizarLista();                   //atualiza a lista visual
            inseriritem.setText("");            //limpa o campo de texto
        });

        //janela
        setBounds(0, 0, 800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //atualiza a JList com os dados mais recentes do BancoDeDados
    private void atualizarLista() {
        listModel.clear();  //limpa o modelo da lista
        for (Item item : BancoDeDados.getItens()) {
            listModel.addElement(item);  //diciona todos os itens atualizados
        }
    }

    public static void main(String[] args) {
        new NovaJanela();
    }

    class PainelComImagem3 extends JPanel {
        private Image imagem;
    
        public PainelComImagem3(String caminhoImagem) {
            this.imagem = new ImageIcon(caminhoImagem).getImage();
            setLayout(null); // Permite posicionar elementos manualmente
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagem, 0, 0, getWidth(), getHeight(), this);
        }
    }



}
