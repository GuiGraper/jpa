import javax.persistence.*;
import java.util.*;


@Entity
class HorarioAulas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name="aulas", joinColumns=@JoinColumn(name="horario_id"))
    @MapKeyColumn(name="dia_semana")
    @Column(name="materia")
    private Map<DiaSemana, String> aulas;

    public HorarioAulas() {
        this.aulas = new HashMap<>();
    }

}

enum DiaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA
}

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("horarioPU");
        EntityManager em = emf.createEntityManager();


        em.getTransaction().begin();
        inserirDados(em);
        em.getTransaction().commit();
        // commit();
        // change.close();
        consultarHorario(em);

        em.close();
        emf.close();
    }

    private static void inserirDados(EntityManager em) {
        HorarioAulas horario = new HorarioAulas();
      // FENS. IHC. DESWEB. ALPC. MAD
        horario.getAulas().put(DiaSemana.SEGUNDA, "FENS"); 
        horario.getAulas().put(DiaSemana.TERCA, "IHC");
        horario.getAulas().put(DiaSemana.QUARTA, "MAD");
        horario.getAulas().put(DiaSemana.QUINTA, "DESWEB");
        horario.getAulas().put(DiaSemana.SEXTA, "ALPC");

       //get(horario).
      //get))
        em.persist(horario);
    }

    private static void consultarHorario(EntityManager em) {
        // csl.
        TypedQuery<HorarioAulas> query = em.createQuery("SELECT h FROM HorarioAulas h", HorarioAulas.class);
        List<HorarioAulas> horarios = query.getResultList();

        // for (HorarioAulas horario : horarios) { F
        for (HorarioAulas horario : horarios) {
            System.out.println("Horário de Aulas:");
            for (Map.Entry<DiaSemana, String> aula : horario.getAulas().entrySet()) {
                System.out.println(aula.getKey() + ": " + aula.getValue());
            }
        }
    }
}
