if __name__ == "__main__":
    TAB = "    "

    with open("FoodData.csv", "r") as data_file, open("FoodData.java", "w") as write_file:
        write_file.write("package seedu.database;\n\n")
        write_file.write("public final class FoodData {\n")
        write_file.write(TAB +"private static final String[] foodData = {\n")
        foods = data_file.readlines()
        for food in foods:
            food = food.replace('\n', '')
            food_1, food_2 = food[:len(food)//2], food[len(food)//2:]
            write_file.write(TAB + TAB + f'"{food_1}" +\n' + TAB + TAB + TAB + f'"{food_2}",\n')
        write_file.write(TAB + "};\n\n")
        write_file.write(TAB + "public static String[] getFoodData() {\n")
        write_file.write(TAB + TAB + "return foodData;\n")
        write_file.write(TAB + "}\n")
        write_file.write("}\n")
