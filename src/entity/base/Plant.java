package entity.base;

public abstract class Plant {
	private int hp;
	private String url;
	private int x,y;
	private int price; 
	private String name;
	public Plant(int hp, String plantName) {
		this.hp = hp;
		switch(plantName) {
		case "Pea" :		url = "PEA0.gif";	price = 100 ; name ="Peashooter"; 	break;
		default : 			url = ".png"; 	;
		}
	}
	
	public String getUrl() {
		return url;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}