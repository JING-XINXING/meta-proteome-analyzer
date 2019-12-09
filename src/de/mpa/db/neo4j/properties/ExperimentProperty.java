package de.mpa.db.neo4j.properties;

public enum ExperimentProperty implements ElementProperty {
	IDENTIFIER("Experiment Title"),
	PROJECTTITLE("Project Title");
	
	ExperimentProperty(String propertyName){
		this.propertyName = propertyName;
	}
	
	private final String propertyName;
	
	@Override
	public String toString() {
		return this.propertyName;
	}
}