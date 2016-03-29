package carAkka.actor;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Node extends UntypedActor {
	protected ArrayList<Node> enfants;
	protected String nom;
	
	/**
	 * Constructeur de la classe Node
	 * @param nom permet d'identifier le noeud dans l'arbre
	 * utile si on veut donner un ordre a ce noeud
	 */
	public Node(String nom){
		this.nom = nom ;
		this.enfants = new ArrayList<Node>();
	}
	
	/**
	 * 
	 * @param n un noeud va etre ajouté à la liste des fils
	 * @return True si ça s est bien passé
	 */
	public boolean addChild(Node n){
		return enfants.add(n) ;
	}
	
	/**
	 * 
	 * @param n le noeud a retiré de la liste des fils
	 * @return True si ça s est bien passé
	 */
	public boolean removeChild(Node n){
		return enfants.remove(n);
	}
	
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String){
			String chaine = message.toString() ;
			System.out.println("Message :"+chaine+"reçu sur le noeud "+this.nom);
			if(chaine.startsWith("CREATE")) {
				System.out.println("ajout noeud");
				String[] separe = chaine.split(" ") ; 
			}
		}
		// TODO Auto-generated method stub
		//On envoie au fils
		
	}

}
