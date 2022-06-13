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
        String word;
        if (firebase_flag){
            word = translateToEnglishCategory(raw_category);
        }else {
            word = translateFromEnglishCategory(raw_category);
        }
        return word;
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
                                                "Аппетайзеры" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Bakery":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אפיה" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Выпечка" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Breakfast":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ארוחת בוקר" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Завтрак" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Desserts":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קינוחים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Десерты" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Dinner":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ארוחת ערב" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Ужин" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Drinks":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "משקאות" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Напитки" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Lunch":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ארוחת צהריים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Обед" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Salads":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "סלטים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Салаты" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Sides":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "צדדיות" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Гарниры" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Snacks":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "חטיפים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Закуски" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Starters":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "מנות ראשונות" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Первое блюдо" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "BBQ":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "על האש" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Барбекю" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Brunch":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "בראנץ'" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Поздний завтрак" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Casual Party":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "מסיבה" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Вечеринку" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Date Night":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "דייט" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Свидание" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Game Day":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "יום המשחק" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Игровой день" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Happy Hour":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "האפי האור" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Счастливый час" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Special Occasion":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אירוע מיוחד" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Особенное событие" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Weeknight":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "לילה רגיל" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Будний вечер" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "African":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אפריקאי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Африканская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "American":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אמריקאי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Американская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Brazilian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ברזילאי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Бразильская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "British":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "בריטי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Британская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Caribbean":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קאריבי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Карибская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Chinese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "סיני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Китайская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Cuban":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קובני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Кубанская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Domonican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "דומיניקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Доминиканская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Ethiopian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "אתיופי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Эфиопская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Filipino":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פיליפיני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Филиппинская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "French":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "צרפתי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Французская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Fusion":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "משולב" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Фьюжн" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "German":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "גרמני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Немецкая" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Greek":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "יווני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Греческая" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Hawaiian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "הוואי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Гавайская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Indian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "הודי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Индийская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Israeli":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ישראלי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Израильская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Italian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "איטלקי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Итальянская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Jamaican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ג'מייקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Джамайканская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Japanese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "יפני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Японская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Korean":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "קוריאני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Корейская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Latin":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "לטיני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Латинская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Lebanese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "לבנוני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Ливанская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Mexican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "מקסיקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Мексиканская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Middle Eastern":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ים תיכוני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Средиземноморская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Persion":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פרסי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Персидская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Peruvian":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פרואני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Перуанская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Portuguese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פורטוגלי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Португальская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Puerto Rican":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פורטו ריקני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Пуэрториканская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Seafood":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "פירות ים" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Морепродукты" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Swedish":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "שוודי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Шведская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Taiwanese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "טייוואני" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Тайваньская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Thai":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "תאילנדי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Таиландская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Venezuelan":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ונצואלי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Венесуэльская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
                break;
            case "Vietnamese":
                translation =
                        deviceLanguage.equals(Utilities.LANGUAGE_ENGLISH) ? word :
                                deviceLanguage.equals(Utilities.LANGUAGE_HEBREW) ?
                                        "ויאטנמי" :
                                        deviceLanguage.equals(Utilities.LANGUAGE_RUSSIAN) ?
                                                "Вьетнамская" :
                                                Utilities.UNSUPPORTED_LANGUAGE;
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
            case "סלטים":
                translation = "Salads";
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
            case "ישראלי":
                translation = "Israeli";
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
            case "Аппетайзеры":
                translation = "Appetizers";
                break;
            case "Выпечка":
                translation = "Bakery";
                break;
            case "Завтрак":
                translation = "Breakfast";
                break;
            case "Десерты":
                translation = "Desserts";
                break;
            case "Ужин":
                translation = "Dinner";
                break;
            case "Напитки":
                translation = "Drinks";
                break;
            case "Обед":
                translation = "Lunch";
                break;
            case "Салаты":
                translation = "Salads";
                break;
            case "Гарниры":
                translation = "Sides";
                break;
            case "Закуски":
                translation = "Snacks";
                break;
            case "Первое блюдо":
                translation = "Starters";
                break;
            case "Барбекю":
                translation = "BBQ";
                break;
            case "Поздний завтрак":
                translation = "Brunch";
                break;
            case "Вечеринку":
                translation = "Casual Party";
                break;
            case "Свидание":
                translation = "Date Night";
                break;
            case "Игровой день":
                translation = "Game Day";
                break;
            case "Счастливый час":
                translation = "Happy Hour";
                break;
            case "Особенное событие":
                translation = "Special Occasion";
                break;
            case "Будний вечер":
                translation = "Weeknight";
                break;
            case "Африканская":
                translation = "African";
                break;
            case "Американская":
                translation = "American";
                break;
            case "Бразильская":
                translation = "Brazilian";
                break;
            case "Британская":
                translation = "British";
                break;
            case "Карибская":
                translation = "Caribbean";
                break;
            case "Китайская":
                translation = "Chinese";
                break;
            case "Кубанская":
                translation = "Cuban";
                break;
            case "Доминиканская":
                translation = "Domonican";
                break;
            case "Эфиопская":
                translation = "Ethiopian";
                break;
            case "Филиппинская":
                translation = "Filipino";
                break;
            case "Французская":
                translation = "French";
                break;
            case "Фьюжн":
                translation = "Fusion";
                break;
            case "Немецкая":
                translation = "German";
                break;
            case "Греческая":
                translation = "Greek";
                break;
            case "Гавайская":
                translation = "Hawaiian";
                break;
            case "Индийская":
                translation = "Indian";
                break;
            case "Израильская":
                translation = "Israeli";
                break;
            case "Итальянская":
                translation = "Italian";
                break;
            case "Джамайканская":
                translation = "Jamaican";
                break;
            case "Японская":
                translation = "Japanese";
                break;
            case "Корейская":
                translation = "Korean";
                break;
            case "Латинская":
                translation = "Latin";
                break;
            case "Ливанская":
                translation = "Lebanese";
                break;
            case "Мексиканская":
                translation = "Mexican";
                break;
            case "Средиземноморская":
                translation = "Middle Eastern";
                break;
            case "Персидская":
                translation = "Persion";
                break;
            case "Перуанская":
                translation = "Peruvian";
                break;
            case "Португальская":
                translation = "Portuguese";
                break;
            case "Пуэрториканская":
                translation = "Puerto Rican";
                break;
            case "Морепродукты":
                translation = "Seafood";
                break;
            case "Шведская":
                translation = "Swedish";
                break;
            case "Тайваньская":
                translation = "Taiwanese";
                break;
            case "Таиландская":
                translation = "Thai";
                break;
            case "Венесуэльская":
                translation = "Venezuelan";
                break;
            case "Вьетнамская":
                translation = "Vietnamese";
                break;
        }
        return translation;
    }

}
