package bidirDetector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import analyzer.relationParser.RelationBijectiveDecorator;
import analyzer.relationParser.RelationHasABijective;
import analyzer.utility.ClassPair;
import analyzer.utility.IClassModel;
import analyzer.utility.IRelationInfo;
import analyzer.utility.ISystemModel;
import analyzer.utility.ISystemModelFilter;

/**
 * Created by lamd on 1/15/2017.
 */
public class BiDirSystemModel extends ISystemModelFilter {
    private BiDirConfiguration config;
    private Map<ClassPair, List<IRelationInfo>> relations = new HashMap<>();;
    private Collection<IClassModel> classes = new ArrayList<>();

    BiDirSystemModel(ISystemModel systemModel, BiDirConfiguration biDirConfig) {
        super(systemModel);
        this.config = biDirConfig;
        processData();
    }
    
//    @Override
//    public Iterable<Relation> getEdges() {
//    	List<Relation> analyzedRelations = new ArrayList<>();
//    	super.getEdges().forEach((r) -> {
//    		IRelationInfo ri = r.getInfo();
//    		if (isBiDir(ri)) {
//    			analyzedRelations.add(new Relation(r.getClassPair(), new ColoredBijectiveDecorator((RelationBijectiveDecorator)ri,this.config)));
//    		} else {
//    			analyzedRelations.add(r);
//    		}
//    	});   	
//    	
//    	return analyzedRelations;
//    }
    
    @Override
    public Map<ClassPair, List<IRelationInfo>> getRelations() {
    	return this.relations;
//    	Map<ClassPair, List<IRelationInfo>> crelations = super.getRelations();
//    	
//    	// Identify the pairs of the bijective relations.
//    	super.getRelations().forEach((c,r) -> {
//    		List<IRelationInfo> relations = new ArrayList<>();
//    		for(IRelationInfo ri: r){
//	    		if (isBiDir(ri)) {
//	    			relations.add(new ColoredBijectiveDecorator((RelationBijectiveDecorator)ri,this.config));
//	    		} else {
//	    			relations.add(ri);
//	    		}
//    		}
//    		crelations.put(c, relations);
//    	});
//    	return crelations;
    }
    
    @Override
    public Collection<? extends IClassModel> getClasses() {
    	return this.classes;
//    	Collection<IClassModel> analyzedClass = new ArrayList<>();
//    	List<String> classList = new ArrayList<>();
//    	
//    	super.getRelations().forEach((c,r)->{
//    		for(IRelationInfo ri: r){
//    			if (isBiDir(ri)){
//    				classList.add(c.getFrom().toString());
//    				classList.add(c.getTo().toString());
//    				break;
//    			}
//    		}
//    	});
//    	
//        super.getClasses().forEach((c) -> {
//            if (classList.contains(c.toString())) {
//                analyzedClass.add(new BiDirClassModel(c, config));
//            } else {
//                analyzedClass.add(c);
//            }
//        });
//        return analyzedClass;
    }
    
    private boolean isBiDir(IRelationInfo relation) {
    	return relation.getClass().getName().equals(RelationBijectiveDecorator.class.getName()) || relation.getClass().getName().equals(RelationHasABijective.class.getName());
    }
    
    private void processData(){
    	Set<IClassModel> flaggedClassModel = new HashSet<>();

        super.getRelations().forEach((pair,infos) -> {
        	List<IRelationInfo> newInfos = new LinkedList<>();
            for (IRelationInfo info : infos) {
                if (isBiDir(info)) {
                    flaggedClassModel.add(pair.getFrom());
                    flaggedClassModel.add(pair.getTo());
                    newInfos.add(new ColoredBijectiveDecorator(info, config));
                } else {
                    newInfos.add(info);
                }
            }

            this.relations.put(pair, newInfos);
        });

        this.classes = new LinkedList<>();
        super.getClasses().forEach((c) -> {
            if (flaggedClassModel.contains(c)) {
                classes.add(new BiDirClassModel(c, config));
            } else {
                classes.add(c);
            }
        });
    }

}
