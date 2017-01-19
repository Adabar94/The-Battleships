#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#include <netdb.h>
#include <netinet/in.h>

#include <string.h>

#include "game.h"

int main( int argc, char *argv[] ) {
   	int serverSocket, clientSocket, socketPlayerOne, socketPlayerTwo; //Sockets
   	char buffer[256];
   	struct sockaddr_in serv_addr, cli_addr;
   	int gid, pid;
   	
   	int portNo = 6789;

   	serverSocket = socket(AF_INET, SOCK_STREAM, 0);

   	if (serverSocket < 0) {
		perror("Error in opening server socket! Terminating server");
    	exit(1);
   	}

   	/* Initialize socket structure */
   	bzero((char *) &serv_addr, sizeof(serv_addr));

   	serv_addr.sin_family = AF_INET;
   	serv_addr.sin_addr.s_addr = INADDR_ANY;
   	serv_addr.sin_port = htons(portNo);

   	if (bind(serverSocket, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) {
    	perror("Error while binding address. Terminating server");
		exit(1);
	}
   
   	printf("Server started.\n");
   	printf("Listening on PORT: %d \n", portNo);

   	listen(serverSocket,6);
   	clientSocket = sizeof(cli_addr);

	gid = 0;

   	while (1) {
    	socketPlayerOne = accept(serverSocket, (struct sockaddr *) &cli_addr, &clientSocket);

    	if (socketPlayerOne < 0) {
        	perror("Error while accepting connection. Terminating server");
        	exit(1);
      	}
        
      
      	socketPlayerTwo = accept(serverSocket, (struct sockaddr *) &cli_addr, &clientSocket);
      
      	if (socketPlayerTwo < 0) {
      		perror("Error while accepting connection. Terminating server");
    		exit(1);
	  	}

      	/* Create new game process */
      	pid = fork();

      	if (pid < 0) {
        	perror("Error while fork. Terminating server");
		exit(1);
      	}

      	if (pid == 0) {
        	close(serverSocket);
        	createGame(gid, socketPlayerOne, socketPlayerTwo);
      	} else {
      		gid++;
		}
   	}
}
