//Weilin Shu

package model;

import java.io.Serializable;

public class Option implements Serializable {
	private String optionName = " ";
	private float priceAdjustment = 0;

	public Option() {
	}
	
	public Option(String n) {
		optionName = n;
	}

	public Option(String n, float price) {
		optionName = n;
		priceAdjustment = price;
	}

	protected String getOptionname() {
		return optionName;
	}

	protected void setOptionname(String optionname) {
		optionName = optionname;
	}

	protected float getPriceAdjustment() {
		return priceAdjustment;
	}

	protected void setPriceAdjustment(float priceAdjustment) {
		this.priceAdjustment = priceAdjustment;
	}

	protected void PrintOptionName() {
		System.out.print(optionName);
	}

	protected void PrintPrice() {
		if (priceAdjustment != 0)
			System.out.print("(" + priceAdjustment + ")");
	}



}
