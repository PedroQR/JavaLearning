import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {

    /** Quantidade máxima de produtos de um pedido */
    private static final int MAX_PRODUTOS = 10;
    
    /** Porcentagem de desconto para pagamentos à vista */
    private static final double DESCONTO_PG_A_VISTA = 0.15;
    
    /** Vetor para armazenar os produtos do pedido */
    private Produto[] produtos;
    
    /** Data de criação do pedido */
    private LocalDate dataPedido;
    
    /** Quantos produtos este pedido possui no momento */
    private int quantProdutos = 0;
    
    /** Indica a forma de pagamento do pedido sendo: 1, pagamento à vista; 2, pagamento parcelado */
    private int formaDePagamento;
    
    /** Construtor do pedido.
     *  Deve criar o vetor de produtos do pedido, 
     *  armazenar a data e a forma de pagamento informadas para o pedido. 
     */  
    public Pedido(LocalDate dataPedido, int formaDePagamento) {
        this.produtos = new Produto[MAX_PRODUTOS];
        this.dataPedido = dataPedido;
        this.formaDePagamento = formaDePagamento;
    }
    
    /** Retorna a data do pedido */
    public LocalDate getDataPedido() {
        return dataPedido;
    }
    
    /** Retorna a forma de pagamento (1 à vista, 2 parcelado) */
    public int getFormaDePagamento() {
        return formaDePagamento;
    }
    
    /** Adiciona um produto ao pedido, caso haja espaço. */
    public boolean adicionarProduto(Produto p) {
        if (p == null) return false;
        if (quantProdutos >= MAX_PRODUTOS) return false;
        produtos[quantProdutos++] = p;
        return true;
    }
    
    /** Remove a primeira ocorrência do produto p do pedido. */
    public boolean removerProduto(Produto p) {
        if (p == null || quantProdutos == 0) return false;
        for (int i = 0; i < quantProdutos; i++) {
            if (produtos[i] == p) { // mesma referência é suficiente aqui
                // desloca à esquerda
                for (int j = i; j < quantProdutos - 1; j++) {
                    produtos[j] = produtos[j + 1];
                }
                produtos[--quantProdutos] = null;
                return true;
            }
        }
        return false;
    }
    
    /** Calcula o total bruto (sem descontos) somando o preço de venda de cada produto. */
    public double calcularTotalBruto() {
        double total = 0.0;
        for (int i = 0; i < quantProdutos; i++) {
            // Assumimos que a classe Produto possui um método getPrecoDeVenda()
            total += produtos[i].getPrecoDeVenda();
        }
        return total;
    }
    
    /** Valor a pagar considerando a forma de pagamento. */
    public double calcularValorAPagar() {
        double total = calcularTotalBruto();
        if (formaDePagamento == 1) { // à vista
            total = total * (1.0 - DESCONTO_PG_A_VISTA);
        }
        return total;
    }
    
    /** Gera uma representação textual do pedido. */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("Data: ").append(dataPedido.format(fmt)).append("\n");
        sb.append("Forma de pagamento: ").append(formaDePagamento == 1 ? "À vista" : "Parcelado").append("\n");
        sb.append("Itens (" + quantProdutos + "):\n");
        for (int i = 0; i < quantProdutos; i++) {
            // Assumimos que Produto possui getDescricao() e getPrecoDeVenda()
            sb.append("  - ").append(produtos[i].getDescricao())
              .append(" (R$ ").append(String.format("%.2f", produtos[i].getPrecoDeVenda())).append(")\n");
        }
        sb.append("Total bruto: R$ ").append(String.format("%.2f", calcularTotalBruto())).append("\n");
        sb.append("Total a pagar: R$ ").append(String.format("%.2f", calcularValorAPagar())).append("\n");
        return sb.toString();
    }
    
    /**
     * Igualdade de pedidos: caso possuam a mesma data. 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pedido other = (Pedido) obj;
        return this.dataPedido != null && this.dataPedido.equals(other.dataPedido);
    }
}
