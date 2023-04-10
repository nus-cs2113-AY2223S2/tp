package seedu.apollo.utils;

import seedu.apollo.module.LessonType;

public class LessonTypeUtil {

    /**
     * Converts a lesson type string to a LessonType enum.
     *
     * @param lessonType The string to be converted.
     * @return The LessonType enum representation of the string.
     */
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

    /**
     * Converts a LessonType enum to a string.
     *
     * @param lessonType The LessonType enum to be converted.
     * @param isFlag Whether the string should be a flag.
     * @return The string representation of the LessonType enum.
     */
    public static String enumToString(LessonType lessonType, Boolean isFlag){
        switch (lessonType) {
        case LECTURE:
            return "Lecture" + (isFlag ? " (-lec)" : "");
        case PACKAGED_LECTURE:
            return "Packaged Lecture" + (isFlag ? " (-plec)" : "");
        case SECTIONAL_TEACHING:
            return "Sectional Teaching" + (isFlag ? " (-st)" : "");
        case DESIGN_LECTURE:
            return "Design Lecture" + (isFlag ? " (-dlec)" : "");
        case TUTORIAL:
            return "Tutorial" + (isFlag ? " (-tut)" : "");
        case PACKAGED_TUTORIAL:
            return "Packaged Tutorial" + (isFlag ? " (-ptut)" : "");
        case RECITATION:
            return "Recitation" + (isFlag ? " (-rcit)" : "");
        case LABORATORY:
            return "Laboratory" + (isFlag ? " (-lab)" : "");
        case WORKSHOP:
            return "Workshop" + (isFlag ? " (-ws)" : "");
        case SEMINAR_STYLE_MODULE_CLASS:
            return "Seminar-Style Module Class" + (isFlag ? " (-smc)" : "");
        case MINI_PROJECT:
            return "Mini-Project" + (isFlag ? " (-mp)" : "");
        case TUTORIAL_TYPE_2:
            return "Tutorial Type 2" + (isFlag ? " (-tt2)" : "");
        default:
            return null;
        }
    }
}
