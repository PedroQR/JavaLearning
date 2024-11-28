package teste.projetoteste.src.ExRevisao.ex1;
public class Main {
    public static void main(String[] args) {
        // Criando um usuário
        Usuario usuario = new Usuario("123.456.789-00", "João Silva", "Rua A, 123");

        // Criando uma conta bancária para o usuário
        ContaBancaria conta = new ContaBancaria(12345, usuario, 100);

        // Exibindo informações do usuário e da conta
        System.out.println("Informações da Conta:");
        System.out.println("Número da Conta: " + conta.getNumeroConta());
        System.out.println("Proprietário: " + conta.getUsuario().getNome());
        System.out.println("CPF: " + conta.getUsuario().getCpf());
        System.out.println("Endereço: " + conta.getUsuario().getEndereco());
        System.out.println("Saldo inicial: " + conta.getSaldo());
        System.out.println("Limite: " + conta.getLimite());

        // Realizando um depósito
        conta.depositar(200);
        System.out.println("\nDepósito de R$200 realizado.");
        System.out.println("Saldo atual: " + conta.getSaldo());

        // Tentando sacar um valor dentro do limite
        if (conta.sacar(250)) {
            System.out.println("\nSaque de R$250 realizado.");
        } else {
            System.out.println("\nSaque de R$250 não permitido.");
        }
        System.out.println("Saldo após saque: " + conta.getSaldo());

        // Tentando sacar um valor fora do limite
        if (conta.sacar(150)) {
            System.out.println("\nSaque de R$150 realizado.");
        } else {
            System.out.println("\nSaque de R$150 não permitido.");
        }
        System.out.println("Saldo após tentativa de saque: " + conta.getSaldo());

        // Realizando um depósito com saldo negativo
        conta.depositar(100);
        System.out.println("\nDepósito de R$100 realizado com saldo negativo.");
        System.out.println("Saldo final: " + conta.getSaldo());

        // Modificando as informações do usuário
        conta.getUsuario().setEndereco("Rua B, 456");
        System.out.println("\nEndereço atualizado do proprietário: " + conta.getUsuario().getEndereco());

        // Modificando o limite da conta
        conta.setLimite(200);
        System.out.println("Limite atualizado: " + conta.getLimite());
    }
}
