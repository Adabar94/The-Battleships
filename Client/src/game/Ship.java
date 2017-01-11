package game;

public class Ship {
	int id;
	int count;
	String setShipPath;

	public Ship(int id, int count, String setShipPath) {
		this.id = id;
		this.count = count;
		this.setShipPath = setShipPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSetShipPath() {
		return setShipPath;
	}
}
