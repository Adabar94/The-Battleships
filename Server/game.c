#include "game.h"

void decode(struct player *owner, char message[]) {
	int point, i, j, k, l, m, x, y;
	char buffer[20];
	bzero(buffer, 20);
	
	if(message[0] == 'L'){
		point = 1;
		
		// Nickname
		owner->nick_length = atoi(&message[point]);
		point = 3;
		for(i = 0; i < owner->nick_length; i++){
			owner->nick[i] = message[i+point];
		}
		printf("Player %.*s joined game %d \n", owner->nick_length, owner->nick, owner->parentGame->ID);
		printf("Login message: %s \n", message);
		point += owner->nick_length;
		
		// Grid
		memset(owner->grid, 0, sizeof(owner->grid[0][0]) * 15 * 15);
		for(i = 1; i < 15; i++){
			x = (int) (message[point++]) - 65;
			y = (int) (message[point++]) - 65;
			if (x < 0 || x >14 ||y < 0 || x > 14){
				printf("ERROROUS COORDINATES!");
				decode(owner, "E");
			}
			buildShip(owner, i, x, y);
		}
		/* Vypis grid
		for(i = 0; i < 15; i++){
			for(j = 0; j < 15; j++){
				printf(" %02d ", owner->grid[j][i]);
			}
			printf("\n");
		} */
		
		if(owner->parentGame->player[0].nick_length != 0 && owner->parentGame->player[1].nick_length != 0){
			printf("Game %d is starting.\n", owner->parentGame->ID);
			for(i = 0, k = 1; i < 2; i++, k--){
				buffer[0] = 'R';
				buffer[1] = owner->parentGame->player[i].id == 0 ? '0' : '1';
				for(j = 0; j < owner->parentGame->player[k].nick_length; j++){
					buffer[2+j] = owner->parentGame->player[k].nick[j];
				}
				buffer[3+owner->parentGame->player[k].nick_length] = '\n';
				if(write(owner->parentGame->player[i].socket, buffer, owner->parentGame->player[k].nick_length+4) < 0){
					perror("ERROR writing to socket");
				}
				bzero(buffer, 20);
			}

		}
		return;	
	}
	if(message[0] == 'A'){
		x = ((int) (message[1])-65);
		y = ((int) (message[2])-65);
		
		if(owner->id == 0){
			i = 1;
		} else {
			i = 0;
		}
		
		if(owner->parentGame->turn != owner->id){
			return;
		}
		
		printf("Game %d: player %d: %s  is shooting on position %d, %d\n", owner->parentGame->ID, owner->id, owner->nick, x, y);
		
		buffer[1] = message[1];
		buffer[2] = message[2];
		
		j = owner->parentGame->player[i].grid[x][y];
		
		if(j == 0){
			buffer[3] = '0';
		} else {
			owner->parentGame->player[i].grid[x][y] = 0;
			m = 0;
			for(k = 0; k < 15; k++){
				for(l = 0; l < 15; l++){
					if(owner->parentGame->player[i].grid[k][l] > 0){
						m = 1;
					}
					if(owner->parentGame->player[i].grid[k][l] == j){
						buffer[3] = '1';
					}
				}
			}
			if(m == 1 && buffer[3] != '1'){
				buffer[3] = '2';
			}
			if(m == 0){
				buffer[3] = '3';
			}
		}
		
		buffer[4] = '\n';
		
		buffer[0] = 'A';
		if(write(owner->parentGame->player[owner->id].socket, buffer, 5) < 0){
		
		}
		
		buffer[0] = 'D';
		if(write(owner->parentGame->player[i].socket, buffer, 5) < 0){
		perror("ERROR writing to socket");
		}

		owner->parentGame->turn = i;
		return;
	}
	if(message[0] == 'E') {
		write(owner->parentGame->player[0].socket, "E\n", 3);
		close(owner->parentGame->player[0].socket);
		write(owner->parentGame->player[1].socket, "E\n", 3);
		close(owner->parentGame->player[1].socket);
		pthread_cancel(owner->parentGame->player[1 - owner->id].reader);
		pthread_exit(NULL);
		return;
	}
	
}

/**
Creates new game
*/
void createGame(int gid, int socketPlayerOne, int socketPlayerTwo) {
   	char buffer[256];
   	bzero(buffer, 256);
   	struct game actualGame;

	printf("Creating game with id %d\n", gid);
	actualGame.ID = gid;
	actualGame.turn = 0;
	actualGame.player[0].id = 0;
	actualGame.player[0].parentGame = &actualGame;
	actualGame.player[0].socket = socketPlayerOne;
	actualGame.player[0].nick_length = 0;
	bzero(actualGame.player[0].nick, sizeof(actualGame.player[0].nick));
	if(pthread_create(&actualGame.player[0].reader, NULL, &readerInit, &actualGame.player[0])) {
		perror("Error in starting reader thread!\n");
		exit(1);
	}
	
	actualGame.player[1].id = 1;
	actualGame.player[1].parentGame = &actualGame;
	actualGame.player[1].socket = socketPlayerTwo;
	actualGame.player[1].nick_length = 0;
	bzero(actualGame.player[1].nick, sizeof(actualGame.player[1].nick));
	if(pthread_create(&actualGame.player[1].reader, NULL, &readerInit, &actualGame.player[1])) {
		perror("Error in starting reader thread!\n");
		pthread_cancel(actualGame.player[0].reader);
		exit(1);
	}
	
	printf("Ending game with id %d\n", gid);
	pthread_join(actualGame.player[1].reader, NULL);
	pthread_join(actualGame.player[2].reader, NULL);
	exit(0);
}

