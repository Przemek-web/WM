package pl.lodz.p.it.isdp.wm.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-08T09:46:33")
@StaticMetamodel(Issue.class)
public class Issue_ { 

    public static volatile SingularAttribute<Issue, Integer> quantity;
    public static volatile SingularAttribute<Issue, String> productSymbol;
    public static volatile SingularAttribute<Issue, String> contractorNumber;
    public static volatile SingularAttribute<Issue, BigDecimal> price;
    public static volatile SingularAttribute<Issue, String> description;
    public static volatile SingularAttribute<Issue, Long> id;
    public static volatile SingularAttribute<Issue, String> login;
    public static volatile SingularAttribute<Issue, Date> creationDate;
    public static volatile SingularAttribute<Issue, String> locationSymbol;

}