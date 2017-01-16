#include <stdio.h>
#include <stdlib.h>

#include <pthread.h>
#include <string.h>

#include "player.h"

#ifndef _GAME_H
#define _GAME_H

struct game 
{
	int ID;
	int turn; 
	struct player player[2];
};

void createGame(int gid, int socketPlayerOne, int socketPlayerTwo);

void decode(struct player *owner, char message[]);

#endif
