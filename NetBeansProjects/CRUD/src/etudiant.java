/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LGC
 */
public class etudiant {
private String nom;
private double note;public etudiant(String nom, double note){
this.nom = nom;
this.note = note;
}
public String getNom(){
return nom;
}
public double getNote(){
return note;
}
public void setNom(String nom){
this.nom = nom;
}
public void setNote(double note){
this.note = note;
}
public boolean estAdmis(){
return note>=10.0;
}
@Override
public String toString(){
return "Nom: " + nom + "Note: " + note;
} }