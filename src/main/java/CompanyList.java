import eventus.company.Company;

import java.util.ArrayList;

public class CompanyList {

    ArrayList<Company> companies = new ArrayList<>();

    public void list(){
        if (companies.size() == 0){
            System.out.println("You do not have any companies in this list");
        }
        else {
            for (int i = 0; i < companies.size(); i += 1) {
                System.out.println((i+1) + ". " + companies.get(1));

            }
        }
    }
}
