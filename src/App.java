import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        RegistroDoTempoRepository repository = new RegistroDoTempoRepository("poa_temps.txt");
        repository.carregaDados();
        Consultas consultas = new Consultas(repository);
        System.out.println("Dia em que mais choveu no ano de 1980: ");
        System.out.println(consultas.diaQueMaisChoveuNoAno(1980));
        System.out.println("Datas em que choveu mais de 90 milimetros");
        consultas.datasEmQueChouveuMaisDe(90)
            .forEach(System.out::println);

        System.out.println("consultas padrão");
        consultas.diasEmQue().forEach(System.out::println);
        Predicate<RegistroDoTempo> consulta = r -> r.getPrecipitacao() > 90;
        consultas.alteraConsultaPadrao(consulta);

        System.out.println("consulta alterada");
        consultas.diasEmQue().forEach(System.out::println);
    }
}
