import java.time.LocalDate;

public class CarPriceEstimator {
	
	public double getSalePrice(String makeAndModel, int yearManufactured, double milesDriven, 
			int airBagsCount, boolean hasAbs, boolean hasEbd,
			boolean hasRearViewCamera, boolean hasSunRoof, boolean hasAutoAC,
			boolean hasAccidentHistory) {
		
		double salePrice = getPrice(makeAndModel, yearManufactured);
		int currentYear = LocalDate.now().getYear();		
		int ageOfCar = currentYear - yearManufactured + 1;
		System.out.println("ageOfCar: " +  ageOfCar);
		
		if(ageOfCar <= 10){
			double depreciationPrice =  salePrice *0.05 * ageOfCar;
			salePrice -= depreciationPrice; 
		}
		else {
			return salePrice * 0.1;
		}
		System.out.println("salePrice after depreciation: " + salePrice);
	
		
		if(!((airBagsCount >= 2) && (hasAbs == true) && (hasEbd == true))){
			salePrice -= 1000.0; 
		}
		System.out.println("salePrice after accounting for security features: " + salePrice);
		
		if(hasRearViewCamera == true && ((hasSunRoof == true) || (hasAutoAC == true))){
			salePrice += 500.0;
		}
		
		System.out.println("salePrice after accounting for comfort features: " + salePrice);
		
		if(hasAccidentHistory == true) {
			salePrice -= 500.0;
		}
		
		System.out.println("salePrice after accounting for past accidents: " + salePrice);
	
		double expectedMilesDriven = ageOfCar * 10000.0;
		double additionalMiles = milesDriven - expectedMilesDriven;
		
		if((additionalMiles > 1000) && (additionalMiles <= 10000)){
			salePrice -= 500.0;
		}
		
		if((additionalMiles > 10000) && (additionalMiles <= 30000)){
			salePrice -= 1000.0;
		}
		
		if((additionalMiles > 30000)){
			salePrice -= 1500.0;
		}
		
		System.out.println("salePrice after accounting for miles driven: " + salePrice);
		
		return salePrice;
	}
	
	private double getPrice(String makeAndModel, int yearManufactured) {
		if (makeAndModel.equalsIgnoreCase("ford ecosport")) {
			return 20000.0;
		} else if (makeAndModel.equalsIgnoreCase("honda city")) {
			return 25000.0;
		} else if (makeAndModel.equalsIgnoreCase("toyota camry hybrid")) {
			return 30000.0;
		}
		return 20000.0;
	}

	public static void main(String[] args) {
		CarPriceEstimator carPriceEstimator = new CarPriceEstimator();
		double salesPrice = carPriceEstimator.getSalePrice("ford ecosport", 2021, 5000.0, 2, true, true, true, false, false, false);		
	}
}
