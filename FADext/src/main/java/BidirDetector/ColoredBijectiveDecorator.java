package bidirDetector;

import analyzer.relationParser.RelationBijectiveDecorator;
import analyzer.utility.IRelationInfo;

public class ColoredBijectiveDecorator implements IRelationInfo {

	private IRelationInfo decorated;
	private BiDirConfiguration config;
	
	public ColoredBijectiveDecorator(IRelationInfo decorated, BiDirConfiguration config) {
		this.decorated = decorated;
		this.config = config;
	}
	
	/**
     * Returns the RelationInfo it decorates.
     *
     * @return IRelationInfo decorated.
     */
    IRelationInfo getDecorated() {
        return decorated;
    }

    @Override
    public String toString() {
        return "colored-" + decorated.toString();
    }

    @Override
    public String getEdgeStyle() {
        return String.format("%s color=\"%s\"", decorated.getEdgeStyle(), this.config.getBiDirColor());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == ColoredBijectiveDecorator.class) {
            ColoredBijectiveDecorator x = (ColoredBijectiveDecorator) obj;
            return x.decorated.equals(this.decorated);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return decorated.hashCode();
    }

}
