import pandas as pd
import json

file_path = "FoodData.csv"
save_path = "foodData.json"

if __name__ == "__main__":
    df = pd.read_csv(file_path)
    result = df.to_json(orient="index")
    parsed = json.loads(result)
    with open(save_path, 'w', encoding='utf-8') as f:
        json.dump(parsed, f, ensure_ascii=False, indent=4)