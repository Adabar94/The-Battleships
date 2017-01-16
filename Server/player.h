#include <stdio.h>
#include <stdlib.h>

#include <pthread.h>
#include <string.h>

#ifndef _PLAYER_H
#define _PLAYER_H

struct player 
{
	int id;
	char nick[12];
	int nick_length;
	int socket; /*player address*/
	int grid[15][15];
	pthread_t reader;
	struct game* parentGame;
};

void *readerInit(void* arg);

void buildShip(struct player *owner, int shipId, int x, int y);

#endif
