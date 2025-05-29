import java.awt.Graphics; //permite usar elementos grafricos
import java.awt.Image; //usar imagem
import java.awt.event.*;
import javax.swing.*;

public class javaintroducao extends JFrame {

    public javaintroducao() {
        super("Tela de Login");

        setContentPane(new PainelComImagem("fundomercado.png")); //coloca a imagem
        getContentPane().setLayout(null);
        
        
        // Componentes

        JLabel textologin = new JLabel("Digite seu login:"); //texto do login
        textologin.setBounds(550, 50, 150, 30); //tamanho

        JTextField campologin = new JTextField(); //caixa pra escrever o login
        campologin.setBounds(550, 80, 150, 30);

        JLabel textosenha = new JLabel("Digite sua senha:"); //caixa pra escrever a senha
        textosenha.setBounds(550, 150, 150, 30);

        JPasswordField camposenha = new JPasswordField(); //caixa pra escrever a senha
        camposenha.setBounds(550, 180, 150, 30);

        JButton botaoFuncionario = new JButton("Login"); //botão do funcionario
        botaoFuncionario.setBounds(550, 230, 150, 30);
        botaoFuncionario.addActionListener(new ActionListener() { //acção do botão
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campologin.getText(); //pega o login
                String senha = new String(camposenha.getPassword()); //pega a senha

                if (login.equals("omelhordonordeste") && senha.equals("vitoria")) { //define a senha e login
                    new NovaJanela(); // abre janela do funcionário
                    dispose();         // fecha janela de login
                } else {
                    JOptionPane.showMessageDialog(null, "Login ou senha incorretos!"); //caso n seja a senha definida, aparece essa mensagem
                }
            }
        });

        JButton botaoComprador = new JButton("Compre Aqui"); //tela do comprador
        botaoComprador.setBounds(100, 230, 150, 30);
        botaoComprador.addActionListener(new ActionListener() { //ção do botão
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaComprador(); // abre janela do comprador
                dispose();           // fecha esta
            }
        });

        //componentes da janela
        add(textologin);
        add(campologin);
        add(textosenha);
        add(camposenha);
        add(botaoFuncionario);
        add(botaoComprador);
        //add(planodefundo);

        //configuração da janela
        setLayout(null);
        setBounds(0, 0, 800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new javaintroducao(); // abre a primeira janela
    }

    class PainelComImagem extends JPanel {
        private Image imagem;
    
        public PainelComImagem(String caminhoImagem) {
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
