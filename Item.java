public class Item {
    private String nome;         //nome do produto
    private int quantidade;      //quantidade disponível

    //cria um item com nome e quantidade inicial
    public Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    //pega nome
    public String getNome() {
        return nome;
    }

    
    public String getNomeSimples() {
        return nome;
    }
    

    //pega quantidade
    public int getQuantidade() {
        return quantidade;
    }

    //atualiza a quantidade
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    //exibir o item na lista (nome + quantidade)
    @Override
    public String toString() {
        return nome + " - disponivel: " + quantidade;
    }
}
