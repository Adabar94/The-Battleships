package constants;

public interface Constants {
	// Sizes of window
	int DEFWIDTH = 800;
	int DEFHEIGHT = 480;
	
	int MINWIDTH = 600;
	int MINHEIGHT = 300;
	
	int MAXWIDTH = 1200;
	int MAXHEIGHT = 600;

	int STARTWIDTH = 300;
	int STARTHEIGHT = 200;
	int STARTVGAP = 20;
	int STARTHGAP = 40;
	
	int OPTWIDTHFOR2COLS = 300;
	int OPTHEIGHTFORROW = 60;
	
	// Menu atd
	String TITLE = "Lodì";
		String GAME = "Hra";
			String START = "Nová hra";
				String NICKNAMEADD = "<html><p align=\"center\">Pøezdívka:<br>[0-9 a-z A-Z . -]</p></html>";
				String SERVERNAMEADD = "<html><p align=\"center\">IP serveru:<br>[xxx.xxx.xxx.xxx]</p></html>";
				String PORTNUMBERADD = "<html><p align=\"center\">Port serveru:<br>[xxxx]</p></html>";
				String CONTINUE = "Pokraèovat";
			String EXIT = "Konec";
		String OPTIONS = "Nastavení";
			String GRAPHIC = "Obraz";
				String RESOLUTION = "Rozlišení";
			String SOUND = "Zvuk";
			String CONNECTION = "Pøipojení";
		String HELP = "Pomoc";
			String HOWPLAY = "Jak hrát";
			String RULES = "Pravidla";
			String ABOUT = "O høe";
}
