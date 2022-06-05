package com.l_es.communityrecipes;

/**
 * Created by Lidor on 06/03/2022.
 * Developer name: L-ES
 *  _        _   _____     ____    ______
 * | |      |_| |  __ \   / __ \  |  O   |
 * | |      | | | |  | | | |  | | |   ___/
 * | |____  | | | |__| | | |__| | | | \
 * |______| |_| |_____/   \____/  |_|__\
 *  ____         ____
 * |  __|       |  __|
 * |  __|   _   |__  |
 * |____|  |_|  |____|
 */
public class Translations {

    public static String translateCategoryHelper(String raw_category, boolean firebase_flag){
        String word = "";
        if (firebase_flag){
            word = translateToEnglishCategory(raw_category);
        }else {
            word = translateFromEnglishCategory(raw_category);
        }
        return word;
    }

    public static String translateFromEnglishCategory(String word){
        String translation = word;
        String deviceLanguage = Utilities.getDeviceLanguage();
        switch (word) {
            case "Appetizers":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "מתאבנים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Bakery":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אפיה" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Breakfast":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ארוחת בוקר" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Desserts":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קינוחים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Dinner":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ארוחת ערב" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Drinks":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "משקאות" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Lunch":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ארוחת צהריים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Sides":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "צדדיות" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Snacks":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "חטיפים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Starters":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "מנות ראשונות" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "BBQ":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "על האש" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Brunch":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "בראנץ'" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Casual Party":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "מסיבה" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Date Night":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "דייט" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Game Day":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "יום המשחק" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Happy Hour":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "האפי האור" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Special Occasion":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אירוע מיוחד" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Weeknight":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "לילה רגיל" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "African":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אפריקאי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "American":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אמריקאי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Brazilian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ברזילאי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "British":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "בריטי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Caribbean":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קאריבי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Chinese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "סיני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Cuban":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קובני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Domonican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "דומיניקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Ethiopian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אתיופי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Filipino":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פיליפיני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "French":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "צרפתי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Fusion":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "משולב" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "German":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "גרמני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Greek":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "יווני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Hawaiian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "הוואי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Indian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "הודי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Italian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "איטלקי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Jamaican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ג'מייקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Japanese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "יפני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Korean":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קוריאני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Latin":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "לטיני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Lebanese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "לבנוני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Mexican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "מקסיקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Middle Eastern":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ים תיכוני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Persion":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פרסי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Peruvian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פרואני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Portuguese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פורטוגלי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Puerto Rican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פורטו ריקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Seafood":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פירות ים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Swedish":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "שוודי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Taiwanese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "טייוואני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Thai":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "תאילנדי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Venezuelan":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ונצואלי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Vietnamese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ויאטנמי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "true" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
        }
        return translation;
    }

    public static String translateToEnglishCategory(String word){
        String translation = word;
        String deviceLanguage = Utilities.getDeviceLanguage();
        switch (deviceLanguage) {
            case Utilities.LANGUAGE_HEBREW:
                translation = translateHebrewCategory(word);
                break;
            case Utilities.LANGUAGE_RUSSIAN:
                translation = translateRussianCategory(word);
                break;
            case Utilities.LANGUAGE_ENGLISH:
                translation = translateFromEnglishCategory(word);
                break;
        }
        return translation;
    }

    public static String translateHebrewCategory(String word){
        String translation = word;
        switch (word) {
            case "מתאבנים":
                translation = "Appetizers";
                break;
            case "אפיה":
                translation = "Bakery";
                break;
            case "ארוחת בוקר":
                translation = "Breakfast";
                break;
            case "קינוחים":
                translation = "Desserts";
                break;
            case "ארוחת ערב":
                translation = "Dinner";
                break;
            case "משקאות":
                translation = "Drinks";
                break;
            case "ארוחת צהריים":
                translation = "Lunch";
                break;
            case "צדדיות":
                translation = "Sides";
                break;
            case "חטיפים":
                translation = "Snacks";
                break;
            case "מנות ראשונות":
                translation = "Starters";
                break;
            case "על האש":
                translation = "BBQ";
                break;
            case "בראנץ'":
                translation = "Brunch";
                break;
            case "מסיבה":
                translation = "Casual Party";
                break;
            case "דייט":
                translation = "Date Night";
                break;
            case "יום המשחק":
                translation = "Game Day";
                break;
            case "האפי האור":
                translation = "Happy Hour";
                break;
            case "אירוע מיוחד":
                translation = "Special Occasion";
                break;
            case "לילה רגיל":
                translation = "Weeknight";
                break;
            case "אפריקאי":
                translation = "African";
                break;
            case "אמריקאי":
                translation = "American";
                break;
            case "ברזילאי":
                translation = "Brazilian";
                break;
            case "בריטי":
                translation = "British";
                break;
            case "קאריבי":
                translation = "Caribbean";
                break;
            case "סיני":
                translation = "Chinese";
                break;
            case "קובני":
                translation = "Cuban";
                break;
            case "דומיניקני":
                translation = "Domonican";
                break;
            case "אתיופי":
                translation = "Ethiopian";
                break;
            case "פיליפיני":
                translation = "Filipino";
                break;
            case "צרפתי":
                translation = "French";
                break;
            case "משולב":
                translation = "Fusion";
                break;
            case "גרמני":
                translation = "German";
                break;
            case "יווני":
                translation = "Greek";
                break;
            case "הוואי":
                translation = "Hawaiian";
                break;
            case "הודי":
                translation = "Indian";
                break;
            case "איטלקי":
                translation = "Italian";
                break;
            case "ג'מייקני":
                translation = "Jamaican";
                break;
            case "יפני":
                translation = "Japanese";
                break;
            case "קוריאני":
                translation = "Korean";
                break;
            case "לטיני":
                translation = "Latin";
                break;
            case "לבנוני":
                translation = "Lebanese";
                break;
            case "מקסיקני":
                translation = "Mexican";
                break;
            case "ים תיכוני":
                translation = "Middle Eastern";
                break;
            case "פרסי":
                translation = "Persion";
                break;
            case "פרואני":
                translation = "Peruvian";
                break;
            case "פורטוגלי":
                translation = "Portuguese";
                break;
            case "פורטו ריקני":
                translation = "Puerto Rican";
                break;
            case "פירות ים":
                translation = "Seafood";
                break;
            case "שוודי":
                translation = "Swedish";
                break;
            case "טייוואני":
                translation = "Taiwanese";
                break;
            case "תאילנדי":
                translation = "Thai";
                break;
            case "ונצואלי":
                translation = "Venezuelan";
                break;
            case "ויאטנמי":
                translation = "Vietnamese";
                break;
        }
        return translation;
    }

