package BidirDetector;

import analyzer.utility.IAnalyzer;
import analyzer.utility.ISystemModel;
import config.IConfiguration;

/**
 * Created by lamd on 1/14/2017.
 */
public class BiDirAnalyzer implements IAnalyzer {
    @Override
    public ISystemModel analyze(ISystemModel systemModel, IConfiguration config) {
        BiDirConfiguration biDirConfig = config.createConfiguration(BiDirConfiguration.class);
        return new BiDirSystemModel(systemModel, biDirConfig);
    }
}
