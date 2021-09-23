import java.io.IOError;
import java.io.IOException;

import it.tdlight.common.Init;
import it.tdlight.common.TelegramClient;
import it.tdlight.common.utils.CantLoadLibrary;
import it.tdlight.jni.TdApi;
import it.tdlight.tdlight.ClientManager;

public class Main {
	
private static TelegramClient client;
	
	public static void main(String[] args) throws CantLoadLibrary {
		Init.start();
		client = ClientManager.create();
		
		ClientListener clientListener = new ClientListener(client);
		client.initialize(clientListener::onUpdate, clientListener::onException , clientListener::onException);


		client.execute(new TdApi.SetLogVerbosityLevel(0));
	
		if (client.execute(new TdApi.SetLogStream(new TdApi.LogStreamFile("tdlib.log", 1 << 27, false))) instanceof TdApi.Error) {
			throw new IOError(new IOException("Write access to the current directory is required"));
		}

	}

}
