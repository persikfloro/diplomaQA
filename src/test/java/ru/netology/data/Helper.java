package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static java.lang.String.valueOf;

public class Helper {
    private static Random random = new Random();
    private static Faker faker = new Faker();
    private static Faker fakerEn = new Faker(new Locale("En"));
    private static Faker fakerRu = new Faker(new Locale("Ru"));

    // ВАЛИДНЫЕ ДАННЫЕ
    //Номер карты
    public static String getApprovedNumber() {
        return "2222 3333 2222 3333";
    }

    //Владелец
    public static String getEnglishOwner() {
        var randomFirstName = fakerEn.name().firstName();
        var randomLastName = fakerEn.name().lastName();
        return randomFirstName + " " + randomLastName;
    }
    //Месяц
    public static String getMonth() {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return months[random.nextInt(months.length)];
    }
    //Год
    public static String getYear() {
        return valueOf(faker.number().numberBetween(23, 27));
    }


    //CVC
    public static String getCVC() {
        return valueOf(faker.number().numberBetween(100, 999));
    }

   // НЕВАЛИДНЫЕ ДАННЫЕ

   // Номер карты
   public static String getDeclinedNumber() {
       return "3333 4444 3333 4444";
   }
    public static String getRandomNumber() {
    return faker.numerify("#### #### #### ####");
}
    public static String getZeroNumber() {
    return "0000 0000 0000 0000";
}

    public static String getRandomSingleNumber() {
        return faker.numerify("#");
    }

    public static String getRandomFifteenNumber() {
        return faker.numerify("#### #### #### ###");
    }

    public static String getRandomSeventeenNumber() {
        return faker.numerify("#### #### #### #####");
    }

    public static String getEmptyNumber() {
        return "" ;
    }

    public static String getCyrillicLettersNumber() {
        return "аааа ииии лллл дддд" ;
    }

    public static String getEnglishLettersNumber() {
        return "gggg hhhh tttt oooo" ;
    }

    public static String getSymbolsNumber() {
        return "@@@@ #### %%%% &&&&" ;
    }



    // Владелец
    public static String getOverLimitLettersOwner(String count) {
    return fakerEn.letterify(count).toUpperCase();
}

    public static String getSymbolOwner() {
        return "#&@$&+*^%№=";
    }

    public static String getNumbersOwner() {
        return faker.numerify("####### ######");
    }

    public static String getCyrillicOwner() {
        var randomFirstName = fakerRu.name().firstName();
        var randomLastName = fakerRu.name().lastName();
        return randomFirstName + " " + randomLastName;
    }

    public static String getSingleWordOwner() {
        return fakerRu.name().firstName();
    }

    public static String getThreeWordOwner() {
        return fakerEn.name().fullName() + "Hermit";
    }

    public static String getEmptyOwner() {
        return "" ;
    }


    // Месяц
    public static String getZeroMonth() {
    return "00";
}

    public static String getThirteenMonth() {
        return "13";
    }

    public static String getRandomSingleNumberMonth() {
        return faker.numerify("#");
    }

    public static String getEnglishLettersNumberMonth(){ return fakerEn.letterify("##");}

    public static String getEmptyMonth() {
        return "" ;
    }


    // Год
    public static String getPastYear() {
    String[] years = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21"};
    return years[random.nextInt(years.length)];
}

    public static String getZeroYear() {
        return "00";
    }

    public static String getMoreThanSixYear() {
        LocalDate date = LocalDate.now().plusYears(7);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        return date.format(format);
    }

    public static String getRandomSingleYear() {
        return faker.numerify("#");
    }

    public static String getEmptyYear() {
        return "" ;
    }

    public static String getLettersYear() {
        return "aa";
    }

    public static String getSymbolYear() {
        return "@$";
    }


    // CVC
    public static String getOneSymbolCVC() {
        return faker.numerify("#");
    }

    public static String getTwoSymbolsCVC() {
        return faker.numerify("##");
    }

    public static String getEmptyCVC() {
        return "" ;
    }

    public static String getZeroCVC() {
        return "000";
    }

    public static String getLettersCVC() {
        return "cvc";
    }

    public static String getSymbolCVC() {
        return "@$&";
    }
}
