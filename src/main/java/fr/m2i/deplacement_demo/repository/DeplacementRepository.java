package fr.m2i.deplacement_demo.repository;

import fr.m2i.deplacement_demo.model.DeplacementModel;
import fr.m2i.deplacement_demo.model.InfirmierModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface DeplacementRepository extends MongoRepository<DeplacementModel, String> {
      List<DeplacementModel> findDeplacementModelByDateDeDeplacementIsAfter(LocalDate localDate);
      List <DeplacementModel> findDeplacementModelByIdPatientAndDateDeDeplacementIsAfter( String idPatient, LocalDate localDate);
      List <DeplacementModel> findDeplacementModelByIdInfirmierAndDateDeDeplacementIsAfter(String idInfirmier, LocalDate date);
}
