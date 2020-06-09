package dev.ravi.bbgreport;

import java.io.FileReader;
import java.io.IOException;
import java.util.PropertyResourceBundle;

public class Settings  extends PropertyResourceBundle {

    private static Settings instance =null;
    private Settings() throws IOException {
        super(new FileReader(Constants.PROPERTIES));
    }

    public static Settings get() throws IOException {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
