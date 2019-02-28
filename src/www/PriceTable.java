/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www;

import java.util.*;
/*
This class stores a table where, given a brand and a level, you get a RepairPrice.

It looks a bit like this:
						|silver	|gold	|platinum
+-----------------------+-----------------------+
|"BigBikes"				|price	|price	|price	|
|"Superbikes"			|price	|price	|price	|
|"Mountain"				|price	|price	|price	|
|"Other Bike Brand(tm)"	|price	|price	|price	|
+-----------------------+-----------------------+

*/
public class PriceTable {
	private HashMap<String, PriceTrio> table;	//given a string, you get an array of prices with each index being a repair level
	PriceTable() {
		table = new HashMap<>();
	}
	
	//add a repair price to the table. Returns true on adding a new brand, false on updating an old one
	//the return value will be useful for handling imperfect user input ("you added a price to a new brand "bijeBrand", did you mean to add to existing "bikeBrand"?)
	public boolean addRepairPrice(String brand, RepairLevel level, int price, int days) {
		boolean didAddNewBrand = false;	//this keeps track of whether a new brand was added
		RepairPrice toAdd = new RepairPrice(brand, level, price, days);
		PriceTrio pricesOfBrand = table.get(brand);	//get the prices for given brand
		if (pricesOfBrand == null) {	//if the brand didn't exist in the table, make a new price list
			pricesOfBrand = new PriceTrio();
			didAddNewBrand = true;
		}
		pricesOfBrand.setRepairPrice(level, toAdd);
		table.put(brand, pricesOfBrand);	//put it in the table in case it wasnt there already
		return didAddNewBrand;
	}
	
	public RepairPrice getRepairPrice(String brand, RepairLevel level) {
		PriceTrio pricesOfBrand = table.get(brand);
		return pricesOfBrand == null ? null : pricesOfBrand.getRepairPrice(level);
	}
	public boolean containsBrand(String brand) {
		PriceTrio pricesOfBrand = table.get(brand);
		return pricesOfBrand != null;
	}
	public boolean containsBrandLevelPair(String brand, RepairLevel level) {
		return getRepairPrice(brand, level) != null;
	}
	public ArrayList<String> getAllBrands() {
		ArrayList<String> brands = new ArrayList<>();
		for (String brand : table.keySet()) {
			brands.add(brand);
		}
		return brands;
	}
	
	public String toStringSingleBrand(String brand) {
		int longestBrandLength = Math.max(7, brand.length());
		String brandNameHorizontalBar = "";
		String brandNameHorizontalBarWithTitle = "[Brands]";
		for (int i = 0; i <= longestBrandLength; ++i) {
			brandNameHorizontalBar += "-";
			if (i > 7) {
				brandNameHorizontalBarWithTitle += "-";
			}
		}
		String toRet = "┌" + brandNameHorizontalBarWithTitle + "┬[Silver]-------------┬[Gold]---------------┬[Platinum]-----------┐\n";
		String sbrand = brand;
		while (sbrand.length() < longestBrandLength) {
			sbrand += " ";
		}
		toRet += "│" + sbrand + " │" + table.get(brand).toString() + "\n";
		toRet += "└" + brandNameHorizontalBar + "┴---------------------┴---------------------┴---------------------┘\n";
		return toRet;
	}
	public String toString() {	//print the table
		int longestBrandLength = 7;	//first, loop through brands to find the one w/ longest name
		for (String brand : table.keySet()) {
			if (brand.length() > longestBrandLength) {
				longestBrandLength = brand.length();
			}
		}
		//construct the string itself
		String toRet;
		String brandNameHorizontalBar = "";
		String brandNameHorizontalBarWithTitle = "[Brands]";
		for (int i = 0; i <= longestBrandLength; ++i) {
			brandNameHorizontalBar += "-";
			if (i > 7) {
				brandNameHorizontalBarWithTitle += "-";
			}
		}
		toRet = "┌" + brandNameHorizontalBarWithTitle + "┬[Silver]-------------┬[Gold]---------------┬[Platinum]-----------┐\n";
		for (Map.Entry<String, PriceTrio> entry : table.entrySet()) {
			PriceTrio value = entry.getValue();
			String brand = entry.getKey();
			//pad the brands so they are all as big as the biggest one, to ensure an aligned grid
			while (brand.length() < longestBrandLength) {
				brand += " ";
			}
			//add the new row
			toRet = toRet + "│" + brand + " │" + value.toString() + "\n";
		}
		toRet = toRet + "└" + brandNameHorizontalBar + "┴---------------------┴---------------------┴---------------------┘\n";
		return toRet;
	}
	public String toCommand() {
		//this method returns a multiline string of RepairPrice commands derived from its PriceTrios
		String command = "";
		//loop through the table of brands
		for (Map.Entry<String, PriceTrio> entry : table.entrySet()) {
			PriceTrio value = entry.getValue();
			//append the commands needed to make the prices for given brand
			command += value.toCommand();
		}
		//return commands
		return command;
	}
}
