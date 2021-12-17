package fr.m2i.deplacement_demo.repository;

import fr.m2i.deplacement_demo.model.InfirmierModel;
import fr.m2i.deplacement_demo.model.PatientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class PatientRepository {

    @Autowired
    private WebClient webClient;

    @Value("${envPat.uri}")
    private String uri;

    public Mono<PatientModel> getPatientById (String id) {
            Mono <PatientModel> patient = webClient.get()
                    .uri(uri + "/patients/" + id)
                    .retrieve()
                    .bodyToMono(PatientModel.class);
            return patient;
    }
}
