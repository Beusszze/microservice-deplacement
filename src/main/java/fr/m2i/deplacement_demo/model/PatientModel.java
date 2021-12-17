package fr.m2i.deplacement_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientModel {
    private String nom;
    private String prenom;
}
