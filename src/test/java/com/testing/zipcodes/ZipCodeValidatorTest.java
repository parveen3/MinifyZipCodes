package com.testing.zipcodes;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ZipCodeValidatorTest extends TestCase {
	ZipCodeValidator zip = new ZipCodeValidator();

	List<ZipRange> input = null;

	public void testZipMinify1() throws Exception {
		input = new ArrayList<>();
		input.add(new ZipRange(50017, 50018));
		input.add(new ZipRange(51111, 51115));
		input.add(new ZipRange(51112, 51115));
		input.add(new ZipRange(51111, 51116));

		assertEquals("[50017->50018, 51111->51116]", zip.minifyZipRanges(input).toString());
	}

	public void testZipMinify2() throws Exception {
		input = new ArrayList<>();
		input.add(new ZipRange(50019, 50019));
		input.add(new ZipRange(51111, 51115));
		input.add(new ZipRange(41110, 45555));
		input.add(new ZipRange(41100, 41111));

		// System.out.println(zip.minifyZipRanges(input));
		assertEquals("[41100->45555, 50019->50019, 51111->51115]", zip.minifyZipRanges(input).toString());
	}

	public void testZipValidate1() {
		input = new ArrayList<>();
		try {
			zip.validateZipCodes(input);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testZipValidate2() {
		input = new ArrayList<>();
		input.add(new ZipRange(1, 50018));
		try {
			zip.validateZipCodes(input);
			assertTrue(false);
		} catch (Exception e) {
			// Should throw Exception
			assertEquals("Invalid Data: Zip code should be 5 digits!", e.getMessage());
		}
	}

	public void testZipValidate3() {
		input = new ArrayList<>();
		input.add(new ZipRange(55555, 33333));
		try {
			zip.validateZipCodes(input);
			assertTrue(false);
		} catch (Exception e) {
			// Should throw Exception
			assertEquals("Invalid Data: lower (55555) is greater than upper (33333)", e.getMessage());
		}
	}
}
