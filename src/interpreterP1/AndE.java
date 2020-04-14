
package interpreterP1;

import java.util.Map;

public class AndE extends FunExp
{
    AndE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "and";
    }
}