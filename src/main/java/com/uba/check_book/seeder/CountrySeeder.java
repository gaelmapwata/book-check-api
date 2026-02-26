package com.uba.check_book.seeder;

import com.uba.check_book.entity.Country;
import com.uba.check_book.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class CountrySeeder implements CommandLineRunner {
    private final CountryRepository countryRepository;

    public CountrySeeder(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (countryRepository.count() > 0) {
            return;
        }

        List<Country> countries = List.of(
                createCountry("Congo, The Democratic Republic of the", "CD", "COD", "+243"),
                createCountry("Nigeria", "NG", "NGA", "+234"),
                createCountry("Kenya", "KE", "KEN", "+254"),
                createCountry("Ghana", "GH", "GHA", "+233"),
                createCountry("South Africa", "ZA", "ZAF", "+27"),
                createCountry("Cameroon", "CM", "CMR", "+237"),
                createCountry("Senegal", "SN", "SEN", "+221"),
                createCountry("Ivory Coast", "CI", "CIV", "+225"),
                createCountry("Rwanda", "RW", "RWA", "+250"),
                createCountry("Uganda", "UG", "UGA", "+256"),
                createCountry("Burkina Faso", "BF", "BFA", "+226"),
                createCountry("Benin", "BJ", "BEN", "+229"),
                createCountry("Mali", "ML", "MLI", "+223")
        );

        countryRepository.saveAll(countries);

        System.out.println("10 African countries seeded successfully ✅");
    }

    private Country createCountry(String label, String code2, String code3, String telPrefix) {
        Country country = new Country();
        country.setLabel(label);
        country.setCode2(code2);
        country.setCode3(code3);
        country.setTelPrefixNum(telPrefix);
        return country;
    }
}
