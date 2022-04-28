/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Participant;
import Entities.Randonnee;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public interface iParticipantService {
      public void ajouterParticipant(Participant p);
    public void modifierParticipant(Participant p,int id); 
    public void supprimerParticipant(int id);
    public ArrayList<Participant> afficherParticipant();
     public int rechercherRandonnee(String nom);
}
