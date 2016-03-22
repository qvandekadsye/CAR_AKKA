package carAkka.actor;

import akka.actor.UntypedActor;

public class Leaf extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		System.out.println("jai reçu");
		
	}

}
