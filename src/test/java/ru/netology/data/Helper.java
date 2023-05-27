package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.String.valueOf;

public class Helper {
    private static Faker faker = new Faker();
    private static Faker fakerEn = new Faker(new Locale("En"));
    private static Faker fakerRu = new Faker(new Locale("Ru"));

    // ВАЛИДНЫЕ ДАННЫЕ
    //Номер карты

    public static String getApprovedCardNumber() {
        var approvedNumber = "4444 4444 4444 4441";
        return approvedNumber;
    }

    //Владелец
    public static String getOwner() {
        var randomFirstName = fakerEn.name().firstName();
        var randomLastName = fakerEn.name().lastName();
        return randomFirstName + " " + randomLastName;
    }

    //Месяц
    public static String getMonth() {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Random index = new Random();
        var indexInt = index.nextInt(months.length);
        return months[indexInt];
    }

    //Год
    public static String getYear() {
        String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String validity1 = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        String validity2 = LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
        String validity3 = LocalDate.now().plusYears(3).format(DateTimeFormatter.ofPattern("yy"));
        String validity4 = LocalDate.now().plusYears(4).format(DateTimeFormatter.ofPattern("yy"));
        String validity5 = LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy"));

        String[] years = {validity1, validity2, validity3, validity4, validity5};
        Random index = new Random();
        var indexInt = index.nextInt(years.length);
        return years[indexInt];
    }


    //CVC
    public static String getCVC() {
        var CVC = valueOf(faker.number().numberBetween(100, 999));
        return CVC;
    }

    // НЕВАЛИДНЫЕ ДАННЫЕ

    public static String getEmpty() {
        var empty = "";
        return empty;
    }

    // Номер карты
    public static String getDeclinedNumber() {
        var declinedNumber = "4444 4444 4444 4442";
        return declinedNumber;
    }

    public static String getRandomNumber() {
        var randomNumber = faker.numerify("#### #### #### ####");
        return randomNumber;
    }

    public static String getZeroNumber() {
        var zeroNumber = "0000 0000 0000 0000";
        return zeroNumber;
    }

    public static String getRandomDigit() {
        var randomDigit = faker.numerify("#");
        return randomDigit;
    }

    public static String getRandomFifteenNumber() {
        var randomFifteenNumber = faker.numerify("#### #### #### ###");
        return randomFifteenNumber;
    }

    public static String getRandomSeventeenNumber() {
        var randomSeventeenNumber = faker.numerify("#### #### #### #####");
        return randomSeventeenNumber;
    }

    public static String getCyrillicLettersNumber() {
        String cyrillicLettersNumber = fakerRu.lorem().sentence(4, 4);
        return cyrillicLettersNumber;
    }

    public static String getEnglishLettersNumber() {
        String englishLettersNumber = fakerEn.letterify("???? ???? ???? ????");
        return englishLettersNumber;
    }

    public static String getSymbolsNumber() {
        var symbolsNumber = "@@@@ #### %%%% &&&&";
        return symbolsNumber;
    }


    // Владелец
    public static String getOverLimitLettersOwner() {
        String overLimit = fakerEn.letterify("?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
        return overLimit;
    }

    public static String getSymbolOwner() {
        var symbolOwner = "#&@$&+*^%№=";
        return symbolOwner;
    }

    public static String getNumbersOwner() {
        var numbersOwner = faker.numerify("####### ######");
        return numbersOwner;
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
        var twoZero = "00";
        return twoZero;
    }

    public static String getThirteenMonth() {
        var thirteenMonth = "13";
        return thirteenMonth;
    }

    public static String getTwoLetters() {
        var twoLetters = fakerEn.letterify("??");
        return twoLetters;
    }

    // Год
    public static String getPastYear() {
        var pastYear = LocalDate.now().minusYears(10);
        var format = DateTimeFormatter.ofPattern("yy");
        return pastYear.format(format);
    }

    public static String getMoreThanFiveYear() {
        var date = LocalDate.now().plusYears(6);
        var format = DateTimeFormatter.ofPattern("yy");
        return date.format(format);
    }

    public static String getSymbolYear() {
        var symbolYear = "@$";
        return symbolYear;
    }


    // CVC
    public static String getTwoSymbolsCVC() {
        var twoSymbolsCVC = faker.numerify("##");
        return twoSymbolsCVC;
    }

    public static String getZeroCVC() {
        var zeroCVC = "000";
        return zeroCVC;
    }

    public static String getLettersCVC() {
        var lettersCVC = fakerEn.letterify("???");
        return lettersCVC;
    }

    public static String getSymbolCVC() {
        var symbolCVC = "@$&";
        return symbolCVC;
    }
}
