import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {

	/** Quantidade máxima de produtos que podem ser armazenados no vetor */
    static final int MAX_NOVOS_PRODUTOS = 10;

    /** Nome do arquivo de dados. O arquivo deve estar localizado na raiz do projeto */
    static String nomeArquivoDados;
    
    /** Scanner para leitura de dados do teclado */
    static Scanner teclado;

    /** Vetor de produtos cadastrados */
    static Produto[] produtosCadastrados;

    /** Quantidade de produtos cadastrados atualmente no vetor */
    static int quantosProdutos = 0;

    /** Quantidade máxima de pedidos que podem ser armazenados no vetor */
    static final int MAX_PEDIDOS = 10;
    
    /** Vetor de pedidos cadastrados */
    static Pedido[] pedidosCadastrados;
    
    /** Quantidade de pedidos cadastrados atualmente no vetor */
    static int quantPedidos = 0;
    
    /** Gera um efeito de pausa na CLI. Espera por um enter para continuar */
    static void pausa() {
        System.out.println("Digite enter para continuar...");
        teclado.nextLine();
    }

    /** Cabeçalho principal da CLI do sistema */
    static void cabecalho() {
        System.out.println("AEDs II COMÉRCIO DE COISINHAS");
        System.out.println("=============================");
    }
    
    /** Imprime o menu principal, lê a opção do usuário e a retorna (int).
     * @return Um inteiro com a opção do usuário.
    */
    static int menu() {
        cabecalho();
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Imprimir dados dos pedidos, por data");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        return Integer.parseInt(teclado.nextLine());
    }
    
    /**
     * Lê os dados de um arquivo-texto e retorna um vetor de produtos. Arquivo-texto no formato
     * N  (quantidade de produtos) <br/>
     * tipo;descrição;preçoDeCusto;margemDeLucro;[dataDeValidade] <br/>
     * Deve haver uma linha para cada um dos produtos. Retorna um vetor vazio em caso de problemas com o arquivo.
     * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
     * @return Um vetor com os produtos carregados, ou vazio em caso de problemas de leitura.
     */
    static Produto[] lerProdutos(String nomeArquivoDados) {
    	
    	Scanner arquivo = null;
    	int numProdutos, i;
    	String linha;
    	Produto produto;
    	Produto[] produtosCadastrados = new Produto[MAX_NOVOS_PRODUTOS];
    	
    	try {
    		arquivo = new Scanner(new File(nomeArquivoDados), Charset.forName("UTF-8"));
    		
    		numProdutos = Integer.parseInt(arquivo.nextLine());
    		for (i = 0; (i < numProdutos && i < MAX_NOVOS_PRODUTOS); i++) {
    			linha = arquivo.nextLine();
    			produto = Produto.criarDoTexto(linha);
    			produtosCadastrados[i] = produto;
    		}
    		quantosProdutos = i;
    		
    	} catch (IOException excecaoArquivo) {
    		produtosCadastrados = null;
    	} finally {
    		arquivo.close();
    	}
    	
    	return produtosCadastrados;
    }
    
    /**
     * Salva os dados dos produtos cadastrados no arquivo csv informado. Sobrescreve todo o conteúdo do arquivo.
     * @param nomeArquivo Nome do arquivo a ser gravado.
     */
    public static void salvarProdutos(String nomeArquivo) {
    
    	FileWriter arquivo = null;
    	
    	try {
    		arquivo = new FileWriter((nomeArquivo), Charset.forName("UTF-8"));
    		
    		arquivo.append(quantosProdutos + "\n");
    		
    		for (int i = 0; i < quantosProdutos; i++) {
    			arquivo.append(produtosCadastrados[i].gerarDadosTexto() + "\n");
    		}
    		arquivo.close();
    		System.out.println("Arquivo " + nomeArquivo + " salvo com sucesso.");
    	} catch (IOException excecao) {
    		System.out.println("Problemas no arquivo " + nomeArquivo + ". Tente novamente");
    	}
    }
    
    /**
     * Rotina para cadastro de um novo produto: pergunta ao usuário o tipo do produto, lê os dados correspondentes,
     * cria o objeto adequado de acordo com o tipo, inclui o produto no vetor.
     */
    static void cadastrarProduto() {
    	
    	int tipo;
    	String descricao;
    	double precoCusto, margemLucro;
    	LocalDate dataDeValidade;
    	Produto produto;
    	
    	cabecalho();
        System.out.println("Cadastro de novo produto:");
        System.out.println("1 - Não perecível (padrão)");
        System.out.println("2 - Perecível");
        System.out.print("Digite o tipo de produto desejado: ");
        tipo = Integer.parseInt(teclado.nextLine());
        if (tipo != 2) {
            tipo = 1;
        }
        System.out.print("\nDescrição do produto: ");
        descricao = teclado.nextLine();
        System.out.print("Preço de custo: R$ ");
        precoCusto = Double.parseDouble(teclado.nextLine());
        System.out.print("Margem de lucro: ");
        margemLucro = Double.parseDouble(teclado.nextLine());
        if (tipo == 2) {
        	System.out.print("Data de validade no formato dd/mm/yyyy: ");
        	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	dataDeValidade = LocalDate.parse(teclado.nextLine(), formatoData); 	
        	produto = new ProdutoPerecivel(descricao, precoCusto, margemLucro, dataDeValidade);
        } else {
        	produto = new ProdutoNaoPerecivel(descricao, precoCusto, margemLucro);
        }
        
        produtosCadastrados[quantosProdutos++] = produto;
        System.out.println(descricao + " cadastrado com sucesso. Total de " + quantosProdutos + " produtos cadastrados no sistema.");
    }  

    /**
     * Lê os dados de um arquivo-texto e retorna um vetor de pedidos. Arquivo-texto no formato
     * N  (quantidade de pedidos) <br/>
     * dataDoPedido;formaDePagamento;descrições dos produtos do pedido <br/>
     * Deve haver uma linha para cada um dos pedidos. Retorna um vetor vazio em caso de problemas com o arquivo.
     * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
     * @return Um vetor com os pedidos carregados, ou vazio em caso de problemas de leitura.
     */
    
    static Pedido[] lerPedidos(String nomeArquivoDados) {
        // Formato esperado do arquivo:
        // 1ª linha: quantidade de pedidos (N)
        // Demais linhas: data(DD/MM/AAAA);formaDePagamento(1=à vista,2=parcelado);Produto1;Produto2;...
        File arquivo = new File(nomeArquivoDados);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de pedidos não encontrado: " + nomeArquivoDados);
            return new Pedido[0];
        }
        try (java.util.Scanner in = new java.util.Scanner(arquivo, java.nio.charset.Charset.forName("UTF-8"))) {
            if (!in.hasNextLine()) return new Pedido[0];
            int n = Integer.parseInt(in.nextLine().trim());
            Pedido[] pedidos = new Pedido[n];
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            int k = 0;
            while (in.hasNextLine() && k < n) {
                String linha = in.nextLine().trim();
                if (linha.isEmpty()) continue;
                String[] partes = linha.split(";");
                if (partes.length < 2) continue;
                LocalDate data = LocalDate.parse(partes[0].trim(), fmt);
                int forma = Integer.parseInt(partes[1].trim());
                Pedido ped = new Pedido(data, forma);
                // adiciona produtos pelo nome (a partir da terceira coluna)
                for (int i = 2; i < partes.length; i++) {
                    String nome = partes[i].trim();
                    Produto p = localizarProdutoPorDescricao(nome);
                    if (p != null) {
                        ped.adicionarProduto(p);
                    } else {
                        System.out.println("Aviso: produto '" + nome + "' não encontrado no cadastro e foi ignorado.");
                    }
                }
                pedidos[k++] = ped;
            }
            // Caso o arquivo tenha menos linhas do que N, ajusta o tamanho
            if (k < n) {
                Pedido[] ajustado = new Pedido[k];
                for (int i = 0; i < k; i++) ajustado[i] = pedidos[i];
                pedidos = ajustado;
            }
            return pedidos;
        } catch (Exception e) {
            System.out.println("Erro ao ler pedidos: " + e.getMessage());
            return new Pedido[0];
        }
    }
    
    
    /** Localiza pedidos no vetor de pedidos cadastrados, a partir da data do pedido informada pelo usuário,
     *  e imprime seus dados.
     *  Em caso de não encontrar nenhum pedido, imprime uma mensagem padrão */
    
    static void localizarPedidosPorData() {
        if (pedidosCadastrados == null || pedidosCadastrados.length == 0) {
            System.out.println("Não há pedidos cadastrados.");
            return;
        }
        System.out.println("Informe a data (dd/MM/aaaa):");
        String entrada = teclado.nextLine();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataBuscada;
        try {
            dataBuscada = LocalDate.parse(entrada.trim(), fmt);
        } catch (Exception e) {
            System.out.println("Data inválida.");
            return;
        }
        int encontrados = 0;
        for (Pedido p : pedidosCadastrados) {
            if (p != null && dataBuscada.equals(p.getDataPedido())) {
                System.out.println("----------------------");
                System.out.print(p.toString());
                encontrados++;
            }
        }
        if (encontrados == 0) {
            System.out.println("Nenhum pedido encontrado para a data " + dataBuscada.format(fmt) + ".");
        }
    }
    
    
	public static void main(String[] args) {
		teclado = new Scanner(System.in, Charset.forName("UTF-8"));
        nomeArquivoDados = "dadosProdutos.csv";
        produtosCadastrados = lerProdutos(nomeArquivoDados);
        String nomeArquivoPedidos = "dadosPedidos.csv";
        pedidosCadastrados = lerPedidos(nomeArquivoPedidos);
        
        int opcao = -1;
      
        do{
            opcao = menu();
            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> localizarPedidosPorData();
            }
            pausa();
        }while(opcao != 0);       

        salvarProdutos(nomeArquivoDados);
        teclado.close();    
    }
}