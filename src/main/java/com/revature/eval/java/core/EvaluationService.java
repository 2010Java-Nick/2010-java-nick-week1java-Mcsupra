package com.revature.eval.java.core;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		String[] words = phrase.replaceAll("[^a-zA-Z]+\\W", " ").replaceAll("[?_-]"," ").split(" ");
		String acro = "";
		
		for (int i = 0; i < words.length; i++) {
			acro += words[i].toCharArray()[0];
			acro = acro.toUpperCase();
		}
		
		return acro;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;
		
		int comp12;
		int comp13;
		int comp23;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			// the first line of every constructor is super() or this(), it calls super() implicitly unless we call 
			//this() explicitly
			this(); //refering to the constructor on line 56
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
			this.comp12 = Double.compare(sideOne, sideTwo);
			this.comp13 = Double.compare(sideOne, sideThree);
			this.comp23 = Double.compare(sideTwo, sideThree);
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}
		
		public boolean isEquilateral() {
			
			return (comp12 == comp23);
		}

		public boolean isIsosceles() {
			
			return (comp12 == 0 || comp23 == 0 || comp13 == 0);
		}

		public boolean isScalene() {
			
			return (comp12 != 0 && comp23 != 0 && comp13 != 0);
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		string = string.toUpperCase();
		char[] scrabble = string.toCharArray();
		int score = 0;
		
		for (int i = 0; i < string.length(); i++) {
			switch (scrabble[i]) {
			case 'A': case 'E': case 'I': case 'O': case 'U': case 'L': case 'N': case 'R': case 'S': case 'T':
				score += 1;
				break;
			case 'D': case 'G':
				score += 2;
				break;
			case 'B': case 'C': case 'M': case 'P':
				score += 3;
				break;
			case 'F': case 'H': case 'V': case 'W': case 'Y':
				score += 4;
				break;
			case 'K':
				score += 5;
				break;
			case 'J': case 'X':
				score += 8;
				break;
			case 'Q': case 'Z':
				score += 10;
				break;
			}
		}
		
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		int count = 0;
		String newNum = "";
		
		String temp = string.replaceAll("[\\s]", "").replaceAll("-", "").replaceAll("[0-9]","").replaceAll("\\.", "")
							.replaceAll("\\)", "").replaceAll("\\(", "");
		string = string.replaceAll("[^0-9]", "");
		char[] phone = string.toCharArray();
		
		if (phone.length > 10) {
			throw new IllegalArgumentException("invalidWhenMoreThan11Digits");
		}
		if (temp.length() > 0) {
			throw new IllegalArgumentException("Invalid Char");			
		}
		
		for (int i = 0; i < phone.length; i++) {
			
				if (count == 0 && phone[i] != '1') {
					newNum += phone[i];
					count++;
				}
				else if (count == 0 && phone[i] == '1')
					count++;
				else {
					newNum += phone[i];
					count++;
				}
		}
		
		return newNum;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		string = string.replaceAll("\n"," ").replaceAll("[^a-zA-Z]"," ");
		String[] words = string.split(" ");
		Map<String, Integer> hash = new HashMap<String, Integer> ();
		
		
		for (int i = 0; i < words.length; i++) {
			
			Integer count = hash.get(words[i]);
			
			
			if (count == null) {
				hash.put(words[i], 1);
			}
			else
				hash.put(words[i], count+1);
			}
			
		hash.remove("");
		
		return hash;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			
			List<T> list = getSortedList();
			int left = 0;
			int right = list.size();
			int middle;
			
			do {
				middle = (left + right)/2;
				
				if (left > right)
					break;
				else if (list.get(middle).compareTo(t) < 0) {
					left = middle + 1;
				}
				else {
					right = middle - 1;
				}
				
			}while (list.get(middle).compareTo(t)!=0);
			
			return middle;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		
		String[]words = string.split(" ");
		String temp = "";
		System.out.println(words.length);
		if(words.length>1) {
			string = "";	
		}
		
		for (int m=0;m<words.length;m++) {
			temp = "";
			char[] pig = words[m].toCharArray();
			
			switch (pig[0]) {
			case 'a': case 'e': case 'i': case 'o': case 'u':
				
				words[m] += "ay";
				break;
			case 'q':
				for(int x=2;x<pig.length;x++) {
					temp += pig[x];
				}
				
				temp += pig[0];
				temp += pig[1];
				
				words[m] = temp + "ay";
				break;
				
			case 's':
				int count = 0;
				int i=0;
				while(pig[i]!='e'&&pig[i]!='i'&&pig[i]!='o'&&pig[i]!='u') {
					count++;
					i++;
				}
			
				for (int j = count; j < pig.length; j++) {
					temp += pig[j];
				}
				
				words[m] = temp;
			
				for (int j = 0; j < count; j++) {
					words[m] += pig[j];
				}
				words[m] += "ay";
				break;
			
			case 't':
				if (pig[1] == 'h') {
				for (int k=2;k<pig.length;k++) {
						temp += pig[k];
				}
				temp += pig[0];
				temp += pig[1];
				words[m] = temp + "ay"; 
				}
				else {
					for (int l = 1; l < pig.length; l++) {
						temp += pig[l];
					}
					temp += pig[0];
					words[m] = temp + "ay";
				}
					
				break;
			
			default:
				for (int l = 1; l < pig.length; l++) {
					temp += pig[l];
				}
				System.out.println("here");
				temp += pig[0];
				words[m] = temp + "ay";
				break;
			}		
		}
		if(words.length>1) {
			string = "";
			for (int z = 0; z<words.length;z++) {
				if (z != words.length-1) {
					string += words[z] + " ";
				}
				else {
					string += words[z];
				}
			
			}
		}
		else {
			string = words[0];
		}
		
		return string;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		
		int temp = input;
		int temp2 = input;
		int total = 0;
		int count = 0;
		
		while (temp2 != 0) {
			while(temp != 0) {
				temp = temp/10;
				count++;
			}
			
			total += Math.pow(temp2%10,count);
			temp2 = temp2/10;
		}
		return (input == total);
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> list = new ArrayList<Long>();
		
		long prime = 2;
		int count = 0;
		while (l != 1) {
			if (l%prime == 0) {
				list.add(prime);
				l = l/prime;
			}
			else {
				count++;
				prime = getNextPrime(count);
			}
		}
		
		return list;
	}
	
	
	public static int getNextPrime(int i) { 
		int prime = 2;
		int count = 0;
		int ticker = 0; 
		do {
			if (prime == 2) {
				prime = 3;
				count++;
			}
			else {
				prime += 2;
				
				for (int j=2;j<prime-1;j++) {
					if (prime%j == 0) {
						ticker++;
					}			
				}
				if (ticker == 0) {
					count++;
				}
				else {
					ticker=0;
				}
			
			}
			
		}while (count != i);
		
		return prime;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
		 	
		    if (key >= 26)
		    {
		        key = key % 26; //Handle "large" numbers
		    }

		    String temp = "";
		    
		    char[] arr = string.toCharArray();
		    
		    for (int i = 0; i < arr.length; i++)
		    {
		        
		        if (arr[i] >= 65 && arr[i] <= 90) //Uppercase handling only
		        {
		            arr[i] = (char) (arr[i] + key);
		            
		            if (arr[i] > 90) //Check for text wrapping to keep case and in alpha
		            {
		                arr[i] = (char) (arr[i] - 26);
		            }
		        }
		        
		        else if (arr[i] >= 97 && arr[i] <= 122) // Check lower
		        {
		            arr[i] = (char) (arr[i]-32+key);
		            
		            if (arr[i] > 90)
		            {
		                arr[i] = (char) (arr[i] - 26);
		            }
		            
		            arr[i] = (char) (arr[i] + 32);
		        }
		        
		        temp += arr[i];
		        
		    }
			return temp;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		
		int prime = 2;
		int count = 1;
		int ticker = 0; 
		
		if (i == 0) {
			throw new IllegalArgumentException("Undefined Prime");
		}
		
		if (i>1) {
			do {
				if (prime == 2) {
					prime = 3;
					count++;
				}
				else {
					prime += 2;
				
					for (int j=2;j<prime-1;j++) {
						if (prime%j == 0) {
							ticker++;
						}			
					}
					if (ticker == 0) {
						count++;
					}
					else {
						ticker=0;
					}
				
				}
				
			}while (count != i);
		}
		
		return prime;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			
			string = string.replaceAll("[^a-zA-Z0-9]", "");
			String temp = "";
			char[]arr = string.toCharArray();
			char[]key = new char[] {'Z','Y','X','W', 'V',
							  		 'U','T','S','R','Q','P',
							  		 'O','N','M','L','K','J',
							  		 'I','H','G','F','E','D',
							  		 'C','B','A'};
			
			
			for (int i = 0; i < arr.length; i++)
		    {
		        if (arr[i] >= 65 && arr[i] <= 90) //Uppercase handling only
		        {
		            arr[i] = (char)(key[arr[i]-65]+32);
		           
		        }
		        
		        else if (arr[i] >= 97 && arr[i] <= 122) // Check lower
		        {
		            arr[i] = (char) (key[arr[i]-97] + 32);
		            
		        }
		        
		        temp += arr[i];
		        
		    }
			
			
		
		char[]arr2 = temp.toCharArray();
			String temp2="";
			int count=1;
			
			for (int i=0; i<arr2.length;i++) {
				if(count == 5 && i != arr2.length-1) {
					temp2 += arr2[i]+" ";
					count = 1;
				}
				else {
					temp2 += arr2[i];
					count++;
				}
			}
			
			return temp2;
		}
		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			string = string.replaceAll("[^a-zA-Z0-9]", "");
			String temp = "";
			char[]arr = string.toCharArray();
			char[]key = new char[] {'Z','Y','X','W', 'V',
							  		 'U','T','S','R','Q','P',
							  		 'O','N','M','L','K','J',
							  		 'I','H','G','F','E','D',
							  		 'C','B','A'};
			
			
			for (int i = 0; i < arr.length; i++)
		    {
		        if (arr[i] >= 65 && arr[i] <= 90) //Uppercase handling only
		        {
		            arr[i] = (char)(key[arr[i]-65]+32);
		           
		        }
		        
		        else if (arr[i] >= 97 && arr[i] <= 122) // Check lower
		        {
		            arr[i] = (char) (key[arr[i]-97] + 32);
		            
		        }
		        
		        temp += arr[i];
		        
		    }
			
			return temp;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		
		string = string.replaceAll("-","").replaceAll("[^0-9X]","");
		char[] arr = string.toCharArray();
		int[]val = new int [string.length()];
		if(string.length() < 10) {
			return false;
		}
		else
		{
			for(int i=0;i<arr.length;i++) {
				if (arr[i] == 'X') {
					val[i] = 10;
				}
				else {
					val[i] = arr[i]-48;
				}
			}
		}
		
		int total=0;
		for(int i=val.length-1;i>=0;i--) {
			total += (i+1)*val[i];
		}
			
		total = total%11;
		
		return (total == 0);
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		
		char[]words = string.replaceAll("[^a-zA-Z]","").toCharArray();
		int sum = 0;
		
		HashSet<Character> key = new HashSet<>();
		
		for(int i = 0;i<words.length;i++) {
			if (key.add(words[i])) {
				sum++;
			}
		}
		
		return (sum == 26);
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		
		String time = given.toString();
		if (time.length() < 11) {
			time += "T00:00:00";
		}
		
		LocalDateTime currTime = LocalDateTime.parse(time);
		
		return currTime.plusSeconds(1000000000L);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		HashSet<Integer> key = new HashSet<>();
		int sum = 0;
		
		for (int j=0;j<set.length;j++) {
			
			for(int k=1; k*set[j]<i;k++) {
					if(key.add(k*set[j])) {
						sum += k*set[j];
					}
			}			
		}
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		String temp = string;
		String test = string;
		test = test.replaceAll("[0-9]","").replaceAll("\\s", "");
		string = string.replaceAll("[^0-9]","");
		if (test.length() != 0) {
			return false;
		}
			
		int count = string.length();
		long by2 = 0;
		long check = 0;
		long notby2 = 0;
		long temp_num = Long.parseLong(string);
		long number = temp_num;
		    
		if (count % 2 == 0) //Checking if count is even or odd
		{
		       for (int i = 0; i < count / 2; i++) //Since we'll be moving through the number 2 digits at a time, 
		           //starting with 2nd from last, we only need 1/2 count
		       {
		           check = ((number % 100 / 10) * 2); // We need to check if twice the digit is 2 digits long
		           
		           if (check >= 10) //If it is, do this.
		           {
		               for (int j = 0; j < 2; j++)
		               {
		                   by2 = by2 + (check % 10); //We need to add the DIGITS of the 2 digit number,
		                   //not the number itself. 2 * 6 is 12 is 1 + 2
		                   check = check / 10;       //Get both digits individually
		               }
		           }
		           else
		           {
		               by2 = by2 + check;  //If the number is less than 10, store it in this variable "by2"
		           }
		           number = number / 100;  //Move to the next pair of digits
		        }
		        
		    }
		    
		    else
		    {
		        count = count - 1; //If count is odd, make it even
		        for (int i = 0; i < count / 2; i++)
		        {
		            check = ((number % 100 / 10) * 2);  //Same code as above from here down
		            
		            if (check >= 10)
		            {
		                for (int j = 0; j < 2; j++)
		                {
		                    by2 = by2 + (check % 10);
		                    check = check / 10;
		                }
		            }
		            else
		            {
		                by2 = by2 + check;
		            }
		            number = number / 100;
		            
		        }
		        
		    }
		    
		    
		    for (int k = 0; k <= count / 2; k++) 
		    {
		        notby2 = notby2 + temp_num % 10; //Now we pull out the digits we didn't use and and add them together
		        temp_num = temp_num / 100;
		    }
		    
		    long validate = by2 + notby2;  //Last piece of Luhn's algorithm 
		    
		    
		    return (validate % 10 == 0); //If the ending digit is zero, return 1 (pass/true)
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		int answer = 0;
		String[] words = string.split(" ");
		int x;
		int y;
		int len;
		
		if (words.length == 6) {
			char[] temp = words[5].toCharArray();
			len = words[5].length();
			words[5] = "";
			for (int i = 0; i < len - 1; i++) {
				words[5] += temp[i];
			}
		}
		
		if (words.length == 5) {
			char[] temp2 = words[4].toCharArray();
			len = words[4].length();
			words[4] = "";
			for (int i = 0; i < len - 1; i++) {
				words[4] += temp2[i];
			}
		}
		
		switch (words[3]) {
		case "plus":
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[4]);
			answer += x + y;
			break;
		case "minus":
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[4]);
			answer += x - y;
			break;
		case "multiplied":
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[5]);
			answer += x * y;
			break;
		case "divided":
			x = Integer.parseInt(words[2]);
			y = Integer.parseInt(words[5]);
			answer += x / y;
			break;
		}
		
		return answer;
	}

}
