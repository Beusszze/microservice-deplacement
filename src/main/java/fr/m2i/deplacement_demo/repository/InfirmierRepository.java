package fr.m2i.deplacement_demo.repository;

import fr.m2i.deplacement_demo.model.DeplacementModel;
import fr.m2i.deplacement_demo.model.InfirmierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class InfirmierRepository {

    @Autowired
    private WebClient webClient;

    @Value("${envInf.uri}")
    private String uri;

    public Mono<InfirmierModel> getInfirmierById (String id) {
            Mono <InfirmierModel> infirmier = webClient.get()
                    .uri(uri + "/infirmiers/" + id)
                    .retrieve()
                    .bodyToMono(InfirmierModel.class);
            return infirmier;
    }
}
