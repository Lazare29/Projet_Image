package schema;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class Base {


Connection co = null;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean ouvrir() {
		System.out.println("Ouverture de la base");
		boolean res = false;
		
		String url = null;
		String user = null;
		String password = null;
		
		ResourceBundle rb = 
			ResourceBundle.getBundle
				("config/config");
		url = rb.getString("url");
		user = rb.getString("user");
		password = rb.getString("password");
		
		System.out.println("url = "+url);
		System.out.println("user = "+user);
		
		try {
			co = DriverManager.getConnection(url, user, password);
			res = true;
		} catch (SQLException e) {
			System.out.println("erreur getConnection "+
					e.getMessage());
			e.printStackTrace();
		}

		return res;
	}
	
	
	/**
	 * MODIFICATION DES INFORMATIONS D'UN FILM
	 * UPDATE Film
		SET nom_colonne_1 = 'nouvelle valeur'
		WHERE film_id=''
	 * @param film
	 * @return
	 */
	
	/*EXEMPLE DE REQUETE SUR UNE BASE DE DONNEES
	public boolean modifierFilm(Film film) {
		String requete;
		if(film.getNom()!=null) {
			System.out.println("Nom a changer");
			try {
				requete = "UPDATE Film SET film_nom = '" + film.getNom() + "' WHERE film_id='" + film.getId() + "'";
				System.out.println("REQUETE NOM " + requete);
				Statement stmt = co.createStatement();
				stmt.execute(requete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(film.getDescription()!=null) {
			System.out.println("Description a changer");
			try {
				requete = "UPDATE Film SET film_description = '" + film.getDescription() + "' WHERE film_id='" + film.getId() + "'";
				System.out.println("REQUETE DESCRIPTION " + requete);
				Statement stmt = co.createStatement();
				stmt.execute(requete);
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(film.getDate()!=null) {
			System.out.println("Date a changer");
			try {
				requete = "UPDATE Film SET film_date_sortie= (\"" + film.getDate() + "\") WHERE film_id='" + film.getId() + "'";
				System.out.println("REQUETE DATE " + requete);
				Statement stmt = co.createStatement();
				stmt.execute(requete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	*/
	
	public String recuperationImage() {
		return null;
	}
	
	public boolean ajoutImage(Image img){
		String requete=null;
		try {
			requete= "INSERT INTO `t_image` (`jpeg`, `titre`) VALUES ('" + img.getMonImage() + "','" + img.getTitre() +"');";
			System.out.println(requete);
			Statement stmt = co.createStatement();
			stmt.execute(requete);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean fermer() {
		System.out.println("Fermeture de la base ");
		try {co.close();}catch (Exception e) {}

		return true;
	}
	
	public static void main(String [] args) {
		Base base = new Base();
		base.ouvrir();	
		base.fermer();
	}
	
	
	
}
