package io.droidme.camelrest.tds;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/securities")
public class SecurityController {

    @GetMapping("{isin}")
    public Security getSecurityByIsin(@PathVariable("isin") String isin) {
        return new Security(isin, "Beispiel Wertpapier", "Beispiel Beschreibung" );
    }

    @GetMapping("/query")
    public Security getSecurityByIsinAndCcy(@RequestParam("isin") String isin, @RequestParam("ccy") String ccy) {
        return new Security(isin, "Beispiel Wertpapier Ccy " + ccy, "Beispiel Wertpapier" );
    }

}
