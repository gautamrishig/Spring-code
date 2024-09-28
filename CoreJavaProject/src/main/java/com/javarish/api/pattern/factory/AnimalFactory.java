package com.javarish.api.pattern.factory;

public abstract class AnimalFactory {

	public static Animal getAnimalType(String animalType) {

		System.out.println(EnumClass.AnimalTpyeCat.CAT.toString());

		if (animalType.equalsIgnoreCase(EnumClass.AnimalTpyeCat.DOG.toString())) {
			Animal dog = new Dog();
			return dog;

		} else if (animalType.equalsIgnoreCase(EnumClass.AnimalTpyeCat.CAT.toString())) {
			Animal cat = new Cat();
			return cat;
		}

		return null;
	}

}
