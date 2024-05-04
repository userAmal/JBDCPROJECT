package demoJDBC;

import java.util.List;

import JDBCProjet.connexion.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.service.ClientService;

public class Test {
	public static void main (String args[]) {
		Connexion.getConnection();
		ClientService cs=new ClientService();
		//Client c=new Client("firas","saadi");
		//cs.create(c);
//		Client c=new Client("meryem","bb");
//		cs.create(c);
//		Client c1=new Client("amal","bouaouina");
//		cs.create(c1);
//		Client c2=new Client("soumaya","anniba");
//		cs.create(c2);
//		Client c3=new Client("syrine","bousetta");
//		cs.create(c3);
		List<Client> lc=cs.findAll();
		for(Client cl:lc) {
			System.out.println(cl.toString());
		}
//		Client c=cs.findById(3);
//		System.out.println("le client d'id 3"+c);
		Client c1=cs.findById(4);
		if(c1!=null)
		cs.delete(c1);
		cs.update(new Client(6,"mohamed","aa"));
	}

}
