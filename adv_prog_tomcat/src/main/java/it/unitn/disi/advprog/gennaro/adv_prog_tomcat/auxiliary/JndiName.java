package it.unitn.disi.advprog.gennaro.adv_prog_tomcat.auxiliary;

public enum JndiName {

    STUDENT("ejb:/adv_prog_wildfly-1.0-SNAPSHOT/StudentManagerBean!" +
            "it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers.StudentManagerFacade"),
    ADVISOR("ejb:/adv_prog_wildfly-1.0-SNAPSHOT/AdvisorChoiceManagerBean!" +
            "it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers.AdvisorChoiceManagerFacade");

    private final String jndiName;

    JndiName(String jndiName){
        this.jndiName = jndiName;
    }

    public String getJndiName(){
        return this.jndiName;
    }

}
