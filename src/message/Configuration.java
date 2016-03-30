package message;

import java.io.Serializable;

import akka.actor.ActorRef;

public class Configuration implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -447145307284278185L;
	protected ActorRef gauche ;
	protected ActorRef droit ;
	
	public Configuration(ActorRef gauche, ActorRef droit) {
		this.gauche = gauche ;
		this.droit = droit ;
	}
	
	public ActorRef getGauche() {
		return this.gauche ;
	}
	
	public ActorRef getDroit(){
		return this.droit ;
	}

}
