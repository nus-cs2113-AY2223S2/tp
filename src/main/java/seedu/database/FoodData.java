package seedu.database;

public final class FoodData {
    private static final String[] foodData = {
        "ID,Type,Name,Store,Store Number,Energy (Kcal),Protein (g),Total Fat (g)," +
            "Saturated Fat (g),Dietary Fibre (g),Carbohyrate (g),Sugar (g),Sodium (g)",
        "0,Dish,Black Pepper Chicken Chop ," +
            "Western,1,775,42.4,51.7,,,31.6,6.9,",
        "1,Dish,Chicken Cutlet ,Wester" +
            "n,1,966,47.8,60.1,,,54.9,7.2,",
        "2,Dish,Grilled Dory Fish ,West" +
            "ern,1,530,45.1,21.3,,,37.2,8.2,",
        "3,Dish,Fish & Chip ,Western" +
            ",1,838,46.4,49.1,,,50.7,8.3,",
        "4,Dish,Black Pepper Ribeye Steak ," +
            "Western,1,807,44.9,45.6,,,53.2,9.3,",
        "5,Dish,Combo Set 1: Ribeye Steak & Grilled" +
            " Chicken,Western,1,831,43.2,48.7,,,52.5,9,",
        "6,Dish,Combo Set 2: Ribeye Steak & Grille" +
            "d Fish,Western,1,680,41.3,33.2,,,52.4,9.2,",
        "7,Dish,Combo Set 3: Ribeye Steak & Grille" +
            "d Salmon,Western,1,776,42.4,43.6,,,52.3,9,",
        "8,Dish,Combo Set 4: Grilled Chicken Chop & Gr" +
            "illed Salmon,Western,1,846,43.7,50.8,,,51.1,9,",
        "9,Dish,Combo Set 5: Grilled Chicken Chop & Grill" +
            "ed Dory Fish,Western,1,750,42.7,40.3,,,51.3,9.2,",
        "10,Dish,Arrabiata ,Wester" +
            "n,1,295,10.3,5.1,,,50,7.3,",
        "11,Dish,Pomodoro ,Western" +
            ",1,341,13.9,2.7,,,69,21.6,",
        "12,Dish,Creamy Chicken Spaghetti (Smal" +
            "l) ,Western,1,755,31.6,54.3,,,34.1,3.7,",
        "13,Dish,Creamy Chicken Spaghetti (Nor" +
            "mal) ,Western,1,822,34,54.7,,,47.2,4,",
        "14,Dish,Creamy Chicken Spaghetti (Upsiz" +
            "e) ,Western,1,901,36.9,55.2,,,62.7,4.3,",
        "15,Dish,Creamy Chicken Ham & Mushroom Spaghett" +
            "i (Small) ,Western,1,571,19.2,38.2,,,37.8,4.2,",
        "16,Dish,Creamy Chicken Ham & Mushroom Spaghett" +
            "i (Normal) ,Western,1,638,21.7,38.6,,,50.9,4.4,",
        "17,Dish,Creamy Chicken Ham & Mushroom Spaghet" +
            "ti (Upsize) ,Western,1,717,24.6,39,,,66.4,4.7,",
        "18,Dish,Prawns & Mushroom Spaghetti (Sm" +
            "all) ,Western,1,557,20.5,36.5,,,36.9,4,",
        "19,Dish,Prawns & Mushroom Spaghetti (N" +
            "ormal) ,Western,1,624,23,36.9,,,50,4.2,",
        "20,Dish,Prawns & Mushroom Spaghetti (Ups" +
            "ize) ,Western,1,703,25.9,37.3,,,65.4,4.5,",
        "21,Dish,Hot Chicken Spaghetti (Smal" +
            "l) ,Western,1,298,16.6,8.6,,,36.9,7,",
        "22,Dish,Hot Chicken Spaghetti (Norm" +
            "al) ,Western,1,365,19.1,9,,,50,7.3,",
        "23,Dish,Hot Chicken Spaghetti (Upsiz" +
            "e) ,Western,1,444,22,9.4,,,65.5,7.6,",
        "24,Dish,Creamy Sausage & Mushroom Spaghetti" +
            " (Small) ,Western,1,688,28.8,46.7,,,37.6,4,",
        "25,Dish,Creamy Sausage & Mushroom Spaghetti" +
            " (Normal) ,Western,1,755,31.3,47,,,50.7,4.2,",
        "26,Dish,Creamy Sausage & Mushroom Spaghetti " +
            "(Upsize) ,Western,1,834,34.2,47.5,,,66.1,4.5,",
        "27,Dish,Alfredo (Small) ,Weste" +
            "rn,1,651,28.9,42.8,,,37.8,4.2,",
        "28,Dish,Alfredo (Normal) ,West" +
            "ern,1,718,31.3,43.2,,,50.9,4.4,",
        "29,Dish,Alfredo (Upsize) ,West" +
            "ern,1,797,34.2,43.7,,,66.4,4.7,",
        "30,Dish,Chicken Patty Spaghetti ,W" +
            "estern,1,530,29.3,19.1,,,58.4,7.6,",
        "31,Dish,Chicken Cheese Ball Spaghetti" +
            " ,Western,1,501,24.3,16.7,,,61.7,9.6,",
        "32,Dish,Salmon e Funghi Spaghetti (Sma" +
            "ll) ,Western,1,715,31.2,49.4,,,36.9,4,",
        "33,Dish,Salmon e Funghi Spaghetti (Nor" +
            "mal) ,Western,1,782,33.7,49.7,,,50,4.2,",
        "34,Dish,Salmon e Funghi Spaghetti (Ups" +
            "ize) ,Western,1,861,36.6,50.2,,,65.4,5,",
        "35,Dish,Seafood Marinara (Small) " +
            ",Western,1,373,36.1,7.8,,,37.9,7,",
        "36,Dish,Seafood Marinara (Normal" +
            ") ,Western,1,440,38.5,8.1,,,51,7,",
        "37,Dish,Seafood Marinara (Upsize)" +
            " ,Western,1,519,41.4,8.6,,,66.5,8,",
        "38,Dish,Creamy Fish Pasta (Small)" +
            " ,Western,1,616,32.1,39,,,34.1,4,",
        "39,Dish,Creamy Fish Pasta (Normal)" +
            " ,Western,1,683,34.6,39.4,,,47.2,4,",
        "40,Dish,Creamy Fish Pasta (Upsize)" +
            " ,Western,1,762,37.5,39.8,,,62.7,4,",
        "41,Dish,Baked Beans & Fries & Saus" +
            "age,Western,1,359,16.8,22,,,21.7,4,",
        "42,Dish,Baked Beans & Mashed Potato & Sa" +
            "usage ,Western,1,447,18.6,24.6,,,35.6,6,",
        "43,Side,Mashed Potato,West" +
            "ern,1,89,1.9,2.5,,,13.8,2,",
        "44,Side,Sunny Side Up,We" +
            "stern,1,102,7,8,,,0.5,0,",
        "45,Side,French Fries,Weste" +
            "rn,1,164,1.3,11.6,,,13.5,0,",
        "46,Side,Hot Dog,Western," +
            "1,153,13.4,10.3,,,0.7,0,",
        "47,Side,Spaghetti,Wester" +
            "n,1,157,5.8,0.9,,,30.8,1,",
        "48,Side,Tasty Rice,Wester" +
            "n,1,210,4.2,0.8,,,46.7,0,",
        "49,Side,Chicken Ham,Wes" +
            "tern,1,36,3.8,1.9,,,1,0,",
        "50,Side,Coleslaw,Western" +
            ",1,68,0.7,4.7,,,4.9,9.4,",
        "51,Dish,Signature Trio Eggs Spinach " +
            "Soup (Regular),Ramen,10,256,,,,,,14,",
        "52,Dish,Signature Trio Eggs Spinach So" +
            "up (Small Portion),Ramen,10,119,,,,,,0,",
        "53,Dish,Seafood Spinach Soup " +
            "(Regular),Ramen,10,188,,,,,,1,",
        "54,Dish,Seafood Spinach Soup (Sm" +
            "all Portion),Ramen,10,113,,,,,,0,",
        "55,Dish,Sliced Fish Spinach Sou" +
            "p (Regular),Ramen,10,224,,,,,,0,",
        "56,Dish,Sliced Fish Spinach Soup (" +
            "Small Portion),Ramen,10,134,,,,,,0,",
        "57,Dish,Fried Fish Spinach Soup" +
            " (Regular),Ramen,10,282,,,,,,0,",
        "58,Dish,Fried Fish Spinach Soup (S" +
            "mall Portion),Ramen,10,169,,,,,,0,",
        "59,Dish,Double Mixed Fish Spinach " +
            "Soup (Regular),Ramen,10,257,,,,,,0,",
        "60,Dish,Double Mixed Fish Spinach Sou" +
            "p (Small Portion),Ramen,10,191,,,,,,0,",
        "61,Dish,Fresh Mushrooms with Ram" +
            "en (Regular),Ramen,10,303,,,,,,5,",
        "62,Dish,Fresh Mushrooms with Ramen " +
            "(Small Portion),Ramen,10,182,,,,,,3,",
        "63,Dish,Mala Dry Ramen w Chicken" +
            " Cheeseballs,Ramen,10,676,,,,,,8,",
        "64,Dish,Mala Dry Ramen w Fuzhou" +
            " Fishballs,Ramen,10,713,,,,,,10,",
        "65,Dish,Mala Beef Ramen " +
            "Soup,Ramen,10,644,,,,,,7,",
        "66,Side,Egg (side)," +
            "Ramen,10,28,,,,,,0,",
        "67,Side,Century Egg (s" +
            "ide),Ramen,10,1,,,,,,0,",
        "68,Side,Salted Egg (si" +
            "de),Ramen,10,7,,,,,,0,",
        "69,Side,Wolfberry (si" +
            "de),Ramen,10,8,,,,,,1,",
        "70,Side,Spinach (sid" +
            "e),Ramen,10,2,,,,,,0,",
        "71,Side,Minced Pork (si" +
            "de),Ramen,10,135,,,,,,0,",
        "72,Side,Sliced Fish (si" +
            "de),Ramen,10,59,,,,,,0,",
        "73,Side,Fried Fish (sid" +
            "e),Ramen,10,117,,,,,,0,",
        "74,Side,Prawns (side" +
            "),Ramen,10,55,,,,,,0,",
        "75,Side,Chicken Cheeseballs" +
            " (side),Ramen,10,94,,,,,,5,",
        "76,Side,Fuzhou Fishballs " +
            "(side),Ramen,10,93,,,,,,6,",
        "77,Side,Sliced Beef (si" +
            "de),Ramen,10,107,,,,,,0,",
        "78,Side,White Rice (sid" +
            "e),Ramen,10,232,,,,,,0,",
        "79,Side,Thick Bee Hoon (s" +
            "ide),Ramen,10,276,,,,,,0,",
        "80,Side,Thin Bee Hoon (s" +
            "ide),Ramen,10,251,,,,,,0,",
        "81,Side,Brown Rice (sid" +
            "e),Ramen,10,218,,,,,,0,",
        "82,Side,Koka Noodle (si" +
            "de),Ramen,10,286,,,,,,4,",
        "83,Side,Ee Mee (side)" +
            ",Ramen,10,377,,,,,,2,",
        "84,Side,Ramen (side)" +
            ",Ramen,10,262,,,,,,4,",
        "85,Ingredient,Mala Sauce (Non-Spicy Bas" +
            "e),Mala,4,46,1.7,0.1,0,0.4,9.7,5.3,1006",
        "86,Ingredient,Coriander," +
            "Mala,4,0,0,0,0,0.1,0,0,1",
        "87,Ingredient,White Rice,Mala,4" +
            ",237,4.7,0.8,0.2,0.7,52.6,0.2,8",
        "88,Ingredient,Sotong Ring,Mala" +
            ",4,51,8.6,0.8,0.2,0,1.7,1.7,24",
        "89,Ingredient,Prawn,Mala," +
            "4,34,7.8,0.2,0.1,0,0,0,134",
        "90,Ingredient,Fish Slice,Mala," +
            "4,57,12.8,0.5,0.1,0,0.1,0.1,51",
        "91,Ingredient,Chicken Gizzard" +
            ",Mala,4,45,8.5,1,0.3,0,0,0,33",
        "92,Ingredient,Beef Slice,Mal" +
            "a,4,109,11.8,6.9,3.3,0,0,0,32",
        "93,Ingredient,Shitake Mushroom" +
            ",Mala,4,16,1,0.2,0,1.2,2,1.1,4",
        "94,Ingredient,King Oyster Mushroom" +
            ",Mala,4,13,1.3,0.2,0,0.7,1.4,0.7,2",
        "95,Ingredient,Xiao Bai Cai,Mal" +
            "a,4,7,0.8,0.1,0,0.5,0.6,0.6,36",
        "96,Ingredient,Wintermelon,Mala" +
            ",4,31,0.3,2.4,0.6,0.9,1.7,1,195",
        "97,Ingredient,Tomato,Mala," +
            "4,16,1,0.1,0,0.4,2.6,2.6,11",
        "98,Ingredient,Potato,Mala,4" +
            ",45,1.5,0.1,0,1.1,8.6,0.4,3",
        "99,Ingredient,Lady's Finger,Mala" +
            ",4,11.2,0.6,0,0,1.1,2.1,2.1,10.8",
        "100,Ingredient,Kang Kong,Mal" +
            "a,4,9,1.1,0.1,0,0.9,0.4,0,47",
        "101,Ingredient,Cucumber,Mal" +
            "a,4,20,0.3,0,0,0.3,1.9,1.9,7",
        "102,Ingredient,Cauliflower," +
            "Mala,4,15,2,0,0,1.4,3,2.9,35",
        "103,Ingredient,Cabbage,Mal" +
            "a,4,9,1.4,0.3,0,0,2.8,0,14",
        "104,Ingredient,Broccoli,Mal" +
            "a,4,12,1.3,0,0,1.2,0.9,0.9,4",
        "105,Ingredient,Brinjal,Mala" +
            ",4,11,0.7,0,0,0.4,2.3,2.3,4",
        "106,Ingredient,Taugay,Mala,4" +
            ",437,1.5,0.1,0,0.9,0.8,0.5,1",
        "107,Ingredient,Potato Starch Noo" +
            "dle,Mala,4,349,0,0,0,0,108.1,0,0",
        "108,Ingredient,Instant Noodle,Mala" +
            ",4,170,8.1,11.7,5.9,0.6,52.9,2.4,85",
        "109,Ingredient,Taiwan Sausage,Ma" +
            "la,4,156,7.2,13,5.8,0,6.2,3.1,287",
        "110,Ingredient,Seaweed Chicken,M" +
            "ala,4,82,7.5,12,2.5,1,4.9,1.4,307",
        "111,Ingredient,Saito Fish Cake,Ma" +
            "la,4,48,12.1,3,1.6,1.1,1.9,0.8,843",
        "112,Ingredient,Quail Egg,Mal" +
            "a,4,46,4,3.4,1.1,0,0.1,0.1,43",
        "113,Ingredient,Mushroom Ball," +
            "Mala,4,47,5.8,0.5,0,0,3.9,0,0",
        "114,Ingredient,Mini Fish Ball,Mala" +
            ",4,210,6.9,1.3,0.2,0.5,1.8,0.6,243",
        "115,Ingredient,Luncheon Meat,Mala" +
            ",4,107,6.2,19.3,7.4,0.5,2.8,0,559",
        "116,Ingredient,Mini Sausage,Mala" +
            ",4,141,4.5,8.3,3.6,0,3.9,1.9,178",
        "117,Ingredient,Fish Ball (Fried),Ma" +
            "la,4,99,10.5,9.8,1.2,0.7,2.8,0.9,370",
        "118,Ingredient,Fish Tau Foo,Mala" +
            ",4,135,4.8,5.8,0.8,0.7,7,2.8,304",
        "119,Ingredient,Cuttlefish Ball,Mal" +
            "a,4,21,8.5,7.1,2.8,1.5,9.4,1.5,537",
        "120,Ingredient,Cuttlefish,Ma" +
            "la,4,11,4.4,0.2,0,0,0.2,0,100",
        "121,Ingredient,Crabstick,Ma" +
            "la,4,100,1.2,0,0,0,0.6,0,16",
        "122,Ingredient,Crab Claw,Mala" +
            ",4,64,3.8,4.3,2,0,11.6,1.9,383",
        "123,Ingredient,Crab Ball,Mala," +
            "4,42,5.8,2,0.6,0.9,5.8,1.5,453",
        "124,Ingredient,Chickuwa,Mal" +
            "a,4,95,5.5,0.5,0,0,3.2,0,377",
        "125,Ingredient,Chicken Hotdog,Mal" +
            "a,4,122,5.5,7,1.9,0.5,2.4,0.8,450",
        "126,Ingredient,Cheese Tofu,Mala," +
            "4,33,6.4,9.1,3.6,0.7,3.7,2.1,486",
        "127,Ingredient,Bermuda Triangle Tofu (Ta" +
            "upok),Mala,4,12,3.3,1.5,0.2,0.2,1.5,0.6,3",
        "128,Ingredient,White Radish,Mal" +
            "a,4,126,0.6,0.1,0,0.8,2.2,2.2,47",
        "129,Ingredient,Peanut,Mala,4,17" +
            "0.5,7.4,14.1,2.1,2.5,2.7,1.5,0.3",
        "130,Ingredient,Thick Beehoon,Mala" +
            ",4,126.1,2.3,0.4,0.1,0.4,28.3,0,7",
        "131,Ingredient,Tau Kwa,Mala" +
            ",4,50,5,2.3,0.4,0.2,2.3,1,4",
        "132,Ingredient,Lotus Root,Mala" +
            ",4,51,1.2,0.1,0,2.4,12.3,0.4,34",
        "133,Ingredient,Kelp,Mala,4,2" +
            "7,1.8,0.4,0.1,0.3,5.2,0.4,526",
        "134,Ingredient,Enoki Mushroom,M" +
            "ala,4,13,0.9,0.1,0,0.9,1.8,0.1,1",
        "135,Ingredient,Black Fungus," +
            "Mala,4,19,1.3,0.2,0,2.3,3,0,8",
        "136,Ingredient,Beancurd Skin (Non-Frie" +
            "d),Mala,4,77,8.2,2.9,0.5,3.4,0.5,0.5,0",
        "137,Ingredient,Beancurd Skin (Fried)" +
            ",Mala,4,76,4.4,5.5,0.7,1.8,0.3,0.3,0",
        "138,Ingredient,Mala Sauce (Very Spicy " +
            "Base),Mala,4,257,0.6,27.7,4.8,1,2,0.5,2",
        "139,Ingredient,Mala Sauce (Spicy Bas" +
            "e),Mala,4,191,0.6,20.2,3.5,1,2,0.5,2",
        "140,Ingredient,Mala Sauce (Slightly Spicy" +
            " Base),Mala,4,95,0.3,10.1,1.7,0.5,1,0.2,1",
        "141,Dish,White Chicken Rice,Chicken Ri" +
            "ce,12,533,19.5,17.3,5.7,1.6,74.6,6,4991",
        "142,Dish,Char Siew Rice,Chicken Rice,1" +
            "2,697,21.9,29.6,11.2,1.6,86.1,16.7,3748",
        "143,Dish,Roasted Pork Rice,Chicken Ric" +
            "e,12,680,22,29.6,11.1,3.1,80.3,5.6,4497",
        "144,Dish,Roasted Chicken Noodles (Egg Noodles) - Reg" +
            "ular,Chicken Rice,12,320,17.1,7.7,2,2.9,43.3,2.3,1600",
        "145,Dish,Roasted Chicken Noodles (Egg Noodles) - Lar" +
            "ge,Chicken Rice,12,388,25.3,11.5,3.2,3,43.6,2.3,1724",
        "146,Dish,Roasted Chicken Noodles (Hor Fun) - Regula" +
            "r,Chicken Rice,12,341,12.8,6.9,2.5,1.5,56.7,1.7,1613",
        "147,Dish,Roasted Chicken Noodles (Hor Fun) - La" +
            "rge,Chicken Rice,12,21,10.7,3.7,1.7,57,1.7,1738,",
        "148,Dish,White Chicken Noodles (Egg Noodles) - Regul" +
            "ar,Chicken Rice,12,307,15.2,7.5,2.2,2.7,42.4,1.7,2583",
        "149,Dish,White Chicken Noodles (Egg Noodles) - Large" +
            ",Chicken Rice,12,365,21.3,11.1,3.6,2.7,42.4,1.7,2633",
        "150,Dish,White Chicken Noodles (Hor Fun) - Regula" +
            "r,Chicken Rice,12,328,10.9,6.7,2.7,1.4,55.8,1,2596",
        "151,Dish,White Chicken Noodles (Hor Fun) - Large" +
            ",Chicken Rice,12,386,17,10.4,4.1,1.4,55.8,1,2646",
        "152,Dish,Char Siew Noodles (Egg Noodles) - Regular" +
            ",Chicken Rice,12,410,16.4,13.6,5,2.7,53.5,12.4,1254",
        "153,Dish,Char Siew Noodles (Egg Noodles) - Large,C" +
            "hicken Rice,12,529,23.7,23.4,9.1,2.7,53.8,12.4,1390",
        "154,Dish,Char Siew Noodles (Hor Fun) - Regular,Ch" +
            "icken Rice,12,431,12.1,12.9,5.5,1.4,66.9,11.7,1268",
        "155,Dish,Char Siew Noodles (Hor Fun) - Large,Chi" +
            "cken Rice,12,550,19.4,22.7,9.6,1.4,67.2,11.7,1404",
        "156,Dish,Roasted Pork Noodles (Egg Noodles) - Regul" +
            "ar,Chicken Rice,12,395,16.6,13.9,5,4.2,47.8,1.4,2006",
        "157,Dish,Roasted Pork Noodles (Egg Noodles) - Large" +
            ",Chicken Rice,12,511,23.7,23.4,9.1,4.2,48.1,1.4,2139",
        "158,Dish,Roasted Pork Noodles (Hor Fun) - Regular," +
            "Chicken Rice,12,416,12.3,13.1,5.5,2.8,61.2,0.7,2020",
        "159,Dish,Roasted Pork Noodles (Hor Fun) - Large,C" +
            "hicken Rice,12,533,19.4,22.7,9.6,2.8,61.4,0.7,2153",
        "160,Dish,Roasted Chicken Rice + Veg + Half Egg Set Me" +
            "al,Chicken Rice,12,604,28.1,21.2,5.7,2.4,75.4,6.1,4243",
        "161,Dish,White Chicken Rice + Veg + Half Egg Set Mea" +
            "l,Chicken Rice,12,582,24.1,20.9,6.1,2.1,74.2,5.5,5151",
        "162,Dish,Char Siew Rice + Veg + Half Egg Set Meal,C" +
            "hicken Rice,12,746,26.5,33.2,11.6,2.1,85.6,16.2,3909",
        "163,Dish,Roasted Pork Rice + Veg + Half Egg Set Meal" +
            ",Chicken Rice,12,728,26.5,33.2,11.6,3.6,79.9,5.1,4658",
        "164,Dish,Curry Chicken Bee Hoon,Chicken R" +
            "ice,12,744,15.8,34.7,26.9,4.9,90.9,30,6334",
        "165,Dish,Curry Chicken Noodles,Chicken Ri" +
            "ce,12,777,21.7,37.8,27.6,6,85.8,28.7,6274",
        "166,Dish,Chicken Shredded Porridge,Chick" +
            "en Rice,12,8.3,3.8,1.4,0.2,20.6,0.9,2158,",
        "167,Dish,Roasted Pork Rice Soup,Chicke" +
            "n Rice,12,9.7,10.2,4.2,1.7,26,0.6,1582,",
        "168,Side,Roasted Chicken,Chicken " +
            "Rice,12,8.2,3.9,1.2,0.2,1.2,1,695,",
        "169,Side,White Chicken,Chicken Ri" +
            "ce,12,6.3,3.7,1.4,0,0.3,0.3,1678,",
        "170,Side,Char Siew,Chicken Ric" +
            "e,12,7.5,9.8,4.2,0,11.4,11,350,",
        "171,Side,Roasted Pork,Chicken Rice" +
            ",12,264,14.9,19.6,8.2,1.5,6,0,1235",
        "172,Side,Veg 1 (Cai Xin),Chicken " +
            "Rice,12,0.8,0.1,0,0.7,0.4,0.5,258,",
        "173,Side,Veg 2 (Nonya Chap Chye),Chick" +
            "en Rice,12,2.1,1.1,0.2,0.6,1.3,0.7,247,",
        "174,Side,Braised Egg,Chicken " +
            "Rice,12,8,7,0.9,0,0.6,0.6,296,",
        "175,Side,Sausage,Chicken Rice," +
            "12,6.2,9.6,3.8,0.4,2.4,0.5,322,",
        "176,Side,Liver + Gizzards,Chicken" +
            " Rice,12,17.5,2.6,0.8,0,0.3,0,34,",
        "177,Side,Roasted Chicken (1 Pax),Chick" +
            "en Rice,12,16.4,7.7,2.4,0.3,1.5,1,820,",
        "178,Side,Roasted Chicken (2 Pax),Chicke" +
            "n Rice,12,32.8,15.4,4.7,0.7,3,2.1,1679,",
        "179,Side,Roasted Chicken (3 Pax),Chicke" +
            "n Rice,12,49.3,23.1,7.1,1,4.5,3.1,2518,",
        "180,Side,White Chicken (1 Pax),Chicke" +
            "n Rice,12,12.5,7.4,2.8,0,0.3,0.3,1729,",
        "181,Side,White Chicken (2 Pax),Chicken" +
            " Rice,12,24.9,14.7,5.6,0,0.6,0.7,3445,",
        "182,Side,White Chicken (3 Pax),Chicke" +
            "n Rice,12,37.4,22.1,8.4,0,0.8,1,5167,",
        "183,Side,Plain Chicken Rice,Chicken Ri" +
            "ce,12,411,6.9,10,2.9,1.4,73.4,4.7,3258",
        "184,Side,White Rice,Chicken Rice" +
            ",12,5.9,1.1,0.2,0.8,65.8,0.2,11,",
    };

    public static String[] getFoodData() {
        return foodData;
    }
}
