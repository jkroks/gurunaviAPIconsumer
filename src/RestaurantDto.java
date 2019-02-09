

public class RestaurantDto {
	
	public String id;
	public String name;
	public String area;
	public String prefactureId;
	public String prefacture;
	public String gurunaviURL;
	public String imageURL;
	public int budget;
	public double lattitude;
	public double longitude;
	public String resttype;
	
	public String getPrefacture() {
		return prefacture;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getResttype() {
		return resttype;
	}
	public void setResttype(String resttype) {
		this.resttype = resttype;
	}
	public void setPrefacture(String prefacture) {
		this.prefacture = prefacture;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public String getPrefactureId() {
		return prefactureId;
	}
	public void setPrefactureId(String prefactureId) {
		this.prefactureId = prefactureId;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getGurunaviURL() {
		return gurunaviURL;
	}
	public void setGurunaviURL(String gurunaviURL) {
		this.gurunaviURL = gurunaviURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
}
