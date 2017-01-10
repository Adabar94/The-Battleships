package core;

import connection.Reader;
import connection.Connection;
import connection.Writer;
import game.AllyGrid;
import game.EnemyGrid;

public abstract class Resources {
	public static int gamePhase; // 0 - not connected yet . 1 - setting ships . 2 - waiting . 3 - game running - -1 end game
	
	public static boolean isError = false;
	public static boolean isOurTurn = false;
	
	public static Connection TCPClient;
	public static Reader reader;
	public static Writer writer;
	
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
		public static final String GAME = "Hra";
		public static final String START = "Nová hra";
		public static final String NICKNAME = "Pøezdívka";
		public static final String NICKNAME_INVALID = "Pøezdívka není validní. Zadejte pøezdívku o velikosti mezi 1 a 12 znaky.";
		public static final String SERVER_IP = "IP serveru";
		public static final String SERVER_IP_INVALID = "IP serveru není validní. Zadejte IP ve tvaru x.x.x.x kde x je èíslo mezi 0 a 255.";
		public static final String PORT = "Port serveru:";
		public static final String PORT_INVALID = "Port serveru není validní. Zadejte port ve tvaru xxxx kde x je èíslo.";
		public static final String CONTINUE = "Pokraèovat";
		public static final String EXIT = "Konec";
		public static final String OPTIONS = "Nastavení";
		public static final String GRAPHIC = "Obraz";
		public static final String RESOLUTION = "Rozlišení";
		public static final String SOUND = "Zvuk";
		public static final String CONNECTION = "Pøipojení";
		public static final String HELP = "Pomoc";
		public static final String HOWPLAY = "Jak hrát";
		public static final String RULES = "Pravidla";
		public static final String ABOUT = "O høe";
		
		public static final String IMG_PATH = "images/";
		public static final String ICON_PATH = IMG_PATH+"icon.png";
		public static final String SET_SHIPS_BG_PATH = IMG_PATH+"set_ships_bg.png";
		public static final String PLAY_GAME_BG_PATH = IMG_PATH+"play_game_bg.png";
		public static final String WATER_PATH = IMG_PATH+"water.png";
		public static final String WATER_SH_EN_PATH = IMG_PATH+"water_sh_en.png";
		public static final String FOG_PATH = IMG_PATH+"fog.png";		
		public static final String HIT_PATH = IMG_PATH+"hit.png";
		public static final String SHIP_SUB_PATH = IMG_PATH+"ship_sub.png";
		public static final String SHIP_DOUBLE0_PATH = IMG_PATH+"ship_double0.png";
		public static final String SHIP_DOUBLE1_PATH = IMG_PATH+"ship_double1.png";
		public static final String SHIP_TRIPLE0_PATH = IMG_PATH+"ship_triple0.png";
		public static final String SHIP_TRIPLE1_PATH = IMG_PATH+"ship_triple1.png";
		public static final String SHIP_TRIPLE2_PATH = IMG_PATH+"ship_triple2.png";
		public static final String SHIP_QUAD0_PATH = IMG_PATH+"ship_quad0.png";
		public static final String SHIP_QUAD1_PATH = IMG_PATH+"ship_quad1.png";
		public static final String SHIP_QUAD2_PATH = IMG_PATH+"ship_quad2.png";
		public static final String SHIP_QUAD3_PATH = IMG_PATH+"ship_quad3.png";
		public static final String SHIP_HEPT0_PATH = IMG_PATH+"ship_hept0.png";
		public static final String SHIP_HEPT1_PATH = IMG_PATH+"ship_hept1.png";
		public static final String SHIP_HEPT2_PATH = IMG_PATH+"ship_hept2.png";
		public static final String SHIP_HEPT3_PATH = IMG_PATH+"ship_hept3.png";
		public static final String SHIP_HEPT4_PATH = IMG_PATH+"ship_hept4.png";
		public static final String SHIP_HEPT5_PATH = IMG_PATH+"ship_hept5.png";
		public static final String SHIP_HEPT6_PATH = IMG_PATH+"ship_hept6.png";
		public static final String SHIP_HEPT7_PATH = IMG_PATH+"ship_hept7.png";
	}
	
	public abstract class Errors {
		public static final String NOT_SUPPORTED_YET = "Not supported yet";
		public static final String FORBIDDEN_CHARACTER = "Nepovolené znaky";
		
		public static final String CANNOT_CREATE_CONFIG = "Nepodaøilo se vytvoøit soubor s nastavením!";
		public static final String CANNOT_WRITE_CONFIG = "Nepodaøilo se uložit soubor s nastavením!";
		public static final String APP_ERROR = "Application error!";
		public static final String PORT_NOT_NUM = "Port is not in number formate!";
		
	}	
	
	public static boolean inBetween(int value ,int min, int max){
		if(min <= value && value <= max){
			return true;
		}
		return false;
	}
}
