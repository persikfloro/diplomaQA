package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.String.valueOf;

public class Helper {
    private static Random random = new Random();
    private static Faker faker = new Faker();
    private static Faker fakerEn = new Faker(new Locale("En"));
    private static Faker fakerRu = new Faker(new Locale("Ru"));

    // ВАЛИДНЫЕ ДАННЫЕ
    //Номер карты

    public static String approvedCardNumber() {
        String getApprovedNumber = "4444 4444 4444 4441";
        return getApprovedNumber;
    }

    //Владелец
    public static String getOwner() {
        String randomFirstName = fakerEn.name().firstName();
        String randomLastName = fakerEn.name().lastName();
        return randomFirstName + " " + randomLastName;
    }

    //Месяц
    public static String getMonth() {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Random index = new Random();
        int indexInt = index.nextInt(months.length);
        return months[indexInt];
    }

    //Год
    public static String getYear() {
        String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String validity1 = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        String validity2 = LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
        String validity3 = LocalDate.now().plusYears(3).format(DateTimeFormatter.ofPattern("yy"));
        String validity4 = LocalDate.now().plusYears(4).format(DateTimeFormatter.ofPattern("yy"));

        String[] years = {now, validity1, validity2, validity3, validity4};
        Random index = new Random();
        int indexInt = index.nextInt(years.length);
        return years[indexInt];
    }


    //CVC
    public static String getCVC() {
        return valueOf(faker.number().numberBetween(100, 999));
    }

    // НЕВАЛИДНЫЕ ДАННЫЕ

    public static String getEmpty() {
        return "";
    }

    // Номер карты
    public static String getDeclinedNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getRandomNumber() {
        return faker.numerify("#### #### #### ####");
    }

    public static String getZeroNumber() {
        return "0000 0000 0000 0000";
    }

    public static String getRandomDigit() {
        return faker.numerify("#");
    }

    public static String getRandomFifteenNumber() {
        return faker.numerify("#### #### #### ###");
    }

    public static String getRandomSeventeenNumber() {
        return faker.numerify("#### #### #### #####");
    }

    public static String getCyrillicLettersNumber() {
        String cyrillicLettersNumber = fakerRu.lorem().sentence(4, 4);
        return cyrillicLettersNumber;
    }

    public static String getEnglishLettersNumber() {
        String englishLettersNumber = fakerEn.lorem().sentence(4, 4);
        return englishLettersNumber;
    }

    public static String getSymbolsNumber() { // подумать как заменить
        return "@@@@ #### %%%% &&&&";
    }


    // Владелец
    public static String getOverLimitLettersOwner() {
        String overLimit = fakerEn.lorem().fixedString(1000);
        return overLimit;
    }

    public static String getSymbolOwner() {
        return "#&@$&+*^%№=";
    }

    public static String getNumbersOwner() {
        return faker.numerify("####### ######");
    }

    public static String getCyrillicOwner() {
        String randomFirstName = fakerRu.name().firstName();
        String randomLastName = fakerRu.name().lastName();
        return randomFirstName + " " + randomLastName;
    }

    public static String getSingleWordOwner() {
        String firstNameOwner = fakerEn.name().firstName();
        return firstNameOwner;
    }

    public static String getThreeWordOwner() {
        String firstNameOwner = fakerEn.name().firstName();
        String threeWordOwner = fakerEn.name().fullName() + " " + firstNameOwner;
        return threeWordOwner;
    }

    // Месяц
    public static String getTwoZero() {
        return "00";
    }

    public static String getThirteenMonth() {
        return "13";
    }

    public static String getTwoLetters() {
        return fakerEn.letterify("##");
    }

    // Год
    public static String getPastYear() {
        LocalDate pastYear = LocalDate.now().minusYears(10);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        return pastYear.format(format);
    }

    public static String getMoreThanFiveYear() {
        LocalDate date = LocalDate.now().plusYears(6);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        return date.format(format);
    }

    public static String getSymbolYear() { // подумать как заменить
        return "@$";
    }


    // CVC
    public static String getTwoSymbolsCVC() {
        return faker.numerify("##");
    }

    public static String getZeroCVC() {
        return "000";
    }

    public static String getLettersCVC() { // подумать как заменить
        return fakerEn.letterify("###");
    }

    public static String getSymbolCVC() { // подумать как заменить
        return "@$&";
    }
}
