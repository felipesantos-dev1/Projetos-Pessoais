import java.awt.*;
import javax.swing.*;

public class TelaComprador extends JFrame {

    private DefaultListModel<Item> modeloItensDisponiveis; //itens disponíveis no mercado
    private DefaultListModel<String> modeloItensComprados;  //itens que o cliente selecionou
    private JList<Item> listaItens;                         //visual com os produtos disponíveis
    private JList<String> listaCompra;                        //visual com os produtos adicionados à compra

    public TelaComprador() {
        super("Área do Cliente");
        setContentPane(new PainelComImagem2("fundocompfun.png")); // <- Altere o caminho aqui
        getContentPane().setLayout(null);
        
        
        
        setLayout(null);

        //t1tulo da janela
        JLabel titulo = new JLabel("Selecione os produtos que deseja comprar:");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(50, 20, 400, 30);
        add(titulo);

        //modelos das listas
        modeloItensDisponiveis = new DefaultListModel<>();
        modeloItensComprados = new DefaultListModel<>();

        //lista com os dados do banco de dados
        for (Item item : BancoDeDados.getItens()) {
            modeloItensDisponiveis.addElement(item);
        }

        //lista de itens disponíveis
        listaItens = new JList<>(modeloItensDisponiveis);
        listaItens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollDisponiveis = new JScrollPane(listaItens);
        scrollDisponiveis.setBounds(50, 70, 300, 300);
        add(scrollDisponiveis);

        //lista de itens comprados
        listaCompra = new JList<>(modeloItensComprados);
        JScrollPane scrollCompra = new JScrollPane(listaCompra);
        scrollCompra.setBounds(450, 70, 300, 300);
        add(scrollCompra);

        //botão para adicionar item à compra
        JButton adicionar = new JButton("Adicionar item");
        adicionar.setBounds(50, 380, 140, 30);
        add(adicionar);

       //ação do botão adicionar
        adicionar.addActionListener(e -> {
        Item selecionado = listaItens.getSelectedValue(); //pega o item daa lista

        if (selecionado != null) {
        //verifica se ainda há estoque
         if (selecionado.getQuantidade() > 0) {
            //diminui a quantidade do item
            selecionado.setQuantidade(selecionado.getQuantidade() - 1);

            //cria um novo item com 1 unidade
            Item itemComprado = new Item(selecionado.getNome(), 1);
            modeloItensComprados.addElement(selecionado.getNome()); // Adiciona à lista de compras

            //atualiza a lista visual
            listaItens.repaint();

            //mensagem confirmando
            JOptionPane.showMessageDialog(this, selecionado.getNome() + " adicionado à sua compra.");
          } else {
            //se a quantidade for 0, avisa que está esgotado
            JOptionPane.showMessageDialog(this, "Este item está esgotado!");
          }   
    }
});


        //botão para finalizar a compra
        JButton finalizar = new JButton("Finalizar Compra");
        finalizar.setBounds(450, 380, 140, 30);
        add(finalizar);

        finalizar.addActionListener(e -> {
            if (modeloItensComprados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum item selecionado!");
            } else {
                JOptionPane.showMessageDialog(this, "Compra finalizada! Obrigado pela preferência.");
                modeloItensComprados.clear(); // Limpa a lista de compra após finalizar
            }
        });

        //botão para voltar à tela de login
        JButton voltar = new JButton("Voltar");
        voltar.setBounds(50, 490, 140, 30);
        add(voltar);

        voltar.addActionListener(e -> {
            new javaintroducao(); // Altere se sua tela principal tiver outro nome
            dispose(); // Fecha a janela atual
        });

        // Configurações da janela
        setBounds(0, 0, 800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Método main para teste
    public static void main(String[] args) {
        new TelaComprador();
    }

    class PainelComImagem2 extends JPanel {
        private Image imagem;
    
        public PainelComImagem2(String caminhoImagem) {
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
