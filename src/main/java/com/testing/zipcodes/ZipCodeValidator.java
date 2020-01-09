package com.testing.zipcodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class ZipRange {
	int lower;
	int upper;

	ZipRange(int lower, int upper) {
		this.lower = lower;
		this.upper = upper;
	}

	@Override
	public String toString() {
		return lower + "->" + upper;
	}
}

public class ZipCodeValidator {

	/**
	 * validate the zip code data
	 * 
	 * @param input
	 * @throws Exception
	 */
	public void validateZipCodes(List<ZipRange> input) throws Exception {
		if (input != null) {
			for (ZipRange z : input) {
				if (z.upper < 9999 || z.upper > 99999 || z.lower < 9999 || z.lower > 99999)
					throw new Exception("Invalid Data: Zip code should be 5 digits!");
				if (z.upper < z.lower)
					throw new Exception(
							"Invalid Data: lower (" + z.lower + ") is greater than upper (" + z.upper + ")");
			}
		}
	}

	/**
	 * This method will return the zip ranges by minify the overlapped zip codes.
	 * will return validation exception for invalid zip codes (less or greter than 5
	 * digits)
	 * 
	 * @param input
	 * @return
	 */
	public List<ZipRange> minifyZipRanges(List<ZipRange> input) throws Exception {

		if (input == null)
			return null;

		validateZipCodes(input);
		LinkedList<ZipRange> output = new LinkedList<>();

		// sort the list
		Collections.sort(input, (z1, z2) -> {
			return z1.lower - z2.lower;
		});

		for (ZipRange range : input) {
			// if the list of output is empty OR current zip ranges does not overlap with
			// the previous, then append it.
			if (output.isEmpty() || output.getLast().upper < range.lower) {
				output.add(range);
			}
			// there is overlap, so merge current and previous zip ranges.
			else {
				output.getLast().upper = Math.max(output.getLast().upper, range.upper);
			}
		}

		return output;
	}

	/**
	 * Input Data
	 * 
	 * @return
	 */
	public static List<ZipRange> getZipCodes() {
		List<ZipRange> list = new ArrayList<>();

		list.add(new ZipRange(50017, 50018));
		list.add(new ZipRange(51111, 51115));
		list.add(new ZipRange(51112, 51115));
		list.add(new ZipRange(51111, 51116));
		return list;
	}

	//main method to run this program
	public static void main(String[] args) throws Exception {
		ZipCodeValidator zip = new ZipCodeValidator();
		List<ZipRange> data = zip.minifyZipRanges(getZipCodes());

		System.out.println(data);
	}

}
