package carAkka.actor;

import message.Configuration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class Node extends UntypedActor {
	protected ActorRef gauche ;
	protected ActorRef droit ;
	protected String nom;
	
	/**
	 * Constructeur de la classe Node
	 * @param nom permet d'identifier le noeud dans l'arbre
	 * utile si on veut donner un ordre a ce noeud
	 */
	public Node(String nom){
		this.nom = nom ;
		this.gauche = null ;
		this.droit = null ;
	}
	
	public void setGauche(ActorRef gauche) {
		this.gauche = gauche ;
	}
	
	public void setDroit(ActorRef droit) {
		this.droit = droit ;
	}
	
	
	public void sendMessageToChilds(Object message){
		if (this.gauche != null) {
			this.gauche.tell(message, this.getSelf()) ;
		}
		if (this.droit != null) {
			this.droit.tell(message, this.getSelf());
		}
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String){
			String chaine = message.toString() ;
			System.out.println("Message :"+chaine+" reçu sur le "+this.nom);
			this.sendMessageToChilds(chaine + " passé par  "+ this.nom) ;
			
		}else if(message instanceof Configuration){
			System.out.println("On reçoit un objet config");
			Configuration conf = (Configuration) message ;
			this.setGauche(conf.getGauche()) ;
			this.setDroit(conf.getDroit()) ;
			
		}
		// TODO Auto-generated method stub
		//On envoie au fils
		
	}
	
	public static void main(String[] argz) {
		System.out.println("#####Création du systeme#####");
		ActorSystem syst = ActorSystem.create("communisme");
		ActorRef noeud1 = syst.actorOf(Props.create(Node.class, "noeud1")) ;
		ActorRef noeud2 = syst.actorOf(Props.create(Node.class, "noeud2")) ;
		ActorRef noeud3 = syst.actorOf(Props.create(Node.class, "noeud3")) ;
		ActorRef noeud4 = syst.actorOf(Props.create(Node.class, "noeud4")) ;
		ActorRef noeud5 = syst.actorOf(Props.create(Node.class, "noeud5")) ;
		ActorRef noeud6 = syst.actorOf(Props.create(Node.class, "noeud6")) ;
		noeud1.tell(new Configuration(noeud2, noeud5), null) ;
		noeud2.tell(new Configuration(noeud3, noeud4), null) ;
		noeud5.tell(new Configuration(noeud6, null), null) ;
		try {
			Thread.sleep(500) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#####Propagation arbre depuis la racine######");
		noeud1.tell("question2", null) ;
		try {
			Thread.sleep(500) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#####Propagation depuis noeud 2#####");
		noeud2.tell("question3",null) ;
		try {
			Thread.sleep(500) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#####Reparti sur deux systeme#####");
		ActorSystem syst2 = ActorSystem.create("capitalisme");
		ActorRef noeud7 = syst2.actorOf(Props.create(Node.class, "noeud7")) ;
		ActorRef noeud8 = syst2.actorOf(Props.create(Node.class, "noeud8")) ;
		ActorRef noeud9 = syst2.actorOf(Props.create(Node.class, "noeud9")) ;
		noeud7.tell(new Configuration(noeud8, noeud9), null) ;
		noeud5.tell(new Configuration(noeud6, noeud7), null) ;
		noeud1.tell("REPARTI", null) ;
		
	}

}
