#ifndef _GAME_H
#define _GAME_H

struct game 
{
	int ID;
	int state; /* game state 0 waiting for second player / 1 waiting for ship preparation / 2 playing*/
	int turn; 
	int p_ID[2];
};

#endif
