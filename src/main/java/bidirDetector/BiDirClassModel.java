package bidirDetector;

import analyzer.utility.IClassModel;
import analyzer.utility.IClassModelFilter;

public class BiDirClassModel extends IClassModelFilter{

	private BiDirConfiguration config;
	
	public BiDirClassModel(IClassModel classModel, BiDirConfiguration config) {
		super(classModel);
		this.config = config;
	}
	
	@Override
    public String getNodeStyle() {
        return String.format("%s color=\"%s\"", super.getNodeStyle(), this.config.getBiDirColor());
    }
}