    public static String translateRussianCategory(String word){
        String translation = word;
        switch (word) {
            case "מתאבנים":
                translation = "Appetizers";
                break;
            case "אפיה":
                translation = "Bakery";
                break;
            case "ארוחת בוקר":
                translation = "Breakfast";
                break;
            case "קינוחים":
                translation = "Desserts";
                break;
            case "ארוחת ערב":
                translation = "Dinner";
                break;
            case "משקאות":
                translation = "Drinks";
                break;
            case "ארוחת צהריים":
                translation = "Lunch";
                break;
            case "צדדיות":
                translation = "Sides";
                break;
            case "חטיפים":
                translation = "Snacks";
                break;
            case "מנות ראשונות":
                translation = "Starters";
                break;
            case "על האש":
                translation = "BBQ";
                break;
            case "בראנץ'":
                translation = "Brunch";
                break;
            case "מסיבה":
                translation = "Casual Party";
                break;
            case "דייט":
                translation = "Date Night";
                break;
            case "יום המשחק":
                translation = "Game Day";
                break;
            case "האפי האור":
                translation = "Happy Hour";
                break;
            case "אירוע מיוחד":
                translation = "Special Occasion";
                break;
            case "לילה רגיל":
                translation = "Weeknight";
                break;
            case "אפריקאי":
                translation = "African";
                break;
            case "אמריקאי":
                translation = "American";
                break;
            case "ברזילאי":
                translation = "Brazilian";
                break;
            case "בריטי":
                translation = "British";
                break;
            case "קאריבי":
                translation = "Caribbean";
                break;
            case "סיני":
                translation = "Chinese";
                break;
            case "קובני":
                translation = "Cuban";
                break;
            case "דומיניקני":
                translation = "Domonican";
                break;
            case "אתיופי":
                translation = "Ethiopian";
                break;
            case "פיליפיני":
                translation = "Filipino";
                break;
            case "צרפתי":
                translation = "French";
                break;
            case "משולב":
                translation = "Fusion";
                break;
            case "גרמני":
                translation = "German";
                break;
            case "יווני":
                translation = "Greek";
                break;
            case "הוואי":
                translation = "Hawaiian";
                break;
            case "הודי":
                translation = "Indian";
                break;
            case "איטלקי":
                translation = "Italian";
                break;
            case "ג'מייקני":
                translation = "Jamaican";
                break;
            case "יפני":
                translation = "Japanese";
                break;
            case "קוריאני":
                translation = "Korean";
                break;
            case "לטיני":
                translation = "Latin";
                break;
            case "לבנוני":
                translation = "Lebanese";
                break;
            case "מקסיקני":
                translation = "Mexican";
                break;
            case "ים תיכוני":
                translation = "Middle Eastern";
                break;
            case "פרסי":
                translation = "Persion";
                break;
            case "פרואני":
                translation = "Peruvian";
                break;
            case "פורטוגלי":
                translation = "Portuguese";
                break;
            case "פורטו ריקני":
                translation = "Puerto Rican";
                break;
            case "פירות ים":
                translation = "Seafood";
                break;
            case "שוודי":
                translation = "Swedish";
                break;
            case "טייוואני":
                translation = "Taiwanese";
                break;
            case "תאילנדי":
                translation = "Thai";
                break;
            case "ונצואלי":
                translation = "Venezuelan";
                break;
            case "ויאטנמי":
                translation = "Vietnamese";
                break;
        }
        return translation;
    }

}
