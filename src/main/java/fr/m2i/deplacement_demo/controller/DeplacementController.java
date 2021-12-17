package fr.m2i.deplacement_demo.controller;

import fr.m2i.deplacement_demo.model.DeplacementInfirmierPatientModel;
import fr.m2i.deplacement_demo.model.DeplacementModel;
import fr.m2i.deplacement_demo.service.DeplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("deplacements")
public class DeplacementController {

    @Autowired
    DeplacementService service;

    /**
     * Route permettant d'appeler la méthode findAll qui affiche tous les déplacements
     * @return la liste des déplacements
     */
    @GetMapping
    public List<DeplacementModel> findAll () {
        return service.findAll();
    }

    /**
     * Route permettant d'appeler la méthode findOne qui affiche un déplacement avec son id
     * @param id : l'id du déplacement
     * @return le déplacement
     */
    @GetMapping("{id}")
    public DeplacementModel findById (@PathVariable String id) {
        return service.findOne(id);
    }

    /**
     * Route permettant d'appeler la méthode create qui sauvegarde un déplacement
     * @param deplacementModel : le déplacement à sauvegarder
     * @return le déplacement sauvegardé
     */
    @PostMapping()
    public DeplacementModel create(@RequestBody DeplacementModel deplacementModel) {
        return service.create(deplacementModel);
    }

    /**
     * Route permettant d'appeler la méthode update qui sauvegarde un déplacement
     * @param deplacementModel : le déplacement à mettre à jour
     * @return le déplacement sauvegardé
     */
    @PutMapping
    public DeplacementModel update(@RequestBody DeplacementModel deplacementModel) {
        return service.update(deplacementModel);
    }

    /**
     * Route permettant d'appeler la méthode delete qui supprime un déplacement
     * @param id : l'id du déplacement à supprimer
     * @return "déplacement supprimé"
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete (@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("déplacement supprimé");
    }

    /**
     * Route permettant d'appeler la méthode findNext qui affiche les déplacements futurs
     * @return la liste des déplacements futurs
     */
    @GetMapping("/a-venir")
    public List<DeplacementModel> findNext() {
        return service.findNext();
    }

    /**
     * Route permettant d'appeler la méthode findByPatient qui affiche les déplacements à venir du patient
     * @param idPatient : l'id du patient
     * @return la liste des déplacements à venir du patient
     */
    @GetMapping("/a-venir/patients/{idPatient}")
    public List<DeplacementModel> findByPatient(@PathVariable String idPatient) {
        return service.findByPatient(idPatient);
    }

    /**
     * Route permettant d'appeler la méthode findByInfirmie qui affiche les déplacements à venir de l'infirmier
     * @param idInfirmier : l'id de l'infirmier
     * @return la liste des déplacements à venir de l'infirmier
     */
    @GetMapping("/a-venir/infirmiers/{idInfirmier}")
    public List<DeplacementModel> findByInfirmier(@PathVariable String idInfirmier) {
        return service.findByInfirmier(idInfirmier);
    }

    /**
     * Route permettant d'appeler la méthode findDetails qui affiche les détails d'un déplacement
     * ainsi que les noms et prénoms du patient et de l'infirmier
     * @param idDeplacement : l'id du déplacement
     * @return le détail du déplacement et le nom et prénom de l'infirmier et du patient
     */
    @GetMapping("details/{idDeplacement}")
    public DeplacementInfirmierPatientModel findDetails (@PathVariable String idDeplacement) {
        return service.findDetail(idDeplacement);
    }

}