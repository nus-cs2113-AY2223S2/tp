package seedu.apollo.utils;

import seedu.apollo.module.LessonType;

public class LessonTypeUtil {

    public static LessonType determineLessonType(String lessonType) {
        switch (lessonType) {
        case "Lecture":
            return LessonType.LECTURE;
        case "Packaged Lecture":
            return LessonType.PACKAGED_LECTURE;
        case "Sectional Teaching":
            return LessonType.SECTIONAL_TEACHING;
        case "Design Lecture":
            return LessonType.DESIGN_LECTURE;
        case "Tutorial":
            return LessonType.TUTORIAL;
        case "Packaged Tutorial":
            return LessonType.PACKAGED_TUTORIAL;
        case "Recitation":
            return LessonType.RECITATION;
        case "Laboratory":
            return LessonType.LABORATORY;
        case "Workshop":
            return LessonType.WORKSHOP;
        case "Seminar-Style Module Class":
            return LessonType.SEMINAR_STYLE_MODULE_CLASS;
        case "Mini-Project":
            return LessonType.MINI_PROJECT;
        case "Tutorial Type 2":
            return LessonType.TUTORIAL_TYPE_2;
        default:
            return null;
        }
    }

    public static String enumToString(LessonType lessonType){
        switch (lessonType) {
        case LECTURE:
            return "Lecture";
        case PACKAGED_LECTURE:
            return "Packaged Lecture";
        case SECTIONAL_TEACHING:
            return "Sectional Teaching";
        case DESIGN_LECTURE:
            return "Design Lecture";
        case TUTORIAL:
            return "Tutorial";
        case PACKAGED_TUTORIAL:
            return "Packaged Tutorial";
        case RECITATION:
            return "Recitation";
        case LABORATORY:
            return "Laboratory";
        case WORKSHOP:
            return "Workshop";
        case SEMINAR_STYLE_MODULE_CLASS:
            return "Seminar-Style Module Class";
        case MINI_PROJECT:
            return "Mini-Project";
        case TUTORIAL_TYPE_2:
            return "Tutorial Type 2";
        default:
            return null;
        }
    }
}
