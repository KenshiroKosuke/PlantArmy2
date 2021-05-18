package entity.base;

public abstract class Plant {
	private int hp;
	//private String url;
	private int x,y;
	private int price; 
	private String name;
	
	public Plant(int hp, String plantName) {
		this.hp = hp;
		switch(plantName) {
		case "PeaShooter" :		price = 100 ; name ="PeaShooter"; 		break;
		case "Sunflower" :		price = 50	; name ="Sunflower"; 		break;
		case "SnowPeaShooter" :	price = 175 ; name ="SnowPeaShooter"; 	break;
		case "Walnut" :			price = 50	; name ="Walnut";			break;
		case "CherryBomb":		price = 150 ; name ="CherryBomb";		break;
		default : 				name = "Logo"; 	;
		}
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getUrl() {
		if (this.name == "Walnut")
			return name+"_Full.gif";
		return name+".gif";
	}

}
