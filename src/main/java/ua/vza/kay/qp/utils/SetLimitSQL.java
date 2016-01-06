package ua.vza.kay.qp.utils;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by kycenko on 10.08.15.
 */
public class SetLimitSQL {
    private static final int COUNT_ELEMTS_ON_PAGE = 20;

    public static List doLimitSQL(Query query, int pageNumber){
        query.setFirstResult((pageNumber - 1) * COUNT_ELEMTS_ON_PAGE);
        query.setMaxResults(COUNT_ELEMTS_ON_PAGE);
        return query.getResultList();
    }
}
