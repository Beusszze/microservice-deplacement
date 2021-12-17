package fr.m2i.deplacement_demo.service;

import fr.m2i.deplacement_demo.model.DeplacementInfirmierPatientModel;
import fr.m2i.deplacement_demo.model.DeplacementModel;
import fr.m2i.deplacement_demo.model.InfirmierModel;
import fr.m2i.deplacement_demo.model.PatientModel;
import fr.m2i.deplacement_demo.repository.DeplacementRepository;
import fr.m2i.deplacement_demo.repository.InfirmierRepository;
import fr.m2i.deplacement_demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DeplacementService {

    @Autowired
    DeplacementRepository repository;

    @Autowired
    InfirmierRepository infirmierRepository;

    @Autowired
    PatientRepository patientRepository;

    /**
     * Méthode permettant de sauvegarder un DeplacementModel
     * @param deplacementModel : le DeplacementModel a créer
     * @return le DeplacementModel sauvegardé
     */
    public DeplacementModel create(DeplacementModel deplacementModel) {
        return repository.save(deplacementModel);
    }

    /**
     * Méthode permettant de mettre à jour un DeplacementModel
     * @param deplacementModel : le DeplacementModel à mettre à jour
     * @return le DeplacementModel sauvegardé
     */
    public DeplacementModel update(DeplacementModel deplacementModel) {
        return repository.save(deplacementModel);
    }

    /**
     * Méthode permettant de supprimer un déplacement par son id
     * @param id : l'id du déplacement à supprimer
     */
    public void delete(String id) {
        repository.deleteById(id);
    }

    /**
     * Méthode permettant d'obtenir tous les déplacements
     * @return la liste des déplacements
     */
    public List<DeplacementModel> findAll() {
        return repository.findAll();
    }

    /**
     * Méthode permettant de trouver un déplacement par son id
     * @param id : l'id du déplacement
     * @return le déplacement
     */
    public DeplacementModel findOne(String id) {
        return repository.findById(id).get();
    }


    /**
     * Méthode permettant d'afficher les déplacements à venir
     * @return la liste des déplacements après la date actuelle
     */
    public List<DeplacementModel> findNext() {
        LocalDate currentDate = LocalDate.now();
        List<DeplacementModel> deplacements =
                repository.findDeplacementModelByDateDeDeplacementIsAfter(currentDate);
        return deplacements;
    }

    /**
     * Méthode permettant d'afficher la liste des déplacements à venir par patient selon son id
     * @param idPatient : l'id du patient
     * @return la liste des déplacements à venir du patient
     */
    public List<DeplacementModel> findByPatient(String idPatient) {
        LocalDate currentDate = LocalDate.now();
        return repository.findDeplacementModelByIdPatientAndDateDeDeplacementIsAfter(idPatient, currentDate);
    }


    /**
     * Méthode permettant d'afficher la liste des déplacements à venir par infirmier selon son id
     * @param idInfirmier : l'id de l'infirmier
     * @return la liste des déplacements à venir de l'infirmier
     */
    public List<DeplacementModel> findByInfirmier(String idInfirmier) {
        LocalDate currentDate = LocalDate.now();
        return repository.findDeplacementModelByIdInfirmierAndDateDeDeplacementIsAfter(idInfirmier, currentDate);
    }


    /**
     * Méthode permettant de récupérer les détails d'un déplacement à partir de son Id
     * et affiche le nom et le prénom du patient et de l'infirmier
     * @param id : l'id du déplacement
     * @return un objet DeplacementInfirmierPatientModel
     */
    public DeplacementInfirmierPatientModel findDetail(String id) {
        // Récupération du déplacement avec son id
        Optional<DeplacementModel> deplacement = repository.findById(id);
        DeplacementInfirmierPatientModel deplacementInfirmierPatient = null;
        if (deplacement.isPresent()) {
            // Appel du microservice infirmier pour récupérer l'infirmier du déplacement
            String idInfirmier = deplacement.get().getIdInfirmier();
            Mono<InfirmierModel> infirmierMono = infirmierRepository.getInfirmierById(idInfirmier);
            InfirmierModel infirmier = infirmierMono.block();
            // Appel du microservice patient pour récupérer le patient du déplacement
            String idPatient = deplacement.get().getIdPatient();
            Mono<PatientModel> patientMono = patientRepository.getPatientById(idPatient);
            PatientModel patient = patientMono.block();
            // Fusion des datas dans le model désiré
            deplacementInfirmierPatient =
                    new DeplacementInfirmierPatientModel(
                            patient, infirmier, deplacement.get()
                    );

        }
             return deplacementInfirmierPatient;
    }
}