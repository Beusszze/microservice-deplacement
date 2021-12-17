package fr.m2i.deplacement_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeplacementInfirmierPatientModel {
    private PatientModel patient;
    private InfirmierModel infirmier;
    private DeplacementModel deplacement;
}
