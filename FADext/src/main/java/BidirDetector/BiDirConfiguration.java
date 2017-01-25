package bidirDetector;

import config.Configurable;
import config.IConfiguration;

public class BiDirConfiguration implements Configurable {
	public static final String CONFIG_PATH = "bidir.";
    public static final String FAVOR_COM_COLOR = CONFIG_PATH + "color";

    private IConfiguration config;

    @Override
    public void setup(IConfiguration config) {
        this.config = config;
        this.config.setIfMissing(BiDirConfiguration.FAVOR_COM_COLOR, "red");
    }

    public String getBiDirColor() {
        return this.config.getValue(BiDirConfiguration.FAVOR_COM_COLOR);
    }
}
