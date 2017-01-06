package core;

public abstract class Resources {
	public static String action;
	public static String[] res;
	
	
	
	public abstract class Constants{
		// Sizes of window
		public static final int DEFWIDTH = 800;
		public static final int  DEFHEIGHT = 480;
		
		public static final int  MINWIDTH = 600;
		public static final int  MINHEIGHT = 300;
		
		public static final int  MAXWIDTH = 1200;
		public static final int  MAXHEIGHT = 600;

		public static final int  STARTWIDTH = 300;
		public static final int  STARTHEIGHT = 200;
		public static final int  STARTVGAP = 20;
		public static final int  STARTHGAP = 40;
		
		public static final int  OPTWIDTHFOR2COLS = 300;
		public static final int  OPTHEIGHTFORROW = 60;
		
		// Menu atd
		public static final String TITLE = "Lodì";
		public static final String GAME = "Hra";
		public static final String START = "Nová hra";
		public static final String NICKNAMEADD = "<html><p align=\"center\">Pøezdívka:<br>[0-9 a-z A-Z . -]</p></html>";
		public static final String SERVERNAMEADD = "<html><p align=\"center\">IP serveru:<br>[xxx.xxx.xxx.xxx]</p></html>";
		public static final String PORTNUMBERADD = "<html><p align=\"center\">Port serveru:<br>[xxxx]</p></html>";
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
	}
	
	public abstract class Errors {
		public static final String NOT_SUPPORTED_YET = "Not supported yet";
		public static final String FORBIDDEN_CHARACTER = "Nepovolené znaky";
	}

	
	
	public static boolean inBetween(int value ,int min, int max){
		if(min <= value && value <= max){
			return true;
		}
		return false;
	}
}
