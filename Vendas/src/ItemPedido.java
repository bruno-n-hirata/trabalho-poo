public class ItemPedido {

    private Pedido pedido;
    private Produto produto;
    private double precoPagar;
    private int quantidade;

    public ItemPedido() {
    }

    public boolean ItensPedido(Pedido pedido, Produto produto,int quantidade, double precoPagar ) {
        if (produto.getQuantidadeProduto() >= quantidade) {
            this.setQuantidade(quantidade);
            this.setPrecoPagar(precoPagar);
            this.produto = produto;
            this.pedido = pedido;
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - this.getQuantidade());
            return true;
        } else {
            return false;
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPrecoPagar() {
        return precoPagar;
    }

    public void setPrecoPagar(double precoPagar) {
        this.precoPagar = precoPagar;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void cancelarItem() {
        produto.setQuantidadeProduto(produto.getQuantidadeProduto() + quantidade);
        System.out.println("Quantidade do produto " + produto.getNomeProduto() + " devolvida ao estoque.");
    }

    public void mostrarItemPedido(){
        this.pedido.mostrarPedido();
        System.out.println("Produto: " + this.produto.getNomeProduto());
        System.out.println("Quantidade: " + this.getQuantidade());
        System.out.println("Produto: " + this.getPrecoPagar());
    }
}
