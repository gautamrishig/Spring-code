package com.javarish.api.pattern.factory;

public class AnimalFactoryPatternDemo {

	public static void main(String[] args) {
     
		Animal dog = AnimalFactory.getAnimalType(EnumClass.AnimalTpyeCat.DOG.toString());
		System.err.println(dog.speak());
		
		Animal cat = AnimalFactory.getAnimalType(EnumClass.AnimalTpyeCat.CAT.toString());
		System.err.println(cat.speak());
	}

}
