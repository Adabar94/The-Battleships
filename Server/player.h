#ifndef _PLAYER_H
#define _PLAYER_H

struct player 
{
	int ID;
	char nick[12];
	int nick_length;
	struct sockaddr_in* addr; /*player address*/
	int grid[15][15];
};

#endif
