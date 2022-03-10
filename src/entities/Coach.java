/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author saisi
 */
public class Coach {

    int id_coach;
    String nom_coach, specialite, mail, mdp_coach;

    public Coach(int id_coach, String nom_coach, String specialite, String mail, String mdp_coach) {
        this.id_coach = id_coach;
        this.nom_coach = nom_coach;
        this.specialite = specialite;
        this.mail = mail;
        this.mdp_coach = mdp_coach;
    }

    public Coach(String nom_coach, String specialite, String mail, String mdp_coach) {
        this.nom_coach = nom_coach;
        this.specialite = specialite;
        this.mail = mail;
        this.mdp_coach = mdp_coach;
    }

    public int getId_coach() {
        return id_coach;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp_coach() {
        return mdp_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp_coach(String mdp_coach) {
        this.mdp_coach = mdp_coach;
    }

    @Override
    public String toString() {
        return "Coach{" + "id_coach=" + id_coach + ", nom_coach=" + nom_coach + ", specialite=" + specialite + ", mail=" + mail + ", mdp_coach=" + mdp_coach + '}';
    }

}
