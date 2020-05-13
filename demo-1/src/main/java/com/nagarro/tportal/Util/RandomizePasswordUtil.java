package com.nagarro.tportal.Util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * randomized password generator
 * 
 * @author surbhiagarwal
 *
 */
public class RandomizePasswordUtil {
	public static String generatePassword() {

		int length = 8;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

		return (generatedString);
	}
}
