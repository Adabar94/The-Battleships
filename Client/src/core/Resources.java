package core;

import connection.Reader;

import java.util.concurrent.Semaphore;

import connection.Writer;
import game.AllyGrid;
import game.EnemyGrid;

/**
 * Class resources is class which is shared with every thread and let them
 * communicate
 * 
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

	public abstract class Constants {
		public static final String TITLE = "Lodì";

		public static final String IMG_PATH = "src/images/";
		public static final String IMG_FORMATE = ".png";
		public static final String ICON_PATH = IMG_PATH + "icon.png";
		
		public static final String WATER_PATH = IMG_PATH + "water.png";
		public static final String WATER_SH_EN_PATH = IMG_PATH + "water_sh_en.png";
		public static final String FOG_PATH = IMG_PATH + "fog.png";
		public static final String HIT_PATH = IMG_PATH + "hit.png";

		public static final String SHIP_SUB_PATH = IMG_PATH + "submarine.png";
		public static final String SHIP_NOSE_PATH = IMG_PATH + "nose.png";
		public static final String SHIP_BODY_PATH = IMG_PATH + "body.png";
		public static final String SHIP_BODY_CHIMNEY_PATH = IMG_PATH + "body_chimney.png";
		public static final String SHIP_CHIMNEY_PATH = IMG_PATH + "chimney.png";
		public static final String SHIP_STERN_PATH = IMG_PATH + "stern.png";

		public static final String SHIP_HEPT_PATH = IMG_PATH + "set_ships_hepta.png";
		public static final String SHIP_QUAD_PATH = IMG_PATH + "set_ships_quad.png";
		public static final String SHIP_TRIO_PATH = IMG_PATH + "set_ships_triple.png";
		public static final String SHIP_DOUB_PATH = IMG_PATH + "set_ships_double.png";
		public static final String SHIP_SUBM_PATH = IMG_PATH + "set_ships_sub.png";
	}
}
