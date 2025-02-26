import java.util.*;

class user {
    String name;
    String email;
    String password;
    List<Reflexao> historico = new ArrayList<>();

    public user(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

class Reflexao {
    String leitura;
    String meditacao;
    String oracao;
    String contemplacao;
    String data;

    public Reflexao(String leitura, String meditacao, String oracao, String contemplacao, String data) {
        this.leitura = leitura;
        this.meditacao = meditacao;
        this.oracao = oracao;
        this.contemplacao = contemplacao;
        this.data = data;
    }
}

public class V1 {
    static List<user> users = new ArrayList<>();
    static user userAtual = null;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Cadastrar\n2. Login\n3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            
            switch (escolha) {
                case 1: cadastrar(); break;
                case 2: login(); break;
                case 3: System.exit(0);
                default: System.out.println("Opção inválida.");
            }
        }
    }

    static void cadastrar() {
        System.out.print("name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();
        
        users.add(new user(name, email, password));
        System.out.println("Cadastro realizado com sucesso!");
    }

    static void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();
        
        for (user u : users) {
            if (u.email.equals(email) && u.password.equals(password)) {
                userAtual = u;
                System.err.println("Olá, " + userAtual.name + " Bem Vindo!");
                MenuUser();
                return;
            }
        }
        System.out.println("Email ou password incorretos.");
    }

    static void MenuUser() {
        while (true) {
            System.out.println("\n1. Ver passagem do dia\n2. Registrar Lectio Divina\n3. Ver histórico\n4. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: exibirPassagem(); break;
                case 2: registrarLectio(); break;
                case 3: verHistorico(); break;
                case 4: return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    static void exibirPassagem() {
        String passagem = "Mateus 6:33 - Buscai primeiro o Reino de Deus e a sua justiça.";
        System.out.println("Passagem do dia: " + passagem);
    }

    static void registrarLectio() {
        System.out.print("Leitura: ");
        String leitura = scanner.nextLine();
        System.out.print("Meditação: ");
        String meditacao = scanner.nextLine();
        System.out.print("Oração: ");
        String oracao = scanner.nextLine();
        System.out.print("Contemplação: ");
        String contemplacao = scanner.nextLine();
        String data = new Date().toString();

        Reflexao reflexao = new Reflexao(leitura, meditacao, oracao, contemplacao, data);
        userAtual.historico.add(reflexao);
        
        System.out.println("Reflexão salva com sucesso!");
        System.out.println("Link para compartilhamento: https://lectio.app/share?data=" + data.replace(" ", "_"));
    }

    static void verHistorico() {
        if (userAtual.historico.isEmpty()) {
            System.out.println("Nenhuma reflexão salva ainda.");
            return;
        }
        
        for (Reflexao r : userAtual.historico) {
            System.out.println("\nData: " + r.data);
            System.out.println("Leitura: " + r.leitura);
            System.out.println("Meditação: " + r.meditacao);
            System.out.println("Oração: " + r.oracao);
            System.out.println("Contemplação: " + r.contemplacao);
        }
    }
}
