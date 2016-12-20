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
	String TITLE = "Lod�";
		String GAME = "Hra";
			String START = "Nov� hra";
				String NICKNAMEADD = "<html><p align=\"center\">P�ezd�vka:<br>[0-9 a-z A-Z . -]</p></html>";
				String SERVERNAMEADD = "<html><p align=\"center\">IP serveru:<br>[xxx.xxx.xxx.xxx]</p></html>";
				String PORTNUMBERADD = "<html><p align=\"center\">Port serveru:<br>[xxxx]</p></html>";
				String CONTINUE = "Pokra�ovat";
			String EXIT = "Konec";
		String OPTIONS = "Nastaven�";
			String GRAPHIC = "Obraz";
				String RESOLUTION = "Rozli�en�";
			String SOUND = "Zvuk";
			String CONNECTION = "P�ipojen�";
		String HELP = "Pomoc";
			String HOWPLAY = "Jak hr�t";
			String RULES = "Pravidla";
			String ABOUT = "O h�e";
}
