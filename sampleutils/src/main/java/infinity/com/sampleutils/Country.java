package infinity.com.sampleutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Country {
    private List<String> codes = new ArrayList<>();

    public Country (){
        codes.addAll(Arrays.asList(
                "az",
                "am",
                "by",
                "kz",
                "kg",
                "ru",
                "tj",
                "tm",
                "uz",
                "ua",
                "AZ",
                "AM",
                "BY",
                "KZ",
                "KG",
                "RU",
                "TJ",
                "TM",
                "UZ",
                "UA"));
    }

    public List<String> getCodes() {
        return codes;
    }
}
