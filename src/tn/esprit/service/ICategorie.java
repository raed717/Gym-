/*
 * To change this tufytflicense header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.SQLException;

/**
 *
 * @author firas
 */
public interface ICategorie <T> {
        public void ajouterc(T p) throws SQLException;

}
