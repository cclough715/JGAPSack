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
	
	/**
	 * Creates an Item with the specified values
	 * 
	 * @param w The weight of the new Item
	 * @param v The value of the new Item
	 * @throws Exception If the specified weight or value of the Item is 
	 * 	negative
	 */
	public Item(double w, double v) throws Exception{
		if(w < 0) throw new Exception("Item weight must be non-negative");
		weight = w; 
		
		if(v < 0) throw new Exception("Item value must be non-negative");
		value = v;
	}
	
	/**
	 * Creates an item by parsing a String
	 * 
	 * @param item A stringified version of an Item. Syntax is as follows
	 * 				"value:weight" 
	 * @throws Exception If item doesn't follow the proper syntax for a
	 * 		string representation of an Item or the value or weight was 
	 * 		negative 
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
	
	/**
	 * @return The weight of the Item
	 */
	public double getWeight(){ return weight; }
	
	/**
	 * @return The value of the Item
	 */
	public double getValue(){ return value; }
	
	/**
	 * Parses a string of Items. The String should follow this format
	 * "i0value:i0weight;i1value:i1weight;...;invalue:inweight"
	 * 
	 * @param s A list of items to parse
	 * @return An array of all the Items contained in the String
	 */
	public static Item[] parseItems(String s){
		try{
			String[] tokens = s.split(";");
			Item[] items = new Item[tokens.length];
			for(int i = 0; i < tokens.length; i++){
				items[i] = new Item(tokens[i]);
			}
			return items;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return The density of this item (value/weight) 
	 */
	public double getDensity(){
		if(weight > 0)
			return value / weight;
		else
			return Double.MAX_VALUE;
	}
	
	/**
	 * "value	weight	density"
	 */
	public String toString(){
		return value + "\t" + weight + "\t" + getDensity();
	}

}
