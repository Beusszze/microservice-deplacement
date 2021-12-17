package fr.m2i.deplacement_demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("deplacements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeplacementModel {
    @Id
    private String id;
    private Date dateDeDeplacement;
    private Float prix;
    private String idInfirmier;
    private String idPatient;

}
