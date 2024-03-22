package Library;

import Library.Literature.EduLiterature.ENGrade;
import Library.Literature.EduLiterature.EduLiteratureFabric;
import Library.Literature.EduLiterature.RUKind;
import Library.Literature.FictionLiterature.FictionLiteratureFabric;
import Library.Literature.FictionLiterature.RUPeriod;
import Library.Literature.Literature;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.*;

import static Library.ExcelReader.ReadExcel;

public class BookGenerator {
    public static final Faker rufaker = new Faker(Locale.forLanguageTag("ru-RU"));
    public static final Faker enfaker = new Faker();

    public static ArrayList<Literature> generateLiterature(int ruFicNum, int enFicNum, int ruEduNum, int enEduNum) {
        ArrayList<Literature> bookList = new ArrayList<Literature>();

        List<String> enMajorList = ReadExcel("files/ensubj.xlsx");
        List<String> ruMajorList = ReadExcel("files/subj.xlsx");

        Random random = new Random();

        // Добавляем учебные пособия на русском языке
        for (int i = 0; i < Math.min(ruEduNum, ruMajorList.size()); i++) {
            // Генерация случайного индекса для выбора типа учебного пособия
            int randomIndex = random.nextInt(RUKind.values().length);
            RUKind randomKind = RUKind.values()[randomIndex];

            bookList.add(
                EduLiteratureFabric.createBook("ru", rufaker.book().title(), rufaker.book().author(),
                        ruMajorList.get(i), randomKind.toString(), null)
            );
        }

        // Добавляем учебные пособия на английском языке
        for (int i = 0; i < Math.min(enEduNum, enMajorList.size()); i++) {
            int randomIndex = random.nextInt(ENGrade.values().length);
            ENGrade randomGrade = ENGrade.values()[randomIndex];

            bookList.add(
                    EduLiteratureFabric.createBook("en", enfaker.book().title(), enfaker.book().author(),
                            enMajorList.get(i), randomGrade.toString(), enfaker.university().name())
            );
        }

        // Добавляем художественную литературу на русском языке
        for (int i = 0; i < ruFicNum; i++) {
            // Генерация случайного индекса для выбора периода
            int randomIndex = random.nextInt(RUPeriod.values().length);
            RUPeriod randomPeriod = RUPeriod.values()[randomIndex];

            bookList.add(
                    FictionLiteratureFabric.createBook("ru", rufaker.book().title(), rufaker.book().author(),
                            rufaker.book().genre(), randomPeriod.getDescription())
            );
        }

        // Добавляем художественную литературу на английском языке
        for (int i = 0; i < ruFicNum; i++) {
            bookList.add(
                    FictionLiteratureFabric.createBook("en", enfaker.book().title(), enfaker.book().author(),
                            enfaker.book().genre(), enfaker.country().name())
            );
        }

        return bookList;
    }
}
