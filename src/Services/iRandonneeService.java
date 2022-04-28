/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Randonnee;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public interface iRandonneeService {
     public void ajouterRandonnee(Randonnee r);
    public void modifierRandonnee(Randonnee r,int id); 
    public void supprimerRandonnee(int id);
    public ArrayList<Randonnee> afficherRandonnee();
    
}
