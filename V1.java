import java.util.*;

class User {
    String name;
    String email;
    String password;
    List<Reflexao> history = new ArrayList<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

class Reflexao {
    String reading;
    String meditation;
    String prayer;
    String contemplation;
    String date;

    public Reflexao(String reading, String meditation, String prayer, String contemplation, String date) {
        this.reading = reading;
        this.meditation = meditation;
        this.prayer = prayer;
        this.contemplation = contemplation;
        this.date = date;
    }
}

public class V1 {
    static List<User> users = new ArrayList<>();
    static User currentUser = null;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Cadastrar\n2. Login\n3. Sair");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: register(); break;
                case 2: login(); break;
                case 3: System.exit(0);
                default: System.out.println("Opção inválida.");
            }
        }
    }

    static void register() {
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();
        
        users.add(new User(name, email, password));
        System.out.println("Cadastro realizado com sucesso!");
    }

    static void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();
        
        for (User u : users) {
            if (u.email.equals(email) && u.password.equals(password)) {
                currentUser = u;
                System.err.println("Olá, " + currentUser.name + " Bem Vindo!");
                userMenu();
                return;
            }
        }
        System.out.println("Email ou senha incorretos.");
    }

    static void userMenu() {
        while (true) {
            System.out.println("\n1. Ver passagem do dia\n2. Registrar Lectio Divina\n3. Ver histórico\n4. Sair");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: displayPassage(); break;
                case 2: registerLectio(); break;
                case 3: viewHistory(); break;
                case 4: return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    static void displayPassage() {
        String passage = "Mateus 6:33 - Buscai primeiro o Reino de Deus e a sua justiça.";
        System.out.println("Passagem do dia: " + passage);
    }

    static void registerLectio() {
        System.out.print("Leitura: ");
        String reading = scanner.nextLine();
        System.out.print("Meditação: ");
        String meditation = scanner.nextLine();
        System.out.print("Oração: ");
        String prayer = scanner.nextLine();
        System.out.print("Contemplação: ");
        String contemplation = scanner.nextLine();
        String date = new Date().toString();

        Reflexao reflexao = new Reflexao(reading, meditation, prayer, contemplation, date);
        currentUser.history.add(reflexao);
        
        System.out.println("Reflexão salva com sucesso!");
        System.out.println("Link para compartilhamento: https://lectio.app/share?data=" + date.replace(" ", "_"));
    }

    static void viewHistory() {
        if (currentUser.history.isEmpty()) {
            System.out.println("Nenhuma reflexão salva ainda.");
            return;
        }
        
        for (Reflexao r : currentUser.history) {
            System.out.println("\nData: " + r.date);
            System.out.println("Leitura: " + r.reading);
            System.out.println("Meditação: " + r.meditation);
            System.out.println("Oração: " + r.prayer);
            System.out.println("Contemplação: " + r.contemplation);
        }
    }
}
