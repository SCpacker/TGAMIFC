

import it.tdlight.common.TelegramClient;
import it.tdlight.jni.TdApi;

public class ClientListener {

	private AuthorizationListener authorizationListener;
	
	private TelegramClient client;
	
	public ClientListener(TelegramClient client) {
		this.client = client;
	}
	
	public void onUpdate(TdApi.Object object) {
		
		switch (object.getConstructor()) {
			case TdApi.UpdateAuthorizationState.CONSTRUCTOR:
				if(authorizationListener == null)
					authorizationListener = new AuthorizationListener(client);
				
				authorizationListener.onAuthorizationState(((TdApi.UpdateAuthorizationState) object).authorizationState);
				break;
		}
	}
	
	public void onException(Throwable e) {
		e.printStackTrace();
	}
	
}
