import java.util.ArrayList;
import java.util.List;

// Simula um banco de dados em memória para armazenar os itens
public class BancoDeDados {
    private static List<Item> itens = new ArrayList<>();  // Lista que armazena os itens

    // Bloco estático para iniciar com alguns itens padrões
    static {
        itens.add(new Item("Arroz", 10));
        itens.add(new Item("Feijão", 5));
        itens.add(new Item("Macarrão", 8));
        itens.add(new Item("Café", 0));
        
    }

    // Retorna a lista atual de itens
    public static List<Item> getItens() {
        return itens;
    }

    // Adiciona um item pelo nome. Se já existe, aumenta a quantidade em 1
    public static void adicionarItem(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                // Se já existe, incrementa a quantidade
                item.setQuantidade(item.getQuantidade() + 1);
                return;
            }
        }
        // Se não existe, cria um novo item com quantidade 1
        itens.add(new Item(nome, 1));
    }

    // Remove o item da lista pelo nome (ignorando maiúsculas/minúsculas)
    public static void removerItem(String nome) {
        itens.removeIf(item -> item.getNome().equalsIgnoreCase(nome));
    }
}
