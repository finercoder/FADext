package adapterDecorator;

import config.IConfiguration;

public class AdapterDetectorConfiguration implements IAdapterDecoratorConfiguration {
    public static final String CONFIG_PATH = "adapter.";
    public static final String FILL_COLOR = CONFIG_PATH + "fillColor";
    public static final String PARENT_STEREOTYPE = CONFIG_PATH + "parentStereotype";
    public static final String CHILD_COMPOSED_RELATIONSHIP_LABEL = CONFIG_PATH + "childComposedRelationshipLabel";
    public static final String ADAPTEE_STEREOTYPE = CONFIG_PATH + "adapteeStereotype";
    public static final String CHILD_STEREOTYPE = CONFIG_PATH + "childStereotype";

    private IConfiguration config;

    @Override
    public void setup(IConfiguration config) {
        this.config = config;
        this.config.setIfMissing(AdapterDetectorConfiguration.FILL_COLOR, "red");
        this.config.setIfMissing(AdapterDetectorConfiguration.PARENT_STEREOTYPE, "target");
        this.config.setIfMissing(AdapterDetectorConfiguration.CHILD_COMPOSED_RELATIONSHIP_LABEL, "<<adapts>>");
        this.config.setIfMissing(AdapterDetectorConfiguration.ADAPTEE_STEREOTYPE, "adaptee");
        this.config.setIfMissing(AdapterDetectorConfiguration.CHILD_STEREOTYPE, "adapter");
    }

    @Override
    public String getFillColor() {
        return this.config.getValue(AdapterDetectorConfiguration.FILL_COLOR);
    }

    @Override
    public String getParentStereotype() {
        return this.config.getValue(AdapterDetectorConfiguration.PARENT_STEREOTYPE);
    }

    @Override
    public String getChildStereotype() {
        return this.config.getValue(AdapterDetectorConfiguration.CHILD_STEREOTYPE);
    }

    @Override
    public String getChildParentRelationshipLabel() {
        return "";
    }

    @Override
    public String getComposedStereotype() {
        return this.config.getValue(AdapterDetectorConfiguration.ADAPTEE_STEREOTYPE);
    }

    @Override
    public String getChildComposedRelationshipLabel() {
        return this.config.getValue(AdapterDetectorConfiguration.CHILD_COMPOSED_RELATIONSHIP_LABEL);
    }
}
