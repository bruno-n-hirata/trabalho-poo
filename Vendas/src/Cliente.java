import java.util.ArrayList;

public class Cliente {

    private static int contadorCodigo = 1;

    private int codigo;
    private String nome;
    private String cpf;
    private ArrayList<Pedido> pedido;

    public Cliente(String nome, String cpf) {
        this.codigo = contadorCodigo++;
        this.nome = nome;
        this.cpf = cpf;
        this.pedido = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }
}
