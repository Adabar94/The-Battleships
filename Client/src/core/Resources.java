package core;

import connection.Reader;

import java.util.concurrent.Semaphore;

import connection.Writer;
import game.AllyGrid;
import game.EnemyGrid;

/**
 * Class resources is class which is shared with every thread and let them communicate
 * @author Adam Barák
 *
 */
public abstract class Resources {
	public static Semaphore gameIsReady = new Semaphore(0);
	
	public static Reader reader;
	public static Writer writer;
	
	public static boolean isOurTurn = false;
	
	public static AllyGrid ally;
	public static EnemyGrid enemy;
	
	public abstract class Constants{
		// Sizes of window
		public static final int DEF_WIDTH = 800;
		public static final int  DEF_HEIGHT = 480;
		public static final int  JMENUBAR_HEIGHT = 25;

		public static final int  START_WIDTH = 300;
		public static final int  START_HEIGHT = 200;
		
		// Menu atd
		public static final String TITLE = "Lodì";
		public static final String NICKNAME = "Pøezdívka";
		public static final String NICKNAME_INVALID = "Pøezdívka není validní. Zadejte pøezdívku o velikosti mezi 1 a 12 znaky.";
		public static final String SERVER_IP = "IP serveru";
		public static final String SERVER_IP_INVALID = "IP serveru není validní. Zadejte IP ve tvaru x.x.x.x kde x je èíslo mezi 0 a 255.";
		public static final String PORT = "Port serveru:";
		public static final String PORT_INVALID = "Port serveru není validní. Zadejte port ve tvaru xxxx kde x je èíslo.";
		public static final String CONTINUE = "Pokraèovat";
		
		public static final String IMG_PATH = "images/";
		public static final String IMG_FORMATE = ".png";
		public static final String ICON_PATH = IMG_PATH+"icon.png";
		public static final String SET_SHIPS_BG_PATH = IMG_PATH+"set_ships_bg.png";
		public static final String PLAY_GAME_BG_PATH = IMG_PATH+"play_game_bg.png";
		public static final String WATER_PATH = IMG_PATH+"water.png";
		public static final String WATER_SH_EN_PATH = IMG_PATH+"water_sh_en.png";
		public static final String FOG_PATH = IMG_PATH+"fog.png";		
		public static final String HIT_PATH = IMG_PATH+"hit.png";
		public static final String SHIP_SUB_PATH = IMG_PATH+"submarine.png";
		public static final String SHIP_NOSE_PATH = IMG_PATH+"nose.png";
		public static final String SHIP_BODY_PATH = IMG_PATH+"body.png";
		public static final String SHIP_BODY_CHIMNEY_PATH = IMG_PATH+"body_chimney.png";
		public static final String SHIP_CHIMNEY_PATH = IMG_PATH+"chimney.png";
		public static final String SHIP_STERN_PATH = IMG_PATH+"stern.png";
		public static final String SHIP_HEPT_PATH = IMG_PATH+"set_ships_hepta.png";
		public static final String SHIP_QUAD_PATH = IMG_PATH+"set_ships_quad.png";
		public static final String SHIP_TRIO_PATH = IMG_PATH+"set_ships_triple.png";
		public static final String SHIP_DOUB_PATH = IMG_PATH+"set_ships_double.png";
		public static final String SHIP_SUBM_PATH = IMG_PATH+"set_ships_sub.png";
		public static final String SHIP_HEPT_GRID_PATH = IMG_PATH+"set_ships_hepta_grid.png";
		public static final String SHIP_QUAD_GRID_PATH = IMG_PATH+"set_ships_quad_grid.png";
		public static final String SHIP_TRIO_GRID_PATH = IMG_PATH+"set_ships_triple_grid.png";
		public static final String SHIP_DOUB_GRID_PATH = IMG_PATH+"set_ships_double_grid.png";
		public static final String SHIP_SUBM_GRID_PATH = IMG_PATH+"submarine.png";
	}
}
