package teste.projetoteste.src.ExRevisao.ex1;

public class ContaBancaria {
    private int numeroConta;
    private double saldo;
    private double limite;
    private static final double TAXA_JUROS = 0.03;
    private Usuario usuario;

    public ContaBancaria(int numeroConta, Usuario usuario, double limite) {
        this.numeroConta = numeroConta;
        this.usuario = usuario;
        this.limite = limite;
        this.saldo = 0.0;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo + limite) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        if (saldo < 0) {
            double valorComJuros = saldo * TAXA_JUROS;
            saldo += (valor - valorComJuros);
        } else {
            saldo += valor;
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
