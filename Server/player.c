#include "player.h"

void *readerInit(void* arg) {
	struct player *owner = (struct player *) arg;
	char buffer[256];
   	bzero(buffer, 256);
   	
   	printf("Thread started player: %d\n", owner->socket);
   	
   	while(1){
   		if(read(owner->socket,buffer,255) < 0){
   			perror("ERROR reading from socket");
		}
		decode(owner, buffer);
	   }	
	return NULL;
}

void buildShip(struct player *owner, int shipId, int y, int x){
	if(shipId == 1){
		if(x < 2 || y < 1 || x > 13 || y > 15){
			printf("Bad coordinates %d, %d! Invalid ship with id %d!", x, y, shipId);
			pthread_exit(NULL);
		} else {
			owner->grid[x-2][y]=shipId;
			owner->grid[x-1][y-1]=shipId;
			owner->grid[x-1][y]=shipId;
			owner->grid[x][y]=shipId;
			owner->grid[x+1][y]=shipId;
			owner->grid[x+1][y-1]=shipId;
			owner->grid[x+2][y]=shipId;
		}
		return;
	}
	if(shipId < 4){
		if(x < 1 || y < 0 || x > 13 || y > 15){
			printf("Bad coordinates %d, %d! Invalid ship with id %d!", x, y, shipId);
			pthread_exit(NULL);
		} else {
		owner->grid[x-1][y]=shipId;
		owner->grid[x][y]=shipId;
		owner->grid[x+1][y]=shipId;
		owner->grid[x+2][y]=shipId;
		}
		return;
	}
	if(shipId < 7){
		if(x < 1 || y < 0 || x > 14 || y > 15){
			printf("Bad coordinates %d, %d! Invalid ship with id %d!", x, y, shipId);
			pthread_exit(NULL);
		} else {
		owner->grid[x-1][y]=shipId;
		owner->grid[x][y]=shipId;
		owner->grid[x+1][y]=shipId;
		}
		return;
	}
	if(shipId < 11){
		if(x < 0 || y < 0 || x > 14 || y > 15){
			printf("Bad coordinates %d, %d! Invalid ship with id %d!", x, y, shipId);
			pthread_exit(NULL);
		} else {
		owner->grid[x][y]=shipId;
		owner->grid[x+1][y]=shipId;
		}
		return;
	}
	if(x < 0 || y < 0 || x > 15 || y > 15){
			printf("Bad coordinates %d, %d! Invalid ship with id %d!", x, y, shipId);
			pthread_exit(NULL);
	} else {
		owner->grid[x][y]=shipId;
	}
}
