/**
 * @author Chad Clough
 * @since 6/21/2015
 *
 * Item is an object to be used in conjunction with the knapsack problem. All 
 * Items will have a non-negative weight and value associated with them. 
 */
public class Item {
	private double weight;
	private double value;
	
	@SuppressWarnings("unused")
	private Item(){
		//restrict default constructor
	}
	
	public Item(double w, double v) throws Exception{
		if(w < 0) throw new Exception("Item weight must be non-negative");
		weight = w; 
		
		if(v < 0) throw new Exception("Item value must be non-negative");
		value = v;
	}
	
	/**
	 * 
	 * @param item A stringified version of an Item. Syntax is as follows
	 * 				"value:weight" 
	 * @throws Exception If item doesn't follow the proper syntax for a
	 * 					string representation of an Item then
	 */
	public Item(String item) throws Exception{
		String[] fields = item.split(":");
		if(fields.length != 2) 
			throw new Exception("Syntax Error: Items must follow the format"
					+ " \"value:weight\"");
		value = Double.parseDouble(fields[0]);
		if(value < 0) throw new Exception("Item value must be non-negative");
		
		weight = Double.parseDouble(fields[1]);
		if(weight < 0) throw new Exception("Item weight must be non-negative");
	}
	
	public double getWeight(){ return weight; }
	public double getValue(){ return value; }
	
	
	public double getDensity(){
		if(weight > 0)
			return value / weight;
		else
			return Double.MAX_VALUE;
	}
	
	public String toString(){
		return "" + value + ":" + weight;
	}
	
}
