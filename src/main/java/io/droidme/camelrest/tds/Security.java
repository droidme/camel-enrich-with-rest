package io.droidme.camelrest.tds;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;

@Data
public class Security {
    private String isin;
    private String name;
    private String description;

    public Security() {
    }

    public Security(String isin, String name, String description) {
        this.isin = isin;
        this.name = name;
        this.description = description;
    }
}
