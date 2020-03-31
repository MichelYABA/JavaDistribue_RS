package up13.handler;




	import java.util.concurrent.*;
	import java.net.*;
	import java.io.*;
	public class Server {
		private final ServerSocket serverSocket;
		private final ExecutorService pool;
		
		public Server(int port, int poolSize)
				throws IOException {
				serverSocket = new ServerSocket(port);
				pool = Executors.newFixedThreadPool(poolSize);
				}
		
		
		
				public void run() {
				try {
				for (;;) {
					
				pool.execute(new Slave(serverSocket.accept()));
				}
				} catch (IOException ex) { pool.shutdown(); }
				}
				
				
				
				}





