package io.droidme.camelrest.tds;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/securities")
public class SecurityController {

    @GetMapping("{isin}")
    public Security getSecurityByIsin(@PathVariable("isin") String isin) {
        return new Security(isin, "Beispiel Wertpapier", "Beispiel Beschreibung" );
    }

}
